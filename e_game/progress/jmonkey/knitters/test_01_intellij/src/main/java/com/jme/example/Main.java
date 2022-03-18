package com.jme.example;


import com.jme.example.loader.Loader;
import com.jme3.app.SimpleApplication;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;

public class Main extends SimpleApplication {

    public static void main(String args[]){
        AppSettings settings = new AppSettings(true);
        settings.setResolution(1024,600);
        settings.setTitle("JMonkey");
        Main main = new Main();
        main.setSettings(settings);
        main.start();

    }

    @Override
    public void simpleInitApp() {
        //ColorRGBA.White
        ColorRGBA color = new ColorRGBA(0.5f,0.5f,0.5f,1);
        viewPort.setBackgroundColor(ColorRGBA.White);
        Loader loader = new Loader(assetManager,"/Models/M-FF_iOS_HERO_Tony_Stark_Iron_Man_Civil_War.obj");
        Node model = loader.getNode();
        rootNode.attachChild(model);
    }
}
