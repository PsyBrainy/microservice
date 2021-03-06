package com.psybrainy.menu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponce {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private String photo;

    private CategoryResponce category;
}
