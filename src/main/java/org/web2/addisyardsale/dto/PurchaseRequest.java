package org.web2.addisyardsale.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseRequest {
    private Long productId;
    private Long sellerId;
    private Long buyerId;
    private double totalAmount;
}
