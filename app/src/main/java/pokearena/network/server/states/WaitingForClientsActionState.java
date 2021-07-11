package pokearena.network.server.states;

import org.java_websocket.WebSocket;
import pokearena.network.packets.Packet;
import pokearena.network.server.ServerProtocol;
import pokearena.network.server.ServerStateName;
import pokearena.network.server.UnexpectedPacketException;

public class WaitingForClientsActionState extends ServerState {

    public WaitingForClientsActionState(ServerProtocol serverProtocol) {
        super(serverProtocol, ServerStateName.WAITING_FOR_CLIENTS_ACTIONS);
    }

    @Override
    public void onRefreshPacket(WebSocket ws, Packet request) {
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
    public void onTeamPacket(WebSocket ws, Packet request) {

    }

    @Override
    public void onForfeitPacket(WebSocket ws, Packet request) {
        var server = serverProtocol.getServer();
        if (serverProtocol.isClient1(ws)) {
            server.sendLose(ws);
            server.sendWin(server.getClient2WS());
            server.setState(new Client2WonState(serverProtocol));
        } else if (serverProtocol.isClient2(ws)) {
            server.sendLose(ws);
            server.sendWin(server.getClient1WS());
            server.setState(new Client1WonState(serverProtocol));
        } else {
            throw new UnexpectedPacketException(this.stateName);
        }
    }

    @Override
    public void onChangePokemonPacket(WebSocket ws, Packet request) {
        serverProtocol.getServer().sendPacket(ws, serverProtocol.processActionPacket(ws, request));
    }
}
