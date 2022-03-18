package com.javaFEX.converter.xml;

import com.javaFEX.ui.components.Pages;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class XML_Formatter {
    private Boolean result = false;

    public XML_Formatter(Pages pages, String directory){
    try{
       if(directory.isEmpty()){
          System.out.println("File path error or not specified");
       }
       else {
           String filepath = directory+ "\\Base.xml";
           File file = new File(filepath);
           file.createNewFile();
           long startTime = System.currentTimeMillis(); // Get the start Time
           long endTime = 0;
           while (true){
               endTime = System.currentTimeMillis();
               long time = endTime-startTime;
               if(time<=10000) {
                   if(file.exists() && !file.isDirectory()) {
                       System.out.println("file is found & waiting to finish writing");
                   }
                   else if(time>=10000){
                       System.out.println("checking if file is there");
                       throw new FileNotFoundException();
                   }
                  //System.out.println("peeking if file is there");
               }
               else {
               FileWriter fw = new FileWriter(file, false);
               BufferedWriter bw = new BufferedWriter(fw);

               String xml_content = pages.output();
               String[] content_lines = xml_content.split("@Token#");

               for (String line : content_lines){
                   bw.write(line+"\n");
                   System.out.println(line);
               }

               bw.close();
               fw.close();
               result = true;
               if(result){
                   System.out.println("Successfully Formatted");
                   break;
               }
             }
           }
        }
    }
    catch(Exception e){
      e.printStackTrace();
      System.out.println("Exception occurs in XML conversion");
    }
  }

    public Boolean getResult() {
        return result;
    }
}
