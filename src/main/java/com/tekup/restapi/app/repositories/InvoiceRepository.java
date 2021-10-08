package com.tekup.restapi.app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tekup.restapi.app.models.Invoice;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Integer> {

}
