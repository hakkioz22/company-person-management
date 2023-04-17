package com.tpe.repository;

import com.tpe.domain.Company;
import com.tpe.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Long> {
    List<Person> findByCompany(Company company);
}
