public class PokeArena {
    public static void main(String[] args) {
        attack dracocharge=new attack("Dracocharge",pokeTypes.type.DRAGON,true,100,70,16);
        attack dracogriffe=new attack("Dracogriffe",pokeTypes.type.DRAGON,true,80,100,24);
        attack seisme=new attack("Séisme",pokeTypes.type.GROUND,true,100,100,16);
        attack lanceflammes=new attack("Lance-Flammes",pokeTypes.type.FIRE,false,90,100,24);
        pokemon carchacrok=new pokemon("Carchacrok",pokeTypes.type.DRAGON,pokeTypes.type.GROUND,50,108,130,95,80,85,102,dracocharge,dracogriffe,seisme,lanceflammes);

        attack poingmeteor=new attack("Poing Météore",pokeTypes.type.STEEL,true,90,90,16);
        attack poingglace=new attack("Poing Glace",pokeTypes.type.ICE,true,75,100,24);
        attack poingeclair=new attack("Poing Éclair",pokeTypes.type.ELECTRIC,true,75,100,24);
        pokemon metalosse=new pokemon("Metalosse", pokeTypes.type.STEEL, pokeTypes.type.PSYCHIC,50,80,135,130,95,90,70,poingmeteor,seisme,poingglace,poingeclair);

        attack lamederock=new attack("Lame de Roc",pokeTypes.type.ROCK,true,100,80,8);
        attack poingfeu=new attack("Poing Feu",pokeTypes.type.FIRE,true,75,100,24);
        attack machouille=new attack("Machouille",pokeTypes.type.DARK,true,80,100,24);
        pokemon tyranocif=new pokemon("Tyranocif",pokeTypes.type.ROCK,pokeTypes.type.DARK,50,100,134,110,95,100,61,lamederock,poingfeu,seisme,machouille);

        attack lamedair=new attack("Lame d'Air",pokeTypes.type.FLYING,false,75,95,24);
        attack eclatmagique=new attack("Éclat Magique",pokeTypes.type.FAIRY,false,80,100,16);
        attack ballombre=new attack("Ball'Ombre",pokeTypes.type.GHOST,false,80,100,24);
        pokemon togekiss=new pokemon("Togekiss",pokeTypes.type.FAIRY,pokeTypes.type.FLYING,50,85,50,95,120,115,80,lamedair,eclatmagique,ballombre,lanceflammes);

        pokemon elekable=new pokemon("Elekable",pokeTypes.type.ELECTRIC,null,50,75,123,67,95,85,95,poingeclair,poingfeu,poingglace,seisme);

        attack surf=new attack("Surf",pokeTypes.type.WATER,false,95,100,24);
        attack hydrocanon=new attack("Hydrocanon",pokeTypes.type.WATER,false,120,80,8);
        attack luminocanon=new attack("Luminocanon",pokeTypes.type.STEEL,false,80,100,16);
        attack laserglace=new attack("Laser Glace",pokeTypes.type.ICE,false,95,100,16);
        pokemon pingoleon=new pokemon("Pingoléon",pokeTypes.type.WATER,pokeTypes.type.STEEL,50,84,86,88,111,101,60,surf,hydrocanon,luminocanon,laserglace);

        pokemon carchacrok1=new pokemon("Carchacrok",pokeTypes.type.DRAGON,pokeTypes.type.GROUND,50,108,130,95,80,85,102,dracocharge,dracogriffe,seisme,lanceflammes);
        pokemon metalosse1=new pokemon("Metalosse", pokeTypes.type.STEEL, pokeTypes.type.PSYCHIC,50,80,135,130,95,90,70,poingmeteor,seisme,poingglace,poingeclair);
        pokemon tyranocif1=new pokemon("Tyranocif",pokeTypes.type.ROCK,pokeTypes.type.DARK,50,100,134,110,95,100,61,lamederock,poingfeu,seisme,machouille);
        pokemon togekiss1=new pokemon("Togekiss",pokeTypes.type.FAIRY,pokeTypes.type.FLYING,50,85,50,95,120,115,80,lamedair,eclatmagique,ballombre,lanceflammes);
        pokemon elekable1=new pokemon("Elekable",pokeTypes.type.ELECTRIC,null,50,75,123,67,95,85,95,poingeclair,poingfeu,poingglace,seisme);
        pokemon pingoleon1=new pokemon("Pingoléon",pokeTypes.type.WATER,pokeTypes.type.STEEL,50,84,86,88,111,101,60,surf,hydrocanon,luminocanon,laserglace);

        pokemon[] team1=new pokemon[3];
        team1[0]=elekable;
        team1[1]=togekiss;
        team1[2]=tyranocif;

        pokemon[] team2=new pokemon[3];
        team2[0]=carchacrok1;
        team2[1]=togekiss1;
        team2[2]=pingoleon1;

        trainer t1=new trainer("Mathis",team1);
        trainer t2=new trainer("Maël",team2);
        battle combat=new battle(t1,t2);
        combat.startBattle();
    }
}
