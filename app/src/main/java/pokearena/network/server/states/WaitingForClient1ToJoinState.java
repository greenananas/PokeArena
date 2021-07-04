package pokearena.network.server.states;

import pokearena.network.server.ServerProtocol;
import pokearena.network.server.ServerStatesEnum;

public class WaitingForClient1ToJoinState extends ServerState {

    public WaitingForClient1ToJoinState(ServerProtocol serverProtocol) {
        super(serverProtocol, ServerStatesEnum.WAITING_FOR_CLIENT_1_TO_JOIN);
    }

    @Override
    void onUpdatePacket() {

    }

    @Override
    void onPongPacket() {

    }

    @Override
    void onRefreshPacket() {

    }

    @Override
    void onLosePacket() {

    }

    @Override
    void onWinPacket() {

    }

    @Override
    void onTeamPacket() {

    }

    @Override
    void onTextPacket() {

    }

    @Override
    void onForfeitPacket() {

    }
}
