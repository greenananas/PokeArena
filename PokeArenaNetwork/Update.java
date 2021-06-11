package PokeArenaNetwork;

import Model.Pokemon;
import Model.Team;

/**
 * Modélise la mise à jour des informations du combat à envoyer aux clients.
 */
public class Update {

    /**
     * Équipe du dresseur.
     */
    private final Team team;

    /**
     * Pokémon en combat du dresseur adverse.
     */
    private final Pokemon opponentPokemon;

    /**
     * Créer un objet de mise à jour.
     *
     * @param team            Équipe du dresseur.
     * @param opponentPokemon Pokémon au combat du dresseur adverse.
     */
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
