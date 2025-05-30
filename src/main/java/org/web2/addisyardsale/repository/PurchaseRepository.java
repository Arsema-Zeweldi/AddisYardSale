package org.web2.addisyardsale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.web2.addisyardsale.model.Purchase;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findByBuyerUserId(Long buyerId);
    List<Purchase> findBySellerUserId(Long sellerId);
}
