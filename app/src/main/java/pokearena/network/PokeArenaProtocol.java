package pokearena.network;

import pokearena.network.packets.PokeArenaPacket;
import org.java_websocket.WebSocket;

/**
 * Définit un protocole utilisé par les clients et serveurs PokeArena.
 *
 * @author Louis
 */
public abstract class PokeArenaProtocol {

    /**
     * Traite un paquet.
     *
     * @param ws      Connexion associé au paquet à traiter.
     * @param request Paquet à traiter.
     */
    public abstract void processPacket(WebSocket ws, PokeArenaPacket request);

}
