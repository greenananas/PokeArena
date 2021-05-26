import Model.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class pokemontest {

    private Pokemon carchacrok;
    private Move dracocharge, dracogriffre, seisme, lanceflammes;

    @Before
    public void setUp() {
        //Construire les attaques du pokémon
        Move dracocharge=new Move("Dracocharge", PokeTypes.type.DRAGON,true,100,70,16);
        Move dracogriffe=new Move("Dracogriffe", PokeTypes.type.DRAGON,true,80,100,24);
        Move seisme=new Move("Séisme", PokeTypes.type.GROUND,true,100,100,16);
        Move lanceflammes=new Move("Lance-Flammes", PokeTypes.type.FIRE,false,90,100,24);
        // Construire les pokémons
        carchacrok=new Pokemon("Carchacrok", PokeTypes.type.DRAGON, PokeTypes.type.GROUND,50,108,130,95,80,85,102,dracocharge,dracogriffe,seisme,lanceflammes);
    }

    @Test
    public void testCarchacrok() {
        assertEquals("Nom du Pokémon incorrect", "Carchacrok", carchacrok.getName());
        assertEquals("Type 1 du Pokémon incorrect", PokeTypes.type.DRAGON, carchacrok.getType1());
        assertEquals("Type 2 du Pokémon incorrect", PokeTypes.type.GROUND, carchacrok.getType2());
        assertEquals("Niveau du Pokémon incorrect", 50, carchacrok.getLevel());
        assertEquals("HP incorrects", 108, carchacrok.getHP());
        assertEquals("Attaque incorrecte", 130, carchacrok.getAttack());
        assertEquals("Défense incorrecte", 95, carchacrok.getDefense());
        assertEquals("Attaque Spéciale incorrecte", 80, carchacrok.getSpeAttack());
        assertEquals("Défense Spéciale incorrecte", 85, carchacrok.getSpeDefense());
        assertEquals("Vitesse incorrecte", 102, carchacrok.getSpeed());
        assertEquals("Move 1 incorrect", dracocharge, carchacrok.getMove1());
        assertEquals("Move 2 incorrect", dracogriffre, carchacrok.getMove2());
        assertEquals("Move 3 incorrect", seisme, carchacrok.getMove3());
        assertEquals("Move 4 incorrect", lanceflammes, carchacrok.getMove4());
    }
}
