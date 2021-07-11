package pokearena.network.server.states;

import org.java_websocket.WebSocket;
import pokearena.battle.Pokemon;
import pokearena.battle.Trainer;
import pokearena.network.packets.Packet;
import pokearena.network.packets.TeamPacket;
import pokearena.network.server.ServerProtocol;
import pokearena.network.server.ServerStateName;
import pokearena.network.server.UnexpectedPacketException;

public class WaitingForClient1TeamState extends ServerState {

    public WaitingForClient1TeamState(ServerProtocol serverProtocol) {
        super(serverProtocol, ServerStateName.WAITING_FOR_CLIENT_1_TEAM);
    }

    @Override
    public void onRefreshPacket(WebSocket ws, Packet request) {

    }

    @Override
    public void onTeamPacket(WebSocket ws, Packet request) {
        var server = serverProtocol.getServer();
        var team = ((TeamPacket) request).getTeam();
        if (logger.isDebugEnabled()) {
            StringBuilder debugMessage;
            debugMessage = new StringBuilder("Équipe reçue :\n");
            for (Pokemon p : team.getPokemons()) {
                debugMessage.append(" - ").append(p).append("\n");
            }
            logger.debug(debugMessage.toString());
        }
        if (serverProtocol.isClient1(ws)) {
            serverProtocol.setClient1Trainer(new Trainer("Joueur 1", team));
            server.setState(new WaitingForStartState(serverProtocol));
        } else {
            throw new UnexpectedPacketException(this.stateName);
        }
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

    }
}
