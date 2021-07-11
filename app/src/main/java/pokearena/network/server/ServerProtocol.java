package pokearena.network.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pokearena.battle.*;
import pokearena.network.packets.*;
import pokearena.network.Protocol;
import pokearena.network.Update;
import org.java_websocket.WebSocket;
import pokearena.network.server.states.*;

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

    private final Logger logger = LoggerFactory.getLogger(Server.class);

    /**
     * Serveur PokeArena qui utilise le protocole.
     */
    private final Server server;

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
        switch (request.getType()) {
            case PING:
                server.getState().onPingPacket(ws);
                break;
            case REFRESH:
                server.getState().onRefreshPacket(ws, request);
                break;
            case FORFEIT:
                server.getState().onForfeitPacket(ws, request);
                break;
            case TEAM:
                server.getState().onTeamPacket(ws, request);
                break;
            case TEXT:
                server.getState().onTextPacket(ws, request);
                break;
            case MOVE:
                server.getState().onMovePacket(ws, request);
                break;
            case CHANGEPOKEMON:
                server.getState().onChangePokemonPacket(ws, request);
                break;
            default:
                logger.warn("Unhandled case in processPacket switch");
        }
    }

    /**
     * Lancer le combat.
     */
    public void startBattle() {
        if (server.getState().getStateName() == ServerStateName.WAITING_FOR_START) {
            battle = new Battle(client1Trainer, client2Trainer, new BattleGround());
            server.setState(new WaitingForClientsActionState(this));
            // Mise à jour des informations du client 1
            server.sendUpdate(server.getClient1WS(), generateClient1Update());
            // Mise à jour des informations du client 2
            server.sendUpdate(server.getClient2WS(), generateClient2Update());
        }
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
                        server.setState(new Client2WonState(this));
                    } else {
                        server.sendWin(server.getClient1WS());
                        server.sendLose(server.getClient2WS());
                        server.setState(new Client1WonState(this));
                    }
                } else {
                    if (firstToAct == trainer1) {
                        server.setState(new WaitingForClient1ChangePkmnState(this));
                    } else {
                        server.setState(new WaitingForClient2ChangePkmnState(this));
                    }
                }
            } else {
                server.setState(new WaitingForClientsActionState(this));

            }
        } else {
            if (!secondToAct.getTrainer().hasPokemonLeft()) {
                if (secondToAct == trainer1) {
                    server.sendLose(server.getClient1WS());
                    server.sendWin(server.getClient2WS());
                    server.setState(new Client2WonState(this));
                } else {
                    server.sendWin(server.getClient1WS());
                    server.sendLose(server.getClient2WS());
                    server.setState(new Client1WonState(this));
                }
            } else {
                if (secondToAct == trainer1) {
                    server.setState(new WaitingForClient1ChangePkmnState(this));
                } else {
                    server.setState(new WaitingForClient2ChangePkmnState(this));
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

    public boolean isClient1(WebSocket ws) {
        return ws == server.getClient1WS();
    }

    public boolean isClient2(WebSocket ws) {
        return ws == server.getClient2WS();
    }

    public String identifyWsUser(WebSocket ws) {
        if (isClient1(ws)) {
            return "client 1";
        } else if (isClient2(ws)) {
            return "client 2";
        } else {
            return "unknown client";
        }
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

    public Server getServer() {
        return server;
    }

    public Move getLastClient1Move() {
        return lastClient1Move;
    }

    public Move getLastClient2Move() {
        return lastClient2Move;
    }

    public void setClient1Trainer(Trainer client1Trainer) {
        this.client1Trainer = client1Trainer;
    }

    public void setLastClient1Move(Move lastClient1Move) {
        this.lastClient1Move = lastClient1Move;
    }

    public void setClient2Trainer(Trainer client2Trainer) {
        this.client2Trainer = client2Trainer;
    }

    public void setLastClient2Move(Move lastClient2Move) {
        this.lastClient2Move = lastClient2Move;
    }

    public Action getClient1Action() {
        return client1Action;
    }

    public void setClient1Action(Action client1Action) {
        this.client1Action = client1Action;
    }

    public Action getClient2Action() {
        return client2Action;
    }

    public void setClient2Action(Action client2Action) {
        this.client2Action = client2Action;
    }

}
