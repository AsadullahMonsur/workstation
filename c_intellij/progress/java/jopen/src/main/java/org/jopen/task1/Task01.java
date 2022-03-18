package org.jopen.task1;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.*;

public class Task01 {
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
             gl.glPointSize(10.0f);
             gl.glBegin(GL2.GL_TRIANGLES);

             gl.glVertex2d(0,0);
             gl.glVertex2d(25,43.33);
             gl.glVertex2d(50,0);

             gl.glVertex2d(50,33.33);
             gl.glVertex2d(25,-10);
             gl.glVertex2d(0,33.33);

             gl.glEnd();

             gl.glColor3d(0,0,0);
             gl.glPointSize(10.0f);
             gl.glBegin(GL2.GL_LINES);

             gl.glVertex2d(100,0);
             gl.glVertex2d(125,43.3);

             gl.glVertex2d(125,43.3);
             gl.glVertex2d(150,0);

             gl.glVertex2d(150,0);
             gl.glVertex2d(100,0);

             gl.glVertex2d(150,33.33);
             gl.glVertex2d(125,-10);

             gl.glVertex2d(125,-10);
             gl.glVertex2d(100,30.33);

             gl.glVertex2d(100,30.33);
             gl.glVertex2d(150,33.33);

             gl.glEnd();
         }

         @Override
         public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

         }
     });

     final JFrame frame = new JFrame("Application Window for Task 01");
     frame.add(canvas);
     frame.setSize(800,450);
     frame.setVisible(true);
    }
}

