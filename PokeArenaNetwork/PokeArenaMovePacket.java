package PokeArenaNetwork;

import Model.Move;

public class PokeArenaMovePacket extends PokeArenaPacket {

    public PokeArenaMovePacket(Move data) {
        super(PokeArenaPacketType.MOVE, data);
    }

}
