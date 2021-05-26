package Model;

public class Team {

    /**
     * Équipe de Pokémon du dresseur
     */
    private Pokemon[] pokemonTeam;

    /**
     * Créer une Team
     *
     * @param team Équipe de Pokémon du dresseur
     */
    public Team(Pokemon[] team) {
        this.pokemonTeam = team;
    }

    /**
     * Obtenir l'équipe de Pokémon d'un dresseur
     *
     * @return Équipe de Pokémon
     */
    public Pokemon[] getPokemonTeam() {
        return this.pokemonTeam;
    }

    /**
     * Obtenir un Pokémon parmi l'équipe d'un dresseur
     *
     * @param n Numéro du Pokémon dans l'équipe du dresseur
     * @return Pokémon choisi
     */
    public Pokemon getPokemon(int n) {
        return this.pokemonTeam[n];
    }

    /**
     * Modifier le Pokémon d'un dresseur
     *
     * @param p Nouveau Pokémon du dresseur
     * @param n Emplacement du Pokémon dans l'équipe du dresseur
     */
    public void setPokemon(Pokemon p, int n) {
        this.pokemonTeam[n] = p;
    }

}
