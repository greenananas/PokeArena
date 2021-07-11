package pokearena.network.server.states;

import org.java_websocket.WebSocket;
import pokearena.network.packets.Packet;
import pokearena.network.server.ServerProtocol;
import pokearena.network.server.ServerStateName;

public class WaitingForClient1TeamState extends ServerState {

    public WaitingForClient1TeamState(ServerProtocol serverProtocol) {
        super(serverProtocol, ServerStateName.WAITING_FOR_CLIENT_1_TEAM);
    }

    @Override
    public void onRefreshPacket(WebSocket ws, Packet request) {

    }

    @Override
    public void onTeamPacket(WebSocket ws, Packet request) {

    }

    @Override
    public void onForfeitPacket(WebSocket ws, Packet request) {

    }

    @Override
    public void onChangePokemonPacket(WebSocket ws, Packet request) {

    }
}
