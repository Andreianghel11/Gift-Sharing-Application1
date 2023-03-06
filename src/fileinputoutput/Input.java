package fileinputoutput;

import database.AnnualChange;
import database.Child;
import database.Gift;

import java.util.List;

/**
 * Class defines objects used
 * to store data from the input.
 */
public final class Input {
    private int numberOfYears;

    private int santaBudget;

    private List<Child> childList;

    private List<Gift> giftList;

    private List<AnnualChange> changes;

    public Input(final int numberOfYears, final int santaBudget, final List<Child> childList,
                 final List<Gift> giftList, final List<AnnualChange> changes) {
        this.numberOfYears = numberOfYears;
        this.santaBudget = santaBudget;
        this.childList = childList;
        this.giftList = giftList;
        this.changes = changes;
    }

    public int getNumberOfYears() {
        return numberOfYears;
    }

    public void setNumberOfYears(final int numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public int getSantaBudget() {
        return santaBudget;
    }

    public void setSantaBudget(final int santaBudget) {
        this.santaBudget = santaBudget;
    }

    public List<Child> getChildList() {
        return childList;
    }

    public void setChildList(final List<Child> childList) {
        this.childList = childList;
    }

    public List<Gift> getGiftList() {
        return giftList;
    }

    public void setGiftList(final List<Gift> giftList) {
        this.giftList = giftList;
    }

    public List<AnnualChange> getChanges() {
        return changes;
    }

    public void setChanges(final List<AnnualChange> changes) {
        this.changes = changes;
    }
}
