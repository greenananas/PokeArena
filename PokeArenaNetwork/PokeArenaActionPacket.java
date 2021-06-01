package PokeArenaNetwork;

import Model.Action;

public class PokeArenaActionPacket extends PokeArenaPacket {

    private Action action = null;

    // Sans constructeur par défaut problème de désérialisation
    public PokeArenaActionPacket() {
        super(PokeArenaPacketType.ACTION);
    }

    public PokeArenaActionPacket(Action action) {
        this();
        this.action = action;
    }

    public Action getMove() {
        return action;
    }

}
