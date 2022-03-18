import javax.tools.*;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

class Copy implements Runnable{  

    public void run(){  
        try{
        File file = new File("Green.java");
        file.createNewFile();
        
        FileReader fr = new FileReader("html.html");
        BufferedReader br = new BufferedReader(fr);
        FileWriter fw = new FileWriter("Green.java", false);
        
        String s = br.readLine();
               s = br.readLine();
        
        int count = Integer.parseInt(s);
        
        for(int i=1;i<count;i++) { 
     
           s = br.readLine();
           fw.write("\n"+s); 
           fw.flush();
        }
       fw.close();
        br.close();
        }
        catch(Exception e){
        }
    }
}  