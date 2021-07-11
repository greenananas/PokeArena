package pokearena.network.server.states;

import org.java_websocket.WebSocket;
import pokearena.network.packets.ChangePokemonPacket;
import pokearena.network.packets.Packet;
import pokearena.network.server.ServerProtocol;
import pokearena.network.server.ServerStateName;
import pokearena.network.server.UnexpectedPacketException;

public class WaitingForClient1ChangePkmnState extends ServerState {

    public WaitingForClient1ChangePkmnState(ServerProtocol serverProtocol) {
        super(serverProtocol, ServerStateName.WAITING_FOR_CLIENT_1_CHANGEPKMN);
    }

    @Override
    void onRefreshPacket(WebSocket ws, Packet request) {
        //renvoyer les informations nécessaires à l'émetteur
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
        if (serverProtocol.isClient1(ws)) {
            serverProtocol.getBattle().trainer1.getTrainer().changePokemon(((ChangePokemonPacket) request).getChangePkmn().getIndex());
            var server = serverProtocol.getServer();
            server.sendUpdate(ws, serverProtocol.generateClient1Update());
            server.sendUpdate(server.getClient2WS(), serverProtocol.generateClient2Update());
            server.setState(new WaitingForClientsActionState(serverProtocol));
        } else {
            throw new UnexpectedPacketException(this.stateName);
        }
    }
}
