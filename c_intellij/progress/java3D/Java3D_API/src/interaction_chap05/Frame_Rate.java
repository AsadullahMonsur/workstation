package interaction_chap05;

import javax.media.j3d.*;
import javax.vecmath.Point3d;
import java.util.Enumeration;

public class Frame_Rate extends Behavior {
    private static long last_frame_time = 0;
    private static float delta = 0;
    private WakeupOnElapsedFrames wakeFrame = null;

    private TransformGroup doom = new TransformGroup();
    public Frame_Rate() {
        last_frame_time = get_current_time();  //
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 1000.0);
        this.setSchedulingBounds(bounds);
    }

    @Override
    public void initialize() {
        wakeFrame = new WakeupOnElapsedFrames(0);
        wakeupOn(wakeFrame);
    }

    @Override
    public void processStimulus(Enumeration enumeration) {
        Transform3D transform3D = new Transform3D();

            long current_frame_time = get_current_time();
            delta = (current_frame_time - last_frame_time)/1000.0f;
            last_frame_time = current_frame_time;
 //           System.out.println(delta);

            wakeupOn(wakeFrame);
    }

    private static long get_current_time(){
        return System.currentTimeMillis();
    }
    public static long getLast_frame_time() {
        return last_frame_time;
    }

    public TransformGroup getDoom() {
        return doom;
    }

    public static void setLast_frame_time(long last_frame_time) {
        Frame_Rate.last_frame_time = last_frame_time;
    }

    public static float getDelta() {
        return delta;
    }

    public static void setDelta(float delta) {
        Frame_Rate.delta = delta;
    }
}
