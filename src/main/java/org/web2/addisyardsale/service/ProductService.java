package org.web2.addisyardsale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web2.addisyardsale.model.Product;
import org.web2.addisyardsale.model.User;
import org.web2.addisyardsale.repository.ProductRepository;
import org.web2.addisyardsale.repository.UserRepository;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;
    public List<Product> getProductsBySellerId(Long sellerId) {
        return productRepository.findByUser_UserId(sellerId);
    }
    public List<Product> getProductsByBuyerId(Long buyerId) {
        return productRepository.findByUser_UserId(buyerId);
    }

}
