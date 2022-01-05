package main;

import checker.Checker;
import common.Constants;
import fileInputOutput.Input;
import fileInputOutput.InputLoader;
import fileInputOutput.Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

        for (File file : Objects.requireNonNull(inputDirectory.listFiles())) {

            String filepath = Constants.OUTPUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                runTest(file.getAbsolutePath(), filepath);
            }
        }
    }

    public static void runTest(final String filePath1,
                              final String filePath2) throws IOException {
        InputLoader inputLoader = new InputLoader(filePath1);
        Input input = inputLoader.readData();

        System.out.println(input);
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
