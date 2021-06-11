package Model;
/**
 * Interface décrivant le choix d'action du dresseur (Attaque ou changement de Pokémon).
 */
public class Action {
    /**
     * Priorité d'une attaque
     */
    private int priority;

    public Action ( int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
