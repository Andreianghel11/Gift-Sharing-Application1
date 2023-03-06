package nicescorestrategy;

import database.Child;

/**
 * Defines objects used to apply the specific
 * algorithm for calculating the nice score.
 */
public class KidNiceScoreCalculator implements NiceScoreCalculator {

    /**
     * Calculates the nice score for a kid.
     */
    @Override
    public double calculateNiceScore(final Child child) {
        double sum = 0;
        for (Double d : child.getNiceScoresList()) {
            sum += d;
        }

        return sum / child.getNiceScoresList().size();
    }
}
