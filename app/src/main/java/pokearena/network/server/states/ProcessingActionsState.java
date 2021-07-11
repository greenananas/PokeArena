package pokearena.network.server.states;

import org.java_websocket.WebSocket;
import pokearena.network.packets.Packet;
import pokearena.network.server.ServerProtocol;
import pokearena.network.server.ServerStateName;
import pokearena.network.server.UnexpectedPacketException;

public class ProcessingActionsState extends ServerState {

    public ProcessingActionsState(ServerProtocol serverProtocol) {
        super(serverProtocol, ServerStateName.PROCESSING_ACTIONS);
    }

    @Override
    void onRefreshPacket(WebSocket ws, Packet request) {
        var server = serverProtocol.getServer();
        if (serverProtocol.isClient1(ws)) {
            server.sendUpdate(ws, serverProtocol.generateClient1Update());
        } else if (serverProtocol.isClient2(ws)) {
            server.sendUpdate(ws, serverProtocol.generateClient2Update());
        } else {
            throw new UnexpectedPacketException(this.stateName);
        }
    }

    @Override
    void onTeamPacket(WebSocket ws, Packet request) {
        throw new UnexpectedPacketException(this.stateName);
    }

    @Override
    void onForfeitPacket(WebSocket ws, Packet request) {
        throw new UnexpectedPacketException(this.stateName);
    }

    @Override
    public void onChangePokemonPacket(WebSocket ws, Packet request) {
        throw new UnexpectedPacketException(this.stateName);
    }
}
