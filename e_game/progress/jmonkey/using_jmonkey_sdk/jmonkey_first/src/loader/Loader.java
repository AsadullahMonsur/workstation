package loader;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.Spatial.CullHint;
import com.jme3.texture.Texture;


public class Loader {
    private Node node;
    public Loader(AssetManager assetManager, String object_path,String material_path){ 
       try{ 
        Spatial model = assetManager.loadModel(object_path);
        
        node = new Node("model");
        node.attachChild(model);

        node.scale(0.5f);
        node.rotate(0.0f, FastMath.PI, 0.0f);
        node.setLocalTranslation(0.0f, -0.75f, -5.0f);
        model.setLocalTranslation(0.0f, -1.0f, 0f);
        
        // Material material = new Material(assetManager,material_path);        
        //  model.setMaterial(material);
        model.setCullHint(CullHint.Never);
          
      }
       catch(Exception e){
          System.out.println(e.getMessage());
      }
    }


    public Node getNode() {
        return node;
    }
        
}
