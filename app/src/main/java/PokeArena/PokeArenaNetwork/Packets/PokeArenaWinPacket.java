package PokeArena.PokeArenaNetwork.Packets;

import PokeArenaNetwork.Packets.PokeArenaPacket;
import PokeArenaNetwork.Packets.PokeArenaPacketType;

public class PokeArenaWinPacket extends PokeArenaPacket {

    public PokeArenaWinPacket() {
        super(PokeArenaPacketType.WIN);
    }

}
