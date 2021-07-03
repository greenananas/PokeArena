package pokearena.network.packets;

import pokearena.battle.Team;

public class TeamPacket extends Packet {

    private Team team;

    public TeamPacket() {
        super(PacketType.TEAM);
    }

    public TeamPacket(Team team) {
        this();
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

}
