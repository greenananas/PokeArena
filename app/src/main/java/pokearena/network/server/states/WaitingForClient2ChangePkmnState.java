package pokearena.network.server.states;

import org.java_websocket.WebSocket;
import pokearena.network.packets.ChangePokemonPacket;
import pokearena.network.packets.Packet;
import pokearena.network.server.ServerProtocol;
import pokearena.network.server.ServerStateName;
import pokearena.network.server.UnexpectedPacketException;

public class WaitingForClient2ChangePkmnState extends ServerState {

    public WaitingForClient2ChangePkmnState(ServerProtocol serverProtocol) {
        super(serverProtocol, ServerStateName.WAITING_FOR_CLIENT_2_CHANGEPKMN);
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

    }

    @Override
    public void onChangePokemonPacket(WebSocket ws, Packet request) {
        if (serverProtocol.isClient2(ws)) {
            serverProtocol.getBattle().trainer2.getTrainer().changePokemon(((ChangePokemonPacket) request).getChangePkmn().getIndex());
            var server = serverProtocol.getServer();
            server.sendUpdate(ws, serverProtocol.generateClient2Update());
            server.sendUpdate(server.getClient1WS(), serverProtocol.generateClient1Update());
            server.setState(new WaitingForClientsActionState(serverProtocol));
        } else {
            throw new UnexpectedPacketException(this.stateName);
        }
    }
}
