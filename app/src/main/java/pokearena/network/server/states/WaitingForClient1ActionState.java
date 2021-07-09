package pokearena.network.server.states;

import org.java_websocket.WebSocket;
import pokearena.network.packets.Packet;
import pokearena.network.server.ServerProtocol;
import pokearena.network.server.ServerStateName;
import pokearena.network.server.UnexpectedPacketException;

public class WaitingForClient1ActionState extends ServerState {

    public WaitingForClient1ActionState(ServerProtocol serverProtocol) {
        super(serverProtocol, ServerStateName.WAITING_FOR_CLIENT_1_ACTION);
    }

    private boolean isClient1(WebSocket ws) {
        return ws == serverProtocol.getServer().getClient1WS();
    }

    @Override
    void onRefreshPacket(WebSocket ws, Packet request) {
        //renvoyer les informations nécessaires à l'émetteur
    }

    @Override
    void onTeamPacket(WebSocket ws, Packet request) {
        throw new UnexpectedPacketException(stateName);
    }

    @Override
    void onForfeitPacket(WebSocket ws, Packet request) {

    }

    @Override
    public void onChangePokemonPacket(WebSocket ws, Packet request) {
        serverProtocol.getServer().sendPacket(ws, serverProtocol.processActionPacket(ws, request));
    }
}
