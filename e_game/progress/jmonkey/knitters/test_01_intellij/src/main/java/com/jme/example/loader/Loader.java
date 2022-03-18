package com.jme.example.loader;

import com.jme3.asset.AssetManager;
import com.jme3.math.FastMath;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

public class Loader {
    private Node node;
    private Spatial spatial;

    public Loader(AssetManager assetManager, String object_path){
        try{
            spatial = assetManager.loadModel(object_path);

//            node = new Node("model");
//            node.attachChild(model);
//
//            node.scale(0.5f);
//            node.rotate(0.0f, FastMath.PI, 0.0f);
//            node.setLocalTranslation(0.0f, -0.75f, -5.0f);
//            model.setLocalTranslation(0.0f, -1.0f, 0f);
//
//            model.setCullHint(Spatial.CullHint.Never);

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


    public Spatial getSpatial() {
        return spatial;
    }

    public Node getNode() {
        return node;
    }
}
