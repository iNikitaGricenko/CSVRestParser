package com.wolfhack.csvrest;

import com.wolfhack.csvrest.data.Model.CSVModel;

import java.util.Map;
import java.util.Vector;

public class MapConverter {

    public static Vector<CSVModel> convertToVector(Map<String, String[]> map) {
        String[] states = map.get("State");
        String[] names = map.get("Name");
        String[] institutions = map.get("Institution Type");
        String[] phones = map.get("Phone Number");
        String[] websites = map.get("Website");

        int modelsSize = states.length;

        Vector<CSVModel> models = new Vector<>();
        models.setSize(modelsSize);

        for (int i = 0; i < models.size(); i++) {
            CSVModel model = new CSVModel(
                    states[i],
                    names[i],
                    institutions[i],
                    phones[i],
                    websites[i]
            );

            models.set(i, model);
        }

        return models;
    }
}
