package com.aquamarine.home;

import com.jme3.app.SimpleApplication;
import com.jme3.system.AppSettings;

public class Main extends SimpleApplication {

    public static void main(String []args){
//        GameViewer gv = new GameViewer();
//        gv.start();
//        gv.stop(true);

        AppSettings settings = new AppSettings(true);
        settings.setTitle("Aquamarine");
        settings.setResizable(true);
        settings.setResolution(1080,600); //w,h
        settings.setSettingsDialogImage("Interface/late_afternoon.jpg");
        
        Main gv = new Main();
        gv.setDisplayFps(true);
        gv.setDisplayStatView(false);
        gv.setSettings(settings);
        gv.setShowSettings(false);
        gv.start();
    }

    
    @Override
    public void simpleInitApp() {

    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
