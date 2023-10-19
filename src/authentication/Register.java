package authentication;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import model.UserConfig;

public class Register {

    private static final String CONFIG_FILE = "config.json";


    public static void selectAccount() {
        System.out.println("\nSelect the required account");
        System.out.println("1. Patient");
        System.out.println("2. Doctor");
        System.out.println("3. Receptionist");
        System.out.print("Please select an option: ");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                registerUser("Patient", 1);
                break;
            case 2:
                registerUser("Doctor", 2);
                break;
            case 3:
                registerUser("Receptionist", 3);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private static void registerUser(String userType, int privilege) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter username: ");
        String username = scanner.nextLine();

        // Check password requirements
        String password;
        do {
            System.out.print("Enter password (at least 8 characters, one uppercase, one number, one symbol): ");
            password = scanner.nextLine();
        } while (!isValidPassword(password));

        // Load existing configuration data
        Map<String, UserConfig> configData = loadConfigData();

        try {
            // Hash the password
            String hashedPassword = hashPasswordMD5(password);

            // Add the new user to the configuration data
            configData.put(username, new UserConfig(hashedPassword, userType, privilege));


            // Save the updated configuration data
            saveConfigData(configData);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.exit(0);
        }

        System.out.println(userType + " registration successful.");
    }

    private static boolean isValidPassword(String password) {
        // At least 8 characters, one uppercase, one number, one symbol
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
        Boolean isValid = Pattern.matches(regex, password);
        if (!isValid)
            System.out.println("Password does not meet the requirements");
        return isValid;
    }

    private static Map<String, UserConfig> loadConfigData() {
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

    private static void saveConfigData(Map<String, UserConfig> configData) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new File(CONFIG_FILE), configData);
        } catch (IOException e) {
            System.out.println("Error saving configuration data: " + e.getMessage());
        }
    }

    public static String hashPasswordMD5(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());

        byte[] byteData = md.digest();

        // Convert the byte data to a hexadecimal string
        StringBuilder sb = new StringBuilder();
        for (byte b : byteData) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }



}
