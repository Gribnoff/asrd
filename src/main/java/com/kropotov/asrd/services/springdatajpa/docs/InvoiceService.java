package com.kropotov.asrd.services.springdatajpa.docs;

import com.kropotov.asrd.converters.docs.InvoiceToDto;
import com.kropotov.asrd.dto.docs.InvoiceDto;
import com.kropotov.asrd.entities.docs.Invoice;
import com.kropotov.asrd.repositories.InvoiceRepository;
import com.kropotov.asrd.services.CrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.kropotov.asrd.services.springdatajpa.specification.InvoiceSpecification.*;

@Service
@RequiredArgsConstructor
public class InvoiceService implements CrudService<Invoice, Long> {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceToDto invoiceToDto;


    public Page<Invoice> getAll(Pageable pageable) {
        return invoiceRepository.findAll(pageable);
    }

    @Override
    public Optional<List<Invoice>> getAll() {
        return Optional.ofNullable(invoiceRepository.findAll());
    }

    public Page<Invoice> getAll(Byte entityStatus, String system, String device, String companyFrom, String companyDest,
                                LocalDateTime createdAtFrom, LocalDateTime createdAtTo, LocalDateTime updatedAtFrom, LocalDateTime updatedAtTo,
                                String number, LocalDate invoiceDateFrom, LocalDate invoiceDateTo, String userName,
                                Pageable pageable) {

        Specification<Invoice> specification = Specification.where(null);

        if (entityStatus != null)
            specification = specification.and(hasStatus(entityStatus));

        if (system != null && !system.isEmpty())
            specification = specification.and(inSystem(system));

        if (device != null && !device.isEmpty())
            specification = specification.and(inDevice(device));

        if (companyFrom != null && !companyFrom.isEmpty())
            specification = specification.and(fromCompanyLike(companyFrom));

        if (companyDest != null && !companyDest.isEmpty())
            specification = specification.and(destCompanyLike(companyDest));

        if (createdAtFrom != null)
            specification = specification.and(createdAtAfter(createdAtFrom));

        if (createdAtTo != null)
            specification = specification.and(createdAtBefore(createdAtTo));

        if (updatedAtFrom != null)
            specification = specification.and(updatedAtAfter(updatedAtFrom));

        if (updatedAtTo != null)
            specification = specification.and(updatedAtBefore(updatedAtTo));

        if (number != null && !number.isEmpty())
            specification = specification.and(hasNumberLike(number));

        if (invoiceDateFrom != null)
            specification = specification.and(invoiceDateAfter(invoiceDateFrom));

        if (invoiceDateTo != null)
            specification = specification.and(invoiceDateBefore(invoiceDateTo));

        if (userName != null && !userName.isEmpty())
            specification = specification.and(hasUserLike(userName));

        return invoiceRepository.findAll(specification, pageable);
    }

    @Override
    public Optional<Invoice> getById(Long id) {
        return invoiceRepository.findById(id);
    }

    public boolean isInvoiceWithNumberExists(String number) {
        return invoiceRepository.findOneByNumber(number) != null;
    }

    @Transactional
    @Override
    public Invoice save(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public List<Invoice> findAllByNumberLike(String number) {
        return invoiceRepository.findAllByNumberLike(number);
    }

    public List<Invoice> findAllByNumber(String number) {
        return invoiceRepository.findAllByNumber(number);
    }

    @Transactional
    public InvoiceDto getDtoById(Long id) {

        Optional<Invoice> invoiceOptional = invoiceRepository.findById(id);
        if (invoiceOptional.isPresent()) {
            return invoiceToDto.convert(invoiceOptional.get());
        } else {
            return new InvoiceDto();
        }
    }

    @Override
    public void deleteById(Long id) {
        invoiceRepository.deleteById(id);
    }
}
