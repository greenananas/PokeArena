package PokeArenaNetwork;

import Model.ChangePkmn;
import Model.Pokemon;

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
