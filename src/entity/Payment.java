public class Payment {
    package com.groupe4.ECOM_MERCHANT_MS.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * Entité représentant un paiement dans le système
 * @author Groupe 4
 * @version 1.0
 */
@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Code unique du paiement (ex: PAY-ABC12345)
    @Column(nullable = false, unique = true)
    private String paymentCode;
    
    // ID du client effectuant le paiement
    @Column(nullable = false)
    private Long customerId;
    
    // Montant du paiement
    @Column(nullable = false)
    private Double amount;
    
    // Devise (XOF par défaut pour le Sénégal)
    @Column(nullable = false)
    private String currency = "XOF";
    
    // Statut du paiement
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status = PaymentStatus.PENDING;
    
    // Méthode de paiement utilisée
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod;
    
    // Référence de la transaction
    private String transactionReference;
    
    // Description du paiement
    private String description;
    
    // Date de création du paiement
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    
    // Date de paiement effectif
    private LocalDateTime paidAt;
    
    // Numéro de téléphone pour Wave, Orange Money, etc.
    private String phoneNumber;
    
    // 4 derniers chiffres de la carte (pour les paiements par carte)
    private String cardLastFourDigits;
    
    /**
     * Énumération des statuts possibles d'un paiement
     */
    public enum PaymentStatus {
        PENDING,      // En attente
        PROCESSING,   // En cours de traitement
        COMPLETED,    // Complété avec succès
        FAILED,       // Échoué
        CANCELLED,    // Annulé
        REFUNDED      // Remboursé
    }
    
    /**
     * Énumération des méthodes de paiement supportées
     */
    public enum PaymentMethod {
        WAVE,           // Wave Sénégal
        ORANGE_MONEY,   // Orange Money
        FREE_MONEY,     // Free Money
        CARD,           // Carte bancaire
        CASH,           // Espèces
        BANK_TRANSFER   // Virement bancaire
    }
}
    
}
