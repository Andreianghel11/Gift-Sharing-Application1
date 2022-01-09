package database;

import java.util.ArrayList;
import java.util.List;

/**
 * Class defines objects that
 * store data about annual changes.
 */
public final class AnnualChange {
    private int newSantaBudget;

    private List<Gift> newGifts;

    private List<Child> newChildren;

    private List<ChildUpdate> childrenUpdates;

    public AnnualChange() {
        this.newSantaBudget = 0;
        this.newGifts = new ArrayList<>();
        this.newChildren = new ArrayList<>();
        this.childrenUpdates = new ArrayList<>();
    }

    public int getNewSantaBudget() {
        return newSantaBudget;
    }

    public void setNewSantaBudget(final int newSantaBudget) {
        this.newSantaBudget = newSantaBudget;
    }

    public List<Gift> getNewGifts() {
        return newGifts;
    }

    public void setNewGifts(final List<Gift> newGifts) {
        this.newGifts = newGifts;
    }

    public List<Child> getNewChildren() {
        return newChildren;
    }

    public void setNewChildren(final List<Child> newChildren) {
        this.newChildren = newChildren;
    }

    public List<ChildUpdate> getChildrenUpdates() {
        return childrenUpdates;
    }

    public void setChildrenUpdates(final List<ChildUpdate> childrenUpdates) {
        this.childrenUpdates = childrenUpdates;
    }
}
