package pokearena.network.packets;

public class PokeArenaLosePacket extends PokeArenaPacket {

    public PokeArenaLosePacket() {
        super(PokeArenaPacketType.LOSE);
    }

}
