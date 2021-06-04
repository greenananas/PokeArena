package PokeArenaNetwork;

import Model.Move;
import Model.Pokemon;
import Model.Team;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class PokeArenaClient extends WebSocketClient {

    /**
     * Statut du client.
     */
    private String status;

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
        System.out.println("Ouverture de la connexion");
    }

    /**
     * Méthode appelée lors de la reception d'un message au sein d'une connexion.
     *
     * @param message Message décodé en UTF-8 qui vient d'être reçu.
     */
    @Override
    public void onMessage(String message) {
        System.out.println("Message reçu : " + message);
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
        System.out.println("Connexion fermée avec code " + code
                + ", informations aditionnels : " + message);
    }

    /**
     * Méthode appelée quand un erreur se produit.
     * Si une erreur provoque l'échec d'une connexion alors la méthode onClose(...) sera appelée additionnellement.
     *
     * @param e Exception qui provoque l'erreur.
     */
    @Override
    public void onError(Exception e) {

    }

    /**
     * Envoyer une action au serveur.
     *
     * @param packetType Type du paquet qui va être envoyé.
     * @param packetData Données du paquet qui va être envoyé.
     * @param <A>        Type générique qui doit être sérialisable en JSON.
     */
    public <A> void sendPacket(PokeArenaPacketType packetType, A packetData) {
        PokeArenaPacket packet = PokeArenaUtilities.createPacket(packetType, packetData);
        send(PokeArenaUtilities.toJson(packet));
    }

    public void sendPing() {
        sendPacket(PokeArenaPacketType.PING, null);
    }

    /**
     * Envoyer une attaque au serveur.
     *
     * @param move Attaque qui va être envoyée au serveur.
     */
    public void sendMove(Move move) {
        sendPacket(PokeArenaPacketType.MOVE, move);
    }

    /**
     * Envoyer une équipe au serveur.
     *
     * @param team Équipe qui va être envoyée au serveur.
     */
    public void sendTeam(Team team) {
        sendPacket(PokeArenaPacketType.TEAM, team);
    }

    /**
     * Envoyer un changement de pokemon au serveur.
     *
     * @param pokemon Pokemon à changer qui va être envoyé au serveur.
     */
    public void sendChangePokemon(Pokemon pokemon) {
        sendPacket(PokeArenaPacketType.CHANGEPOKEMON, pokemon);
    }

    /**
     * Envoyer une déclaration de forfait au serveur.
     */
    public void sendForfeit() {
        // Envoie d'un message de forfait
    }

    /**
     * Obtenir le statut du client.
     *
     * @return Statut du client.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Modifier le statut du client.
     *
     * @param status Statut du client.
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
