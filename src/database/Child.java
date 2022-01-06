package database;

import java.util.ArrayList;
import java.util.List;

public class Child {
    private int id;

    private String lastName;

    private String firstName;

    private int age;

    private String city;

    private double niceScore;

    private List<String> giftPreferences;

    private List<Double> niceScoresList;

    public double budgetAllocated;

    private List<Gift> giftsReceived;

    public Child(int id, String lastName, String firstName, int age, String city,
                 double niceScore, ArrayList<String> giftPreferences) {
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

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getNiceScore() {
        return niceScore;
    }

    public void setNiceScore(double niceScore) {
        this.niceScore = niceScore;
    }

    public List<String> getGiftPreferences() {
        return giftPreferences;
    }

    public void setGiftPreferences(List<String> giftPreferences) {
        this.giftPreferences = giftPreferences;
    }

    public List<Double> getNiceScoresList() {
        return niceScoresList;
    }

    public void setNiceScoresList(List<Double> niceScoresList) {
        this.niceScoresList = niceScoresList;
    }

    public double getBudgetAllocated() {
        return budgetAllocated;
    }

    public void setBudgetAllocated(double budgetAllocated) {
        this.budgetAllocated = budgetAllocated;
    }

    public List<Gift> getGiftsReceived() {
        return giftsReceived;
    }

    public void setGiftsReceived(List<Gift> giftsReceived) {
        this.giftsReceived = giftsReceived;
    }

    @Override
    public String toString() {
        return "Child{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                ", niceScore=" + niceScore +
                ", giftPreferences=" + giftPreferences +
                ", niceScoresList=" + niceScoresList +
                ", budgetAllocated=" + budgetAllocated +
                '}';
    }
}
