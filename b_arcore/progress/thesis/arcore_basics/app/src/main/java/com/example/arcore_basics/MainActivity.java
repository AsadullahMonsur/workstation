package com.example.arcore_basics;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.graphics.Point;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;
import com.google.ar.core.Anchor;
import com.google.ar.core.Frame;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.core.Trackable;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class MainActivity extends AppCompatActivity {

    ArFragment arFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addObject(Uri.parse("M-FF_iOS_HERO_Tony_Stark_Iron_Man_Civil_War.sfb"));
            }
        });

        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.sceneForm);

//        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
//
//            Anchor anchor = hitResult.createAnchor();
//
//            ModelRenderable.builder()
//               .setSource(this,Uri.parse("res/raw/iron_man.sfb"))
//               .build().thenAccept(modelRenderable -> addModelToScene(anchor,modelRenderable))
//               .exceptionally(throwable -> {
//               AlertDialog.Builder builder = new AlertDialog.Builder(this);
//               builder.setMessage(throwable.getMessage())
//                       .show();
//               return null;
//            });
//
//        });
    }

    private  void addModelToScene(Anchor anchor,ModelRenderable modelRenderable){
        AnchorNode anchorNode = new AnchorNode(anchor);
        TransformableNode transformableNode = new
                TransformableNode(arFragment.getTransformationSystem());

        transformableNode.setParent(anchorNode);
        transformableNode.setRenderable(modelRenderable);
        arFragment.getArSceneView().getScene().onAddChild(anchorNode);
        transformableNode.select();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addObject(Uri parse) {
        Frame frame = arFragment.getArSceneView().getArFrame();
        Point point = getScreenCenter();
        if (frame != null) {
            List<HitResult> hits = frame.hitTest((float) point.x, (float) point.y);

            for (int i = 0; i < hits.size(); i++) {
                Trackable trackable = hits.get(i).getTrackable();
                if (trackable instanceof Plane && ((Plane) trackable).isPoseInPolygon(hits.get(i).getHitPose())) {
                    placeObject(arFragment, hits.get(i).createAnchor(), parse);
                }
            }
        }
    }

    private final void placeObject(final ArFragment fragment, final Anchor createAnchor, Uri model) {
        ModelRenderable.builder().setSource(fragment.getContext(), model).build().thenAccept((new Consumer() {
            public void accept(Object var1) {
                this.accept((ModelRenderable) var1);
            }

            public final void accept(ModelRenderable it) {
                if (it != null)
                    MainActivity.this.addNode(arFragment, createAnchor, it);
            }
        })).exceptionally((new Function() {
            public Object apply(Object var1) {
                return this.apply((Throwable) var1);
            }

            @Nullable
            public final Void apply(Throwable it) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage(it.getMessage()).setTitle("error!");
                AlertDialog dialog = builder.create();
                dialog.show();
                return null;
            }
        }));
    }

    private void addNode(ArFragment fragment, Anchor createAnchor, ModelRenderable renderable) {

        AnchorNode anchorNode = new AnchorNode(createAnchor);
        TransformableNode transformableNode = new TransformableNode(fragment.getTransformationSystem());
        transformableNode.setRenderable(renderable);
        transformableNode.setParent(anchorNode);
        fragment.getArSceneView().getScene().addChild(anchorNode);
        transformableNode.select();
    }

    private Point getScreenCenter() {
        View vw = findViewById(android.R.id.content);
        return new Point(vw.getWidth() / 2, vw.getHeight() / 2);
    }
}

