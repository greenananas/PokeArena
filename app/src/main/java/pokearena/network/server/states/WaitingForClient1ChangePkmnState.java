package pokearena.network.server.states;

import pokearena.network.server.ServerProtocol;
import pokearena.network.server.ServerStatesEnum;

public class WaitingForClient1ChangePkmnState extends ServerState {

   public  WaitingForClient1ChangePkmnState(ServerProtocol serverProtocol) {
        super(serverProtocol, ServerStatesEnum.WAITING_FOR_CLIENT_1_CHANGEPKMN);
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
