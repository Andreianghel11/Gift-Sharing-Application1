package calculatingNicenessScore;

import database.Child;

public class KidNiceScoreCalculator implements NiceScoreCalculator{
    @Override
    public double calculateNiceScore(Child child) {
        double sum = 0;
        for (Double d : child.getNiceScoresList()) {
            sum += d;
        }

        return sum / child.getNiceScoresList().size();
    }
}
