package dashboard;

import model.PatientData;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static storage.Data.loadPatientDataFromJson;

public class Dashboard {

    public static void dashboardFunctions(int userPrivilegeLevel, String user) {

        int[][] accessControlMatrix = { {1, 0}, {1,1} };

        System.out.println("\nSelect the required function");
        System.out.println("0. Read Patient Data");
        System.out.println("1. Update Patient Data");
        System.out.print("Please select an option: ");

        Scanner scanner = new Scanner(System.in);
        int selectedFunction = scanner.nextInt();

        // Check if the user can access the selected function
        if (selectedFunction >= 0 && selectedFunction < accessControlMatrix[0].length) {
            if (accessControlMatrix[userPrivilegeLevel][selectedFunction] == 1) {
                // User has access to the selected function
                switch (selectedFunction) {
                    case 0:
                        Map<String, PatientData> currData = loadPatientDataFromJson();
                        printUserData(user, currData);
                        break;
                    case 1:
                        Map<String, PatientData> currData1 = loadPatientDataFromJson();
                        break;
                    default:
                        System.out.println("Invalid function selected.");
                }
            } else {
                System.out.println("Access denied. Insufficient privilege level.");
            }
        } else {
            System.out.println("Invalid function selected.");
        }
    }



    public static <List> void printUserData(String username, Map<String, PatientData> dataMap) {
        if (dataMap.containsKey(username)) {
            PatientData patientData = dataMap.get(username);

            // Print personal details
            System.out.println("\nPersonal Details for User: " + username);
            Map<String, String> personalDetails = patientData.getPersonalDetails();
            for (Map.Entry<String, String> entry : personalDetails.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            // Print records
            System.out.println("\nRecords for User: " + username);
            java.util.List<Map<String, String>> records = patientData.getRecords();
            for (Map<String, String> record : records) {
                System.out.println("Record:");
                for (Map.Entry<String, String> entry : record.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
            }
        } else {
            System.out.println("User not found: " + username);
        }
    }


    public static void updateUserData(String username, String key, String value, Map<String, PatientData> dataMap) {
        if (dataMap.containsKey(username)) {
            PatientData patientData = dataMap.get(username);

            // Update personal details
            Map<String, String> personalDetails = patientData.getPersonalDetails();
            personalDetails.put(key, value);

            // Save the updated data back to the JSON file


            System.out.println("Information updated for User: " + username);
        } else {
            System.out.println("User not found: " + username);
        }
    }



}


