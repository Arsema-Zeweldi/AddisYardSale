package org.web2.addisyardsale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.web2.addisyardsale.model.Product;
import org.web2.addisyardsale.model.User;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByUser_UserId(Long userId);
    List<Product> findByStatus(String status);


    List<Product> findByCategory_CategoryIdAndStatusAndProductIdNot(Long categoryId, String available, Long productId);
}
