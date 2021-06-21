package PokeArenaNetwork.Server;

/**
 * États que le serveur peut prendre.
 *
 * @author Louis
 */
public enum PokeArenaServerState {
    /**
     * Le serveur est en attente de la connexion du client 1.
     */
    WAITING_FOR_CLIENT1_TO_JOIN,
    /**
     * Le serveur est en attente de la connexion du client 2.
     */
    WAITING_FOR_CLIENT2_TO_JOIN,
    /**
     * Le serveur est en attente des équipes des deux clients.
     */
    WAITING_FOR_CLIENTS_TEAM,
    /**
     * Le serveur est en attente de l'équipe du client 1.
     */
    WAITING_FOR_CLIENT_1_TEAM,
    /**
     * Le serveur est en attente de l'équipe du client 2.
     */
    WAITING_FOR_CLIENT_2_TEAM,
    /**
     * Le serveur est en attente du démarrage du combat.
     */
    WAITING_FOR_START,
    /**
     * Le client 1 a gagné le combat.
     */
    CLIENT_1_WON,
    /**
     * Le client 2 a gagné le combat.
     */
    CLIENT_2_WON,
    /**
     * Le serveur est attente du changement de Pokémon du client 1.
     */
    WAITING_FOR_CLIENT_1_CHANGEPKMN,
    /**
     * Le serveur est attente du changement de Pokémon du client 2.
     */
    WAITING_FOR_CLIENT_2_CHANGEPKMN,
    /**
     * Le serveur est en attente des actions des deux clients.
     */
    WAITING_FOR_CLIENTS_ACTIONS,
    /**
     * Le serveur est en attente de l'action du client 1.
     */
    WAITING_FOR_CLIENT_1_ACTION,
    /**
     * Le serveur est en attente de l'action du client 2.
     */
    WAITING_FOR_CLIENT_2_ACTION,
    /**
     * Le serveur est entrain d'appliquer les actions des deux clients.
     */
    PROCESSING_ACTIONS,
}
