package PokeArenaNetwork;

public abstract class PokeArenaPacket {

    private PokeArenaPacketType type;

    public PokeArenaPacket(PokeArenaPacketType type) {
        this.type = type;
    }

    public PokeArenaPacketType getType() {
        return type;
    }

}
