package storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.PatientData;
import model.UserConfig;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Data {
    private static final String DATA_FILE = "data.json";

    public static Map<String, PatientData> loadPatientDataFromJson() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File configFile = new File(DATA_FILE);

            if (configFile.exists()) {
                return objectMapper.readValue(configFile, new TypeReference<Map<String, PatientData>>() {});
            } else {
                return new HashMap<>();
            }
        } catch (IOException e) {
            System.out.println("Error loading patient data from data.json: " + e.getMessage());
            return new HashMap<>();
        }
    }

    public static void updatePatientData(String patientName, String key, String value, Map<String, PatientData> patientDataMap) {
        if (patientDataMap.containsKey(patientName)) {
            PatientData patientData = patientDataMap.get(patientName);
            Map<String, String> personalDetails = patientData.getPersonalDetails();
            personalDetails.put(key, value);
        } else {
            System.out.println("Patient not found: " + patientName);
        }
    }

    public static void savePatientDataToJson(Map<String, PatientData> patientDataMap) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(DATA_FILE), patientDataMap);
        } catch (IOException e) {
            System.out.println("Error saving patient data to data.json: " + e.getMessage());
        }
    }
}
