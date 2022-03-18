import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Load_Scheduler{
      
        private void printLines(String name, InputStream ins) throws Exception {
        String line = null;
        
        BufferedReader in = new BufferedReader(new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
            System.out.println(name + " " + line);
        }
    }
    
    private void runProcess(String command) throws Exception {
        Process pro = Runtime.getRuntime().exec(command);
        printLines(command + " stdout:", pro.getInputStream());
        printLines(command + " stderr:", pro.getErrorStream());
        pro.waitFor();
        System.out.println(command + " exitValue() " + pro.exitValue());
    }
    
    public void processor(String[] args) {
        try {
           // runProcess("pwd");
            runProcess("javac Copy_Writer.java");
            runProcess("java Copy_Writer");
            runProcess("javac Green_Load.java");
            runProcess("java Green_Load");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}