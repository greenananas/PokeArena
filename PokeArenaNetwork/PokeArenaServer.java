package PokeArenaNetwork;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import javax.swing.text.Utilities;
import java.net.InetSocketAddress;

public class PokeArenaServer extends WebSocketServer {

    private final PokeArenaProtocol pap = new PokeArenaProtocol();

    /**
     * Nom ou adresse IP du serveur.
     */
    private String hostname;
    /**
     * Numéro de port du serveur.
     */
    private int portNumber;
    /**
     * Statut du serveur.
     */
    private String status;

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
        ws.send("Bienvenue sur le serveur " + hostname + ":" + portNumber); // Envoie un message au nouveau client
        broadcast("Nouvelle connexion : " + clientHandshake.getResourceDescriptor()); // Envoie d'un message à tout les clients connectés
        System.out.println("Nouvelle connexion de : " + ws.getRemoteSocketAddress());
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
        //pap.processPacket(PokeArenaUtilities.GSON.fromJson(message, PokeArenaPacket.class));
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
     * Obtenir le statut du serveur.
     *
     * @return Statut du serveur.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Modifier le statut du serveur.
     *
     * @param status Statut du serveur.
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
