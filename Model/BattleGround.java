package Model;
/**
 * Class décrivant le terrain d'un combat Pokémon.
 */
public class BattleGround {
    private FieldTypes.field field;
    private FieldTypes.weather weather;
    private FieldTypes.traps leftsideTraps;
    private FieldTypes.traps rightsideTraps;

    public FieldTypes.field getTerrain() {
        return this.field;
    }

    public void setTerrain(FieldTypes.field t) {
        this.field = t;
    }
}
