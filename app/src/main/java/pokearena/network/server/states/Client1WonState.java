package pokearena.network.server.states;

import pokearena.network.server.ServerProtocol;
import pokearena.network.server.ServerStateName;

public class Client1WonState extends ServerState {

    public Client1WonState(ServerProtocol serverProtocol) {
        super(serverProtocol, ServerStateName.CLIENT_1_WON);
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
