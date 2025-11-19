public class PaymentInput {
    package com.groupe4.ECOM_MERCHANT_MS.dto;

import com.groupe4.ECOM_MERCHANT_MS.entity.Payment.PaymentMethod;
import lombok.Data;

/**
 * DTO pour GraphQL - Création de paiement
 * @author Groupe 4
 */
@Data
public class PaymentInput {
    private Long customerId;
    private Double amount;
    private PaymentMethod paymentMethod;
    private String phoneNumber;
    private String description;
}

}
