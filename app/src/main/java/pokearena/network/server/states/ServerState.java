package pokearena.network.server.states;

import pokearena.network.server.ServerProtocol;
import pokearena.network.server.ServerStatesEnum;

public abstract class ServerState {

    ServerProtocol serverProtocol;

    ServerStatesEnum stateName;

    ServerState(ServerProtocol serverProtocol, ServerStatesEnum stateName) {
        this.serverProtocol = serverProtocol;
        this.stateName = stateName;
    }

    abstract void onUpdatePacket();

    abstract void onPingPacket();

    abstract void onPongPacket();

    abstract void onRefreshPacket();

    abstract void onLosePacket();

    abstract void onWinPacket();

    abstract void onTeamPacket();

    abstract void onTextPacket();

    abstract void onForfeitPacket();

    public ServerStatesEnum getStateName() {
        return stateName;
    }
}
