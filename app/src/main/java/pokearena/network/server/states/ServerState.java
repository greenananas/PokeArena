package pokearena.network.server.states;

import org.java_websocket.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pokearena.network.server.ServerProtocol;
import pokearena.network.server.ServerStateName;

public abstract class ServerState {

    private final Logger logger = LoggerFactory.getLogger(ServerState.class);

    ServerProtocol serverProtocol;

    ServerStateName stateName;

    ServerState(ServerProtocol serverProtocol, ServerStateName stateName) {
        this.serverProtocol = serverProtocol;
        this.stateName = stateName;
    }

    abstract void onUpdatePacket();

    public void onPingPacket(WebSocket ws) {
        serverProtocol.getServer().sendPong(ws);
    }

    public void onPongPacket(WebSocket ws) {
        if (logger.isTraceEnabled()) {
            if (ws == serverProtocol.getServer().getClient1WS()) {
                logger.trace("Pong Packet received from client 1");
            } else if (ws == serverProtocol.getServer().getClient2WS()) {
                logger.trace("Pong Packet received from client 2");
            } else {
                logger.trace("Pong packet received from a client which is not client 1 nor client 2");
            }
        }
    }

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

    public ServerStateName getStateName() {
        return stateName;
    }
}
