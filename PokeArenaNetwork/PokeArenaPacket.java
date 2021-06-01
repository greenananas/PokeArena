package PokeArenaNetwork;

public abstract class PokeArenaPacket<A> {

    /**
     * Type du paquet.
     */
    private PokeArenaPacketType type;

    /**
     * Données du paquet au format JSON.
     */
    private A data;

    public PokeArenaPacket(PokeArenaPacketType type, A data) {
        this.type = type;
        this.data = data;
    }

    public PokeArenaPacketType getType() {
        return type;
    }


}
