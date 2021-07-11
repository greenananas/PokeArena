package pokearena.network;

import pokearena.battle.Action;
import pokearena.battle.ChangePkmn;
import pokearena.battle.Move;
import pokearena.battle.Team;
import pokearena.network.packets.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Classe regroupant des utilitaires servant pour la manipulation des paquets.
 *
 * @author Louis
 */
public class Utils {

    private Utils() {
        throw new IllegalStateException("Utility class");
    }


    /**
     * Utilisé pour la sérialisation.
     */
    private static final TypeToken<Packet> REQUEST_LIST_TYPE_TOKEN = new TypeToken<Packet>() {
    };

    /**
     * Utilisé pour la sérialisation.
     * Enregistrement des sous-types de la classe PokeArenaPacket.
     */
    private static final RuntimeTypeAdapterFactory<?> TYPE_FACTORY = RuntimeTypeAdapterFactory
            .of(Packet.class, "type")
            .registerSubtype(PingPacket.class, "PING")
            .registerSubtype(PongPacket.class, "PONG")
            .registerSubtype(WinPacket.class, "WIN")
            .registerSubtype(LosePacket.class, "LOSE")
            .registerSubtype(RefreshPacket.class, "REFRESH")
            .registerSubtype(ForfeitPacket.class, "FORFEIT")
            .registerSubtype(UpdatePacket.class, "UPDATE")
            .registerSubtype(TextPacket.class, "TEXT")
            .registerSubtype(MovePacket.class, "MOVE")
            .registerSubtype(TeamPacket.class, "TEAM")
            .registerSubtype(ChangePokemonPacket.class, "CHANGEPOKEMON");

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
    public static Packet parseJsonPacket(String jsonPacket) {
        Packet packet = null;
        try {
            packet = GSON.fromJson(jsonPacket, REQUEST_LIST_TYPE_TOKEN.getType());
        } catch (Exception e) {
            System.out.println("Une erreur est survenue : " + e.getMessage());
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
    public static <D> Packet createPacket(PacketType packetType, D packetData) {
        Packet packet;
        switch (packetType) {
            case PING:
                packet = new PingPacket();
                break;
            case PONG:
                packet = new PongPacket();
                break;
            case WIN:
                packet = new WinPacket();
                break;
            case LOSE:
                packet = new LosePacket();
                break;
            case REFRESH:
                packet = new RefreshPacket();
                break;
            case FORFEIT:
                packet = new ForfeitPacket();
                break;
            case UPDATE:
                packet = new UpdatePacket((Update) packetData);
                break;
            case MOVE:
                packet = new MovePacket((Move) packetData);
                break;
            case CHANGEPOKEMON:
                packet = new ChangePokemonPacket((ChangePkmn) packetData);
                break;
            case TEAM:
                packet = new TeamPacket((Team) packetData);
                break;
            case TEXT:
                packet = new TextPacket((String) packetData);
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
    public static String toJson(Packet packet) {
        return GSON.toJson(packet);
    }

}
