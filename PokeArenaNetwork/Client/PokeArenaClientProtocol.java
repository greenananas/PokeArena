package PokeArenaNetwork.Client;

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

public class PokeArenaClientProtocol extends PokeArenaProtocol {

    /**
     * Client PokeArena qui utilise le protocole.
     */
    private PokeArenaClient client;

    private Trainer trainer;

    private Pokemon opponentPokemon;

    private Team team;

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

    public void setTeam(Team team) {
        this.team = team;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public Pokemon getOpponentPokemon() {
        return opponentPokemon;
    }

}
