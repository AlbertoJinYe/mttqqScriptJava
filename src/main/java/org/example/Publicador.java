package org.example;

import org.eclipse.paho.client.mqttv3.*;

public class Publicador {
    public static void main(String[] args) {

        String broker="tcp://localhost:1883";
        String topic="/NTT/NTTdata/IBIOL/Spain/Madrid/AlbertoJinYe";
        String message="Mi primer mensaje MQTT --> Alberto Jin Ye";

        int QoS=1;

        try {
            MqttClient mqttClient= new MqttClient(broker,"cliente");
            MqttConnectOptions mqttOptions = new MqttConnectOptions();

            mqttOptions.setCleanSession(true);
            System.out.println("Conectando al broker: " + broker);
            mqttClient.connect(mqttOptions);

            if (mqttClient.isConnected()){

                System.out.println("Conectado");

                int contador = 0;
                while (contador <5) {

                    MqttMessage mqttMsg = new MqttMessage(message.getBytes());
                    mqttMsg.setQos(QoS);

                    mqttClient.publish(topic, mqttMsg);
                    contador++;
                    Thread.sleep(5000); // 5 segundos
                }
                mqttClient.disconnect();
                mqttClient.close();
                System.out.println("Desconectado");
            }

        } catch (MqttException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
