package fileInputOutput;

import database.AnnualChange;
import database.Child;
import database.Gift;

import java.util.List;

public final class Input {
    private int numberOfYears;

    private int santaBudget;

    private List<Child> childList;

    private List<Gift> giftList;

    private List<AnnualChange> changes;

    public Input(int numberOfYears, int santaBudget, List<Child> childList, List<Gift> giftList, List<AnnualChange> changes) {
        this.numberOfYears = numberOfYears;
        this.santaBudget = santaBudget;
        this.childList = childList;
        this.giftList = giftList;
        this.changes = changes;
    }

    public int getNumberOfYears() {
        return numberOfYears;
    }

    public int getSantaBudget() {
        return santaBudget;
    }

    public List<Child> getChildList() {
        return childList;
    }

    public List<Gift> getGiftList() {
        return giftList;
    }

    public List<AnnualChange> getChanges() {
        return changes;
    }

    @Override
    public String toString() {
        return "Input{" +
                "numberOfYears=" + numberOfYears +
                ", santaBudget=" + santaBudget +
                ", children=" + childList +
                ", gifts=" + giftList +
                ", changes=" + changes +
                '}';
    }
}
