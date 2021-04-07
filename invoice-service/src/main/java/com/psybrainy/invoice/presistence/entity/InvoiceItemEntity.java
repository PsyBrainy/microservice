package com.psybrainy.invoice.presistence.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "invoice_item")
@Data @Builder
public class InvoiceItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "d_invoice_item")
    private Long id;

    @Column(name = "id_product")
    private Long idProduct;

    @Column(name = "name_product")
    private String nameProduct;

    @ManyToOne
    @JoinColumn(name = "id_invoice")
    private InvoiceEntity invoice;

}
