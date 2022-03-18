/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customs;

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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asadullah Monsur
 */
public class CubeCharacter extends Node{
    private float dimension;
    
    private float h = 0.0f;   //height on y-axis
    private float w = 0.0f;   //width on z-axis
    private float l = 0.0f;   //length on x-axis
    
    private int default_layers = 5;
    private CollisionLayer  []vertical_layers;
    private CollisionLayer  []horizontal_layers;

    private Vector3f centre = new Vector3f(0.0f,0.0f,0.0f);
    
    private int identifier = 0;
    private int ui = 0;
    public CubeCharacter() {
        dimension = 0.5f;
        vertical_layers = new CollisionLayer[6];
        horizontal_layers = new CollisionLayer[6];
        
        localToWorld(this.getLocalTranslation(), centre);
        centre = new Vector3f(centre.x,centre.y+0.5f,centre.z);
        
       // vertical_stack_manager(dimension,default_layers);
       // horizontal_stack_manager(dimension,default_layers);
    }

    public CubeCharacter(float dimension) {
        this.dimension = dimension;
        localToWorld(this.getLocalTranslation(), centre);
        centre = new Vector3f(centre.x,centre.y+0.5f,centre.z);
        
        if(dimension<=0){
            dimension = 0.5f;
        }
        
        int layer_amount = custom_layers(dimension);
        vertical_layers = new CollisionLayer[layer_amount];
        horizontal_layers = new CollisionLayer[layer_amount];
           
       // vertical_stack_manager(dimension,layer_amount);
       //horizontal_stack_manager(dimension,layer_amount);
    }
    
    private int custom_layers(float dimension){
        if(dimension<1){
           return default_layers;
        }
        int layer_amount = (int)dimension*10;
        
        if(layer_amount%2==0){
            layer_amount = layer_amount - 1;
            return layer_amount;
        }
        
        return layer_amount;
    }
    
    private boolean vertical_stack_manager(float dimension, int layer_amount){
        
        try {
            int loop_counter = (layer_amount/2)+1;
            float shifter = dimension/layer_amount;
            
            CollisionLayer layer = new CollisionLayer(0,dimension,0,centre);
            vertical_layers[vertical_layers.length/2] = layer;

            boolean r1 = form_layers(dimension,loop_counter,1,shifter,0);
            boolean r2 = form_layers(dimension,loop_counter,1,-shifter,0);
            if(r1 && r2){
                return true;
            }
        } 
        catch (Exception ex) {
            System.out.println("Exception in Vertical Stack \n"+ex);
            Logger.getLogger(CubeCharacter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    private boolean horizontal_stack_manager(float dimension, int layer_amount){
        try {
            int loop_counter = (layer_amount/2)+1;
            float shifter = dimension/layer_amount;
            
            CollisionLayer layer = new CollisionLayer(1,dimension,0,centre);
            horizontal_layers[horizontal_layers.length/2] = layer;
            boolean r1 = form_layers(dimension,loop_counter,1,shifter,1);
            boolean r2 = form_layers(dimension,loop_counter,1,-shifter,1);
     
            if(r1 && r2){
                return true;
            }
        }
        catch (Exception ex) {
            System.out.println("Exception in Vertical Stack \n"+ex);
            Logger.getLogger(CubeCharacter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    private boolean form_layers(float dimension, int loop_counter, int i, float shifter,int LT) throws Exception {
       if(i<loop_counter){
            CollisionLayer layer = new CollisionLayer(LT,dimension,shifter,centre);
            if(LT==0){
                if(shifter>0){
                    vertical_layers[(vertical_layers.length/2)+i] = layer;
                    System.out.println("doing verticle positive: "+shifter);
                }
                else {
                    vertical_layers[(vertical_layers.length/2)-i] = layer;
                    System.out.println("doing verticle negative: "+shifter);
                }
              System.out.println("verticle: ");
            }
            else if(LT==1){
                if(shifter>0){
                    horizontal_layers[(horizontal_layers.length/2)+i] = layer;
                    System.out.println("doing horizontal positive: "+shifter);
                }
                else{
                    horizontal_layers[(horizontal_layers.length/2)-i] = layer;
                    System.out.println("doing horizontal negative: "+shifter);
                }
                System.out.println("horizontal: ");
            }
            System.out.println("condition true layer_form");
            form_layers(dimension,loop_counter,(++i),(shifter+shifter),LT);

       }
        System.out.println("layer_form running");
        return true;
    }

    public boolean collision_result(AssetManager assetManager,
            Node rootNode,Node obstacles,Geometry mark){
      
//       localToWorld(this.getLocalTranslation(), centre);
//       centre = new Vector3f(centre.x,centre.y+0.5f,centre.z);
//       System.out.println("looper cal: "+(++ui));
//       boolean r1 = vertical_stack_manager(dimension,custom_layers(dimension));
//       boolean r2 = horizontal_stack_manager(dimension,custom_layers(dimension));
//       
//       if(r1 && r2){
//         return  calculation(assetManager,rootNode,
//                                 obstacles,mark);
//       }


    try{
       int i = 1;

       while(i<vertical_layers.length){
          CollisionLayer vlayer = vertical_layers[i]; 
          CollisionLayer hlayer = horizontal_layers[i];
          
          Vector3f []vdirections = vlayer.getDirections();
          boolean v1 = individual_collision(assetManager,rootNode,obstacles,mark,centre,vdirections[1],0);
          boolean v2 = individual_collision(assetManager,rootNode,obstacles,mark,centre,vdirections[2],0);
          boolean v3 = individual_collision(assetManager,rootNode,obstacles,mark,centre,vdirections[3],0);
          boolean v4 = individual_collision(assetManager,rootNode,obstacles,mark,centre,vdirections[4],0);
          boolean v5 = individual_collision(assetManager,rootNode,obstacles,mark,centre,vdirections[5],0);
          boolean v6 = individual_collision(assetManager,rootNode,obstacles,mark,centre,vdirections[6],0);
          boolean v7 = individual_collision(assetManager,rootNode,obstacles,mark,centre,vdirections[7],0);
          boolean v8 = individual_collision(assetManager,rootNode,obstacles,mark,centre,vdirections[8],0);
          
          Vector3f []hdirections = hlayer.getDirections();
          boolean h1 = individual_collision(assetManager,rootNode,obstacles,mark,centre,hdirections[1],1);
          boolean h2 = individual_collision(assetManager,rootNode,obstacles,mark,centre,hdirections[2],1);
          boolean h3 = individual_collision(assetManager,rootNode,obstacles,mark,centre,hdirections[3],1);
          boolean h4 = individual_collision(assetManager,rootNode,obstacles,mark,centre,hdirections[4],1);
          boolean h5 = individual_collision(assetManager,rootNode,obstacles,mark,centre,hdirections[5],1);
          boolean h6 = individual_collision(assetManager,rootNode,obstacles,mark,centre,hdirections[6],1);
          boolean h7 = individual_collision(assetManager,rootNode,obstacles,mark,centre,hdirections[7],1);
          boolean h8 = individual_collision(assetManager,rootNode,obstacles,mark,centre,hdirections[8],1);

          i++;
       }
       }
       catch(Exception ex){
          ex.printStackTrace();
       }
       return false;
    }
    
    private boolean individual_collision(AssetManager assetManager, Node rootNode,
            Node obstacles, Geometry mark, Vector3f centre, Vector3f direction,int flag){
        System.out.println("individual layers : "+(++identifier));
        
        CollisionResults results = new CollisionResults();

        Ray ray = new Ray(centre, direction);
        obstacles.collideWith(ray, results);

         if (results.size() > 0) {
            CollisionResult closest = results.getClosestCollision();
            System.out.println("Hit : "+closest.getDistance());
            if(closest.getDistance()<2.1f){
              return true;
            }
            
            mark.setLocalTranslation(closest.getContactPoint());
            rootNode.attachChild(mark);
           
        }
         else {
          //rootNode.detachChild(mark);
        }
        visible_ray(centre,direction,rootNode,assetManager,flag);
        return false;
    }

    public CollisionLayer[] getVertical_layers() {
        return vertical_layers;
    }

    public CollisionLayer[] getHorizontal_layers() {
        return horizontal_layers;
    }
    
    private void visible_ray(Vector3f point1, Vector3f point2,
            Node rootNode,AssetManager assetManager,int flag){
      
      Line line = new Line(point1,point2);
      line.setLineWidth(2);
      
      Geometry ray = new Geometry("ray", line);  
      
      Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
      if(flag==0){
        material.setColor("Color", ColorRGBA.Red);
      }
      else{
        material.setColor("Color", ColorRGBA.Blue);
      }

      ray.setMaterial(material);
      
      rootNode.attachChild(ray);
  }
    
    private boolean calculation(AssetManager assetManager,Node rootNode,Node obstacles,
                   Geometry mark){

      try{
       int i = 1;

       while(i<vertical_layers.length){
          CollisionLayer vlayer = vertical_layers[i]; 
          CollisionLayer hlayer = horizontal_layers[i];
          
          Vector3f []vdirections = vlayer.getDirections();
          boolean v1 = individual_collision(assetManager,rootNode,obstacles,mark,centre,vdirections[1],0);
          boolean v2 = individual_collision(assetManager,rootNode,obstacles,mark,centre,vdirections[2],0);
          boolean v3 = individual_collision(assetManager,rootNode,obstacles,mark,centre,vdirections[3],0);
          boolean v4 = individual_collision(assetManager,rootNode,obstacles,mark,centre,vdirections[4],0);
          boolean v5 = individual_collision(assetManager,rootNode,obstacles,mark,centre,vdirections[5],0);
          boolean v6 = individual_collision(assetManager,rootNode,obstacles,mark,centre,vdirections[6],0);
          boolean v7 = individual_collision(assetManager,rootNode,obstacles,mark,centre,vdirections[7],0);
          boolean v8 = individual_collision(assetManager,rootNode,obstacles,mark,centre,vdirections[8],0);
          
          Vector3f []hdirections = hlayer.getDirections();
          boolean h1 = individual_collision(assetManager,rootNode,obstacles,mark,centre,hdirections[1],1);
          boolean h2 = individual_collision(assetManager,rootNode,obstacles,mark,centre,hdirections[2],1);
          boolean h3 = individual_collision(assetManager,rootNode,obstacles,mark,centre,hdirections[3],1);
          boolean h4 = individual_collision(assetManager,rootNode,obstacles,mark,centre,hdirections[4],1);
          boolean h5 = individual_collision(assetManager,rootNode,obstacles,mark,centre,hdirections[5],1);
          boolean h6 = individual_collision(assetManager,rootNode,obstacles,mark,centre,hdirections[6],1);
          boolean h7 = individual_collision(assetManager,rootNode,obstacles,mark,centre,hdirections[7],1);
          boolean h8 = individual_collision(assetManager,rootNode,obstacles,mark,centre,hdirections[8],1);

          i++;
       }
       }
       catch(Exception ex){
          ex.printStackTrace();
       }
      return true;
    }
    
    public void updates_fart(){
       localToWorld(this.getLocalTranslation(), centre);
       centre = new Vector3f(centre.x,centre.y+0.5f,centre.z);
       System.out.println("looper cal: "+(++ui));
       boolean r1 = vertical_stack_manager(dimension,custom_layers(dimension));
       boolean r2 = horizontal_stack_manager(dimension,custom_layers(dimension));

    }
}
