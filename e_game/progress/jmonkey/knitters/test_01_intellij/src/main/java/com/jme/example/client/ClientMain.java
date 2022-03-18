package com.jme.example.client;

import com.jme.example.accessories.PlayerAppState;
import com.jme.example.accessories.PlayerData;
import com.jme.example.accessories.Utils;
import com.jme.example.messages.CloseConnectionMessage;
import com.jme.example.messages.PlayerDataMessage;
import com.jme.example.messages.TextMessages;
import com.jme.example.server.ServerMain;
import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.network.Client;
import com.jme3.network.ClientStateListener;
import com.jme3.network.Network;
import com.jme3.system.JmeContext;

import java.io.IOException;

public class ClientMain extends SimpleApplication implements ClientStateListener {
    private Client client;
    private PlayerAppState playerAppState;
    private BulletAppState bulletAppState;
    private PlayerData playerData;
    private SceneManager sceneManager;

    public static void  main(String []args){
        Utils.initSerializers();
        ClientMain cm = new ClientMain();
        cm.setPauseOnLostFocus(false);
        cm.start(JmeContext.Type.Display);
    }

    @Override
    public void simpleInitApp() {
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        bulletAppState.setDebugEnabled(true);

        flyCam.setMoveSpeed(30);
        flyCam.setDragToRotate(true);

        sceneManager = new SceneManager(this,assetManager,bulletAppState);

        initClient();
        initPlayerData();
        initAppState();
        initScene();
    }

    @Override
    public void simpleUpdate(float tpf) {
         if(client.isConnected()){
             client.send(new PlayerDataMessage(playerData));
         }
    }



    private void initClient() {
        try {
            client = Network.connectToServer(Utils.ip_address,Utils.port);
            client.addMessageListener(new ClientListener(client,sceneManager));
            client.addClientStateListener(this);
            client.start();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clientConnected(Client c) {
        playerData.setId(c.getId());
        sceneManager.setUser_id(c.getId());
        client.send(new TextMessages("Client first connect "+c.getId()));
    }

    @Override
    public void clientDisconnected(Client c, DisconnectInfo info) {
        client.send(new TextMessages("Client disconnected"));
    }
    private void initAppState() {
       playerAppState = new PlayerAppState(bulletAppState,rootNode,playerData);
       stateManager.attach(playerAppState);
    }

    private void initScene(){

        AmbientLight light = new AmbientLight();
        light.setColor(ColorRGBA.White);
        rootNode.addLight(light);

        DirectionalLight sun = new DirectionalLight();
        sun.setColor(ColorRGBA.White);
        sun.setDirection(new Vector3f(-0.5f,-0.5f,-0.5f).normalizeLocal());
        rootNode.addLight(sun);
    }

    private void initPlayerData(){
        this.playerData = new PlayerData();
    }

    @Override
    public void destroy() {
        bulletAppState.cleanup();
        client.send(new CloseConnectionMessage(playerData.getId()));
        super.destroy();
    }
}
