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
            client = new PokeArenaClient("localhost", 8888);
            client.connect();

            Scanner sc = new Scanner(System.in);
            String input = sc.next();


            while (!input.equals("quit")) {
                if (input.equals("ping")) client.sendPing();
                if (input.equals("refresh")) client.sendRefresh();
                if (input.equals("move"))
                    client.sendMove(new Move("Dracocharge", PokeTypes.type.DRAGON, true, 100, 70, 16, 0));
                if (input.equals("texte")) client.sendText("Ceci est un message texte");
                input = sc.next();
            }
            client.close();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
