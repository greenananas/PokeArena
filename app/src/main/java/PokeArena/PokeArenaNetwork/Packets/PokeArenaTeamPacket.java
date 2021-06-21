package PokeArena.PokeArenaNetwork.Packets;

import PokeArena.PokeArenaBattle.Team;
import PokeArena.PokeArenaNetwork.Packets.PokeArenaPacket;
import PokeArena.PokeArenaNetwork.Packets.PokeArenaPacketType;

public class PokeArenaTeamPacket extends PokeArenaPacket {

    private Team team;

    public PokeArenaTeamPacket() {
        super(PokeArenaPacketType.TEAM);
    }

    public PokeArenaTeamPacket(Team team) {
        this();
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

}
