package fileinputoutput;

import common.Constants;
import database.Child;
import database.Gift;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class used to write the output.
 */
public class Writer {
    /**
     * The file where the data will be written
     */
    private final FileWriter file;

    public Writer(final String path) throws IOException {
        this.file = new FileWriter(path);
    }

    /**
     * Writes a Child list as a JSONObject.
     */
    public JSONObject writeChildList(final List<Child> childList) throws IOException {
        JSONObject object = new JSONObject();
        JSONArray arrayChildren = new JSONArray();
        for (Child child : childList) {
            arrayChildren.add(writeChild(child));
        }

        object.put(Constants.CHILDREN, arrayChildren);

        return object;
    }

    /**
     * Writes a Child as a JSONObject.
     */
    public JSONObject writeChild(final Child child) throws IOException {
        JSONObject objectChild = new JSONObject();

        objectChild.put(Constants.ID, child.getId());
        objectChild.put(Constants.LAST_NAME, child.getLastName());
        objectChild.put(Constants.FIRST_NAME, child.getFirstName());
        objectChild.put(Constants.CITY, child.getCity());
        objectChild.put(Constants.AGE, child.getAge());

        List<String> giftsPreferencesList = new ArrayList<>(child.getGiftPreferences());
        objectChild.put(Constants.GIFTS_PREFERENCES, giftsPreferencesList);

        objectChild.put(Constants.AVERAGE_SCORE, child.getNiceScore());

        List<Double> niceScoreList = new ArrayList<>(child.getNiceScoresList());
        objectChild.put(Constants.NICE_SCORE_HISTORY, niceScoreList);

        objectChild.put(Constants.ASSIGNED_BUDGET, child.getBudgetAllocated());

        JSONArray arrayReceivedGifts = writeReceivedGifts(child.getGiftsReceived());
        objectChild.put(Constants.RECEIVED_GIFTS, arrayReceivedGifts);

        return objectChild;
    }

    /**
     * Writes a Gift list as a JSONArray.
     */
    public JSONArray writeReceivedGifts(final List<Gift> giftsReceived) {
        JSONArray arrayReceivedGifts = new JSONArray();
        for (Gift gift : giftsReceived) {
            JSONObject objectGift = writeGift(gift);
            arrayReceivedGifts.add(objectGift);
        }

        return arrayReceivedGifts;
    }

    /**
     * Writes a Gift as a JSONObject.
     */
    public JSONObject writeGift(final Gift gift) {
        JSONObject objectGift = new JSONObject();
        objectGift.put(Constants.PRODUCT_NAME, gift.getName());
        objectGift.put(Constants.PRICE, gift.getPrice());
        objectGift.put(Constants.CATEGORY, gift.getCategory());

        return objectGift;
    }

    /**
     * Writes to the file and closes it.
     */
    public void closeJSON(final JSONArray array) {
        JSONObject result = new JSONObject();
        result.put(Constants.ANNUAL_CHILDREN, array);

        try {
            file.write(result.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
