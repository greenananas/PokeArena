package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Class décrivant un dresseur de Pokémon.
 */
public class Trainer {

    /**
     * Pseudonyme du dresseur
     */
    private String displayName;

    /**
     * Équipe de Pokémon du dresseur
     */
    private Team pokemonTeam;

    /**
     * Créer un dresseur
     *
     * @param name Pseudonyme du dresseur
     * @param pkteam Équipe de Pokémon du dresseur
     */
    public Trainer(String name, Team pkteam) {
        this.displayName = name;
        this.pokemonTeam = pkteam;
    }

    /**
     * Obtenir le pseudonyme d'un dresseur
     *
     * @return Pseudonyme du dresseur
     */
    public String getDisplayName() {
        return this.displayName;
    }

    /**
     * Obtenir l'équipe de Pokémon d'un dresseur
     *
     * @return Équipe de Pokémon
     */
    public Team getPokemonTeam() {
        return this.pokemonTeam;
    }

    /**
     * Modifier le pseudonyme d'un dresseur
     *
     * @param n Nouveau pseudonyme du dresseur
     */
    public void setDisplayName(String n) {
        this.displayName = n;
    }

    /**
     * Modifier l'équipe de Pokémon d'un dresseur
     *
     * @param pkteam Nouvelle équipe du dresseur
     */
    public void setPokemonTeam(Team pkteam) {
        this.pokemonTeam = pkteam;
    }

    public boolean hasPokemonLeft() {
        return !this.pokemonTeam.isDefeated();
    }

    public Pokemon getLeadingPkmn() {
        return pokemonTeam.get(0);
    }

    public Pokemon changePokemon() {
        int counter = 1;
        for (Pokemon p : this.pokemonTeam.getPokemons()) {
            if (!p.isKO()) {
                System.out.print(" K.O ");
            }
            System.out.println(counter + " - " + p.getName());
            counter++;
        }
        Scanner s = new Scanner(System.in);
        int choice;
        do {
            choice = s.nextInt();
        } while (choice < 1 || choice > 6 || pokemonTeam.get(choice).isKO());
        Collections.swap(pokemonTeam.getPokemons(), 0, choice);
        return pokemonTeam.get(choice);
    }

    public Action chooseAction(Pokemon ennemy) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Move> moves = getLeadingPkmn().getMove();
        System.out.println(ennemy);
        for (int i = 1; i<5; i++) {
            System.out.println(i + " - "+moves.get(i-1));
        }
        System.out.println("5 - Changer de pokémon");
        int choix;
        do {
            System.out.println("Choisissez une action : ");
            choix = sc.nextInt();
        } while (choix < 0 || choix > 5);
        if (choix != 5) {
            return moves.get(choix-1);
        } else {
            return new Action(acTypes.CHANGEPKM, 6);
        }
    }
}
