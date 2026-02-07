package org.example;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //new Thread(() -> Publicador.main(null)).start();
        //new Thread(() -> Suscriptor.main(null)).start();

        System.out.println("Selecciona el tipo de suscripción:");
        System.out.println("1 - Temperatura Madrid (específico)");
        System.out.println("2 - Temperatura TODAS las ciudades (+)");
        System.out.println("3 - Humedad TODAS las ciudades (+)");
        System.out.println("4 - TODO de Microfactory (#)");
        System.out.print("\nElige opción (1-5): ");

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        switch (n) {
            case 1:
                Suscriptor1.main(null);
                break;
            case 2:
                Suscriptor2.main(null);
                break;
            case 3:
                Suscriptor3.main(null);
                break;
            case 4:
                Suscriptor4.main(null);
                break;
            default:
                break;
        }
        PublicadorV2.main(null);

    }
}
