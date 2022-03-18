import java.util.*;
public class Search_Walk{
 
    public static void main(String []arg)throws Exception{
          
       
      long array[][] = new long[99][99];
        
        
        int leftTop_x = (int)array.length/2;
        int leftTop_y = (int)array.length/2;

        int rightTop_x = (int)((array.length/2)+1);
        int rightTop_y = (int)((array.length/2));
        
        int leftBottom_x = (int)((array.length/2));
        int leftBottom_y = (int)((array.length/2)-1);
        
        int rightBottom_x = (int)((array.length/2)+1);
        int rightBottom_y = (int)((array.length/2)-1);
        
        array[rightTop_x][rightTop_y] = 1;
        array[leftTop_x][leftTop_y]= 1;
        array[leftBottom_x][leftBottom_y]= 1; 
        array[rightBottom_x][rightBottom_y]= 1;
        
        check(array,rightTop_x,rightTop_y);
        check(array,leftTop_x,leftTop_y);
        check(array,leftBottom_x,leftBottom_y);
        check(array,rightBottom_x,rightBottom_y);
        
        System.out.println(Arrays.deepToString(array));

         
     }
    public static void check(long a[][],int x, int y){
    
        if(x>=0 && x+2<a.length && y>=0 && y+1<a.length){
            
            if(a[x+2][y+1]==0){
            a[x+2][y+1]=1;
            check(a,x+2,y+1);
            }//1
            
        }
        if(x>=0 && x+2<a.length && y-1>=0 && y<a.length){
            if(a[x+2][y-1]==0){
            a[x+2][y-1]=1;
            check(a,x+2,y-1);
            }//2
        }
        if(x>=0 && x+1<a.length && y>=0 && y+2<a.length){
            if(a[x+1][y+2]==0){
            a[x+1][y+2]=1;
            check(a,x+1,y+2);
            }//3
        }
        if(x-1>=0 && x<a.length && y>=0 && y+2<a.length){
            if(a[x-1][y+2]==0){
            a[x-1][y+2]=1;
            check(a,x-1,y+2);
            }//4
        }
        if(x-2>=0 && x<a.length && y>=0 && y+1<a.length){
            if(a[x-2][y+1]==0){
            a[x-2][y+1]=1;
            check(a,x-2,y+1);
            }//5
        }
        
        if(x-2>=0 && x<a.length && y-1>=0 && y<a.length){
            if(a[x-2][y-1]==0){
            a[x-2][y-1]=1;
            check(a,x-2,y-1);
            }//6
            
        }
        if(x>=0 && x+1<a.length && y-2>=0 && y<a.length){
            if(a[x+1][y-2]==0){
            a[x+1][y-2]=1;
            check(a,x+1,y-2);
            }//7
        }
        if(x-1>=0 && x<a.length && y-2>=0 && y<a.length){
            if(a[x-1][y-2]==0){
            a[x-1][y-2]=1;
            check(a,x-1,y-2);
            }//8
        }
       
    } 
}