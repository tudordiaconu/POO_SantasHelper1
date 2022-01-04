package michelaneous;

import data.Database;
import enums.Category;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Simulation {
    public static void roundZero(Database database) {
        database.setChildren(database.getInitialData().getChildren());
        database.setGifts(database.getInitialData().getSantaGiftsList());

        for (Child child : database.getChildren()) {
            if (child.getAge() < 5) {
                child.setAgeCategory("Baby");
                child.setAverageScore(10.0);
            }

            if (child.getAge() >= 5 && child.getAge() < 12) {
                child.setAgeCategory("Kid");
                child.setAverageScore(child.getNiceScore());
            }

            if (child.getAge() >= 12 && child.getAge() < 18) {
                child.setAgeCategory("Teen");
                child.setAverageScore(child.getNiceScore());
            }

            if (child.getAge() >= 18) {
                child.setAgeCategory("Young Adult");
            }


            child.niceScoreHistory.add(child.getNiceScore());
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

            Double money = child.getAssignedBudget();

            for(Category category : child.getGiftsPreferences()) {
                Gift assignedGift = null;
                for (Gift gift : database.getGifts()) {
                    if (gift.getCategory() == category) {
                        if (assignedGift == null) {
                            assignedGift = gift;
                        } else {
                            if (gift.getPrice() < assignedGift.getPrice()) {
                                assignedGift = gift;
                            }
                        }

                        if (money > assignedGift.getPrice()) {
                            child.receivedGifts.add(assignedGift);
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }
}
