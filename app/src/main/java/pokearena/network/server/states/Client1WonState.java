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
        Team trainerTeam = null;
        Pokemon opponentPokemon = null;
        Move opponentMove = null;
        var battle = serverProtocol.getBattle();
        if (serverProtocol.isClient1(ws)) {
            trainerTeam = battle.trainer1.getTrainer().getPokemonTeam();
            opponentPokemon = battle.trainer2.getTrainer().getLeadingPkmn();
            opponentMove = serverProtocol.getLastClient2Move();
        } else if (serverProtocol.isClient2(ws)) {
            trainerTeam = battle.trainer2.getTrainer().getPokemonTeam();
            opponentPokemon = battle.trainer1.getTrainer().getLeadingPkmn();
            opponentMove = serverProtocol.getLastClient1Move();
        }
        if (trainerTeam != null && opponentPokemon != null) {
            serverProtocol.getServer().sendPacket(ws, Utils.createPacket(PacketType.UPDATE, new Update(trainerTeam, opponentPokemon, opponentMove)));
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
