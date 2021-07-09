package pokearena.network.server.states;

import org.java_websocket.WebSocket;
import pokearena.network.packets.Packet;
import pokearena.network.server.ServerProtocol;
import pokearena.network.server.ServerStateName;

public class WaitingForClient2ActionState extends ServerState {

    public WaitingForClient2ActionState(ServerProtocol serverProtocol) {
        super(serverProtocol, ServerStateName.WAITING_FOR_CLIENT_2_ACTION);
    }

    @Override
    void onRefreshPacket(WebSocket ws, Packet request) {

    }

    @Override
    void onTeamPacket(WebSocket ws, Packet request) {

    }

    @Override
    void onForfeitPacket(WebSocket ws, Packet request) {

    }

    @Override
    public void onChangePokemonPacket(WebSocket ws, Packet request) {
        serverProtocol.getServer().sendPacket(ws, serverProtocol.processActionPacket(ws, request));
    }
}
