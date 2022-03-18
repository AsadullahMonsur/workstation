package com.renovator3D;

import com.sun.j3d.utils.applet.MainFrame;
import opener.GameViewer;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends Applet {


    public static void main(String[] args) {
	// write your code here
       String path = "/resources/M-FF_iOS_HERO_Tony_Stark_Iron_Man_Civil_War.obj";
       // String path = "/resources/cube.obj";
        GameViewer game = new GameViewer(path);
        Frame frame = new MainFrame(game,700,500);
        frame.setTitle("Renovator");
    }

}
