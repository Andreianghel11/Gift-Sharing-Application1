package nicescorestrategy;

import database.Child;

/**
 * Defines a generic object to calculate
 * the nice score of a child.
 */
public interface NiceScoreCalculator {
    double calculateNiceScore(Child child);
}
