package pokearena.network.server.states;

import org.java_websocket.WebSocket;
import pokearena.battle.Move;
import pokearena.battle.Pokemon;
import pokearena.battle.Team;
import pokearena.network.Update;
import pokearena.network.Utils;
import pokearena.network.packets.Packet;
import pokearena.network.packets.PacketType;
import pokearena.network.server.ServerProtocol;
import pokearena.network.server.ServerStateName;
import pokearena.network.server.UnexpectedPacketException;

public class Client1WonState extends ServerState {

    public Client1WonState(ServerProtocol serverProtocol) {
        super(serverProtocol, ServerStateName.CLIENT_1_WON);
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
