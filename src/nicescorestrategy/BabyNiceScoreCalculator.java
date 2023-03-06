package nicescorestrategy;

import common.Constants;
import database.Child;

/**
 * Defines objects used to apply the specific
 * algorithm for calculating the nice score.
 */
public class BabyNiceScoreCalculator implements NiceScoreCalculator {

    /**
     * Calculates the nice score for a baby.
     */
    @Override
    public double calculateNiceScore(final Child child) {
        return Constants.MAX_NICE_SCORE;
    }
}
