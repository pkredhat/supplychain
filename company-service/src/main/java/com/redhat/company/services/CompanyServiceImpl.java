package com.redhat.company.services;

import com.redhat.company.dtos.CompanyDTO;
import com.redhat.company.exceptions.EntityNotFoundException;
import com.redhat.company.models.CompanyEntity;
import com.redhat.company.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public CompanyDTO save(CompanyDTO CompanyDTO) {
        return new CompanyDTO(companyRepository.save(CompanyDTO.toCompanyEntity()));
    }

    @Override
    public List<CompanyDTO> findAll() {
        return companyRepository.findAll().stream().map(CompanyDTO::new).toList();
    }

    @Override
    public CompanyDTO findOneById(String id) {
        CompanyEntity company = companyRepository.findOneById(id);
        if (company == null) {
            throw new EntityNotFoundException(id);
        }
        return new CompanyDTO(company);
    }

    @Override
    public CompanyDTO findOneByName(String name) {
        CompanyEntity company = companyRepository.findOneByName(name);
        if (company == null) {
            throw new EntityNotFoundException(name);
        }
        return new CompanyDTO(company);
    }

    @Override
    public long deleteAll() {
        return companyRepository.deleteAll();
    }

}
