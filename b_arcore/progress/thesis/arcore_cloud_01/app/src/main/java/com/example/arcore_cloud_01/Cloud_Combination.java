package com.example.arcore_cloud_01;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;

public class Cloud_Combination extends AppCompatActivity {
    private Custom_AR_Fragment cf;
    private enum AppAnchorState{
        NONE,
        HOSTING,
        HOSTED
    }
    private Cloud_Combination.AppAnchorState aaP = Cloud_Combination.AppAnchorState.NONE;
    private Anchor anchor;

    private  boolean isPlaced = false;
    private SharedPreferences sPref;
    private SharedPreferences.Editor sPEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cloud_combination_layout);

        sPref = getSharedPreferences("AnchorId",MODE_PRIVATE);
        sPEditor = sPref.edit();

        cf = (Custom_AR_Fragment) getSupportFragmentManager().findFragmentById(R.id.fragment);

        cf.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {

            if(!isPlaced){
                anchor = cf.getArSceneView()
                        .getSession()
                        .hostCloudAnchor(hitResult.createAnchor());

                aaP = Cloud_Combination.AppAnchorState.HOSTING;
                showToast("Hosting");
                create_model(anchor);
                isPlaced = true;
            }
        });

        cf.getArSceneView().getScene()
                .addOnUpdateListener(frameTime -> {

                    if(aaP!= Cloud_Combination.AppAnchorState.HOSTING){
                        return;
                    }
                    Anchor.CloudAnchorState aCS = anchor.getCloudAnchorState();

                    if( aCS.isError()){
                        showToast(aCS.toString());
                    }
                    else if(aCS==Anchor.CloudAnchorState.SUCCESS){
                        aaP = Cloud_Combination.AppAnchorState.HOSTED;
                        String anchorId = anchor.getCloudAnchorId();
                        sPEditor.putString("AnchorId",anchorId);
                        sPEditor.apply();
                        showToast("Hosted Successful.Anchor ID: "+anchorId);
                    }
                });

        Button b = findViewById(R.id.resolve);
        b.setOnClickListener(view ->{
            String anchorId = sPref.getString("AnchorId",null);

            if(anchorId.equals("null")){
                Toast.makeText(this,"No id found",Toast.LENGTH_LONG).show();
                return;
            }

            Anchor resolvedAnchor =  cf.getArSceneView().getSession().resolveCloudAnchor(anchorId);
            create_model(resolvedAnchor);
        });
    }

    private void showToast(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

    private void create_model(Anchor anchor) {
        ModelRenderable
                .builder()
                .setSource(this, Uri.parse("Convertible.sfb"))
                .build().thenAccept(modelRenderable ->
                 place_model(anchor,modelRenderable));
    }

    private void place_model(Anchor anchor, ModelRenderable modelRenderable) {
        AnchorNode node = new AnchorNode(anchor);
        node.setRenderable(modelRenderable);
        cf.getArSceneView().getScene().addChild(node);
    }
}
