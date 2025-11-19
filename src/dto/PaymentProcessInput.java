public class PaymentProcessInput {
    
    package com.groupe4.ECOM_MERCHANT_MS.dto;

import lombok.Data;

/**
 * DTO pour GraphQL - Traitement de paiement
 * @author Groupe 4
 */
@Data
public class PaymentProcessInput {
    private String paymentCode;
    private String verificationCode;
}
}
