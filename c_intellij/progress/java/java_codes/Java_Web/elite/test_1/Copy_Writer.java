import javax.tools.*;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

class Copy_Writer{  
    public static void main(String arg[]){  
        try{            
        File file = new File("E:\\WorkStation\\java_codes\\Java_Web\\elite\\test_1\\Green_Load.java");
        file.createNewFile();
        
        FileReader fr = new FileReader("E:\\WorkStation\\java_codes\\Java_Web\\elite\\test_1\\html.html");
        BufferedReader br = new BufferedReader(fr);
        FileWriter fw = new FileWriter("E:\\WorkStation\\java_codes\\Java_Web\\elite\\test_1\\Green_Load.java", false);
        
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