package pokearena.network.packets;

public class PingPacket extends Packet {

    public PingPacket() {
        super(PacketType.PING);
    }

}
