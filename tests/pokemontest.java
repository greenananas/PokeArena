import Model.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class pokemontest {

    private pokemon carchacrok;
    private attack dracocharge, dracogriffre, seisme, lanceflammes;

    @Before
    public void setUp() {
        //Construire les attaques du pokémon
        attack dracocharge=new attack("Dracocharge",pokeTypes.type.DRAGON,true,100,70,16);
        attack dracogriffe=new attack("Dracogriffe",pokeTypes.type.DRAGON,true,80,100,24);
        attack seisme=new attack("Séisme",pokeTypes.type.GROUND,true,100,100,16);
        attack lanceflammes=new attack("Lance-Flammes",pokeTypes.type.FIRE,false,90,100,24);
        // Construire les pokémons
        carchacrok=new pokemon("Carchacrok",pokeTypes.type.DRAGON,pokeTypes.type.GROUND,50,108,130,95,80,85,102,dracocharge,dracogriffe,seisme,lanceflammes);
    }

    @Test
    public void testCarchacrok() {
        assertEquals("Nom du Pokémon incorrect", "Carchacrok", carchacrok.getName());
        assertEquals("Type 1 du Pokémon incorrect", pokeTypes.type.DRAGON, carchacrok.getType1());
        assertEquals("Type 2 du Pokémon incorrect", pokeTypes.type.GROUND, carchacrok.getType2());
        assertEquals("Niveau du Pokémon incorrect", 50, carchacrok.getLevel());
        assertEquals("HP incorrects", 108, carchacrok.getHP());
        assertEquals("Attaque incorrecte", 130, carchacrok.getAttack());
        assertEquals("Défense incorrecte", 95, carchacrok.getDefense());
        assertEquals("Attaque Spéciale incorrecte", 80, carchacrok.getSpeAttack());
        assertEquals("Défense Spéciale incorrecte", 85, carchacrok.getSpeDefense());
        assertEquals("Vitesse incorrecte", 102, carchacrok.getSpeed());
        assertEquals("Move 1 incorrect", dracocharge, carchacrok.getAttack1());
        assertEquals("Move 2 incorrect", dracogriffre, carchacrok.getAttack2());
        assertEquals("Move 3 incorrect", seisme, carchacrok.getAttack3());
        assertEquals("Move 4 incorrect", lanceflammes, carchacrok.getAttack4());
    }
}
