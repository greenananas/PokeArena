package pokearena.network.packets;

import pokearena.battle.Team;

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
