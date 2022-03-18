package participants;

import accessories.RefreshRate;
import loader.Loader;

import javax.media.j3d.*;
import javax.vecmath.Matrix4d;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import java.util.Enumeration;

public class PlayerBehaviour extends Behavior {
    private Matrix4d matrix = new Matrix4d();
    private TransformGroup player = new TransformGroup();
    private Transform3D prev_transform = new Transform3D();
    private Transform3D next_transform = new Transform3D();

    private Vector3d past_position;
    private Vector3d present_position;

    private Point3d past_pos_point;
    private Point3d present_pos_point;

    private double past_angle_rotate_Y;

    private WakeupOnElapsedFrames wakeFrame = null;

    public PlayerBehaviour(String path) {
     try {
         player = new TransformGroup();
         prev_transform = new Transform3D();
         next_transform = new Transform3D();

         past_position = new Vector3d();
         present_position = new Vector3d();
         past_angle_rotate_Y = 0.0;  // in radian


         player.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
         player.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
         prev_transform.setScale(1.0);
         player.setTransform(prev_transform);

         Loader loader = new Loader(path);
         player.addChild(loader.object_to_graph());

         BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),
                 100.0);
         this.setSchedulingBounds(bounds);

         preliminary();
      }
      catch (Exception e){
         e.printStackTrace();
          System.out.println("Player Behaviour : \n" + e.getMessage());
      }
    }

    public void initialize() {
        wakeFrame = new WakeupOnElapsedFrames(0);
        wakeupOn(wakeFrame);
    }

    public void processStimulus(Enumeration criteria) {
        player.getTransform(prev_transform);
        prev_transform.get(present_position);

        wakeupOn(wakeFrame);
    }

    private void  preliminary(){
        next_transform.set(new Vector3d(0.0, -1.5, -10.0f));
                                                   // -10 given but -5 will be done
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

    public boolean rotationY(int flag,  double angle){
        next_transform.rotY(flag*angle);
        player.getTransform(prev_transform);
        prev_transform.get(matrix);
        prev_transform.setTranslation(new Vector3d(0.0, 0.0, 0.0));
        prev_transform.mul(next_transform);
        prev_transform.setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
        player.setTransform(prev_transform);

        past_angle_rotate_Y = angle;

        return true;
    }

    public boolean rotationY(double angle){
        next_transform.rotY(angle);
        player.getTransform(prev_transform);
        prev_transform.get(matrix);
        prev_transform.setTranslation(new Vector3d(0.0, 0.0, 0.0));
        prev_transform.mul(next_transform);
        prev_transform.setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
        player.setTransform(prev_transform);

        return true;
    }

    public boolean move_forward_backward(double increment){
        next_transform.set(new Vector3d(0.0, 0.0, increment));
                                                //if n is given, z increased n/2
        player.getTransform(prev_transform);
        prev_transform.get(past_position);
        prev_transform.mul(next_transform);
        player.setTransform(prev_transform);

        return true;
    }

    public TransformGroup getPlayer() {
        return player;
    }

    public Vector3d getPast_position() {
        return past_position;
    }

    public Vector3d getPresent_position() {
        player.getTransform(prev_transform);
        prev_transform.get(present_position);
        return present_position;
    }

    public Point3d getPast_pos_point() {
        past_pos_point = new Point3d(past_pos_point);
        return past_pos_point;
    }

    public Point3d getPresent_pos_point() {
        present_pos_point = new Point3d(present_pos_point);
        return present_pos_point;
    }

    public double getPast_angle_rotate_Y() {
        return past_angle_rotate_Y;
    }
}
