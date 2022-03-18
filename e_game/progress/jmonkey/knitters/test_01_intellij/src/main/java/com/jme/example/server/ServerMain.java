package com.jme.example.server;

import com.jme.example.accessories.Utils;
import com.jme.example.messages.PlayerDataMessage;
import com.jme.example.messages.PlayerListMessage;
import com.jme3.app.SimpleApplication;
import com.jme3.network.Network;
import com.jme3.network.Server;
import com.jme3.system.JmeContext;

import java.io.IOException;

public class ServerMain extends SimpleApplication {
    private Server server;
    private ServerDataManager sdm;

    public static void  main(String []args){
        Utils.initSerializers();
        ServerMain sm = new ServerMain();
        sm.start(JmeContext.Type.Headless);
    }
    @Override
    public void simpleInitApp() {
        initDataManager();
        initServer();
    }

    private void initDataManager() {
        sdm = new ServerDataManager();
    }

    private void initServer() {
        try {
            server = Network.createServer(Utils.port);
            server.addMessageListener(new ServerListener(server,sdm));
            server.start();
            System.out.println("Server started");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Server could not start");
        }
    }

    @Override
    public void simpleUpdate(float tpf) {
        server.broadcast(new PlayerListMessage(sdm.getPlayerList()));
        System.out.println(sdm.getPlayerList().size());
    }
}
