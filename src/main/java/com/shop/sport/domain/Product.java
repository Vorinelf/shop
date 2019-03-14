package com.shop.sport.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Builder
@Data
@AllArgsConstructor
public class Product {
    private long id;
    private String name;
    private String type;
    private double price;
}
