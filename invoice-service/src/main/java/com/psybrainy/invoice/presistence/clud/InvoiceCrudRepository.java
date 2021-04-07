package com.psybrainy.invoice.presistence.clud;

import com.psybrainy.invoice.presistence.entity.InvoiceEntity;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceCrudRepository extends CrudRepository<InvoiceEntity, Long> {
}
