package com.house_rent_desktop_server.networks;

import com.house_rent_desktop_server.controller.SignInController;
import com.house_rent_desktop_server.messages.Result;
import com.house_rent_desktop_server.messages.SignIn;
import com.jme3.network.Filters;
import com.jme3.network.Server;

public class ServerManager {
    private String []sign_in_data;
    private int result;
    private Server server;

    public ServerManager(){

    }

    public void set_sign_in_data(String []sign_in_data){
        this.sign_in_data = sign_in_data;
    }

    public String[] getSign_in_data(){
        return sign_in_data;
    }

    public void result(int result){
      this.result = result;
    }

    public int getResult() {
        return result;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public void handle_sign_in(){
        try {
            SignInController controller = new SignInController(this.sign_in_data,server);
        }
        catch (Exception e){
          System.out.println("Server could not handle sign in: \n"+e.getMessage());
        }
    }

}
