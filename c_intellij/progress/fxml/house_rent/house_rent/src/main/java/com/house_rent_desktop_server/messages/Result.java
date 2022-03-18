package com.house_rent_desktop_server.messages;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

@Serializable
public class Result extends AbstractMessage {
    private int result;

    public Result(){
    }

    public Result(int result){
        this.result = result;
    }

    public int getResult() {
        return result;
    }
}
