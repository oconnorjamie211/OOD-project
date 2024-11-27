import java.io.*;
import java.util.*;

public class SalaryAndEmployeeCsvReader {
    public static void main(String[] args) {
        String file1 = "Employee Data(Sheet1).csv";
        String file2 = "Project Data scales(Sheet1).csv";

        BufferedReader reader = null;
        String line = "";

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter Employee Name: ");
            String name = scanner.nextLine().trim();

            BufferedReader reader1 = new BufferedReader(new FileReader(file1));
            BufferedReader reader2 = new BufferedReader(new FileReader(file2));

            String line1, line2;
            boolean found = false;


            while ((line1 = reader1.readLine()) != null) {
                String[] row1 = line1.split(",");


                if (row1.length >= 3 && row1[0].equals(name)) {
                    System.out.println("Job Title: " + row1[1] + " Salary Scale: " + row1[2]);

                    while ((line2 = reader2.readLine()) != null) {
                        String[] row2 = line2.split(",");


                        if (row2.length >= 3 && row1[1].equals(row2[0]) && row1[2].equals(row2[1])) {
                            System.out.println("Salary: " + row2[2]);
                            found = true;
                            break;
                        }
                    }
                    break;
                }
            }

            if (!found) {
                System.out.println("Employee not found");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found please ensure that the correct file path is listed.");
        } catch (IOException e) {
            System.out.println("I/O Error file cannot be read.");
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