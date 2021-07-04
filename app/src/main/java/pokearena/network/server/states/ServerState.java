package pokearena.network.server.states;

import org.java_websocket.WebSocket;
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

    public void onPingPacket(WebSocket ws) {
        serverProtocol.getServer().sendPong(ws);
    }

    abstract void onPongPacket();

    abstract void onRefreshPacket();

    abstract void onLosePacket();

    abstract void onWinPacket();

    abstract void onTeamPacket();

    abstract void onTextPacket();

    abstract void onForfeitPacket();

    @Override
    public String toString() {
        return stateName.toString();
    }

    public ServerStatesEnum getStateName() {
        return stateName;
    }
}
