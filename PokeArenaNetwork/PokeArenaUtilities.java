package PokeArenaNetwork;

import Model.Move;
import com.google.gson.Gson;

public class PokeArenaUtilities {

    protected static final Gson GSON = new Gson();

    public static PokeArenaPacket parseJsonPacket(String jsonPacket) {
       PokeArenaPacket packet = null;
       try {
           packet = GSON.fromJson(jsonPacket, PokeArenaPacket.class);
       } catch (Exception e) {
           System.out.println("Une erreur est survenue");
       }
       return packet;
    }

    public static <A> PokeArenaPacket createPacket(PokeArenaPacketType packetType, A packetData) {
        PokeArenaPacket packet;
        switch (packetType) {
            case PING:
                packet = new PokeArenaPingPacket();
                break;
            case MOVE:
                packet = new PokeArenaMovePacket((Move) packetData);
            default:
                packet = null;
                //TODO: Remplacer par le lancement d'une erreur
        }
        return packet;
    }

}
