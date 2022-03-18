package com.example.mysqlspring.repository;

import com.example.mysqlspring.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepo extends CrudRepository<Person,Integer> {
}
