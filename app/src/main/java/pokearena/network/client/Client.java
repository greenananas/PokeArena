package pokearena.network.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pokearena.battle.*;
import pokearena.network.packets.Packet;
import pokearena.network.packets.PacketType;
import pokearena.network.packets.UpdatePacket;
import pokearena.network.Utils;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Client permettant l'échange de messages à un serveur PokeArena via l'utilisation de WebSocket.
 *
 * @author Louis
 */
public class Client extends WebSocketClient {

    /**
     * État du client.
     */
    private ClientState state = ClientState.NOT_CONNECTED;

    private final Logger logger = LoggerFactory.getLogger(Client.class);

    /**
     * Protocole utilisé pour traiter les paquets et gérer l'état du client.
     */
    private final ClientProtocol protocol = new ClientProtocol(this);

    /**
     * Créer un client PokeArenaClient à partir d'une URI.
     *
     * @param serveurURI URI du serveur au format de type ws://hostname:portnumber
     */
    public Client(URI serveurURI) {
        super(serveurURI);
    }

    /**
     * Créer un client PokeArenaClient à partir d'une URI au format String.
     *
     * @param serveurURI URI du serveur au format de type ws://hostname:portnumber
     * @throws URISyntaxException Soulevé si la chaîne de caractères n'est pas à un format valide d'URI.
     */
    public Client(String serveurURI) throws URISyntaxException {
        this(new URI(serveurURI));
    }

    /**
     * @param hostname   Nom d'hôte du serveur
     * @param portNumber Numéro de port du serveur.
     * @throws URISyntaxException Soulevé si le nom d'hôte ou le numéro de port ne sont pas valides.
     */
    public Client(String hostname, int portNumber) throws URISyntaxException {
        this("ws://" + hostname + ":" + portNumber);
    }

    /**
     * Méthode appelée lors de l'ouverture d'une connexion.
     *
     * @param serverHandshake Informations relatives à la connexion.
     */
    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        if (state == ClientState.NOT_CONNECTED) {
            logger.info("Ouverture de la connexion");
            state = ClientState.NEED_TO_SEND_TEAM;
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
        Packet packet = Utils.parseJsonPacket(message);
        if (packet.getType() == PacketType.UPDATE && logger.isDebugEnabled()) {
            var up = ((UpdatePacket) packet).getUpdate();
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
    public void sendPacket(Packet packet) {
        send(Utils.toJson(packet));
    }

    /**
     * Envoyer un ping au serveur.
     */
    @Override
    public void sendPing() {
        sendPacket(Utils.createPacket(PacketType.PING, null));
    }

    /**
     * Envoyer une demande de rafraichissement des informations du combat au serveur.
     */
    public void sendRefresh() {
        sendPacket(Utils.createPacket(PacketType.REFRESH, null));
    }

    /**
     * Envoyer un message texte au serveur.
     *
     * @param text Texte du message qui va être envoyé au serveur.
     */
    public void sendText(String text) {
        sendPacket(Utils.createPacket(PacketType.TEXT, text));
    }

    /**
     * Envoyer une équipe au serveur.
     *
     * @param team Équipe qui va être envoyée au serveur.
     */
    public void sendTeam(Team team) {
        if (state == ClientState.NEED_TO_SEND_TEAM) {
            state = ClientState.WAITING_FOR_START;
        }
        protocol.setTeam(team);
        sendPacket(Utils.createPacket(PacketType.TEAM, team));
    }


    /**
     * Envoyer une action au serveur.
     *
     * @param action Action qui va être envoyée au serveur.
     */
    public void sendAction(Action action) {
        if (state == ClientState.NEED_TO_SEND_ACTION) {
            state = ClientState.ACTION_SENT;
        }
        sendPacket(Utils.createPacket(PacketType.ACTION, action));
    }

    /**
     * Envoyer une attaque au serveur.
     *
     * @param move Attaque qui va être envoyée au serveur.
     */
    public void sendMove(Move move) {
        if (state == ClientState.NEED_TO_SEND_ACTION) {
            state = ClientState.ACTION_SENT;
        }
        sendPacket(Utils.createPacket(PacketType.MOVE, move));
    }

    /**
     * Envoyer un changement de Pokémon au serveur.
     *
     * @param changePkmn Changement de Pokémon qui va être envoyé au serveur.
     */
    public void sendChangePkmn(ChangePkmn changePkmn) {
        if (state == ClientState.NEED_TO_SEND_ACTION) {
            state = ClientState.ACTION_SENT;
        }
        sendPacket(Utils.createPacket(PacketType.CHANGEPOKEMON, changePkmn));
    }

    /**
     * Envoyer une déclaration de forfait au serveur.
     */
    public void sendForfeit() {
        sendPacket(Utils.createPacket(PacketType.FORFEIT, null));
    }

    /**
     * Obtenir l'état du client.
     *
     * @return État du client.
     */
    public ClientState getState() {
        return state;
    }

    /**
     * Modifier l'état du client.
     *
     * @param state État du client.
     */
    public void setState(ClientState state) {
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
