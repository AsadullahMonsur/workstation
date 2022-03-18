/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package character;

import com.jme3.asset.AssetManager;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Line;
import customs.CollisionLayer;

/**
 *
 * @author Asadullah Monsur
 */
public class ConcealedCharacter extends Node{
    // layer_type: 0 for vertical_stack, 1 for horizontal 
    
    private Vector3f []vdirections; // vertical direction
    private Vector3f []hdirections; // horizontal direction
     
    private float h,w,l;  // h to y-axis, w to z axis, l to x-asis
    
    private int ray_visibility = 1;
    private ColorRGBA []colors = new ColorRGBA[2];
    
    public ConcealedCharacter(float dimension){
        vdirections = new Vector3f[9];
        hdirections = new Vector3f[9];
        
        h = dimension;
        w = dimension;
        l = dimension;
        
        colors[1] = ColorRGBA.Red; // horizontal_layer
        colors[0] = ColorRGBA.Blue;  // vertical_layer
    }
    
    public boolean collision_result(AssetManager assetManager,
            Node rootNode,Node obstacles,Geometry mark, int collision_direction){
      
      try{
          Vector3f centre = get_centre_location();
          Vector3f []vdirection = vertical_layer(0,w,l,centre);          
          Vector3f []hdirection = horizontal_layer(h,w,0,centre);
 
          if(collision_direction==1){
              boolean h4 = individual_collision(assetManager,rootNode,obstacles,mark,centre,hdirection[4],colors,ray_visibility,1);
              boolean h5 = individual_collision(assetManager,rootNode,obstacles,mark,centre,hdirection[5],colors,ray_visibility,1);
              boolean h6 = individual_collision(assetManager,rootNode,obstacles,mark,centre,hdirection[6],colors,ray_visibility,1);

              boolean v6 = individual_collision(assetManager,rootNode,obstacles,mark,centre,vdirection[6],colors,ray_visibility,0);
              boolean v7 = individual_collision(assetManager,rootNode,obstacles,mark,centre,vdirection[7],colors,ray_visibility,0);
              boolean v8 = individual_collision(assetManager,rootNode,obstacles,mark,centre,vdirection[8],colors,ray_visibility,0);

              System.out.println("Forward: "+((h4||h5||h6) || (v6||v7||v8)));
              
              return ((h4||h5||h6) || (v6||v7||v8));
          }
          else if(collision_direction==2){

               boolean h1 = individual_collision(assetManager,rootNode,obstacles,mark,centre,hdirection[1],colors,ray_visibility,1);
               boolean h2 = individual_collision(assetManager,rootNode,obstacles,mark,centre,hdirection[2],colors,ray_visibility,1);
               boolean h8 = individual_collision(assetManager,rootNode,obstacles,mark,centre,hdirection[8],colors,ray_visibility,1);

               boolean v2 = individual_collision(assetManager,rootNode,obstacles,mark,centre,vdirection[2],colors,ray_visibility,0);
               boolean v3 = individual_collision(assetManager,rootNode,obstacles,mark,centre,vdirection[3],colors,ray_visibility,0);
               boolean v4 = individual_collision(assetManager,rootNode,obstacles,mark,centre,vdirection[4],colors,ray_visibility,0);

               System.out.println("Backward: "+((h1||h2||h8) || (v2||v3||v4)));               
               return ((h1||h2||h8) || (v2||v3||v4));
          }
          else if(collision_direction==3){
             boolean v1 = individual_collision(assetManager,rootNode,obstacles,mark,centre,vdirection[1],colors,ray_visibility,0);
             boolean v2 = individual_collision(assetManager,rootNode,obstacles,mark,centre,vdirection[2],colors,ray_visibility,0);
             boolean v8 = individual_collision(assetManager,rootNode,obstacles,mark,centre,vdirection[8],colors,ray_visibility,0);
          
             System.out.println("Right: "+(v1||v2||v8));             
             return (v1||v2||v8);
          }
          else if(collision_direction==4){
             boolean v4 = individual_collision(assetManager,rootNode,obstacles,mark,centre,vdirection[4],colors,ray_visibility,0);
             boolean v5 = individual_collision(assetManager,rootNode,obstacles,mark,centre,vdirection[5],colors,ray_visibility,0);
             boolean v6 = individual_collision(assetManager,rootNode,obstacles,mark,centre,vdirection[6],colors,ray_visibility,0);
          
             System.out.println("Left: "+(v4||v5||v6)); 
             return (v4||v5||v6);
          }
          else if(collision_direction==5){
             boolean v2 = individual_collision(assetManager,rootNode,obstacles,mark,centre,vdirection[2],colors,ray_visibility,0);
             boolean v3 = individual_collision(assetManager,rootNode,obstacles,mark,centre,vdirection[3],colors,ray_visibility,0);
             boolean v4 = individual_collision(assetManager,rootNode,obstacles,mark,centre,vdirection[4],colors,ray_visibility,0);

             boolean h2 = individual_collision(assetManager,rootNode,obstacles,mark,centre,hdirection[2],colors,ray_visibility,1);
             boolean h3 = individual_collision(assetManager,rootNode,obstacles,mark,centre,hdirection[3],colors,ray_visibility,1);
             boolean h4 = individual_collision(assetManager,rootNode,obstacles,mark,centre,hdirection[4],colors,ray_visibility,1);

             return ((h2||h3||h4) || (v2||v3||v4));
          }
          else if(collision_direction==6){
             boolean v6 = individual_collision(assetManager,rootNode,obstacles,mark,centre,vdirection[6],colors,ray_visibility,0);
             boolean v7 = individual_collision(assetManager,rootNode,obstacles,mark,centre,vdirection[7],colors,ray_visibility,0);
             boolean v8 = individual_collision(assetManager,rootNode,obstacles,mark,centre,vdirection[8],colors,ray_visibility,0);

             boolean h6 = individual_collision(assetManager,rootNode,obstacles,mark,centre,hdirection[6],colors,ray_visibility,1);
             boolean h7 = individual_collision(assetManager,rootNode,obstacles,mark,centre,hdirection[7],colors,ray_visibility,1);
             boolean h8 = individual_collision(assetManager,rootNode,obstacles,mark,centre,hdirection[8],colors,ray_visibility,1);

             return ((h6||h7||h8) || (v6||v7||v8));
          }
          
        }
        catch(Exception ex){
          ex.printStackTrace();
       }
       return false;
    }
    
    private boolean individual_collision(AssetManager assetManager, Node rootNode,
           Node obstacles, Geometry mark, Vector3f centre,
           Vector3f direction,ColorRGBA colors[],int visibility,int layer_type){
        
        CollisionResults results = new CollisionResults();

        Ray ray = new Ray(centre, direction);
        obstacles.collideWith(ray, results);

         if (results.size() > 0) {
            CollisionResult closest = results.getClosestCollision();
            System.out.println("Hit : "+closest.getDistance());
            if(closest.getDistance()<1.1f){
               mark.setLocalTranslation(closest.getContactPoint());
               rootNode.attachChild(mark);
              return true;
            }
            rootNode.detachChild(mark);      
        }
        else {
          rootNode.detachChild(mark);
        }
        if(visibility==1){ 
           make_ray_visible(centre,direction,rootNode,
                                         assetManager,colors,layer_type);
        }
        return false;
    }
    
    private Vector3f[] vertical_layer(float h, float w, float l, Vector3f centre) {
        vdirections[1] = new Vector3f(centre.x+l/2,centre.y+h,centre.z+0);
        vdirections[2] = new Vector3f(centre.x+l/2,centre.y+h,centre.z+w/2);
        vdirections[3] = new Vector3f(centre.x+0,centre.y+h,centre.z+w/2);
        vdirections[4] = new Vector3f(centre.x-l/2,centre.y+h,centre.z+w/2);
        vdirections[5] = new Vector3f(centre.x-l/2,centre.y+h,centre.z+0);
        vdirections[6] = new Vector3f(centre.x-l/2,centre.y+h,centre.z-w/2);
        vdirections[7] = new Vector3f(centre.x+0,centre.y+h,centre.z-w/2);
        vdirections[8] = new Vector3f(centre.x+l/2,centre.y+h,centre.z-w/2);
        
        return vdirections;
    }

    private Vector3f[] horizontal_layer(float h, float w, float l, Vector3f centre) { 
        hdirections[1] = new Vector3f(centre.x+l,centre.y+0,centre.z+w/2);
        hdirections[2] = new Vector3f(centre.x+l,centre.y+h/2,centre.z+w/2);
        hdirections[3] = new Vector3f(centre.x+l,centre.y+h/2,centre.z+0);
        hdirections[4] = new Vector3f(centre.x+l,centre.y+h/2,centre.z-w/2);
        hdirections[5] = new Vector3f(centre.x+l,centre.y+0,centre.z-w/2);
        hdirections[6] = new Vector3f(centre.x+l,centre.y-h/2,centre.z-w/2);
        hdirections[7] = new Vector3f(centre.x+l,centre.y-h/2,centre.z+0);
        hdirections[8] = new Vector3f(centre.x+l,centre.y-h/2,centre.z+w/2);
        return hdirections;
    }

   
    private void make_ray_visible(Vector3f centre, Vector3f direction,
        Node rootNode, AssetManager assetManager,ColorRGBA colors[],int layer_type) {
      
      Line line = new Line(centre,direction);
      line.setLineWidth(2);
      
      Geometry ray = new Geometry("ray", line);  
      
      Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");

      if(layer_type==1){
        material.setColor("Color", colors[1]);
      }  
      else if(layer_type==0){
        material.setColor("Color", colors[0]);
      }

      ray.setMaterial(material);
      
      rootNode.attachChild(ray);
    
    }
    
    private Vector3f get_centre_location(){
       Vector3f centre = new Vector3f(0.0f,0.0f,0.0f);
       localToWorld(this.getLocalTranslation(), centre);
       return new Vector3f(centre.x,centre.y+0.5f,centre.z);
    }
    
    public Vector3f[] getCurrentVerticalDirections() {
        return vdirections;
    }

    public Vector3f[] getCurrentHorizontalDirections() {
        return hdirections;
    }

    public void setRay_visibity(int ray_visibility) {
        this.ray_visibility = ray_visibility;
    }

    public void setColors(ColorRGBA[] colors) {
        this.colors = colors;
    }
    
}
