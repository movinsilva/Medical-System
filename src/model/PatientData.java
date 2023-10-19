package model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientData {
    @JsonProperty("personal details")
    private Map<String, String> personalDetails;

    @JsonProperty("records")
    private List<Map<String, String>> records;

    public PatientData() {
        personalDetails = new HashMap<>();
        records = new ArrayList<>();
    }

    // Getters and setters for personalDetails and records
    public Map<String, String> getPersonalDetails() {
        return personalDetails;
    }

    public void setPersonalDetails(Map<String, String> personalDetails) {
        this.personalDetails = personalDetails;
    }

    public List<Map<String, String>> getRecords() {
        return records;
    }

    public void setRecords(List<Map<String, String>> records) {
        this.records = records;
    }
}
