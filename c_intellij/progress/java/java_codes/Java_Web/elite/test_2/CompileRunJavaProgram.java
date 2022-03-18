import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CompileRunJavaProgram {

    public static void main(String[] args){
        
//    try {
//        if(args.length < 2) throw new Exception("Mandatory Arguments missing");
//        runProcess(args[0]);
//        runProcess(args[1]);
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//        
        try {
            runProcess("pwd");
            System.out.println("**********");
            runProcess("javac -cp src src/com/journaldev/files/Test.java");
            System.out.println("**********");
            runProcess("java -cp src com/journaldev/files/Test Hi Pankaj");
        }
        catch (Exception e) {
            e.printStackTrace();
         }   
        
         }

    private static void printLines(String cmd, InputStream ins) throws Exception {
        String line = null;
        BufferedReader in = new BufferedReader(
            new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
            System.out.println(cmd + " " + line);
        }
      }

      private static void runProcess(String command) throws Exception {
        Process pro = Runtime.getRuntime().exec(command);
        printLines(command + " stdout:", pro.getInputStream());
        printLines(command + " stderr:", pro.getErrorStream());
        pro.waitFor();
        System.out.println(command + " exitValue() " + pro.exitValue());
      }

}
//   String separator = File.separator;
//System.out.println("File Serapartor = "+separator);
//
//separator = System.getProperty("file.separator");
//System.out.println("File Serapartor = "+separator);
//
//runProcess("javac -cp src src"+separator+"com"+separator+"journaldev"+separator+"files"+separator+"Test.java");
//System.out.println("**********");
//runProcess("java -cp src com"+separator+"journaldev"+separator+"files"+separator+"Test Hi Pankaj");
