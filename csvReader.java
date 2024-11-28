import java.io.*;
import java.util.Scanner;

public class csvReader {
    public static void main(String[] args) {
        // CSV file path
        String file = "Project Data scales(Sheet1).csv";
        BufferedReader reader = null;
        String line = "";

        Scanner scanner = new Scanner(System.in);

        try {
            // Prompt user for job title and pay scale
            System.out.print("Enter Job Title: ");
            String jobTitle = scanner.nextLine().trim();

            System.out.print("Enter Pay Scale: ");
            String payScale = scanner.nextLine().trim();

            // Open the CSV file for reading
            reader = new BufferedReader(new FileReader(file));
            boolean found = false; // To check if a match is found

            // Read the CSV file line by line
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");

                // Check if the row matches the job title and pay scale
                if (row.length >= 3 && row[0].equalsIgnoreCase(jobTitle) && row[1].equalsIgnoreCase(payScale)) {
                    //this removes all charecters in the string that arent numbers
                    String strSalary = (row[2] + row[3]).replaceAll("[^0-9.]", "");                    found = true;
                    //this changes the salary to a double so it can be used in calculations
                    double DblSalary = Double.parseDouble(strSalary);
                    System.out.println("salary: " + DblSalary);

                    break; // Stop searching after finding the first match
                }
            }

            if (!found) {
                System.out.println("No matching record found for Job Title and Pay Scale.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found. Please ensure the file path is correct.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                scanner.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
