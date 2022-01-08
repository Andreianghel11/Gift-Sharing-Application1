package calculatingNiceScore;

import database.Child;

public class TeenNiceScoreCalculator implements NiceScoreCalculator{
    @Override
    public double calculateNiceScore(Child child) {
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
