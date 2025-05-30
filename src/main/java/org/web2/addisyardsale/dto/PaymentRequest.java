package org.web2.addisyardsale.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaymentRequest {
    private String amount;
    private String currency;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String txRef;
    private List<Long> products;
}
