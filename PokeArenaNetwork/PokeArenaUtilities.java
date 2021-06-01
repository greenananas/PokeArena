package PokeArenaNetwork;

import Model.Move;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class PokeArenaUtilities {

    /**
     * Utilisé pour la sérialisation.
     */
    private static final TypeToken<PokeArenaPacket> requestListTypeToken = new TypeToken<PokeArenaPacket>() {
    };

    /**
     * Utilisé pour la sérialisation.
     * Enregistrement des sous-types de la classe PokeArenaPacket.
     */
    private static final RuntimeTypeAdapterFactory<?> typeFactory = RuntimeTypeAdapterFactory
            .of(PokeArenaPacket.class, "type") // Here you specify which is the parent class and what field particularizes the child class.
            .registerSubtype(PokeArenaPingPacket.class, "PING") // if the flag equals the class name, you can skip the second parameter. This is only necessary, when the "type" field does not equal the class name.
            .registerSubtype(PokeArenaMovePacket.class, "MOVE");

    /**
     * Objet utilisé pour la sérialisation des paquets PokeArenaPacket.
     */
    private static final Gson GSON = new GsonBuilder().registerTypeAdapterFactory(typeFactory).create();

    public static PokeArenaPacket parseJsonPacket(String jsonPacket) {
        PokeArenaPacket packet = null;
        try {
            packet = GSON.fromJson(jsonPacket, requestListTypeToken.getType());
        } catch (Exception e) {
            System.out.println("Une erreur est survenue : ");
            e.printStackTrace();
        }
        return packet;
    }

    public static <D> PokeArenaPacket createPacket(PokeArenaPacketType packetType, D packetData) {
        PokeArenaPacket packet;
        switch (packetType) {
            case PING:
                packet = new PokeArenaPingPacket();
                break;
            case PONG:
                packet = new PokeArenaPongPacket();
                break;
            case MOVE:
                packet = new PokeArenaMovePacket((Move) packetData);
                break;
            default:
                packet = null;
                //TODO: Remplacer par le lancement d'une erreur
        }
        return packet;
    }

    public static String toJson(PokeArenaPacket packet) {
        return GSON.toJson(packet);
    }

}
