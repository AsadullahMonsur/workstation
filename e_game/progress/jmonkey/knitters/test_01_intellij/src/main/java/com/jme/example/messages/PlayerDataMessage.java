package com.jme.example.messages;

import com.jme.example.accessories.PlayerData;
import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

@Serializable
public class PlayerDataMessage extends AbstractMessage {
    private PlayerData playerData;

    public PlayerDataMessage(){

    }
    public PlayerDataMessage(PlayerData playerData){
        this.playerData = playerData;
    }

    public PlayerData getPlayerData() {
        return playerData;
    }
}
