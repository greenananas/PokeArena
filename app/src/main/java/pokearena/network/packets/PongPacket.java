package pokearena.network.packets;

public class PongPacket extends Packet {

    public PongPacket() {
        super(PacketType.PONG);
    }

}
