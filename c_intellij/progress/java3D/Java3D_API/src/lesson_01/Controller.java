package lesson_01;

import javax.media.j3d.*;
import javax.vecmath.Matrix4d;
import javax.vecmath.Vector3d;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller {
    private TransformGroup tGroup = null;
    private Transform3D past_transform = null;
    private Transform3D new_transform = new Transform3D();
    private Matrix4d matrix = new Matrix4d();
    private Canvas canvas;

    public Controller(Canvas3D canvas, TransformGroup tGroup, Transform3D transform) {
        this.tGroup = tGroup;
        this.canvas = canvas;
        this.past_transform = transform;
        this.canvas.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                key_typing(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    private void key_typing(KeyEvent e) {
        char key = e.getKeyChar();

        if (key == 'd') {
            new_transform.rotY(Math.PI / 8);
            tGroup.getTransform(past_transform);
            past_transform.get(matrix);
            past_transform.setTranslation(new Vector3d(0.0, 0.0, 0.0));
            past_transform.mul(new_transform);
            past_transform.setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
            tGroup.setTransform(past_transform);
        }

        if (key == 'a') {
            new_transform.rotY(-Math.PI / 8);
            tGroup.getTransform(past_transform);
            past_transform.get(matrix);
            past_transform.setTranslation(new Vector3d(0.0, 0.0, 0.0));
            past_transform.mul(new_transform);
            past_transform.setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
            tGroup.setTransform(past_transform);
        }
    }
}