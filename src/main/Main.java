package main;

import checker.Checker;
import common.Constants;
import data.Database;
import data.ReadWrite;
import data.WriteDatabase;

import java.io.IOException;

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
        for (int i = 1; i <= Constants.TESTS_NUMBER; i++) {
            ReadWrite readWrite = new ReadWrite(new Database(), new WriteDatabase());
            String inputName = Constants.INPUT_PATH + i + Constants.FILE_EXTENSION;
            String outputName = Constants.OUTPUT_PATH + i + Constants.FILE_EXTENSION;
            readWrite.readAllData(inputName);
            ReadWrite.roundZero(readWrite.getDatabase(), readWrite.getWriteDatabase());
            ReadWrite.action(readWrite.getDatabase(), readWrite.getWriteDatabase());
            readWrite.writeAllData(outputName);
        }

        Checker.calculateScore();
    }
}
