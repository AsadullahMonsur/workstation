package com.jme.example.server;

import com.jme.example.accessories.PlayerData;
import com.jme.example.messages.CloseConnectionMessage;
import com.jme.example.messages.PlayerDataMessage;
import com.jme.example.messages.TextMessages;
import com.jme3.network.*;

public class ServerListener implements MessageListener<HostedConnection> {
    private Server server;
    private ServerDataManager sdm;

    public ServerListener(Server server,ServerDataManager sdm) {
        this.server = server;
        this.sdm = sdm;
    }

    @Override
    public void messageReceived(HostedConnection source, Message m) {
        if(m instanceof TextMessages){
            String message = ((TextMessages)m).getMessage();
            System.out.println("Received: "+message+ "\n sent from: "+source.getId());
            server.broadcast(Filters.notEqualTo(source),new TextMessages("Client : "+source.getId()+
                     " wrote : "+message));
        }
        else if(m instanceof PlayerDataMessage){
            PlayerDataMessage pdm = (PlayerDataMessage) m;
            PlayerData pd = pdm.getPlayerData();
            sdm.addOrRefresh(pd.getId(),pd);
        }
        else if(m instanceof CloseConnectionMessage){
            CloseConnectionMessage ccm = (CloseConnectionMessage)m;
            sdm.remove(ccm.getId());
            System.out.println("Player Removed");
            server.broadcast(new CloseConnectionMessage(ccm.getId()));
        }
    }
}
