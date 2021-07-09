package pokearena.network.server.states;

import org.java_websocket.WebSocket;
import pokearena.network.packets.Packet;
import pokearena.network.server.ServerProtocol;
import pokearena.network.server.ServerStateName;
import pokearena.network.server.UnexpectedPacketException;

public class Client2WonState extends ServerState {

    public Client2WonState(ServerProtocol serverProtocol) {
        super(serverProtocol, ServerStateName.CLIENT_2_WON);
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
        throw new UnexpectedPacketException(this.stateName);
    }
}
