package main;

import checker.Checker;
import common.Constants;
import database.AnnualChange;
import database.Database;
import fileinputoutput.Input;
import fileinputoutput.InputLoader;
import fileinputoutput.UtilsInputOutput;
import fileinputoutput.Writer;
import org.json.simple.JSONArray;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() { }

    /**
     * Method used to run the application, open the input files,
     * create the output files and directory and generate the output
     * for each one of the 25 tests.
     */
    public static void runApplication() throws IOException {
        Path outputPath = Paths.get(Constants.OUTPUT_DIRECTORY);
        if (!Files.exists(outputPath)) {
            Files.createDirectory(outputPath);
        }
        File outputDirectory = new File(Constants.OUTPUT_DIRECTORY);
        UtilsInputOutput.deleteFiles(outputDirectory.listFiles());

        for (int fileNumber = 1; fileNumber <= Constants.TESTS_NUMBER; fileNumber++) {
            String inputFilePath = Constants.INPUT_PATH + fileNumber + Constants.FILE_EXTENSION;
            String outputFilePath = Constants.OUTPUT_PATH + fileNumber + Constants.FILE_EXTENSION;

            runTest(inputFilePath, outputFilePath);
        }
    }

    /**
     * This method is used to run a single test. It loads the input,
     * creates the database and modifies it accordingly, then writes
     * the output.
     * @param filePath1 Path of the input file.
     * @param filePath2 Path of the output file.
     */
    public static void runTest(final String filePath1,
                              final String filePath2) throws IOException {


        InputLoader inputLoader = new InputLoader(filePath1);
        Input input = inputLoader.readData();

        Writer fileWriter = new Writer(filePath2);
        JSONArray arrayAnnualChildList = new JSONArray();

        //Implementarea logicii
        Database database = Database.getInstance();
        database.loadDatabase(input);

        //Runda 0
        database.implementAnnualOperations();

        arrayAnnualChildList.add(fileWriter.writeChildList(database.getChildList()));

        for (int currentYear = 0; currentYear <= input.getNumberOfYears() - 1; currentYear++) {

            AnnualChange annualChange = input.getChanges().get(currentYear);
            database.implementAnnualChange(annualChange);

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
        /*
          Here is the entry point of the application.
         */
        runApplication();
        Checker.calculateScore();
    }
}
