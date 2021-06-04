package PokeArenaNetwork;

import Model.Action;
import Model.Move;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class PokeArenaUtilities {

    /**
     * Utilisé pour la sérialisation.
     */
    private static final TypeToken<PokeArenaPacket> REQUEST_LIST_TYPE_TOKEN = new TypeToken<PokeArenaPacket>() {
    };

    /**
     * Utilisé pour la sérialisation.
     * Enregistrement des sous-types de la classe PokeArenaPacket.
     */
    private static final RuntimeTypeAdapterFactory<?> TYPE_FACTORY = RuntimeTypeAdapterFactory
            .of(PokeArenaPacket.class, "type") // Here you specify which is the parent class and what field particularizes the child class.
            .registerSubtype(PokeArenaPingPacket.class, "PING") // if the flag equals the class name, you can skip the second parameter. This is only necessary, when the "type" field does not equal the class name.
            .registerSubtype(PokeArenaMovePacket.class, "MOVE")
            .registerSubtype(PokeArenaActionPacket.class, "ACTION");

    /**
     * Objet utilisé pour la sérialisation des paquets PokeArenaPacket.
     */
    private static final Gson GSON = new GsonBuilder().registerTypeAdapterFactory(TYPE_FACTORY).create();

    /**
     * Parser un paquet sérialisé en JSON vers un paquet au type contenu dans le paquet JSON.
     *
     * @param jsonPacket Paquet au format JSON.
     * @return Paquet parsé vers le type contenu dans le paquet JSON.
     */
    public static PokeArenaPacket parseJsonPacket(String jsonPacket) {
        PokeArenaPacket packet = null;
        try {
            packet = GSON.fromJson(jsonPacket, REQUEST_LIST_TYPE_TOKEN.getType());
        } catch (Exception e) {
            System.out.println("Une erreur est survenue : ");
            e.printStackTrace();
        }
        return packet;
    }

    /**
     * Créer un paquet à partir de son type et de ses données.
     *
     * @param packetType Type du paquet.
     * @param packetData Données du paquet.
     * @param <D>        Type générique qui contient les informations du paquet.
     * @return Paquet créé.
     */
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
            case ACTION:
                packet = new PokeArenaActionPacket((Action) packetData);
                break;
            default:
                packet = null;
                //TODO: Remplacer par le lancement d'une erreur
        }
        return packet;
    }

    /**
     * Sérialise un paquet PokeArenaPacket vers un paquet au format JSON.
     *
     * @param packet Paquet de type PokeArenaPacket.
     * @return Paquet de type String sérialisé en JSON.
     */
    public static String toJson(PokeArenaPacket packet) {
        return GSON.toJson(packet);
    }

}
