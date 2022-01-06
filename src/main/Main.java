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
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }

    public static void runApplication() throws IOException {
        File inputDirectory = new File(Constants.TESTS_PATH);

        Path outputPath = Paths.get(Constants.OUTPUT_DIRECTORY);
        if (!Files.exists(outputPath)) {
            Files.createDirectory(outputPath);
        }
        File outputDirectory = new File(Constants.OUTPUT_DIRECTORY);
        Utils.deleteFiles(outputDirectory.listFiles());

        for (int fileNumber = 1; fileNumber <= 25; fileNumber++) {
            String inputFilePath = Constants.INPUT_PATH + fileNumber + Constants.FILE_EXTENSION;

            String filepath = Constants.OUTPUT_PATH + fileNumber + Constants.FILE_EXTENSION;
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                runTest(inputFilePath, filepath);
            }
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
        Database database = new Database(input);
        database.removeYoungAdults();

        //Runda 0
        database.calculateChildScores();
        database.calculateBudget();
        database.distributeGifts();

        database.getChildList().sort(Comparator.comparingInt(Child::getId));
        arrayAnnualChildList.add(fileWriter.writeChildList(database.getChildList()));

        //System.out.println(database.getChildList());

        for (AnnualChange annualChange : input.getChanges()) {
            database.implementAnnualChange(annualChange);
            database.calculateChildScores();
            database.calculateBudget();
            database.distributeGifts();

            database.getChildList().sort(Comparator.comparingInt(Child::getId));
            arrayAnnualChildList.add(fileWriter.writeChildList(database.getChildList()));
            //System.out.println(database.getChildList());
        }

        //Afisarea in fisierul out

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
