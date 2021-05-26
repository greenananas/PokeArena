package PokeArenaNetwork;

public class PokeArenaPingPacket extends PokeArenaPacket {

    public PokeArenaPingPacket() {
        super(PokeArenaPacketType.PING, null);
    }

}
