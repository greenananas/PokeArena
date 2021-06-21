package PokeArena.PokeArenaBattle;

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

    public void changePokemon(int choice) {
        if (getLeadingPkmn().getStatus().status == PokeStatus.Status.BADLY_POISONED) getLeadingPkmn().getStatus().resetDuration();
        Collections.swap(pokemonTeam.getPokemons(), 0, choice);
    }

    /**
     * Méthode permettant le choix de l'action d'un utilisateur dans le cadre d'un combat en ligne de commande. Non
     * implantée dans l'application.
     *
     * @param ennemy Le pokémon adverse.
     * @return L'action prise par le joueur courant.
     */
    public Action chooseAction(Pokemon ennemy) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Move> moves = getLeadingPkmn().getMove();
        int actChoice, changeChoice;
        do {
            changeChoice = 0;
            System.out.println(ennemy);
            for (int i = 1; i<5; i++) {
                System.out.println(i + " - "+moves.get(i-1));
            }
            System.out.println("5 - Changer de pokémon");
            do {
                System.out.println("Choisissez une action : ");
                actChoice = sc.nextInt();
            } while (actChoice < 0 || actChoice > 5);
            if (actChoice == 5) {
                actChoice = 0;
                int counter = 1;
                System.out.println("0 - Annuler");
                for (Pokemon p : this.pokemonTeam.getPokemons()) {
                    if (p.isKO()) {
                        System.out.print(" K.O ");
                    }
                    System.out.println(counter + " - " + p.getName());
                    counter++;
                    do {
                        changeChoice = sc.nextInt();
                    } while (changeChoice < 0 || changeChoice == 1 || changeChoice > 6 || pokemonTeam.get(changeChoice).isKO());
                }
            }
        } while (actChoice == 0 && changeChoice == 0);
        if (actChoice != 0) {
            return moves.get(actChoice-1);
        } else {
            return new ChangePkmn(changeChoice-1);
        }
    }
}
