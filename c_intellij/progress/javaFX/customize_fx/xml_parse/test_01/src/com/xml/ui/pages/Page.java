package com.xml.ui.pages;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import com.xml.ui.components.Buttons;
import com.xml.ui.components.Labels;
import com.xml.ui.components.Layouts;
import com.xml.ui.components.TextFields;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.awt.event.*;
import java.io.File;

import java.awt.*;
import java.util.ArrayList;

public class Page {
    private Buttons buttons;
    private Labels labels;
    private TextFields textFields;
    private Layouts layouts;
    private ArrayList<Panel> pages;
    private Frame f;

    public Page() {
        buttons = new Buttons();
        labels = new Labels();
        textFields = new TextFields();
        layouts = new Layouts();
        pages = new ArrayList<Panel>();
        f = new Frame("Browser");
        f.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                f.dispose();
            }
        });
        f.setSize(700,400);
        f.setLayout(new BorderLayout());
        f.setVisible(true);
        Panel default_panel = new Panel();
        default_panel.setBounds(40,80,200,200);
        windows(0,default_panel);
    }

    private void xml_to_obj(){
        try {
        File fXmlFile = new File("C:\\Users\\Asadullah Monsur\\Documents\\homepage.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();

        System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

        NodeList nList = doc.getElementsByTagName("panel");

        System.out.println("----------------------------");

        if(nList.getLength()>0) {
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    Button b = new Button(eElement.getElementsByTagName("button").item(0).getTextContent());
                    b.setBounds(150, 200, 100, 30);
                    buttons.getButtons().add(b);

                    Panel panel = new Panel();
                    panel.setBackground(Color.BLACK);
                    panel.setBounds(40, 80, 200, 200);
                    panel.add(buttons.getButtons().get(0));
                    layouts.getPanels().add(panel);
                    pages.add(layouts.getPanels().get(0));

                    System.out.println("Panel id : " + eElement.getAttribute("id"));
                    System.out.println("label : " + eElement.getElementsByTagName("label").item(0).getTextContent());
                    System.out.println("text-field : " + eElement.getElementsByTagName("textfield").item(0).getTextContent());
                    System.out.println("button : " + eElement.getElementsByTagName("button").item(0).getTextContent());
                    System.out.println("display : " + eElement.getElementsByTagName("display").item(0).getTextContent());
                }
            }

            windows(1, pages.get(0));
        }
        else {
              System.out.println("No panels available");
        }
        }
        catch (Exception e) {
        e.printStackTrace();
    }
    }

    private void windows(int flag,Panel panel){
        if(flag==0){
            Button b = new Button("Click Here");
            b.setBounds(150,200,100,30);
            panel.add(b);
            f.add(panel);
            b.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    f.remove(panel);
                    xml_to_obj();

                }
            });
            System.out.println("Main view");
        }
        else{
            f.add(panel);
            f.revalidate();
            System.out.println("Custom view");
        }
    }


}
