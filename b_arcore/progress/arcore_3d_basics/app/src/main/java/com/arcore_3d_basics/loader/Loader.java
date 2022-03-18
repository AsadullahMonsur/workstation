package com.arcore_3d_basics.loader;

import android.content.Context;

import android.net.Uri;
import android.view.Gravity;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.arcore_3d_basics.accessories.DataNotification;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.SceneView;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;

import java.io.Serializable;

public class Loader implements Serializable {
    private final Context context;

    public Loader(Context context){
        this.context = context;
    }

    public void load_model(String path, Node holder, Vector3 position, Vector3 scale,
                           MutableLiveData<DataNotification> mdn, int dn_index){
        ModelRenderable
                .builder()
                .setSource(context,Uri.parse(path))
                .build().thenAccept(model->
                        load_to_screen(model,holder,position,scale,
                                       mdn,dn_index))
                .exceptionally(throwable -> {
                    show_notification("error in rendering model: \n"+
                                        throwable.getMessage(),0);
                    return null;
                });
    }

    private void load_to_screen(ModelRenderable model, Node holder,
                                Vector3 position, Vector3 scale,
                                MutableLiveData<DataNotification> mdn,
                                int dn_index) {
        holder.setRenderable(model);
        holder.setLocalScale(scale);
        holder.setLocalPosition(position);

        mdn.setValue(new DataNotification(dn_index));
    }

    private void show_notification(String s, int length){
        if(context != null){
            Toast toast = Toast.makeText(context, s,length);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }
}
