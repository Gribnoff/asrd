package com.kropotov.asrd.services;

import com.kropotov.asrd.entities.Company;
import com.kropotov.asrd.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;

    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> findAll() {
        List<Company> companyList = new ArrayList<>();
        companyRepository.findAll().iterator().forEachRemaining(companyList::add);
        return companyList;
    }

    public Company saveOrUpdate(Company company) {
        return companyRepository.save(company);
    }

    public Company findByTitle(String title) {
        return companyRepository.findOneByTitle(title);
    }

    public Company findById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }
}
