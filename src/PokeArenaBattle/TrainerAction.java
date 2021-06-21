package Model;

import Model.Utils.Pair;

public class TrainerAction extends Pair<Trainer, Action> {
    public TrainerAction(Trainer left, Action right) {
        super(left, right);
    }

    public Trainer getTrainer(){
        return super.getLeft();
    }

    public Action getAction(){
        return super.getRight();
    }
}
