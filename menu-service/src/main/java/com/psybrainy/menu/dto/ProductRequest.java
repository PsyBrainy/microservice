package com.psybrainy.menu.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequest {

    private String name;

    private String description;

    private Double price;

    private String category;

    private String photo;


}
