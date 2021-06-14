package Model;

public class MultipleSamePokemonException extends Exception{

    public MultipleSamePokemonException(String s, int pos_input_error){
        super(s);
        System.out.println("Position de la mauvaise input :" + pos_input_error);
    }

}
