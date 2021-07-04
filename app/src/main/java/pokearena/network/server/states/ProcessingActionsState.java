package pokearena.network.server.states;

import pokearena.network.server.ServerProtocol;
import pokearena.network.server.ServerStatesEnum;

public class ProcessingActionsState extends ServerState {

   public  ProcessingActionsState(ServerProtocol serverProtocol) {
        super(serverProtocol, ServerStatesEnum.PROCESSING_ACTIONS);
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
