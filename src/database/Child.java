package database;

import enums.Category;

import java.util.ArrayList;
import java.util.List;

public class Child {
    private int id;

    private String lastName;

    private String firstName;

    private int age;

    private String city;

    private double niceScore;

    private List<String> giftsPreferences;

    public Child(int id, String lastName, String firstName, int age, String city,
                 double niceScore, ArrayList<String> giftsPreferences) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.city = city;
        this.niceScore = niceScore;
        this.giftsPreferences = giftsPreferences;
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

    public List<String> getGiftsPreferences() {
        return giftsPreferences;
    }

    public void setGiftsPreferences(List<String> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
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
                ", giftsPreferences=" + giftsPreferences +
                '}';
    }
}
