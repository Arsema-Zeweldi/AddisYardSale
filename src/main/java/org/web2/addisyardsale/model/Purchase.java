package org.web2.addisyardsale.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "purchases")
@Getter
@Setter
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseId;

    @Column(nullable = false)
    private Long sellerId;

    @Column(nullable = false)
    private Long buyerId;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private String status = "paid"; // Default

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date purchaseDate = new Date(); // Default to now

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sellerId", referencedColumnName = "userId", insertable = false, updatable = false)
    private User seller;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "buyerId", referencedColumnName = "userId", insertable = false, updatable = false)
    private User buyer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productId", referencedColumnName = "productId", insertable = false, updatable = false)
    private Product product;
}
