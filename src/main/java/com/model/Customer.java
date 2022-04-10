package com.model;

import lombok.*;


import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Customer {


    private Integer customerId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String billingAddress;


    private List<FlowersOrder> orders;
}
