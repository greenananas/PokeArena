/**
 * Class regroupant des énumérations de types et d'effets (faiblesses, résistances)
 */
public class pokeTypes {
    /**
     * Énumération des différents types de Pokémon.
     */
    public enum type {
        NORMAL, FIGHTING, FLYING, POISON, GROUND, ROCK, BUG, GHOST, STEEL, FIRE, WATER, GRASS, ELECTRIC, PSYCHIC, ICE, DRAGON, DARK, FAIRY
    }

    /**
     * Énumération des niveaux d'effet des attaques de Pokémon.
     */
    public enum EffectLevel {
        INEFFECTIVE(0.0), WEAK(0.5), NORMAL(1.0), STRONG(2.0);

        private double value;

        EffectLevel(final double newValue) {
            value = newValue;
        }

        public double getEffect() {
            return value;
        }
    }

    // norm fight fly pois grnd rock bug ghst stel fire wter grss elec psyc ice drag dark fair
    private static final EffectLevel norm = EffectLevel.NORMAL;
    private static final EffectLevel weak = EffectLevel.WEAK;
    private static final EffectLevel str  = EffectLevel.STRONG;
    private static final EffectLevel inef = EffectLevel.INEFFECTIVE;
    private static final EffectLevel allGen[][] =
    {
        //offensif en colonne, défensif en ligne
             // norm  fght  fly   pois  grnd  rock  bug   ghst  stel  fire  wter  grss  elec  psyc  ice   drag  dark  fair
    /* norm */ {norm, str , norm, norm, norm, norm, norm, inef, norm, norm, norm, norm, norm, norm, norm, norm, norm, norm},
    /* fght */ {norm, norm, str , norm, norm, norm, norm, inef, norm, norm, norm, norm, norm, norm, norm, norm, norm, str},
    /* fly  */ {norm, weak, norm, norm, inef, str , weak, norm, norm, norm, norm, weak, str , norm, str , norm, norm, norm},
    /* pois */ {norm, weak, norm, weak, str , norm, weak, norm, norm, norm, norm, weak, norm, str , norm, norm, norm, weak},
    /* grnd */ {norm, norm, norm, weak, norm, weak, norm, norm, norm, norm, str , str , inef, norm, str , norm, norm, norm},
    /* rock */ {weak, str , weak, weak, str , norm, norm, norm, norm, norm, str , str , norm, norm, norm, norm, norm, norm},
    /* bug  */ {norm, weak, str , norm, weak, str , norm, norm, norm, str , norm, weak, norm, norm, norm, norm, norm, norm},
    /* ghst */ {inef, inef, norm, weak, norm, norm, weak, norm, norm, norm, norm, norm, norm, norm, norm, norm, str , norm},
    /* stel */ {weak, str , weak, inef, str , weak, weak, weak, weak, str , norm, weak, norm, weak, weak, weak, weak, weak},
    /* fire */ {norm, norm, norm, norm, str , str , weak, norm, weak, weak, str , weak, norm, norm, weak, norm, norm, weak},
    /* wter */ {norm, norm, norm, norm, norm, norm, norm, norm, weak, weak, weak, str , str , norm, weak, norm, norm, norm},
    /* grss */ {norm, norm, str , str , weak, norm, str , norm, norm, str , weak, weak, weak, norm, str , norm, norm, norm},
    /* elec */ {norm, norm, weak, norm, str , norm, norm, norm, weak, norm, norm, norm, weak, norm, norm, norm, norm, norm},
    /* psyc */ {norm, weak, norm, norm, norm, norm, str , str , norm, norm, norm, norm, norm, weak, norm, norm, str , norm},
    /* ice  */ {norm, str , norm, norm, norm, str , norm, norm, str , str , norm, norm, norm, norm, weak, norm, norm, norm},
    /* drag */ {norm, norm, norm, norm, norm, norm, norm, norm, norm, weak, weak, weak, weak, norm, str , str , norm, str},
    /* dark */ {norm, str , norm, norm, norm, str , weak, norm, norm, norm, norm, norm, norm, inef, norm, norm, weak, str},
    /* fair */ {norm, weak, norm, str , norm, norm, weak, norm, str , norm, norm, norm, norm, norm, norm, inef, weak, norm}
             // norm  fght  fly   pois  grnd  rock  bug   ghst  stel  fire  wter  grss  elec  psyc  ice   drag  dark  fair
    };

    public static EffectLevel getTypeAdvantage(type defenseType, type attackType) {
        return allGen[defenseType.ordinal()][attackType.ordinal()];
    }
}