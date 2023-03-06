package nicescorestrategy;

import database.Child;

/**
 * Defines objects used to apply the specific
 * algorithm for calculating the nice score.
 */
public class TeenNiceScoreCalculator implements NiceScoreCalculator {

    /**
     * Calculates the nice score for a teen.
     */
    @Override
    public double calculateNiceScore(final Child child) {
        double sum = 0;
        int position = 0;

        for (Double d : child.getNiceScoresList()) {
            sum += d * (position + 1);
            position += 1;
        }

        int divisor = position * (position + 1) / 2;

        return sum / divisor;
    }
}
