package com.psybrainy.product.domain;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data @Builder
public class CategoryRequest {

    private Long categoryId;

    private String name;

    private List<ProductRequest> productEntity;
}
