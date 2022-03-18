package com.example.vapor.controller;

import com.example.vapor.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("jspViews")
public class JspViewController {
// for JSP we need to add library Tomcat Jasper as additional in maven or gradle
// version of tomcat can be found from terminal after run
/*
    @RequestMapping("Index")
    public String showIndex(){
        return "Index";
    }
*/
    //this Index is a file with .jsp extension , it will not work we need basic
    //.jsp file , here index was updated later, not usable anymore for this function
    // following html in Index.and jsp and run to test
/*
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
</head>

<body>
here
</body>
</html>
*/

    List<Person> list  =  new ArrayList<Person>();

    public JspViewController() {
        Person p1 = new Person(1,"Asadullah","Mohakhali");
        Person p2 = new Person(2,"Monsur","Mohakhali");
        Person p3 = new Person(3,"Badhon","Mohakhali");

        list.add(p1);
        list.add(p2);
        list.add(p3);

    }

    @RequestMapping("Index")
    public ModelAndView showDown(){
        String n =  "Mango";

        ModelAndView mv = new ModelAndView("Index");
        mv.addObject("name",n);
        mv.addObject("personL",list);
        mv.addObject("person",new Person());

        return mv;
    }

    @RequestMapping(value="addPerson",method = RequestMethod.POST)
    public ModelAndView addPerson(Person p){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/jspViews/Index");
        list.add(p);
        return mv;
    }

}
