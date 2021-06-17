package Model;

/**
 * Classe décrivant les actions menées pendant un combat Pokémon.
 */
public class Battle {
    /*
     * Objet représentant le premier dresseur et son action associée.
     */
    public TrainerAction trainer1;
    /*
     * Objet représentant le second dresseur et son action associée.
     */
    public TrainerAction trainer2;
    /*
     * Terrain sur lequel a lieu le combat.
     */
    private static BattleGround bg;  //Terrain de combat

    /**
     * Défini un combat entre deux dresseurs.
     *
     * @param left  Premier dresseur;
     * @param right Second dresseur.
     * @param bg    Terrain du combat.
     */
    public Battle(Trainer left, Trainer right, BattleGround bg) {
        trainer1 = new TrainerAction(left, null);
        trainer2 = new TrainerAction(right, null);
        Battle.bg = bg;
    }

    /**
     * Applique les actions effectuées pendant un tour de jeu. Utilisée en ligne de commande séquentiel. Non utilisée
     * dans le programme principal.
     *
     * @param T1action Action choisie par le premier dresseur.
     * @param T2action Action choisie par le second dresseur.
     */
    public void takeTurns(Action T1action, Action T2action) {
        trainer1.pairWith(T1action);
        trainer2.pairWith(T2action);
        TrainerAction firstToAct = calculatePriority();
        TrainerAction secondToAct = (firstToAct == trainer1 ? trainer2 : trainer1);
        apply(firstToAct, secondToAct, true);
        if (!secondToAct.getTrainer().getLeadingPkmn().isKO()) {
            apply(secondToAct, firstToAct, false);
            if (firstToAct.getTrainer().getLeadingPkmn().isKO()) {
                if (!firstToAct.getTrainer().hasPokemonLeft()) {
                    endBattle(secondToAct.getTrainer());
                } else {
                    firstToAct.getTrainer().changePokemon(((ChangePkmn) firstToAct.getAction()).getIndex());
                    boolean target = (firstToAct == trainer1);
                    bg.applyTrapsEffect(firstToAct.getTrainer().getLeadingPkmn(), target);
                }
            }
        } else {
            if (!secondToAct.getTrainer().hasPokemonLeft()) {
                endBattle(firstToAct.getTrainer());
            } else {
                secondToAct.getTrainer().changePokemon(((ChangePkmn) secondToAct.getAction()).getIndex());
                boolean target = (secondToAct == trainer1);
                bg.applyTrapsEffect(secondToAct.getTrainer().getLeadingPkmn(), target);
            }
        }
    }

    /**
     * Applique une action spécifiée selon son type.
     *
     * @param current Dresseur effectuant l'action.
     * @param other   Adversaire du dresseur courant.
     * @param isFirst Est-ce la première action du tour ?
     */
    public void apply(TrainerAction current, TrainerAction other, boolean isFirst) {
        if (current.getAction() instanceof Move) {
            Trainer currentTrainer = current.getTrainer();
            Pokemon user = currentTrainer.getLeadingPkmn();
            Trainer opponentTrainer = other.getTrainer();
            Pokemon target = opponentTrainer.getLeadingPkmn();

            boolean canPlay = user.getStatus().applyStatusEffect(user);
            if (canPlay) {
                Move move = (Move) current.getAction();
                applyPreMoveEffect(move, current, other, true);
                boolean landed = false;
                if (doesHit(user, target, move) || move.getPrecision() == 0) {
                    landed = true;
                    if (move.getPower() != 0)
                        user.attack(target, move);
                    else
                        applyStatusMoveEffect(move, user, target);
                } else {
                    System.out.println("\nL'attaque " + move.getName() + " a échoué.");
                }
                if (landed) applyPostMoveEffect(move, user, target);
            }
        } else if (current.getAction() instanceof ChangePkmn) {
            current.getTrainer().changePokemon(((ChangePkmn) current.getAction()).getIndex());
            boolean target = (current == trainer1);
            bg.applyTrapsEffect(current.getTrainer().getLeadingPkmn(), target);
        }
    }

    /**
     * Permet de calculer quel dresseur effectuera son action en premier.
     *
     * @return Le joueur ayant le priorité.
     */
    public TrainerAction calculatePriority() {
        TrainerAction firstToAct;
        applyPrePriority();

        if (trainer1.getAction().getPriority() > trainer2.getAction().getPriority()) {
            firstToAct = trainer1;
        } else if (trainer1.getAction().getPriority() < trainer2.getAction().getPriority()) {
            firstToAct = trainer2;
        } else {
            firstToAct = (trainer1.getTrainer().getLeadingPkmn().isFaster(trainer2.getTrainer().getLeadingPkmn()) ? trainer1 : trainer2);
        }
        return firstToAct;
    }

    /**
     * Applique des effets d'attaque se passant avant le calcul de priorité.
     */
    public void applyPrePriority() {
        //Poursuite
        if (trainer1.getAction() instanceof Move && ((Move) trainer1.getAction()).getId() == 228) {
            if (trainer2.getAction() instanceof ChangePkmn) {
                trainer1.getAction().setPriority(7);
                ((Move) trainer1.getAction()).setPower(80);
            } else {
                trainer1.getAction().setPriority(0);
                ((Move) trainer1.getAction()).setPower(40);
            }
        }
        if (trainer2.getAction() instanceof Move && ((Move) trainer2.getAction()).getId() == 228) {
            if (trainer1.getAction() instanceof ChangePkmn) {
                trainer2.getAction().setPriority(7);
                ((Move) trainer2.getAction()).setPower(80);
            } else {
                trainer2.getAction().setPriority(0);
                ((Move) trainer2.getAction()).setPower(40);
            }
        }
    }

    /**
     * Applique des effets d'attaque ayant lieu avant le calcul des dommages.
     *
     * @param moveToProcess Attaque sur le point d'être lancée.
     * @param current       Dresseur effectuant l'action.
     * @param other         Adversaire du dresseur courant.
     * @param isFirst       Est-ce la première action du tour ?
     */
    public void applyPreMoveEffect(Move moveToProcess, TrainerAction current, TrainerAction other, boolean isFirst) {
        Trainer currentTrainer = current.getTrainer();
        Pokemon user = currentTrainer.getLeadingPkmn();
        Trainer opponentTrainer = other.getTrainer();
        Pokemon target = opponentTrainer.getLeadingPkmn();
        switch (moveToProcess.getId()) {
            case 369: //Demi-Tour
                //getOwner(user).changePokemon();
                break;
            case 263: //Façade
                if (user.getStatus().equals(PokeStatus.Status.PARALYZED) ||
                        user.getStatus().equals(PokeStatus.Status.BURNT) ||
                        user.getStatus().equals(PokeStatus.Status.POISONED) ||
                        user.getStatus().equals(PokeStatus.Status.BADLY_POISONED))
                    moveToProcess.setPower(140);
                else moveToProcess.setPower(70);
                break;
            case 389: //Coup bas
                if (!(isFirst && other.getAction() instanceof Move && ((Move) other.getAction()).getPower() != 0))
                    moveToProcess.setPrecision(0);
                else
                    moveToProcess.setPrecision(100);
                break;
            case 182: //Abri
                //envie de crever
                break;
            case 137: //Regard Médusant
                if (target.hasType(PokeTypes.type.ELECTRIC))
                    moveToProcess.setPrecision(0);
                else
                    moveToProcess.setPrecision(100);
        }
    }

    /**
     * Applique des effets d'attaque ayant lieu après le calcul des dommages.
     *
     * @param moveToProcess Attaque lancée.
     * @param user          Pokémon effectuant l'attaque.
     * @param target        Pokémon subissant l'attaque.
     */
    public void applyPostMoveEffect(Move moveToProcess, Pokemon user, Pokemon target) {
        switch (moveToProcess.getId()) {
            case 369: //Demi-Tour
                //getOwner(user).changePokemon();
                break;
            case 405: //Bourdon
                if (Math.random() < 0.10) modifyStat(target, "speDefence", -1);
                break;
            case 413: //Rapace
                user.getDamaged(user.getMaxHP() / 3);
                break;
        }
    }

    /**
     * Applique les effets des attaques de statut.
     *
     * @param moveToProcess Attaque lancée.
     * @param user          Pokémon effectuant l'attaque.
     * @param target        Pokémon subissant l'attaque.
     */
    public void applyStatusMoveEffect(Move moveToProcess, Pokemon user, Pokemon target) {
        switch (moveToProcess.getId()) {
            case 14: //Danse Lames
                modifyStat(user, "attack", 2);
                System.out.println("L'attaque de "+user.getName()+" a augmentée !");
                break;
            case 226: //Relais
                //encore un changement de pokémon -> voir avec Louis
            case 137: //Regard Médusant
                target.setStatus(PokeStatus.Status.PARALYZED);
        }
    }

    /**
     * Permet de changer le modificateur de statistique d'un pokémon.
     *
     * @param poke  Pokémon subissant la modification.
     * @param stat  Statistique cible de la modification.
     * @param value Valeur de la modification.
     */
    public void modifyStat(Pokemon poke, String stat, int value) {
        switch (stat) {
            case "attack":
                if (poke.getAttackModifier()<6) poke.setAttackModifier(poke.getAttackModifier() + value);
                else System.out.println("Cela n'aura aucun effet...");
            case "defence":
                if (poke.getDefenceModifier()<6) poke.setDefenceModifier(poke.getDefenceModifier() + value);
                else System.out.println("Cela n'aura aucun effet...");
            case "speAttack":
                if (poke.getSpeAttackModifier()<6) poke.setSpeAttackModifier(poke.getSpeAttackModifier() + value);
                else System.out.println("Cela n'aura aucun effet...");
            case "speDefence":
                if (poke.getDefenceModifier()<6) poke.setDefenceModifier(poke.getDefenceModifier() + value);
                else System.out.println("Cela n'aura aucun effet...");
            case "speed":
                if (poke.getSpeedModifier()<6) poke.setSpeedModifier(poke.getSpeedModifier() + value);
                else System.out.println("Cela n'aura aucun effet...");
        }
    }

    /**
     * L'attaque du Pokémon va-t-elle atteindre sa cible ?
     *
     * @param target Cible de l'attaque.
     * @param at     Attaque lancée.
     * @return Si l'attaque lancée touche l'ennemi.
     */
    public boolean doesHit(Pokemon user, Pokemon target, Move at) { //Prendre en compte l'esquive
        int precision = (int) Math.floor(Math.random() * 100);
        return (precision <= at.getPrecision());
    }

    /**
     * Affiche le vainqueur du combat.
     *
     * @param w Dresseur vainqueur du combat.
     */
    public void endBattle(Trainer w) {
        System.out.println(w.getDisplayName() + " remporte le combat.");
    }
}
