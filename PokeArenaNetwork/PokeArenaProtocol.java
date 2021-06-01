package PokeArenaNetwork;

import Model.Battle;
import org.java_websocket.WebSocket;


import static PokeArenaNetwork.PokeArenaUtilities.createPacket;

public class PokeArenaProtocol {


    /**
     * Serveur PokeArena qui utilise le protocole.
     */
    private PokeArenaServer server;

    /**
     * Combat sur lequel le protocole va agir.
     */
    private Battle battle;

    /**
     * Dernière action envoyée par le client 1.
     */
    private Action client1Action;

    /**
     * Dernière action envoyée par le client 2.
     */
    private Action client2Action;

    public PokeArenaProtocol(PokeArenaServer server) {
        this.server = server;
    }

    /**
     * Traite un paquet et retourne la réponse associé.
     * La réponse peut être nulle si le paquet entré en paramètre ne nécessite pas de réponse.
     *
     * @param ws      Connexion associé au paquet à traiter.
     * @param request Paquet à traiter.
     * @return Paquet de réponse.
     */
    public PokeArenaPacket<?> processPacket(WebSocket ws, PokeArenaPacket<?> request) {
        PokeArenaPacket<?> response;
        switch (request.getType()) {
            case PING:
                response = createPacket(PokeArenaPacketType.PONG, null);
                break;
            case ACTION:
                response = processActionPacket(ws, (PokeArenaPacket<Action>) request);
            default:
                response = null;
        }
        return response;
    }

    /**
     * Traite un paquet de type Action et retourne la réponse associé.
     * La réponse peut être nulle si le paquet entré en paramètre ne nécessite pas de réponse.
     *
     * @param ws      Connexion associé au paquet à traiter.
     * @param request Paquet à traiter.
     * @return Paquet de réponse.
     */
    private PokeArenaPacket<?> processActionPacket(WebSocket ws, PokeArenaPacket<Action> request) {

        switch (server.getState()) {
            case WAITING_FOR_CLIENTS_ACTIONS:
                if (ws == server.getClient1WS()) {
                    client1Action = request.getAction();
                    server.setState(PokeArenaServerState.WAITING_FOR_CLIENT_2_ACTION);
                    break;
                } else if (ws == server.getClient2WS()) {
                    client2Action = request.getAction();
                    server.setState(PokeArenaServerState.WAITING_FOR_CLIENT_1_ACTION);
                    break;
                }
                // On n'utilise pas de break pour utiliser le cas default du switch
            case WAITING_FOR_CLIENT_1_ACTION:
                if (ws == server.getClient1WS()) {
                    client1Action = request.getAction();
                    server.setState(PokeArenaServerState.PROCESSING_ACTIONS);
                    // Qqchose retour = battle.evaluateActions(client1Action, client2Action);
                    // En fonction du retour changer état du serveur : WAITING_FOR_CLIENTS_ACTIONS, BATTLE_ENDED...
                    // Puis envoyer un message UPDATE au client (mise à jour HP...)
                    break;
                }
            case WAITING_FOR_CLIENT_2_ACTION:
                if (ws == server.getClient2WS()) {
                    client1Action = request.getAction();
                    server.setState(PokeArenaServerState.PROCESSING_ACTIONS);
                    // Pareil que en haut
                    break;
                }
            default:
                // Envoyer un paquet d'erreur
                // reponse = createPacket(ClassePaquetErreur, null);
        }
        return response = createPacket(PokeArenaPacketType.PONG, null);
    }

}
