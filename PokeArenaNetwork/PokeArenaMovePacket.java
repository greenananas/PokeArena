package PokeArenaNetwork;

import Model.Move;

public class PokeArenaMovePacket extends PokeArenaPacket {

    private Move move;

    public PokeArenaMovePacket(Move move) {
        super(PokeArenaPacketType.MOVE);
        this.move = move;
    }

}
