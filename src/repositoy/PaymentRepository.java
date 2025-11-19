public class PaymentRepository {
    package com.groupe4.ECOM_MERCHANT_MS.repository;

import com.groupe4.ECOM_MERCHANT_MS.entity.Payment;
import com.groupe4.ECOM_MERCHANT_MS.entity.Payment.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository pour gérer les opérations en base de données des paiements
 * Utilise Spring Data JPA pour générer automatiquement les implémentations
 * @author Groupe 4
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    
    // Trouver un paiement par son code unique
    Optional<Payment> findByPaymentCode(String paymentCode);
    
    // Récupérer tous les paiements d'un client
    List<Payment> findByCustomerId(Long customerId);
    
    // Récupérer tous les paiements avec un statut donné
    List<Payment> findByStatus(PaymentStatus status);
    
    // Récupérer les paiements d'un client avec un statut spécifique
    List<Payment> findByCustomerIdAndStatus(Long customerId, PaymentStatus status);
    
    // Trouver un paiement par sa référence de transaction
    Optional<Payment> findByTransactionReference(String transactionReference);
}
}
