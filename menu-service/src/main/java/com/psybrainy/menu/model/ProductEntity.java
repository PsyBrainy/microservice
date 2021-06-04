package com.psybrainy.menu.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "porduct")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductEntity {


    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
}
