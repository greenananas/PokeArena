package PokeArenaNetwork;

import com.google.gson.Gson;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class PokeArenaClient extends WebSocketClient {

    /**
     * Statut du client
     */
    private String status;

    // TODO: Peut-être à déplacer dans une classe utilitaire
    private static final Gson GSON = new Gson();

    /**
     * Créer un client PokeArenaClient
     *
     * @param serveurURI URI du serveur au format de type ws://hostname:portnumber
     */
    public PokeArenaClient(URI serveurURI) {
        super(serveurURI);
    }

    /**
     * Méthode appelée lors de l'ouverture d'une connexion
     *
     * @param serverHandshake Informations relatives à la connexion
     */
    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        send("HELLO");
        System.out.println("Ouverture de la connexion");
    }

    /**
     * Méthode appelée lors de la reception d'un message au sein d'une connexion
     *
     * @param message Message décodé en UTF-8 qui vient d'être reçu
     */
    @Override
    public void onMessage(String message) {
        System.out.println("Message reçu : " + message);
    }

    /**
     * Méthode appelée lors de la fermeture d'une connexion
     *
     * @param code    Code
     * @param message Informations additionnels
     * @param remote  Indique si la connexion a été fermée par l'hôte distant
     */
    @Override
    public void onClose(int code, String message, boolean remote) {
        System.out.println("Connexion fermée avec code " + code
                + ", informations aditionnels : " + message);
    }

    /**
     * Méthode appelée quand un erreur se produit.
     * Si une erreur provoque l'échec d'une connexion alors la méthode onClose(...) sera appelée additionnellement
     *
     * @param e Exception qui provoque l'erreur
     */
    @Override
    public void onError(Exception e) {

    }

    /**
     * Envoyer une action au serveur
     *
     * @param action Action qui va être envoyée au serveur
     * @param <A>    Type générique qui doit être sérialisable en JSON
     */
    public <A> void sendAction(A action) {
        send(GSON.toJson(action));
    }

    /**
     * Envoyer une attaque au serveur
     *
     * @param move Attaque qui va être envoyée au serveur
     */
    public void sendMove(Move move) {
        sendAction(move);
    }

    /**
     * Envoyer un objet au serveur
     *
     * @param item Objet qui va être envoyé au serveur
     */
    public void sendItem(Item item) {
        sendAction(item);
    }

    /**
     * Envoyer une équipe au serveur
     *
     * @param team Équipe qui va être envoyée au serveur
     */
    public void sendTeam(Team team) {
        sendAction(team);
    }

    /**
     * Envoyer un changement de pokemon au serveur
     *
     * @param pokemon Pokemon à changer qui va être envoyé au serveur
     */
    public void sendChangePokemon(Pokemon pokemon) {
        sendAction(pokemon);
    }

    /**
     * Envoyer une déclaration de forfait au serveur
     */
    public void sendForfeit() {
        // Envoie d'un message de forfait
    }

    /**
     * Obtenir le statut du client
     *
     * @return Statut du client
     */
    public String getStatus() {
        return status;
    }

    /**
     * Modifier le statut du client
     *
     * @param status Statut du client
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
