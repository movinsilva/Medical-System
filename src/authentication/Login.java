package authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Scanner;

import model.UserConfig;

import static authentication.Hashing.hashPasswordMD5;
import static dashboard.Dashboard.dashboardFunctions;
import static storage.Config.loadConfigData;

public class Login {
    private static final String CONFIG_FILE = "config.json";

    public static void loginUser() {


        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Load the user configuration data
        Map<String, UserConfig> configData = loadConfigData();

        // Check if the provided username exists in the configuration
        if (configData.containsKey(username)) {
            UserConfig userConfig = configData.get(username);

            // Verify the password
            try {
                if (hashPasswordMD5(password).equals(userConfig.getHashedPassword())) {
                    System.out.println("Login successful.");


                    dashboardFunctions(userConfig.getPrivilegeLevel(), username);

                } else {
                    System.out.println("Incorrect password. Login failed.");
                }
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("User not found. Login failed.");
        }







    }





}