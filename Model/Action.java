package Model;
/**
 * Interface décrivant le choix d'action du dresseur (Attaque ou changement de Pokémon).
 */
public class Action {

    /**
     * Type de l'action choisie par le dresseur.
     */
    private acTypes type_action;

    /**
     * Priorité d'une attaque
     */
    private int priority;

    public Action (acTypes type, int priority) {
        this.type_action = type;
        this.priority = priority;
    }

    public acTypes getType() {
        return this.type_action;
    }

    public int getPriority() {
        return priority;
    }
}
