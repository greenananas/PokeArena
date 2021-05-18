package Model;
import Utils.terminalColors;

import java.util.Scanner;

/**
 * Class décrivant un combat Pokémon. Non définitif (à remplacer par une interface sûrement).
 */
public class battle {
    private trainer trainer1;
    private trainer trainer2;

    public battle(trainer left, trainer right) {
        this.trainer1 = left;
        this.trainer2 = right;
    }

    public void startBattle() {
        System.out.println(trainer1.getDisplayName() + " vs " + trainer2.getDisplayName() + "\n");
        pokemon t1 = trainer1.getPokemon(0);
        pokemon t2 = trainer2.getPokemon(0);
        pokemon firstAttacker;
        pokemon secondAttacker;
        trainer winner = null;
        Scanner s = new Scanner(System.in);
        int attackN1;
        int attackN2;
        while (winner == null) {
            this.showHP(t1);
            this.showHP(t2);
            if (t1.isFaster(t2)) {
                firstAttacker = t1;
                secondAttacker = t2;
            } else {
                firstAttacker = t2;
                secondAttacker = t1;
            }
            System.out.println(firstAttacker.getName() + " attaque en premier.\n");
            System.out.println("Attaque 1: " + firstAttacker.getAttack1().getName());
            System.out.println("Attaque 2: " + firstAttacker.getAttack2().getName());
            System.out.println("Attaque 3: " + firstAttacker.getAttack3().getName());
            System.out.println("Attaque 4: " + firstAttacker.getAttack4().getName());
            System.out.println("5: Switch");
            attackN1 = s.nextInt();

            if (attackN1 == 5) {
                if (t1 == firstAttacker) {
                    t1 = trainer1.changePokemon();
                    firstAttacker = t1;
                } else {
                    t2 = trainer2.changePokemon();
                    firstAttacker = t2;
                }
            }

            System.out.println("\n" + secondAttacker.getName() + " attaque en second.");
            System.out.println("Attaque 1: " + secondAttacker.getAttack1().getName());
            System.out.println("Attaque 2: " + secondAttacker.getAttack2().getName());
            System.out.println("Attaque 3: " + secondAttacker.getAttack3().getName());
            System.out.println("Attaque 4: " + secondAttacker.getAttack4().getName());
            System.out.println("5: Switch");
            attackN2 = s.nextInt();

            if (attackN2 == 5) {
                if (t1 == secondAttacker) {
                    t1 = trainer1.changePokemon();
                    secondAttacker = t1;
                } else {
                    t2 = trainer2.changePokemon();
                    secondAttacker = t2;
                }
            }

            switch (attackN1) {
                case 1:
                    firstAttacker.attack(secondAttacker, firstAttacker.getAttack1());
                    break;
                case 2:
                    firstAttacker.attack(secondAttacker, firstAttacker.getAttack2());
                    break;
                case 3:
                    firstAttacker.attack(secondAttacker, firstAttacker.getAttack3());
                    break;
                case 4:
                    firstAttacker.attack(secondAttacker, firstAttacker.getAttack4());
                    break;
                case 5:
                    break;

                default:
                    System.out.println("Tu troll mec.");
                    break;
            }
            if (!secondAttacker.isKO()) {
                switch (attackN2) {
                    case 1:
                        secondAttacker.attack(firstAttacker, secondAttacker.getAttack1());
                        break;
                    case 2:
                        secondAttacker.attack(firstAttacker, secondAttacker.getAttack2());
                        break;
                    case 3:
                        secondAttacker.attack(firstAttacker, secondAttacker.getAttack3());
                        break;
                    case 4:
                        secondAttacker.attack(firstAttacker, secondAttacker.getAttack4());
                        break;
                    case 5:
                        break;

                    default:
                        System.out.println("Tu troll mec.");
                        break;
                }
            }
            if (t1.isKO()) {
                if (trainer1.hasPokemonLeft()) {
                    t1 = trainer1.changePokemon();
                } else {
                    winner = trainer2;
                }
            } else if (t2.isKO()) {
                if (trainer2.hasPokemonLeft()) {
                    t2 = trainer2.changePokemon();
                } else {
                    winner = trainer1;
                }
            }
        }
        endBattle(winner);
    }

    public void endBattle(trainer w) {
        System.out.println(w.getDisplayName() + " remporte le combat.");
    }

    public void showHP(pokemon p) {
        String hpBarString = p.getName() + " : |";
        int hpBar = (int) Math.ceil(((double) p.getHP() / (double) p.getFullHP()) * 10);
        String barColor;
        if (hpBar > 5) {
            barColor = terminalColors.ANSI_GREEN;
        } else if (hpBar > 3) {
            barColor = terminalColors.ANSI_YELLOW;
        } else {
            barColor = terminalColors.ANSI_RED;
        }
        for (int i = 0; i < 10; i++) {
            if (hpBar > i) {
                hpBarString += barColor + "█";
            } else {
                hpBarString += terminalColors.ANSI_WHITE + "-";
            }
        }
        hpBarString += terminalColors.ANSI_WHITE + "| " + p.getHP() + "HP";
        System.out.println(hpBarString);
    }
}
