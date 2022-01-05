package fileInputOutput;

import database.AnnualChange;
import database.Child;
import database.Gift;

import java.util.List;

public final class Input {
    private final int numberOfYears;

    private final int santaBudget;

    private final List<Child> children;

    private final List<Gift> gifts;

    private final List<AnnualChange> changes;

    public Input(int numberOfYears, int santaBudget, List<Child> children, List<Gift> gifts, List<AnnualChange> changes) {
        this.numberOfYears = numberOfYears;
        this.santaBudget = santaBudget;
        this.children = children;
        this.gifts = gifts;
        this.changes = changes;
    }

    public int getNumberOfYears() {
        return numberOfYears;
    }

    public int getSantaBudget() {
        return santaBudget;
    }

    public List<Child> getChildren() {
        return children;
    }

    public List<Gift> getGifts() {
        return gifts;
    }

    public List<AnnualChange> getChanges() {
        return changes;
    }

    @Override
    public String toString() {
        return "Input{" +
                "numberOfYears=" + numberOfYears +
                ", santaBudget=" + santaBudget +
                ", children=" + children +
                ", gifts=" + gifts +
                ", changes=" + changes +
                '}';
    }
}
