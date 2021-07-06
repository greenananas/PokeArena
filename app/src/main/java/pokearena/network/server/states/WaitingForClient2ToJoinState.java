package pokearena.network.server.states;

import pokearena.network.server.ServerProtocol;
import pokearena.network.server.ServerStateName;

public class WaitingForClient2ToJoinState extends ServerState {


    public WaitingForClient2ToJoinState(ServerProtocol serverProtocol) {
        super(serverProtocol, ServerStateName.WAITING_FOR_CLIENT_2_TO_JOIN);
    }

    @Override
    void onUpdatePacket() {

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
