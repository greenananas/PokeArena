package PokeArenaNetwork;

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

    private WebSocket client1WS;
    private WebSocket client2WS;

    private final PokeArenaProtocol protocol = new PokeArenaProtocol(this);

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
                ws.send("Bienvenue sur le serveur, vous êtes le joueur 1");
                state = PokeArenaServerState.WAITING_FOR_CLIENT2_TO_JOIN;
                break;
            case WAITING_FOR_CLIENT2_TO_JOIN:
                client2WS = ws;
                ws.send("Bienvenue sur le serveur, vous êtes le joueur 2");
                state = PokeArenaServerState.WAITING_FOR_START;
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
        ws.send("J'ai bien recu ton message");
        //TODO: Faire différement en parsant le paquet
        PokeArenaUtilities.parseJsonPacket(message);
        protocol.processPacket(ws, PokeArenaUtilities.parseJsonPacket(message));
    }

    /**
     * Envoyer un paquet au client.
     *
     * @param packet Paquet qui va être envoyé.
     */
    public void sendPacket(WebSocket ws, PokeArenaPacket packet) {
        ws.send(PokeArenaUtilities.toJson(packet));
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
     * Méthode appelée quand le démarrage du serveur est réussi.
     */
    @Override
    public void onStart() {
        this.state = PokeArenaServerState.WAITING_FOR_CLIENT1_TO_JOIN;
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

    protected WebSocket getClient1WS() {
        return client1WS;
    }

    protected WebSocket getClient2WS() {
        return client2WS;
    }
}
