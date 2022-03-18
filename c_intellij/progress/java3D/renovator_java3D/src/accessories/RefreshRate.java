package accessories;

import javax.media.j3d.Behavior;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.WakeupOnElapsedFrames;
import javax.vecmath.Point3d;
import java.util.Enumeration;

public class RefreshRate extends Behavior {
    private static long last_frame_time = 0;
    private static float time_difference = 0;
    private WakeupOnElapsedFrames wakeFrame = null;

    public RefreshRate() {
        last_frame_time = current_time();

        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 10.0);
        this.setSchedulingBounds(bounds);
    }

    @Override
    public void initialize() {
        wakeFrame = new WakeupOnElapsedFrames(0);
        wakeupOn(wakeFrame);
    }

    @Override
    public void processStimulus(Enumeration enumeration) {
        long current_frame_time = current_time();
        time_difference = (current_frame_time - last_frame_time)/1000.0f;
        last_frame_time = current_frame_time;
        wakeupOn(wakeFrame);
    }

    public static float get_time_difference() {
        return time_difference;
    }

    private static long current_time(){
        return System.currentTimeMillis();
    }
}
