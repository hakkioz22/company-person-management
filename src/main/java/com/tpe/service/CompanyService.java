package com.tpe.service;

import com.tpe.domain.Company;
import com.tpe.domain.Person;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();
    Company findCompanyById(Long id);
    void saveCompany(Company company);
    Company updateCompany(Long id,Company company);
    void deleteCompany(Long id);
    List<Person> listOfPersonByCompanyId(Long id);

}
