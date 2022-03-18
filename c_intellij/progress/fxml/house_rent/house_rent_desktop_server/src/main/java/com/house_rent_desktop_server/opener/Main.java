package com.house_rent_desktop_server.opener;

import com.house_rent_desktop_server.database.Database;
import com.house_rent_desktop_server.networks.ServerListener;
import com.house_rent_desktop_server.networks.ServerManager;
import com.jme3.app.SimpleApplication;
import com.jme3.network.Network;
import com.jme3.network.Server;
import com.jme3.system.JmeContext;
import com.house_rent_desktop_server.networks.NetConfiguration;

public class Main extends SimpleApplication {
    private Server server;
    private ServerManager manager;

    public static void  main(String []args){
        Database database = new Database();
        database.initialization();

        NetConfiguration.serialize();
        Main main = new Main();
        main.start(JmeContext.Type.Headless);
    }

    @Override
    public void simpleInitApp(){
        manager_initialize();
        server_initialize();
        manager.setServer(server);
    }

    private void manager_initialize() {
        manager = new ServerManager();
    }

    private void server_initialize() {
        try {
            server = Network.createServer(NetConfiguration.port);
            server.addMessageListener(new ServerListener(server,manager));
            server.start();
            System.out.println("Server started");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Server could not start");
        }
    }

    @Override
    public void simpleUpdate(float tpf) {

    }
}
