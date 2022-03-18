package com.jme.example.messages;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

import java.util.HashMap;

@Serializable
public class PlayerListMessage extends AbstractMessage {
    private HashMap  players;

    public PlayerListMessage(){

    }

    public PlayerListMessage(HashMap players){
        this.players = players;
    }

    public HashMap getPlayers() {
        return players;
    }
}
