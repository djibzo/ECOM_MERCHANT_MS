public class PaymentRequest {
    package com.groupe4.ECOM_MERCHANT_MS.dto;

import com.groupe4.ECOM_MERCHANT_MS.entity.Payment.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour créer un nouveau paiement
 * Utilisé par l'API REST
 * @author Groupe 4
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    // ID du client effectuant le paiement
    private Long customerId;
    
    // Montant à payer
    private Double amount;
    
    // Méthode de paiement choisie
    private PaymentMethod paymentMethod;
    
    // Numéro de téléphone (pour Wave, Orange Money, etc.)
    private String phoneNumber;
    
    // Description optionnelle du paiement
    private String description;
}
    
}
