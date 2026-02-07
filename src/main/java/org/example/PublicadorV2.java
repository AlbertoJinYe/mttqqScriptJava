package org.example;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.LinkedList;
import java.util.Random;

public class PublicadorV2 {
    public static void main(String[] args) {

        Random rand = new Random();

        String broker="tcp://test.mosquitto.org:1883";
        String topic1="/NTTdata/Madrid/Nvision/microfactory/temperatura";
        String topic2="/NTTdata/Pamplona/Nvision/microfactory/humedad";
        String topic3="/NTTdata/Bilbao/Nvision/microfactory/consumo";
        String topic4="/NTTdata/Granada/Nvision/microfactory/temperatura";
        String topic5="/NTTdata/Huelva/Nvision/microfactory/humedad";

        int QoS=1;

        Sensor sensor1= new Sensor(topic1,rand.nextInt(10,31));
        Sensor sensor2= new Sensor(topic2,rand.nextInt(50,91));
        Sensor sensor3= new Sensor(topic3,rand.nextInt(250,501));
        Sensor sensor4= new Sensor(topic4,rand.nextInt(10,31));
        Sensor sensor5= new Sensor(topic5,rand.nextInt(50,91));

        LinkedList<Sensor> sensors=new LinkedList<>();
        sensors.add(sensor1);
        sensors.add(sensor2);
        sensors.add(sensor3);
        sensors.add(sensor4);
        sensors.add(sensor5);


        try {
            MqttClient mqttClient= new MqttClient(broker,"Alberto");
            MqttConnectOptions mqttOptions = new MqttConnectOptions();

            mqttOptions.setCleanSession(true);
            System.out.println("Conectando al broker soy Publicador: " + broker);
            System.out.println("======================================================");
            mqttClient.connect(mqttOptions);

            if (mqttClient.isConnected()){

                System.out.println("Conectado soy publicador");
                System.out.println("======================================================");

                for (int i = 0; i < sensors.size(); i++) {
                    Sensor sensor = sensors.get(i);

                    String mensaje=String.valueOf(sensor.getMessage());
                    MqttMessage mqttMsg = new MqttMessage(mensaje.getBytes());

                    mqttMsg.setQos(QoS);

                    mqttClient.publish(sensor.getTopic(), mqttMsg);

                    //System.out.println(sensor.getTopic() + " " + mensaje);
                   // System.out.println();
                    Thread.sleep(1000);
                }


                mqttClient.disconnect();
                mqttClient.close();
                System.out.println("Desconectado publicador");
                System.out.println("=======================================================");


            }

        } catch (MqttException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
