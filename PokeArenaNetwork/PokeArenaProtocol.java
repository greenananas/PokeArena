package PokeArenaNetwork;

import Model.Battle;
import Model.Action;
import org.java_websocket.WebSocket;

import static PokeArenaNetwork.PokeArenaUtilities.createPacket;

abstract public class PokeArenaProtocol {

    /**
     * Combat sur lequel le protocole va agir.
     */
    private Battle battle;

    /**
     * Traite un paquet.
     *
     * @param ws      Connexion associé au paquet à traiter.
     * @param request Paquet à traiter.
     */
    abstract public void processPacket(WebSocket ws, PokeArenaPacket request);

    /**
     * Traite un paquet de type Action et retourne la réponse associé.
     * La réponse peut être nulle si le paquet entré en paramètre ne nécessite pas de réponse.
     *
     * @param ws      Connexion associé au paquet à traiter.
     * @param request Paquet à traiter.
     * @return Paquet de réponse.
     */
    abstract protected PokeArenaPacket processActionPacket(WebSocket ws, PokeArenaPacket request);

}
