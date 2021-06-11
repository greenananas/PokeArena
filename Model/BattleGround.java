package Model;

import java.util.HashMap;
import java.util.Map;

/**
 * Class décrivant le terrain d'un combat Pokémon.
 */
public class BattleGround {
    private FieldTypes.field field;
    private FieldTypes.weather weather;
    private HashMap<FieldTypes.traps, Integer> leftsideTraps;
    private HashMap<FieldTypes.traps, Integer> rightsideTraps;

    BattleGround () {
        this.field = null;
        this.weather = null;
        this.leftsideTraps = new HashMap<>();
        this.rightsideTraps = new HashMap<>();
        for (FieldTypes.traps trap: FieldTypes.traps.values()) {
            leftsideTraps.put(trap, 0);
            rightsideTraps.put(trap, 0);
        }

    }

    public FieldTypes.field getTerrain() {
        return this.field;
    }

    public void setTerrain(FieldTypes.field t) {
        this.field = t;
    }

    public FieldTypes.weather getWeather() {
        return weather;
    }

    public void setWeather(FieldTypes.weather weather) {
        this.weather = weather;
    }

    public HashMap<FieldTypes.traps, Integer> getTraps(boolean left) {
        return (left ? leftsideTraps : rightsideTraps);
    }

    public void addTraps(Move mv, boolean left) {
        HashMap<FieldTypes.traps, Integer> traps = (left ? leftsideTraps : rightsideTraps);
        switch (mv.getName().toUpperCase()) {
            case "PICOTS" :
                traps.replace(FieldTypes.traps.SPIKES, traps.get(FieldTypes.traps.SPIKES)+1);
                break;
            case "PICS TOXIK":
                traps.replace(FieldTypes.traps.POISON_SPIKES, traps.get(FieldTypes.traps.POISON_SPIKES)+1);
                break;
            case "PIEGE DE ROC":
                traps.replace(FieldTypes.traps.STEALTH_ROCKS, traps.get(FieldTypes.traps.STEALTH_ROCKS)+1);
                break;
        }
    }

    public void applyTrapsEffect (Pokemon target, boolean left) {
        HashMap<FieldTypes.traps, Integer> traps = (left ? leftsideTraps : rightsideTraps);
        for (FieldTypes.traps trap : traps.keySet()) {
            int amount = traps.get(trap);
            if (amount > 0){
                switch (trap) {
                    case SPIKES :
                        if (!target.hasType(PokeTypes.type.FLYING)) {
                            switch (amount) {
                                case 1 :
                                    target.getDamaged((int) Math.round(target.getMaxHP()/12.5));
                                    break;
                                case 2 :
                                    target.getDamaged((int) Math.round(target.getMaxHP()/16.66));
                                    break;
                                case 3 :
                                    target.getDamaged(Math.round(target.getMaxHP()/25));
                                    break;
                            }
                        }
                        break;
                    case POISON_SPIKES:
                        if (!target.hasType(PokeTypes.type.FLYING) && !target.hasType(PokeTypes.type.POISON) && !target.hasType(PokeTypes.type.STEEL)) {
                            switch (amount) {
                                case 1 :
                                    target.setStatus(PokeStatus.Status.POISONED);
                                    break;
                                case 2 :
                                    target.setStatus(PokeStatus.Status.BADLY_POISONED);
                                    break;
                            }
                        }
                        break;
                    case STEALTH_ROCKS:
                        double damageMultiplier = target.getTypeMultiplier(PokeTypes.type.ROCK);
                        if (damageMultiplier == 0.25d) {
                            target.getDamaged((int) Math.round(target.getMaxHP()/3.125));
                        }
                        if (damageMultiplier == 0.5d) {
                            target.getDamaged((int) Math.round(target.getMaxHP()/6.25));
                        }
                        if (damageMultiplier == 1d) {
                            target.getDamaged((int) Math.round(target.getMaxHP()/12.5));
                        }
                        if (damageMultiplier == 2d) {
                            target.getDamaged(Math.round(target.getMaxHP()/25));
                        }
                        if (damageMultiplier == 4d) {
                            target.getDamaged(Math.round(target.getMaxHP()/50));
                        }
                        break;
                }
            }
        }
    }
}
