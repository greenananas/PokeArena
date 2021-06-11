package PokeArenaNetwork.Packets;

import PokeArenaNetwork.Packets.PokeArenaPacketType;

public abstract class PokeArenaPacket {

    /**
     * Type du paquet.
     */
    private final PokeArenaPacketType type;

    public PokeArenaPacket(PokeArenaPacketType type) {
        this.type = type;
    }

    public PokeArenaPacketType getType() {
        return type;
    }


}
