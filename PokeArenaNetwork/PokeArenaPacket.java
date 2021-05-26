package PokeArenaNetwork;

public abstract class PokeArenaPacket {

    /**
     * Type du paquet.
     */
    private PokeArenaPacketType type;

    /**
     * Données du paquet au format JSON.
     */
    private String data;

    public PokeArenaPacket(PokeArenaPacketType type, String data) {
        this.type = type;
        this.data = data;
    }

    public PokeArenaPacketType getType() {
        return type;
    }


}
