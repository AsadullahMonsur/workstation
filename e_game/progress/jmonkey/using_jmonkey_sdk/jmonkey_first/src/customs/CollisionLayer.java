/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customs;

import com.jme3.math.Vector3f;

/**
 *
 * @author Asadullah Monsur
 */
public class CollisionLayer {
    // layer_type = 0;  // 0 for verical , 1 for horizontal
    
   // private final Vector3f centre = new Vector3f(0.0f,0.0f,0.0f);
    
    private Vector3f []directions;
    
    private float h,w,l;  // h to y-axis, w to z axis, l to x-asis
    private float shifter;
    
    public CollisionLayer(int layer_type, float dimension, float shifter,Vector3f centre) throws Exception {
          directions = new Vector3f[9]; 
          h = dimension;
          w = dimension;
          l = dimension;
          this.shifter = shifter;
          
        if(layer_type==0){
          vertical_shifting(shifter,w,l,centre); 
        }       
        else if(layer_type==1){
          horizontal_shifting(h,w,shifter,centre);           
        }
        else{
         System.out.println("Incomapible layer type");
         throw new Exception();
        }
    }

    private void vertical_shifting(float h, float w, float l, Vector3f centre) {
        directions[1] = new Vector3f(centre.x+l/2,centre.y+h,centre.z+0);
        directions[2] = new Vector3f(centre.x+l/2,centre.y+h,centre.z+w/2);
        directions[3] = new Vector3f(centre.x+0,centre.y+h,centre.z+w/2);
        directions[4] = new Vector3f(centre.x-l/2,centre.y+h,centre.z+w/2);
        directions[5] = new Vector3f(centre.x-l/2,centre.y+h,centre.z+0);
        directions[6] = new Vector3f(centre.x-l/2,centre.y+h,centre.z-w/2);
        directions[7] = new Vector3f(centre.x+0,centre.y+h,centre.z-w/2);
        directions[8] = new Vector3f(centre.x+l/2,centre.y+h,centre.z-w/2);
    }

    private void horizontal_shifting(float h, float w, float l, Vector3f centre) { 
        directions[1] = new Vector3f(centre.x+l,centre.y+0,centre.z+w/2);
        directions[2] = new Vector3f(centre.x+l,centre.y+h/2,centre.z+w/2);
        directions[3] = new Vector3f(centre.x+l,centre.y+h/2,centre.z+0);
        directions[4] = new Vector3f(centre.x+l,centre.y+h/2,centre.z-w/2);
        directions[5] = new Vector3f(centre.x+l,centre.y+0,centre.z-w/2);
        directions[6] = new Vector3f(centre.x+l,centre.y-h/2,centre.z-w/2);
        directions[7] = new Vector3f(centre.x+l,centre.y-h/2,centre.z+0);
        directions[8] = new Vector3f(centre.x+l,centre.y-h/2,centre.z-w/2);
    }

    public Vector3f[] getDirections() {
        return directions;
    }
    
    
}
