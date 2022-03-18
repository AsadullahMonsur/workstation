package interaction_chap03;

import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import lesson_01.Loader;

import javax.media.j3d.*;
import javax.vecmath.Matrix4d;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Enumeration;

public class Player_Movement extends Behavior {
    private Canvas canvas;
    private Matrix4d matrix = new Matrix4d();
    // Player_Behavior/behavior must attach to corresponding Group
    private TransformGroup model_group = null;
    private Transform3D past_transform = null;
    private Transform3D new_transform = new Transform3D();
    private WakeupOnElapsedFrames wakeFrame = null;

    private TransformGroup space = null;
    private Transform3D space_past_transform = null;
    private Transform3D space_new_transform = new Transform3D();

    private float turned_x = 0.0f;
    private float turned_z = 0.0f;
    private float theta = -90;

    public Player_Movement(Canvas3D canvas, TransformGroup space, Transform3D space_transform, Transform3D t3d, String path3) {
        this.canvas = canvas;
        this.space = space;
        this.space.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        this.space_past_transform = space_transform;
        this.past_transform = t3d;

        model_group = new TransformGroup();
        model_group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        Loader loader = new Loader(path3);
        model_group.addChild(loader.obj_file_to_graph());

        //bound is must
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 1000.0);
        this.setSchedulingBounds(bounds);

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
        if(theta>=0 && theta<=90 || theta<-270 && theta>=-360) {
            new_transform.set(new Vector3d(turned_x, 0.0, turned_z));
        }
        else if(theta>90 && theta<=180 || theta<-180 && theta>=-270) {
            new_transform.set(new Vector3d(turned_x, 0.0, -turned_z));
        }
        else if(theta>180 && theta<=270 || theta<-90 && theta>=-180) {
            new_transform.set(new Vector3d(-turned_x, 0.0, -turned_z));
        }
        else if(theta>270 && theta<=360 || theta<0 && theta>=-90) {
            new_transform.set(new Vector3d(-turned_x, 0.0, turned_z));
        }

        model_group.getTransform(past_transform);
        past_transform.mul(new_transform);
        model_group.setTransform(past_transform);
        move_forward();
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

    public TransformGroup getSpace() {
        return space;
    }

    public Transform3D getSpace_past_transform() {
        return space_past_transform;
    }

    public Transform3D getSpace_new_transform() {
        return space_new_transform;
    }

    private void move_forward(){
        if(theta>=0 && theta<=90 || theta<-270 && theta>=-360){
            space_new_transform.set(new Vector3d(-10*turned_x,0.0,-10*turned_z));
        }
        else if(theta>90 && theta<=180 || theta<-180 && theta>=-270){
            space_new_transform.set(new Vector3d(-10*turned_x,0.0,turned_z));

        }
        else if(theta>180 && theta<=270 || theta<-90 && theta>=-180){
            space_new_transform.set(new Vector3d(10*turned_x,0.0,10*turned_z));

        }
        else if(theta>270 && theta<=360 || theta<0 && theta>=-90){
            space_new_transform.set(new Vector3d(10*turned_x,0.0,-10*turned_z));
        }
        space.getTransform(space_past_transform);
        space_past_transform.mul(space_new_transform);
        space.setTransform(space_past_transform);
    }

    private void key_typing(KeyEvent e) {
        char key = e.getKeyChar();
        if(theta==360 || theta==-360){
            theta = 0;
        }

        if (key == 'a') {
            theta = theta-10;
            double value = Math.toRadians(10);
            turned_x = (float) Math.sin(theta)/10000.0f;
            turned_z = (float) Math.cos(theta)/10000.0f;
            getNew_transform().rotY(value);
            getModel_group().getTransform(getPast_transform());
            getPast_transform().get(matrix);
            getPast_transform().setTranslation(new Vector3d(0.0, 0.0, 0.0));
            getPast_transform().mul(getNew_transform());
            getPast_transform().setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
            getModel_group().setTransform(getPast_transform());

            rotate_environment(-1,value);

        }

        else if (key == 'd') {
            theta = theta+10;
            double value = Math.toRadians(10);
            turned_x = (float) Math.sin(theta)/10000.0f;
            turned_z = (float) Math.cos(theta)/10000.0f;
            getNew_transform().rotY(-1*value);
            getModel_group().getTransform(getPast_transform());
            getPast_transform().get(matrix);
            getPast_transform().setTranslation(new Vector3d(0.0, 0.0, 0.0));
            getPast_transform().mul(getNew_transform());
            getPast_transform().setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
            getModel_group().setTransform(getPast_transform());

            rotate_environment(1,value);

        }


        int keyCode = e.getKeyCode();
        switch( keyCode ) {
            case KeyEvent.VK_UP:

                break;
            case KeyEvent.VK_DOWN:

                break;
            case KeyEvent.VK_LEFT:

                break;
            case KeyEvent.VK_RIGHT :

                break;
        }
    }

    private void rotate_environment(int flag, double value){
        Matrix4d matrix = new Matrix4d();
        getSpace_new_transform().rotY(flag*value*0.5);
        getSpace().getTransform(getSpace_past_transform());
        getSpace_past_transform().get(matrix);
        getSpace_past_transform().setTranslation(new Vector3d(0.0, 0.0, 0.0));
        getSpace_past_transform().mul(getSpace_new_transform());
        getSpace_past_transform().setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
        getSpace().setTransform(getSpace_past_transform());
    }
}
