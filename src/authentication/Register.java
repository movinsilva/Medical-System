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

import model.PatientData;
import model.UserConfig;

import static authentication.Hashing.hashPasswordMD5;
import static storage.Config.loadConfigData;
import static storage.Config.saveConfigData;
import static storage.Data.loadPatientDataFromJson;
import static storage.Data.savePatientDataToJson;

public class Register {




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
                registerUser("Patient", 0);
                break;
            case 2:
                registerUser("Doctor", 1);
                break;
            case 3:
                registerUser("Receptionist", 2);
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

        if (privilege == 1) {
            Map<String, PatientData> currData = loadPatientDataFromJson();
            currData.put(username, new PatientData());
            savePatientDataToJson(currData);
        }
    }

    private static boolean isValidPassword(String password) {
        // At least 8 characters, one uppercase, one number, one symbol
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
        Boolean isValid = Pattern.matches(regex, password);
        if (!isValid)
            System.out.println("Password does not meet the requirements");
        return isValid;
    }









}
