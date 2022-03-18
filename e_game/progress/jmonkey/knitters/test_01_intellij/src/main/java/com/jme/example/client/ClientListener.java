package com.jme.example.client;

import com.jme.example.messages.CloseConnectionMessage;
import com.jme.example.messages.PlayerListMessage;
import com.jme.example.messages.TextMessages;
import com.jme3.network.Client;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;

public class ClientListener implements MessageListener<Client>{
    private final SceneManager sceneManager;
    private Client client;

    public ClientListener(Client client,SceneManager sceneManager) {
       this.client = client;
       this.sceneManager = sceneManager;
    }

    @Override
    public void messageReceived(Client source, Message m) {
        if(m instanceof TextMessages){
            TextMessages tm = (TextMessages) m;
            System.out.println("Server says: "+tm.getMessage());
        }
        else if(m instanceof PlayerListMessage){
            PlayerListMessage plm = (PlayerListMessage) m;
            sceneManager.refreshPlayerList(plm.getPlayers());
        }
        else if(m instanceof CloseConnectionMessage){
            CloseConnectionMessage ccm = (CloseConnectionMessage)m;
            sceneManager.remove(ccm.getId());
            if(ccm.getId()==source.getId()){
                client.close();
            }
        }

    }
}
