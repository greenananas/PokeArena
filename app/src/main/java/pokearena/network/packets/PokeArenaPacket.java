package pokearena.network.packets;

/**
 * Définit le format des paquets utilisés pour la communication entre les serveur et clients PokeArena.
 *
 * @author Louis
 */
public abstract class PokeArenaPacket {

    /**
     * Type du paquet.
     */
    private final PokeArenaPacketType type;

    /**
     * Créer un paquet du type fourni en paramètre.
     *
     * @param type Type du paquet.
     */
    protected PokeArenaPacket(PokeArenaPacketType type) {
        this.type = type;
    }

    /**
     * Obtenir le type du paquet.
     *
     * @return Type du paquet.
     */
    public PokeArenaPacketType getType() {
        return type;
    }


}
