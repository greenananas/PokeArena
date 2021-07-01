package pokearena.network.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pokearena.battle.*;
import pokearena.network.packets.PokeArenaPacket;
import pokearena.network.packets.PokeArenaPacketType;
import pokearena.network.packets.PokeArenaUpdatePacket;
import pokearena.network.PokeArenaUtilities;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Client permettant l'échange de messages à un serveur PokeArena via l'utilisation de WebSocket.
 *
 * @author Louis
 */
public class PokeArenaClient extends WebSocketClient {

    /**
     * État du client.
     */
    private PokeArenaClientState state = PokeArenaClientState.NOT_CONNECTED;

    private final Logger logger = LoggerFactory.getLogger(PokeArenaClient.class);

    /**
     * Protocole utilisé pour traiter les paquets et gérer l'état du client.
     */
    private final PokeArenaClientProtocol protocol = new PokeArenaClientProtocol(this);

    /**
     * Créer un client PokeArenaClient à partir d'une URI.
     *
     * @param serveurURI URI du serveur au format de type ws://hostname:portnumber
     */
    public PokeArenaClient(URI serveurURI) {
        super(serveurURI);
    }

    /**
     * Créer un client PokeArenaClient à partir d'une URI au format String.
     *
     * @param serveurURI URI du serveur au format de type ws://hostname:portnumber
     * @throws URISyntaxException Soulevé si la chaîne de caractères n'est pas à un format valide d'URI.
     */
    public PokeArenaClient(String serveurURI) throws URISyntaxException {
        this(new URI(serveurURI));
    }

    /**
     * @param hostname   Nom d'hôte du serveur
     * @param portNumber Numéro de port du serveur.
     * @throws URISyntaxException Soulevé si le nom d'hôte ou le numéro de port ne sont pas valides.
     */
    public PokeArenaClient(String hostname, int portNumber) throws URISyntaxException {
        this("ws://" + hostname + ":" + portNumber);
    }

    /**
     * Méthode appelée lors de l'ouverture d'une connexion.
     *
     * @param serverHandshake Informations relatives à la connexion.
     */
    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        if (state == PokeArenaClientState.NOT_CONNECTED) {
            logger.info("Ouverture de la connexion");
            state = PokeArenaClientState.NEED_TO_SEND_TEAM;
        } else {
            //TODO: Lever une erreur
        }
    }

    /**
     * Méthode appelée lors de la reception d'un message au sein d'une connexion.
     *
     * @param message Message décodé en UTF-8 qui vient d'être reçu.
     */
    @Override
    public void onMessage(String message) {
        PokeArenaPacket packet = PokeArenaUtilities.parseJsonPacket(message);
        if (packet.getType() == PokeArenaPacketType.UPDATE && logger.isDebugEnabled()) {
            var up = ((PokeArenaUpdatePacket) packet).getUpdate();
            StringBuilder updateInfo;
            updateInfo = new StringBuilder("Équipe :\n");
            for (Pokemon poke : up.getTeam().getPokemons()) {
                updateInfo.append("- ").append(poke.getName()).append("\n");
            }
            updateInfo.append("Pokémon adverse : ").append(up.getOpponentPokemon()).append("\n");
            updateInfo.append("Dernière attaque adverse : ").append(up.getOppenentMove());
            logger.debug("{}", updateInfo);
        } else {
            logger.debug("Message reçu : {}", message);
        }
        if (packet != null) protocol.processPacket(getConnection(), packet);
    }

    /**
     * Méthode appelée lors de la fermeture d'une connexion.
     *
     * @param code    Code.
     * @param message Informations additionnels.
     * @param remote  Indique si la connexion a été fermée par l'hôte distant.
     */
    @Override
    public void onClose(int code, String message, boolean remote) {
        logger.info("Connexion fermée avec code {}, informations aditionnels : {}", code, message);
    }

    /**
     * Méthode appelée quand un erreur se produit.
     * Si une erreur provoque l'échec d'une connexion alors la méthode onClose(...) sera appelée additionnellement.
     *
     * @param e Exception qui provoque l'erreur.
     */
    @Override
    public void onError(Exception e) {
        logger.error("", e);
    }

    /**
     * Envoyer un paquet au serveur.
     *
     * @param packet Paquet qui va être envoyé.
     */
    public void sendPacket(PokeArenaPacket packet) {
        send(PokeArenaUtilities.toJson(packet));
    }

    /**
     * Envoyer un ping au serveur.
     */
    @Override
    public void sendPing() {
        sendPacket(PokeArenaUtilities.createPacket(PokeArenaPacketType.PING, null));
    }

    /**
     * Envoyer une demande de rafraichissement des informations du combat au serveur.
     */
    public void sendRefresh() {
        sendPacket(PokeArenaUtilities.createPacket(PokeArenaPacketType.REFRESH, null));
    }

    /**
     * Envoyer un message texte au serveur.
     *
     * @param text Texte du message qui va être envoyé au serveur.
     */
    public void sendText(String text) {
        sendPacket(PokeArenaUtilities.createPacket(PokeArenaPacketType.TEXT, text));
    }

    /**
     * Envoyer une équipe au serveur.
     *
     * @param team Équipe qui va être envoyée au serveur.
     */
    public void sendTeam(Team team) {
        if (state == PokeArenaClientState.NEED_TO_SEND_TEAM) {
            state = PokeArenaClientState.WAITING_FOR_START;
        }
        protocol.setTeam(team);
        sendPacket(PokeArenaUtilities.createPacket(PokeArenaPacketType.TEAM, team));
    }


    /**
     * Envoyer une action au serveur.
     *
     * @param action Action qui va être envoyée au serveur.
     */
    public void sendAction(Action action) {
        if (state == PokeArenaClientState.NEED_TO_SEND_ACTION) {
            state = PokeArenaClientState.ACTION_SENT;
        }
        sendPacket(PokeArenaUtilities.createPacket(PokeArenaPacketType.ACTION, action));
    }

    /**
     * Envoyer une attaque au serveur.
     *
     * @param move Attaque qui va être envoyée au serveur.
     */
    public void sendMove(Move move) {
        if (state == PokeArenaClientState.NEED_TO_SEND_ACTION) {
            state = PokeArenaClientState.ACTION_SENT;
        }
        sendPacket(PokeArenaUtilities.createPacket(PokeArenaPacketType.MOVE, move));
    }

    /**
     * Envoyer un changement de Pokémon au serveur.
     *
     * @param changePkmn Changement de Pokémon qui va être envoyé au serveur.
     */
    public void sendChangePkmn(ChangePkmn changePkmn) {
        if (state == PokeArenaClientState.NEED_TO_SEND_ACTION) {
            state = PokeArenaClientState.ACTION_SENT;
        }
        sendPacket(PokeArenaUtilities.createPacket(PokeArenaPacketType.CHANGEPOKEMON, changePkmn));
    }

    /**
     * Envoyer une déclaration de forfait au serveur.
     */
    public void sendForfeit() {
        sendPacket(PokeArenaUtilities.createPacket(PokeArenaPacketType.FORFEIT, null));
    }

    /**
     * Obtenir l'état du client.
     *
     * @return État du client.
     */
    public PokeArenaClientState getState() {
        return state;
    }

    /**
     * Modifier l'état du client.
     *
     * @param state État du client.
     */
    public void setState(PokeArenaClientState state) {
        this.state = state;
    }

    /**
     * Obtenir le trainer qui est manipulé par le protocole.
     *
     * @return Trainer qui est manipulé par le protocole.
     */
    public Trainer getTrainer() {
        return protocol.getTrainer();
    }

    /**
     * Obtenir le pokémon au combat de l'adversaire.
     *
     * @return Pokémon au combat de l'adversaire.
     */
    public Pokemon getOpponentPokemon() {
        return protocol.getOpponentPokemon();
    }

    /**
     * Obtenir la dernière attaque de l'adversaire.
     *
     * @return Dernière attaque de l'adversaire.
     */
    public Move getOpponentMove() {
        return protocol.getOpponentMove();
    }

}
