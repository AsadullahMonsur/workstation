class GFG 
{ 
    // function for line generation 
    static void bresenham(int x1, int y1, int x2, 
                                         int y2) 
    { 
        int m_new = 2 * (y2 - y1); 
        int slope_error_new = m_new - (x2 - x1); 
      System.out.print(slope_error_new);
        for (int x = x1, y = y1; x <= x2; x++) 
        { 
            System.out.print("(" +x + "," + y + ")\n"); 
  
            // Add slope to increment angle formed 
            slope_error_new += m_new; 
       System.out.print(slope_error_new);
            // Slope error reached limit, time to 
            // increment y and update slope error. 
            if (slope_error_new >= 0) 
            { 
                y++; 
                slope_error_new -= 2 * (x2 - x1);
                     System.out.print(slope_error_new);
            } 
        } 
    }          
  
    // Driver code  
    public static void main (String[] args) 
    { 
        int x1 = 1, y1 = 2, x2 = 6, y2 =13;     
        bresenham(x1, y1, x2, y2); 
    } 
} 