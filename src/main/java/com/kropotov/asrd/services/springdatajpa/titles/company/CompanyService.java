package com.kropotov.asrd.services.springdatajpa.titles.company;

import com.kropotov.asrd.converters.company.CompanyToDto;
import com.kropotov.asrd.dto.company.CompanyDto;
import com.kropotov.asrd.entities.company.Company;
import com.kropotov.asrd.repositories.company.CompanyRepository;
import com.kropotov.asrd.services.CrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.kropotov.asrd.services.springdatajpa.specification.CompanySpecification.*;

@Service
@RequiredArgsConstructor
public class CompanyService implements CrudService<Company, Long> {

    private final CompanyRepository companyRepository;
    private final CompanyToDto companyToDto;

    public Optional<Company> getById(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    public Optional<List<Company>> getAll() {
        return Optional.ofNullable(companyRepository.findAll());
    }

    public Page<Company> getAll(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }

    public Page<CompanyDto> getAll(String title, String email, String fax, String militaryRepresentation,
                                String phone, String zipCode, String city, String street, String place,
                                Pageable pageable) {
        Specification<Company> specification = Specification.where(null);

        if (title != null && !title.isEmpty())
            specification = specification.and(hasTitleLike(title));

        if (email != null && !email.isEmpty())
            specification = specification.and(hasEmailLike(email));

        if (fax != null && !fax.isEmpty())
            specification = specification.and(hasFaxLike(fax));

        if (militaryRepresentation != null && !militaryRepresentation.isEmpty())
            specification = specification.and(hasMilReprLike(militaryRepresentation));

        if (phone != null && !phone.isEmpty())
            specification = specification.and(hasPhoneLike(phone));

        if (zipCode != null && !zipCode.isEmpty()
                || city != null && !city.isEmpty()
                || street != null && !street.isEmpty()
                || place !=null && !place.isEmpty())
            specification = specification.and(hasAddressLike(zipCode, city, street, place));

        return companyRepository.findAll(specification, pageable)
                .map(companyToDto::convert);
    }

    public Optional<Company> getOneByTitle(String title) {
        return companyRepository.findOneByTitle(title);
    }

    @Transactional
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }

    public List<Company> getByMilitaryRepresentation(String militaryRepresentation) {
        return companyRepository.findByMilitaryRepresentation(militaryRepresentation);
    }

    @Transactional
    public CompanyDto getDtoById(Long id) {
        return companyToDto.convert(getById(id).orElse(new Company()));
    }
}
