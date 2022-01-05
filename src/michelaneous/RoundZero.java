package michelaneous;

import data.Database;
import data.WriteDatabase;
import enums.Category;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class RoundZero {
    public static void roundZero(Database database, WriteDatabase writeDatabase) {
        database.setChildren(database.getInitialData().getChildren());
        database.setGifts(database.getInitialData().getSantaGiftsList());
        ChildWriterList auxiliarList = new ChildWriterList();

        List<Gift> sortedGifts = database.getGifts().stream()
                .sorted(Comparator.comparingDouble(Gift::getPrice)).toList();

        for (Child child : database.getChildren()) {
            if (child.getAge() < 5) {
                child.setAgeCategory("Baby");
                child.setAverageScore(10.0);
            }

            if (child.getAge() >= 5 && child.getAge() < 12) {
                child.setAgeCategory("Kid");
                child.setAverageScore(child.getNiceScore());
            }

            if (child.getAge() >= 12 && child.getAge() <= 18) {
                child.setAgeCategory("Teen");
                child.setAverageScore(child.getNiceScore());
            }

            if (child.getAge() > 18) {
                child.setAgeCategory("Young Adult");
            }

            child.niceScoreHistory.add(child.getNiceScore());
        }

        database.getChildren().removeIf(child -> (child.getAge() > 18));

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
                if (!child.receivedCategories.contains(category)) {
                    for (Gift gift : sortedGifts) {
                        if (gift.getCategory() == category) {
                            if (!child.receivedGifts.contains(gift) && (money >= gift.getPrice())) {
                                child.receivedGifts.add(gift);
                                child.receivedCategories.add(category);
                                money -= gift.getPrice();
                                break;
                            }
                        }
                    }
                }
            }

            auxiliarList.getChildren().add(new ChildWriter(child.getId(), child.getLastName(), child.getFirstName(), child.getCity(), child.getAge(), child.getGiftsPreferences(), child.getAverageScore(), child.niceScoreHistory, child.getAssignedBudget(), child.receivedGifts));
        }

        writeDatabase.getAnnualChildren().add(auxiliarList);
    }

}
