package lesson_04;

import java.awt.*;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.PlatformGeometry;
import com.sun.j3d.utils.behaviors.keyboard.*;

import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import java.io.*;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.util.*;

import java.awt.event.*;

public class Mykeynavbeh extends Frame implements KeyListener {
    private String path3 = "F:\\model_3d\\three_D\\iron_man\\M-FF_iOS_HERO_Tony_Stark_Iron_Man_Civil_War\\M-FF_iOS_HERO_Tony_Stark_Iron_Man_Civil_War.obj";
    private SimpleUniverse universe = null;
    private Canvas3D canvas = null;
    private TransformGroup viewtrans = null;

    private TransformGroup tg = null;
    private Transform3D t3d = null;
    private Transform3D t3dstep = new Transform3D();
    private Matrix4d matrix = new Matrix4d();

    private MovingMScooter mscooter = null;
    private boolean cstate = false;

    private Transform3D t3d_vt = new Transform3D();

    private Vector3d trans = new Vector3d();
    private Vector3d pre_trans = new Vector3d();

    public Mykeynavbeh() {
        setLayout(new BorderLayout());
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();

        canvas = new Canvas3D(config);
        add("Center", canvas);
        universe = new SimpleUniverse(canvas);

        BranchGroup scene = createSceneGraph();
        universe.getViewingPlatform().setNominalViewingTransform();

        universe.getViewer().getView().setBackClipDistance(100.0);

        canvas.addKeyListener(this);

        universe.addBranchGraph(scene);
    }

    private BranchGroup createSceneGraph() {
        BranchGroup objRoot = new BranchGroup();

        BoundingSphere bounds = new BoundingSphere(new Point3d(), 10000.0);

        viewtrans = universe.getViewingPlatform().getViewPlatformTransform();

        KeyNavigatorBehavior keyNavBeh = new KeyNavigatorBehavior(viewtrans);
        keyNavBeh.setSchedulingBounds(bounds);
        PlatformGeometry platformGeom = new PlatformGeometry();
        platformGeom.addChild(keyNavBeh);
        universe.getViewingPlatform().setPlatformGeometry(platformGeom);

        objRoot.addChild(createMScooter());
        objRoot.addChild(createApartment());

        return objRoot;
    }

    private BranchGroup createMScooter() {

        BranchGroup objRoot = new BranchGroup();
        tg = new TransformGroup();
        t3d = new Transform3D();

        // tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        t3d.setTranslation(new Vector3d(0.0, -0.3, -1.9));
        // t3d.setRotation(new AxisAngle4f(0.0f, 1.0f, 0.0f, 3.0f));
        t3d.setScale(0.1);

        tg.setTransform(t3d);

        mscooter = new MovingMScooter(path3);
        tg.addChild(mscooter.tg);
        // car.tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        tg.addChild(mscooter);

        objRoot.addChild(tg);
        objRoot.addChild(createLight());
        objRoot.addChild(createLight2());

        objRoot.compile();

        return objRoot;

    }

    private BranchGroup createApartment() {

        BranchGroup objRoot = new BranchGroup();

        TransformGroup tg = new TransformGroup();
        Transform3D t3d = new Transform3D();

        t3d.setTranslation(new Vector3d(0.0, -0.8, -16.0));
        t3d.setScale(2.5);
        tg.setTransform(t3d);

        tg.addChild(createObjLoad(path3));

        objRoot.addChild(tg);
        objRoot.addChild(createLight());
        objRoot.addChild(createLight2());

        objRoot.compile();

        return objRoot;

    }

    private BranchGroup createObjLoad(String filename) {

        BranchGroup objRoot = new BranchGroup();

        TransformGroup tg = new TransformGroup();
        Transform3D t3d = new Transform3D();
        t3d.setScale(1.0);
        tg.setTransform(t3d);

        ObjectFile loader = new ObjectFile();
        Scene s = null;

        File file = new java.io.File(filename);

        try {
            s = loader.load(file.toURI().toURL());
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }

        tg.addChild(s.getSceneGroup());

        objRoot.addChild(tg);

        objRoot.compile();

        return objRoot;

    }

    private Light createLight() {
        DirectionalLight light = new DirectionalLight(true, new Color3f(1.0f, 1.0f, 1.0f),
                new Vector3f(-0.3f, 0.2f, -1.0f));

        light.setInfluencingBounds(new BoundingSphere(new Point3d(), 10000.0));

        return light;
    }

    private Light createLight2() {
        DirectionalLight light = new DirectionalLight(true, new Color3f(1.0f, 1.0f, 1.0f),
                new Vector3f(0.3f, -0.2f, 1.0f));

        light.setInfluencingBounds(new BoundingSphere(new Point3d(), 10000.0));

        return light;
    }

    public static void main(String args[]) {

        Mykeynavbeh window = new Mykeynavbeh();

        window.setSize(800, 600);

        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        window.setVisible(true);
    }

    public void keyTyped(KeyEvent e) {
        char key = e.getKeyChar();

        if (key == 'd') {
            cstate = true;
            mscooter.setEnable(cstate);
        }

        if (key == 's') {

            t3dstep.rotY(Math.PI / 32);
            mscooter.tg.getTransform(mscooter.t3d);
            mscooter.t3d.get(matrix);
            mscooter.t3d.setTranslation(new Vector3d(0.0, 0.0, 0.0));
            mscooter.t3d.mul(t3dstep);
            mscooter.t3d.setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
            mscooter.tg.setTransform(mscooter.t3d);

        }

        if (key == 'f') {

            t3dstep.rotY(-Math.PI / 32);
            mscooter.tg.getTransform(mscooter.t3d);
            mscooter.t3d.get(matrix);
            mscooter.t3d.setTranslation(new Vector3d(0.0, 0.0, 0.0));
            mscooter.t3d.mul(t3dstep);
            mscooter.t3d.setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
            mscooter.tg.setTransform(mscooter.t3d);

        }

        if (key == 'x') {
            cstate = false;
            mscooter.setEnable(cstate);
        }

    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
    }

    class MovingMScooter extends Behavior {

        public TransformGroup tg = null;
        public Transform3D t3d = null;
        private Transform3D t3dstep = new Transform3D();
        private WakeupOnElapsedFrames wakeFrame = null;

        public MovingMScooter(String filename) {

            tg = new TransformGroup();
            t3d = new Transform3D();

            t3d.setTranslation(new Vector3d(0.0, 0.0, 0.0));
            t3d.setScale(1.0);
            tg.setTransform(t3d);

            tg.addChild(createObjLoad(filename));

            tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

            BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 1000.0);
            this.setSchedulingBounds(bounds);
        }

        public void initialize() {
            wakeFrame = new WakeupOnElapsedFrames(0);
            wakeupOn(wakeFrame);
        }

        public void processStimulus(Enumeration criteria) {

            tg.getTransform(t3d);
            t3d.get(trans);

            t3dstep.set(new Vector3d((trans.x - pre_trans.x) * 0.1, (trans.y - pre_trans.y) * 0.1,
                    (trans.z - pre_trans.z) * 0.1 * 0.7));
            viewtrans.getTransform(t3d_vt);
            t3d_vt.mul(t3dstep);
            viewtrans.setTransform(t3d_vt);

            t3dstep.set(new Vector3d(0.0, 0.0, 0.2f));
            tg.getTransform(t3d);
            t3d.mul(t3dstep);
            tg.setTransform(t3d);

            System.out.println("trans.x: " + trans.x);
            System.out.println("trans.y: " + trans.y);
            System.out.println("trans.z: " + trans.z);
            System.out.println("pre_trans.x: " + pre_trans.x);
            System.out.println("pre_trans.y: " + pre_trans.y);
            System.out.println("pre_trans.z: " + pre_trans.z);
            System.out.println("trans.x - pre_trans.x: " + (trans.x - pre_trans.x));
            System.out.println("trans.y - pre_trans.y: " + (trans.y - pre_trans.y));
            System.out.println("trans.z - pre_trans.z: " + (trans.z - pre_trans.z));

            pre_trans.x = trans.x;
            pre_trans.y = trans.y;
            pre_trans.z = trans.z;

            wakeupOn(wakeFrame);
        }
    }

}