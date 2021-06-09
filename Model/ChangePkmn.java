package Model;

public class ChangePkmn extends Action{

    private int indexNewPokemon;

    public ChangePkmn(int index) {
        super(acTypes.CHANGEPKM, 6);
        indexNewPokemon = index;
    }

    public int getIndex() {
        return indexNewPokemon;
    }
}
