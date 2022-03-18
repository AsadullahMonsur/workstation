package com.example.house_rent_and_payment.utility;

import com.example.house_rent_and_payment.user_authentication.UserAuthentication;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MediaUtility{

    public static Image getImage(String path) {
      try {
          URL url = MediaUtility.class.getResource(path);
          if(url!=null){
              return new Image(url.toString());
          }
      }
      catch (Exception e){
          Logger.getLogger(MediaUtility.class.getName())
                .log(Level.SEVERE, null, e);
          e.printStackTrace();

          System.out.println("Error loading Image-> MediaUtility: "+
                              e.getMessage());
      }

      return null;
    }
}
