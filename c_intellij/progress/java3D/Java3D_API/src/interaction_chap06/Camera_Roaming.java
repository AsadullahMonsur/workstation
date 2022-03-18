package interaction_chap06;

import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.universe.ViewingPlatform;

import javax.media.j3d.*;
import javax.vecmath.Matrix4d;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.util.Enumeration;

public class Camera_Roaming extends Behavior {

    private WakeupOnAWTEvent   wakeupOne = null;
    private WakeupOnAWTEvent   wakeupTwo = null;
    private WakeupCriterion[]  wakeupArray = new WakeupCriterion[2];
    private WakeupCondition    wakeupCondition = null;

    private Canvas3D canvas;
    private Matrix4d view_matrix = new Matrix4d();
    private ViewingPlatform view_platform = new ViewingPlatform();
    private TransformGroup view = new TransformGroup();
    private Transform3D view_prev_transform = new Transform3D();
    private Transform3D view_next_transform = new Transform3D();

    private Player_Roaming player_roaming;
    private TransformGroup player = new TransformGroup();
    private Transform3D player_prev_transform = new Transform3D();
    private Transform3D player_next_transform = new Transform3D();

    private Refresh_Rate rate;
    //private WakeupOnElapsedFrames wakeFrame = null;

    private double cheat=0.0;
    private Berculate orbit;
    private int beta = 90;
    private int theta = 90;
    private double radi = 0.0;
    private double x_angle =0.0;
    private double y_angle = 0.0;
    private double u_angle =0.0;
    private double v_angle = 0.0;

    private int x = 0;
    private int y = 0;
    private int z = 0;
    private int cam_player_distance = 5;
    RotationInterpolator interpolator;

    public Camera_Roaming(Canvas3D canvas, Player_Roaming player_roaming, Refresh_Rate rate) {

        wakeupOne = new WakeupOnAWTEvent(MouseEvent.MOUSE_MOVED);
        wakeupTwo = new WakeupOnAWTEvent(MouseEvent.MOUSE_WHEEL);
        wakeupArray[0] = wakeupOne;
        wakeupArray[1] = wakeupTwo;
        wakeupCondition = new WakeupOr(wakeupArray);

        this.canvas = canvas;
        this.player_roaming = player_roaming;
        this.rate = rate;
        this.view_platform.getViewPlatform().setActivationRadius(300f);

        this.view.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        this.view.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        this.view.setCapability(TransformGroup.ALLOW_LOCAL_TO_VWORLD_READ);
        this.view.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
        this.view.setTransform(view_prev_transform);
        this.view = this.view_platform.getViewPlatformTransform();


        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 1000.0);
        this.setSchedulingBounds(bounds);

        player = player_roaming.getPlayer();
        player.getTransform(player_prev_transform);
        Vector3d player_pos = new Vector3d();
        player_prev_transform.get(player_pos);
        Point3d point3d = new Point3d(player_pos);

        System.out.println("X:"+player_pos.x+" y: "+player_pos.y+" z:"+player_pos.z);
        orbit = new Berculate(this.canvas, OrbitBehavior.REVERSE_ROTATE);

        orbit.setViewingPlatform(view_platform);
        orbit.setSchedulingBounds(new BoundingSphere());
        orbit.setRotYFactor(0.9);
        orbit.setRotXFactor(0.9);
        center_fart();

       orbit.setRotationCenter(point3d);
        view_platform.setViewPlatformBehavior(orbit);
 //       camera_fix();
//        player_prev_transform.get(player_pos);
//        point3d = new Point3d(player_pos);
//        System.out.println("x: "+point3d.x+" y: "+point3d.y+" z:"+point3d.z);

//        player_next_transform.setTranslation(new Vector3d(0.0,-.5,-2.5));
//        Alpha alpha = new Alpha(1,60000);
//        interpolator = new RotationInterpolator(
//                alpha,view_platform.getViewPlatformTransform(),
//                player_next_transform,
//                0.0f,(float) Math.PI*2);
//
//        BoundingSphere boun = new BoundingSphere();
//        interpolator.setSchedulingBounds(boun);
//        view_platform.getViewPlatformTransform().addChild(interpolator);

        this.canvas.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                    calculate(e);

            }

            @Override
            public void keyPressed(KeyEvent e) {


            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }


    private void calculate(KeyEvent e) {

        // System.out.println(theta);

        char key = e.getKeyChar();

        if (beta == 360 || beta == -360) {
            beta = 0;
        }
        if (key == 'a') {
            beta = beta - 10;
            theta = theta - 10;
            player_roaming.rotation(1, Math.toRadians(10.0));
        } else if (key == 'd') {

            beta = beta + 10;
            theta = theta + 10;
            player_roaming.rotation(1, Math.toRadians(10.0));

        } else if (key == 'w') {
            boolean result = player_roaming.move(rate.getTime_difference() * 20);
            //  center_fart();
            //     camera_position_manager();
            farrt_yeaaj();
        }

        //   orbit.setRotationCenter(new Point3d(player_roaming.getPosition()));
        else if (key == 's') {
            boolean result = player_roaming.move(rate.getTime_difference() * -20);

            if (result) {
                //   camera_position_manager();
                // center_fart();
                farrt_yeaaj();
            }

            //   orbit.setRotationCenter(new Point3d(player_roaming.getPosition()));
        }
    }

    @Override
    public void initialize() {
        wakeupOn(wakeupCondition);
    }

    @Override
    public void processStimulus(Enumeration enumeration) {

        WakeupOnAWTEvent ev;
        WakeupCriterion genericEvt;
        AWTEvent[] events;
        while (enumeration.hasMoreElements())  {
            genericEvt = (WakeupCriterion) enumeration.nextElement();
            if (genericEvt instanceof WakeupOnAWTEvent)   {
                ev = (WakeupOnAWTEvent) genericEvt;
                events = ev.getAWTEvent();
              //  processEvent(events);
            }
        }
         wakeupOn(wakeupCondition);
    }


    private void processEvent(AWTEvent[] events) {
        for( int n = 0; n <events.length; n++)  {
            if( events[n] instanceof MouseEvent)   {
                MouseEvent event = (MouseEvent) events[n];
                if (event.getID()==MouseEvent.MOUSE_MOVED){
                    camera_fix_2(event);
                }
                if (event.getID()==MouseEvent.MOUSE_WHEEL){
                    zooming((MouseWheelEvent)event);
                }
            }
        }
    }

    private void zooming(MouseWheelEvent event) {
            if (event.getWheelRotation() < 0) {
                System.out.println("mouse wheel Up");
                zooming_in();

            } else {
                System.out.println("mouse wheel Down");
                zooming_out();
            }

    }

    private void zooming_in() {
        if(cam_player_distance>1){
            cam_player_distance = cam_player_distance-1;
        }
        farrt_yeaaj();
    }

    private void zooming_out() {
        if(cam_player_distance<10){
            cam_player_distance = cam_player_distance+1;
        }
        farrt_yeaaj();
    }

    public ViewingPlatform getView_platform() {
        return view_platform;
    }

    private void camera_fix(){
       // System.out.println(Math.sin(Math.PI/6)); radian input
       // System.out.println(Math.sin(Math.PI/3));

        view = view_platform.getViewPlatformTransform();
        view.getTransform(view_prev_transform);
        Vector3d camera_pos = new Vector3d();
        view_prev_transform.get(camera_pos);

        Vector3d v = player_roaming.getPosition();
        double but = Math.sin(0);
        double hut = Math.cos(0);
        Point3d centre =  new Point3d();

    //    orbit.getRotationCenter(centre);

        Point3d eye = new Point3d(camera_pos.x,2,camera_pos.z);

        Vector3d up = new Vector3d(0, 1, 0);
        view_prev_transform.lookAt(eye, centre, up);//eye, center, up

        view_prev_transform.invert();
        view.setTransform(view_prev_transform);
    }

    private void  center_fart(){

        view_platform.getViewPlatformTransform().getTransform(view_prev_transform);
        Vector3d vector = new Vector3d();
        view_prev_transform.get(vector);
        Vector3d v = new Vector3d();
        v = player_roaming.getPosition();

        double but = Math.sin(0);
        double hut = Math.cos(0);

        Point3d point = new Point3d((v.x/2),v.y,(v.z/2));
       // orbit.setRotationCenter(point);

    }

    private void camera_move(Vector3d vector){
       view_platform.getViewPlatformTransform().getTransform(view_prev_transform);
       view_next_transform.setTranslation(vector);
//        view_prev_transform.get(view_matrix);
//        view_prev_transform.setTranslation(new Vector3d(0.0, 0.0, 0.0));
//        view_prev_transform.mul(view_next_transform);
//        view_prev_transform.setTranslation(new Vector3d(view_matrix.m03, view_matrix.m13, view_matrix.m23));
        view_platform.getViewPlatformTransform().setTransform(view_next_transform);

        camera_fix();
    }
    private void camera_position_manager(){

        Vector3d player_past_pos = player_roaming.getOld_pos();
        Vector3d player_present_pos = player_roaming.getPosition();

        double dx = player_present_pos.x-player_past_pos.x;
        double dz = player_present_pos.z-player_past_pos.z;

       // System.out.println("x: "+2*dx+" y:"+2*dz);
        view_platform.getViewPlatformTransform().getTransform(view_prev_transform);
        Vector3d camera_present_pos = new Vector3d();
        view_prev_transform.get(camera_present_pos);
        Vector3d camera_next_pos = new Vector3d(camera_present_pos.x+dx/2,0.0,camera_present_pos.z+dz/2);
        camera_move(camera_next_pos);
    }

    private  void camera_fix_2(MouseEvent evt){
        int p = evt.getX();
        if(p-x>0){
            x_angle = x_angle-Math.PI/180;
            player_roaming.rotation(1, x_angle-y_angle);
        }
        else if(p-x<0){
            x_angle = x_angle+Math.PI/180;
            player_roaming.rotation(1, x_angle-y_angle);
        }

        int q =  evt.getY();

        if(q-y>0){
            u_angle = u_angle-Math.PI/180;

        }
        else if(q-y<0){
            u_angle = u_angle+Math.PI/180;
        }

        if(u_angle<-(Math.PI/3)){
            u_angle = -Math.PI/3;
        }
        if (u_angle>(Math.PI/2)){
            u_angle = Math.PI/2;
        }

        farrt_yeaaj();
        x = p;
        y_angle = x_angle;

        y = q;
      //  butla_putla(evt);
    }

    private void butla_putla(MouseEvent evt) {
        int q =  evt.getY();

        if(q-y>0){
            u_angle = u_angle-Math.PI/180;

        }
        else if(q-y<0){
            u_angle = u_angle+Math.PI/180;
        }

        if(u_angle<-(Math.PI/3)){
            u_angle = -Math.PI/3;
        }
        if (u_angle>(Math.PI/2)){
            u_angle = Math.PI/2;
        }

        view_next_transform.rotX(u_angle);
        view_platform.getViewPlatformTransform().getTransform(view_prev_transform);
        view_prev_transform.get(view_matrix);
        view_prev_transform.setTranslation(new Vector3d(0.0, 0.0, 0.0));
        view_prev_transform.mul(view_next_transform);
        view_prev_transform.setTranslation(new Vector3d(view_matrix.m03, view_matrix.m13, view_matrix.m23));
        view_platform.getViewPlatformTransform().setTransform(view_prev_transform);

        y = q;
        //v_angle = u_angle;
    }

    private void donkey(MouseEvent evt) {
//
//        Vector3d v = player_roaming.getPosition();
//        double but = cam_player_distance*Math.sin(u_angle);
//        double hut = cam_player_distance*Math.cos(u_angle);
//
//        double put = cam_player_distance*Math.sin(x_angle);
//        double cut = cam_player_distance*Math.cos(x_angle);
//
//        Point3d centre = new Point3d(v.x/2,v.y+but,v.z/2);
//        //this.getRotationCenter(centre);
//        Point3d eye = new Point3d(v.x/2+put,1.5,v.z/2+cut);
//        Vector3d up = new Vector3d(0, 1, 0);
//        view_prev_transform.lookAt(eye, centre, up);//eye, center, up
//
//        view_prev_transform.invert();
//        view_platform.getViewPlatformTransform().setTransform(view_prev_transform);
//        y = q;
//        v_angle = u_angle;
    }


    public static double angleOf(double x1,double y1, double x2, double y2) {
        double result = 0.0;
        double deltaY = y2-y1;
        double deltaX = x2-x1;

        result = Math.atan2(deltaY,deltaX);

        return ((Math.PI/90)-result);
    }

    private void louse_fart() {
        view =  view_platform.getViewPlatformTransform();
        view.getTransform(view_prev_transform);
        Vector3d vector = new Vector3d();
        view_prev_transform.get(vector);
        Vector3d v = player_roaming.getPosition();

        double  angelo = angleOf(v.x/2,v.z/2,vector.x,vector.z);
        System.out.println(angelo);

        player_roaming.rotation(1, Math.toRadians(angelo));

        //fart_smart_angle = angelo;
        //     camera_fix();
    }
    private void farrt_yeaaj(){
        Vector3d v = player_roaming.getPosition();
        double but = cam_player_distance*Math.sin(x_angle);
        double hut = cam_player_distance*Math.cos(x_angle);
        double put = cam_player_distance*Math.sin(u_angle);

        Point3d centre = new Point3d(v.x/2,v.y+1+put,v.z/2);
        //this.getRotationCenter(centre);
        Point3d eye = new Point3d(v.x/2+but,1.5,v.z/2+hut);
        Vector3d up = new Vector3d(0, 1, 0);
        view_prev_transform.lookAt(eye, centre, up);//eye, center, up

        view_prev_transform.invert();
        view_platform.getViewPlatformTransform().setTransform(view_prev_transform);
    }
}
