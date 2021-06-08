package PokeArenaNetwork;

public enum PokeArenaClientState {
    NOT_CONNECTED,
    NEED_TO_SEND_TEAM,
    WAITING_FOR_START,
    IN_BATTLE,
    BATTLE_ENDED,
    WAITING_FOR_USER_ACTION,
    WAITING_FOR_SERVER_RESULT,
}
