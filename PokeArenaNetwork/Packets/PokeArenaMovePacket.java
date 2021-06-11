package PokeArenaNetwork.Packets;

import Model.Move;

public class PokeArenaMovePacket extends PokeArenaPacket {

    private Move move = null;

    // Sans constructeur par défaut problème de désérialisation
    public PokeArenaMovePacket() {
        super(PokeArenaPacketType.MOVE);
    }

    public PokeArenaMovePacket(Move move) {
        this();
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

}
