package main;

import checker.Checker;
import common.Constants;
import data.Database;
import data.ReadWrite;
import data.WriteDatabase;
import michelaneous.RoundZero;
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
        for (int i = 12; i <= 25; i++) {
            ReadWrite readWrite = new ReadWrite(new Database(), new WriteDatabase());
            String inputName = "tests/test" + i + Constants.FILE_EXTENSION;
            String outputName = Constants.OUTPUT_PATH + i + Constants.FILE_EXTENSION;
            readWrite.readAllData(inputName);
            RoundZero.roundZero(readWrite.getDatabase(), readWrite.getWriteDatabase());
            Simulation.action(readWrite.getDatabase(), readWrite.getWriteDatabase());
            readWrite.writeAllData(outputName);
        }

        Checker.calculateScore();
    }
}
