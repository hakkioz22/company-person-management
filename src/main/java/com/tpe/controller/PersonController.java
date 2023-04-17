package com.tpe.controller;

import com.tpe.domain.Company;
import com.tpe.domain.WorkType;
import com.tpe.domain.Person;
import com.tpe.domain.PersonDTO;
import com.tpe.service.CompanyService;
import com.tpe.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private CompanyService companyService;

    //Get all people
    @GetMapping
    public ResponseEntity<List<Person>> personList(){
        List<Person> allPerson = personService.getAllPerson();
        return new ResponseEntity<>(allPerson, HttpStatus.OK);
    }

    //Find person by id
    @GetMapping("/{id}")
    public ResponseEntity<Person> findPersonById(@PathVariable Long id){
        Person person = personService.findPersonById(id);
        return new ResponseEntity<>(person,HttpStatus.OK);
    }

    //Create a new person
    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody PersonDTO personDTO){
        Person person = new Person();
        person.setName(personDTO.getName());
        person.setPhone(personDTO.getPhone());
        person.setSalary(personDTO.getSalary());
        person.setWorkType(WorkType.valueOf(personDTO.getJobType()));

        Company company = companyService.findCompanyById(personDTO.getCompanyId());
        person.setCompany(company);

        Person createdPerson = personService.savePerson(person);
        return new ResponseEntity<>(createdPerson,HttpStatus.CREATED);
    }

    //Update an existing person
    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id,@RequestBody Person person){
        Person updatedPerson = personService.updatePerson(id, person);
        return new ResponseEntity<>(updatedPerson,HttpStatus.OK);
    }

    //Delete an existing person
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id){
        Person person = personService.findPersonById(id);
        if (person!=null){
            personService.deletePerson(id);
            return new ResponseEntity<>("Person with id: " + id +" deleted succesfully",HttpStatus.OK);
        }
        else {return new ResponseEntity<String>("Person not found with id: " + id,HttpStatus.NOT_FOUND);}


    }

}
