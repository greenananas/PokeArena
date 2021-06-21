package Model;

import Model.Utils.TerminalColors;

import java.util.ArrayList;

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
     * Statistique de points de vie de base du Pokémon.
     */
    private int baseHP;

    /**
     * Statistique d'attaque de base du Pokémon.
     */
    private int baseAttack;

    /**
     * Statistique de défense de base du Pokémon.
     */
    private int baseDefence;

    /**
     * Statistique d'attaque spéciale de base du Pokémon.
     */
    private int baseSpeAttack;

    /**
     * Statistique de défense spéciale de base du Pokémon.
     */
    private int baseSpeDefence;

    /**
     * Statistique de vitesse de base du Pokémon.
     */
    private int baseSpeed;

    /**
     * Points d'effort des points de vie du Pokémon.
     */
    private int hpEV;

    /**
     * Points d'effort d'attaque du Pokémon.
     */
    private int attackEV;

    /**
     * Points d'effort de défense du Pokémon.
     */
    private int defenceEV;

    /**
     * Points d'effort d'attaque spéciale du Pokémon.
     */
    private int speAttackEV;

    /**
     * Point d'effort de défense spéciale du Pokémon.
     */
    private int speDefenceEV;

    /**
     * Points d'efforts de vitesse du Pokémon.
     */
    private int speedEV;

    /**
     * Points de vie restants du Pokémon.
     */
    private int hp;

    /**
     * Points de vie maximum du Pokémon.
     */
    private int maxhp;

    /**
     * Statistique d'attaque du Pokémon.
     */
    private int attack;

    /**
     * Statistique de défense du Pokémon.
     */
    private int defence;

    /**
     * Statistique d'attaque spéciale du Pokémon.
     */
    private int speAttack;

    /**
     * Statistique de défense spéciale du Pokémon.
     */
    private int speDefence;

    /**
     * Statistique de vitesse du Pokémon.
     */
    private int speed;

    /**
     * Modificateur d'attaque du Pokémon.
     */
    private int attackMod = 0;

    /**
     * Modificateur de défense du Pokémon.
     */
    private int defenceMod = 0;

    /**
     * Modificateur d'attaque spéciale du Pokémon.
     */
    private int speAttackMod = 0;

    /**
     * Modificateur de défense spéciale du Pokémon.
     */
    private int speDefenceMod = 0;

    /**
     * Modificateur de vitesse du Pokémon.
     */
    private int speedMod = 0;

    /**
     * Nature du Pokémon.
     */
    private Nature nature;

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
    private PokeStatus status = new PokeStatus();

    /**
     * État de confusion du Pokémon.
     */
    private boolean confused = false;

    /**
     * Attaques du Pokémon.
     */
    private Move move1, move2, move3, move4;

    /**
     * Identifiant du Pokémon inscrit dans notre BDD.
     */
    private int id;

    /**
     * Créer un Pokémon.
     *
     * @param n            Nom du Pokémon
     * @param t1           Premier type du Pokémon
     * @param t2           Second type du Pokémon
     * @param l            Niveau du Pokémon
     * @param hp           Points de vie du Pokémon
     * @param attack       Statistique d'attaque du Pokémon
     * @param defence      Statistique de défense du Pokémon
     * @param speAttack    Statistique d'attaque spéciale du Pokémon
     * @param speDefence   Statistique de défense spéciale du Pokémon
     * @param speed        Statistique de vitesse du Pokémon
     * @param attackev     Statistique d'attaque du Pokémon
     * @param defenceev    Statistique de défense du Pokémon
     * @param speattackev  Statistique d'attaque spéciale du Pokémon
     * @param spedefenceev Statistique de défense spéciale du Pokémon
     * @param speedev      Statistique de vitesse du Pokémon
     * @param nature       Nature du Pokémon
     * @param a1           Première attaque du Pokémon
     * @param a2           Deuxième attaque du Pokémon
     * @param a3           Troisième attaque du Pokémon
     * @param a4           Quatrième attaque du Pokémon
     * @param ident        Identifiant du Pokémon inscrit dans la BDD.
     */
    public Pokemon(String n, PokeTypes.type t1, PokeTypes.type t2, int l,
                   int hp, int attack, int defence, int speAttack, int speDefence, int speed,
                   int hpev, int attackev, int defenceev, int speattackev, int spedefenceev, int speedev,
                   String nature, Move a1, Move a2, Move a3, Move a4, int ident) {
        this.name = n;
        this.type1 = t1;
        this.type2 = t2;
        this.level = l;

        this.baseHP = hp;
        this.baseAttack = attack;
        this.baseDefence = defence;
        this.baseSpeAttack = speAttack;
        this.baseSpeDefence = speDefence;
        this.baseSpeed = speed;
        this.hpEV = hpev;
        this.attackEV = attackev;
        this.defenceEV = defenceev;
        this.speAttackEV = speattackev;
        this.speDefenceEV = spedefenceev;
        this.speedEV = speedev;

        this.move1 = a1;
        this.move2 = a2;
        this.move3 = a3;
        this.move4 = a4;

        this.maxhp = Math.round(((2 * baseHP + 31 + Math.round(hpEV / 4f)) * level) / 100f) + level + 10;
        this.attack = Math.round(((2 * baseAttack + 31 + Math.round(attackEV / 4f)) * level) / 100f + 5);
        this.defence = Math.round(((2 * baseDefence + 31 + Math.round(defenceEV / 4f)) * level) / 100f + 5);
        this.speAttack = Math.round(((2 * baseSpeAttack + 31 + Math.round(speAttackEV / 4f)) * level) / 100f + 5);
        this.speDefence = Math.round(((2 * baseSpeDefence + 31 + Math.round(speDefenceEV / 4f)) * level) / 100f + 5);
        this.speed = Math.round(((2 * baseSpeed + Math.round(speedEV / 4f)) * level) / 100f + 5);
        this.hp = maxhp;
        applyNature(nature);

        this.id = ident;
    }

    /**
     * Obtenir le nom du Pokémon.
     *
     * @return Nom du Pokémon.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Obtenir le premier type du Pokémon.
     *
     * @return Type du Pokémon.
     */
    public PokeTypes.type getType1() {
        return this.type1;
    }

    /**
     * Obtenir le second type du Pokémon.
     *
     * @return Type du Pokémon.
     */
    public PokeTypes.type getType2() {
        return this.type2;
    }

    /**
     * Obtenir le niveau du Pokémon.
     *
     * @return Niveau du Pokémon.
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Obtenir les points de vie du Pokémon.
     *
     * @return Points de vie du Pokémon.
     */
    public int getHP() {
        return this.hp;
    }

    /**
     * Obtenir le nombre de points de vie maximum du Pokémon.
     *
     * @return Points de vie maximum du Pokémon.
     */
    public int getMaxHP() {
        return this.maxhp;
    }

    /**
     * Obtenir la statistique d'attaque du Pokémon.
     *
     * @return Attaque du Pokémon.
     */
    public int getAttack() {
        return this.attack;
    }

    /**
     * Obtenir la statistique de défense du Pokémon.
     *
     * @return Défense du Pokémon.
     */
    public int getDefence() {
        return this.defence;
    }

    /**
     * Obtenir la statistique d'attaque spéciale du Pokémon.
     *
     * @return Attaque Spéciale du Pokémon.
     */
    public int getSpeAttack() {
        return this.speAttack;
    }

    /**
     * Obtenir la statistique de défense spéciale du Pokémon.
     *
     * @return Défense spéciale du Pokémon.
     */
    public int getSpeDefence() {
        return this.speDefence;
    }

    /**
     * Obtenir la statistique de vitesse du Pokémon.
     *
     * @return Vitesse du Pokémon.
     */
    public int getSpeed() {
        return this.speed;
    }

    /**
     * Obtenir le modificateur d'attaque du Pokémon.
     *
     * @return Modificateur d'attaque du Pokémon.
     */
    public int getAttackModifier() {
        return attackMod;
    }

    /**
     * Obtenir le modificateur de défense du Pokémon.
     *
     * @return Modificateur de défense du Pokémon.
     */
    public int getDefenceModifier() {
        return defenceMod;
    }

    /**
     * Obtenir le modificateur d'attaque spéciale du Pokémon.
     *
     * @return Modificateur d'attaque spéciale du Pokémon.
     */
    public int getSpeAttackModifier() {
        return speAttackMod;
    }

    /**
     * Obtenir le modificateur de défense spéciale du Pokémon.
     *
     * @return Modificateur de défense spéciale du Pokémon.
     */
    public int getSpeDefenceModifier() {
        return speDefenceMod;
    }

    /**
     * Obtenir le modificateur de vitesse du Pokémon.
     *
     * @return Modificateur de vitesse du Pokémon.
     */
    public int getSpeedModifier() {
        return speedMod;
    }

    /**
     * Obtenir la première attaque du Pokémon.
     *
     * @return Attaque 1 du Pokémon.
     */
    public Move getMove1() {
        return this.move1;
    }

    /**
     * Obtenir la deuxième attaque du Pokémon.
     *
     * @return Attaque 2 du Pokémon.
     */
    public Move getMove2() {
        return this.move2;
    }

    /**
     * Obtenir la troisième attaque du Pokémon.
     *
     * @return Attaque 3 du Pokémon.
     */
    public Move getMove3() {
        return this.move3;
    }

    /**
     * Obtenir la quatrième attaque du Pokémon.
     *
     * @return Attaque 4 du Pokémon.
     */
    public Move getMove4() {
        return this.move4;
    }

    /**
     * Obtenir la précision du Pokémon.
     *
     * @return Précision du Pokémon.
     */
    public int getPrecision() {
        return this.precision;
    }

    /**
     * Obtenir l'esquive du Pokémon.
     *
     * @return Esquive du Pokémon.
     */
    public int getEvasion() {
        return this.evasion;
    }

    public ArrayList<Move> getMove() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(move1);
        moves.add(move2);
        moves.add(move3);
        moves.add(move4);
        return moves;
    }

    /**
     * Obtenir le statut du Pokémon.
     *
     * @return Statut du Pokémon.
     */
    public PokeStatus getStatus() {
        return this.status;
    }

    /**
     * Obtenir l'identifiant BDD du Pokémon.
     *
     * @return Identifiant BDD du Pokémon.
     */
    public int getId() {
        return id;
    }

    /**
     * Vérifier que le Pokémon est confus.
     *
     * @return Pokémon confus.
     */
    public boolean isConfused() {
        return this.confused;
    }

    /**
     * Modifier le nom du Pokémon.
     *
     * @param n Nouveau nom du Pokémon.
     */
    public void setName(String n) {
        this.name = n;
    }

    /**
     * Modifier le premier type du Pokémon.
     *
     * @param t Nouveau type du Pokémon.
     */
    public void setType1(PokeTypes.type t) {
        this.type1 = t;
    }

    /**
     * Modifier le second type du Pokémon.
     *
     * @param t Nouveau type du Pokémon.
     */
    public void setType2(PokeTypes.type t) {
        this.type2 = t;
    }

    /**
     * Modifier le niveau du Pokémon.
     *
     * @param l Nouveau niveau du Pokémon.
     */
    public void setLevel(int l) {
        this.level = l;
    }

    /**
     * Modifier les points de vie du Pokémon.
     *
     * @param hp Nouveaux points de vie du Pokémon.
     */
    public void setHP(int hp) {
        this.hp = hp;
    }

    /**
     * Modifier les points de vie maximum du Pokémon.
     *
     * @param hp Nouveaux points de vie maximum du Pokémon.
     */
    public void setFullHP(int hp) {
        this.maxhp = hp;
    }

    /**
     * Modifier la statistique d'attaque du Pokémon.
     *
     * @param a Nouvelle statistique d'attaque du Pokémon.
     */
    public void setAttack(int a) {
        this.attack = a;
    }

    /**
     * Modifier la statistique de défense du Pokémon.
     *
     * @param a Nouvelle statistique de défense du Pokémon.
     */
    public void setDefence(int a) {
        this.defence = a;
    }

    /**
     * Modifier la statistique d'attaque spéciale du Pokémon.
     *
     * @param a Nouvelle statistique d'attaque spéciale du Pokémon.
     */
    public void setSpeAttack(int a) {
        this.speAttack = a;
    }

    /**
     * Modifier la statistique de défense spéciale du Pokémon.
     *
     * @param a Nouvelle statistique de défense spéciale du Pokémon.
     */
    public void setSpeDefence(int a) {
        this.speDefence = a;
    }

    /**
     * Modifier la statistique de vitesse du Pokémon.
     *
     * @param a Nouvelle statistique de vitesse du Pokémon.
     */
    public void setSpeed(int a) {
        this.speed = a;
    }

    /**
     * Obtenir le modificateur d'attaque du Pokémon.
     *
     * @return Nouveau modificateur d'attaque du Pokémon.
     */
    public void setAttackModifier(int attackMod) {
        this.attackMod = attackMod;
        if (this.attackMod > 6) this.attackMod = 6;
    }

    /**
     * Modifier le modificateur de défense du Pokémon.
     *
     * @param defenceMod Nouveau modificateur de défense du Pokémon.
     */
    public void setDefenceModifier(int defenceMod) {
        this.defenceMod = defenceMod;
        if (this.defenceMod > 6) this.defenceMod = 6;
    }

    /**
     * Modifier le modificateur d'attaque spéciale du Pokémon.
     *
     * @param speAttackMod Nouveau modificateur d'attaque spéciale du Pokémon.
     */
    public void setSpeAttackModifier(int speAttackMod) {
        this.speAttackMod = speAttackMod;
        if (this.speAttackMod > 6) this.speAttackMod = 6;
    }

    /**
     * Modifier le modificateur de défense spéciale du Pokémon.
     *
     * @param speDefenceMod Nouveau modificateur de défense spéciale du Pokémon.
     */
    public void setSpeDefenceModifier(int speDefenceMod) {
        this.speDefenceMod = speDefenceMod;
        if (this.speDefenceMod > 6) this.speDefenceMod = 6;
    }

    /**
     * Modifier le modificateur de vitesse du Pokémon.
     *
     * @param speedMod Nouveau modificateur de vitesse du Pokémon.
     */
    public void setSpeedModifier(int speedMod) {
        this.speedMod = speedMod;
        if (this.speedMod > 6) this.speedMod = 6;
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
    public void setStatus(PokeStatus.Status s) {
        if (this.status.status.equals(PokeStatus.Status.NORMAL))
            this.status.giveStatus(s);
        else
            System.out.println("Cela n'aura aucun effet...");
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
     *  Retourne si oui ou non le pokémon possède un certain type.
     *
     * @param type Le type recherché.
     * @return Le pokémon possède le type.
     */
    public boolean hasType(PokeTypes.type type) {
        return (type1 == type || type2 == type);
    }

    /**
     * Applique les effets de la nature associée au Pokémon
     *
     * @param nature La nature du Pokémon
     */
    private void applyNature(String nature) {
        this.nature = Nature.valueOf(nature.toUpperCase());
        this.attack = Math.round(this.attack * this.nature.getMultipliers()[0]);
        this.defence = Math.round(this.defence * this.nature.getMultipliers()[1]);
        this.speAttack = Math.round(this.speAttack * this.nature.getMultipliers()[2]);
        this.speDefence = Math.round(this.speDefence * this.nature.getMultipliers()[3]);
        this.speed = Math.round(this.speed * this.nature.getMultipliers()[4]);
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
        this.setHP(Math.max(this.getHP() - damage, 0));
    }

    /**
     * Faire attaquer le Pokémon.
     *
     * @param target Cible de l'attaque
     * @param at     Attaque lancée
     */
    public void attack(Pokemon target, Move at) {
        if (target.getTypeMultiplier(at.getMoveType()) != 0) {
            System.out.println("\nL'attaque " + at.getName() + " a touché " + target.getName() + ".");
            target.getDamaged(this.getHitDamage(target, at));
        } else {
            System.out.println("\nÇa n'affecte pas " + target.getName() + "...");
        }
        at.setPP(at.getPP() - 1);
    }

    /**
     * Calcul des dégâts infligés par une attaque..
     *
     * @param target Cible de l'attaque
     * @param at     Attaque lancée
     * @return Le nombre de points de vie infligés à la cible
     */
    public int getHitDamage(Pokemon target, Move at) { //Prendre en compte les changements de stats
        double damageAlgo;
        if (at.isPhysical()) {
            damageAlgo = ((((2 * (double) this.getLevel()) / 5) + 2) * at.getPower() * ((double) this.getAttack() / (double) target.getDefence())) / 50;
        } else {
            damageAlgo = ((((2 * (double) this.getLevel()) / 5) + 2) * at.getPower() * ((double) this.getSpeAttack() / (double) target.getSpeDefence())) / 50;
        }

        double randomMultiplier = this.getRandomMultiplier();

        double typeMultiplier = target.getTypeMultiplier(at.getMoveType());
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
        if (this.getStatus().status == PokeStatus.Status.BURNT && at.isPhysical()) {
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
    public double getTypeMultiplier(PokeTypes.type at) {
        double typeMultiplier = PokeTypes.getTypeAdvantage(at, this.getType1()).getEffectiveness();
        if (this.getType2() != null) {
            typeMultiplier *= PokeTypes.getTypeAdvantage(at, this.getType2()).getEffectiveness();
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
        if (this.getCritStage(at) >= 3) {
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
     * Obtenir le niveau de probabilité d'obtenir un coup critique.
     *
     * @param at Attaque lancée.
     * @return Niveau de probabilité (1, 2, 3 ou 4).
     */
    public int getCritStage(Move at) {
        //Tenir compte de nature, objet et changement de stats lorsque implémenté
        return at.getCritRate();
    }

    public boolean isFaster(Pokemon p) {
        double ownSpeedMultiplier = (status.status.equals(PokeStatus.Status.PARALYZED) ? 0.5 : 1);
        double ennemySpeedMultiplier = (p.getStatus().status.equals(PokeStatus.Status.PARALYZED) ? 0.5 : 1);
        if (this.getSpeed()*ownSpeedMultiplier > p.getSpeed()*ennemySpeedMultiplier) {
            return true;
        } else if (this.getSpeed()*ownSpeedMultiplier < p.getSpeed()*ennemySpeedMultiplier) {
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
        int hpBar = (int) Math.ceil(((double) this.getHP() / (double) this.getMaxHP()) * 10);
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

    @Override
    public String toString() {
        return type1 + " " + (type2 == null ? "" : type2) + " | " + showHP() + " | " + status + (confused ? "CONFUS" : "");
    }
}