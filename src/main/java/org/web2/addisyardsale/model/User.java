package org.web2.addisyardsale.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long userId;
    @Getter
    @Setter
    private String fullName;
    @Getter
    @Setter
    @Column(nullable = false, unique = true)
    private String email;
    @Setter
    @Column(nullable = false)
    private String password;
    @Getter
    @Setter
    private Integer soldItems = 0;
    @Getter
    @Setter
    private Integer activeItems = 0;
    @Getter
    @Setter
    private double totalPurchases = 0;
    @Getter
    @Setter
    private double rating = 0.0;

    @JsonIgnore
    public String getPassword() {
        return password;
    }

}
