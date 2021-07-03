package pokearena.network.client;

/**
 * États que le client peut prendre.
 *
 * @author Louis
 */
public enum ClientState {
    /**
     * Le client n'est pas connecté à un serveur.
     */
    NOT_CONNECTED,
    /**
     * Le client doit envoyer son équipe au serveur.
     */
    NEED_TO_SEND_TEAM,
    /**
     * Le client est en attente du démarrage du combat.
     */
    WAITING_FOR_START,
    /**
     * Le client doit envoyer une action au serveur.
     */
    NEED_TO_SEND_ACTION,
    /**
     * Le client doit envoyer un changement de pokémon au serveur.
     */
    NEED_TO_SEND_CHANGEPKMN,
    /**
     * Le client a envoyé une action au serveur et est en attente des informations de mise à jour.
     */
    ACTION_SENT,
    /**
     * Le client a remporté le combat.
     */
    BATTLE_WON,
    /**
     * Le client a perdu le combat.
     */
    BATTLE_LOST,
}
