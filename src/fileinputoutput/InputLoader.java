package fileinputoutput;

import common.Constants;
import database.AnnualChange;
import database.Child;
import database.ChildUpdate;
import database.Gift;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class defines an object used to
 * read data from the JSON files.
 */
public final class InputLoader {
    private final String inputPath;

    public InputLoader(final String inputPath) {
        this.inputPath = inputPath;
    }

    /**
     * Parses data from a JSON file.
     * Returns an object of type Input
     * containing the data received.
     */
    public Input readData() {
        JSONParser jsonParser = new JSONParser();
        int numberOfYears = 0;
        int santaBudget = 0;
        List<Child> childList = new ArrayList<>();
        List<Gift> giftList = new ArrayList<>();
        List<AnnualChange> changes = new ArrayList<>();

        try {
            JSONObject jsonObject = (JSONObject) jsonParser
                    .parse(new FileReader(inputPath));

            numberOfYears = ((Long) (jsonObject.get(Constants.NUMBER_OF_YEARS))).intValue();
            santaBudget = ((Long) (jsonObject.get(Constants.SANTA_BUDGET))).intValue();
            JSONObject jsonInitialData = (JSONObject) jsonObject.get(Constants.INITIAL_DATA);
            JSONArray jsonChildren = (JSONArray) jsonInitialData.get(Constants.CHILDREN);
            JSONArray jsonGifts = (JSONArray) jsonInitialData.get(Constants.SANTA_GIFTS_LIST);
            JSONArray jsonAnnualChanges = (JSONArray) jsonObject.get(Constants.ANNUAL_CHANGES);

            if (jsonChildren != null) {
                addChildren(jsonChildren, childList);
            }

            if (jsonGifts != null) {
                addGifts(jsonGifts, giftList);
            }

            if (jsonAnnualChanges != null) {
                for (Object jsonIterator : jsonAnnualChanges) {
                    AnnualChange annualChange = new AnnualChange();
                    annualChange.setNewSantaBudget(Integer.parseInt(((JSONObject) jsonIterator)
                            .get(Constants.NEW_SANTA_BUDGET).toString()));

                    JSONArray jsonNewGifts = (JSONArray) ((JSONObject) jsonIterator)
                            .get(Constants.NEW_GIFTS);

                    if (jsonNewGifts != null) {
                        List<Gift> newGiftList = new ArrayList<>();
                        addGifts(jsonNewGifts, newGiftList);
                        annualChange.setNewGifts(newGiftList);
                    }

                    JSONArray jsonNewChildren = (JSONArray) ((JSONObject) jsonIterator)
                            .get(Constants.NEW_CHILDREN);

                    if (jsonNewChildren != null) {
                        List<Child> newChildList = new ArrayList<>();
                        addChildren(jsonNewChildren, newChildList);
                        annualChange.setNewChildren(newChildList);
                    }

                    JSONArray jsonChildrenUpdates = (JSONArray) ((JSONObject) jsonIterator)
                            .get(Constants.CHILDREN_UPDATES);

                    if (jsonChildrenUpdates != null) {
                        List<ChildUpdate> childrenUpdates = new ArrayList<>();
                        for (Object secondJsonIterator : jsonChildrenUpdates) {
                            if (((JSONObject) secondJsonIterator)
                                    .get(Constants.NICE_SCORE) == null) {
                                addChildUpdateNullScore(childrenUpdates,
                                        (JSONObject) secondJsonIterator);
                            } else {
                                addChildUpdateValidScore(childrenUpdates,
                                        (JSONObject) secondJsonIterator);
                            }
                        }
                        annualChange.setChildrenUpdates(childrenUpdates);
                    }

                    changes.add(annualChange);
                }
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return new Input(numberOfYears, santaBudget, childList, giftList, changes);
    }

    /**
     * Parses a list of Child objects.
     */
    private void addChildren(final JSONArray jsonNewChildren, final List<Child> newChildren) {
        for (Object jsonIterator : jsonNewChildren) {
            newChildren.add(new Child(
                   ((Long) ((JSONObject) jsonIterator).get(Constants.ID)).intValue(),
                    ((JSONObject) jsonIterator).get(Constants.LAST_NAME).toString(),
                    ((JSONObject) jsonIterator).get(Constants.FIRST_NAME).toString(),
                    ((Long) ((JSONObject) jsonIterator).get(Constants.AGE)).intValue(),
                    ((JSONObject) jsonIterator).get(Constants.CITY).toString(),
                    ((Long) ((JSONObject) jsonIterator).get(Constants.NICE_SCORE)).intValue(),
                    UtilsInputOutput.convertJSONArray((JSONArray) ((JSONObject) jsonIterator)
                            .get(Constants.GIFTS_PREFERENCES))
            ));
        }
    }

    /**
     * Parses a list of Gift objects.
     */
    private void addGifts(final JSONArray jsonGifts, final List<Gift> giftList) {
        for (Object jsonIterator : jsonGifts) {
            giftList.add(new Gift(
                    ((JSONObject) jsonIterator).get(Constants.PRODUCT_NAME).toString(),
                    ((Long) ((JSONObject) jsonIterator).get(Constants.PRICE)).intValue(),
                    ((JSONObject) jsonIterator).get(Constants.CATEGORY).toString()
            ));
        }
    }

    /**
     * Parses a list of ChildUpdate object in the
     * case that the new nice score is not null.
     */
    private void addChildUpdateValidScore(final List<ChildUpdate> childrenUpdates,
                                          final JSONObject secondJsonIterator) {
        childrenUpdates.add(new ChildUpdate(
                ((Long) secondJsonIterator.get(Constants.ID)).intValue(),
                ((Long) secondJsonIterator.get(Constants.NICE_SCORE)).intValue(),
                UtilsInputOutput.convertJSONArray((JSONArray) secondJsonIterator
                        .get(Constants.GIFTS_PREFERENCES))
        ));
    }

    /**
     * Parses a list of ChildUpdate object in the
     * case that the new nice score is null.
     */
    private void addChildUpdateNullScore(final List<ChildUpdate> childrenUpdates,
                                         final JSONObject secondJsonIterator) {
        childrenUpdates.add(new ChildUpdate(
                ((Long) secondJsonIterator.get(Constants.ID))
                        .intValue(),
                -1,
                UtilsInputOutput.convertJSONArray((JSONArray) secondJsonIterator
                        .get(Constants.GIFTS_PREFERENCES))
        ));
    }
}
