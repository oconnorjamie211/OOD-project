import java.io.*;
import java.util.*;


public class HourlyRateandEmployeeCSVReader {
    private static double hourlyRate;

    public static void main(String[] args) throws IOException {
        String file1 = "ptEmployeeData.csv";
        String file2 ="HourlyPositionsandRates.csv";

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


                if (row1.length >= 10 && row1[0].equals(name)) {


                    while ((line2 = reader2.readLine()) != null) {
                        String[] row2 = line2.split(",");


                        if (row2.length >= 3 && row1[1].equals(row2[0]) && row1[9].equals(row2[1])) {
                            System.out.println("Hourly Rate: " + row2[2]);
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
