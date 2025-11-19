public class PaymentProcessRequest {
    package com.groupe4.ECOM_MERCHANT_MS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour traiter un paiement avec code de vérification
 * Le client entre ce code reçu par SMS/notification
 * @author Groupe 4
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentProcessRequest {
    // Code de vérification reçu par le client
    private String verificationCode;
}

}
