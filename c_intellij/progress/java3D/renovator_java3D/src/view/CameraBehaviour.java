package view;

import accessories.RefreshRate;
import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.universe.ViewingPlatform;
import participants.PlayerBehaviour;

import javax.media.j3d.*;
import javax.vecmath.Matrix4d;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.security.Key;
import java.util.Enumeration;

public class CameraBehaviour extends Behavior {
    private RefreshRate rate;
    private PlayerBehaviour player_behaviour;

    private Matrix4d view_matrix = new Matrix4d();
    private ViewingPlatform view_platform = new ViewingPlatform();
    private TransformGroup view = new TransformGroup();
    private Transform3D view_prev_transform = new Transform3D();
    private Transform3D view_next_transform = new Transform3D();
    private Vector3d present_camera_position = new Vector3d();

    private WakeupOnAWTEvent   wakeup_one = null;
    private WakeupOnAWTEvent   wakeup_two = null;
    private WakeupOnAWTEvent   wakeup_three = null;
    private WakeupOnAWTEvent   wakeup_four = null;
    private WakeupOnAWTEvent   wakeup_five = null;

    private WakeupCriterion[]  wakeup_array = new WakeupCriterion[5];
    private WakeupCondition    wakeup_condition = null;

    private int past_x = 0;
    private int past_y = 0;

    private double past_yaw_angle = 0.0;
    private double present_yaw_angle = 0.0;

    private double past_pitch_angle = 0.0;
    private double present_pitch_angle = 0.0;

    private int camera_to_player_distance = 5;

    public CameraBehaviour(RefreshRate rate, PlayerBehaviour player_behaviour) {
      wakeup_one = new WakeupOnAWTEvent(MouseEvent.MOUSE_MOVED);
      wakeup_two = new WakeupOnAWTEvent(MouseEvent.MOUSE_WHEEL);
      wakeup_three = new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED);
      wakeup_four = new WakeupOnAWTEvent(KeyEvent.KEY_TYPED);
      wakeup_five = new WakeupOnAWTEvent(KeyEvent.MOUSE_MOTION_EVENT_MASK);

      wakeup_array[0] = wakeup_one;
      wakeup_array[1] = wakeup_two;
      wakeup_array[2] = wakeup_three;
      wakeup_array[3] = wakeup_four;
      wakeup_array[4] = wakeup_five;
      wakeup_condition = new WakeupOr(wakeup_array);

      this.rate = rate;
      this.player_behaviour = player_behaviour;

      this.view_platform.getViewPlatform().setActivationRadius(300f);
      this.view.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
      this.view.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
      this.view.setTransform(view_prev_transform);
      this.view = view_platform.getViewPlatformTransform();


      BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 1000.0);
      this.setSchedulingBounds(bounds);

    }

    @Override
    public void initialize() {
        wakeupOn(wakeup_condition);
    }

    @Override
    public void processStimulus(Enumeration enumeration) {
        WakeupOnAWTEvent wake_event;
        WakeupCriterion criterion;
        AWTEvent []events;

        while (enumeration.hasMoreElements())  {
            criterion = (WakeupCriterion) enumeration.nextElement();

            if (criterion instanceof WakeupOnAWTEvent)   {
                wake_event = (WakeupOnAWTEvent) criterion;
                events = wake_event.getAWTEvent();

                event_manager(events);
            }
        }
        wakeupOn(wakeup_condition);
    }

    private void event_manager(AWTEvent[] events) {
        for( int n = 0; n <events.length; n++)  {
            if( events[n] instanceof MouseEvent)   {
               MouseEvent event = (MouseEvent) events[n];
               mouse_event_manager(event);
            }
            if(events[n] instanceof KeyEvent){
                KeyEvent event = (KeyEvent) events[n];
                key_event_manager(event);
            }
            camera_relocation();
        }
    }

    private void key_event_manager(KeyEvent event) {
        if (event.getID()== KeyEvent.KEY_PRESSED){
            camera_player_movement_handler(event);
        }
        if (event.getID()== KeyEvent.KEY_TYPED){
           if(event.getKeyChar()=='a'){
               boolean result = player_behaviour.rotationY(1,Math.toRadians(5.0));
           }
           else if(event.getKeyChar()=='d'){
               boolean result = player_behaviour.rotationY(-1,Math.toRadians(5.0));
           }
        }
    }

    private void camera_player_movement_handler(KeyEvent event) {
        if(event.getKeyCode()==KeyEvent.VK_UP){
            boolean result = player_behaviour.move_forward_backward(
                                               rate.get_time_difference()*30);

        }
        else if(event.getKeyCode()==KeyEvent.VK_DOWN){
            boolean result = player_behaviour.move_forward_backward(
                                              rate.get_time_difference()*-30);

        }

    }

    private void mouse_event_manager(MouseEvent event){
        if (event.getID()==MouseEvent.MOUSE_MOVED){
            boolean result1 = camera_yaw_handler(event);
            boolean result2 = camera_pitch_handler(event);
        }
        if(event.getID()==MouseEvent.MOUSE_DRAGGED){
            boolean result1 = camera_yaw_handler(event);
            boolean result2 = camera_pitch_handler(event);
        }
        if (event.getID()==MouseEvent.MOUSE_WHEEL){
            zoom_handler((MouseWheelEvent)event);
        }

    }


    private void  camera_relocation(){

        Vector3d v = player_behaviour.getPresent_position();
        double x_push = camera_to_player_distance * Math.sin(present_yaw_angle);
        double z_push = camera_to_player_distance * Math.cos(present_yaw_angle);
        double y_push = camera_to_player_distance * Math.tan(present_pitch_angle);

        Point3d centre = new Point3d(v.x/2, (v.y+1)+y_push,v.z/2);
        Point3d eye = new Point3d((v.x/2)+x_push,1.5,(v.z/2)+z_push);
        Vector3d up = new Vector3d(0, 1, 0);

        view_prev_transform.lookAt(eye, centre, up);//eye, center, up
        view_prev_transform.invert();
        view_platform.getViewPlatformTransform().setTransform(view_prev_transform);

    }

    private boolean camera_yaw_handler(MouseEvent event) {
        int present_x = event.getX();

        if(present_x- past_x>0){
            present_yaw_angle = present_yaw_angle - (Math.PI/180);   // in radian
        }
        else if(present_x-past_x<0){
            present_yaw_angle = present_yaw_angle + (Math.PI/180);
        }

        player_behaviour.rotationY(1,present_yaw_angle-past_yaw_angle);

        past_x = present_x;
        past_yaw_angle = present_yaw_angle;

        return  true;
    }

    private boolean camera_pitch_handler(MouseEvent event){
        int present_y =  event.getY();

        if(present_y-past_y>0){
            present_pitch_angle = present_pitch_angle - (Math.PI/180);
        }
        else if(present_y-past_y<0){
            present_pitch_angle = present_pitch_angle + (Math.PI/180);
        }

        if(present_pitch_angle<(-Math.PI/3)){
            present_pitch_angle = -Math.PI/3;
        }
        if (present_pitch_angle>((Math.PI/180)*89)){
            present_pitch_angle = (Math.PI/180)*89;
        }

        past_y = present_y;
        past_pitch_angle = present_pitch_angle;
        return true;
    }

    private void zoom_handler(MouseWheelEvent event) {
        if (event.getWheelRotation() < 0) {
            //System.out.println("mouse wheel Up");
            zooming(1);  // 1 for zoom in

        } else {
            // System.out.println("mouse wheel Down");
            zooming(-1);  // -1 for zoom out
        }
    }

    private void zooming(int zoom_direction) {
        if(zoom_direction==-1 && camera_to_player_distance<10){
            camera_to_player_distance = camera_to_player_distance+1;
        }
        else if (zoom_direction==1 && camera_to_player_distance>1){
            camera_to_player_distance = camera_to_player_distance-1;
        }

    }

    public ViewingPlatform getView_platform() {
        return view_platform;
    }
}
