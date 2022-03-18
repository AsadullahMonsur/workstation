package com.jme.example.messages;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

@Serializable
public class TextMessages extends AbstractMessage {
    private String message;

    public TextMessages(){
    }

    public TextMessages(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
