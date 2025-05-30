package org.web2.addisyardsale.dto;

import lombok.Getter;
import lombok.Setter;
import org.web2.addisyardsale.model.Product;

import java.util.Date;

@Getter
@Setter
public class PurchaseResponse {
    private Long purchaseId;
    private Product product;
    private Long buyerId;
    private Long sellerId;
    private String status;
    private Date purchaseDate;
}
