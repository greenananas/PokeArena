package Model;

public class ChangePokemon implements Action{

    /**
     * Nouveau Pokémon désigné par le dresseur.
     */
    private Pokemon new_pokemon;

    /**
     * Valeur constante de priorité de l'action de changement de Pokémon.
     */
    private int priority = 6;

    /**
     * Récupérer le nouveau Pokémon.
     */
    public Pokemon getNew_pokemon() {
        return new_pokemon;
    }

    /**
     * Définir le nouveau Pokémon à envoyer.
     */
    public void setNew_pokemon(Pokemon new_pokemon) {
        this.new_pokemon = new_pokemon;
    }

    /**
     * Récupérer la valeur de priorité de l'action de changement de Pokémon.
     */
    public int getPriority() {
        return priority;
    }

}
