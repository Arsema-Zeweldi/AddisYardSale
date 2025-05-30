package org.web2.addisyardsale.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="products")
public class Product {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long productId;
    @Getter
    @Setter
    private String Name;
    @Getter
    @Setter
    private String Description;
    @Getter
    @Setter
    private double Price;
    @Getter
    @Setter
    private String status;
    @Getter
    @Setter
    @Column(columnDefinition = "TEXT")
    private String image1;
    @Getter
    @Setter
    @Column(columnDefinition = "TEXT")
    private String image2;
    @Getter
    @Setter
    @Column(columnDefinition = "TEXT")
    private String image3;
    @Getter
    @Setter
    @Column(columnDefinition = "TEXT")
    private String image4;
    @Getter
    @Setter
    private String Brand;
    @Getter
    @Setter
    private String Color;
    @Getter
    @Setter
    private String Style;
    @Getter
    @Setter
    private String Size;
    @Getter
    @Setter
    private String Material;
    @Getter
    @Setter
    private String Location;
    @Getter
    @Setter
    private Date PostedDate;
    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "condition_id", nullable = false)
    private Condition condition;

    @Setter
    @Getter
    private Integer watching;

}
