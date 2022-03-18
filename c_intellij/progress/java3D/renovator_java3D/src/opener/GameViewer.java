package opener;

import accessories.RefreshRate;
import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.Viewer;
import environment.Surface;
import participants.PlayerBehaviour;
import view.CameraBehaviour;

import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;
import java.applet.Applet;
import java.awt.*;

public class GameViewer extends Applet {

    private Canvas3D canvas;
    private SimpleUniverse universe;

    public GameViewer(String path) {
        setLayout(new BorderLayout());
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        canvas = new Canvas3D(config);
        add("Center",canvas);

        Viewer viewer = new Viewer(canvas);
        View view = viewer.getView();
        view.setMinimumFrameCycleTime(18);
        view.setBackClipDistance(300);

        RefreshRate rate = new RefreshRate();

        PlayerBehaviour player_behaviour = new PlayerBehaviour(
                                              getClass().getResource(path).getFile());
        CameraBehaviour camera_behaviour = new CameraBehaviour(rate,player_behaviour);

        universe = new SimpleUniverse(camera_behaviour.getView_platform(),viewer);
        universe.getViewer().getView().setBackClipDistance(500.0);
        universe.getViewer().getView().setDepthBufferFreezeTransparent(false);

        BranchGroup graph = prepare_graph(rate,player_behaviour,
                                       camera_behaviour);
        universe.addBranchGraph(graph);
    }

    private BranchGroup prepare_graph(RefreshRate rate, PlayerBehaviour player_behaviour, CameraBehaviour camera_behaviour) {
        BranchGroup scene = new BranchGroup();
        TransformGroup root_element = new TransformGroup();
        Transform3D transform = new Transform3D();

        root_element.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        root_element.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        transform.setScale(0.5);
        root_element.setTransform(transform);

        BoundingSphere bound = form_boundary(scene);
        lightening(scene,bound);

        Surface surface = new Surface(18);

        TransformGroup element_floor = surface.form_floor(0);
        BranchGroup floor = new BranchGroup();
        floor.addChild(element_floor);
        floor.compile();

        TransformGroup element_obstacle = surface.form_obstacle();
        BranchGroup obstacle = new BranchGroup();
        obstacle.addChild(element_obstacle);
        obstacle.compile();

        TransformGroup zone = new TransformGroup();
        zone.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        zone.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        Transform3D zone_transform = new Transform3D();
        zone.setTransform(zone_transform);
        zone.addChild(floor);
        zone.addChild(obstacle);

        root_element.addChild(rate);
        root_element.addChild(player_behaviour);
        root_element.addChild(player_behaviour.getPlayer());
        root_element.addChild(camera_behaviour);
        scene.addChild(zone);
        scene.addChild(root_element);
        scene.compile();

        return scene;
    }

    private void lightening(BranchGroup scene, BoundingSphere bound) {

        Color3f lightColor = new Color3f(1.0f,1.0f,0.9f);
        Vector3f lightDirection = new Vector3f(4.0f,-7.0f,-12.0f);

        DirectionalLight light = new DirectionalLight(lightColor, lightDirection);

        scene.addChild(light);
        light.setInfluencingBounds(bound);
    }

    private BoundingSphere form_boundary(BranchGroup scene) {
        BoundingSphere bound= new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
        Color3f bgColor = new Color3f(1.0f,1.0f,1.0f);
        Background bg = new Background(bgColor);
        bg.setApplicationBounds(bound);
        scene.addChild(bg);
        return bound;
    }
}
