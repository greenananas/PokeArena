package PokeArena.PokeArenaBattle;

public enum Nature {
    BOLD (0.9f,1.1f,1,1,1),
    QUIRKY (1,1,1,1,1),
    BRAVE (1.1f,1,1,1,0.9f),
    CALM (0.9f,1,1,0.9f,1),
    QUIET (1,1,1.1f,1,0.9f),
    DOCILE (1,1,1,1,1),
    MILD (1,0.9f,1.1f,1,1),
    RASH (1,1,1.1f,0.9f,1),
    GENTLE (1,0.9f,1,1.1f,1),
    HARDY (1,1,1,1,1),
    JOLLY (1,1,0.9f,1,1.1f),
    LAX (1,1.1f,1,0.9f,1),
    IMPISH (1,1.1f,0.9f,1,1),
    SASSY (1,1,1,1.1f,0.9f),
    NAUGHTY (1.1f,1,1,0.9f,1),
    MODEST (0.9f,1,1.1f,1,1),
    NAIVE (1,1,1,0.9f,1.1f),
    HASTY (1,0.9f,1,1,1.1f),
    CAREFUL (1,1,0.9f,1.1f,1),
    BASHFUL (1,1,1,1,1),
    RELAXED (1,1.1f,1,1,0.9f),
    ADAMANT (1.1f,1,0.9f,1,1),
    SERIOUS (1,1,1,1,1),
    LONELY (1.1f,0.9f,1,1,1),
    TIMID (0.9f,1,1,1,1.1f);

    private float atkMultiplier;
    private float defMultiplier;
    private float speAtkMultiplier;
    private float speDefMultiplier;
    private float spdMultiplier;

    Nature(float atkMultiplier, float defMultiplier, float speAtkMultiplier,
           float speDefMultiplier, float spdMultiplier)
    {
        this.atkMultiplier = atkMultiplier;
        this.defMultiplier = defMultiplier;
        this.speAtkMultiplier = speAtkMultiplier;
        this.speDefMultiplier = speDefMultiplier;
        this.spdMultiplier = spdMultiplier;
    }

    public float[] getMultipliers() {
        return new float[]{atkMultiplier, defMultiplier,
                speAtkMultiplier,speDefMultiplier, spdMultiplier};
    }
}
