package interaction_chap04;

import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.universe.ViewingPlatform;
import lesson_01.Loader;

import javax.media.j3d.*;
import javax.vecmath.Matrix4d;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Enumeration;

public class Player_Around extends Behavior{
    private Canvas canvas;
    private Matrix4d matrix = new Matrix4d();
    private TransformGroup model_group = null;
    private Transform3D past_transform = null;
    private Transform3D new_transform = new Transform3D();
    private WakeupOnElapsedFrames wakeFrame = null;

    private TransformGroup vt;
    private Transform3D p_transform = null;
    private Transform3D n_transform = new Transform3D();
    private Matrix4d vmatrix = new Matrix4d();
    private float turned_x = 0.0f;
    private float turned_z = 0.0f;
    private float turned_a = 0.0f;
    private float turned_b = 0.0f;

    private int theta = 0;

    public Player_Around(ViewingPlatform vp,Canvas3D canvas, Transform3D transform, String path3) {
        this.canvas = canvas;
        this.past_transform = transform;

        p_transform = new Transform3D();
        vt = new TransformGroup();
        vt.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        vt.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        vt.setTransform(p_transform);
        this.vt = vp.getViewPlatformTransform();

        model_group = new TransformGroup();
        model_group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        model_group.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        Loader loader = new Loader(path3);
        model_group.addChild(loader.obj_file_to_graph());

        //bound is must
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 1000.0);
        this.setSchedulingBounds(bounds);

        double value = Math.toRadians(180);
        getNew_transform().rotY(value);
        getModel_group().getTransform(getPast_transform());
        getPast_transform().get(matrix);
        getPast_transform().setTranslation(new Vector3d(0.0, 0.0, 0.0));
        getPast_transform().mul(getNew_transform());
        getPast_transform().setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
        getModel_group().setTransform(getPast_transform());

        this.canvas.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                key_typing(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public void initialize() {
        wakeFrame = new WakeupOnElapsedFrames(0);
        wakeupOn(wakeFrame);
    }

    public void processStimulus(Enumeration criteria) {
        double value = Math.toRadians(5);
        Vector3d vector = new Vector3d();
        past_transform.get(vector);
        double r = Math.sqrt(vector.x*vector.x+vector.z*vector.z);
        turned_x = ((float) (r+1)* (float) Math.sin(value))/1000.0f;
        turned_z = ((float) (r+1)*(float) Math.cos(value))/1000.0f;

        new_transform.set(new Vector3d(turned_x, 0.0, turned_z));
        model_group.getTransform(past_transform);
        past_transform.mul(new_transform);
        model_group.setTransform(past_transform);

        past_transform.get(vector);

        Point3d centre = new Point3d(vector.x,vector.y,vector.z);
        Point3d eye = new Point3d(turned_x,2,turned_z);
        Vector3d up = new Vector3d(0,1,0);
        p_transform.lookAt(eye,centre, up);//eye, center, up
        p_transform.invert();
        vt.setTransform(p_transform);


        wakeupOn(wakeFrame);
    }

    public TransformGroup getModel_group() {
        return model_group;
    }

    public Transform3D getPast_transform() {
        return past_transform;
    }

    public Transform3D getNew_transform() {
        return new_transform;
    }

    private void key_typing(KeyEvent e) {
        char key = e.getKeyChar();
        if(theta == 360 || theta == -360){
            theta = 0;
        }
        if (key == 'a') {
            theta = theta+ 5;
            double value = Math.toRadians(5);
            getNew_transform().rotY(value);
            getModel_group().getTransform(getPast_transform());
            getPast_transform().get(matrix);
            getPast_transform().setTranslation(new Vector3d(0.0, 0.0, 0.0));
            getPast_transform().mul(getNew_transform());
            getPast_transform().setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
            getModel_group().setTransform(getPast_transform());

            double val = Math.toRadians(5);
            n_transform.rotY(val);
            vt.getTransform(p_transform);
            p_transform.get(vmatrix);
            p_transform.setTranslation(new Vector3d(0.0, 0.0, 0.0));
            p_transform.mul(n_transform);
            p_transform.setTranslation(new Vector3d(vmatrix.m03, vmatrix.m13, vmatrix.m23));
            vt.setTransform(p_transform);

//            Vector3d vector = new Vector3d();
//            past_transform.get(vector);
//            Vector3d vectors = new Vector3d();
//            p_transform.get(vectors);
//            double r1 = Math.sqrt((vectors.z-vector.z)*(vectors.z-vector.z)+(vectors.x-vector.x)*(vectors.x-vector.x));
//            System.out.println("radiuos :  "+r1);
//            turned_a = ((float) (r1) * (float) Math.sin(theta))+(float) vector.x;
//            turned_b = ((float) (r1) * (float) Math.cos(theta)) - (float) vector.z;
//            n_transform.set(new Vector3d(turned_a, 0.0, turned_b));
//            System.out.println("a:  "+turned_a+" b:"+turned_b);
//
//            Point3d centre = new Point3d(vector.x,vector.y,vector.z);
//            Point3d eye = new Point3d(turned_a,2,turned_b);
//            Vector3d up = new Vector3d(0,1,0);
//            p_transform.lookAt(eye,centre, up);//eye, center, up
//            p_transform.invert();
//            vt.setTransform(p_transform);

 //           System.out.println(vectors.x+" "+vectors.y+" "+vectors.z+" ");
        }
        else if (key == 'd') {
            theta = theta-5;
            double value = Math.toRadians(5);
            getNew_transform().rotY(-1 * value);
            getModel_group().getTransform(getPast_transform());
            getPast_transform().get(matrix);
            getPast_transform().setTranslation(new Vector3d(0.0, 0.0, 0.0));
            getPast_transform().mul(getNew_transform());
            getPast_transform().setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
            getModel_group().setTransform(getPast_transform());

            double val = Math.toRadians(5);
            n_transform.rotY(-1 * val);
            vt.getTransform(p_transform);
            p_transform.get(vmatrix);
            p_transform.setTranslation(new Vector3d(0.0, 0.0, 0.0));
            p_transform.mul(n_transform);
            p_transform.setTranslation(new Vector3d(vmatrix.m03, vmatrix.m13, vmatrix.m23));
            vt.setTransform(p_transform);

//            Vector3d vector = new Vector3d();
//            past_transform.get(vector);
//            Vector3d vectors = new Vector3d();
//            p_transform.get(vectors);
//            double r1 = Math.sqrt((vectors.z-vector.z)*(vectors.z-vector.z)+(vectors.x-vector.x)*(vectors.x-vector.x));
//           System.out.println("radiuos :  "+r1);
//            turned_a = ((float) (r1) * (float) Math.sin(theta))+(float) vector.x;
//            turned_b = ((float) (r1) * (float) Math.cos(theta)) - (float) vector.z;
//            System.out.println("a:  "+turned_a+" b:"+turned_b);
//
//            Point3d centre = new Point3d(vector.x,vector.y,vector.z);
//            Point3d eye = new Point3d(turned_a,2,turned_b);
//            Vector3d up = new Vector3d(0,1,0);
//            p_transform.lookAt(eye,centre, up);//eye, center, up
//            p_transform.invert();
//            vt.setTransform(p_transform);
//            System.out.println(vectors.x+" "+vectors.y+" "+vectors.z+" ");

        }
    }
}
