package calculatingNiceScore;

public class NiceScoreFactory {
    public static NiceScoreCalculator createNiceScoreCalculator(int age) {
        if (age < 5) {
            return new BabyNiceScoreCalculator();
        } else if (age < 12) {
            return new KidNiceScoreCalculator();
        } else if (age <= 18) {
            return new TeenNiceScoreCalculator();
        }
        return null;
    }
}
