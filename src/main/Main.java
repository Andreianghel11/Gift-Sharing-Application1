package main;

import checker.Checker;
import common.Constants;
import database.AnnualChange;
import database.Child;
import database.Database;
import fileInputOutput.Input;
import fileInputOutput.InputLoader;
import fileInputOutput.Utils;
import fileInputOutput.Writer;
import org.json.simple.JSONArray;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }

    public static void runApplication() throws IOException {
        Path outputPath = Paths.get(Constants.OUTPUT_DIRECTORY);
        if (!Files.exists(outputPath)) {
            Files.createDirectory(outputPath);
        }
        File outputDirectory = new File(Constants.OUTPUT_DIRECTORY);
        Utils.deleteFiles(outputDirectory.listFiles());

        for (int fileNumber = 1; fileNumber <= 25; fileNumber++) {
            String inputFilePath = Constants.INPUT_PATH + fileNumber + Constants.FILE_EXTENSION;
            String outputFilePath = Constants.OUTPUT_PATH + fileNumber + Constants.FILE_EXTENSION;

            runTest(inputFilePath, outputFilePath);
        }
    }

    public static void runTest(final String filePath1,
                              final String filePath2) throws IOException {

        //Citirea input-ului
        InputLoader inputLoader = new InputLoader(filePath1);
        Input input = inputLoader.readData();

        Writer fileWriter = new Writer(filePath2);
        JSONArray arrayAnnualChildList = new JSONArray();

        //Implementarea logicii
        Database database = Database.getInstance();
        database.loadDatabase(input);
        database.removeYoungAdults();

        //Runda 0
        database.calculateChildScores();
        database.calculateBudget();
        database.distributeGifts();
        database.sortChildren();
        arrayAnnualChildList.add(fileWriter.writeChildList(database.getChildList()));

        for (int currentYear = 0; currentYear <= input.getNumberOfYears() - 1; currentYear++) {

            AnnualChange annualChange = input.getChanges().get(currentYear);
            database.implementAnnualChange(annualChange);

            database.sortChildren();
            arrayAnnualChildList.add(fileWriter.writeChildList(database.getChildList()));
        }

        fileWriter.closeJSON(arrayAnnualChildList);
    }


    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException {
        runApplication();

        Checker.calculateScore();
    }
}
