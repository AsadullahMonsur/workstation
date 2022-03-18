package com.company;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.universe.SimpleUniverse;
import lesson_01.Viewer3D;
import lesson_02.Ground_Floor;
import lesson_03.HelloUniverse;

import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;
import java.applet.Applet;
import java.awt.*;

public class Main extends Applet {
    private static final long serialVersionUID = 5841679659336190804L;
    public BranchGroup createSceneGraph(){
        BranchGroup group = new BranchGroup();

        TransformGroup transGroup = new TransformGroup();
        Transform3D trans3d = new Transform3D();
        transGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        transGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        trans3d.setScale(0.8);
        transGroup.setTransform(trans3d);
        group.addChild(transGroup);

        BoundingSphere bound= new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
        Color3f bgColor = new Color3f(0.05f,0.05f,0.2f);
        Background bg = new Background(bgColor);
        bg.setApplicationBounds(bound);
        group.addChild(bg);
        Color3f lightColor = new Color3f(1.0f,1.0f,0.9f);
        Vector3f lightDirection = new Vector3f(4.0f,-7.0f,-12.0f);
        DirectionalLight light = new DirectionalLight(lightColor, lightDirection);
        light.setInfluencingBounds(bound);
        group.addChild(light);
        TransformGroup objTrans = new TransformGroup();
        objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        String path = "/resources/Convertible.obj";
        String path2 ="/resources/model.obj";
        String path3 = "/resources/M-FF_iOS_HERO_Tony_Stark_Iron_Man_Civil_War.obj";
        objTrans.addChild(new ObjFileReader(getClass().getResource(path3).getPath()));
        transGroup.addChild(objTrans);

        MouseRotate myMouseRotate = new MouseRotate();
        myMouseRotate.setTransformGroup(transGroup);
        myMouseRotate.setSchedulingBounds(new BoundingSphere());
        group.addChild(myMouseRotate);

        group.compile();

        return group;
    }

    public Main(){
        setLayout(new BorderLayout());
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas = new Canvas3D(config);
        add("Center",canvas);
        BranchGroup scene = createSceneGraph();

        SimpleUniverse universe = new SimpleUniverse(canvas);
        universe.getViewingPlatform().setNominalViewingTransform();
        universe.addBranchGraph(scene);
    }

    public static void main(String[] args) {
	// write your code here
     //   new MainFrame(new Main(), 360,360);
     //   Player_Behavior abc = new Player_Behavior();

        Frame frame = new MainFrame(new Viewer3D(),700,500);
        frame.setTitle("Java 3D API");

//         Frame frame = new Viewer3D();
//         frame.setBounds(100,100,700,500);
//         frame.setTitle("Java 3D API");
//         frame.setVisible(true);

//        Frame frame = new MainFrame(new HelloUniverse(),800,500);
//        frame.setTitle("Java 3D API");
    }
}

