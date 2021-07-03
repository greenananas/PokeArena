package pokearena.network.packets;

import pokearena.battle.ChangePkmn;

/**
 * Modélise le format des paquets utilisés pour envoyer des changements de Pokémon.
 *
 * @author Louis
 */
public class ChangePokemonPacket extends Packet {

    private ChangePkmn changePkmn = null;

    // Sans constructeur par défaut problème de désérialisation
    public ChangePokemonPacket() {
        super(PacketType.CHANGEPOKEMON);
    }

    public ChangePokemonPacket(ChangePkmn changePkmn) {
        this();
        this.changePkmn = changePkmn;
    }

    public ChangePkmn getChangePkmn() {
        return changePkmn;
    }

}
