package database;

import calculatingNicenessScore.NiceScoreCalculator;
import calculatingNicenessScore.NiceScoreFactory;
import fileInputOutput.Input;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Database {
    private int santaBudget;

    private List<Child> childList;

    private List<Gift> giftList;

    public Database(Input input) {
        this.santaBudget = input.getSantaBudget();
        this.childList = input.getChildList();
        this.giftList = input.getGiftList();
    }

    public int getSantaBudget() {
        return santaBudget;
    }

    public void setSantaBudget(int santaBudget) {
        this.santaBudget = santaBudget;
    }

    public List<Child> getChildList() {
        return childList;
    }

    public void setChildList(List<Child> childList) {
        this.childList = childList;
    }

    public List<Gift> getGiftList() {
        return giftList;
    }

    public void setGiftList(List<Gift> giftList) {
        this.giftList = giftList;
    }

    @Override
    public String toString() {
        return "Database{" +
                "santaBudget=" + santaBudget +
                ", children=" + childList +
                ", gifts=" + giftList +
                '}';
    }

    public void calculateChildScores() {
        for (Child c : childList) {
            NiceScoreCalculator niceScoreCalculator = NiceScoreFactory
                    .createNiceScoreCalculator(c.getAge());
            if (niceScoreCalculator != null)
                c.setNiceScore(niceScoreCalculator.calculateNiceScore(c));
        }
    }

    //Asta si cea de sus ar putea fi cuplate
    public double calculateAverageNiceScoreSum() {
        double sum = 0;
        for (Child c : childList) {
            sum += c.getNiceScore();
        }
        return sum;
    }

    public void calculateBudget() {
        double budgetUnit = santaBudget / calculateAverageNiceScoreSum();
        for (Child c : childList) {
            c.setBudgetAllocated(c.getNiceScore() * budgetUnit);
        }
    }

    public void sortGifts() {
        giftList.sort(Comparator.comparingDouble(Gift::getPrice));
    }

    public int findGiftByPreference(String preference) {
        int position = 0;
        for (Gift gift : giftList) {
            if (preference.equalsIgnoreCase(gift.getCategory())) {
                return position;
            }
            position++;
        }
        return -1;
    }

    public void distributeGifts() {
        sortGifts();
        for (Child child : childList) {
            child.getGiftsReceived().clear();
            double remainingBudget = child.getBudgetAllocated();
            for (String preference : child.getGiftPreferences()) {
                int position = findGiftByPreference(preference);
                if (position >= 0) {
                    Gift gift = giftList.get(position);
                    if (remainingBudget - gift.getPrice() >= 0) {
                        remainingBudget -= gift.getPrice();
                        child.getGiftsReceived().add(gift);
                    }
                }
            }
        }
    }

    public void implementAnnualChange(AnnualChange annualChange) {
        increaseAge();
        removeYoungAdults();
        addNewChildren(annualChange.getNewChildren());
        addNewGifts(annualChange.getNewGifts());
        updateChildren(annualChange.getChildrenUpdates());
        santaBudget = annualChange.getNewSantaBudget();
    }

    public void increaseAge() {
        for (Child child : childList) {
            child.setAge(child.getAge() + 1);
        }
    }

    public void removeYoungAdults() {
        childList.removeIf(child -> child.getAge() > 18);
    }

    public void addNewChildren(List<Child> newChildren) {
        for (Child newChild : newChildren) {
            if (newChild.getAge() <= 18) {
                childList.add(newChild);
            }
        }
    }

    public void addNewGifts(List<Gift> newGifts) {
        giftList.addAll(newGifts);
    }

    public boolean isChildInList(int id) {
        for (Child child : childList) {
            if (id == child.getId())
                return true;
        }
        return false;
    }

    public Child getChildById(int id) {
        for (Child child : childList) {
            if (child.getId() == id)
                return child;
        }
        return null;
    }

    public void updateChildren(List<ChildUpdate> childUpdates) {
        for (ChildUpdate childUpdate : childUpdates) {
            if (isChildInList(childUpdate.getId())) {
                Child child = getChildById(childUpdate.getId());

                if (childUpdate.getNiceScore() >= 0) {
                    child.getNiceScoresList().add(childUpdate.getNiceScore());
                }

                int position = 0;
                for (String preference : childUpdate.getGiftsPreferences()) {
                    child.getGiftPreferences().add(position, preference);
                    position++;
                }
            }
        }
    }
}
