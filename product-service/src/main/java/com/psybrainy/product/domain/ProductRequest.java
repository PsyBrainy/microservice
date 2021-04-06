package com.psybrainy.product.domain;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class ProductRequest {

    private Long porductId;

    private String name;

    private String description;

    private Double price;

    private String category;

    private String status;

}
