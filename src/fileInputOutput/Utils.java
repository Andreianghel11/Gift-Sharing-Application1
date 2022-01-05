package fileInputOutput;

import org.json.simple.JSONArray;

import java.io.File;
import java.util.ArrayList;

public final class Utils {
    public static void deleteFiles(final File[] directory) {
        if (directory != null) {
            for (File file : directory) {
                file.delete();
            }
        }
    }

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
