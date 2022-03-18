import javax.tools.*;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

class Compilation implements Runnable{  

    public void run(){  
        try{
        Process p1 = Runtime.getRuntime().exec("javac Green");  
        p1.waitFor();
        }
        catch(Exception e){
        }
    }
}  