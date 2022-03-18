package interaction_chap06;
import com.sun.j3d.utils.universe.ViewingPlatform;
import lesson_01.Loader;
import javax.media.j3d.*;
import javax.vecmath.Matrix4d;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import java.util.Enumeration;

public class Player_Roaming extends Behavior {
    private Canvas3D canvas;
    private Matrix4d matrix = new Matrix4d();
    private TransformGroup player = null;
    private Transform3D prev_transform = null;
    private Transform3D next_transform = new Transform3D();
    private Vector3d position = new Vector3d();
    private WakeupOnElapsedFrames wakeFrame = null;
    private double past_angle = 0.0;

    private Vector3d old_pos = new Vector3d();
    public Player_Roaming(Canvas3D canvas,String path) {
        this.canvas = canvas;

        prev_transform = new Transform3D();
        player = new TransformGroup();
        player.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        player.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        prev_transform.setScale(1.0);
        player.setTransform(prev_transform);
        Loader loader = new Loader(path);
        player.addChild(loader.obj_file_to_graph());


        BoundingSphere bounds = new BoundingSphere();//new Point3d(0.0, 0.0, 0.0), 1000.0
        this.setSchedulingBounds(bounds);

        next_transform.set(new Vector3d(0.0, -1.5, -10.0f));// -10 given but -5 will be done
        player.getTransform(prev_transform);
        prev_transform.mul(next_transform);
        player.setTransform(prev_transform);

        double angle = Math.toRadians(180);
        next_transform.rotY(angle);
        player.getTransform(prev_transform);
        prev_transform.get(matrix);
        prev_transform.setTranslation(new Vector3d(0.0, 0.0, 0.0));
        prev_transform.mul(next_transform);
        prev_transform.setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
        player.setTransform(prev_transform);
    }

    public void initialize() {
        wakeFrame = new WakeupOnElapsedFrames(0);
        wakeupOn(wakeFrame);
    }

    public void processStimulus(Enumeration criteria) {
        player.getTransform(prev_transform);
        prev_transform.get(position);
        wakeupOn(wakeFrame);
    }


    public void rotation(int flag,  double angle){
//        next_transform.rotY(-past_angle);
//        player.getTransform(prev_transform);
//        prev_transform.get(matrix);
//        prev_transform.setTranslation(new Vector3d(0.0, 0.0, 0.0));
//        prev_transform.mul(next_transform);
//        prev_transform.setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
//        player.setTransform(prev_transform);

        next_transform.rotY(flag*angle);
        player.getTransform(prev_transform);
        prev_transform.get(matrix);
        prev_transform.setTranslation(new Vector3d(0.0, 0.0, 0.0));
        prev_transform.mul(next_transform);
        prev_transform.setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
        player.setTransform(prev_transform);

        past_angle = angle;
    }

    public void rotation(double angle){

        next_transform.rotY(angle);
//        player.getTransform(prev_transform);
//        prev_transform.get(matrix);
//        prev_transform.setTranslation(new Vector3d(0.0, 0.0, 0.0));
//        prev_transform.mul(next_transform);
//        prev_transform.setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
        player.setTransform(next_transform);
    }
    public boolean move(double increment){
        next_transform.set(new Vector3d(0.0, 0.0, increment));//if n is given, z increased n/2
        player.getTransform(prev_transform);
        prev_transform.get(old_pos);
        prev_transform.mul(next_transform);
        player.setTransform(prev_transform);

        return true;
    }


    public TransformGroup getPlayer() {
        return player;
    }

    public Matrix4d getMatrix() {
        return matrix;
    }

    public Transform3D getPrev_transform() {
        return prev_transform;
    }

    public Transform3D getNext_transform() {
        return next_transform;
    }


    public Vector3d getPosition() {
        player.getTransform(prev_transform);
        prev_transform.get(position);
        return position;
    }

    public Vector3d getOld_pos() {
        return old_pos;
    }
}
