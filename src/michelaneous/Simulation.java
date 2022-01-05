package michelaneous;

import data.Database;
import data.WriteDatabase;
import enums.Category;

import java.util.*;

public class Simulation {
    public static void action(Database database, WriteDatabase writeDatabase) {
        for (int i = 0; i < database.getNumberOfYears(); i++) {
            for (Child child : database.getChildren()) {
                child.receivedGifts = new ArrayList<>();
                child.receivedCategories = new ArrayList<>();
            }

            ChildWriterList auxiliarList = new ChildWriterList();
            for (Child child : database.getChildren()) {
                child.setAge(child.getAge() + 1);
            }

            List<Gift> sortedGifts = database.getGifts().stream()
                    .sorted(Comparator.comparingDouble(Gift::getPrice)).toList();

            database.getChildren().removeIf(child -> (child.getAge() > 18));

            AnnualChange currentChange = database.getAnnualChanges().get(i);

            for (Child child : currentChange.getNewChildren()) {
                if (child.getAge() <= 18) {
                    database.getChildren().add(child);
                    child.niceScoreHistory.add(child.getNiceScore());
                }
            }

            for (ChildUpdate childUpdate : currentChange.getChildrenUpdates()) {
                for (Child child : database.getChildren()) {
                    if (Objects.equals(child.getId(), childUpdate.getId())) {
                        if (childUpdate.getNiceScore() != null) {
                            child.niceScoreHistory.add(childUpdate.getNiceScore());
                        }

                        if (childUpdate.getGiftsPreferences() != null) {
                            ArrayList<Category> childReversedGiftPreferences = child.getGiftsPreferences();
                            ArrayList<Category> childUpdateReversedGiftPreferences = childUpdate.getGiftsPreferences();
                            Collections.reverse(childReversedGiftPreferences);
                            Collections.reverse(childUpdateReversedGiftPreferences);
                            for (Category category : childUpdateReversedGiftPreferences) {
                                if (childReversedGiftPreferences.contains(category)) {
                                    childReversedGiftPreferences.remove(category);
                                    childReversedGiftPreferences.add(category);
                                } else {
                                    childReversedGiftPreferences.add(category);
                                }
                            }

                            Collections.reverse(childReversedGiftPreferences);
                            child.setGiftsPreferences(childReversedGiftPreferences);
                        }
                    }
                }
            }

            for (Gift gift : currentChange.getNewGifts()) {
                database.getGifts().add(gift);
            }

            database.setSantaBudget(currentChange.getNewSantaBudget());

            for (Child child : database.getChildren()) {
                if (child.getAge() < 5) {
                    child.setAverageScore(10.0);
                }

                if (child.getAge() >= 5 && child.getAge() < 12) {
                    double score = 0;
                    for (int j = 0; j < child.niceScoreHistory.size(); j++) {
                        score += child.niceScoreHistory.get(j);
                    }
                    child.setAverageScore(score / child.niceScoreHistory.size());
                }

                if (child.getAge() >= 12) {
                    double score = 0;
                    int sum = 0;
                    for (int j = 0; j < child.niceScoreHistory.size(); j++) {
                        sum += j + 1;
                        score += child.niceScoreHistory.get(j) * (j + 1);
                    }

                    child.setAverageScore(score / sum);
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

                Double money = child.getAssignedBudget();

                for(Category category : child.getGiftsPreferences()) {
                    if (!child.receivedCategories.contains(category)) {
                        for (Gift gift : sortedGifts) {
                            if (money >= gift.getPrice()) {
                                if (gift.getCategory() == category) {
                                    if (!child.receivedGifts.contains(gift)) {
                                        child.receivedGifts.add(gift);
                                        child.receivedCategories.add(category);
                                        money -= gift.getPrice();
                                        break;
                                    }
                                }
                            } else {
                                break;
                            }
                        }
                    }
                }

                auxiliarList.getChildren().add(new ChildWriter(child.getId(), child.getLastName(), child.getFirstName(), child.getCity(), child.getAge(), child.getGiftsPreferences(), child.getAverageScore(), child.niceScoreHistory, child.getAssignedBudget(), child.receivedGifts));
            }

            writeDatabase.getAnnualChildren().add(auxiliarList);
        }
    }
}
