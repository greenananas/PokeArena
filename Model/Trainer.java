package Model;

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
        for (Pokemon p : this.getPokemonTeam()) {
            if (!p.isKO()) {
                return true;
            }
        }
        return false;
    }

    public Pokemon changePokemon() {
        int counter = 1;
        Pokemon[] alive = new Pokemon[5];
        System.out.println(this.getDisplayName() + " change de Pokémon.");
        for (Pokemon p : this.getPokemonTeam()) {
            if (!p.isKO()) {
                alive[counter - 1] = p;
                System.out.println(counter + " " + p.getName());
                counter++;
            }
        }
        Scanner s = new Scanner(System.in);
        boolean check = false;
        int choice = 0;
        while (!check) {
            choice = s.nextInt();
            switch (choice) {
                case 1:
                    check = true;
                    break;
                case 2:
                    if (counter >= 2) {
                        check = true;
                    } else {
                        System.out.println("Pokémon introuvable.");
                    }
                    break;
                case 3:
                    if (counter >= 3) {
                        check = true;
                    } else {
                        System.out.println("Pokémon introuvable.");
                    }
                    break;
                case 4:
                    if (counter >= 4) {
                        check = true;
                    } else {
                        System.out.println("Pokémon introuvable.");
                    }
                    break;
                case 5:
                    if (counter >= 5) {
                        check = true;
                    } else {
                        System.out.println("Pokémon introuvable.");
                    }
                    break;
                case 6:
                    if (counter >= 6) {
                        check = true;
                    } else {
                        System.out.println("Pokémon introuvable.");
                    }
                    break;
                default:
                    System.out.println("Tu troll mec.");
                    break;
            }
        }
        return alive[choice - 1];
    }
}
