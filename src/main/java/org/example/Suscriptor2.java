package org.example;

import org.eclipse.paho.client.mqttv3.*;

public class Suscriptor2 {
    private static final String BROKER = "tcp://test.mosquitto.org:1883";
    private static final int QOS = 1;


    public static void main(String[] args) {

        String topic="/NTTdata/+/Nvision/microfactory/temperatura";

        try {

            MqttClient mqttClient= new MqttClient(BROKER,"cliente");
            MqttConnectOptions mqttOptions = new MqttConnectOptions();

            mqttOptions.setCleanSession(true);
            System.out.println("Conectando al broker soy Suscriptor: " + BROKER);
            System.out.println("======================================================");
            mqttClient.connect(mqttOptions);


            if (mqttClient.isConnected()) {
                System.out.println("Conectado al topic: " + topic + " soy suscriptor");
                System.out.println("======================================================");

                mqttClient.setCallback(new MqttCallback() {
                    @Override
                    public void messageArrived(String topic, MqttMessage message) throws Exception {

                        String ciudad=topic.split("/")[2];
                        String mensajeRecibido= new String(message.getPayload());
                        System.out.println("Temperatura de la ciudad de "+ciudad+ ": "+mensajeRecibido);
                        System.out.println();

                    }

                    @Override
                    public void connectionLost(Throwable cause) {
                        System.err.println("Conexión perdida");
                    }

                    @Override
                    public void deliveryComplete(IMqttDeliveryToken token) {

                    }
                });

                mqttClient.subscribe(topic, QOS);



            }
        } catch (MqttException e) {
            System.err.println("Error MQTT: " + e.getMessage());
        }


    }
}
