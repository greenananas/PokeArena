package PokeArena.PokeArenaBattle;

/**
 * Interface décrivant le choix d'action du dresseur (Attaque ou changement de Pokémon).
 */
public class Action {
    /**
     * Priorité d'une attaque. Une haute priorité implique que l'action aurap lus de chance de s'effectuer
     * en premier pendant le tour.
     */
    private int priority;

    /**
     * Créé un object Action permettant de représenter un type d'action utilisateur. Cela permet une communication
     * plus simple entre serveur et clients.
     *
     * @param priority Priorité d'une action durant le combat.
     */
    public Action ( int priority) {
        this.priority = priority;
    }

    /**
     * Retourne la priorité d'une action.
     *
     * @return La priorité de l'action.
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Change la priorité de l'action.
     *
     * @param priority Nouvelle priorité de l'action.
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }
}
