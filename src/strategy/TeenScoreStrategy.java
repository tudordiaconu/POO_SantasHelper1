package strategy;

import michelaneous.Child;

public class TeenScoreStrategy implements ScoreStrategy {
    private final Child child;

    public TeenScoreStrategy(final Child child) {
        this.child = child;
    }

    /** sets the score of a teen */
    @Override
    public void getScore() {
        double score = 0;
        int sum = 0;

        for (int j = 0; j < child.getNiceScoreHistory().size(); j++) {
            sum += j + 1;
            score += child.getNiceScoreHistory().get(j) * (j + 1);
        }

        child.setAverageScore(score / sum);
    }
}
