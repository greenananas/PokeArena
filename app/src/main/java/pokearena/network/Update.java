package pokearena.network;

import pokearena.battle.Move;
import pokearena.battle.Pokemon;
import pokearena.battle.Team;

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
     * Dernière attaque du dresseur adverse.
     */
    private final Move oppenentMove;

    /**
     * Créer un objet de mise à jour.
     *
     * @param team            Équipe du dresseur.
     * @param opponentPokemon Pokémon au combat du dresseur adverse.
     * @param oppenentMove    Attaque du dresseur adverse.
     */
    public Update(Team team, Pokemon opponentPokemon, Move oppenentMove) {
        this.team = team;
        this.opponentPokemon = opponentPokemon;
        this.oppenentMove = oppenentMove;
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

    /**
     * Obtenir l'attaque du dresseur adverse.
     *
     * @return Attaque du dresseur adverse.
     */
    public Move getOppenentMove() {
        return oppenentMove;
    }

}

