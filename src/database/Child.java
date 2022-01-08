package database;

import nicescorestrategy.NiceScoreCalculator;
import nicescorestrategy.NiceScoreFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Class defines objects that
 * store data about children.
 */
public final class Child {
    private int id;

    private String lastName;

    private String firstName;

    private int age;

    private String city;

    private double niceScore;

    private List<String> giftPreferences;

    private List<Double> niceScoresList;

    private double budgetAllocated;

    private List<Gift> giftsReceived;

    public Child(final int id, final String lastName, final String firstName, final int age,
                 final String city, final double niceScore,
                 final ArrayList<String> giftPreferences) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.city = city;
        this.niceScore = niceScore;
        this.giftPreferences = giftPreferences;
        niceScoresList = new ArrayList<>();
        niceScoresList.add(this.niceScore);
        budgetAllocated = 0;
        giftsReceived = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public double getNiceScore() {
        return niceScore;
    }

    public void setNiceScore(final double niceScore) {
        this.niceScore = niceScore;
    }

    public List<String> getGiftPreferences() {
        return giftPreferences;
    }

    public void setGiftPreferences(final List<String> giftPreferences) {
        this.giftPreferences = giftPreferences;
    }

    public List<Double> getNiceScoresList() {
        return niceScoresList;
    }

    public void setNiceScoresList(final List<Double> niceScoresList) {
        this.niceScoresList = niceScoresList;
    }

    public double getBudgetAllocated() {
        return budgetAllocated;
    }

    public void setBudgetAllocated(final double budgetAllocated) {
        this.budgetAllocated = budgetAllocated;
    }

    public List<Gift> getGiftsReceived() {
        return giftsReceived;
    }

    public void setGiftsReceived(final List<Gift> giftsReceived) {
        this.giftsReceived = giftsReceived;
    }

    /**
     * Calculates a child's nice score using the
     * strategy and factory design patterns to determine
     * the right algorithm to use.
     */
    public void calculateChildScore() {
        NiceScoreCalculator niceScoreCalculator = NiceScoreFactory
                .createNiceScoreCalculator(age);
        if (niceScoreCalculator != null) {
            niceScore = niceScoreCalculator.calculateNiceScore(this);
        }
    }
}
