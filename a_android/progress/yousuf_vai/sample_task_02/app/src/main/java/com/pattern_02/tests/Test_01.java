package com.pattern_02.tests;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import static android.content.ContentValues.TAG;

public class Test_01 {

    public Test_01(Context context) {
        connection(context);
    }

    private void connection(Context context) {
        String clientId = MqttClient.generateClientId();
        MqttAndroidClient client = new MqttAndroidClient(context, "tcp://127.0.0.1:1883",
                        clientId);

        try {
            IMqttToken token = client.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    Log.d(TAG, "onSuccess");
                    notification(context,"Successful");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken,
                                                        Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Log.d(TAG, "onFailure");
                    notification(context,"Failure");
                }
            });
        }
        catch (MqttException e) {
            e.printStackTrace();
            notification(context,"error go");
        }
    }

    private void notification(Context context, String s) {
        Toast.makeText(context,s,Toast.LENGTH_LONG).show();
    }
}
