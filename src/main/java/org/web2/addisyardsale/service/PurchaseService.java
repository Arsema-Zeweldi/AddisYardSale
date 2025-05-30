package org.web2.addisyardsale.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.web2.addisyardsale.dto.PurchaseRequest;
import org.web2.addisyardsale.dto.PurchaseResponse;
import org.web2.addisyardsale.model.Purchase;
import org.web2.addisyardsale.repository.ProductRepository;
import org.web2.addisyardsale.repository.PurchaseRepository;
import org.web2.addisyardsale.model.Product;
import org.web2.addisyardsale.repository.UserRepository;

import java.util.Date;
import java.util.List;

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public PurchaseService(PurchaseRepository purchaseRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.purchaseRepository = purchaseRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Purchase createPurchase(PurchaseRequest dto) {
        // Save Purchase
        Purchase purchase = new Purchase();
        purchase.setProductId(dto.getProductId());
        purchase.setSellerId(dto.getSellerId());
        purchase.setBuyerId(dto.getBuyerId());
        purchase.setStatus("sold");
        purchase.setPurchaseDate(new Date());

        Purchase savedPurchase = purchaseRepository.save(purchase);

        // Update product status to "sold"
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setStatus("sold");
        productRepository.save(product);

        // Update seller: increase soldItems
        userRepository.findById(dto.getSellerId()).ifPresent(seller -> {
            seller.setSoldItems(seller.getSoldItems() + 1);
            userRepository.save(seller);
        });

        // Update buyer: increase totalPurchases
        userRepository.findById(dto.getBuyerId()).ifPresent(buyer -> {
            buyer.setTotalPurchases(buyer.getTotalPurchases() + dto.getTotalAmount());
            userRepository.save(buyer);
        });

        return savedPurchase;
    }

    public List<PurchaseResponse> getPurchasesByBuyerId(Long buyerId) {
        List<Purchase> purchases = purchaseRepository.findByBuyerUserId(buyerId);
        return purchases.stream().map(this::toDTO).toList();
    }

    public List<PurchaseResponse> getPurchasesBySellerId(Long sellerId) {
        List<Purchase> purchases = purchaseRepository.findBySellerUserId(sellerId);
        return purchases.stream().map(this::toDTO).toList();
    }

    private PurchaseResponse toDTO(Purchase purchase) {
        PurchaseResponse dto = new PurchaseResponse();
        dto.setPurchaseId(purchase.getPurchaseId());
        dto.setProduct(purchase.getProduct());
        dto.setBuyerId(purchase.getBuyer().getUserId());
        dto.setSellerId(purchase.getSeller().getUserId());
        dto.setStatus(purchase.getStatus());
        dto.setPurchaseDate(purchase.getPurchaseDate());
        return dto;
    }
}
