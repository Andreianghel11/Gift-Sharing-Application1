package database;

import java.util.ArrayList;
import java.util.List;

/**
 * Class defines objects that store
 * data about annual child updates.
 */
public final class ChildUpdate {
    private int id;

    private double niceScore;

    private List<String> giftsPreferences;

    public ChildUpdate(final int id, final double niceScore,
                       final ArrayList<String> giftsPreferences) {
        this.id = id;
        this.niceScore = niceScore;
        this.giftsPreferences = giftsPreferences;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public double getNiceScore() {
        return niceScore;
    }

    public void setNiceScore(final double niceScore) {
        this.niceScore = niceScore;
    }

    public List<String> getGiftsPreferences() {
        return giftsPreferences;
    }

    public void setGiftsPreferences(final List<String> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }
}
