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
            case FORFEIT:
                if (ws == server.getClient1WS()) {
                    server.sendWin(server.getClient2WS());
                    server.setState(PokeArenaServerState.CLIENT_2_WON);
                } else if (ws == server.getClient2WS()) {
                    server.sendWin(server.getClient1WS());
                    server.setState(PokeArenaServerState.CLIENT_1_WON);
                }
                response = createPacket(PokeArenaPacketType.LOSE, null);
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
                response = processActionPacket(ws, request);
                break;
            case CHANGEPOKEMON:
                switch (server.getState()) {
                    case WAITING_FOR_CLIENT_1_CHANGEPKMN:
                        if (ws == server.getClient1WS()) {
                            battle.trainer1.getTrainer().changePokemon(((PokeArenaChangePokemonPacket) request).getChangePkmn().getIndex());
                            response = createPacket(PokeArenaPacketType.UPDATE, getClient1Update()); // Message d'update au client 1
                            server.sendUpdate(server.getClient2WS(), getClient2Update()); // Envoie de l'update au client 2
                            server.setState(PokeArenaServerState.WAITING_FOR_CLIENTS_ACTIONS);
                        } else {
                            response = null;
                        }
                        break;
                    case WAITING_FOR_CLIENT_2_CHANGEPKMN:
                        if (ws == server.getClient2WS()) {
                            battle.trainer2.getTrainer().changePokemon(((PokeArenaChangePokemonPacket) request).getChangePkmn().getIndex());
                            response = createPacket(PokeArenaPacketType.UPDATE, getClient2Update()); // Message d'update au client 2
                            server.sendUpdate(server.getClient1WS(), getClient1Update()); // Envoie de l'update au client 1
                            server.setState(PokeArenaServerState.WAITING_FOR_CLIENTS_ACTIONS);
                        } else {
                            response = null;
                        }
                        break;
                    case WAITING_FOR_CLIENT_1_ACTION:
                    case WAITING_FOR_CLIENT_2_ACTION:
                    case WAITING_FOR_CLIENTS_ACTIONS:
                        response = processActionPacket(ws, request);
                        break;
                    default:
                        response = null;
                        break;
                }
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
            server.sendUpdate(server.getClient1WS(), getClient1Update());
            // Mise à jour des informations du client 2
            server.sendUpdate(server.getClient2WS(), getClient2Update());
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
        Action action;
        switch (request.getType()) {
            case MOVE:
                action = ((PokeArenaMovePacket) request).getMove();
                break;
            case ACTION:
                action = ((PokeArenaActionPacket) request).getAction();
                break;
            case CHANGEPOKEMON:
                action = ((PokeArenaChangePokemonPacket) request).getChangePkmn();
                break;
            default:
                action = null;
        }

        switch (server.getState()) {
            case WAITING_FOR_CLIENTS_ACTIONS:
                if (ws == server.getClient1WS()) {
                    client1Action = action;
                    server.setState(PokeArenaServerState.WAITING_FOR_CLIENT_2_ACTION);
                    response = null;
                    break;
                } else if (ws == server.getClient2WS()) {
                    client2Action = action;
                    server.setState(PokeArenaServerState.WAITING_FOR_CLIENT_1_ACTION);
                    response = null;
                    break;
                }
                // On n'utilise pas de break pour utiliser le cas default du switch
            case WAITING_FOR_CLIENT_1_ACTION:
                if (ws == server.getClient1WS()) {
                    client1Action = action;
                    server.setState(PokeArenaServerState.PROCESSING_ACTIONS);

                    handleActions(client1Action, client2Action);

                    server.sendUpdate(server.getClient2WS(), getClient2Update()); // Envoie de l'update au client 2
                    response = createPacket(PokeArenaPacketType.UPDATE, getClient1Update()); // Envoie de l'update au client 1
                    break;
                }
            case WAITING_FOR_CLIENT_2_ACTION:
                if (ws == server.getClient2WS()) {
                    client2Action = action;
                    server.setState(PokeArenaServerState.PROCESSING_ACTIONS);

                    handleActions(client1Action, client2Action);

                    server.sendUpdate(server.getClient1WS(), getClient1Update()); // Envoie de l'update au client 1
                    response = createPacket(PokeArenaPacketType.UPDATE, getClient2Update()); // Envoie de l'update au client 2
                    break;
                }
            default:
                response = null;
                // Envoyer un paquet d'erreur
                // reponse = createPacket(ClassePaquetErreur, null);
        }
        return response;
    }

    public void handleActions(Action T1action, Action T2action) {
        TrainerAction trainer1 = battle.trainer1;
        TrainerAction trainer2 = battle.trainer2;
        trainer1.pairWith(T1action);
        trainer2.pairWith(T2action);
        TrainerAction firstToAct = battle.calculatePriority();
        TrainerAction secondToAct = (firstToAct == trainer1 ? trainer2 : trainer1);
        battle.apply(firstToAct);
        if (!secondToAct.getTrainer().getLeadingPkmn().isKO()) {
            battle.apply(secondToAct);
            if (firstToAct.getTrainer().getLeadingPkmn().isKO()) {
                if (!firstToAct.getTrainer().hasPokemonLeft()) {
                    if (firstToAct == trainer1) {
                        server.sendLose(server.getClient1WS());
                        server.sendWin(server.getClient2WS());
                        server.setState(PokeArenaServerState.CLIENT_2_WON);
                    } else {
                        server.sendWin(server.getClient1WS());
                        server.sendLose(server.getClient2WS());
                        server.setState(PokeArenaServerState.CLIENT_1_WON);
                    }
                } else {
                    if (firstToAct == trainer1) {
                        server.setState(PokeArenaServerState.WAITING_FOR_CLIENT_1_CHANGEPKMN);
                    } else {
                        server.setState(PokeArenaServerState.WAITING_FOR_CLIENT_2_CHANGEPKMN);
                    }
                }
            } else {
                server.setState(PokeArenaServerState.WAITING_FOR_CLIENTS_ACTIONS);
            }
        } else {
            if (!secondToAct.getTrainer().hasPokemonLeft()) {
                if (secondToAct == trainer1) {
                    server.sendLose(server.getClient1WS());
                    server.sendWin(server.getClient2WS());
                    server.setState(PokeArenaServerState.CLIENT_2_WON);
                } else {
                    server.sendWin(server.getClient1WS());
                    server.sendLose(server.getClient2WS());
                    server.setState(PokeArenaServerState.CLIENT_1_WON);
                }
            } else {
                if (secondToAct == trainer1) {
                    server.setState(PokeArenaServerState.WAITING_FOR_CLIENT_1_CHANGEPKMN);
                } else {
                    server.setState(PokeArenaServerState.WAITING_FOR_CLIENT_2_CHANGEPKMN);
                }
            }
        }
    }

    public Battle getBattle() {
        return battle;
    }

    public Update getClient1Update() {
        return new Update(battle.trainer1.getTrainer().getPokemonTeam(), battle.trainer2.getTrainer().getLeadingPkmn());
    }

    public Update getClient2Update() {
        return new Update(battle.trainer2.getTrainer().getPokemonTeam(), battle.trainer1.getTrainer().getLeadingPkmn());
    }

}
