package PokeArenaNetwork;

import Model.Move;
import Model.PokeTypes;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class ClientExemple {

    public static void main(String[] args) {
        PokeArenaClient client;
        try {
            client = new PokeArenaClient("localhost", 8889);
            client.connect();

            Scanner sc = new Scanner(System.in);
            String input = sc.next();


            while (!input.equals("quit")) {
                switch (input) {
                    case "ping":
                        client.sendPing();
                        break;
                    case "refresh":
                        client.sendRefresh();
                        break;
                    case "move":
                        client.sendMove(new Move("Dracogriffe", PokeTypes.type.DRAGON, true, 80, 100, 24, 0, 0));
                        break;
                    case "texte":
                        client.sendText("Ceci est un message texte");
                        break;
                    case "state":
                        System.out.println("État du client : " + client.getState());
                        break;
                    default:
                        System.out.println("Commande non reconnue");
                        break;
                }
                input = sc.next();
            }
            client.close();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
