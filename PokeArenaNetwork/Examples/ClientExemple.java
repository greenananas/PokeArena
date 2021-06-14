package PokeArenaNetwork.Examples;

import Model.*;
import PokeArenaNetwork.Client.PokeArenaClient;

import java.net.URISyntaxException;
import java.util.Scanner;

/**
 * Exemple d'implémentation d'un client PokeArena en ligne de commande.
 *
 * @author Louis
 */
public class ClientExemple {

    public static void main(String[] args) {
        PokeArenaClient client;
        try {
            client = new PokeArenaClient("localhost", 8887);
            client.connect();
            Scanner sc = new Scanner(System.in);
            String input = sc.next();

            // Initialisation d'objets pour l'exemple
            Move dracocharge = new Move(1,"Dracocharge", PokeTypes.type.DRAGON, true, 100, 70, 16, 0, 0);
            Move dracogriffe = new Move(2,"Dracogriffe", PokeTypes.type.DRAGON, true, 80, 100, 24, 0, 0);
            Move seisme = new Move(3,"Séisme", PokeTypes.type.GROUND, true, 100, 100, 16, 0, 0);
            Move lanceflammes = new Move(4,"Lance-Flammes", PokeTypes.type.FIRE, false, 90, 100, 24, 0, 0);

            Move poingmeteor = new Move(4,"Poing Météore", PokeTypes.type.STEEL, true, 90, 90, 16, 0, 0);
            Move poingglace = new Move(5,"Poing Glace", PokeTypes.type.ICE, true, 75, 100, 24, 0, 0);
            Move poingeclair = new Move(6,"Poing Éclair", PokeTypes.type.ELECTRIC, true, 75, 100, 24, 0, 0);

            Move lamederock = new Move(7,"Lame de Roc", PokeTypes.type.ROCK, true, 100, 80, 8, 0, 0);
            Move poingfeu = new Move(8,"Poing Feu", PokeTypes.type.FIRE, true, 75, 100, 24, 0, 0);
            Move machouille = new Move(9,"Machouille", PokeTypes.type.DARK, true, 80, 100, 24, 0, 0);

            Move lamedair = new Move(10,"Lame d'Air", PokeTypes.type.FLYING, false, 75, 95, 24, 0, 0);
            Move eclatmagique = new Move(11,"Éclat Magique", PokeTypes.type.FAIRY, false, 80, 100, 16, 0, 0);
            Move ballombre = new Move(12,"Ball'Ombre", PokeTypes.type.GHOST, false, 80, 100, 24, 0, 0);

            Move surf = new Move(13,"Surf", PokeTypes.type.WATER, false, 95, 100, 24, 0, 0);
            Move hydrocanon = new Move(14,"Hydrocanon", PokeTypes.type.WATER, false, 120, 80, 8, 0, 0);
            Move luminocanon = new Move(15,"Luminocanon", PokeTypes.type.STEEL, false, 80, 100, 16, 0, 0);
            Move laserglace = new Move(16,"Laser Glace", PokeTypes.type.ICE, false, 95, 100, 16, 0, 0);

            Pokemon carchacrok = new Pokemon("Carchacrok", PokeTypes.type.DRAGON, PokeTypes.type.GROUND, 50, 108, 130, 95, 80, 85, 102,0,0,0,0,0,0,"HARDY", dracocharge, dracogriffe, seisme, lanceflammes);
            Pokemon metalosse = new Pokemon("Metalosse", PokeTypes.type.STEEL, PokeTypes.type.PSYCHIC, 50, 80, 135, 130, 95, 90, 70,0,0,0,0,0,0,"HARDY", poingmeteor, seisme, poingglace, poingeclair);
            Pokemon tyranocif = new Pokemon("Tyranocif", PokeTypes.type.ROCK, PokeTypes.type.DARK, 50, 100, 134, 110, 95, 100, 61,0,0,0,0,0,0,"HARDY", lamederock, poingfeu, seisme, machouille);
            Pokemon togekiss = new Pokemon("Togekiss", PokeTypes.type.FAIRY, PokeTypes.type.FLYING, 50, 85, 50, 95, 120, 115, 80,0,0,0,0,0,0,"HARDY", lamedair, eclatmagique, ballombre, lanceflammes);
            Pokemon elekable = new Pokemon("Elekable", PokeTypes.type.ELECTRIC, null, 50, 75, 123, 67, 95, 85, 95,0,0,0,0,0,0,"HARDY", poingeclair, poingfeu, poingglace, seisme);
            Pokemon pingoleon = new Pokemon("Pingoléon", PokeTypes.type.WATER, PokeTypes.type.STEEL, 50, 84, 86, 88, 111, 101, 60,0,0,0,0,0,0,"HARDY", surf, hydrocanon, luminocanon, laserglace);

            Pokemon carchacrok1 = new Pokemon("Carchacrok", PokeTypes.type.DRAGON, PokeTypes.type.GROUND, 50, 108, 130, 95, 80, 85, 102,0,0,0,0,0,0,"HARDY", dracocharge, dracogriffe, seisme, lanceflammes);
            Pokemon metalosse1 = new Pokemon("Metalosse", PokeTypes.type.STEEL, PokeTypes.type.PSYCHIC, 50, 80, 135, 130, 95, 90, 70,0,0,0,0,0,0,"HARDY", poingmeteor, seisme, poingglace, poingeclair);
            Pokemon tyranocif1 = new Pokemon("Tyranocif", PokeTypes.type.ROCK, PokeTypes.type.DARK, 50, 100, 134, 110, 95, 100, 61,0,0,0,0,0,0,"HARDY", lamederock, poingfeu, seisme, machouille);
            Pokemon togekiss1 = new Pokemon("Togekiss", PokeTypes.type.FAIRY, PokeTypes.type.FLYING, 50, 85, 50, 95, 120, 115, 80,0,0,0,0,0,0,"HARDY", lamedair, eclatmagique, ballombre, lanceflammes);
            Pokemon elekable1 = new Pokemon("Elekable", PokeTypes.type.ELECTRIC, null, 50, 75, 123, 67, 95, 85, 95,0,0,0,0,0,0,"HARDY", poingeclair, poingfeu, poingglace, seisme);
            Pokemon pingoleon1 = new Pokemon("Pingoléon", PokeTypes.type.WATER, PokeTypes.type.STEEL, 50, 84, 86, 88, 111, 101, 60,0,0,0,0,0,0,"HARDY", surf, hydrocanon, luminocanon, laserglace);

            Team team1 = new Team();
            team1.addPokemon(carchacrok);
            team1.addPokemon(metalosse);
            team1.addPokemon(pingoleon);

            Team team2 = new Team();
            team2.addPokemon(tyranocif);
            team2.addPokemon(togekiss);
            team2.addPokemon(elekable);

            Team soloTeam1 = new Team();
            soloTeam1.addPokemon(elekable);

            Team soloTeam2 = new Team();
            soloTeam2.addPokemon(pingoleon);

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
                            Trainer trainer = client.getTrainer();
                            System.out.println("Mes pokémons : ");
                            for (Pokemon pokemon : trainer.getPokemonTeam().getPokemons()) {
                                System.out.println(" - " + pokemon);
                            }
                            System.out.println("Pokémon adverse :");
                            System.out.println(client.getOpponentPokemon());
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
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
