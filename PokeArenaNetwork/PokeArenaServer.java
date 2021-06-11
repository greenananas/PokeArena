package PokeArenaNetwork;

import Model.Battle;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

public class PokeArenaServer extends WebSocketServer {

    /**
     * Nom ou adresse IP du serveur.
     */
    private String hostname;

    /**
     * Numéro de port du serveur.
     */
    private int portNumber;

    /**
     * État du serveur.
     */
    private PokeArenaServerState state;

    /**
     * Connexion associée au client 1.
     */
    private WebSocket client1WS;

    /**
     * Connexion associée au client 2.
     */
    private WebSocket client2WS;

    /**
     * Protocole utilisé pour traiter les paquets et gérer l'état du serveur.
     */
    private final PokeArenaServerProtocol protocol = new PokeArenaServerProtocol(this);

    /**
     * Créer un serveur PokeArenaServer.
     *
     * @param hostname   Nom ou adresse IP du serveur.
     * @param portNumber Numéro de port du serveur.
     */
    public PokeArenaServer(String hostname, int portNumber) {
        super(new InetSocketAddress(hostname, portNumber));
    }

    /**
     * Méthode appelée lors de l'ouverture d'une connexion.
     *
     * @param ws              Instance de connexion du entre le serveur et le client qui vient d'initier la connexion.
     * @param clientHandshake Informations relatives à la connexion.
     */
    @Override
    public void onOpen(WebSocket ws, ClientHandshake clientHandshake) {
        switch (state) {
            case WAITING_FOR_CLIENT1_TO_JOIN:
                client1WS = ws;
                sendPacket(ws, PokeArenaUtilities.createPacket(PokeArenaPacketType.TEXT, "Bienvenue sur le serveur, vous êtes le joueur 1"));
                state = PokeArenaServerState.WAITING_FOR_CLIENT2_TO_JOIN;
                break;
            case WAITING_FOR_CLIENT2_TO_JOIN:
                client2WS = ws;
                sendPacket(ws, PokeArenaUtilities.createPacket(PokeArenaPacketType.TEXT, "Bienvenue sur le serveur, vous êtes le joueur 2"));
                state = PokeArenaServerState.WAITING_FOR_CLIENTS_TEAM;
                break;
            default:
                ws.close();
        }
    }

    /**
     * Méthode appelée lors de la fermeture d'une connexion.
     *
     * @param ws      Instance de connexion entre le serveur et le client qui vient de fermer la connexion.
     * @param code    Code.
     * @param message Informations additionnels.
     * @param remote  Indique si la connexion a été fermée par l'hôte distant.
     */
    @Override
    public void onClose(WebSocket ws, int code, String message, boolean remote) {
        System.out.println("Connexion fermée avec code " + code
                + ", informations additionnels : " + message);
    }

    /**
     * Méthode appelée lors de la reception d'un message au sein d'une connexion.
     *
     * @param ws      Instance de connexion associée à l'évenement.
     * @param message Message décodé en UTF-8 qui vient d'être reçu.
     */
    @Override
    public void onMessage(WebSocket ws, String message) {
        System.out.println("Message reçu : " + message);
        //TODO: Faire différement en parsant le paquet
        PokeArenaPacket packet = PokeArenaUtilities.parseJsonPacket(message);
        if (packet != null) protocol.processPacket(ws, packet);
    }

    /**
     * Méthode appelée quand un erreur se produit.
     * Si une erreur provoque l'échec d'une connexion alors la méthode onClose(...) sera appelée additionnellement.
     *
     * @param ws Instance de la connexion associée à l'évenement.
     * @param e  Exception qui provoque l'erreur.
     */
    @Override
    public void onError(WebSocket ws, Exception e) {

    }

    /**
     * Envoyer un paquet au client.
     *
     * @param packet Paquet qui va être envoyé.
     */
    public void sendPacket(WebSocket ws, PokeArenaPacket packet) {
        ws.send(PokeArenaUtilities.toJson(packet));
    }

    public void sendPing(WebSocket ws) {
        sendPacket(ws, PokeArenaUtilities.createPacket(PokeArenaPacketType.PING, null));
    }

    public void sendPong(WebSocket ws) {
        sendPacket(ws, PokeArenaUtilities.createPacket(PokeArenaPacketType.PONG, null));
    }

    public void sendText(WebSocket ws, String texte) {
        sendPacket(ws, PokeArenaUtilities.createPacket(PokeArenaPacketType.TEXT, texte));
    }

    /**
     * Méthode appelée quand le démarrage du serveur est réussi.
     */
    @Override
    public void onStart() {
        this.state = PokeArenaServerState.WAITING_FOR_CLIENT1_TO_JOIN;
    }

    /**
     * Lancer le combat.
     */
    public void startBattle() {
        protocol.startBattle();
    }

    /**
     * Obtenir le nom d'hôte ou adresse IP du serveur.
     *
     * @return Nom d'hôte ou adresse IP du serveur
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * Obtenir le numéro de port du serveur.
     *
     * @return Numéro de port du serveur.
     */
    public int getPortNumber() {
        return portNumber;
    }

    /**
     * Obtenir l'état du serveur.
     *
     * @return État du serveur.
     */
    public PokeArenaServerState getState() {
        return state;
    }

    /**
     * Modifier l'état du serveur.
     *
     * @param state État du serveur.
     */
    public void setState(PokeArenaServerState state) {
        this.state = state;
    }

    /**
     * Obtenir la connexion associée au client 1.
     *
     * @return Connexion associé au client 1.
     */
    protected WebSocket getClient1WS() {
        return client1WS;
    }

    /**
     * Obtenir la connexion associée au client 2.
     *
     * @return Connexion associée au client 2.
     */
    protected WebSocket getClient2WS() {
        return client2WS;
    }

    public Battle getBattle() {
       return protocol.getBattle();
    }

}
