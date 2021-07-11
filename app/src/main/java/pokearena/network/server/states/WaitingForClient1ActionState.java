package pokearena.network.server.states;

import org.java_websocket.WebSocket;
import pokearena.network.packets.ChangePokemonPacket;
import pokearena.network.packets.MovePacket;
import pokearena.network.packets.Packet;
import pokearena.network.server.ServerProtocol;
import pokearena.network.server.ServerStateName;
import pokearena.network.server.UnexpectedPacketException;

public class WaitingForClient1ActionState extends ServerState {

    public WaitingForClient1ActionState(ServerProtocol serverProtocol) {
        super(serverProtocol, ServerStateName.WAITING_FOR_CLIENT_1_ACTION);
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
        throw new UnexpectedPacketException(stateName);
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
        if (serverProtocol.isClient1(ws)) {
            var server = serverProtocol.getServer();
            var changePkmn = ((ChangePokemonPacket) request).getChangePkmn();
            serverProtocol.setClient1Action(changePkmn);
            server.setState(new ProcessingActionsState(serverProtocol));
            serverProtocol.handleActions(serverProtocol.getClient1Action(), serverProtocol.getClient2Action());
            server.sendUpdate(ws, serverProtocol.generateClient1Update());
            server.sendUpdate(server.getClient2WS(), serverProtocol.generateClient2Update());
        } else {
            throw new UnexpectedPacketException(this.stateName);
        }
    }

    @Override
    public void onMovePacket(WebSocket ws, Packet request) {
        if (serverProtocol.isClient1(ws)) {
            var server = serverProtocol.getServer();
            var move = ((MovePacket) request).getMove();
            serverProtocol.setClient1Action(move);
            serverProtocol.setLastClient1Move(move);
            server.setState(new ProcessingActionsState(serverProtocol));
            serverProtocol.handleActions(serverProtocol.getClient1Action(), serverProtocol.getClient2Action());
            server.sendUpdate(ws, serverProtocol.generateClient1Update());
            server.sendUpdate(server.getClient2WS(), serverProtocol.generateClient2Update());
        } else {
            throw new UnexpectedPacketException(this.stateName);
        }
    }
}
