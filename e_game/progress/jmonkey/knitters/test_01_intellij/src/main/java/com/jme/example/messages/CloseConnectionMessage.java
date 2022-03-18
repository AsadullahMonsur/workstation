package com.jme.example.messages;

import com.jme.example.accessories.PlayerData;
import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

@Serializable
public class CloseConnectionMessage extends AbstractMessage {
    private int id;

    public CloseConnectionMessage(){
    }

    public CloseConnectionMessage(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
