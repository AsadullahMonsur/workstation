package interaction_chap05;

import com.sun.j3d.utils.universe.ViewingPlatform;

import javax.media.j3d.*;
import javax.vecmath.Matrix4d;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Enumeration;

public class CameraMoving extends Behavior {

    private Canvas canvas;
    private ViewingPlatform vp = new ViewingPlatform();
    private TransformGroup vt;
    private Transform3D p_transform = new Transform3D();
    private Transform3D n_transform = new Transform3D();
    private Matrix4d vmatrix = new Matrix4d();
    private WakeupOnElapsedFrames wakeFrame = null;

    private TransformGroup player = new TransformGroup();
    private Transform3D past_transform = new Transform3D();
    private Transform3D new_transform = new Transform3D();
    private Matrix4d matrix = new Matrix4d();

    private int ceh = 0;
    private float turned_x = 0.0f;
    private float turned_z = 0.0f;
    private float pitch = 20.0f;
    private float yaw = 0.0f;
    private float roll = 0.0f;
    private float distance_camera = 5.0f;
    private float angle_around = 0.0f;



    private int theta = -90;

    Transform3D previous = new Transform3D();

    public CameraMoving(Canvas3D canvas, TransformGroup player, KeyMoving keyMoving, Frame_Rate rate) {
        this.canvas =canvas;
        vp.getViewPlatform().setActivationRadius(300f);
        vp.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        vp.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        p_transform = new Transform3D();
        vt = new TransformGroup();
        vt.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        vt.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        vt.setTransform(p_transform);
        vt = vp.getViewPlatformTransform();

        this.previous = keyMoving.old_data;
        Vector3d vb = new Vector3d();
        keyMoving.old_data.get(vb);
        System.out.println(vb.z);
        this.player = player;
        this.player.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        this.player.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        this.player.setTransform(past_transform);

        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 1000.0);
        this.setSchedulingBounds(bounds);

    }


    @Override
    public void initialize() {
        wakeFrame = new WakeupOnElapsedFrames(0);
        wakeupOn(wakeFrame);
    }

    @Override
    public void processStimulus(Enumeration enumeration) {

//       float vd = vertical();
//       float hd = horizontal();
//
//       n_transform.setTranslation( camera_position(hd,vd));
//       vt.getTransform(p_transform);
//       p_transform.mul(n_transform);
//       vt.setTransform(p_transform);
        player.getTransform(past_transform);
        Vector3d player_pos = new Vector3d();
        past_transform.get(player_pos);
//
//        vt.getTransform(p_transform);
//        Vector3d viewer_pos = new Vector3d();
//        p_transform.get(viewer_pos);
//
//        Vector3d fart = new Vector3d();
//        previous.get(fart);
//
//        double angle = Math.atan(viewer_pos.z/viewer_pos.x);
//        double r = Math.sqrt(viewer_pos.x*viewer_pos.x+viewer_pos.z*viewer_pos.z);
//
//        turned_x = (float) ((r+1)*Math.sin(angle));
//        turned_z =  (float) ((r+1)*Math.cos(angle));
////        turned_x = (float) (viewer_pos.x+(player_pos.x-fart.x));
////        turned_z = (float) (viewer_pos.z+(player_pos.z-fart.z));
//
//        System.out.println((player_pos.z+fart.z));
//        System.out.println(turned_z);
//
//       if(ceh>0) {
        float vd = vertical();
        float hd = horizontal();

        n_transform.setTranslation( camera_position(hd,vd));
        vt.getTransform(p_transform);
        p_transform.mul(n_transform);
        vt.setTransform(p_transform);
        
            Point3d eye = new Point3d(camera_position(hd,vd));
            Point3d centre = new Point3d(player_pos.x, 0.0, player_pos.z);
            Vector3d up = new Vector3d(0, 1, 0);
            p_transform.lookAt(eye, centre, up);//eye, center, up
            p_transform.invert();
            vt.setTransform(p_transform);
//
//            n_transform.setTranslation(new Vector3d(0.0, 0.0, -0.009));
//            p_transform.mul(n_transform);
//            vt.setTransform(p_transform);
//        }
        ceh++;
        wakeupOn(wakeFrame);
    }

    private Vector3d camera_position(float hd, float vd){
        player.getTransform(past_transform);
        Vector3d player_pos = new Vector3d();
        past_transform.get(player_pos);

        vt.getTransform(p_transform);
        Vector3d camera_pos = new Vector3d();
        p_transform.get(camera_pos);

        double zeta = 5+angle_around;
        float x_off = (float) (hd* Math.sin(Math.toRadians(zeta)));
        float z_off = (float) (hd* Math.cos(Math.toRadians(zeta)));
        camera_pos.x = player_pos.x - x_off;
        camera_pos.y = player_pos.y+vd;
        camera_pos.x = player_pos.z - z_off;

        Vector3d camera_update = new Vector3d(camera_pos.x,camera_pos.y,camera_pos.z);
        return camera_update;
    }
    public ViewingPlatform getVp() {
        return vp;
    }

    private void key_typing(KeyEvent e) {
        char key = e.getKeyChar();

        if(key == 'm'){
            distance_camera = distance_camera -0.5f;
        }
        if(key == 'n'){
            distance_camera = distance_camera +0.5f;
        }

        if(key == 'b'){
            pitch = pitch-1;
        }
        if(key == 'v'){
            pitch = pitch+1;
        }

        if(key == 'l'){
           angle_around = angle_around-5;
        }



        if(theta == 360 || theta == -360){
            theta = 0;
        }
        if (key == 'a') {
            theta = theta+ 5;
            double value = Math.toRadians(5);
            n_transform.rotY(1 * value);
            vt.getTransform(p_transform);
            p_transform.get(vmatrix);
            p_transform.setTranslation(new Vector3d(0.0, 0.0, 0.0));
            p_transform.mul(n_transform);
            p_transform.setTranslation(new Vector3d(vmatrix.m03, vmatrix.m13, vmatrix.m23));
            vt.setTransform(p_transform);
        }
        else if (key == 'd') {
            theta = theta-5;

            double value = Math.toRadians(5);
            n_transform.rotY(-1 * value);
            vt.getTransform(p_transform);
            p_transform.get(vmatrix);
            p_transform.setTranslation(new Vector3d(0.0, 0.0, 0.0));
            p_transform.mul(n_transform);
            p_transform.setTranslation(new Vector3d(vmatrix.m03, vmatrix.m13, vmatrix.m23));
            vt.setTransform(p_transform);

        }
    }

    private float horizontal(){
        return (float) (distance_camera*Math.cos(Math.toRadians(pitch)));
    }
    private float vertical(){
        return (float) (distance_camera*Math.sin(Math.toRadians(pitch)));
    }

}
