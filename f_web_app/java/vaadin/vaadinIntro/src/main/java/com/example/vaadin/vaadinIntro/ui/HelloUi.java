package com.example.vaadin.vaadinIntro.ui;


import com.example.vaadin.vaadinIntro.model.Person;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Route("")
public class HelloUi extends VerticalLayout{

    public HelloUi(){
        Button bt = new Button("Click");
        bt.addClickListener(e-> {
            Notification.show("clicked button").setDuration(1000);
        });
        this.add(bt);
        //add(bt);

        List<Person> pl = new ArrayList<Person>();
        pl.addAll(Arrays.asList(new Person("1","Badhon","dhaka","000111"),
                new Person("2","on","aka","2222"),
                new Person("3","adhon","haka","3333"),
                new Person("4","hon","ka","4444")));

        Grid<Person> g = new Grid<Person>(Person.class);
          g.setItems(pl);
          this.add(bt,g);


        Grid<Person> g1 = new Grid<Person>();
        g1.addColumn(Person::getId).setHeader("Id");
        g1.addColumn(Person::getName).setHeader("Name");
        g1.setItems(pl);
        this.add(bt,g1);
    }
}
