package com.psybrainy.invoice.domain;

import lombok.Data;

@Data
public class ProductRequest {

    private Long productId;

    private String name;

    private String description;

    private Double price;

    private String category;

    private String status;

}
