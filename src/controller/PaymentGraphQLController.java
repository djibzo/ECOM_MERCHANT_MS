public class PaymentGraphQLController {
    package com.groupe4.ECOM_MERCHANT_MS.controller;

import com.groupe4.ECOM_MERCHANT_MS.dto.PaymentInput;
import com.groupe4.ECOM_MERCHANT_MS.dto.PaymentProcessInput;
import com.groupe4.ECOM_MERCHANT_MS.entity.Payment;
import com.groupe4.ECOM_MERCHANT_MS.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Contrôleur GraphQL pour gérer les paiements
 * Expose les requêtes et mutations GraphQL
 * @author Groupe 4
 */
@Controller
@RequiredArgsConstructor
public class PaymentGraphQLController {
    
    private final PaymentService paymentService;
    
    /**
     * Query: Obtenir un paiement par code
     */
    @QueryMapping
    public Payment getPayment(@Argument String paymentCode) {
        return paymentService.getPaymentByCode(paymentCode);
    }
    
    /**
     * Query: Obtenir les paiements d'un client
     */
    @QueryMapping
    public List<Payment> getCustomerPayments(@Argument Long customerId) {
        return paymentService.getCustomerPayments(customerId);
    }
    
    /**
     * Query: Obtenir tous les paiements
     */
    @QueryMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }
    
    /**
     * Mutation: Créer un nouveau paiement
     */
    @MutationMapping
    public Payment createPayment(@Argument PaymentInput input) {
        return paymentService.createPayment(
            input.getCustomerId(),
            input.getAmount(),
            input.getPaymentMethod(),
            input.getPhoneNumber(),
            input.getDescription()
        );
    }
    
    /**
     * Mutation: Traiter un paiement
     */
    @MutationMapping
    public Payment processPayment(@Argument PaymentProcessInput input) {
        return paymentService.processPayment(
            input.getPaymentCode(),
            input.getVerificationCode()
        );
    }
    
    /**
     * Mutation: Annuler un paiement
     */
    @MutationMapping
    public Payment cancelPayment(@Argument String paymentCode) {
        return paymentService.cancelPayment(paymentCode);
    }
}
}
