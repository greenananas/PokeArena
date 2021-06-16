package PokeArenaNetwork.Client;

import Model.Move;
import Model.Pokemon;
import Model.Team;
import Model.Trainer;
import PokeArenaNetwork.Packets.PokeArenaPacket;
import PokeArenaNetwork.Packets.PokeArenaPacketType;
import PokeArenaNetwork.Packets.PokeArenaTextPacket;
import PokeArenaNetwork.Packets.PokeArenaUpdatePacket;
import PokeArenaNetwork.PokeArenaProtocol;
import PokeArenaNetwork.Update;
import org.java_websocket.WebSocket;

import static PokeArenaNetwork.PokeArenaUtilities.createPacket;

/**
 * Protocole associé à un client PokeArena.
 * Le protocole permet de :
 * <ul>
 *     <li>Déterminer les actions que le client doit effectuer en fonction de son état et des messages reçus</li>
 *     <li>Modifier l'état du dresseur en fonction des informations reçues par le client.</li>
 * </ul>
 *
 * @author Louis
 */
public class PokeArenaClientProtocol extends PokeArenaProtocol {

    /**
     * Client PokeArena qui utilise le protocole.
     */
    private PokeArenaClient client;

    /**
     * Dresseur qui est manipulé par le protocole.
     */
    private Trainer trainer;

    /**
     * Pokémon au combat de l'adversaire.
     */
    private Pokemon opponentPokemon;

    /**
     * Dernière attaque de l'adversaire.
     */
    private Move opponentMove;

    /**
     * Équipe du dresseur, utilisé uniquement lors de l'initialisation de ce dernier.
     */
    private Team team;

    /**
     * Créer un protocole qui va manipuler le client rentré en paramètre.
     *
     * @param client Client qui va être manipulé par le protocole.
     */
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
            case WIN:
                client.setState(PokeArenaClientState.BATTLE_WON);
                response = null;
                break;
            case LOSE:
                client.setState(PokeArenaClientState.BATTLE_LOST);
                response = null;
                break;
            case UPDATE:

                // Mise à jour des informations du combat
                Update update = ((PokeArenaUpdatePacket) request).getUpdate();
                this.opponentPokemon = update.getOpponentPokemon();
                this.opponentMove = update.getOppenentMove();
                if (trainer == null) {
                    trainer = new Trainer("Nom Joueur", team);
                } else {
                    trainer.setPokemonTeam(update.getTeam());
                }

                // Changement de l'état du cient
                switch (client.getState()) {
                    case WAITING_FOR_START:
                        client.setState(PokeArenaClientState.NEED_TO_SEND_ACTION);
                        break;
                    case ACTION_SENT:
                    case NEED_TO_SEND_CHANGEPKMN:
                        if (client.getTrainer().getLeadingPkmn().isKO()) {
                            client.setState(PokeArenaClientState.NEED_TO_SEND_CHANGEPKMN);
                        } else {
                            client.setState(PokeArenaClientState.NEED_TO_SEND_ACTION);
                        }
                        break;
                    default:
                        break;
                }

                response = null;
                break;
            default:
                response = null;
        }
        if (response != null) {
            client.sendPacket(response);
        }
    }

    /**
     * Modifier l'équipe de Pokémon.
     *
     * @param team Nouvelle valeur de l'équipe de Pokémon.
     */
    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     * Obtenir le dresseur manipulé par le protocole.
     *
     * @return Dresseur manipulé par le protocole.
     */
    public Trainer getTrainer() {
        return trainer;
    }

    /**
     * Obtenir le pokémon au combat de l'adversaire.
     *
     * @return Pokémon au combat de l'adversaire.
     */
    public Pokemon getOpponentPokemon() {
        return opponentPokemon;
    }

    public Move getOpponentMove() {
        return opponentMove;
    }

}
