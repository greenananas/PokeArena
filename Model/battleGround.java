package Model;
/**
 * Class décrivant le terrain d'un combat Pokémon.
 */
public class battleGround {
    private fieldTypes.field field;
    private fieldTypes.weather weather;
    private fieldTypes.traps leftsideTraps;
    private fieldTypes.traps rightsideTraps;

    public fieldTypes.field getTerrain() {
        return this.field;
    }

    public void setTerrain(fieldTypes.field t) {
        this.field = t;
    }
}
