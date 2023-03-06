package database;

import common.Constants;
import fileinputoutput.Input;

import java.util.Comparator;
import java.util.List;

/**
 * Singleton class that defines an object
 * containing most of the important data.
 */
public final class Database {
    private int santaBudget;

    private List<Child> childList;

    private List<Gift> giftList;

    private static Database instance = null;

    private Database() { }

    /**
     * Method specific to the Singleton pattern.
     */
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    /**
     * Loads the database based on the given input.
     */
    public void loadDatabase(final Input input) {
        this.santaBudget = input.getSantaBudget();
        this.childList = input.getChildList();
        this.giftList = input.getGiftList();
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

    /**
     * Operations that must be implemented every year.
     */
    public void implementAnnualOperations() {
        removeYoungAdults();
        calculateChildScores();
        calculateBudget();
        distributeGifts();
        sortChildren();
    }

    /**
     * Removes young adults that don't receive
     * gifts and don't appear in the output.
     */
    public void removeYoungAdults() {
        childList.removeIf(child -> child.getAge() > Constants.TEEN_MAX_AGE);
    }

    /**
     * Calculates and updates the
     * nice score for every child.
     */
    public void calculateChildScores() {
        for (Child child : childList) {
            child.calculateChildScore();
        }
    }

    /**
     * Calculates the average
     * nice score of all children.
     */
    public double calculateAverageNiceScoreSum() {
        double sum = 0;
        for (Child child : childList) {
            sum += child.getNiceScore();
        }
        return sum;
    }

    /**
     * Calculates and sets the budget
     * allocated for every child.
     */
    public void calculateBudget() {
        double budgetUnit = santaBudget / calculateAverageNiceScoreSum();
        for (Child c : childList) {
            c.setBudgetAllocated(c.getNiceScore() * budgetUnit);
        }
    }

    /**
     * Method to sort the gift list by price.
     */
    public void sortGifts() {
        giftList.sort(Comparator.comparingDouble(Gift::getPrice));
    }

    /**
     * Method to sort the children list by id.
     */
    public void sortChildren() {
        childList.sort(Comparator.comparingInt(Child::getId));
    }

    /**
     * Looks for a child's preference amongst the
     * gift list. If found, the method returns the
     * position of the gift, otherwise returns value -1.
     */
    public int findGiftByPreference(final String preference) {
        int position = 0;
        for (Gift gift : giftList) {
            if (preference.equals(gift.getCategory())) {
                return position;
            }
            position++;
        }
        return -1;
    }

    /**
     * Determines the received gifts for all children
     * based on their nice score, preferences and
     * the list of available gifts.
     */
    public void distributeGifts() {
        /*
          First perform a gift sort by price
          to ensure that the first gift found
          is the one with the lowest price.
         */
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

    /**
     * Implements the annual changes received from
     * the input then applies the usual operations.
     */
    public void implementAnnualChange(final AnnualChange annualChange) {
        increaseAge();
        removeYoungAdults();
        addNewChildren(annualChange.getNewChildren());
        addNewGifts(annualChange.getNewGifts());
        updateChildren(annualChange.getChildrenUpdates());
        santaBudget = annualChange.getNewSantaBudget();

        implementAnnualOperations();
    }

    /**
     * Increases the age of every child.
     */
    public void increaseAge() {
        for (Child child : childList) {
            child.setAge(child.getAge() + 1);
        }
    }

    /**
     * Adds a new child to the list only if
     * he isn't a young adult.
     */
    public void addNewChildren(final List<Child> newChildren) {
        for (Child newChild : newChildren) {
            if (newChild.getAge() <= Constants.TEEN_MAX_AGE) {
                childList.add(newChild);
            }
        }
    }

    /**
     * Adds the new gifts to the database.
     */
    public void addNewGifts(final List<Gift> newGifts) {
        giftList.addAll(newGifts);
    }

    /**
     * Determines if a child is already in
     * the database (based on his/her id).
     */
    public boolean isChildInList(final int id) {
        for (Child child : childList) {
            if (id == child.getId()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Looks for a child in the database
     * by id and returns the object if
     * found. Otherwise, returns null.
     */
    public Child getChildById(final int id) {
        for (Child child : childList) {
            if (child.getId() == id) {
                return child;
            }
        }
        return null;
    }

    /**
     * Updates the data of a child only
     * if it is found in the database.
     */
    public void updateChildren(final List<ChildUpdate> childUpdates) {
        for (ChildUpdate childUpdate : childUpdates) {
            if (isChildInList(childUpdate.getId())) {
                Child child = getChildById(childUpdate.getId());

                /*
                  Add the new nice score only if it isn't equal
                  to -1 - the score corresponding to a null input.
                 */
                if (childUpdate.getNiceScore() >= 0) {
                    child.getNiceScoresList().add(childUpdate.getNiceScore());
                }

                /*
                  Add the new preferences at the top of the list and remove
                  previous appearances of the String in the preference list.
                 */
                for (int i = childUpdate.getGiftsPreferences().size() - 1; i >= 0; i--) {
                    String preference = childUpdate.getGiftsPreferences().get(i);
                    if (child.getGiftPreferences().contains(preference)) {
                        child.getGiftPreferences().remove(preference);
                        child.getGiftPreferences().add(0, preference);
                    } else {
                        child.getGiftPreferences().add(0, preference);
                    }
                }
            }
        }
    }
}
