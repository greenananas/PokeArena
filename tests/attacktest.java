import Model.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class attacktest {


    private attack dracocharge;

    @Before
    public void setUp() {
        // Construire les pokémons
        dracocharge= new attack("Dracocharge",pokeTypes.type.DRAGON,true,100,70,16);
    }

    @Test
    public void testDracocharge() {
        assertEquals("Nom de l'attaque incorrect","Dracocharge", dracocharge.getName());
        assertEquals("Type de l'attaque incorrect",pokeTypes.type.DRAGON, dracocharge.getType());
        assertEquals("Attaque Physique/Spéciale incorrecte",true,dracocharge.isPhysical());
        assertEquals("Puissance incorrecte",100, dracocharge.getPower());
        assertEquals("Précisison incorrecte",70, dracocharge.getPrecision());
        assertEquals("PP incorrects",16, dracocharge.getPP());
    }
}
