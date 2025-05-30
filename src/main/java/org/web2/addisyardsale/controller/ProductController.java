package org.web2.addisyardsale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.web2.addisyardsale.dto.ProductRequest;
import org.web2.addisyardsale.exception.ResourceNotFoundException;
import org.web2.addisyardsale.model.Product;
import org.web2.addisyardsale.repository.CategoryRepository;
import org.web2.addisyardsale.repository.ConditionRepository;
import org.web2.addisyardsale.repository.ProductRepository;
import org.web2.addisyardsale.repository.UserRepository;
import org.web2.addisyardsale.service.ProductService;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(originPatterns = "*")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ConditionRepository conditionRepository;

    @Autowired
    private ProductService productService;

    @GetMapping("/seller/{sellerId}")
    public List<Product> getProductsBySeller(@PathVariable Long sellerId) {
        return productService.getProductsBySellerId(sellerId);
    }

    @GetMapping("")
    public List<Product> getAvailableProducts() {
        return productRepository.findByStatus("available");
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest dto) {
        try {
            Product product = new Product();
            product.setName(dto.name);
            product.setDescription(dto.description);
            product.setPrice(dto.price);
            product.setStatus(dto.status);
            product.setImage1(dto.image1);
            product.setImage2(dto.image2);
            product.setImage3(dto.image3);
            product.setImage4(dto.image4);
            product.setBrand(dto.brand);
            product.setColor(dto.color);
            product.setStyle(dto.style);
            product.setSize(dto.size);
            product.setMaterial(dto.material);
            product.setLocation(dto.location);
            product.setPostedDate(new Date());
            product.setWatching(dto.watching);

            product.setUser(userRepository.findById(dto.userId).orElseThrow());
            product.setCategory(categoryRepository.findById(dto.categoryId).orElseThrow());
            product.setCondition(conditionRepository.findById(dto.conditionId).orElseThrow());

            Product saved = productRepository.save(product);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/category/{categoryId}/exclude/{productId}")
    public List<Product> getAvailableProductsByCategoryExceptCurrent(
            @PathVariable Long categoryId,
            @PathVariable Long productId) {
        return productRepository.findByCategory_CategoryIdAndStatusAndProductIdNot(categoryId, "available", productId);
    }


    @GetMapping("/{productId}")
    public Optional<Product> getProduct(@PathVariable Long productId) {
        return Optional.ofNullable(productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product with ID " + productId + " not found")));
    }

    @PostMapping("/{productId}/watching")
    public ResponseEntity<?> updateWatching(
            @PathVariable Long productId,
            @RequestParam(name = "increment", defaultValue = "true") boolean increment) {

        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product not found with ID: " + productId);
        }

        Product product = optionalProduct.get();
        int current = product.getWatching() != null ? product.getWatching() : 0;
        int updated = increment ? current + 1 : Math.max(0, current - 1);
        product.setWatching(updated);

        productRepository.save(product);

        return ResponseEntity.ok().body("Watch count updated to: " + updated);
    }


    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        productRepository.deleteById(productId);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product updatedProduct) {
        return productRepository.findById(productId).map(product -> {
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setPrice(updatedProduct.getPrice());
            return ResponseEntity.ok(productRepository.save(product));
        }).orElse(ResponseEntity.notFound().build());
    }

}
