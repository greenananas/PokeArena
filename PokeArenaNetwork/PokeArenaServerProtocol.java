package PokeArenaNetwork;

import Model.*;
import org.java_websocket.WebSocket;

import static PokeArenaNetwork.PokeArenaUtilities.createPacket;

public class PokeArenaServerProtocol extends PokeArenaProtocol {

    /**
     * Serveur PokeArena qui utilise le protocole.
     */
    private PokeArenaServer server;

    /**
     * Dernière action envoyée par le client 1.
     */
    private Action client1Action;

    /**
     * Dernière action envoyée par le client 2.
     */
    private Action client2Action;

    /**
     * Trainer du client 1, utilisée uniquement pour créer l'objet Battle lors de l'initialisation du combat.
     */
    private Trainer client1Trainer;

    /**
     * Trainer du client 2, utilisée uniquement pour créer l'objet Battle lors de l'initialisation du combat.
     */
    private Trainer client2Trainer;

    public PokeArenaServerProtocol(PokeArenaServer server) {
        this.server = server;
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
            case REFRESH:
                Team trainerTeam = null;
                Pokemon opponentPokemon = null;
                if (ws == server.getClient1WS()) {
                    trainerTeam = battle.trainer1.getTrainer().getPokemonTeam();
                    opponentPokemon = battle.trainer2.getTrainer().getLeadingPkmn();
                } else if (ws == server.getClient2WS()) {
                    trainerTeam = battle.trainer2.getTrainer().getPokemonTeam();
                    opponentPokemon = battle.trainer1.getTrainer().getLeadingPkmn();
                }
                response = (trainerTeam != null && opponentPokemon != null)
                        ? createPacket(PokeArenaPacketType.UPDATE, new Update(trainerTeam, opponentPokemon))
                        : null;
                break;
            case TEAM:
                Team team = ((PokeArenaTeamPacket) request).getTeam();
                System.out.println("Équipe reçue :");
                for (Pokemon p : team.getPokemons()) {
                    System.out.println(" - " + p);
                }
                switch (server.getState()) {
                    case WAITING_FOR_CLIENTS_TEAM:
                        if (ws == server.getClient1WS()) {
                            client1Trainer = new Trainer("Joueur 1", team);
                            server.setState(PokeArenaServerState.WAITING_FOR_CLIENT_2_TEAM);
                        } else if (ws == server.getClient2WS()) {
                            client2Trainer = new Trainer("Joueur 2", team);
                            server.setState(PokeArenaServerState.WAITING_FOR_CLIENT_1_TEAM);
                        }
                        break;
                    case WAITING_FOR_CLIENT_1_TEAM:
                        if (ws == server.getClient1WS()) {
                            client1Trainer = new Trainer("Joueur 1", team);
                            server.setState(PokeArenaServerState.WAITING_FOR_START);
                        }
                        break;
                    case WAITING_FOR_CLIENT_2_TEAM:
                        if (ws == server.getClient2WS()) {
                            client2Trainer = new Trainer("Joueur 2", team);
                            server.setState(PokeArenaServerState.WAITING_FOR_START);
                        }
                        break;
                    default:
                        break;
                }
                response = null;
                break;
            case TEXT:
                String clt = ws == server.getClient1WS() ? "Client 1" : "Client 2";
                System.out.println(clt + " dit : " + ((PokeArenaTextPacket) request).getText());
                response = null;
                break;
            case MOVE: //TODO: A enlever
                System.out.println(((PokeArenaMovePacket) request).getMove().getName());
                response = request;
                break;
            case ACTION:
                response = processActionPacket(ws, request);
                break;
            default:
                response = null;
        }
        if (response != null) {
            server.sendPacket(ws, response);
        }
    }

    /**
     * Lancer le combat.
     */
    public void startBattle() {
        if (server.getState() == PokeArenaServerState.WAITING_FOR_START) {
            battle = new Battle(client1Trainer, client2Trainer, new BattleGround());
            server.setState(PokeArenaServerState.WAITING_FOR_CLIENTS_ACTIONS);
            // Mise à jour des informations du client 1
            Update updateClient1 = new Update(battle.trainer1.getTrainer().getPokemonTeam(), battle.trainer2.getTrainer().getLeadingPkmn());
            server.sendPacket(server.getClient1WS(), createPacket(PokeArenaPacketType.UPDATE, updateClient1));
            // Mise à jour des informations du client 2
            Update updateClient2 = new Update(battle.trainer2.getTrainer().getPokemonTeam(), battle.trainer1.getTrainer().getLeadingPkmn());
            server.sendPacket(server.getClient2WS(), createPacket(PokeArenaPacketType.UPDATE, updateClient2));
        }
    }

    /**
     * Traite un paquet de type Action et retourne la réponse associé.
     * La réponse peut être nulle si le paquet entré en paramètre ne nécessite pas de réponse.
     *
     * @param ws      Connexion associé au paquet à traiter.
     * @param request Paquet à traiter.
     * @return Paquet de réponse.
     */
    protected PokeArenaPacket processActionPacket(WebSocket ws, PokeArenaPacket request) {
        PokeArenaPacket response;
        switch (server.getState()) {
            case WAITING_FOR_CLIENTS_ACTIONS:
                if (ws == server.getClient1WS()) {
                    //client1Action = request.getAction();
                    server.setState(PokeArenaServerState.WAITING_FOR_CLIENT_2_ACTION);
                    break;
                } else if (ws == server.getClient2WS()) {
                    //client2Action = request.getAction();
                    server.setState(PokeArenaServerState.WAITING_FOR_CLIENT_1_ACTION);
                    break;
                }
                // On n'utilise pas de break pour utiliser le cas default du switch
            case WAITING_FOR_CLIENT_1_ACTION:
                if (ws == server.getClient1WS()) {
                    //client1Action = request.getAction();
                    server.setState(PokeArenaServerState.PROCESSING_ACTIONS);
                    // Qqchose retour = battle.evaluateActions(client1Action, client2Action);
                    // En fonction du retour changer état du serveur : WAITING_FOR_CLIENTS_ACTIONS, BATTLE_ENDED...
                    // Puis envoyer un message UPDATE au client (mise à jour HP...)
                    break;
                }
            case WAITING_FOR_CLIENT_2_ACTION:
                if (ws == server.getClient2WS()) {
                    //client1Action = request.getAction();
                    server.setState(PokeArenaServerState.PROCESSING_ACTIONS);
                    // Pareil que en haut
                    break;
                }
            default:
                // Envoyer un paquet d'erreur
                // reponse = createPacket(ClassePaquetErreur, null);
        }
        return response = null;
    }

    public Battle getBattle() {
        return battle;
    }

}
