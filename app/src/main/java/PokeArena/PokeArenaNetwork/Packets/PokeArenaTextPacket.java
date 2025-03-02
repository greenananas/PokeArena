package PokeArena.PokeArenaNetwork.Packets;

public class PokeArenaTextPacket extends PokeArenaPacket {

    private String text = null;

    // Sans constructeur par défaut problème de désérialisation
    public PokeArenaTextPacket() {
        super(PokeArenaPacketType.TEXT);
    }

    public PokeArenaTextPacket(String text) {
        this();
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
