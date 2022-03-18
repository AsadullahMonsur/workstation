package interaction_chap01;

import javax.media.j3d.Canvas3D;
import javax.media.j3d.Transform3D;
import javax.vecmath.Matrix4d;
import javax.vecmath.Vector3d;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player_Controller {
    private Player_Behavior playerBehavior = null;
    private Matrix4d matrix = new Matrix4d();
    private Canvas canvas;

    public Player_Controller(Canvas3D canvas, Player_Behavior playerBehavior) {
        this.playerBehavior = playerBehavior;
        this.canvas = canvas;
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

        if (key == 'a') {
            double value = Math.PI / 18;
            playerBehavior.getNew_transform().rotY(value);
            playerBehavior.getModel_group().getTransform(playerBehavior.getPast_transform());
            playerBehavior.getPast_transform().get(matrix);
            playerBehavior.getPast_transform().setTranslation(new Vector3d(0.0, 0.0, 0.0));
            playerBehavior.getPast_transform().mul(playerBehavior.getNew_transform());
            playerBehavior.getPast_transform().setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
            playerBehavior.getModel_group().setTransform(playerBehavior.getPast_transform());

            rotate_environment(-1,value);
        }

        if (key == 'd') {
            double value = Math.PI / 18;
            playerBehavior.getNew_transform().rotY(-1*value);
            playerBehavior.getModel_group().getTransform(playerBehavior.getPast_transform());
            playerBehavior.getPast_transform().get(matrix);
            playerBehavior.getPast_transform().setTranslation(new Vector3d(0.0, 0.0, 0.0));
            playerBehavior.getPast_transform().mul(playerBehavior.getNew_transform());
            playerBehavior.getPast_transform().setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
            playerBehavior.getModel_group().setTransform(playerBehavior.getPast_transform());

            rotate_environment(1,value);
        }

        int keyCode = e.getKeyCode();
        switch( keyCode ) {
            case KeyEvent.VK_UP:

                break;
            case KeyEvent.VK_DOWN:

                break;
            case KeyEvent.VK_LEFT:

                break;
            case KeyEvent.VK_RIGHT :

                break;
        }
    }

    private void rotate_environment(int flag, double value){
        Matrix4d matrix = new Matrix4d();
        playerBehavior.getSpace_new_transform().rotY(flag*value*0.5);
        playerBehavior.getSpace().getTransform(playerBehavior.getSpace_past_transform());
        playerBehavior.getSpace_past_transform().get(matrix);
        playerBehavior.getSpace_past_transform().setTranslation(new Vector3d(0.0, 0.0, 0.0));
        playerBehavior.getSpace_past_transform().mul(playerBehavior.getSpace_new_transform());
        playerBehavior.getSpace_past_transform().setTranslation(new Vector3d(matrix.m03, matrix.m13, matrix.m23));
        playerBehavior.getSpace().setTransform(playerBehavior.getSpace_past_transform());
    }
}
