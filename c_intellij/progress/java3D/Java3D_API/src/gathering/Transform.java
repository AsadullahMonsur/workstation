package gathering;

import javax.media.j3d.Transform3D;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

class Transform extends Transform3D {
    Point3d position=new Point3d();
    Point3d lookingAt=new Point3d(1,0,0); // looking at the x axis
    Vector3d upVector=new Vector3d(0,1,0); // y is up
    double totalAngleXZ=0; // its a must to add the angle and to calculate it from the top since it creates a bug later on
    double totalAngleY=0; // its a must to add the angle and to calculate it from the top since it creates a bug later on
    public void rotXZ(double angle) {
        totalAngleXZ+= angle;
        lookAt();
    }
    public void rotY(double angle) {
        totalAngleY+= angle;
        lookAt();
    }

    private void lookAt() {
        lookingAt.set(Math.cos(totalAngleXZ),Math.cos(totalAngleY),Math.sin(totalAngleXZ));
        lookAt(position,lookingAt,upVector);
    }

}