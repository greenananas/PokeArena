package PokeArenaNetwork;

import Model.Battle;
import Model.Pokemon;

import java.util.Scanner;

public class ServeurExemple {

    public static void main(String[] args) {
        PokeArenaServer server = new PokeArenaServer("localhost", 8888);
        server.start();
        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        while (!input.equals("quit")) {
            switch (input) {
                case "ping1":
                    try {
                        server.sendPing(server.getClient1WS());
                    } catch (NullPointerException e) {
                        System.out.println("Pas de client 1 connecté");
                    }
                    break;
                case "ping2":
                    try {
                        server.sendPing(server.getClient2WS());
                    } catch (NullPointerException e) {
                        System.out.println("Pas de client 2 connecté");
                    }
                    break;
                case "texte1":
                    try {
                        server.sendText(server.getClient1WS(), "Message texte à destination du client 1");
                    } catch (NullPointerException e) {
                        System.out.println("Pas de client 1 connecté");
                    }
                    break;
                case "texte2":
                    try {
                        server.sendText(server.getClient2WS(), "Message texte à destination du client 2");
                    } catch (NullPointerException e) {
                        System.out.println("Pas de client 2 connecté");
                    }
                    break;
                case "state":
                    System.out.println("État du serveur : " + server.getState());
                    break;
                case "battleinfo":
                    try {
                        Battle battle = server.getBattle();

                        // Informations du client 1
                        System.out.println("=== CLIENT 1 ===");
                        System.out.println("Équipe :");
                        for (Pokemon pokemon : battle.trainer1.getTrainer().getPokemonTeam().getPokemons()) {
                            System.out.println(" - " + pokemon);
                        }
                        System.out.println("Pokémon au combat :");
                        System.out.println(battle.trainer1.getTrainer().getLeadingPkmn());

                        // Informations du client 2
                        System.out.println("=== CLIENT 2 ===");
                        System.out.println("Équipe :");
                        for (Pokemon pokemon : battle.trainer2.getTrainer().getPokemonTeam().getPokemons()) {
                            System.out.println(" - " + pokemon);
                        }
                        System.out.println("Pokémon au combat :");
                        System.out.println(battle.trainer2.getTrainer().getLeadingPkmn());
                    } catch (NullPointerException e) {
                        System.out.println("NullPointerException");
                    }
                    break;
                case "start":
                    server.startBattle();
                    break;
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
