package org.example;


public class Main {
    public static void main(String[] args) {
        new Thread(() -> Publicador.main(null)).start();
        new Thread(() -> Suscriptor.main(null)).start();

    }
}
