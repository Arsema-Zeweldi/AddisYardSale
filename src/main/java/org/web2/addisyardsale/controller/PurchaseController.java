package org.web2.addisyardsale.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.web2.addisyardsale.dto.PurchaseRequest;
import org.web2.addisyardsale.dto.PurchaseResponse;
import org.web2.addisyardsale.model.Purchase;
import org.web2.addisyardsale.service.PurchaseService;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
@CrossOrigin(origins = "*")
public class PurchaseController {
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    public ResponseEntity<Purchase> createPurchase(@RequestBody PurchaseRequest dto) {
        Purchase purchase = purchaseService.createPurchase(dto);
        return ResponseEntity.ok(purchase);
    }

    @GetMapping("/buyer/{buyerId}")
    public List<PurchaseResponse> getPurchasesByBuyer(@PathVariable Long buyerId) {
        return purchaseService.getPurchasesByBuyerId(buyerId);
    }

    @GetMapping("/seller/{sellerId}")
    public List<PurchaseResponse> getPurchasesBySeller(@PathVariable Long sellerId) {
        return purchaseService.getPurchasesBySellerId(sellerId);
    }
}
