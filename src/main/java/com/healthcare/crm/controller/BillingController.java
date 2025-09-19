package com.healthcare.crm.controller;

import com.healthcare.crm.dto.BillingRequest;
import com.healthcare.crm.entity.Billing;
import com.healthcare.crm.service.BillingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/billing")
@RequiredArgsConstructor
public class BillingController {

    private final BillingService billingService;

    // ✅ Create Billing
    @PostMapping
    public ResponseEntity<Billing> createBilling(@RequestBody BillingRequest request) {
        return ResponseEntity.ok(billingService.createBilling(request));
    }

    // ✅ Get All Billings
    @GetMapping
    public ResponseEntity<List<Billing>> getAllBillings() {
        return ResponseEntity.ok(billingService.getAllBillings());
    }

    // ✅ Get Billing by ID
    @GetMapping("/{id}")
    public ResponseEntity<Billing> getBillingById(@PathVariable Long id) {
        return billingService.getBillingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Delete Billing
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBilling(@PathVariable Long id) {
        return billingService.deleteBilling(id) ?
                ResponseEntity.noContent().<Void>build() :
                ResponseEntity.notFound().build();
    }
}
