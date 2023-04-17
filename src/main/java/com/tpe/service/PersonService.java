package com.tpe.service;

import com.tpe.domain.Person;

import java.util.List;

public interface PersonService {

    List<Person> getAllPerson();
    Person findPersonById(Long id);
    Person savePerson(Person person);
    Person updatePerson(Long id,Person person);
    void deletePerson(Long id);


}
