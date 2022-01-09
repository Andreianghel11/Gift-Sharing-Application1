package nicescorestrategy;

import database.Child;

/**
 * Defines a generic object to calculate
 * the nice score of a child.
 */
public interface NiceScoreCalculator {
    /**
     * Method used to calculate the
     * nice score of a child.
     */
    double calculateNiceScore(Child child);
}
