package testy;

import com.sun.j3d.utils.geometry.Box;

import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

public class Ground_Floor {
    private int row;
    private int column;

    public Ground_Floor() {
        row = 4;
        column = 4;
    }

    public Ground_Floor(int dimension) {
        if(dimension<4){
            dimension = 4;
        }
        if(dimension%2!=0){
            dimension = dimension +1;
        }
        this.row = dimension;
        this.column = dimension;
    }

    public TransformGroup form_floor(int flag) {
        int r = row;
        int c = column;
        int color = 1; // 1 white, 0 black

        TransformGroup tGroup = new TransformGroup();
        Transform3D transform = new Transform3D();
        tGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        transform.setTranslation(new Vector3f(0f,0f,0f));
        tGroup.setTransform(transform);

        if(flag==1){
            r = 2;
            c = 2;
        }

        int width = 0;
        int z = -5;

        while(width<r){
            if(color==0){
                color = 1;
            }
            else{
                color = 0;
            }

            int x = r/2;
            int length =0;

            while (length<c) {
                floor_installment(x,z,flag,color,tGroup);
                x = x-1;
                length++;
            }
            z = z-2;
            width++;
        }
        return tGroup;
    }

    private void floor_installment(int x, int z, int flag, int color, TransformGroup tGroup){
        ColoringAttributes color_attributes = new ColoringAttributes();
        if(color==1){
            color_attributes.setColor(0f,0f,0f);
            if(flag==1){
                color_attributes.setColor(0f,0f,1f);
            }
            color = 0;
        }
        else {
            color = 1;
            color_attributes.setColor(1f,1f,1f);
            if(flag==1){
                color_attributes.setColor(1f,0f,0f);
            }
        }

        Appearance appearance = new Appearance();
        appearance.setColoringAttributes(color_attributes);
        Box box = new Box(0.5f,0.2f,1f,appearance);

        if(flag==1) {
            box = new Box(0.5f,1.0f,1f,appearance);
        }

        TransformGroup group = new TransformGroup();
        group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        group.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

        Transform3D tf = new Transform3D();
        tf.setTranslation(new Vector3d(x, -1.5, z));

        group.setTransform(tf);

        group.addChild(box);
        tGroup.addChild(group);
    }

    public TransformGroup form_obstacle(){
        TransformGroup obstacles = new TransformGroup();
        obstacles.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        obstacles.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

        Transform3D transform = new Transform3D();
        transform.setTranslation(new Vector3f(0f,0f,0f));
        obstacles.setTransform(transform);

        int z = -5;
        int checker_outter = 1;
        for(int width=0; width<row; width=width+2){
            if(checker_outter%2==0) {
                int x = (row / 2);
                if (x % 2 != 0) {
                    x = x - 1;
                }
                int checker_inner = 1;
                for (int length = 0; length < column; length = length + 2) {
                    if (checker_inner % 2 == 0) {
                        obstacles_installment(x,z,obstacles);                    }
                    checker_inner = checker_inner + 1;
                    x = x - 2;
                }
            }
            checker_outter = checker_outter+1;
            z = z-4;
        }
        return obstacles;
    }

    private void obstacles_installment(int x, int z, TransformGroup obstacles) {
        TransformGroup group = form_floor(1);
        group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        group.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        Transform3D tf = new Transform3D();
        tf.setTranslation(new Vector3f(x, 0.5f, z+(1+4) ));
        group.setTransform(tf);
        obstacles.addChild(group);

    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

}
