package org.example;

import org.eclipse.paho.client.mqttv3.*;

import java.io.IOException;

public class Suscriptor {
    public static void main(String[] args) {
        String broker="tcp://localhost:1883";
        String topic="/NTT/NTTdata/IBIOL/Spain/Madrid/AlbertoJinYe";
        int QoS=1;

        try {

            MqttClient mqttClient= new MqttClient(broker,"cliente");
            MqttConnectOptions mqttOptions = new MqttConnectOptions();

            mqttOptions.setCleanSession(true);
            System.out.println("Conectando al broker: " + broker);
            mqttClient.connect(mqttOptions);


            if (mqttClient.isConnected()) {
                System.out.println("Conectado");

                mqttClient.setCallback(new MqttCallback() {
                    @Override
                    public void messageArrived(String topic, MqttMessage message) throws Exception {

                        String mensajeRecibido=message.getPayload().toString();
                        System.out.println(mensajeRecibido);
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

                mqttClient.subscribe(topic, QoS);
                mqttClient.disconnect();

                mqttClient.close();
                System.out.println("Desconectado");
            }
        } catch (MqttException e) {
            System.err.println("Error MQTT: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error IO: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
