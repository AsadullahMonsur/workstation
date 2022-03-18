package interaction_chap05;

import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.universe.ViewingPlatform;
import lesson_01.Loader;

import javax.media.j3d.*;
import javax.vecmath.Matrix4d;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Enumeration;

public class Domo extends Behavior{
    private Canvas canvas;
    private Matrix4d matrix = new Matrix4d();
    private TransformGroup model_group = null;
    private Transform3D past_transform = null;
    private Transform3D new_transform = new Transform3D();
    private WakeupOnElapsedFrames wakeFrame = null;

    private float turned_x = 0.0f;
    private float turned_z = 0.0f;

    public Domo(Canvas3D canvas, String path3) {
        this.canvas = canvas;
        past_transform = new Transform3D();
        model_group = new TransformGroup();
        model_group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        model_group.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        Loader loader = new Loader(path3);
        model_group.addChild(loader.obj_file_to_graph());

        //bound is must
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 1000.0);
        this.setSchedulingBounds(bounds);

        double value = Math.toRadians(180);
        getNew_transform().rotY(value);
        getModel_group().getTransform(getPast_transform());
        getPast_transform().get(matrix);
        getPast_transform().setTranslation(new Vector3d(0.0, 0.0, 0.0));
        getPast_transform().mul(getNew_transform());
        getPast_transform().setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
        getModel_group().setTransform(getPast_transform());

    }

    public void initialize() {
        wakeFrame = new WakeupOnElapsedFrames(0);
        wakeupOn(wakeFrame);
    }

    public void processStimulus(Enumeration criteria) {
        double value = Math.toRadians(5);
        Vector3d vector = new Vector3d();
        past_transform.get(vector);
        double r = Math.sqrt(vector.x*vector.x+vector.z*vector.z);
        turned_x = ((float) (r+1)* (float) Math.sin(value))/1000.0f;
        turned_z = ((float) (r+1)*(float) Math.cos(value))/1000.0f;

        new_transform.set(new Vector3d(turned_x, 0.0, turned_z));
        model_group.getTransform(past_transform);
        past_transform.mul(new_transform);
        model_group.setTransform(past_transform);

        past_transform.get(vector);


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
}
