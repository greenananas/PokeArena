package Model;

public enum Nature {
    BOLD (0.9f,1.1f,0,0,0),
    QUIRKY (0,0,0,0,0),
    BRAVE (1.1f,0,0,0,0.9f),
    CALM (0.9f,0,0,0.9f,0),
    QUIET (0,0,1.1f,0,0.9f),
    DOCILE (0,0,0,0,0),
    MILD (0,0.9f,1.1f,0,0),
    RASH (0,0,1.1f,0.9f,0),
    GENTLE (0,0.9f,0,1.1f,0),
    HARDY (0,0,0,0,0),
    JOLLY (0,0,0.9f,0,1.1f),
    LAX (0,1.1f,0,0.9f,0),
    IMPISH (0,1.1f,0.9f,0,0),
    SASSY (0,0,0,1.1f,0.9f),
    NAUGHTY (1.1f,0,0,0.9f,0),
    MODEST (0.9f,0,1.1f,0,0),
    NAIVE (0,0,0,0.9f,1.1f),
    HASTY (0,0.9f,0,0,1.1f),
    CAREFUL (0,0,0.9f,1.1f,0),
    BASHFUL (0,0,0,0,0),
    RELAXED (0,1.1f,0,0,0.9f),
    ADAMANT (1.1f,0,0.9f,0,0),
    SERIOUS (0,0,0,0,0),
    LONELY (1.1f,0.9f,0,0,0),
    TIMID (0.9f,0,0,0,1.1f);

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
