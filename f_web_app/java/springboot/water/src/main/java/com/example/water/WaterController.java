package com.example.water;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
public class WaterController {
    @RequestMapping("poke")
    @ResponseBody
    private String Greeting(){
        return "Peace Be Upon you with Rah-mah and Ba-ra-kah of Allah";
    }

    @RequestMapping("persons")
    @ResponseBody
    private List<Person> showAllPerson(){
        Person p1 = new Person(1,"Fatih","Banai");
        Person p2 = new Person(1,"Fatih","Banai");
        Person p3 = new Person(1,"Fatih","Banai");
        List<Person> personList = new ArrayList<Person>();
        personList.add(p1);
        personList.add(p1);
        personList.add(p1);

        return personList;
    }

    @RequestMapping("name/{hi}")
    @ResponseBody
    private String showName(@PathVariable String h){
        return h; // gives error: hi -> hi  (should not be h)
    }

    @RequestMapping("person/{id}")
    @ResponseBody
    private Person showIdMatch(@PathVariable int id){
        Person p = new Person();
        for(Person pp: showAllPerson()){
            if(pp.getId()==id){
                p = pp;
                break;
            }
        }
        return p;
    }


}
