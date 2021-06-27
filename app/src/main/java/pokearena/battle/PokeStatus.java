package pokearena.battle;

import java.util.Random;

/**
 * Class énumérant les status.
 */
public class PokeStatus {

    private int duration;

    public Status status = Status.NORMAL;

    /**
     * Énumération des status.
     */
    public enum Status {
        NORMAL, ASLEEP, PARALYZED, POISONED, BADLY_POISONED, BURNT, FROZEN
    }

    public void resetDuration () {
        duration = 1;
    }

    public void giveStatus (PokeStatus.Status s) {
        status = s;
        duration = -1;
        if (s.equals(Status.ASLEEP)) {
            Random ran = new Random();
            duration = ran.nextInt(3) + 1;
        }
        if (s.equals(Status.BADLY_POISONED)) {
            duration = 1;
        }
    }

    /**
     * Retourne si le pokémon est capable d'effectuer son tour.
     *
     * @return Si le pokémon peut agir.
     */
    public boolean applyStatusEffect (Pokemon pkmn) {
        boolean b;
        switch (status) {
            case ASLEEP:
                if (duration > 0) {
                    b = false;
                    System.out.println(pkmn.getName() + " est endormi...");
                    duration -= 1;
                } else {
                    b = true;
                    System.out.println(pkmn.getName() + " se réveille !");
                    giveStatus(Status.NORMAL);
                }
                break;
            case PARALYZED:
                if (Math.random() < 0.25) {
                    System.out.println(pkmn.getName() + " est paralysé, il ne peut pas attaquer !");
                    b = false;
                }
                else {
                    b = true;
                }

                break;
            case FROZEN:
                if (Math.random() < 0.20){
                    b = true;
                    System.out.println(pkmn.getName() + " dégèle !");
                    giveStatus(Status.NORMAL);
                }
                else {
                    System.out.println(pkmn.getName() + " est gelé.");
                    b = false;
                }
                break;
            default:
                b = true;
        }
        return b;
    }

    public void applyStatusDamage (Pokemon pkmn) {
        switch (status) {
            case BURNT:
                pkmn.getDamaged(pkmn.getMaxHP()/16);
                break;
            case POISONED:
                pkmn.getDamaged(pkmn.getMaxHP()/8);
            case BADLY_POISONED:
                pkmn.getDamaged((pkmn.getMaxHP()/16)*duration);
                duration++;
        }
    }

}