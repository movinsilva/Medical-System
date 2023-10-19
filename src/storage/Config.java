package storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.UserConfig;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Config {

    private static final String CONFIG_FILE = "config.json";

    public static Map<String, UserConfig> loadConfigData() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File configFile = new File(CONFIG_FILE);

            if (configFile.exists()) {
                return objectMapper.readValue(configFile, new TypeReference<Map<String, UserConfig>>() {});
            } else {
                return new HashMap<>();
            }
        } catch (IOException e) {
            System.out.println("Error loading configuration data: " + e.getMessage());
            return new HashMap<>();
        }
    }

    public static void saveConfigData(Map<String, UserConfig> configData) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new File(CONFIG_FILE), configData);
        } catch (IOException e) {
            System.out.println("Error saving configuration data: " + e.getMessage());
        }
    }

}
