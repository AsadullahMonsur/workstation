package com.example.mysqlspring.repository;

import com.example.mysqlspring.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepo extends CrudRepository<Customer,Integer> {

 Iterable<Customer> findAllByBalance(double balance);
 Iterable<Customer> findAllByOpeningBalance(double balance);

 @Query("select c from Customer c where c.name.fName=?1")
 Iterable<Customer> findAllByFName(String name);

}