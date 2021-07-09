package pokearena.network.server.states;

import org.java_websocket.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pokearena.network.packets.Packet;
import pokearena.network.packets.TextPacket;
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

    abstract void onRefreshPacket(WebSocket ws, Packet request);

    abstract void onTeamPacket(WebSocket ws, Packet request);

    public void onTextPacket(WebSocket ws, Packet request) {
        logger.info("Message reçu de la part de '{}' : {}", serverProtocol.identifyWsUser(ws), ((TextPacket) request).getText());
    }

    abstract void onForfeitPacket(WebSocket ws, Packet request);

    public abstract void onChangePokemonPacket(WebSocket ws, Packet request);

    @Override
    public String toString() {
        return stateName.toString();
    }

    public ServerStateName getStateName() {
        return stateName;
    }
}
