package pokearena.network.examples;

import org.slf4j.LoggerFactory;
import pokearena.battle.Pokemon;
import pokearena.network.server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Exemple d'implémentation d'un serveur PokeArena en ligne de commande.
 *
 * @author Louis
 */
public class CLIServer {

    public static void main(String[] args) {
        var logger = LoggerFactory.getLogger(CLIServer.class);

        var server = new Server("localhost", 8887);
        server.start();

        var reader = new BufferedReader(new InputStreamReader(System.in));
        var loop = true;
        while (loop) {
            String input = null;
            try {
                input = reader.readLine();
            } catch (IOException e) {
                input = "";
                e.printStackTrace();
            }
            switch (input) {
                case "quit":
                    loop = false;
                    break;
                case "ping1":
                    try {
                        server.sendPing(server.getClient1WS());
                    } catch (NullPointerException e) {
                        logger.error("Pas de client 1 connecté");
                    }
                    break;
                case "ping2":
                    try {
                        server.sendPing(server.getClient2WS());
                    } catch (NullPointerException e) {
                        logger.error("Pas de client 2 connecté");
                    }
                    break;
                case "texte1":
                    try {
                        server.sendText(server.getClient1WS(), "Message texte à destination du client 1");
                    } catch (NullPointerException e) {
                        logger.error("Pas de client 1 connecté");
                    }
                    break;
                case "texte2":
                    try {
                        server.sendText(server.getClient2WS(), "Message texte à destination du client 2");
                    } catch (NullPointerException e) {
                        logger.error("Pas de client 2 connecté");
                    }
                    break;
                case "s":
                case "state":
                    logger.info("État du serveur : {}", server.getState());
                    break;
                case "b":
                case "battleinfo":
                    try {
                        var battle = server.getBattle();

                        StringBuilder info;
                        // Informations du client 1
                        info = new StringBuilder("=== CLIENT 1 ===\nÉquipe :\n");
                        for (Pokemon pokemon : battle.trainer1.getTrainer().getPokemonTeam().getPokemons()) {
                            info.append("- ").append(pokemon).append("\n");
                        }
                        info.append("Pokémon au combat :\n").append(battle.trainer1.getTrainer().getLeadingPkmn());
                        logger.info("{}", info);

                        // Informations du client 2
                        info = new StringBuilder("=== CLIENT 2 ===\nÉquipe :\n");
                        for (Pokemon pokemon : battle.trainer2.getTrainer().getPokemonTeam().getPokemons()) {
                            info.append("- ").append(pokemon).append("\n");
                        }
                        info.append("Pokémon au combat :\n").append(battle.trainer2.getTrainer().getLeadingPkmn());
                        logger.info("{}", info);
                    } catch (NullPointerException e) {
                        logger.error("Pas de combat en cours");
                    }
                    break;
                case "start":
                    server.startBattle();
                    break;
                default:
                    logger.error("Commande non reconnue");
                    break;
            }
        }
        try {
            server.stop();
        } catch (IOException e) {
            logger.error(e.toString());
        } catch (InterruptedException e) {
            logger.error(e.toString());
            Thread.currentThread().interrupt();
        }

    }
}
