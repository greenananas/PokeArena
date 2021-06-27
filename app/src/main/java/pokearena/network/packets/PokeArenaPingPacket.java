package pokearena.network.packets;

public class PokeArenaPingPacket extends PokeArenaPacket {

    public PokeArenaPingPacket() {
        super(PokeArenaPacketType.PING);
    }

}
