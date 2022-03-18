package com.example.mysqlspring.controller;

import com.example.mysqlspring.model.Customer;
import com.example.mysqlspring.model.Person;
import com.example.mysqlspring.repository.CustomerRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("customer")
public class CustomerController {

    private CustomerRepo customerRepo;

    public CustomerController(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @GetMapping("all")
    @ResponseBody
    public Iterable<Customer> getAllCustomer(){
        return customerRepo.findAll();
        }

        @GetMapping("id/{id}")
        @ResponseBody
         public Optional<Customer> getCustomerById(@PathVariable int id){
        return customerRepo.findById(id);
        }

    @PostMapping("save")
    @ResponseBody
    public Customer saveCustomer(@RequestBody Customer cst){
        return customerRepo.save(cst);
    }

    @PutMapping("edit/{id}")
    @ResponseBody
    public Customer editCustomer(@PathVariable int id,@RequestBody Customer cst){

        Optional<Customer> c = customerRepo.findById(id);
        if(c.isPresent()){
            return customerRepo.save(cst);
        }
        return null;
    }

    @DeleteMapping("deleteCustomer/{id}")
    @ResponseBody
    public Customer deleteCustomer(@PathVariable int id){

        Optional<Customer> c = customerRepo.findById(id);
        if(c.isPresent()){
             customerRepo.delete(c.get());
             return c.get();
        }
        return null;
    }

    @GetMapping("openingBalance/{balance}")
    @ResponseBody
    public Iterable<Customer> getAllCustomerByOpeningBalance(@PathVariable double balance){
         return customerRepo.findAllByOpeningBalance(balance);
    }

    @GetMapping("name/{name}")
    @ResponseBody
    public Iterable<Customer> getAllCustomerByName(@PathVariable String name){
        return customerRepo.findAllByFName(name);
    }


}
