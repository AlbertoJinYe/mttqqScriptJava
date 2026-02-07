package org.example;

import org.eclipse.paho.client.mqttv3.*;

public class Suscriptor4 {
    private static final String BROKER = "tcp://test.mosquitto.org:1883";
    private static final int QOS = 1;


    public static void main(String[] args) {

        String topic="/NTTdata/+/Nvision/microfactory/#";

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

                        String mensajeRecibido= new String(message.getPayload());
                        String ciudad=topic.split("/")[2];
                        String telemetria=topic.split("/")[5];


                        if (telemetria.equals("temperatura")) {
                            System.out.println("temperatura de la ciudad de "+ciudad+ ": "+mensajeRecibido);
                            System.out.println();
                        } else if (telemetria.equals("humedad")) {
                            System.out.println("humedad de la ciudad de "+ciudad+ ": "+mensajeRecibido);
                            System.out.println();

                        }else if (telemetria.equals("consumo")) {
                            System.out.println("consumo de la ciudad de "+ciudad+ ": "+mensajeRecibido);
                            System.out.println();
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

                mqttClient.subscribe(topic, QOS);

            }
        } catch (MqttException e) {
            System.err.println("Error MQTT: " + e.getMessage());
        }
    }
}
