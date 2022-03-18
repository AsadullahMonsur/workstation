package lesson_01;

import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.Viewer;
import com.sun.j3d.utils.universe.ViewingPlatform;
import interaction_chap01.Player_Behavior;
import interaction_chap01.Player_Controller;
import interaction_chap03.Player_Movement;
import interaction_chap04.Player_Around;
import interaction_chap05.CameraMoving;
import interaction_chap05.Domo;
import interaction_chap05.Frame_Rate;
import interaction_chap05.KeyMoving;
import interaction_chap06.Camera_Roaming;
import interaction_chap06.Player_Roaming;
import interaction_chap06.Refresh_Rate;
import lesson_02.Ground_Floor;
import lesson_05.Obstacles;
import lesson_05.TourSprite;
import lesson_05.TouristControls;

import javax.media.j3d.*;
import javax.vecmath.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Viewer3D extends Applet {

    private String path = "/resources/Convertible.obj";
    private String path2 ="/resources/model.obj";
    private String path3 = "/resources/M-FF_iOS_HERO_Tony_Stark_Iron_Man_Civil_War.obj";
    private Canvas3D canvas;
    private SimpleUniverse universe;
    TransformGroup roots;
    TransformGroup voots;
    public Viewer3D() {
        setLayout(new BorderLayout());
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        canvas = new Canvas3D(config);
        add("Center",canvas);

         roots = new TransformGroup();
        Transform3D root_transform = new Transform3D();
        roots.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        roots.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        roots.setTransform(root_transform);
        root_transform.setScale(0.5);

        voots = new TransformGroup();
        Transform3D voot_transform = new Transform3D();
        voots.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        voots.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        voots.setTransform(voot_transform);
        voot_transform.setScale(0.5);

//        ViewingPlatform viewingPlatform = new ViewingPlatform();
//        viewingPlatform.getViewPlatform().setActivationRadius(300f);
//
//        TransformGroup viewTransform = viewingPlatform.getViewPlatformTransform();
//        viewTransform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
//        viewTransform.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
//        Transform3D m = new Transform3D();
//        viewTransform.setTransform(m);
//
//        viewTransform.addChild(roots);
//
//        Point3d centre = new Point3d(0,0,-4);
//        Point3d eye = new Point3d(0.0,2,3);
//        Vector3d up = new Vector3d(0,1,0);
//        m.lookAt(eye,centre, up);//eye, center, up
//        m.invert();
//        viewTransform.setTransform(m);

        Viewer viewer = new Viewer(canvas);
        View view = viewer.getView();
        view.setBackClipDistance(300);

        Refresh_Rate rate = new Refresh_Rate();
        roots.addChild(rate);
//        roots.addChild(rate.getDoom());
        Transform3D yAxis = new Transform3D();
        yAxis.rotY(Math.toRadians(5.0));
        Player_Roaming player_roaming = new Player_Roaming(canvas,
                                        getClass().getResource(path3).getFile());
        roots.addChild(player_roaming);
        roots.addChild(player_roaming.getPlayer());
//        Alpha alpha = new Alpha (-1, 60);
//        RotationInterpolator rotator1 =
//                new RotationInterpolator(alpha,
//                        player_roaming.getPlayer(),
//                        yAxis,
//                        0.0f, (float) Math.PI*2.0f);
//        rotator1.setSchedulingBounds(new BoundingSphere());
//        roots.addChild(rotator1);

//        CameraMoving camera = new CameraMoving(canvas,player.getModel_group(),player,rate);
//        roots.addChild(camera);

        Camera_Roaming camera_roaming = new Camera_Roaming(canvas,player_roaming,rate);
        roots.addChild(camera_roaming);
        universe = new SimpleUniverse(camera_roaming.getView_platform(),viewer);
//       universe = new SimpleUniverse(canvas);
//        Domo player_around = new Domo(
//                canvas,path3);
//        roots.addChild(player_around);
//        roots.addChild(player_around.getModel_group());
//      universe.getViewingPlatform().setNominalViewingTransform();


        universe.getViewer().getView().setBackClipDistance(500.0);

        BranchGroup graph = get_graph();

        universe.addBranchGraph(graph);
    }

    private BranchGroup get_graph() {
        BranchGroup graph = new BranchGroup();

        TransformGroup tGroup = new TransformGroup();
        Transform3D transform = new Transform3D();
        tGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        transform.setScale(0.5);
        tGroup.setTransform(transform);

        BoundingSphere bound = form_boundary(graph);
        lightening(graph,bound);

//        TransformGroup model_group = new TransformGroup();
//        model_group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
//
//        Loader loader = new Loader(path3);
//        model_group.addChild(loader.obj_file_to_graph());
//        tGroup.addChild(model_player);
//        controlling(model_group,transform,graph);

        Ground_Floor  ground_floor = new Ground_Floor(10);
        TransformGroup floor = ground_floor.form_floor(0);
        BranchGroup graph_element_01 = new BranchGroup();
        graph_element_01.addChild(floor);
        graph_element_01.compile();


        TransformGroup obstacles = ground_floor.form_obstacle();
        BranchGroup graph_element_02 = new BranchGroup();
        graph_element_02.addChild(obstacles);
        graph_element_02.compile();

        TransformGroup space = new TransformGroup();
        tGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        Transform3D space_transform = new Transform3D();
        space.setTransform(space_transform);
        space.addChild(graph_element_01);
        space.addChild(graph_element_02);


//        TransformGroup roots = new TransformGroup();
//        Transform3D root_transform = new Transform3D();
//        roots.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
//        roots.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
//        roots.setTransform(root_transform);


        universe.getViewer().getView().setDepthBufferFreezeTransparent(false);

       // Player_Behavior playerBehavior = new Player_Behavior(space,space_transform,transform,path3);

//        KeyMoving player = new KeyMoving(universe.getViewingPlatform(),canvas,space,space_transform,root_transform,path3);
        //roots.addChild(player.getModel_group());
        //roots.addChild(player);
//

//          MouseRotate myMouseRotate = new MouseRotate();
//        myMouseRotate.setTransformGroup(roots);
//        myMouseRotate.setSchedulingBounds( new BoundingSphere() );
//        myMouseRotate.setFactor(1.0,0);
//        tGroup.addChild(myMouseRotate);


        tGroup.addChild(voots);
        tGroup.addChild(roots);
//        tGroup.addChild(keyNavBeh);
        graph.addChild(space);
        graph.addChild(tGroup);
        graph.compile();

        return graph;
    }

    private void control_player(Player_Behavior playerBehavior, BranchGroup graph) {
        Player_Controller player_controller = new Player_Controller(canvas,playerBehavior);
    }

    private void controlling(TransformGroup transGroup, Transform3D transform,
                             BranchGroup graph) {

        MouseRotate myMouseRotate = new MouseRotate();
        myMouseRotate.setTransformGroup(transGroup);
        myMouseRotate.setSchedulingBounds(new BoundingSphere());
        graph.addChild(myMouseRotate);

  //     Controller controller = new Controller(canvas,transGroup,transform);
    }

    private void lightening(BranchGroup graph, BoundingSphere bound) {

        Color3f lightColor = new Color3f(1.0f,1.0f,0.9f);
        Vector3f lightDirection = new Vector3f(4.0f,-7.0f,-12.0f);

        DirectionalLight light = new DirectionalLight(lightColor, lightDirection);

        graph.addChild(light);
        light.setInfluencingBounds(bound);
    }

    private BoundingSphere form_boundary(BranchGroup graph) {
        BoundingSphere bound= new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
        Color3f bgColor = new Color3f(1.0f,1.0f,1.0f);
        Background bg = new Background(bgColor);
        bg.setApplicationBounds(bound);
        graph.addChild(bg);
        return bound;
    }
}
