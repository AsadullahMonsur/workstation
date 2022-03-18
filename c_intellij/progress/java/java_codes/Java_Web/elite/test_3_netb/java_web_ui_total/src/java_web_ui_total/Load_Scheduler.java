/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_web_ui_total;

/**
 *
 * @author Asadullah Monsur
 */
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
            runProcess("javac -cp src E:\\WorkStation\\java_codes\\Java_Web\\elite\\test_3_netb\\java_web_ui_total\\src\\java_web_ui_total\\Copy_Writer.java");
            runProcess("java -cp src E:\\WorkStation\\java_codes\\Java_Web\\elite\\test_3_netb\\java_web_ui_total\\src\\java_web_ui_total\\Copy_Writer");
            runProcess("javac -cp src E:\\WorkStation\\java_codes\\Java_Web\\elite\\test_3_netb\\java_web_ui_total\\src\\java_web_ui_total\\Green_Load.java");
            runProcess("java -cp src E:\\WorkStation\\java_codes\\Java_Web\\elite\\test_3_netb\\java_web_ui_total\\src\\java_web_ui_total\\Green_Load");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}