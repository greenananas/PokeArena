package pokearena.network.packets;

import pokearena.battle.Move;

public class MovePacket extends Packet {

    private Move move = null;

    // Sans constructeur par défaut problème de désérialisation
    public MovePacket() {
        super(PacketType.MOVE);
    }

    public MovePacket(Move move) {
        this();
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

}
