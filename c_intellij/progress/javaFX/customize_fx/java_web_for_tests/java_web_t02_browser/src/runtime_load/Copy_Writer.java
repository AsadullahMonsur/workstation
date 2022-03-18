import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;

public class Copy_Writer{
    public static  String url_txt;
    public static void main(String arg[]){  
        try{            
        File file = new File("E:\\WorkStation\\IntelliJ\\java_web_for_tests\\java_web_t02_browser\\src\\main_section\\Green_Load.java");
        file.createNewFile();
        
//        FileReader fr = new FileReader("E:\\WorkStation\\IntelliJ\\java_web_for_tests\\java_web_t01\\src\\com\\company\\html.html");
//        BufferedReader br = new BufferedReader(fr);

        URL url = new URL("http://localhost/java/html.html");
        BufferedReader  br = new BufferedReader(
                new InputStreamReader(url.openStream()));

        FileWriter fw = new FileWriter("E:\\WorkStation\\IntelliJ\\java_web_for_tests\\java_web_t02_browser\\src\\main_section\\Green_Load.java", false);
        
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