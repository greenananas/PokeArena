package PokeArenaNetwork;

import Model.Battle;
import Model.Action;
import org.java_websocket.WebSocket;

import static PokeArenaNetwork.PokeArenaUtilities.createPacket;

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
