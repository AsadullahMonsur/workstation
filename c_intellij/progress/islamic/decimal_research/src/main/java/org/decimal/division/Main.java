package org.decimal.division;

import org.decimal.export.ExportAsText;

public class Main {
   private static String path = "/home/zylem/workstation/c_intellij/" +
                                "progress/islamic/output/";

   private static String []file_name;
   public static void main(String []args){
      file_name = new String[]{
                     "division_series_0_from_0_upto_100",
                     "division_series_1_from_100_upto_200",
                     "division_series_2_from_100_upto_200",
                     "division_series_3_from_100_upto_200",
                     "division_series_4_from_100_upto_200",
                     "division_series_5_from_100_upto_200",
                     "division_series_6_from_100_upto_200",
                     "division_series_7_from_100_upto_200",
                     "division_series_8_from_100_upto_200",
                     "division_series_9_from_100_upto_200"
                    };

      execute(file_name[0],10,100);
//      for(int i=1;i<10;i++){
//         execute(file_name[i],i+100,200);
//      }
   }

   private static void execute(String file_name, int from, int upto) {
      String result = form_data(from,upto);
      ExportAsText asText = new ExportAsText(path,file_name);
      asText.write_to_file(result);
      asText.close_file();
   }

   private static String form_data(double value, int upto){
      String result = " ";
      double initial = value;
      int counter = 1;
      while (initial<upto){
         Divison1by divison = new Divison1by();

         if(counter==1){
            result = result+ "1 / "+ (initial+"  :-> "+divison.divide(initial));
         }
         else {
            result = result + "\n 1 / "+ (initial+"  :-> "+divison.divide(initial));
         }
         initial = initial +10.0;
         counter++;
      }
      return result;
   }
}
