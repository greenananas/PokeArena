package PokeArena.PokeArenaBattle;

/**
 * Class décrivant l'attaque d'un Pokémon.
 */
public class Move extends Action {

    /**
     * Identifiant de l'attaque
     */
    private final int id;

    /**
     * Nom de l'attaque
     */
    private String name;

    /**
     * Type de l'attaque
     */
    private PokeTypes.type type;

    /**
     * Attaque physique ou spéciale
     */
    private boolean physical;

    /**
     * Puissance de l'attaque
     */
    private int power;

    /**
     * Precision de l'attaque
     */
    private int precision;

    /**
     * Points de pouvoir d'une attaque
     */
    private int pp;

    /**
     * Taux de critique de base d'une attaque
     */
    private int critRate;

    /**
     * Créer une attaque
     *
     * @param id         Identifiant de référence de l'attaque
     * @param name       Nom de l'attaque
     * @param type       Type de l'attaque
     * @param isPhysical Attaque physique ou spéciale
     * @param power      Puissance de l'attaque
     * @param precision  Précision de l'attaque
     * @param pp         Points de pouvoir de l'attaque
     * @param priority   Priorité d'une attaque
     * @param critRate   Niveau de critique d'une attaque
     */
    public Move(int id, String name, PokeTypes.type type, boolean isPhysical, int power, int precision, int pp, int priority, int critRate) {
        super(priority);
        this.id = id;
        this.name = name;
        this.type = type;
        this.physical = isPhysical;
        this.power = power;
        this.precision = precision;
        this.pp = pp;
        this.critRate = critRate;
    }

    /**
     * Obtenir l'identifiant de l'attaque
     *
     * @return Numero d'identification
     */
    public int getId() {
        return id;
    }

    /**
     * Obtenir le nom de l'attaque
     *
     * @return Nom de l'attaque
     */
    public String getName() {
        return this.name;
    }

    /**
     * Obtenir le type de l'attaque
     *
     * @return Type de l'attaque
     */
    public PokeTypes.type getMoveType() {
        return this.type;
    }

    /**
     * Vérifier que l'attaque est physique
     *
     * @return Attaque physique ou spéciale
     */
    public boolean isPhysical() {
        return this.physical;
    }

    /**
     * Obtenir la puissance de l'attaque
     *
     * @return Puissance de l'attaque
     */
    public int getPower() {
        return this.power;
    }

    /**
     * Obtenir la précision de l'attaque
     *
     * @return Précision de l'attaque
     */
    public int getPrecision() {
        return this.precision;
    }

    /**
     * Obtenir les points de pouvoir de l'attaque
     *
     * @return Points de pouvoir de l'attaque
     */
    public int getPP() {
        return this.pp;
    }

    /**
     * Obtenir le taux de critique d'une attaque
     *
     * @return Taux de critique de l'attaque
     */
    public int getCritRate() {
        return critRate;
    }

    /**
     * Modifier le nom de l'attaque
     *
     * @param n Nouveau nom de l'attaque
     */
    public void setName(String n) {
        this.name = n;
    }

    /**
     * Modifier le type de l'attaque
     *
     * @param t Nouveau type de l'attaque
     */
    public void setType(PokeTypes.type t) {
        this.type = t;
    }

    /**
     * Rendre l'attaque physique ou spéciale
     *
     * @param p Physique (true) ou spéciale (false)
     */
    public void setPhysical(boolean p) {
        this.physical = p;
    }

    /**
     * Modifier la puissance de l'attaque
     *
     * @param p Nouvelle puissance de l'attaque
     */
    public void setPower(int p) {
        this.power = p;
    }

    /**
     * Modifier la précision de l'attaque
     *
     * @param p Nouvelle précision de l'attaque
     */
    public void setPrecision(int p) {
        this.precision = p;
    }

    /**
     * Modifier les points de pouvoir de l'attaque
     *
     * @param pp Points de pouvoir de l'attaque
     */
    public void setPP(int pp) {
        this.pp = pp;
    }

    /**
     * Modifier le taux de critique d'uen attaque
     *
     * @param critRate Nouveau taux de critique de l'attaque
     */
    public void setCritRate(int critRate) {
        this.critRate = critRate;
    }

    @Override
    public String toString() {
        String mv = (physical ? "Phy." : "Spé.");
        return " " + type + " | " + name + " | " + mv + " | Puissance = " + power +
                " | Précision = " + precision +
                " | PP = " + pp;
    }
}