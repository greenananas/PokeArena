package pokearena.network.packets;

import pokearena.network.Update;

public class UpdatePacket extends Packet {

    private Update update;

    public UpdatePacket() {
        super(PacketType.UPDATE);
    }

    public UpdatePacket(Update update) {
        this();
        this.update = update;
    }

    public Update getUpdate() {
        return update;
    }

}
