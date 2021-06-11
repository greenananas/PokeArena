package PokeArenaNetwork.Packets;

import PokeArenaNetwork.Packets.PokeArenaPacket;
import PokeArenaNetwork.Packets.PokeArenaPacketType;
import PokeArenaNetwork.Update;

public class PokeArenaUpdatePacket extends PokeArenaPacket {

    private Update update;

    public PokeArenaUpdatePacket() {
        super(PokeArenaPacketType.UPDATE);
    }

    public PokeArenaUpdatePacket(Update update) {
        this();
        this.update = update;
    }

    public Update getUpdate() {
        return update;
    }

}
