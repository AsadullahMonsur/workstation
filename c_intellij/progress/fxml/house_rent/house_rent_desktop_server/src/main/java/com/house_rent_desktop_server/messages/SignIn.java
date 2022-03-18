package com.house_rent_desktop_server.messages;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

@Serializable
public class SignIn extends AbstractMessage {
    private String []data;

    public SignIn(){
    }

    public SignIn(String []data){
       this.data = data;
    }

    public String[] getData(){
        return data;
    }
}
