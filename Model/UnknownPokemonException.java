package Model;

public class UnknownPokemonException extends Exception{

    public UnknownPokemonException(String s,int pos_input_error){
        super(s);
        System.out.println("Positition de la mauvaise input : " + pos_input_error);
    }

}
