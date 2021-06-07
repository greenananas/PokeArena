package Model;

/**
 * Class décrivant un combat Pokémon. Non définitif (à remplacer par une interface sûrement).
 */
public class Battle {
    private TrainerAction trainer1;
    private TrainerAction trainer2;

    private static BattleGround bg;  //Terrain de combat

    public Battle(Trainer left, Trainer right, BattleGround bg) {
        trainer1 = new TrainerAction(left, null);
        trainer2 = new TrainerAction(right, null);
        Battle.bg = bg;
    }

    public void takeTurns (Action T1action, Action T2action) {
        trainer1.pairWith(T1action);
        trainer2.pairWith(T2action);
        TrainerAction firstToAct = calculatePriority();
        TrainerAction secondToAct = (firstToAct == trainer1 ? trainer2 : trainer1);
        apply(firstToAct);
        if (!secondToAct.getTrainer().getLeadingPkmn().isKO()) {
            apply(secondToAct);
            if (firstToAct.getTrainer().getLeadingPkmn().isKO()) {
                if (!firstToAct.getTrainer().hasPokemonLeft()) {
                    endBattle(secondToAct.getTrainer());
                } else {
                    firstToAct.getTrainer().changePokemon();
                }
            }
        }
        else {
            if (!secondToAct.getTrainer().hasPokemonLeft()) {
                endBattle(firstToAct.getTrainer());
            } else {
                secondToAct.getTrainer().changePokemon();
            }
        }
    }

    public void apply (TrainerAction current) {
        if (current.getAction().getType() == acTypes.ATTACK) {
            Pokemon target = (current == trainer1 ? trainer2.getTrainer().getLeadingPkmn() : trainer1.getTrainer().getLeadingPkmn());
            current.getTrainer().getLeadingPkmn().attack(target, (Move) current.getAction());
        }
        else if (current.getAction().getType() == acTypes.CHANGEPKM) {
            current.getTrainer().changePokemon();
        }
    }

    public TrainerAction calculatePriority() {
        TrainerAction firstToAct;
        if (trainer1.getAction().getPriority() > trainer2.getAction().getPriority()) {
            firstToAct = trainer1;
        }
        else if (trainer1.getAction().getPriority() < trainer2.getAction().getPriority()) {
            firstToAct = trainer2;
        }
        else {
            firstToAct = (trainer1.getTrainer().getLeadingPkmn().isFaster(trainer2.getTrainer().getLeadingPkmn()) ? trainer1 : trainer2);
        }
        return firstToAct;
    }

    /*
    public void battle() {
        /* Affichage initial
        System.out.println(trainer1.getDisplayName() + " vs " + trainer2.getDisplayName() + "\n");
        Sélection des Pokémon main

        Pokemon firstAttacker;
        Pokemon secondAttacker;
        Trainer winner = null;

        int attackN1;
        int attackN2;
        while (winner == null) {
            currentPokemonT1.showHP();
            currentPokemonT2.showHP();

            if (currentPokemonT1.isFaster(currentPokemonT2)) {
                firstAttacker = currentPokemonT1;
                secondAttacker = currentPokemonT2;
            } else {
                firstAttacker = currentPokemonT2;
                secondAttacker = currentPokemonT1;
            }

            //Trainer.getAction
            System.out.println(firstAttacker.getName() + " attaque en premier.\n");
            System.out.println("Attaque 1: " + firstAttacker.getMove1().getName());
            System.out.println("Attaque 2: " + firstAttacker.getMove2().getName());
            System.out.println("Attaque 3: " + firstAttacker.getMove3().getName());
            System.out.println("Attaque 4: " + firstAttacker.getMove4().getName());
            System.out.println("5: Switch");
            attackN1 = s.nextInt();

            if (attackN1 == 5) {
                if (currentPokemonT1 == firstAttacker) {
                    currentPokemonT1 = trainer1.changePokemon();
                    firstAttacker = currentPokemonT1;
                } else {
                    currentPokemonT2 = trainer2.changePokemon();
                    firstAttacker = currentPokemonT2;
                }
            }

            System.out.println("\n" + secondAttacker.getName() + " attaque en second.");
            System.out.println("Attaque 1: " + secondAttacker.getMove1().getName());
            System.out.println("Attaque 2: " + secondAttacker.getMove2().getName());
            System.out.println("Attaque 3: " + secondAttacker.getMove3().getName());
            System.out.println("Attaque 4: " + secondAttacker.getMove4().getName());
            System.out.println("5: Switch");
            attackN2 = s.nextInt();

            if (attackN2 == 5) {
                if (currentPokemonT1 == secondAttacker) {
                    currentPokemonT1 = trainer1.changePokemon();
                    secondAttacker = currentPokemonT1;
                } else {
                    currentPokemonT2 = trainer2.changePokemon();
                    secondAttacker = currentPokemonT2;
                }
            }

            switch (attackN1) {
                case 1:
                    firstAttacker.attack(secondAttacker, firstAttacker.getMove1());
                    break;
                case 2:
                    firstAttacker.attack(secondAttacker, firstAttacker.getMove2());
                    break;
                case 3:
                    firstAttacker.attack(secondAttacker, firstAttacker.getMove3());
                    break;
                case 4:
                    firstAttacker.attack(secondAttacker, firstAttacker.getMove4());
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
                        secondAttacker.attack(firstAttacker, secondAttacker.getMove1());
                        break;
                    case 2:
                        secondAttacker.attack(firstAttacker, secondAttacker.getMove2());
                        break;
                    case 3:
                        secondAttacker.attack(firstAttacker, secondAttacker.getMove3());
                        break;
                    case 4:
                        secondAttacker.attack(firstAttacker, secondAttacker.getMove4());
                        break;
                    case 5:
                        break;

                    default:
                        System.out.println("Tu troll mec.");
                        break;
                }
            }
            if (currentPokemonT1.isKO()) {
                if (trainer1.hasPokemonLeft()) {
                    currentPokemonT1 = trainer1.changePokemon();
                } else {
                    winner = trainer2;
                }
            } else if (currentPokemonT2.isKO()) {
                if (trainer2.hasPokemonLeft()) {
                    currentPokemonT2 = trainer2.changePokemon();
                } else {
                    winner = trainer1;
                }
            }
        }
        endBattle(winner);
    }
    */

    public void endBattle(Trainer w) {
        System.out.println(w.getDisplayName() + " remporte le combat.");
    }
}
