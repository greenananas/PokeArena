package PokeArenaNetwork;

import java.util.Scanner;

public class ServeurExemple {

    public static void main(String[] args) {
        PokeArenaServer server = new PokeArenaServer("localhost", 8889);
        server.start();
        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        while (!input.equals("quit")) {
            switch (input) {
                case "ping1":
                    try {
                        server.sendPacket(server.getClient1WS(), PokeArenaUtilities.createPacket(PokeArenaPacketType.PING, null));
                    } catch (NullPointerException e) {
                        System.out.println("Pas de client 1 connecté");
                    }
                    break;
                case "ping2":
                    try {
                        server.sendPacket(server.getClient2WS(), PokeArenaUtilities.createPacket(PokeArenaPacketType.PING, null));
                    } catch (NullPointerException e) {
                        System.out.println("Pas de client 2 connecté");
                    }
                    break;
                case "texte1":
                    try {
                        server.sendPacket(server.getClient1WS(), PokeArenaUtilities.createPacket(PokeArenaPacketType.TEXT, "Message texte à destination du client 1"));
                    } catch (NullPointerException e) {
                        System.out.println("Pas de client 1 connecté");
                    }
                    break;
                case "texte2":
                    try {
                        server.sendPacket(server.getClient2WS(), PokeArenaUtilities.createPacket(PokeArenaPacketType.TEXT, "Message texte à destination du client 2"));
                    } catch (NullPointerException e) {
                        System.out.println("Pas de client 2 connecté");
                    }
                    break;
                case "state":
                    System.out.println("État du serveur : " + server.getState());
                default:
                    System.out.println("Commande non reconnue");
                    break;
            }
            input = sc.next();
        }
        try {
            server.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
