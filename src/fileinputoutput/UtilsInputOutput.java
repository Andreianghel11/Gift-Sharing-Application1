package fileinputoutput;

import org.json.simple.JSONArray;

import java.io.File;
import java.util.ArrayList;

/**
 * Contains utility methods used
 * for input/output.
 */
public final class UtilsInputOutput {

    private UtilsInputOutput() { }
    /**
     * Deletes every file inside a directory.
     */
    public static void deleteFiles(final File[] directory) {
        if (directory != null) {
            for (File file : directory) {
                file.delete();
            }
        }
    }

    /**
     * Converts a JSONArray to an ArrayList of Strings.
     */
    public static ArrayList<String> convertJSONArray(final JSONArray array) {
        if (array != null) {
            ArrayList<String> finalArray = new ArrayList<>();
            for (Object object : array) {
                finalArray.add((String) object);
            }
            return finalArray;
        } else {
            return null;
        }
    }
}
