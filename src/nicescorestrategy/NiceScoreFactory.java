package nicescorestrategy;

import common.Constants;

/**
 * Class used to create a specific
 * NiceScoreCalculator object based on the child's age.
 */
public final class NiceScoreFactory {
    private NiceScoreFactory() { }

    /**
     * Factory method to create a specific object.
     */
    public static NiceScoreCalculator createNiceScoreCalculator(final int age) {
        if (age < Constants.BABY_MAX_AGE) {
            return new BabyNiceScoreCalculator();
        } else if (age < Constants.KID_MAX_AGE) {
            return new KidNiceScoreCalculator();
        } else if (age <= Constants.TEEN_MAX_AGE) {
            return new TeenNiceScoreCalculator();
        }
        return null;
    }
}
