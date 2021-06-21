package PokeArenaNetwork;

import PokeArenaNetwork.Packets.PokeArenaPacket;
import org.java_websocket.WebSocket;

/**
 * Définit un protocole utilisé par les clients et serveurs PokeArena.
 *
 * @author Louis
 */
abstract public class PokeArenaProtocol {

    /**
     * Traite un paquet.
     *
     * @param ws      Connexion associé au paquet à traiter.
     * @param request Paquet à traiter.
     */
    abstract public void processPacket(WebSocket ws, PokeArenaPacket request);

}
