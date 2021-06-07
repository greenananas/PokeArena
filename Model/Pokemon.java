package Model;

import Model.Utils.TerminalColors;

/**
 * Class décrivant un Pokémon.
 */
public class Pokemon {
    /**
     * Nom du Pokémon
     */
    private String name;

    /**
     * Types du Pokémon
     */
    private PokeTypes.type type1, type2;

    /**
     * Niveau du Pokémon.
     */
    private int level;

    /**
     * Points de vie restants du Pokémon.
     */
    private int hp;

    /**
     * Points de vie maximum du Pokémon.
     */
    private int fullhp;

    /**
     * Statistique d'attaque du Pokémon.
     */
    private int attack;

    /**
     * Statistique de défense du Pokémon.
     */
    private int defense;

    /**
     * Statistique d'attaque spéciale du Pokémon.
     */
    private int speAttack;

    /**
     * Statistique de défense spéciale du Pokémon.
     */
    private int speDefense;

    /**
     * Statistique de vitesse du Pokémon.
     */
    private int speed;

    /**
     * Précision du Pokémon.
     */
    private int precision = 100;

    /**
     * Esquive du Pokémon.
     */
    private int evasion = 100;

    /**
     * Statut du Pokémon.
     */
    private PokeStatus.status status = PokeStatus.status.NORMAL;

    /**
     * État de confusion du Pokémon.
     */
    private boolean confused = false;

    /**
     * Attaques du Pokémon.
     */
    private Move move1, move2, move3, move4;

    /**
     * Créer un Pokémon.
     *
     * @param n          Nom du Pokémon
     * @param t1         Premier type du Pokémon
     * @param t2         Second type du Pokémon
     * @param l          Niveau du Pokémon
     * @param hp         Points de vie du Pokémon
     * @param attack     Statistique d'attaque du Pokémon
     * @param defense    Statistique de défense du Pokémon
     * @param speAttack  Statistique d'attaque spéciale du Pokémon
     * @param speDefense Statistique de défense spéciale du Pokémon
     * @param speed      Statistique de vitesse du Pokémon
     * @param a1         Première attaque du Pokémon
     * @param a2         Deuxième attaque du Pokémon
     * @param a3         Troisième attaque du Pokémon
     * @param a4         Quatrième attaque du Pokémon
     */
    public Pokemon(String n, PokeTypes.type t1, PokeTypes.type t2, int l, int hp,
                   int attack, int defense, int speAttack, int speDefense, int speed,
                   Move a1, Move a2, Move a3, Move a4) {
        this.name = n;
        this.type1 = t1;
        this.type2 = t2;
        this.level = l;

        this.hp = hp;
        this.fullhp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speAttack = speAttack;
        this.speDefense = speDefense;
        this.speed = speed;

        this.move1 = a1;
        this.move2 = a2;
        this.move3 = a3;
        this.move4 = a4;
    }

    /**
     * Obtenir le nom du Pokémon.
     *
     * @return Nom du Pokémon
     */
    public String getName() {
        return this.name;
    }

    /**
     * Obtenir le premier type du Pokémon.
     *
     * @return Type du Pokémon
     */
    public PokeTypes.type getType1() {
        return this.type1;
    }

    /**
     * Obtenir le second type du Pokémon.
     *
     * @return Type du Pokémon
     */
    public PokeTypes.type getType2() {
        return this.type2;
    }

    /**
     * Obtenir le niveau du Pokémon.
     *
     * @return Niveau du Pokémon
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Obtenir les points de vie du Pokémon.
     *
     * @return Points de vie du Pokémon
     */
    public int getHP() {
        return this.hp;
    }

    /**
     * Obtenir le nombre de points de vie maximum du Pokémon.
     *
     * @return Points de vie maximum du Pokémon
     */
    public int getFullHP() {
        return this.fullhp;
    }

    /**
     * Obtenir la statistique d'attaque du Pokémon.
     *
     * @return Attaque du Pokémon
     */
    public int getAttack() {
        return this.attack;
    }

    /**
     * Obtenir la statistique de défense du Pokémon.
     *
     * @return Défense du Pokémon
     */
    public int getDefense() {
        return this.defense;
    }

    /**
     * Obtenir la statistique d'attaque spéciale du Pokémon.
     *
     * @return Attaque Spéciale du Pokémon
     */
    public int getSpeAttack() {
        return this.speAttack;
    }

    /**
     * Obtenir la statistique de défense spéciale du Pokémon.
     *
     * @return Défense spéciale du Pokémon
     */
    public int getSpeDefense() {
        return this.speDefense;
    }

    /**
     * Obtenir la statistique de vitesse du Pokémon.
     *
     * @return Vitesse du Pokémon
     */
    public int getSpeed() {
        return this.speed;
    }

    /**
     * Obtenir la première attaque du Pokémon.
     *
     * @return Attaque 1 du Pokémon
     */
    public Move getMove1() {
        return this.move1;
    }

    /**
     * Obtenir la deuxième attaque du Pokémon.
     *
     * @return Attaque 2 du Pokémon
     */
    public Move getMove2() {
        return this.move2;
    }

    /**
     * Obtenir la troisième attaque du Pokémon.
     *
     * @return Attaque 3 du Pokémon
     */
    public Move getMove3() {
        return this.move3;
    }

    /**
     * Obtenir la quatrième attaque du Pokémon.
     *
     * @return Attaque 4 du Pokémon
     */
    public Move getMove4() {
        return this.move4;
    }

    /**
     * Obtenir la précision du Pokémon.
     *
     * @return Précision du Pokémon
     */
    public int getPrecision() {
        return this.precision;
    }

    /**
     * Obtenir l'esquive du Pokémon.
     *
     * @return Esquive du Pokémon
     */
    public int getEvasion() {
        return this.evasion;
    }

    /**
     * Obtenir le statut du Pokémon.
     *
     * @return Statut du Pokémon
     */
    public PokeStatus.status getStatus() {
        return this.status;
    }

    /**
     * Vérifier que le Pokémon est confus.
     *
     * @return Pokémon confus
     */
    public boolean isConfused() {
        return this.confused;
    }

    /**
     * Modifier le nom du Pokémon.
     *
     * @param n Nouveau nom du Pokémon
     */
    public void setName(String n) {
        this.name = n;
    }

    /**
     * Modifier le premier type du Pokémon.
     *
     * @param t Nouveau type du Pokémon
     */
    public void setType1(PokeTypes.type t) {
        this.type1 = t;
    }

    /**
     * Modifier le second type du Pokémon.
     *
     * @param t Nouveau type du Pokémon
     */
    public void setType2(PokeTypes.type t) {
        this.type2 = t;
    }

    /**
     * Modifier le niveau du Pokémon.
     *
     * @param l Nouveau niveau du Pokémon
     */
    public void setLevel(int l) {
        this.level = l;
    }

    /**
     * Modifier les points de vie du Pokémon.
     *
     * @param hp Nouveaux points de vie du Pokémon
     */
    public void setHP(int hp) {
        this.hp = hp;
    }

    /**
     * Modifier les points de vie maximum du Pokémon.
     *
     * @param hp Nouveaux points de vie maximum du Pokémon
     */
    public void setFullHP(int hp) {
        this.fullhp = hp;
    }

    /**
     * Modifier la statistique d'attaque du Pokémon.
     *
     * @param a Nouvelle statistique d'attaque du Pokémon
     */
    public void setAttack(int a) {
        this.attack = a;
    }

    /**
     * Modifier la statistique de défense du Pokémon.
     *
     * @param a Nouvelle statistique de défense du Pokémon
     */
    public void setDefense(int a) {
        this.defense = a;
    }

    /**
     * Modifier la statistique d'attaque spéciale du Pokémon.
     *
     * @param a Nouvelle statistique d'attaque spéciale du Pokémon
     */
    public void setSpeAttack(int a) {
        this.speAttack = a;
    }

    /**
     * Modifier la statistique de défense spéciale du Pokémon.
     *
     * @param a Nouvelle statistique de défense spéciale du Pokémon
     */
    public void setSpeDefense(int a) {
        this.speDefense = a;
    }

    /**
     * Modifier la statistique de vitesse du Pokémon.
     *
     * @param a Nouvelle statistique de vitesse du Pokémon
     */
    public void setSpeed(int a) {
        this.speed = a;
    }

    /**
     * Modifier la première attaque du Pokémon.
     *
     * @param a Nouvelle première attaque du Pokémon
     */
    public void setMove1(Move a) {
        this.move1 = a;
    }

    /**
     * Modifier la deuxième attaque du Pokémon.
     *
     * @param a Nouvelle deuxième attaque du Pokémon
     */
    public void setMove2(Move a) {
        this.move2 = a;
    }

    /**
     * Modifier la troisième attaque du Pokémon.
     *
     * @param a Nouvelle troisième attaque du Pokémon
     */
    public void setMove3(Move a) {
        this.move3 = a;
    }

    /**
     * Modifier la quatrième attaque du Pokémon.
     *
     * @param a Nouvelle quatrième attaque du Pokémon
     */
    public void setMove4(Move a) {
        this.move4 = a;
    }

    /**
     * Modifier la précision du Pokémon.
     *
     * @param p Nouvelle précision du Pokémon
     */
    public void setPrecision(int p) {
        this.precision = p;
    }

    /**
     * Modifier l'esquive du Pokémon.
     *
     * @param e Nouvelle esquive du Pokémon
     */
    public void setEvasion(int e) {
        this.evasion = e;
    }

    /**
     * Modifier le statut du Pokémon.
     *
     * @param s Nouveau statut du Pokémon
     */
    public void setStatus(PokeStatus.status s) {
        this.status = s;
    }

    /**
     * Rendre un Pokémon confus ou le sortir de confusion.
     *
     * @param c Confus
     */
    public void setConfused(boolean c) {
        this.confused = c;
    }

    /**
     * Vérifier que le Pokémon peut encore se battre.
     *
     * @return Le Pokémon est KO
     */
    public boolean isKO() {
        return this.getHP() <= 0;
    }


    /**
     * Vérifier que l'attaque est du même type que le Pokémon.
     *
     * @param a L'attaque à vérifier
     * @return Le Pokémon est de même type
     */
    public boolean isStabbed(Move a) {
        if (this.getType1() == a.getMoveType()) {
            return true;
        } else if (this.getType2() != null) {
            if (this.getType2() == a.getMoveType()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retirer des points de vie au Pokémon.
     *
     * @param damage Dégats infligés au Pokémon
     */
    public void getDamaged(int damage) {
        this.setHP(this.getHP() - damage);
    }

    /**
     * Faire attaquer le Pokémon.
     *
     * @param target Cible de l'attaque
     * @param at     Attaque lancée
     */
    public void attack(Pokemon target, Move at) {
        if (doesHit(target, at)) {
            if (target.getTypeMultiplier(at) != 0) {
                System.out.println("\nL'attaque " + at.getName() + " a touché " + target.getName() + ".");
                target.getDamaged(this.getHitDamage(target, at));
            } else {
                System.out.println("\nÇa n'affecte pas " + target.getName() + "...");
            }
        } else {
            System.out.println("\nL'attaque " + at.getName() + " a échoué.");
        }
    }

    /**
     * L'attaque du Pokémon va-t-elle atteindre sa cible.
     *
     * @param target Cible de l'attaque
     * @param at     Attaque lancée
     * @return L'attaque lancée touche l'ennemi
     */
    public boolean doesHit(Pokemon target, Move at) {
        int precision = (int) Math.floor(Math.random() * 100);
        if (precision <= at.getPrecision()) {
            return true;
        }
        return false;
    }

    /**
     * Calcul des dégâts infligés par une attaque..
     *
     * @param target Cible de l'attaque
     * @param at     Attaque lancée
     * @return Le nombre de points de vie infligés à la cible
     */
    public int getHitDamage(Pokemon target, Move at) {
        double damageAlgo;
        if (at.isPhysical()) {
            damageAlgo = ((((2 * (double) this.getLevel()) / 5) + 2) * at.getPower() * ((double) this.getAttack() / (double) target.getDefense())) / 50;
        } else {
            damageAlgo = ((((2 * (double) this.getLevel()) / 5) + 2) * at.getPower() * ((double) this.getSpeAttack() / (double) target.getSpeDefense())) / 50;
        }

        double randomMultiplier = this.getRandomMultiplier();

        double typeMultiplier = target.getTypeMultiplier(at);
        if (typeMultiplier > 1) {
            System.out.println("\nC'est super efficace!");
        } else if (typeMultiplier < 1) {
            System.out.println("\nCe n'est pas très efficace...");
        }

        double stabMultiplier = 1;
        if (this.isStabbed(at)) {
            stabMultiplier = 1.5;
        }

        double critMultiplier = 1;
        if (isCritical(at)) {
            critMultiplier = 1.5;
        }

        double burntMultiplier = 1;
        if (this.getStatus() == PokeStatus.status.BURNT && at.isPhysical()) {
            burntMultiplier = 0.5;
        }

        return (int) Math.floor(damageAlgo * randomMultiplier * typeMultiplier * stabMultiplier * critMultiplier * burntMultiplier);
    }

    /**
     * Obtenir le multiplicateur aléatoire des dégâts d'une attaque.
     *
     * @return Nombre compris entre 0.85 et 1
     */
    public double getRandomMultiplier() {
        return Math.random() * 0.15 + 0.85;
    }

    /**
     * Obtenir le multiplicateur de dégâts lié au type de l'attaque
     *
     * @param at Attaque lancée
     * @return Multiplicateur compris entre 0 et 4
     */
    public double getTypeMultiplier(Move at) {
        double typeMultiplier = PokeTypes.getTypeAdvantage(this.getType1(), at.getMoveType()).getEffectiveness();
        if (this.getType2() != null) {
            typeMultiplier *= PokeTypes.getTypeAdvantage(this.getType2(), at.getMoveType()).getEffectiveness();
        }
        return typeMultiplier;
    }

    /**
     * L'attaque est-elle un coup critique.
     *
     * @param at Attaque lancée
     * @return L'attaque est un coup critique
     */
    public boolean isCritical(Move at) {
        double critProb = 1 / 24;
        if (this.getCritStage(at) == 1) {
            critProb = 1 / 8;
        }
        if (this.getCritStage(at) == 2) {
            critProb = 1 / 2;
        }
        if (this.getCritStage(at) == 3) {
            critProb = 1;
        }

        double critRandom = (int) Math.floor(Math.random());
        if (critRandom < critProb) {
            System.out.println("\nCoup critique!");
            return true;
        }
        return false;
    }

    /**
     * Obtenir le niveau de probabilité d'obtenir un coup critique
     *
     * @param at Attaque lancée
     * @return Niveau de probabilité (0, 1, 2 ou 3)
     */
    public int getCritStage(Move at) {
        //non implémenté
        return 0;
    }

    public boolean isFaster(Pokemon p) {
        if (this.getSpeed() > p.getSpeed()) {
            return true;
        } else if (this.getSpeed() < p.getSpeed()) {
            return false;
        } else {
            if (Math.random() > 0.5) {
                return true;
            } else {
                return false;
            }
        }
    }

    public String showHP() {
        String hpBarString = this.getName() + " : |";
        int hpBar = (int) Math.ceil(((double) this.getHP() / (double) this.getFullHP()) * 10);
        String barColor;
        if (hpBar > 5) {
            barColor = TerminalColors.ANSI_GREEN;
        } else if (hpBar > 3) {
            barColor = TerminalColors.ANSI_YELLOW;
        } else {
            barColor = TerminalColors.ANSI_RED;
        }
        for (int i = 0; i < 10; i++) {
            if (hpBar > i) {
                hpBarString += barColor + "█";
            } else {
                hpBarString += TerminalColors.ANSI_WHITE + "-";
            }
        }
        hpBarString += TerminalColors.ANSI_WHITE + "| " + this.getHP() + "HP";
        return hpBarString;
    }
}