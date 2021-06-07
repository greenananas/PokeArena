package tests;

import Model.*;

public class testBattle {
    public static void main(String[] args) {
        Move dracocharge=new Move("Dracocharge", PokeTypes.type.DRAGON,true,100,70,16,0,0);
        Move dracogriffe=new Move("Dracogriffe", PokeTypes.type.DRAGON,true,80,100,24,0,0);
        Move seisme=new Move("Séisme", PokeTypes.type.GROUND,true,100,100,16,0,0);
        Move lanceflammes=new Move("Lance-Flammes", PokeTypes.type.FIRE,false,90,100,24,0,0);
        Pokemon carchacrok=new Pokemon("Carchacrok", PokeTypes.type.DRAGON, PokeTypes.type.GROUND,50,108,130,95,80,85,102,dracocharge,dracogriffe,seisme,lanceflammes);

        Move surf=new Move("Surf", PokeTypes.type.WATER,false,95,100,24, 0,0);
        Move hydrocanon=new Move("Hydrocanon", PokeTypes.type.WATER,false,120,80,8, 0,0);
        Move luminocanon=new Move("Luminocanon", PokeTypes.type.STEEL,false,80,100,16, 0,0);
        Move laserglace=new Move("Laser Glace", PokeTypes.type.ICE,false,95,100,16, 0,0);
        Pokemon pingoleon=new Pokemon("Pingoléon", PokeTypes.type.WATER, PokeTypes.type.STEEL,50,84,86,88,111,101,60,surf,hydrocanon,luminocanon,laserglace);

        Team team1=new Team();
        team1.addPokemon(carchacrok);

        Team team2=new Team();
        team2.addPokemon(pingoleon);

        Trainer t1 = new Trainer("Nathan", team1);
        Trainer t2 = new Trainer("Moha", team2);
        Battle combat = new Battle(t1, t2, new BattleGround());

        Action mv1 = t1.chooseAction(t2.getLeadingPkmn());
        Action mv2 = t2.chooseAction(t1.getLeadingPkmn());

        combat.takeTurns(mv1, mv2);

        mv1 = t1.chooseAction(t2.getLeadingPkmn());
        mv2 = t2.chooseAction(t1.getLeadingPkmn());

        combat.takeTurns(mv1, mv2);

    }
}
