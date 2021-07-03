package pokearena.network.server;

import pokearena.battle.*;
import pokearena.network.packets.*;
import pokearena.network.Protocol;
import pokearena.network.Update;
import org.java_websocket.WebSocket;

import static pokearena.network.Utils.createPacket;

/**
 * Protocole associé à un serveur PokeArena.
 * Le protocole permet de :
 * <ul>
 *     <li>Déterminer les actions que le serveur doit effectuer en fonction de son état et des messages reçus</li>
 *     <li>Modifier l'état du combat en fonction des informations reçues par le serveur.</li>
 * </ul>
 */
public class ServerProtocol extends Protocol {

    /**
     * Serveur PokeArena qui utilise le protocole.
     */
    private Server server;

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

    /**
     * Dernière attaque envoyé par le client 1.
     */
    private Move lastClient1Move;

    /**
     * Dernière attaque envoyé par le client 2.
     */
    private Move lastClient2Move;

    /**
     * Trainer du client 1, utilisée uniquement pour créer l'objet Battle lors de l'initialisation du combat.
     */
    private Trainer client1Trainer;

    /**
     * Trainer du client 2, utilisée uniquement pour créer l'objet Battle lors de l'initialisation du combat.
     */
    private Trainer client2Trainer;

    /**
     * Créer un protocole qui va manipuler le serveur rentré en paramètre.
     *
     * @param server Serveur qui va être manipulé par le protocole.
     */
    public ServerProtocol(Server server) {
        this.server = server;
    }

    /**
     * Traite un paquet et retourne la réponse associé.
     * La réponse peut être nulle si le paquet entré en paramètre ne nécessite pas de réponse.
     *
     * @param ws      Connexion associé au paquet à traiter.
     * @param request Paquet à traiter.
     */
    public void processPacket(WebSocket ws, Packet request) {
        Packet response;
        switch (request.getType()) {
            case PING:
                response = createPacket(PacketType.PONG, null);
                break;
            case REFRESH:
                Team trainerTeam = null;
                Pokemon opponentPokemon = null;
                Move opponentMove = null;
                if (ws == server.getClient1WS()) {
                    trainerTeam = battle.trainer1.getTrainer().getPokemonTeam();
                    opponentPokemon = battle.trainer2.getTrainer().getLeadingPkmn();
                    opponentMove = lastClient2Move;
                } else if (ws == server.getClient2WS()) {
                    trainerTeam = battle.trainer2.getTrainer().getPokemonTeam();
                    opponentPokemon = battle.trainer1.getTrainer().getLeadingPkmn();
                    opponentMove = lastClient1Move;
                }
                response = (trainerTeam != null && opponentPokemon != null)
                        ? createPacket(PacketType.UPDATE, new Update(trainerTeam, opponentPokemon, opponentMove))
                        : null;
                break;
            case FORFEIT:
                if (ws == server.getClient1WS()) {
                    server.sendWin(server.getClient2WS());
                    server.setState(ServerState.CLIENT_2_WON);
                } else if (ws == server.getClient2WS()) {
                    server.sendWin(server.getClient1WS());
                    server.setState(ServerState.CLIENT_1_WON);
                }
                response = createPacket(PacketType.LOSE, null);
                break;
            case TEAM:
                var team = ((TeamPacket) request).getTeam();
                System.out.println("Équipe reçue :");
                for (Pokemon p : team.getPokemons()) {
                    System.out.println(" - " + p);
                }
                switch (server.getState()) {
                    case WAITING_FOR_CLIENTS_TEAM:
                        if (ws == server.getClient1WS()) {
                            client1Trainer = new Trainer("Joueur 1", team);
                            server.setState(ServerState.WAITING_FOR_CLIENT_2_TEAM);
                        } else if (ws == server.getClient2WS()) {
                            client2Trainer = new Trainer("Joueur 2", team);
                            server.setState(ServerState.WAITING_FOR_CLIENT_1_TEAM);
                        }
                        break;
                    case WAITING_FOR_CLIENT_1_TEAM:
                        if (ws == server.getClient1WS()) {
                            client1Trainer = new Trainer("Joueur 1", team);
                            server.setState(ServerState.WAITING_FOR_START);
                        }
                        break;
                    case WAITING_FOR_CLIENT_2_TEAM:
                        if (ws == server.getClient2WS()) {
                            client2Trainer = new Trainer("Joueur 2", team);
                            server.setState(ServerState.WAITING_FOR_START);
                        }
                        break;
                    default:
                        break;
                }
                response = null;
                break;
            case TEXT:
                String clt = ws == server.getClient1WS() ? "Client 1" : "Client 2";
                System.out.println(clt + " dit : " + ((TextPacket) request).getText());
                response = null;
                break;
            case MOVE:
                response = processActionPacket(ws, request);
                break;
            case CHANGEPOKEMON:
                switch (server.getState()) {
                    case WAITING_FOR_CLIENT_1_CHANGEPKMN:
                        if (ws == server.getClient1WS()) {
                            battle.trainer1.getTrainer().changePokemon(((ChangePokemonPacket) request).getChangePkmn().getIndex());
                            response = createPacket(PacketType.UPDATE, generateClient1Update()); // Message d'update au client 1
                            server.sendUpdate(server.getClient2WS(), generateClient2Update()); // Envoie de l'update au client 2
                            server.setState(ServerState.WAITING_FOR_CLIENTS_ACTIONS);
                        } else {
                            response = null;
                        }
                        break;
                    case WAITING_FOR_CLIENT_2_CHANGEPKMN:
                        if (ws == server.getClient2WS()) {
                            battle.trainer2.getTrainer().changePokemon(((ChangePokemonPacket) request).getChangePkmn().getIndex());
                            response = createPacket(PacketType.UPDATE, generateClient2Update()); // Message d'update au client 2
                            server.sendUpdate(server.getClient1WS(), generateClient1Update()); // Envoie de l'update au client 1
                            server.setState(ServerState.WAITING_FOR_CLIENTS_ACTIONS);
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
        if (server.getState() == ServerState.WAITING_FOR_START) {
            battle = new Battle(client1Trainer, client2Trainer, new BattleGround());
            server.setState(ServerState.WAITING_FOR_CLIENTS_ACTIONS);
            // Mise à jour des informations du client 1
            server.sendUpdate(server.getClient1WS(), generateClient1Update());
            // Mise à jour des informations du client 2
            server.sendUpdate(server.getClient2WS(), generateClient2Update());
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
    protected Packet processActionPacket(WebSocket ws, Packet request) {
        Packet response;
        Action action;
        switch (request.getType()) {
            case MOVE:
                action = ((MovePacket) request).getMove();
                break;
            case ACTION:
                action = ((ActionPacket) request).getAction();
                break;
            case CHANGEPOKEMON:
                action = ((ChangePokemonPacket) request).getChangePkmn();
                break;
            default:
                action = null;
        }

        switch (server.getState()) {
            case WAITING_FOR_CLIENTS_ACTIONS:
                if (ws == server.getClient1WS()) {
                    client1Action = action;
                    if (action instanceof Move) lastClient1Move = (Move) action;
                    server.setState(ServerState.WAITING_FOR_CLIENT_2_ACTION);
                    response = null;
                    break;
                } else if (ws == server.getClient2WS()) {
                    if (action instanceof Move) lastClient2Move = (Move) action;
                    client2Action = action;
                    server.setState(ServerState.WAITING_FOR_CLIENT_1_ACTION);
                    response = null;
                    break;
                }
                response = null;
                break;
            case WAITING_FOR_CLIENT_1_ACTION:
                if (ws == server.getClient1WS()) {
                    client1Action = action;
                    if (action instanceof Move) lastClient1Move = (Move) action;
                    server.setState(ServerState.PROCESSING_ACTIONS);

                    handleActions(client1Action, client2Action);

                    server.sendUpdate(server.getClient2WS(), generateClient2Update()); // Envoie de l'update au client 2
                    response = createPacket(PacketType.UPDATE, generateClient1Update()); // Envoie de l'update au client 1
                    break;
                }
                response = null;
                break;
            case WAITING_FOR_CLIENT_2_ACTION:
                if (ws == server.getClient2WS()) {
                    client2Action = action;
                    if (action instanceof Move) lastClient2Move = (Move) action;
                    server.setState(ServerState.PROCESSING_ACTIONS);

                    handleActions(client1Action, client2Action);

                    server.sendUpdate(server.getClient1WS(), generateClient1Update()); // Envoie de l'update au client 1
                    response = createPacket(PacketType.UPDATE, generateClient2Update()); // Envoie de l'update au client 2
                    break;
                }
                response = null;
                break;
            default:
                response = null;
                // Envoyer un paquet d'erreur
        }
        return response;
    }

    /**
     * Applique les actions des deux joueurs au combat.
     * Cela va engendrer :
     * <ul>
     *     <li>La modification des informations du combat.</li>
     *     <li>La modification de l'état du serveur.</li>
     *     <li>L'envoie de paquet aux clients.</li>
     * </ul>
     *
     * @param t1Action Action du joueur 1.
     * @param t2action Action du joueur 2.
     */
    public void handleActions(Action t1Action, Action t2action) {
        TrainerAction trainer1 = battle.trainer1;
        TrainerAction trainer2 = battle.trainer2;
        trainer1.pairWith(t1Action);
        trainer2.pairWith(t2action);
        TrainerAction firstToAct = battle.calculatePriority();
        TrainerAction secondToAct = (firstToAct == trainer1 ? trainer2 : trainer1);
        battle.apply(firstToAct, secondToAct, true);
        if (!secondToAct.getTrainer().getLeadingPkmn().isKO()) {
            battle.apply(secondToAct, firstToAct, false);
            if (firstToAct.getTrainer().getLeadingPkmn().isKO()) {
                if (!firstToAct.getTrainer().hasPokemonLeft()) {
                    if (firstToAct == trainer1) {
                        server.sendLose(server.getClient1WS());
                        server.sendWin(server.getClient2WS());
                        server.setState(ServerState.CLIENT_2_WON);
                    } else {
                        server.sendWin(server.getClient1WS());
                        server.sendLose(server.getClient2WS());
                        server.setState(ServerState.CLIENT_1_WON);
                    }
                } else {
                    if (firstToAct == trainer1) {
                        server.setState(ServerState.WAITING_FOR_CLIENT_1_CHANGEPKMN);
                    } else {
                        server.setState(ServerState.WAITING_FOR_CLIENT_2_CHANGEPKMN);
                    }
                }
            } else {
                server.setState(ServerState.WAITING_FOR_CLIENTS_ACTIONS);
            }
        } else {
            if (!secondToAct.getTrainer().hasPokemonLeft()) {
                if (secondToAct == trainer1) {
                    server.sendLose(server.getClient1WS());
                    server.sendWin(server.getClient2WS());
                    server.setState(ServerState.CLIENT_2_WON);
                } else {
                    server.sendWin(server.getClient1WS());
                    server.sendLose(server.getClient2WS());
                    server.setState(ServerState.CLIENT_1_WON);
                }
            } else {
                if (secondToAct == trainer1) {
                    server.setState(ServerState.WAITING_FOR_CLIENT_1_CHANGEPKMN);
                } else {
                    server.setState(ServerState.WAITING_FOR_CLIENT_2_CHANGEPKMN);
                }
            }
        }
    }

    /**
     * Obtenir le combat manipulé par le protocole.
     *
     * @return Combat manipulé par le procotole.
     */
    public Battle getBattle() {
        return battle;
    }

    /**
     * Générer la mise à jour des informations du joueur 1.
     *
     * @return Mise à jour des informations du joueur 1.
     */
    public Update generateClient1Update() {
        return new Update(battle.trainer1.getTrainer().getPokemonTeam(), battle.trainer2.getTrainer().getLeadingPkmn(), lastClient2Move);
    }

    /**
     * Générer la mise à jour des informations du joueur 2.
     *
     * @return Mise à jour des informations du joueur 2.
     */
    public Update generateClient2Update() {
        return new Update(battle.trainer2.getTrainer().getPokemonTeam(), battle.trainer1.getTrainer().getLeadingPkmn(), lastClient1Move);
    }

}
