package database;

import java.util.ArrayList;
import java.util.List;

public class ChildUpdate {
    private int id;

    private double niceScore;

    private List<String> giftsPreferences;

    public ChildUpdate(int id, double niceScore, ArrayList<String> giftsPreferences) {
        this.id = id;
        this.niceScore = niceScore;
        this.giftsPreferences = giftsPreferences;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "ChildUpdate{" +
                "id=" + id +
                ", niceScore=" + niceScore +
                ", giftsPreferences=" + giftsPreferences +
                '}';
    }
}
