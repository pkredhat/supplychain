package com.redhat.company.services;

import com.redhat.company.dtos.CompanyDTO;
import java.util.List;


public interface CompanyService {

    CompanyDTO save(CompanyDTO CompanyDTO);

    List<CompanyDTO> findAll();

    CompanyDTO findOneById(String id);

    CompanyDTO findOneByName(String name);

    long deleteAll();
}
