package PokeArenaNetwork.Client;

/**
 * États que le client peut prendre.
 */
public enum PokeArenaClientState {
    NOT_CONNECTED,
    NEED_TO_SEND_TEAM,
    WAITING_FOR_START,
    NEED_TO_SEND_ACTION,
    NEED_TO_SEND_CHANGEPKMN,
    ACTION_SENT,
    IN_BATTLE,
    BATTLE_ENDED,
    BATTLE_WON,
    BATTLE_LOST,
    WAITING_FOR_USER_ACTION,
    WAITING_FOR_SERVER_RESULT,
}
