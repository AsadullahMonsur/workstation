import javax.tools.*;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

class Waiter extends TimerTask{  
public void run(){  
    try{
        Process theProcess1 = Runtime.getRuntime().exec("javac Green");
        Thread.sleep(1000);
        Process theProcess = Runtime.getRuntime().exec("java Green");
        
       //Custom_Loader loader = new Custom_Loader();
       //loader.invokeClassMethod("Green", "visualize");
    }
    catch(Exception e){
    System.out.print(e);
    }
}


}  