package com.example.arcore_with_leap_motion.data_model;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.arcore_with_leap_motion.R;
import com.example.arcore_with_leap_motion.cloud_connection.Custom_AR_Fragment;
import com.example.arcore_with_leap_motion.io_handler.Choice;
import com.google.android.material.button.MaterialButton;
import com.google.ar.core.Anchor;
import com.google.ar.core.Frame;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.animation.ModelAnimator;
import com.google.ar.sceneform.math.Quaternion;
import com.google.ar.sceneform.math.QuaternionEvaluator;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.AnimationData;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collection;
import java.util.Objects;

public class Object_Builder extends AppCompatActivity {
    private static final double MIN_OPENGL_VERSION = 3.0;
    private static final String TAG = Object_Builder.class.getSimpleName();
    private static int checker = 0;

    MaterialButton rotate_x_btn;
    MaterialButton rotate_y_btn;
    MaterialButton anchor_resolve;

    private Custom_AR_Fragment viewer_fragment;
    private Anchor anchor;
    private Object_Builder.AppAnchorState aaP = Object_Builder.AppAnchorState.NONE;

    private TransformableNode t_node;
    private ModelRenderable m_renderable;

    private  boolean isPlaced = false;
    private SharedPreferences sPref;
    private SharedPreferences.Editor sPEditor;

    private FirebaseDatabase dB;
    private DatabaseReference dBR;// main root
    private DatabaseReference dBCR; // child root

    private enum AppAnchorState{
        NONE,
        HOSTING,
        HOSTED
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_object_container_layout);

        if (!compatibility(this)) {
            notification("Incompatible Device");
        }

        dB = FirebaseDatabase.getInstance();
        dBR = dB.getReference();
//        dBCR = dBR.child("input");

        sPref = getSharedPreferences("AnchorId",MODE_PRIVATE);
        sPEditor = sPref.edit();

        rotate_x_btn = findViewById(R.id.b_object_rotate_x_btn);
        rotate_y_btn = findViewById(R.id.b_object_rotate_y_btn);
        anchor_resolve = findViewById(R.id.b_object_cloud_anchor_btn);

        viewer_fragment = (Custom_AR_Fragment) getSupportFragmentManager()
                            .findFragmentById(R.id.b_object_fragment);

        try {
            cloud_anchor_former();

            anchor_resolve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String anchorId = sPref.getString("AnchorId",null);

                    if(anchorId.equals("null")){
                        return;
                    }

                    Anchor resolvedAnchor =  viewer_fragment.getArSceneView()
                                                    .getSession()
                                                    .resolveCloudAnchor(anchorId);
                    prepare_object(resolvedAnchor);
                    checker = 1;

                    dBR.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                            for(DataSnapshot child: children){
                                try {
                                    Choice choice = child.getValue(Choice.class);
                                    notification("Successfully fetched data");

                                    if(choice.getChoice()==1){
                                        x_rotation(0);
                                    }
                                    else if(choice.getChoice()==2){
                                        y_rotation(0);
                                    }
                                }
                                catch (Exception e){
                                    notification("Error fetching info from database");
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            notification("Error in Database");
                        }
                    });
                }
            });

            rotate_x_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checker == 1) {
                        x_rotation(0);
                    } else {
                        notification("object has not been formed. resolve anchor first");
                    }
                }
            });

            rotate_y_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checker == 1) {
                        y_rotation(0);
                    } else {
                        notification("object has not been formed. resolve anchor first");
                    }
                }
            });

        }
        catch (Exception ex){
            notification("Exception occurred");
        }
    }

    private void cloud_anchor_former() {
      viewer_fragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
        if (!isPlaced) {
          anchor = viewer_fragment.getArSceneView()
                         .getSession()
                         .hostCloudAnchor(hitResult.createAnchor());

          aaP = Object_Builder.AppAnchorState.HOSTING;
          notification("Hosting");
          prepare_object(anchor);
          isPlaced = true;
        }
      });

      viewer_fragment.getArSceneView().getScene()
            .addOnUpdateListener(frameTime -> {

         if(aaP!= Object_Builder.AppAnchorState.HOSTING){
            return;
         }

         Anchor.CloudAnchorState aCS = anchor.getCloudAnchorState();

         if( aCS.isError()){
            notification(aCS.toString());
         }
         else if(aCS == Anchor.CloudAnchorState.SUCCESS){
            aaP = Object_Builder.AppAnchorState.HOSTED;
            String anchorId = anchor.getCloudAnchorId();
            sPEditor.putString("AnchorId",anchorId);
            sPEditor.apply();
            notification("Hosted Successful.Anchor ID: "+anchorId);
         }
      });
    }

    private void prepare_object(Anchor anchor) {
        ModelRenderable
                .builder()
                .setSource(this, Uri.parse("model.sfb"))
                .build().thenAccept(modelRenderable ->
                place_object(anchor, modelRenderable))
                .exceptionally(throwable -> {
                    Toast toast = Toast.makeText(this,
                            "Unable to load model",
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return null;
                });
    }

    private void place_object(Anchor anchor, ModelRenderable modelRenderable) {
        if(t_node!=null){
            t_node.setParent(null);
        }

        AnchorNode anchor_node = new AnchorNode(anchor);
        setM_renderable(modelRenderable);

        TransformableNode tnode = new TransformableNode(viewer_fragment.getTransformationSystem());
        tnode.setRenderable(modelRenderable);
        tnode.setParent(anchor_node);
        viewer_fragment.getArSceneView().getScene().addChild(tnode);
        setT_node(tnode);

        tnode.select();
    }


    public TransformableNode getT_node() {
        return t_node;
    }

    public void setT_node(TransformableNode t_node) {
        this.t_node = t_node;
    }

    public ModelRenderable getM_renderable() {
        return m_renderable;
    }

    public void setM_renderable(ModelRenderable m_renderable) {
        this.m_renderable = m_renderable;
    }

    private void x_rotation(int selector) {

    t_node.setLocalRotation(Quaternion.multiply(
           t_node.getLocalRotation(),
           Quaternion.axisAngle(new Vector3(1.0f, 0.0f, 0.0f), 45) ));
    }

    private void y_rotation(int selector) {
        t_node.setLocalRotation(Quaternion.multiply(
                t_node.getLocalRotation(),
                Quaternion.axisAngle(new Vector3(0.0f, 1.0f, 0.0f), 45) ));
    }

    private boolean object_with_cloud_anchor() {

        return false;
    }

    public boolean compatibility(final Activity activity) {

        String open_gl_version = ((ActivityManager) Objects.requireNonNull
                                         (activity.getSystemService
                                           (Context.ACTIVITY_SERVICE)))
                                         .getDeviceConfigurationInfo()
                                         .getGlEsVersion();

        if (Double.parseDouble(open_gl_version) < MIN_OPENGL_VERSION) {
            Log.e(TAG, "Sceneform requires OpenGL ES 3.0 later");
            notification("Sceneform requires OpenGL ES 3.0 or later");
            activity.finish();

            return false;
        }
        notification("Sceneform Supports");
        return true;
    }

    private void notification(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

}
