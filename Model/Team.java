package Model;

import java.util.ArrayList;

public class Team {

    /**
     * Équipe de Pokémon du dresseur
     */
    private ArrayList<Pokemon> pokemonTeam;

    /**
     * Créer une team vide
     *
     */
    public Team() {
        this.pokemonTeam = new ArrayList<>();
    }

    /**
     * Créer une team
     *
     * @param team Équipe de Pokémon du dresseur
     */
    public Team(ArrayList<Pokemon> team) {
        this.pokemonTeam = team;
    }

    /**
     * Obtenir l'équipe de Pokémon d'un dresseur
     *
     * @return Équipe de Pokémon
     */
    public ArrayList<Pokemon> getPokemons() {
        return this.pokemonTeam;
    }

    /**
     * Obtenir un Pokémon parmi l'équipe d'un dresseur
     *
     * @param n Numéro du Pokémon dans l'équipe du dresseur
     * @return Pokémon choisi
     */
    public Pokemon get(int n) {
        return this.pokemonTeam.get(n);
    }

    /**
     * Modifier le Pokémon d'un dresseur
     *
     * @param p Nouveau Pokémon du dresseur
     */
    public void addPokemon(Pokemon p) {
        assert pokemonTeam.size() < 6;
        this.pokemonTeam.add(p);
    }

    public boolean isDefeated() {
        for (Pokemon p : this.pokemonTeam) {
            if (!p.isKO()) {
                return false;
            }
        }
        return true;
    }

}
