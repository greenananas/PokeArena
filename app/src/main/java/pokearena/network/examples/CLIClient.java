package pokearena.network.examples;

import pokearena.battle.*;
import pokearena.network.client.Client;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Exemple d'implémentation d'un client PokeArena en ligne de commande
 *
 * @author Louis
 */
public class CLIClient {

    public static void main(String[] args) {

        // Initialisation d'objets pour l'exemple
        var dracocharge = new Move(1, "Dracocharge", PokeTypes.type.DRAGON, true, 100, 70, 16, 0, 0);
        var dracogriffe = new Move(2, "Dracogriffe", PokeTypes.type.DRAGON, true, 80, 100, 24, 0, 0);
        var seisme = new Move(3, "Séisme", PokeTypes.type.GROUND, true, 100, 100, 16, 0, 0);
        var lanceflammes = new Move(4, "Lance-Flammes", PokeTypes.type.FIRE, false, 90, 100, 24, 0, 0);

        var poingmeteor = new Move(4, "Poing Météore", PokeTypes.type.STEEL, true, 90, 90, 16, 0, 0);
        var poingglace = new Move(5, "Poing Glace", PokeTypes.type.ICE, true, 75, 100, 24, 0, 0);
        var poingeclair = new Move(6, "Poing Éclair", PokeTypes.type.ELECTRIC, true, 75, 100, 24, 0, 0);

        var lamederock = new Move(7, "Lame de Roc", PokeTypes.type.ROCK, true, 100, 80, 8, 0, 0);
        var poingfeu = new Move(8, "Poing Feu", PokeTypes.type.FIRE, true, 75, 100, 24, 0, 0);
        var machouille = new Move(9, "Machouille", PokeTypes.type.DARK, true, 80, 100, 24, 0, 0);

        var lamedair = new Move(10, "Lame d'Air", PokeTypes.type.FLYING, false, 75, 95, 24, 0, 0);
        var eclatmagique = new Move(11, "Éclat Magique", PokeTypes.type.FAIRY, false, 80, 100, 16, 0, 0);
        var ballombre = new Move(12, "Ball'Ombre", PokeTypes.type.GHOST, false, 80, 100, 24, 0, 0);

        var surf = new Move(13, "Surf", PokeTypes.type.WATER, false, 95, 100, 24, 0, 0);
        var hydrocanon = new Move(14, "Hydrocanon", PokeTypes.type.WATER, false, 120, 80, 8, 0, 0);
        var luminocanon = new Move(15, "Luminocanon", PokeTypes.type.STEEL, false, 80, 100, 16, 0, 0);
        var laserglace = new Move(16, "Laser Glace", PokeTypes.type.ICE, false, 95, 100, 16, 0, 0);

        var nature = "HARDY";
        var carchacrok = new Pokemon("Carchacrok", PokeTypes.type.DRAGON, PokeTypes.type.GROUND, 50, 108, 130, 95, 80, 85, 102, 0, 0, 0, 0, 0, 0, nature, dracocharge, dracogriffe, seisme, lanceflammes, 445);
        var metalosse = new Pokemon("Metalosse", PokeTypes.type.STEEL, PokeTypes.type.PSYCHIC, 50, 80, 135, 130, 95, 90, 70, 0, 0, 0, 0, 0, 0, nature, poingmeteor, seisme, poingglace, poingeclair, 376);
        var tyranocif = new Pokemon("Tyranocif", PokeTypes.type.ROCK, PokeTypes.type.DARK, 50, 100, 134, 110, 95, 100, 61, 0, 0, 0, 0, 0, 0, nature, lamederock, poingfeu, seisme, machouille, 248);
        var togekiss = new Pokemon("Togekiss", PokeTypes.type.FAIRY, PokeTypes.type.FLYING, 50, 85, 50, 95, 120, 115, 80, 0, 0, 0, 0, 0, 0, nature, lamedair, eclatmagique, ballombre, lanceflammes, 468);
        var elekable = new Pokemon("Elekable", PokeTypes.type.ELECTRIC, null, 50, 75, 123, 67, 95, 85, 95, 0, 0, 0, 0, 0, 0, nature, poingeclair, poingfeu, poingglace, seisme, 446);
        var pingoleon = new Pokemon("Pingoléon", PokeTypes.type.WATER, PokeTypes.type.STEEL, 50, 84, 86, 88, 111, 101, 60, 0, 0, 0, 0, 0, 0, nature, surf, hydrocanon, luminocanon, laserglace, 395);

        Client client = null;

        try {
            client = new Client("localhost", 8887);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        if (client != null) {
            client.connect();
            var sc = new Scanner(System.in);
            String input = sc.next();

            ArrayList<Pokemon> alTeam1 = new ArrayList<>();
            alTeam1.add(carchacrok);
            alTeam1.add(metalosse);
            alTeam1.add(pingoleon);
            var team1 = new Team(alTeam1);

            ArrayList<Pokemon> alTeam2 = new ArrayList<>();
            alTeam2.add(tyranocif);
            alTeam2.add(togekiss);
            alTeam2.add(elekable);
            var team2 = new Team(alTeam2);

            ArrayList<Pokemon> alSoloTeam1 = new ArrayList<>();
            alSoloTeam1.add(elekable);
            var soloTeam1 = new Team(alSoloTeam1);

            ArrayList<Pokemon> alSoloTeam2 = new ArrayList<>();
            alSoloTeam2.add(pingoleon);
            var soloTeam2 = new Team(alSoloTeam2);

            while (!input.equals("quit")) {
                try {
                    switch (input) {
                        case "ping":
                            client.sendPing();
                            break;
                        case "refresh":
                            client.sendRefresh();
                            break;
                        case "m1":
                        case "move1":
                            client.sendMove(client.getTrainer().getLeadingPkmn().getMove1());
                            break;
                        case "m2":
                        case "move2":
                            client.sendMove(client.getTrainer().getLeadingPkmn().getMove2());
                            break;
                        case "m3":
                        case "move3":
                            client.sendMove(client.getTrainer().getLeadingPkmn().getMove3());
                            break;
                        case "m4":
                        case "move4":
                            client.sendMove(client.getTrainer().getLeadingPkmn().getMove4());
                            break;
                        case "moves":
                            System.out.println("Attaques disponibles :");
                            for (Move m : client.getTrainer().getLeadingPkmn().getMove()) {
                                System.out.println(" - " + m);
                            }
                            break;
                        case "team1":
                            client.sendTeam(team1);
                            break;
                        case "team2":
                            client.sendTeam(team2);
                            break;
                        case "st1":
                            client.sendTeam(soloTeam1);
                            break;
                        case "st2":
                            client.sendTeam(soloTeam2);
                            break;
                        case "cp1":
                        case "changepkmn1":
                            client.sendChangePkmn(new ChangePkmn(0));
                            break;
                        case "cp2":
                        case "changepkmn2":
                            client.sendChangePkmn(new ChangePkmn(1));
                            break;
                        case "cp3":
                        case "changepkmn3":
                            client.sendChangePkmn(new ChangePkmn(2));
                            break;
                        case "cp4":
                        case "changepkmn4":
                            client.sendChangePkmn(new ChangePkmn(3));
                            break;
                        case "cp5":
                        case "changepkmn5":
                            client.sendChangePkmn(new ChangePkmn(4));
                            break;
                        case "cp6":
                        case "changepkmn6":
                            client.sendChangePkmn(new ChangePkmn(5));
                            break;
                        case "b":
                        case "battleinfo":
                            var trainer = client.getTrainer();
                            System.out.println("Mes pokémons : ");
                            for (Pokemon pokemon : trainer.getPokemonTeam().getPokemons()) {
                                System.out.println(" - " + pokemon);
                            }
                            System.out.println("Pokémon adverse :");
                            System.out.println(client.getOpponentPokemon());
                            System.out.println("Dernière attaque de l'adversaire :");
                            System.out.println(client.getOpponentMove());
                            break;
                        case "texte":
                            client.sendText("Ceci est un message texte");
                            break;
                        case "s":
                        case "state":
                            System.out.println("État du client : " + client.getState());
                            break;
                        case "ff":
                            client.sendForfeit();
                            break;
                        default:
                            System.out.println("Commande non reconnue");
                            break;
                    }
                } catch (NullPointerException e) {
                    System.out.println("Le dresseur ou le pokemon adverse n'a pas été initialisé");
                }
                input = sc.next();
            }
            client.close();
        }
    }
}
