package org.reservoir.fragment.secure_core_ui.controller;

import org.reservoir.fragment.secure_core_ui.model.PersonForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

//@Controller
//@RequestMapping("view")
public class SampleViewController {

    @RequestMapping("index")
    public String showIndex(PersonForm personForm) {
        return "index";
    }

    @PostMapping("index")
    public String addPerson(@Valid PersonForm personForm, BindingResult bindingResult) {

        System.out.println(personForm.toString());
        if (bindingResult.hasErrors()) {
            System.out.println("inside"+bindingResult.toString());
            return "index";
        }
        return "results";
    }
}