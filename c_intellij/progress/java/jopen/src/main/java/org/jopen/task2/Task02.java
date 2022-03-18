package org.jopen.task2;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.*;

public class Task02 {
    public static void main(String args[]){
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities=new GLCapabilities(profile);
        final GLCanvas canvas = new GLCanvas(capabilities);
        canvas.setSize(400, 400);

        canvas.addGLEventListener(new GLEventListener() {

            @Override
            public void init(GLAutoDrawable glAutoDrawable) {
                GL2 gl = glAutoDrawable.getGL().getGL2();
                GLU glu = new GLU();

                gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
                gl.glViewport(-400, -225, 800, 450);
                gl.glMatrixMode(GL2.GL_PROJECTION);
                gl.glLoadIdentity();
                glu.gluOrtho2D(-600.0, 800.0, -225.0, 450.0);
            }

            @Override
            public void dispose(GLAutoDrawable glAutoDrawable) {

            }

            @Override
            public void display(GLAutoDrawable glAutoDrawable) {
                GL2 gl = glAutoDrawable.getGL().getGL2();

                gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

                gl.glColor3d(0,0,0);
                gl.glPointSize(30.0f);
                gl.glBegin(GL2.GL_LINES);

                gl.glVertex2d(100,100);
                gl.glVertex2d(0,100);

                gl.glVertex2d(0,100);
                gl.glVertex2d(0,0);

                gl.glVertex2d(0,0);
                gl.glVertex2d(100,0);

                gl.glVertex2d(0,50);
                gl.glVertex2d(100,50);

                gl.glEnd();
            }

            @Override
            public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

            }
        });

        final JFrame frame = new JFrame("Application Window for Task 02");
        frame.add(canvas);
        frame.setSize(800,450);
        frame.setVisible(true);
    }
}
