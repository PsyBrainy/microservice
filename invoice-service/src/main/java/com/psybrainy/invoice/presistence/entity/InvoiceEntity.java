package com.psybrainy.invoice.presistence.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "invoice")
@Data @Builder
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_invoice")
    private Long id;

    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "total")
    private Double total;

    @Column(name = "payment_method")
    private String paymentMethod;

    @OneToMany(mappedBy = "invoice")
    private List<InvoiceItemEntity> invoiceItemEntityList;
}
