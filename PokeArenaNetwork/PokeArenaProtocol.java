package PokeArenaNetwork;

import Model.Battle;
import PokeArenaNetwork.Packets.PokeArenaPacket;
import org.java_websocket.WebSocket;

abstract public class PokeArenaProtocol {

    /**
     * Combat sur lequel le protocole va agir.
     */
    protected Battle battle;

    /**
     * Traite un paquet.
     *
     * @param ws      Connexion associé au paquet à traiter.
     * @param request Paquet à traiter.
     */
    abstract public void processPacket(WebSocket ws, PokeArenaPacket request);

}
