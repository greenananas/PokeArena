package pokearena.network.server.states;

import pokearena.network.server.ServerProtocol;
import pokearena.network.server.ServerStatesEnum;

public class WaitingForClientsActionState extends ServerState {

    public WaitingForClientsActionState(ServerProtocol serverProtocol) {
        super(serverProtocol, ServerStatesEnum.WAITING_FOR_CLIENTS_ACTIONS);
    }

    @Override
    void onUpdatePacket() {

    }

    @Override
    void onPingPacket() {

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
