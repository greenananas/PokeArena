package Model;

public class ChangePkmn extends Action{

    private final int newPokemonIndex;

    public ChangePkmn(int index) {
        super(6);
        newPokemonIndex = index;
    }

    public int getIndex() {
        return newPokemonIndex;
    }
}
