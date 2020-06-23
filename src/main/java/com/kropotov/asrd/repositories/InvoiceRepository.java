package com.kropotov.asrd.repositories;

import com.kropotov.asrd.entities.docs.Invoice;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends PagingAndSortingRepository<Invoice, Long>, JpaSpecificationExecutor<Invoice> {
    Invoice findOneByNumber(String number);
    List<Invoice> findAllByNumberLike(String number);
    List<Invoice> findAllByNumber(String number);
    List<Invoice> findAll();
}
