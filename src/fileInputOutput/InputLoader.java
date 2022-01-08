package fileInputOutput;

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

public final class InputLoader {
    private final String inputPath;

    public InputLoader(final String inputPath) {
        this.inputPath = inputPath;
    }

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
                for (Object jsonIterator : jsonGifts) {
                    giftList.add(new Gift(
                            ((JSONObject) jsonIterator).get(Constants.PRODUCT_NAME).toString(),
                            ((Long)((JSONObject) jsonIterator).get(Constants.PRICE)).intValue(),
                            ((JSONObject) jsonIterator).get(Constants.CATEGORY).toString()
                    ));
                }
            }

            if (jsonAnnualChanges != null) {
                for (Object jsonIterator : jsonAnnualChanges) {
                    AnnualChange annualChange = new AnnualChange();
                    annualChange.setNewSantaBudget(Integer.parseInt(((JSONObject) jsonIterator).get(Constants.NEW_SANTA_BUDGET).toString()));

                    JSONArray jsonNewGifts = (JSONArray) ((JSONObject) jsonIterator).get(Constants.NEW_GIFTS);

                    if (jsonNewGifts != null) {
                        List<Gift> newGifts = new ArrayList<>();
                        for (Object secondJsonIterator : jsonNewGifts) {
                            newGifts.add(new Gift(
                                    ((JSONObject) secondJsonIterator).get(Constants.PRODUCT_NAME).toString(),
                                    ((Long) ((JSONObject) secondJsonIterator).get(Constants.PRICE)).intValue(),
                                    ((JSONObject) secondJsonIterator).get(Constants.CATEGORY).toString()
                            ));
                        }
                        annualChange.setNewGifts(newGifts);
                    } else {
                        annualChange.setNewGifts(null);
                    }


                    JSONArray jsonNewChildren = (JSONArray) ((JSONObject) jsonIterator).get(Constants.NEW_CHILDREN);

                    if (jsonNewChildren != null) {
                        List<Child> newChildren = new ArrayList<>();
                        addChildren(jsonNewChildren, newChildren);
                        annualChange.setNewChildren(newChildren);
                    } else {
                        annualChange.setNewChildren(null);
                    }


                    JSONArray jsonChildrenUpdates = (JSONArray) ((JSONObject) jsonIterator).get(Constants.CHILDREN_UPDATES);

                    if (jsonChildrenUpdates != null) {
                        List<ChildUpdate> childrenUpdates = new ArrayList<>();
                        for (Object secondJsonIterator : jsonChildrenUpdates) {
                            if( ((JSONObject) secondJsonIterator).get(Constants.NICE_SCORE) == null) {
                                childrenUpdates.add(new ChildUpdate(
                                        ((Long) ((JSONObject) secondJsonIterator).get(Constants.ID)).intValue(),
                                        -1,
                                        Utils.convertJSONArray((JSONArray) ((JSONObject) secondJsonIterator)
                                                .get(Constants.GIFTS_PREFERENCES))
                                ));
                            } else {

                            childrenUpdates.add(new ChildUpdate(
                                    ((Long) ((JSONObject) secondJsonIterator).get(Constants.ID)).intValue(),
                                    ((Long) ((JSONObject) secondJsonIterator).get(Constants.NICE_SCORE)).intValue(),
                                    Utils.convertJSONArray((JSONArray) ((JSONObject) secondJsonIterator)
                                            .get(Constants.GIFTS_PREFERENCES))
                            ));
                            }
                        }
                        annualChange.setChildrenUpdates(childrenUpdates);
                    } else {
                        annualChange.setChildrenUpdates(null);
                    }

                    changes.add(annualChange);
                }
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return new Input(numberOfYears, santaBudget, childList, giftList, changes);
    }

    private void addChildren(JSONArray jsonNewChildren, List<Child> newChildren) {
        for (Object secondJsonIterator : jsonNewChildren) {
            newChildren.add(new Child(
                   ((Long) ((JSONObject) secondJsonIterator).get(Constants.ID)).intValue(),
                    ((JSONObject) secondJsonIterator).get(Constants.LAST_NAME).toString(),
                    ((JSONObject) secondJsonIterator).get(Constants.FIRST_NAME).toString(),
                    ((Long) ((JSONObject) secondJsonIterator).get(Constants.AGE)).intValue(),
                    ((JSONObject) secondJsonIterator).get(Constants.CITY).toString(),
                    ((Long) ((JSONObject) secondJsonIterator).get(Constants.NICE_SCORE)).intValue(),
                    Utils.convertJSONArray((JSONArray) ((JSONObject) secondJsonIterator)
                            .get(Constants.GIFTS_PREFERENCES))
            ));
        }
    }
}
