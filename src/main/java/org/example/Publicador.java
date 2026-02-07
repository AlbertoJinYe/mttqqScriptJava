package org.example;

import org.eclipse.paho.client.mqttv3.*;

public class Publicador {
    public static void main(String[] args) {

        String broker="tcp://test.mosquitto.org:1883";
        String topic="/NTT/NTTdata/IBIOL/Spain/Madrid/AlbertoJinYe";
        String message="Mi primer mensaje MQTT --> Alberto Jin Ye";

        int QoS=1;

        try {
            MqttClient mqttClient= new MqttClient(broker,"Alberto");
            MqttConnectOptions mqttOptions = new MqttConnectOptions();

            mqttOptions.setCleanSession(true);
            System.out.println("Conectando al broker soy Publicador: " + broker);
            mqttClient.connect(mqttOptions);

            if (mqttClient.isConnected()){

                System.out.println("Conectado soy publicador");

                int contador = 0;
                while (contador <5) {
                    String mensajeContado= message + " " + contador;
                    MqttMessage mqttMsg = new MqttMessage(mensajeContado.getBytes());
                    mqttMsg.setQos(QoS);

                    mqttClient.publish(topic, mqttMsg);
                    contador++;
                    Thread.sleep(1000);
                }


                mqttClient.disconnect();
                mqttClient.close();
                System.out.println("Desconectado publicador");
            }

        } catch (MqttException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
