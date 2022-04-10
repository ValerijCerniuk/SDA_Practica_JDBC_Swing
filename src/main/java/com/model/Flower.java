package com.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flower {

    private Integer flowerId;
    private String name;
    private Integer flowerPrice;
    private String color;

   // private Integer suppler_id;


    private FlowersOrder flowerOrders;

    @Override
    public String toString() {
        return  "flowerId=" + flowerId +
                ", name='" + name + '\'' +
                ", flowerPrice=" + flowerPrice +
                ", color='" + color + '\'' +
                ", flowerOrders=" + flowerOrders +
                "}\n";
    }
}