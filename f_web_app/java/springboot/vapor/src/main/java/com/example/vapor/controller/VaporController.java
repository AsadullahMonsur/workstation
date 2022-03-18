package com.example.vapor.controller;

import com.example.vapor.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("person")
public class VaporController {

    List<Person> list  =  new ArrayList<Person>();

    @RequestMapping("method")
    @ResponseBody
    public String method(){
        return "Allah is All-Great";
    }

    @RequestMapping("method1")
    @ResponseBody
    public Person method1(){
        Person person = new Person(1,"Asadullah","Mohakhali");
        return person;
    }
    @RequestMapping("call")
    @ResponseBody
    public List<Person> method2(){
        Person p1 = new Person(1,"Asadullah","Mohakhali");
        Person p2 = new Person(2,"Monsur","Mohakhali");
        Person p3 = new Person(3,"Badhon","Mohakhali");

        list.add(p1);
        list.add(p2);
        list.add(p3);
        return list;
    }

    @RequestMapping("name/{n}")
    @ResponseBody
    public String myName(@PathVariable String n) {
        return n;
    }

    @RequestMapping("person/{id}")
    @ResponseBody
    public Person showParticularPerson(@PathVariable int id) {
        Person person = new Person();
        for(Person p:list){
            if(p.getId()==id){
                person=p;
                break;
            }

        }
        return person;
    }
}

