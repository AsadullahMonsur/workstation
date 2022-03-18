package com.arcore_3d_basics.options;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.arcore_3d_basics.R;
import com.arcore_3d_basics.accessories.DataNotification;
import com.arcore_3d_basics.loader.Loader;
import com.google.ar.core.exceptions.CameraNotAvailableException;
import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.HitTestResult;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.Scene;
import com.google.ar.sceneform.SceneView;
import com.google.ar.sceneform.math.Quaternion;
import com.google.ar.sceneform.math.QuaternionEvaluator;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.ux.TransformableNode;
import com.google.ar.sceneform.ux.TransformationSystem;

import java.util.ArrayList;
import java.util.Objects;

public class OperationMenu extends AppCompatActivity {
    private static final double MIN_OPENGL_VERSION = 3.0;

    private SceneView scene_view;
    private Loader loader;

    private ArrayList<String> model_paths;

    private Node parent_node;
    private Node player;
    private Node []bed;
    private int bed_flag = -1;

    private MutableLiveData<DataNotification> mdn; // mdn-> mutable data notification
                                                   // dn-> data notification

    private float yaw_angle[]  = new float[]{0.5f};
    private float pitch_angle[]  = new float[]{0.5f};
    private boolean yaw_rotation_flag = true;
    private boolean pitch_rotation_flag = true;

    private float []touch_x = new float[]{0,0};
    private float []touch_y = new float[]{0,0};
    private final float MIN_DISTANCE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_activity);
        show_notification("Operation Menu Loaded",0);
        scene_view = findViewById(R.id.viewer3D);

        model_paths = new ArrayList<String>();
        loader = new Loader(getApplicationContext());

        parent_node = new Node();
        player = new Node();
        bed = new Node[]{ new Node(),
                           new Node(),
                           new Node(),
                           new Node(),
                           new Node(),
                           new Node()};

        mdn = new MutableLiveData<DataNotification>();
        data_notification_operator();

      if (compatibility(this)) {
          load_all_models_to_screen(loader);
      }
    }

    private void load_all_models_to_screen(Loader loader) {
        model_paths.add("car_bed_black_white_wheel.sfb");
        model_paths.add("car_bed_glass_white_wheel.sfb");
        model_paths.add("car_bed_glass_white.sfb");
        model_paths.add("car_bed_glass_green.sfb");
        model_paths.add("car_bed_glass_blue.sfb");
        model_paths.add("car_bed_glass_blue_sharp.sfb");
        model_paths.add("Car.sfb");

        Thread updateUi = new Thread() {
            public void run() {
                try {
                    handler.sendEmptyMessage(0);
                }
                catch (Exception e) {
                    show_notification("exception: loading all model to screen",1);
                }
            }
        };
        updateUi.start();
    }

    private void configure_view_controller() {
        scene_view.getScene().setOnTouchListener(new Scene.OnTouchListener() {
            @Override
            public boolean onSceneTouch(HitTestResult hitTestResult, MotionEvent motionEvent) {
                touch_x[1] = motionEvent.getX();
                touch_y[1] = motionEvent.getY();

                float xchange = touch_x[1] - touch_x[0];
                float ychange = touch_y[1] - touch_y[0];

                if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    yaw_rotation_control(xchange);
                    pitch_rotation_control(ychange);
                }

                touch_x[0] = touch_x[1];
                touch_y[0] = touch_y[1];
                return true;
            }
        });

        player.setOnTapListener(new Node.OnTapListener() {
            @Override
            public void onTap(HitTestResult hitTestResult, MotionEvent motionEvent) {
                if(bed_flag==0){
                    parent_node.removeChild(bed[0]);
                    parent_node.addChild(bed[1]);
                    bed_flag++;
                }
                else if(bed_flag==1){
                    parent_node.removeChild(bed[1]);
                    parent_node.addChild(bed[2]);
                    bed_flag++;
                }
                else if(bed_flag==2){
                    parent_node.removeChild(bed[2]);
                    parent_node.addChild(bed[3]);
                    bed_flag++;
                }
                else if(bed_flag==3){
                    parent_node.removeChild(bed[3]);
                    parent_node.addChild(bed[4]);
                    bed_flag++;
                }
                else if(bed_flag==4){
                    parent_node.removeChild(bed[4]);
                    parent_node.addChild(bed[5]);
                    bed_flag++;
                }
                else if(bed_flag==5){
                    parent_node.removeChild(bed[5]);
                    parent_node.addChild(bed[0]);
                    bed_flag = 0;
                }
            }
        });
    }

    private void pitch_rotation_control(float ychange) {
        // yaw_rotation_flag = true (previously left to right),
        // yaw_rotation_flag = false (previously right to left)

        if (ychange > 0) {
            // left to right touch

            if(!pitch_rotation_flag){
                pitch_rotation_flag = true;
                pitch_angle[0] = 360-( pitch_angle[0]+ ychange*0.5f);
            }
            else {
                pitch_angle[0] = pitch_angle[0] + ychange*0.5f;
            }

            if(pitch_angle[0]>360f){
               pitch_angle[0] = pitch_angle[0]%360;
            }

            operate_positional_rotation(pitch_angle[0],
                        player,
                        scene_view.getScene().getCamera());
        }

        else if (ychange < 0) {
            // right to left touch

            if(pitch_rotation_flag){
                pitch_rotation_flag = false;
                pitch_angle[0] = -360.0f-( pitch_angle[0]- ychange*0.5f);
            }
            else {
                pitch_angle[0] = pitch_angle[0] - ychange*0.5f ;
            }

            if(pitch_angle[0]<-360f){
                pitch_angle[0] = pitch_angle[0]%360;
            }

            operate_positional_rotation(-pitch_angle[0],
                                         player,
                                         scene_view.getScene().getCamera());
        }
    }

    private void yaw_rotation_control(float xchange) {
       // yaw_rotation_flag = true (previously left to right),
       // yaw_rotation_flag = false (previously right to left)

        if (xchange > 0) {
            // left to right touch

            if(!yaw_rotation_flag){
                yaw_rotation_flag = true;
                yaw_angle[0] = 360-( yaw_angle[0]+ xchange*0.5f);
            }
            else {
                yaw_angle[0] = yaw_angle[0] + xchange*0.5f;
            }

            if(yaw_angle[0]>360f){
                yaw_angle[0] = yaw_angle[0]%360;
            }

            operate_rotation(new Vector3(0, 1, 0),
                    yaw_angle[0],
                    parent_node);
        }

        else if (xchange < 0) {
            // right to left touch

            if(yaw_rotation_flag){
                yaw_rotation_flag = false;
                yaw_angle[0] = -360.0f-( yaw_angle[0]- xchange*0.5f);
            }
            else {
                yaw_angle[0] = yaw_angle[0] - xchange*0.5f ;
            }

            if(yaw_angle[0]<-360f){
                yaw_angle[0] = yaw_angle[0]%360;
            }
            operate_rotation(new Vector3(0, 1, 0),
                    -yaw_angle[0],
                    parent_node);
        }
    }

    private void operate_positional_rotation(float angle,Node centre_node,
                                                   Node moving_node){

//        Vector3 vc = centre_node.getLocalPosition();
//        Vector3 vm = moving_node.getLocalPosition();
//        float distance = Math.abs(vm.x - vc.x);   // distance from the rotation axis

        // note: ARCore Scene-form does not allow to access the camera position vector
        //  so we keep track our own [ centre to object(camera) distance]
        //  here distance -> between x the axis]

        float distance = 1;
        float z = (float) (distance * Math.cos((double) Math.toRadians(angle)));
        float y = (float) (distance * Math.sin((double) Math.toRadians(angle)));

        moving_node.setLocalPosition(new Vector3(0,y,z));

        operate_rotation(new Vector3(1,0,0), -angle, moving_node);
    }

    private void operate_rotation(Vector3 axis, float angle,Node node){
        // single axis example-> x axis (1,0,0)---- y axis (0,1,0) ---- z axis (0,0,1)
        // angle in degree
        // time [0 to 1]

        Quaternion rotation = Quaternion.axisAngle(axis,angle);
        Quaternion result   = Quaternion.slerp(node.getLocalRotation(), rotation,1);
        node.setLocalRotation(result);
    }


    final Handler handler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message msg) {
            loader.load_model(model_paths.get(0),
                    bed[0],
                    new Vector3(0,-0.05f,0),
                    new Vector3(0.5f,0.2f,0.5f),
                    mdn,0);

            loader.load_model(model_paths.get(1),
                    bed[1],
                    new Vector3(0,-0.05f,0),
                    new Vector3(0.5f,0.2f,0.5f),
                    mdn,1);

            loader.load_model(model_paths.get(2),
                    bed[2],
                    new Vector3(0,-0.05f,0),
                    new Vector3(0.5f,0.2f,0.5f),
                    mdn,2);

            loader.load_model(model_paths.get(3),
                    bed[3],
                    new Vector3(0,-0.05f,0),
                    new Vector3(0.5f,0.2f,0.5f),
                    mdn,3);

            loader.load_model(model_paths.get(4),
                    bed[4],
                    new Vector3(0,-0.05f,0),
                    new Vector3(0.5f,0.2f,0.5f),
                    mdn,4);

            loader.load_model(model_paths.get(5),
                    bed[5],
                    new Vector3(0,-0.05f,0),
                    new Vector3(0.5f,0.2f,0.5f),
                    mdn,5);

            loader.load_model(model_paths.get(6),
                    player,
                    new Vector3(0,0,0f),
                    new Vector3(0.2f,0.2f,0.2f),
                    mdn,6);
        }
    };

    final Handler handler_pitch = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message msg) {

        }
    };

    private void data_notification_operator() {
        mdn.observe(this,new Observer<DataNotification>() {
            @Override
            public void onChanged(DataNotification changedValue) {
                show_notification("Object Loaded Successfully",0);
                if(changedValue.getNumber()==0){
                 parent_node.setLocalPosition(new Vector3(0,0,0));

                 parent_node.addChild(bed[0]);
                }

                if(changedValue.getNumber()==1){
                    parent_node.setLocalPosition(new Vector3(0,0,0));

                    parent_node.removeChild(bed[0]);
                    parent_node.addChild(bed[1]);
                }

                if(changedValue.getNumber()==2){
                    parent_node.setLocalPosition(new Vector3(0,0,0));

                    parent_node.removeChild(bed[1]);
                    parent_node.addChild(bed[2]);
                }

                if(changedValue.getNumber()==3){
                    parent_node.setLocalPosition(new Vector3(0,0,0));

                    parent_node.removeChild(bed[2]);
                    parent_node.addChild(bed[3]);
                }

                if(changedValue.getNumber()==4){
                    parent_node.setLocalPosition(new Vector3(0,0,0));

                    parent_node.removeChild(bed[3]);
                    parent_node.addChild(bed[4]);
                }

                if(changedValue.getNumber()==5){
                    parent_node.setLocalPosition(new Vector3(0,0,0));

                    parent_node.removeChild(bed[4]);
                    parent_node.addChild(bed[5]);
                }

               if(changedValue.getNumber()==6){
                   parent_node.addChild(player);
                   scene_view.getScene().addChild(parent_node);
                   scene_view.getScene().getCamera().setLocalPosition(
                           new Vector3(0,0,1));

                   bed_flag = 5;
                   configure_view_controller();
               }
            }
        });
    }

    private void show_notification(String s,int length){
        Toast.makeText(this,s,length).show();
    }

    public boolean compatibility(final Activity activity) {
      String open_gl_version = ((ActivityManager)
                              Objects.requireNonNull(activity.getSystemService
                                                    (Context.ACTIVITY_SERVICE)))
                                     .getDeviceConfigurationInfo()
                                     .getGlEsVersion();

        if (Double.parseDouble(open_gl_version) < MIN_OPENGL_VERSION) {
            show_notification("Sceneform does not support. \n " +
                                 "requires OpenGL ES 3.0 or later", 1);
            activity.finish();
            return false;
        }
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        scene_view.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            scene_view.resume();
        }
        catch (CameraNotAvailableException e) {
            show_notification("Camera not found : "+e.getMessage(),1);
        }
    }

    private static ObjectAnimator createAnimator(boolean clockwise, float axisTiltDeg) {
        // Node's setLocalRotation method accepts Quaternions as parameters.
        // First, set up orientations that will animate a circle.
        Quaternion[] orientations = new Quaternion[4];
        // Rotation to apply first, to tilt its axis.
        Quaternion baseOrientation = Quaternion.axisAngle(new Vector3(1.0f, 0f, 0.0f), axisTiltDeg);
        for (int i = 0; i < orientations.length; i++) {
            float angle = i * 360 / (orientations.length - 1);
            if (clockwise) {
                angle = 360 - angle;
            }
            Quaternion orientation = Quaternion.axisAngle(new Vector3(0.0f, 1.0f, 0.0f), angle);
            orientations[i] = Quaternion.multiply(baseOrientation, orientation);
        }

        ObjectAnimator orbitAnimation = new ObjectAnimator();
        // Cast to Object[] to make sure the varargs overload is called.
        orbitAnimation.setObjectValues((Object[]) orientations);

        // Next, give it the localRotation property.
        orbitAnimation.setPropertyName("localRotation");

        // Use Sceneform's QuaternionEvaluator.
        orbitAnimation.setEvaluator(new QuaternionEvaluator());

        //  Allow orbitAnimation to repeat forever
        orbitAnimation.setRepeatCount(ObjectAnimator.INFINITE);
        orbitAnimation.setRepeatMode(ObjectAnimator.RESTART);
        orbitAnimation.setInterpolator(new LinearInterpolator());
        orbitAnimation.setAutoCancel(true);

        return orbitAnimation;
    }
//        ObjectAnimator orbitAnimation = createAnimator(true,0.4f);
//        orbitAnimation.setTarget(parent_node);
//        orbitAnimation.setDuration(2000);
//        orbitAnimation.start();
}
