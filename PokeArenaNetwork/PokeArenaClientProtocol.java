package PokeArenaNetwork;

import Model.Action;
import org.java_websocket.WebSocket;

import static PokeArenaNetwork.PokeArenaUtilities.createPacket;

public class PokeArenaClientProtocol extends PokeArenaProtocol {

    /**
     * Client PokeArena qui utilise le protocole.
     */
    private PokeArenaClient client;

    public PokeArenaClientProtocol(PokeArenaClient client) {
        this.client = client;
    }

    /**
     * Traite un paquet et retourne la réponse associé.
     * La réponse peut être nulle si le paquet entré en paramètre ne nécessite pas de réponse.
     *
     * @param ws      Connexion associé au paquet à traiter.
     * @param request Paquet à traiter.
     */
    public void processPacket(WebSocket ws, PokeArenaPacket request) {
        PokeArenaPacket response;
        switch (request.getType()) {
            case PING:
                response = createPacket(PokeArenaPacketType.PONG, null);
                break;
            case TEXT:
                System.out.println("Serveur dit : " + ((PokeArenaTextPacket) request).getText());
                response = null;
                break;
            case UPDATE:
                //TODO: Mettre à jour les informations du client contenus dans battle
                response = null;
                break;
            default:
                response = null;
        }
        if (response != null) {
            client.sendPacket(response);
        }
    }

}
