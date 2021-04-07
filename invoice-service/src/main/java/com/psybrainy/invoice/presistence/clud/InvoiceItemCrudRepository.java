package com.psybrainy.invoice.presistence.clud;

import com.psybrainy.invoice.presistence.entity.InvoiceItemEntity;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceItemCrudRepository extends CrudRepository<InvoiceItemEntity, Long> {
}
