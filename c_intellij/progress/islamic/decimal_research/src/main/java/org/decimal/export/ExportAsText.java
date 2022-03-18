package org.decimal.export;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExportAsText {
    private File file;
    private FileWriter fw;

    public ExportAsText(String path, String file_name) {
      try{
        file = new File((path+file_name));
        file.createNewFile();

        fw = new FileWriter((path+file_name), false);
      }
      catch(Exception e) {
         e.printStackTrace();
      }
    }

    public void write_to_file(String line){
      try {
        fw.write(line+"\n");
        fw.flush();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    public void close_file(){
        try {
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
