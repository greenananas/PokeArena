package Model;

public class team {

    /**
     * Équipe de Pokémon du dresseur
     */
    private pokemon[] pokemonTeam;

    /**
     * Créer une team
     *
     * @param team Équipe de Pokémon du dresseur
     */
    public trainer(pokemon[] team) {
        this.pokemonTeam = team;
    }

    /**
     * Obtenir l'équipe de Pokémon d'un dresseur
     *
     * @return Équipe de Pokémon
     */
    public pokemon[] getPokemonTeam() {
        return this.pokemonTeam;
    }

    /**
     * Obtenir un Pokémon parmi l'équipe d'un dresseur
     *
     * @param n Numéro du Pokémon dans l'équipe du dresseur
     * @return Pokémon choisi
     */
    public pokemon getPokemon(int n) {
        return this.pokemonTeam[n];
    }

    /**
     * Modifier le Pokémon d'un dresseur
     *
     * @param p Nouveau Pokémon du dresseur
     * @param n Emplacement du Pokémon dans l'équipe du dresseur
     */
    public void setPokemon(pokemon p, int n) {
        this.pokemonTeam[n] = p;
    }

}
