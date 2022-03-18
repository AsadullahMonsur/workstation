package com.example.house_rent_and_payment.networks;

import com.house_rent_desktop_server.messages.Result;
import com.house_rent_desktop_server.messages.SignIn;
import com.jme3.network.Client;

public class ClientManager {
    private Client client;
    private int id;

    public ClientManager(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void handle_sign_in(){
        String data[] = new String[]{"a@gmail.com","@Aaa321",this.id+""};
        client.send(new SignIn(data));
    }
}
