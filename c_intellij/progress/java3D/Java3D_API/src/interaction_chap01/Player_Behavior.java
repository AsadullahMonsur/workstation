package interaction_chap01;

import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import lesson_01.Loader;

import javax.media.j3d.*;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import java.util.Enumeration;

public class Player_Behavior extends Behavior {
    // Player_Behavior/behavior must attach to corresponding Group
        private TransformGroup model_group = null;
        private Transform3D past_transform = null;
        private Transform3D new_transform = new Transform3D();
        private WakeupOnElapsedFrames wakeFrame = null;

        private TransformGroup space = null;
        private Transform3D space_past_transform = null;
        private Transform3D space_new_transform = new Transform3D();

        public Player_Behavior(TransformGroup space, Transform3D space_transform, Transform3D t3d, String path3) {
            this.space = space;
            this.space.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
            this.space_past_transform = space_transform;
            this.past_transform = t3d;

            model_group = new TransformGroup();
            model_group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

            Loader loader = new Loader(path3);
            model_group.addChild(loader.obj_file_to_graph());

            //bound is must
            BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 1000.0);
            this.setSchedulingBounds(bounds);
        }

        public void initialize() {
            wakeFrame = new WakeupOnElapsedFrames(0);
            wakeupOn(wakeFrame);
        }

        public void processStimulus(Enumeration criteria) {

            float value = -0.0003f;
            new_transform.set(new Vector3d(0, 0,value));
            model_group.getTransform(past_transform);
            past_transform.mul(new_transform);
            model_group.setTransform(past_transform);

            move_forward(value);
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

    public TransformGroup getSpace() {
        return space;
    }

    public Transform3D getSpace_past_transform() {
        return space_past_transform;
    }

    public Transform3D getSpace_new_transform() {
        return space_new_transform;
    }

    private void move_forward(float value){
        space_new_transform.set(new Vector3d(0,0,(-10*value)));
        space.getTransform(space_past_transform);
        space_past_transform.mul(space_new_transform);
        space.setTransform(space_past_transform);
    }
}
