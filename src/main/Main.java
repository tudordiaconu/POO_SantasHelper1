package main;

import checker.Checker;
import data.Database;
import data.ReadWrite;
import michelaneous.Simulation;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle

    }
    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException {

        ReadWrite readWrite = new ReadWrite(new Database());
        File directory = new File("tests");

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            readWrite.readAllData(file);
            Simulation.roundZero(readWrite.getDatabase());

            int x = 1;
        }

        Checker.calculateScore();
    }
}
