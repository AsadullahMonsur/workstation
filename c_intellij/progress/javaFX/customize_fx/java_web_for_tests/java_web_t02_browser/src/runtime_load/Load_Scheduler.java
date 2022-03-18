package runtime_load;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Load_Scheduler {
    String separator = File.separator;
    private  void printLines(String name, InputStream ins) throws Exception {

        String line = null;

        BufferedReader in = new BufferedReader(new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
            System.out.println(name + " " + line);
        }
    }

    private  void runProcess(String command) throws Exception {
        Process pro = Runtime.getRuntime().exec(command);
        printLines(command + " stdout:", pro.getInputStream());
        printLines(command + " stderr:", pro.getErrorStream());
        pro.waitFor();
        System.out.println(command + " exitValue() " + pro.exitValue());
    }

    public void processor() {
        try {
             System.out.println("File Serapartor = "+separator);

             separator = System.getProperty("file.separator");
             System.out.println("File Serapartor = "+separator);
//
//             runProcess("javac -cp src src" + separator + "com" + separator + "company" + separator + "Copy_Writer.java");
//            System.out.println("**********");
//            runProcess("java -cp src src"+separator+"com"+separator+"company"+separator+"Copy_Writer");
//            System.out.println("**********");
//             runProcess("javac -cp src src" + separator + "com" + separator + "company" + separator + "Green_Load.java");
//             System.out.println("**********");
//             runProcess("java -cp src src"+separator+"com"+separator+"company"+separator+"Green_Load");

            runProcess("javac E:\\WorkStation\\IntelliJ\\java_web_for_tests\\java_web_t02_browser\\src\\runtime_load\\Copy_Writer.java");
            runProcess("java -cp E:\\WorkStation\\IntelliJ\\java_web_for_tests\\java_web_t02_browser\\src\\runtime_load Copy_Writer");
            runProcess("javac E:\\WorkStation\\IntelliJ\\java_web_for_tests\\java_web_t02_browser\\src\\main_section\\Green_Load.java");
            runProcess("java -cp E:\\WorkStation\\IntelliJ\\java_web_for_tests\\java_web_t02_browser\\src\\main_section Green_Load");


// remember we can not use package and package directory,
// we need a separate class with main method project root directory
//
//            runProcess("javac Copy_Writer.java");
//            runProcess("java Copy_Writer");
//            runProcess("javac Green_Load.java");
//            runProcess("java Green_Load");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
