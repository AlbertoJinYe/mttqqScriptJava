package org.example;

import org.eclipse.*;
import org.eclipse.paho.client.mqttv3.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        new Thread(() -> Publicador.main(null)).start();
        new Thread(() -> Suscriptor.main(null)).start();

    }
}
