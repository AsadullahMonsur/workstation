public class PixelChooser{
    public static void main(String args[]){
    
    pixel_chooser(3,2,5,15);
    
    }
    
    public static void pixel_chooser(int x1, int y1, int x2, int y2){
    int a = y2-y1;
    int range =x2-x1+1;
    //int Y[] = new int[range];
    int pi[] = new int[range];
    int pas[] = new int[a+1];
    
    int c = y1;
    int z = 0;
    while(c<=y2){
        pas[z] = c+a;
    c = c+1;
    z++;
    }
    
    z = 0; 
    while(z<pas.length){
        System.out.println(pas[z]);
        if(z!=pas.length-1){
        pas[z] = pas[z]+pas[z+1]- pas[pas.length-1]-1;
        }
        else{
        pas[z] = pas[pas.length-1];
        }
        z++;
    }
    
    int f = 0;
    while(f<pas.length-1){
        System.out.println(pas[f]);
        f++;
    }
    z = 0;
    c = 0;
    while(z<pi.length-2){
        pi[z] = pas[c];
        c++;
        z = z+2;
    }
    pi[pi.length-1] = pas[0];
    z = pi.length-3;
    c = 0;
    while(z>=2){
            pi[z] = pas[c]*(-1);
        c++;
                
        z = z-2;
    }
    
   z = 1;
   pi[0] = y1;
   System.out.println("( "+x1+","+y1+" )");
   System.out.println(pi[0]);
   
   c = y1+1;
   int d = y1;
   while(z<pi.length-1){
       if(pi[z]<0){
           d = d+1;
           System.out.println("( "+c+","+d+" )");
       }
       else{
           System.out.println("( "+c+","+d+" )");
       }
       System.out.println(pi[z]);
       c++;
       z++;
   }
   System.out.println("( "+x2+","+y2+" )");
  }
}