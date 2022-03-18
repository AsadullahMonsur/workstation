package interaction_chap05;

import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
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

public class KeyMoving extends Behavior{
    private Canvas canvas;
    private Matrix4d matrix = new Matrix4d();
    private TransformGroup model_group = null;
    private Transform3D past_transform = null;
    private Transform3D new_transform = new Transform3D();
    private WakeupOnElapsedFrames wakeFrame = null;

    private int theta = -90;
    Transform3D old_data = new Transform3D();

    private double angle = 0.0f;
    private int rotate_direction = 1;
    private static final float run_speed = 10f;
    private static final float turn_speed = 160.0f;

    private float current_run_speed = 0;
    private float current_turn_speed = 0;
    private Frame_Rate rate;


    public KeyMoving(Canvas3D canvas, Transform3D transform, Frame_Rate rate, String path3) {
        this.rate = rate;
        this.canvas = canvas;
        this.past_transform = transform;

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

        model_group.setTransform(old_data);
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
        move();
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

    private void move(){
        float distance = current_run_speed * rate.getDelta();
        float dx = (float) (distance*Math.sin(angle));
        float dz = (float) (distance*Math.cos(angle));

    //    System.out.println(dz);
        model_group.getTransform(past_transform);
        Vector3d vector = new Vector3d();
        past_transform.get(vector);

//        if(dz>0){
//            dz = dz*-1;
//        }
        new_transform.set(new Vector3d(dx, 0.0, dz));// turned x can also work at 0
        model_group.getTransform(past_transform);
        past_transform.mul(new_transform);
        model_group.setTransform(past_transform);

        old_data.set(vector);
    }

    private void key_typing(KeyEvent e) {
        char key = e.getKeyChar();
        if(theta == 360 || theta == -360){
            theta = 0;
        }
        if (key == 'a') {
            current_turn_speed = turn_speed;
            rotate_direction = -1;
            rotation(rotate_direction,current_turn_speed*rate.getDelta());
        }
        else if (key == 'd') {
            current_turn_speed = turn_speed;
            rotate_direction = 1;
            rotation(rotate_direction,current_turn_speed*rate.getDelta());
        }
        if (key == 'w') {
            current_run_speed = -1*run_speed;
        }
        else if(key=='s'){
            current_run_speed = run_speed;
        }
        else {
            current_run_speed = 0;
        }


    }


    private void rotation(int flag,float value){
        angle = flag * Math.toRadians(value);
        System.out.println(angle);
        getNew_transform().rotY(angle);
        getModel_group().getTransform(getPast_transform());
        getPast_transform().get(matrix);
        getPast_transform().setTranslation(new Vector3d(0.0, 0.0, 0.0));
        getPast_transform().mul(new_transform);
        getPast_transform().setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
        getModel_group().setTransform(past_transform);
    }
}
