package strategy;

import common.Constants;
import michelaneous.Child;

public class BabyScoreStrategy implements ScoreStrategy {
    private final Child child;

    public BabyScoreStrategy(final Child child) {
        this.child = child;
    }

    /** sets the score of a baby */
    @Override
    public void getScore() {
        child.setAverageScore(Constants.BABY_SCORE);
    }
}
