package com.jme.example.client;

import com.jme.example.accessories.PlayerData;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

public class SceneManager {
    private HashMap<Integer, PlayerData> players = new HashMap<Integer, PlayerData>();
    private HashMap<Integer, Spatial> entities = new HashMap<Integer, Spatial>();

    private int user_id;
    private AssetManager assetManager;
    private BulletAppState bulletAppState;

    private ClientMain clientMain;
    public SceneManager(ClientMain clientMain,AssetManager assetManager
                            ,BulletAppState bulletAppState) {
        this.clientMain = clientMain;
        this.assetManager = assetManager;
        this.bulletAppState = bulletAppState;
    }
    public void addOrRefresh(int id, PlayerData playerData){
        players.put(id,playerData);
    }
    public void remove(final int id){
        if(players.containsKey(id)) {
            players.remove(id);
        }
        if (entities.containsKey(id)) {
            clientMain.enqueue(new Callable() {
                 public Object call() throws Exception{
                     clientMain.getRootNode().detachChild(entities.get(id));
                     bulletAppState.getPhysicsSpace().remove(entities.get(id));
                     entities.remove(id);
                     return null;
                 }
            });
        }
    }
    public boolean isTherePlayerWithID(int id){
        return players.containsKey(id);
    }

    public HashMap getPlayerList(){
        return players;
    }

    public void refreshPlayerList(HashMap players){
        this.players.putAll(players);
        updateAllPlayerEntities();
    }

    private void updateAllPlayerEntities() {
       // System.out.println(players.size());
       Set<Map.Entry<Integer, PlayerData>> set = players.entrySet();

        for (Map.Entry<Integer, PlayerData> entry : set) {
            updatePlayerEntity(entry.getKey(), entry.getValue());
            //System.out.println(12345);
        }
    }

    private void updatePlayerEntity(final Integer id,final PlayerData data) {
        if(user_id!=id){
            if(!entities.containsKey(id)){
                loadAndAddPlayerToRootNode(id,data);
                return;
            }
            clientMain.enqueue(new Callable() {
                 public Object call() throws Exception{
                    entities.get(id).getControl(BetterCharacterControl.class)
                            .warp(data.getLocation());
                    entities.get(id).setLocalRotation(data.getRotation());
                    return null;
                 }
            });
        }
    }

    private void loadAndAddPlayerToRootNode(Integer id, PlayerData data) {
        entities.put(id, loadSpatial(data.getLocation(),data.getRotation()));
        BetterCharacterControl control = new BetterCharacterControl(1.5f,9,40);
        entities.get(id).addControl(control);

        control.setGravity(new Vector3f(0,40,0));
        control.warp(data.getLocation());

        clientMain.enqueue(new Callable() {
            public Object call() throws Exception{
                clientMain.getRootNode().attachChild(entities.get(id));
                bulletAppState.getPhysicsSpace().addAll(entities.get(id));
                return null;
            }
        });
    }

    private Spatial loadSpatial(Vector3f location, Quaternion rotation) {
         Spatial spatial = assetManager.loadModel("Models/M-FF_iOS_HERO_Tony_Stark_Iron_Man_Civil_War.obj");
         spatial.setLocalTranslation(location);
         spatial.setLocalRotation(rotation);
         return spatial;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
