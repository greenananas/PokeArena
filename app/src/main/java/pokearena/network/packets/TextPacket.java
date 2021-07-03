package pokearena.network.packets;

public class TextPacket extends Packet {

    private String text = null;

    // Sans constructeur par défaut problème de désérialisation
    public TextPacket() {
        super(PacketType.TEXT);
    }

    public TextPacket(String text) {
        this();
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
