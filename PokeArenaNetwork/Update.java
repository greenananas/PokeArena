package PokeArenaNetwork;

import Model.Pokemon;
import Model.Team;

public class Update {

    /**
     * Équipe du dresseur.
     */
    private Team team;

    /**
     * Pokémon en combat du dresseur adverse.
     */
    private Pokemon opponentPokemon;

    public Update(Team team, Pokemon opponentPokemon) {
        this.team = team;
        this.opponentPokemon = opponentPokemon;
    }

    /**
     * Obtenir l'équipe du dresseur.
     *
     * @return Équipe du dresseur.
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Obtenir le pokémon en combat du dresseur adverse.
     *
     * @return Pokémon en combat du dresseur adverse.
     */
    public Pokemon getOpponentPokemon() {
        return opponentPokemon;
    }
}
