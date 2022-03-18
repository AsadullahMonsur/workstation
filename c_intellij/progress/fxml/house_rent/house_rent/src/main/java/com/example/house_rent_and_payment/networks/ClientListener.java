package com.example.house_rent_and_payment.networks;

import com.example.house_rent_and_payment.opening_appearance.Main;
import com.house_rent_desktop_server.messages.Result;
import com.jme3.network.Client;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;

public class ClientListener implements MessageListener<Client> {
    private Client client;
    private final ClientManager manager;

    public ClientListener(Client client, ClientManager manager) {
        this.client = client;
        this.manager = manager;
    }

    @Override
    public void messageReceived(Client source, Message m) {
        System.out.println("Received message for client: "+source.getId());

        if(m instanceof Result){
            Result result = (Result) m;
            if(result.getResult()==1){
                System.out.println("Alhamdulillah-> result fetched");
            }
            else {
                System.out.println("Insha-Allah-> next time");
            }
        }
        else {
            System.out.println("Message does not belong to categories");
        }
    }

}
