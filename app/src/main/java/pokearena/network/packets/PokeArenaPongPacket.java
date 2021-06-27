package pokearena.network.packets;

public class PokeArenaPongPacket extends PokeArenaPacket {

    public PokeArenaPongPacket() {
        super(PokeArenaPacketType.PONG);
    }

}
