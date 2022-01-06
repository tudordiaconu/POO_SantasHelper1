package data;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.Constants;
import enums.Category;
import michelaneous.AnnualChange;
import michelaneous.Child;
import michelaneous.ChildUpdate;
import michelaneous.ChildWriter;
import michelaneous.ChildWriterList;
import michelaneous.Gift;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class ReadWrite {
    private Database database;
    private WriteDatabase writeDatabase;

    /** getter for the database used for writing */
    public WriteDatabase getWriteDatabase() {
        return writeDatabase;
    }

    /** setter for the database used for writing */
    public void setWriteDatabase(final WriteDatabase writeDatabase) {
        this.writeDatabase = writeDatabase;
    }

    public ReadWrite(final Database database, final WriteDatabase writeDatabase) {
        this.database = database;
        this.writeDatabase = writeDatabase;
    }


    /** method that reads from file into database */
    public void readAllData(final String file) throws IOException {
        database = new ObjectMapper().readerFor(Database.class).readValue(new File(file));
    }

    /** method that does the writing */
    public void writeAllData(final String file) throws IOException {
        ObjectMapper om = new ObjectMapper();
        om.writerWithDefaultPrettyPrinter().writeValue(new File(file), writeDatabase);
    }


    /** getter for the database used to store data */
    public Database getDatabase() {
        return database;
    }

    /** setter for the database used to store data */
    public void setDatabase(final Database database) {
        this.database = database;
    }

    /** method that simulates round zero */
    public static void roundZero(final Database database, final WriteDatabase writeDatabase) {
        database.setChildren(database.getInitialData().getChildren());
        database.setGifts(database.getInitialData().getSantaGiftsList());
        ChildWriterList auxiliarList = new ChildWriterList();

        List<Gift> sortedGifts = database.getGifts().stream()
                .sorted(Comparator.comparingDouble(Gift::getPrice)).toList();

        for (Child child : database.getChildren()) {
            if (child.getAge() < Constants.BABY_MAX) {
                child.setAgeCategory("Baby");
                child.setAverageScore(Constants.BABY_SCORE);
            }

            if (child.getAge() >= Constants.BABY_MAX && child.getAge() < Constants.KID_MAX) {
                child.setAgeCategory("Kid");
                child.setAverageScore(child.getNiceScore());
            }

            if (child.getAge() >= Constants.KID_MAX && child.getAge() <= Constants.TEEN_MAX) {
                child.setAgeCategory("Teen");
                child.setAverageScore(child.getNiceScore());
            }

            if (child.getAge() > Constants.TEEN_MAX) {
                child.setAgeCategory("Young Adult");
            }

            child.getNiceScoreHistory().add(child.getNiceScore());
        }

        database.getChildren().removeIf(child -> (child.getAge() > Constants.TEEN_MAX));

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

            for (Category category : child.getGiftsPreferences()) {
                if (!child.getReceivedCategories().contains(category)) {
                    for (Gift gift : sortedGifts) {
                        if (gift.getCategory() == category) {
                            if (!child.getReceivedGifts().contains(gift)
                                    && (money >= gift.getPrice())) {
                                child.getReceivedGifts().add(gift);
                                child.getReceivedCategories().add(category);
                                money -= gift.getPrice();
                                break;
                            }
                        }
                    }
                }
            }

            auxiliarList.getChildren().add(new ChildWriter(child.getId(), child.getLastName(),
                    child.getFirstName(), child.getCity(), child.getAge(),
                    child.getGiftsPreferences(), child.getAverageScore(),
                    child.getNiceScoreHistory(), child.getAssignedBudget(),
                    child.getReceivedGifts()));
        }

        writeDatabase.getAnnualChildren().add(auxiliarList);
    }

    /** method that does the rounds following round zero */
    public static void action(final Database database, final WriteDatabase writeDatabase) {
        for (int i = 0; i < database.getNumberOfYears(); i++) {
            for (Child child : database.getChildren()) {
                child.setReceivedGifts(new ArrayList<>());
                child.setReceivedCategories(new ArrayList<>());
            }

            ChildWriterList auxiliarList = new ChildWriterList();
            for (Child child : database.getChildren()) {
                child.setAge(child.getAge() + 1);
            }

            List<Gift> sortedGifts = database.getGifts().stream()
                    .sorted(Comparator.comparingDouble(Gift::getPrice)).toList();

            database.getChildren().removeIf(child -> (child.getAge() > Constants.TEEN_MAX));

            AnnualChange currentChange = database.getAnnualChanges().get(i);

            for (Child child : currentChange.getNewChildren()) {
                if (child.getAge() <= Constants.TEEN_MAX) {
                    database.getChildren().add(child);
                    child.getNiceScoreHistory().add(child.getNiceScore());
                }
            }

            for (ChildUpdate childUpdate : currentChange.getChildrenUpdates()) {
                for (Child child : database.getChildren()) {
                    if (Objects.equals(child.getId(), childUpdate.getId())) {
                        if (childUpdate.getNiceScore() != null) {
                            child.getNiceScoreHistory().add(childUpdate.getNiceScore());
                        }

                        if (childUpdate.getGiftsPreferences() != null) {
                            ArrayList<Category> childReversedGiftPreferences =
                                    child.getGiftsPreferences();
                            ArrayList<Category> childUpdateReversedGiftPreferences =
                                    childUpdate.getGiftsPreferences();
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
                if (child.getAge() < Constants.BABY_MAX) {
                    child.setAverageScore(Constants.BABY_SCORE);
                }

                if (child.getAge() >= Constants.BABY_MAX && child.getAge() < Constants.KID_MAX) {
                    double score = 0;
                    for (int j = 0; j < child.getNiceScoreHistory().size(); j++) {
                        score += child.getNiceScoreHistory().get(j);
                    }
                    child.setAverageScore(score / child.getNiceScoreHistory().size());
                }

                if (child.getAge() >= Constants.KID_MAX) {
                    double score = 0;
                    int sum = 0;
                    for (int j = 0; j < child.getNiceScoreHistory().size(); j++) {
                        sum += j + 1;
                        score += child.getNiceScoreHistory().get(j) * (j + 1);
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

                for (Category category : child.getGiftsPreferences()) {
                    if (!child.getReceivedCategories().contains(category)) {
                        for (Gift gift : sortedGifts) {
                            if (money >= gift.getPrice()) {
                                if (gift.getCategory() == category) {
                                    if (!child.getReceivedGifts().contains(gift)) {
                                        child.getReceivedGifts().add(gift);
                                        child.getReceivedCategories().add(category);
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

                auxiliarList.getChildren().add(new ChildWriter(child.getId(), child.getLastName(),
                        child.getFirstName(), child.getCity(), child.getAge(),
                        child.getGiftsPreferences(), child.getAverageScore(),
                        child.getNiceScoreHistory(), child.getAssignedBudget(),
                        child.getReceivedGifts()));
            }

            writeDatabase.getAnnualChildren().add(auxiliarList);
        }
    }
}
