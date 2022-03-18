package com.example.arcore_database_sample;

import com.google.ar.core.AugmentedImageDatabase;
import com.google.ar.core.Config;
import com.google.ar.core.Session;
import com.google.ar.sceneform.ux.ArFragment;

import java.io.IOException;
import java.io.InputStream;

public class Custom_AR_Fragment extends ArFragment {

    @Override
    protected Config getSessionConfiguration(Session session){
        Config config = new Config(session);
        config.setUpdateMode(Config.UpdateMode.LATEST_CAMERA_IMAGE);

        MainActivity activity = (MainActivity) getActivity();
        activity.load_db(session,config);

        this.getArSceneView().setupSession(session);
        return config;
    }


}
