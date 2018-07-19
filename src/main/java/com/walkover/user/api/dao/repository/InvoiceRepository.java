package com.walkover.user.api.dao.repository;

import com.walkover.user.api.dao.model.Invoice;
import com.walkover.user.api.dao.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

//public Invoice getInvoice(long userId);

}
