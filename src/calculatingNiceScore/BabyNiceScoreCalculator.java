package calculatingNiceScore;

import database.Child;

public class BabyNiceScoreCalculator implements NiceScoreCalculator{
    @Override
    public double calculateNiceScore(Child child) {
        return 10;
    }
}
