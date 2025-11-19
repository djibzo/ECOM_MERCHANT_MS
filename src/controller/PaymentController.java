public class PaymentController {
    package com.groupe4.ECOM_MERCHANT_MS.controller;

import com.groupe4.ECOM_MERCHANT_MS.dto.PaymentRequest;
import com.groupe4.ECOM_MERCHANT_MS.dto.PaymentProcessRequest;
import com.groupe4.ECOM_MERCHANT_MS.entity.Payment;
import com.groupe4.ECOM_MERCHANT_MS.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST pour gérer les paiements
 * Expose les endpoints de l'API REST
 * @author Groupe 4
 */
@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Permet les requêtes depuis n'importe quelle origine
public class PaymentController {
    
    private final PaymentService paymentService;
    
    /**
     * Créer un nouveau paiement
     * POST /api/payments
     */
    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody PaymentRequest request) {
        try {
            Payment payment = paymentService.createPayment(
                request.getCustomerId(),
                request.getAmount(),
                request.getPaymentMethod(),
                request.getPhoneNumber(),
                request.getDescription()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(payment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    /**
     * Traiter un paiement avec code de vérification
     * POST /api/payments/{paymentCode}/process
     */
    @PostMapping("/{paymentCode}/process")
    public ResponseEntity<Payment> processPayment(
            @PathVariable String paymentCode,
            @RequestBody PaymentProcessRequest request) {
        try {
            Payment payment = paymentService.processPayment(
                paymentCode, 
                request.getVerificationCode()
            );
            return ResponseEntity.ok(payment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    /**
     * Obtenir un paiement par code
     * GET /api/payments/{paymentCode}
     */
    @GetMapping("/{paymentCode}")
    public ResponseEntity<Payment> getPayment(@PathVariable String paymentCode) {
        try {
            Payment payment = paymentService.getPaymentByCode(paymentCode);
            return ResponseEntity.ok(payment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    /**
     * Obtenir les paiements d'un client
     * GET /api/payments/customer/{customerId}
     */
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Payment>> getCustomerPayments(@PathVariable Long customerId) {
        List<Payment> payments = paymentService.getCustomerPayments(customerId);
        return ResponseEntity.ok(payments);
    }
    
    /**
     * Obtenir tous les paiements (admin)
     * GET /api/payments
     */
    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }
    
    /**
     * Annuler un paiement
     * POST /api/payments/{paymentCode}/cancel
     */
    @PostMapping("/{paymentCode}/cancel")
    public ResponseEntity<Payment> cancelPayment(@PathVariable String paymentCode) {
        try {
            Payment payment = paymentService.cancelPayment(paymentCode);
            return ResponseEntity.ok(payment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}

}
