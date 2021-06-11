package PokeArenaNetwork;

import Model.Pokemon;
import Model.Team;
import Model.Trainer;
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
            case UPDATE:
                switch (client.getState()) {
                    case WAITING_FOR_START:
                    case ACTION_SENT:
                        client.setState(PokeArenaClientState.NEED_TO_SEND_ACTION);
                        break;
                    default:
                        break;
                }
                if (client.getState() == PokeArenaClientState.WAITING_FOR_START) {
                    client.setState(PokeArenaClientState.NEED_TO_SEND_ACTION);
                }
                if (trainer == null) {
                    trainer = new Trainer("Nom Joueur", team);
                } else {
                    Update update = ((PokeArenaUpdatePacket) request).getUpdate();
                    trainer.setPokemonTeam(update.getTeam());
                    this.opponentPokemon = update.getOpponentPokemon();
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

    public Pokemon getOpponentPokemon () {
        return opponentPokemon;
    }

}
