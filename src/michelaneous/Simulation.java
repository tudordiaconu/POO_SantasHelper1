package michelaneous;

import data.Database;

public class Simulation {
    public static void roundZero(Database database) {
        database.setChildren(database.getInitialData().getChildren());
        database.setGifts(database.getInitialData().getSantaGiftsList());

        for (Child child : database.getChildren()) {
            if (child.getAge() < 5) {
                child.setAgeCategory("Baby");
            }

            if (child.getAge() >= 5 && child.getAge() < 12) {
                child.setAgeCategory("Kid");
            }

            if (child.getAge() >= 12 && child.getAge() < 18) {
                child.setAgeCategory("Teen");
            }

            if (child.getAge() >= 18) {
                child.setAgeCategory("Young Adult");
            }
        }
    }
}
