package com.tpe.service;

import com.tpe.domain.Company;
import com.tpe.domain.Person;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.CompanyRepository;
import com.tpe.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImp implements CompanyService{

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company findCompanyById(Long id) {
        return companyRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Company not found with id: " + id));
    }

    @Override
    public void saveCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public Company updateCompany(Long id, Company updatedCompany) {
        return companyRepository.findById(id)
                .map(company->{company.setName(updatedCompany.getName());
                company.setCity(updatedCompany.getCity());
                return companyRepository.save(company);
                }).orElseThrow(()-> new ResourceNotFoundException("Company with id: " + id + " not found!"));
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public List<Person> listOfPersonByCompanyId(Long id) {
        Optional<Company> company = companyRepository.findById(id);

        if (company.isPresent()){
            return personRepository.findByCompany(company.get());
        }else {
            throw new ResourceNotFoundException("Company with id: " + id + " not found!");
        }
    }
}
