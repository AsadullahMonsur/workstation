package com.house_rent_desktop_server.networks;

import com.house_rent_desktop_server.messages.Result;
import com.house_rent_desktop_server.messages.SignIn;
import com.jme3.network.serializing.Serializer;

import java.security.Signature;

public class NetConfiguration {
    public static final String ip_address = "localhost";
    public static final int port = 9345;

    public static void serialize(){
        Serializer.registerClass(SignIn.class);
        Serializer.registerClass(Result.class);
    }

}
