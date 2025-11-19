public class PaymentService {
    package com.groupe4.ECOM_MERCHANT_MS.service;

import com.groupe4.ECOM_MERCHANT_MS.entity.Payment;
import com.groupe4.ECOM_MERCHANT_MS.entity.Payment.PaymentMethod;
import com.groupe4.ECOM_MERCHANT_MS.entity.Payment.PaymentStatus;
import com.groupe4.ECOM_MERCHANT_MS.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Service gérant la logique métier des paiements
 * Contient toutes les opérations possibles sur les paiements
 * @author Groupe 4
 */
@Service
@RequiredArgsConstructor
public class PaymentService {
    
    private final PaymentRepository paymentRepository;
    
    /**
     * Créer un nouveau paiement
     * @param customerId ID du client
     * @param amount Montant à payer
     * @param paymentMethod Méthode de paiement
     * @param phoneNumber Numéro de téléphone (optionnel)
     * @param description Description du paiement (optionnel)
     * @return Le paiement créé
     */
    @Transactional
    public Payment createPayment(Long customerId, Double amount, 
                                PaymentMethod paymentMethod, 
                                String phoneNumber, String description) {
        
        // Créer une nouvelle instance de Payment
        Payment payment = new Payment();
        payment.setPaymentCode(generatePaymentCode());
        payment.setCustomerId(customerId);
        payment.setAmount(amount);
        payment.setPaymentMethod(paymentMethod);
        payment.setPhoneNumber(phoneNumber);
        payment.setDescription(description);
        payment.setStatus(PaymentStatus.PENDING);
        payment.setTransactionReference(generateTransactionReference());
        
        // Sauvegarder et retourner le paiement
        return paymentRepository.save(payment);
    }
    
    /**
     * Traiter un paiement avec code de vérification
     * Simule la vérification avec un opérateur mobile (Wave, Orange Money, etc.)
     * @param paymentCode Code du paiement à traiter
     * @param verificationCode Code de vérification du client
     * @return Le paiement mis à jour
     */
    @Transactional
    public Payment processPayment(String paymentCode, String verificationCode) {
        // Récupérer le paiement
        Payment payment = paymentRepository.findByPaymentCode(paymentCode)
            .orElseThrow(() -> new RuntimeException("Paiement non trouvé avec le code: " + paymentCode));
        
        // Vérifier que le paiement est en attente
        if (payment.getStatus() != PaymentStatus.PENDING) {
            throw new RuntimeException("Ce paiement ne peut plus être traité. Statut actuel: " + payment.getStatus());
        }
        
        // TODO: Intégration réelle avec les APIs (Wave, Orange Money, etc.)
        // Pour l'instant, on simule la vérification
        if (verificationCode != null && verificationCode.length() >= 4) {
            // Paiement réussi
            payment.setStatus(PaymentStatus.COMPLETED);
            payment.setPaidAt(LocalDateTime.now());
        } else {
            // Paiement échoué
            payment.setStatus(PaymentStatus.FAILED);
        }
        
        return paymentRepository.save(payment);
    }
    
    /**
     * Obtenir un paiement par son code
     * @param paymentCode Code du paiement
     * @return Le paiement trouvé
     */
    public Payment getPaymentByCode(String paymentCode) {
        return paymentRepository.findByPaymentCode(paymentCode)
            .orElseThrow(() -> new RuntimeException("Paiement non trouvé avec le code: " + paymentCode));
    }
    
    /**
     * Obtenir tous les paiements d'un client
     * @param customerId ID du client
     * @return Liste des paiements du client
     */
    public List<Payment> getCustomerPayments(Long customerId) {
        return paymentRepository.findByCustomerId(customerId);
    }
    
    /**
     * Obtenir tous les paiements (pour l'administration)
     * @return Liste de tous les paiements
     */
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
    
    /**
     * Annuler un paiement en attente
     * @param paymentCode Code du paiement à annuler
     * @return Le paiement annulé
     */
    @Transactional
    public Payment cancelPayment(String paymentCode) {
        Payment payment = paymentRepository.findByPaymentCode(paymentCode)
            .orElseThrow(() -> new RuntimeException("Paiement non trouvé avec le code: " + paymentCode));
        
        // Vérifier que le paiement peut être annulé
        if (payment.getStatus() != PaymentStatus.PENDING) {
            throw new RuntimeException("Seuls les paiements en attente peuvent être annulés");
        }
        
        payment.setStatus(PaymentStatus.CANCELLED);
        return paymentRepository.save(payment);
    }
    
    /**
     * Générer un code de paiement unique
     * Format: PAY-XXXXXXXX
     * @return Code de paiement généré
     */
    private String generatePaymentCode() {
        return "PAY-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
    
    /**
     * Générer une référence de transaction unique
     * Format: TXN-timestamp-XXXXXX
     * @return Référence de transaction générée
     */
    private String generateTransactionReference() {
        return "TXN-" + System.currentTimeMillis() + "-" + 
               UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }
}
}
