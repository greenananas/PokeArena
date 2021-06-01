import Model.*;
import PokeArenaNetwork.ClientExemple;
import PokeArenaNetwork.PokeArenaClient;
import PokeArenaNetwork.ServeurExemple;

public class PokeArena {
    public static void main(String[] args) {
        /*
        Move dracocharge=new Move("Dracocharge", PokeTypes.type.DRAGON,true,100,70,16,0);
        Move dracogriffe=new Move("Dracogriffe", PokeTypes.type.DRAGON,true,80,100,24,0);
        Move seisme=new Move("Séisme", PokeTypes.type.GROUND,true,100,100,16,0);
        Move lanceflammes=new Move("Lance-Flammes", PokeTypes.type.FIRE,false,90,100,24,0);
        Pokemon carchacrok=new Pokemon("Carchacrok", PokeTypes.type.DRAGON, PokeTypes.type.GROUND,50,108,130,95,80,85,102,dracocharge,dracogriffe,seisme,lanceflammes);

        Move poingmeteor=new Move("Poing Météore", PokeTypes.type.STEEL,true,90,90,16,0);
        Move poingglace=new Move("Poing Glace", PokeTypes.type.ICE,true,75,100,24,0);
        Move poingeclair=new Move("Poing Éclair", PokeTypes.type.ELECTRIC,true,75,100,24,0);
        Pokemon metalosse=new Pokemon("Metalosse", PokeTypes.type.STEEL, PokeTypes.type.PSYCHIC,50,80,135,130,95,90,70,poingmeteor,seisme,poingglace,poingeclair);

        Move lamederock=new Move("Lame de Roc", PokeTypes.type.ROCK,true,100,80,8,0);
        Move poingfeu=new Move("Poing Feu", PokeTypes.type.FIRE,true,75,100,24,0);
        Move machouille=new Move("Machouille", PokeTypes.type.DARK,true,80,100,24,0);
        Pokemon tyranocif=new Pokemon("Tyranocif", PokeTypes.type.ROCK, PokeTypes.type.DARK,50,100,134,110,95,100,61,lamederock,poingfeu,seisme,machouille);

        Move lamedair=new Move("Lame d'Air", PokeTypes.type.FLYING,false,75,95,24,0);
        Move eclatmagique=new Move("Éclat Magique", PokeTypes.type.FAIRY,false,80,100,16,0);
        Move ballombre=new Move("Ball'Ombre", PokeTypes.type.GHOST,false,80,100,24,0);
        Pokemon togekiss=new Pokemon("Togekiss", PokeTypes.type.FAIRY, PokeTypes.type.FLYING,50,85,50,95,120,115,80,lamedair,eclatmagique,ballombre,lanceflammes);

        Pokemon elekable=new Pokemon("Elekable", PokeTypes.type.ELECTRIC,null,50,75,123,67,95,85,95,poingeclair,poingfeu,poingglace,seisme);

        Move surf=new Move("Surf", PokeTypes.type.WATER,false,95,100,24, 0);
        Move hydrocanon=new Move("Hydrocanon", PokeTypes.type.WATER,false,120,80,8, 0);
        Move luminocanon=new Move("Luminocanon", PokeTypes.type.STEEL,false,80,100,16, 0);
        Move laserglace=new Move("Laser Glace", PokeTypes.type.ICE,false,95,100,16, 0);
        Pokemon pingoleon=new Pokemon("Pingoléon", PokeTypes.type.WATER, PokeTypes.type.STEEL,50,84,86,88,111,101,60,surf,hydrocanon,luminocanon,laserglace);

        Pokemon carchacrok1=new Pokemon("Carchacrok", PokeTypes.type.DRAGON, PokeTypes.type.GROUND,50,108,130,95,80,85,102,dracocharge,dracogriffe,seisme,lanceflammes);
        Pokemon metalosse1=new Pokemon("Metalosse", PokeTypes.type.STEEL, PokeTypes.type.PSYCHIC,50,80,135,130,95,90,70,poingmeteor,seisme,poingglace,poingeclair);
        Pokemon tyranocif1=new Pokemon("Tyranocif", PokeTypes.type.ROCK, PokeTypes.type.DARK,50,100,134,110,95,100,61,lamederock,poingfeu,seisme,machouille);
        Pokemon togekiss1=new Pokemon("Togekiss", PokeTypes.type.FAIRY, PokeTypes.type.FLYING,50,85,50,95,120,115,80,lamedair,eclatmagique,ballombre,lanceflammes);
        Pokemon elekable1=new Pokemon("Elekable", PokeTypes.type.ELECTRIC,null,50,75,123,67,95,85,95,poingeclair,poingfeu,poingglace,seisme);
        Pokemon pingoleon1=new Pokemon("Pingoléon", PokeTypes.type.WATER, PokeTypes.type.STEEL,50,84,86,88,111,101,60,surf,hydrocanon,luminocanon,laserglace);

        Team team1=new Team(new Pokemon[3]);
        team1.setPokemon(elekable, 0);
        team1.setPokemon(togekiss, 1);
        team1.setPokemon(tyranocif, 2);

        Team team2=new Team(new Pokemon[3]);
        team2.setPokemon(elekable1, 0);
        team2.setPokemon(togekiss1, 1);
        team2.setPokemon(tyranocif1, 2);

        Trainer t1 = new Trainer("Mathis", team1);
        Trainer t2 = new Trainer("Maël", team2);
        Battle combat = new Battle(t1, t2, new BattleGround());
        combat.setUpBattle();

         */

        //Créer un trainer

        ServeurExemple.main(null);
        PokeArenaClient moi = new PokeArenaClient();
        //connecte le client au serveur
        //moi.connect();
        //Passe le trainer au serveur

        //Serveur, une fois que les deux clients se sont connectés, lance le combat

    }
}
