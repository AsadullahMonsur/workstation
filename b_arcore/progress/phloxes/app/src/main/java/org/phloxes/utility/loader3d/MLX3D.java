package org.phloxes.utility.loader3d;

import android.content.Context;
import android.net.Uri;
import androidx.lifecycle.MutableLiveData;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import org.phloxes.utility.notification.DataNotification;

import java.io.Serializable;

import static org.phloxes.utility.notification.Notification.show_notification;

public class MLX3D implements Serializable {
    private final Context context;

    public MLX3D(Context context){
        this.context = context;
    }

    public void load_model(String path, Node holder, Vector3 position, Vector3 scale,
                           MutableLiveData<DataNotification> mdn, int dn_index){
        //dn_index -> data notification index

        ModelRenderable.builder()
                       .setSource(context, Uri.parse(path))
                       .build()
                       .thenAccept(model->
                            load_to_screen(model,holder,position,scale,mdn,dn_index))
                            .exceptionally(throwable -> {
                            show_notification(context,
                                "error in rendering model at MLX3D :-> \n"+throwable.getMessage(),
                                0);
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

}
