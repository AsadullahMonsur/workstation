package com.jme.example.accessories;

import com.jme.example.messages.CloseConnectionMessage;
import com.jme.example.messages.PlayerDataMessage;
import com.jme.example.messages.PlayerListMessage;
import com.jme.example.messages.TextMessages;
import com.jme3.network.serializing.Serializer;

public class Utils {
    public static final String ip_address = "localhost";
    public static final int port = 8765;

    public static void initSerializers(){
        Serializer.registerClass(TextMessages.class);
        Serializer.registerClass(PlayerData.class);
        Serializer.registerClass(PlayerDataMessage.class);
        Serializer.registerClass(PlayerListMessage.class);
        Serializer.registerClass(CloseConnectionMessage.class);
    }
}
