package PokeArenaNetwork;

import Model.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class ClientExemple {

    public static void main(String[] args) {
        PokeArenaClient client;
        try {
            client = new PokeArenaClient("localhost", 8887);
            client.connect();
            Scanner sc = new Scanner(System.in);
            String input = sc.next();

            // Initialisation d'objets pour l'exemple
            Move dracocharge = new Move("Dracocharge", PokeTypes.type.DRAGON, true, 100, 70, 16, 0, 0);
            Move dracogriffe = new Move("Dracogriffe", PokeTypes.type.DRAGON, true, 80, 100, 24, 0, 0);
            Move seisme = new Move("Séisme", PokeTypes.type.GROUND, true, 100, 100, 16, 0, 0);
            Move lanceflammes = new Move("Lance-Flammes", PokeTypes.type.FIRE, false, 90, 100, 24, 0, 0);

            Move poingmeteor = new Move("Poing Météore", PokeTypes.type.STEEL, true, 90, 90, 16, 0, 0);
            Move poingglace = new Move("Poing Glace", PokeTypes.type.ICE, true, 75, 100, 24, 0, 0);
            Move poingeclair = new Move("Poing Éclair", PokeTypes.type.ELECTRIC, true, 75, 100, 24, 0, 0);

            Move lamederock = new Move("Lame de Roc", PokeTypes.type.ROCK, true, 100, 80, 8, 0, 0);
            Move poingfeu = new Move("Poing Feu", PokeTypes.type.FIRE, true, 75, 100, 24, 0, 0);
            Move machouille = new Move("Machouille", PokeTypes.type.DARK, true, 80, 100, 24, 0, 0);

            Move lamedair = new Move("Lame d'Air", PokeTypes.type.FLYING, false, 75, 95, 24, 0, 0);
            Move eclatmagique = new Move("Éclat Magique", PokeTypes.type.FAIRY, false, 80, 100, 16, 0, 0);
            Move ballombre = new Move("Ball'Ombre", PokeTypes.type.GHOST, false, 80, 100, 24, 0, 0);

            Move surf = new Move("Surf", PokeTypes.type.WATER, false, 95, 100, 24, 0, 0);
            Move hydrocanon = new Move("Hydrocanon", PokeTypes.type.WATER, false, 120, 80, 8, 0, 0);
            Move luminocanon = new Move("Luminocanon", PokeTypes.type.STEEL, false, 80, 100, 16, 0, 0);
            Move laserglace = new Move("Laser Glace", PokeTypes.type.ICE, false, 95, 100, 16, 0, 0);

            Pokemon carchacrok = new Pokemon("Carchacrok", PokeTypes.type.DRAGON, PokeTypes.type.GROUND, 50, 108, 130, 95, 80, 85, 102, 0, 0, 0, 0, 0, 0, "SERIOUS", dracocharge, dracogriffe, seisme, lanceflammes);
            Pokemon metalosse = new Pokemon("Metalosse", PokeTypes.type.STEEL, PokeTypes.type.PSYCHIC, 50, 80, 135, 130, 95, 90, 70, 0, 0, 0, 0, 0, 0, "SERIOUS", poingmeteor, seisme, poingglace, poingeclair);
            Pokemon tyranocif = new Pokemon("Tyranocif", PokeTypes.type.ROCK, PokeTypes.type.DARK, 50, 100, 134, 110, 95, 100, 61, 0, 0, 0, 0, 0, 0, "SERIOUS", lamederock, poingfeu, seisme, machouille);
            Pokemon togekiss = new Pokemon("Togekiss", PokeTypes.type.FAIRY, PokeTypes.type.FLYING, 50, 85, 50, 95, 120, 115, 80, 0, 0, 0, 0, 0, 0, "SERIOUS", lamedair, eclatmagique, ballombre, lanceflammes);
            Pokemon elekable = new Pokemon("Elekable", PokeTypes.type.ELECTRIC, null, 50, 75, 123, 67, 95, 85, 95, 0, 0, 0, 0, 0, 0, "SERIOUS", poingeclair, poingfeu, poingglace, seisme);
            Pokemon pingoleon = new Pokemon("Pingoléon", PokeTypes.type.WATER, PokeTypes.type.STEEL, 50, 84, 86, 88, 111, 101, 60, 0, 0, 0, 0, 0, 0, "SERIOUS", surf, hydrocanon, luminocanon, laserglace);

            Pokemon carchacrok1 = new Pokemon("Carchacrok", PokeTypes.type.DRAGON, PokeTypes.type.GROUND, 50, 108, 130, 95, 80, 85, 102, 0, 0, 0, 0, 0, 0, "SERIOUS", dracocharge, dracogriffe, seisme, lanceflammes);
            Pokemon metalosse1 = new Pokemon("Metalosse", PokeTypes.type.STEEL, PokeTypes.type.PSYCHIC, 50, 80, 135, 130, 95, 90, 70, 0, 0, 0, 0, 0, 0, "SERIOUS", poingmeteor, seisme, poingglace, poingeclair);
            Pokemon tyranocif1 = new Pokemon("Tyranocif", PokeTypes.type.ROCK, PokeTypes.type.DARK, 50, 100, 134, 110, 95, 100, 61, 0, 0, 0, 0, 0, 0, "SERIOUS", lamederock, poingfeu, seisme, machouille);
            Pokemon togekiss1 = new Pokemon("Togekiss", PokeTypes.type.FAIRY, PokeTypes.type.FLYING, 50, 85, 50, 95, 120, 115, 80, 0, 0, 0, 0, 0, 0, "SERIOUS", lamedair, eclatmagique, ballombre, lanceflammes);
            Pokemon elekable1 = new Pokemon("Elekable", PokeTypes.type.ELECTRIC, null, 50, 75, 123, 67, 95, 85, 95, 0, 0, 0, 0, 0, 0, "SERIOUS", poingeclair, poingfeu, poingglace, seisme);
            Pokemon pingoleon1 = new Pokemon("Pingoléon", PokeTypes.type.WATER, PokeTypes.type.STEEL, 50, 84, 86, 88, 111, 101, 60, 0, 0, 0, 0, 0, 0, "SERIOUS", surf, hydrocanon, luminocanon, laserglace);

            Team team1 = new Team();
            team1.addPokemon(carchacrok);
            team1.addPokemon(metalosse);
            team1.addPokemon(pingoleon);

            Team team2 = new Team();
            team2.addPokemon(tyranocif);
            team2.addPokemon(togekiss);
            team2.addPokemon(elekable);

            while (!input.equals("quit")) {
                try {
                    switch (input) {
                        case "ping":
                            client.sendPing();
                            break;
                        case "refresh":
                            client.sendRefresh();
                            break;
                        case "move1":
                            client.sendMove(client.getTrainer().getLeadingPkmn().getMove1());
                            break;
                        case "move2":
                            client.sendMove(client.getTrainer().getLeadingPkmn().getMove2());
                            break;
                        case "move3":
                            client.sendMove(client.getTrainer().getLeadingPkmn().getMove3());
                            break;
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
                        case "changepkmn1":
                            client.sendChangePkmn(new ChangePkmn(1));
                            break;
                        case "changepkmn2":
                            client.sendChangePkmn(new ChangePkmn(2));
                            break;
                        case "changepkmn3":
                            client.sendChangePkmn(new ChangePkmn(3));
                            break;
                        case "changepkmn4":
                            client.sendChangePkmn(new ChangePkmn(4));
                            break;
                        case "changepkmn5":
                            client.sendChangePkmn(new ChangePkmn(5));
                            break;
                        case "changepkmn6":
                            client.sendChangePkmn(new ChangePkmn(6));
                            break;
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
                        case "state":
                            System.out.println("État du client : " + client.getState());
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
