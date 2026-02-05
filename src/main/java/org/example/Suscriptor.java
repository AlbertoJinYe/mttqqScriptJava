package org.example;

import org.eclipse.paho.client.mqttv3.*;

import java.io.IOException;

public class Suscriptor {
    public static void main(String[] args) {
        String broker="tcp://test.mosquitto.org:1883";
        String topic="/NTT/NTTdata/IBIOL/Spain/Madrid/AlbertoJinYe";
        int QoS=1;

        try {

            MqttClient mqttClient= new MqttClient(broker,"cliente");
            MqttConnectOptions mqttOptions = new MqttConnectOptions();

            mqttOptions.setCleanSession(true);
            System.out.println("Conectando al broker soy Suscriptor: " + broker);
            mqttClient.connect(mqttOptions);


            if (mqttClient.isConnected()) {
                System.out.println("Conectado soy suscriptor");

                mqttClient.setCallback(new MqttCallback() {
                    @Override
                    public void messageArrived(String topic, MqttMessage message) throws Exception {

                        String mensajeRecibido= new String(message.getPayload());
                        System.out.println(mensajeRecibido);
                        System.out.println();

                        if (mensajeRecibido.equals("Mi primer mensaje MQTT --> Alberto Jin Ye 4")){
                            System.out.println("Desconectado suscriptor");
                            mqttClient.disconnect();
                            mqttClient.close();
                        }

                    }

                    @Override
                    public void connectionLost(Throwable cause) {
                        System.err.println("Conexión perdida");
                    }

                    @Override
                    public void deliveryComplete(IMqttDeliveryToken token) {

                    }
                });

                mqttClient.subscribe(topic, QoS);

            }
        } catch (MqttException e) {
            System.err.println("Error MQTT: " + e.getMessage());
        }
    }

}
