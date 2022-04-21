package com.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class FlowersOrder {

    private Integer id;

    // private boolean status;
    private String orderDate;
    private String deliveryDay;


    private List<Flower> flowers;


    private Customer customer;


}
