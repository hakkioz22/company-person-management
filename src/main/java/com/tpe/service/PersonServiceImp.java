package com.tpe.service;

import com.tpe.domain.Person;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImp implements PersonService{

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> getAllPerson() {
        return personRepository.findAll();
    }

    @Override
    public Person findPersonById(Long id) {
        return personRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Person not found with id: " +id));
    }

    @Override
    public Person savePerson(Person person) {
        personRepository.save(person);
        return person;
    }

    @Override
    public Person updatePerson(Long id, Person updatedPerson) {
        return personRepository.findById(id)
                .map(person -> {person.setName(updatedPerson.getName());
                person.setCompany(updatedPerson.getCompany());
                person.setPhone(updatedPerson.getPhone());
                person.setSalary(updatedPerson.getSalary());
                person.setJobType(updatedPerson.getJobType());
                return personRepository.save(person);
                }).orElseThrow(()-> new ResourceNotFoundException("Person not found with id: " + id));
    }
    @Override
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }
}
