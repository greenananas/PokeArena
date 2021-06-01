package PokeArenaNetwork;

import com.google.gson.Gson;

public class PokeArenaUtilities {

    protected static final Gson GSON = new Gson();

    public static PokeArenaPacket parseJsonPacket(String jsonPacket) {
       PokeArenaPacket packet = null;
       try {
           packet = GSON.fromJson(jsonPacket, PokeArenaPacket.class);
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       return packet;
    }

    public static <A> PokeArenaPacket createPacket(PokeArenaPacketType packetType, A packetData) {
        PokeArenaPacket packet;
        switch (packetType) {
            case PING:
                packet = new PokeArenaPingPacket();
                break;
            default:
                packet = null;
                //TODO: Remplacer par le lancement d'une erreur
        }
        return packet;
    }

}
