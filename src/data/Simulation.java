package data;

import common.Constants;
import michelaneous.AnnualChange;
import michelaneous.Child;
import michelaneous.ChildWriter;
import michelaneous.ChildWriterList;
import michelaneous.Gift;
import strategy.ScoreStrategy;
import strategy.ScoreStrategyFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class Simulation {
    private Simulation() {
        // constructor for checkstyle
    }

    /** method that gives gifts to children from database */
    public static void givingGifts(final Database database, final WriteDatabase writeDatabase) {
        ChildWriterList auxiliarList = new ChildWriterList();
        List<Gift> sortedGifts = database.getGifts().stream()
                .sorted(Comparator.comparingDouble(Gift::getPrice)).toList();

        database.setAgeCategories();
        for (Child child : database.getChildren()) {
            ScoreStrategy scoreStrategy = ScoreStrategyFactory.createStrategy(child);

            if (scoreStrategy != null) {
                scoreStrategy.getScore();
            }
        }

        List<Child> sortedChildren = database.getChildren().stream()
                .sorted(Comparator.comparingInt(Child::getId)).toList();

        double allAverage = 0;
        for (Child child : sortedChildren) {
            allAverage += child.getAverageScore();
        }

        double budgetUnit = database.getSantaBudget() / allAverage;
        for (Child child : database.getChildren()) {
            child.setAssignedBudget(child.getAverageScore() * budgetUnit);
            child.receiveGift(sortedGifts);

            auxiliarList.getChildren().add(new ChildWriter(child.getId(), child.getLastName(),
                    child.getFirstName(), child.getCity(), child.getAge(),
                    child.getGiftsPreferences(), child.getAverageScore(),
                    child.getNiceScoreHistory(), child.getAssignedBudget(),
                    child.getReceivedGifts()));
        }

        writeDatabase.getAnnualChildren().add(auxiliarList);
    }

    /** method that simulates round zero */
    public static void roundZero(final Database database, final WriteDatabase writeDatabase) {
        database.setChildren(database.getInitialData().getChildren());
        database.setGifts(database.getInitialData().getSantaGiftsList());
        database.getChildren().removeIf(child -> (child.getAge() > Constants.TEEN_MAX));

        for (Child child : database.getChildren()) {
            child.getNiceScoreHistory().add(child.getNiceScore());
        }

        Simulation.givingGifts(database, writeDatabase);
    }

    /** method that does the rounds following round zero */
    public static void action(final Database database, final WriteDatabase writeDatabase) {
        for (int i = 0; i < database.getNumberOfYears(); i++) {
            for (Child child : database.getChildren()) {
                child.setReceivedGifts(new ArrayList<>());
                child.setReceivedCategories(new ArrayList<>());
            }

            for (Child child : database.getChildren()) {
                child.setAge(child.getAge() + 1);
            }

            database.getChildren().removeIf(child -> (child.getAge() > Constants.TEEN_MAX));
            AnnualChange currentChange = database.getAnnualChanges().get(i);

            for (Child child : currentChange.getNewChildren()) {
                if (child.getAge() <= Constants.TEEN_MAX) {
                    database.getChildren().add(child);
                    child.getNiceScoreHistory().add(child.getNiceScore());
                }
            }

            currentChange.updateChildren(database);
            for (Gift gift : currentChange.getNewGifts()) {
                database.getGifts().add(gift);
            }

            database.setSantaBudget(currentChange.getNewSantaBudget());
            Simulation.givingGifts(database, writeDatabase);
        }
    }
}
