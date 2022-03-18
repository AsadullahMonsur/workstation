package com.jme.example.server;

import com.jme.example.accessories.PlayerData;

import java.util.HashMap;

public class ServerDataManager {
    private HashMap<Integer, PlayerData>  entities = new HashMap<Integer, PlayerData>();

    public ServerDataManager() {
    }

    public void addOrRefresh(int id, PlayerData playerData) {
        entities.put(id,playerData);
    }
    public void remove(int id){
        if(entities.containsKey(id)){
          entities.remove(id);
        }
    }
    public boolean isTherePlayerWithID(int id){
        return entities.containsKey(id);
    }

    public HashMap getPlayerList(){
        return entities;
    }
}
