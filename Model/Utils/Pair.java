package Model.Utils;

public class Pair<L,R> {

    private final L left;
    private R right;

    public Pair(L left, R right) {
        assert left != null;

        this.left = left;
        this.right = right;
    }

    public L getTrainer() { return left; }
    public R getAction() { return right; }

    public void pairWith(R newRight) {
        this.right = newRight;
    }

    @Override
    public int hashCode() { return left.hashCode() ^ right.hashCode(); }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair)) return false;
        Pair pairo = (Pair) o;
        return this.left.equals(pairo.getTrainer()) &&
                this.right.equals(pairo.getAction());
    }

}