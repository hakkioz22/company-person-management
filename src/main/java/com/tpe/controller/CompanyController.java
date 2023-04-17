package com.tpe.controller;

import com.tpe.domain.Company;
import com.tpe.domain.Person;
import com.tpe.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    //Get all companies
    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies(){
        List<Company> allCompanies = companyService.getAllCompanies();
        return new ResponseEntity<>(allCompanies, HttpStatus.OK);
    }

    //Get company by id
    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
        Company company = companyService.findCompanyById(id);
        return new ResponseEntity<>(company,HttpStatus.OK);
    }

    //Create a new Company
    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company){
        companyService.saveCompany(company);
        return new ResponseEntity<>(company,HttpStatus.CREATED);
    }

    //Update an existing company
    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id,@RequestBody Company company){
        Company updatedCompany = companyService.updateCompany(id, company);
        return new ResponseEntity<>(updatedCompany,HttpStatus.OK);
    }

    //Delete an existing company
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id){
        companyService.deleteCompany(id);
        return new ResponseEntity<>("Company with id: " +id + " deleted succesfully!",HttpStatus.OK);
    }

    //Get all persons for specific company
    @GetMapping("{id}/personList")
    public ResponseEntity<List<Person>> getCompanyPersons(@PathVariable Long id){
        List<Person> personList = companyService.listOfPersonByCompanyId(id);
        return new ResponseEntity<>(personList,HttpStatus.OK);
    }
}
