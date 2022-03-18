package com.example.mysqlspring.controller;

import com.example.mysqlspring.model.Person;
import com.example.mysqlspring.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class PersonController {

    @Autowired
    private PersonRepo pR;

    //@RequestMapping(method= RequestMethod.POST, value="addPerson")
    @PostMapping("addPerson")
    @ResponseBody
    public Person addPerson(@RequestBody Person person){
        return pR.save(person);
    }

   // @RequestMapping("persons")
    @GetMapping("persons")
    @ResponseBody
    public Iterable<Person> getAllPersons(){

        return pR.findAll();
    }

    @PutMapping("updatePerson/{id}")
    @ResponseBody
    public Person uP(@PathVariable int id, @RequestBody Person person){

    return pR.save(person);
    }

    @DeleteMapping("deletePerson/{id}")
    @ResponseBody
    public Optional<Person> deletePByID(@PathVariable int id){

        Optional<Person> p = pR.findById(id);
        if(p.isPresent()){
            pR.deleteById(id);
        }
        return p;
    }


    @DeleteMapping("deletePerson")
    @ResponseBody
    public Optional<Person> delP(@RequestBody Person person){

        Optional<Person> p = pR.findById(person.getId());
        if(p.isPresent()){
            pR.delete(person);
        }
        return p;
    }

}
