package com.example.house_rent_and_payment.opening_appearance;
import com.example.house_rent_and_payment.networks.ClientListener;
import com.example.house_rent_and_payment.networks.ClientManager;
import com.example.house_rent_and_payment.networks.NetConfiguration;
import com.jme3.network.Client;
import com.jme3.network.ClientStateListener;
import com.jme3.network.Network;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application implements ClientStateListener {
    private Client client;
    public static ClientManager manager;
    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            client_connection_initialize();
            Pane root = FXMLLoader.load(getClass().getResource("/layouts/opening_appearance/Opening.fxml"));
            Scene scene = new Scene(root, 800, 500);
            root.prefHeightProperty().bind(scene.heightProperty());
            root.prefWidthProperty().bind(scene.widthProperty());
            primaryStage.setTitle("House Rent & Payment");
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
            System.out.println("Error in Launcher");
        }
    }

    public static void main(String[] args) {
        NetConfiguration.serialize();
        manager = new ClientManager();
        launch(args);
    }

    @Override
    public void clientConnected(Client c) {
        manager.setClient(c);
        manager.setId(c.getId());
        System.out.println("Connected");
    }

    @Override
    public void clientDisconnected(Client c, DisconnectInfo info) {

    }

    private void client_connection_initialize() {
        try {
            client = Network.connectToServer(NetConfiguration.ip_address,
                                             NetConfiguration.port);
            client.addMessageListener(new ClientListener(client,manager));
            client.addClientStateListener(this);
            client.start();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}