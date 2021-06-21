package PokeArena.PokeArenaNetwork.Packets;

import PokeArena.PokeArenaBattle.ChangePkmn;

/**
 * Modélise le format des paquets utilisés pour envoyer des changements de Pokémon.
 *
 * @author Louis
 */
public class PokeArenaChangePokemonPacket extends PokeArenaPacket {

    private ChangePkmn changePkmn = null;

    // Sans constructeur par défaut problème de désérialisation
    public PokeArenaChangePokemonPacket() {
        super(PokeArenaPacketType.CHANGEPOKEMON);
    }

    public PokeArenaChangePokemonPacket(ChangePkmn changePkmn) {
        this();
        this.changePkmn = changePkmn;
    }

    public ChangePkmn getChangePkmn() {
        return changePkmn;
    }

}
