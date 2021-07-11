package pokearena.network.server.states;

import org.java_websocket.WebSocket;
import pokearena.network.packets.ChangePokemonPacket;
import pokearena.network.packets.MovePacket;
import pokearena.network.packets.Packet;
import pokearena.network.server.ServerProtocol;
import pokearena.network.server.ServerStateName;
import pokearena.network.server.UnexpectedPacketException;

public class WaitingForClient2ActionState extends ServerState {

    public WaitingForClient2ActionState(ServerProtocol serverProtocol) {
        super(serverProtocol, ServerStateName.WAITING_FOR_CLIENT_2_ACTION);
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
        if (serverProtocol.isClient2(ws)) {
            var server = serverProtocol.getServer();
            var changePkmn = ((ChangePokemonPacket) request).getChangePkmn();
            serverProtocol.setClient2Action(changePkmn);
            server.setState(new ProcessingActionsState(serverProtocol));
            serverProtocol.handleActions(serverProtocol.getClient1Action(), serverProtocol.getClient2Action());
            server.sendUpdate(ws, serverProtocol.generateClient2Update());
            server.sendUpdate(server.getClient1WS(), serverProtocol.generateClient1Update());
        } else {
            throw new UnexpectedPacketException(this.stateName);
        }
    }

    @Override
    public void onMovePacket(WebSocket ws, Packet request) {
        if (serverProtocol.isClient2(ws)) {
            var server = serverProtocol.getServer();
            var move = ((MovePacket) request).getMove();
            serverProtocol.setClient2Action(move);
            serverProtocol.setLastClient2Move(move);
            server.setState(new ProcessingActionsState(serverProtocol));
            serverProtocol.handleActions(serverProtocol.getClient1Action(), serverProtocol.getClient2Action());
            server.sendUpdate(ws, serverProtocol.generateClient2Update());
            server.sendUpdate(server.getClient1WS(), serverProtocol.generateClient1Update());
        } else {
            throw new UnexpectedPacketException(this.stateName);
        }
    }
}
