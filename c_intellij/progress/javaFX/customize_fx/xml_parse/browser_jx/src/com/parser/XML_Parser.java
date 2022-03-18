package com.parser;

import com.page.Page;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class XML_Parser {
    private Page page;
    public XML_Parser() {
        page = new Page();
    }

  public Page xml_to_page(){
    try {
      int result  = write_xml_file();

      while(true){
        if(result==1){
          File fXmlFile = new File("content.xml");
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
                  b.setPrefHeight(40);
                  b.setPrefWidth(100);
                  b.setOnMouseClicked(new EventHandler<MouseEvent>() {
                      @Override
                      public void handle(MouseEvent event) {

                      }
                  });
                  page.getButtons().add(b);

                   Label label = new Label(eElement.getElementsByTagName("label").item(0).getTextContent());
                   label.setPrefHeight(100);
                   label.setPrefWidth(40);
                   page.getLabels().add(label);

                   TextField textField = new TextField();
                   textField.setPromptText(eElement.getElementsByTagName("textfield").item(0).getTextContent());
                   textField.setPrefHeight(100);
                   textField.setPrefWidth(40);
                   page.getTextFields().add(textField);

                   page.method(eElement.getElementsByTagName("link").item(0).getTextContent(),
                           eElement.getElementsByTagName("id").item(0).getTextContent());
                   System.out.println("Panel id : " + eElement.getAttribute("id"));
                   System.out.println("label : " + eElement.getElementsByTagName("label").item(0).getTextContent());
                   System.out.println("text-field : " + eElement.getElementsByTagName("textfield").item(0).getTextContent());
                   System.out.println("button : " + eElement.getElementsByTagName("button").item(0).getTextContent());
                   System.out.println("link : " + eElement.getElementsByTagName("link").item(0).getTextContent());
                   System.out.println("id : " + eElement.getElementsByTagName("id").item(0).getTextContent());
                    }
                }
            }
            else {
                System.out.println("No panels available");
            }
            break;
        }
        else if(result==0) {
            System.out.println("xml file couldn't be written");
        }
      }
    }
    catch (Exception e) {
     e.printStackTrace();
    }
    return getPage();
  }

    private int write_xml_file() {
      try {
        URL url = new URL("http://localhost/java/homepage.xml");
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.connect();
        int code = con.getResponseCode();

          File file = new File("content.xml");
          file.createNewFile();
          BufferedReader br = new BufferedReader(
                  new InputStreamReader(con.getInputStream()));
          FileWriter fw = new FileWriter("content.xml", false);
          String s;
          while ((s=br.readLine()) != null) {
                fw.write(s+"\n");
                fw.flush();
          }
          fw.close();
          br.close();
          con.disconnect();
          return  1;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Getting Server Response");
        }
        return 0;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
