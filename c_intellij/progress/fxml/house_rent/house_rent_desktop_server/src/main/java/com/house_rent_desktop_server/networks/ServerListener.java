package com.house_rent_desktop_server.networks;

import com.house_rent_desktop_server.messages.Result;
import com.house_rent_desktop_server.messages.SignIn;
import com.jme3.network.HostedConnection;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;
import com.jme3.network.Server;

public class ServerListener implements MessageListener<HostedConnection> {
    private Server server;
    private ServerManager manager;

    public ServerListener(Server server,ServerManager manager) {
        this.server = server;
        this.manager = manager;
    }

    @Override
    public void messageReceived(HostedConnection source, Message m) {
        if(m instanceof SignIn){
            System.out.println("SignIn info taken");
            SignIn signIn = (SignIn) m;
            this.manager.set_sign_in_data(signIn.getData());
            this.manager.handle_sign_in();
        }
        if(m instanceof Result){
            Result r = (Result) m;
            System.out.println("Result taken:"+r.getResult());
        }
    }
}
