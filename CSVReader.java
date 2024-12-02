import java.io.*;
import java.util.*;

public class CSVReader {

    /**
     * this is a reader that gets the salary of a FullTimeEmployee object from "Project Data scales(Sheet1).csv"
     * by using the objects getters to retrieve the gross salary according to the Employees job title and pay scale
     * @param employee object passed that holds the data required
     * @return a double grossSalary which holds the gross salary for the employee or a type double -1 if no matching record is found
     */

    public static double getSalary(FullTimeEmployee employee) {
        //init variables for use in method
        String file = "Project Data scales(Sheet1).csv";
        BufferedReader reader = null;
        String line = "";
        String jobTitle = employee.getJobTitle();
        String payScale = employee.getPayScale();

        try {
            //open the CSV file for reading
            reader = new BufferedReader(new FileReader(file));

            //read the CSV file line by line
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");

                //check if the row matches the job title and pay scale
                if (row.length >= 3 && row[0].equalsIgnoreCase(jobTitle) && row[1].equalsIgnoreCase(payScale)) {
                    //remove non number characters and turn to double
                    String strSalary = (row[2] + row[3]).replaceAll("[^0-9.]", "");
                    return Double.parseDouble(strSalary); //return salary as a double
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();//close reader
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //return -1 if no matching record is found
        return -1;
    }


    /**
     * checks if a job title and the according payscale exist with in the "Project Data scales(Sheet1).csv" for Full-time
     * @param jobTitle stores the job title an Employee
     * @param payScale stores the payscale of an Employee
     * @return a boolean value of true if it exists in the csv and false if it does not
     */
    public static boolean checkJobTitlePayScale(String jobTitle, String payScale) {
        String file = "Project Data scales(Sheet1).csv";
        BufferedReader reader = null;
        String line = "";


        try {
            //open the CSV file for reading
            reader = new BufferedReader(new FileReader(file));

            //read the CSV file line by line
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");

                //check if the row matches the job title and pay scale
                if (row.length >= 3 && row[0].equalsIgnoreCase(jobTitle) && row[1].equalsIgnoreCase(payScale)) {
                    return true; //return true as it is there
                } else {
                    return false;//returns false as it is not there
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();//close reader
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //return false if no matching record is found
        return false;
    }


    /**
     * this is a reader that gets the hourlyRate of a PartTimeEmployee object from "HourlyPositionsandRates.csv"
     * by using the objects getters to retrieve the gross salary according to the Employees job title and pay scale
     * @param employee PartTimeEmployee object passed that holds the data required
     * @return a double hourlyRate which holds the hourlyRate for the employee
     */
    public static double getHourlyRate(PartTimeEmployee employee) {
        //init for later use in method
        String jobTitle = employee.getJobTitle();
        String payScale = employee.getPayScale();

        String HOURLY_POSITIONS_FILE = "HourlyPositionsandRates.csv";
        double hourlyRate = 0.0;
        try (BufferedReader br = new BufferedReader(new FileReader(HOURLY_POSITIONS_FILE))) {
            String line = br.readLine(); //read the header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                //checking the values in rows in csv
                if (values[0].equalsIgnoreCase(jobTitle) && values[1].trim().equals(payScale)) {
                    //getting hourly rate
                    hourlyRate = Double.parseDouble(values[2].trim());
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hourlyRate;//return hourlyRAte
    }


    /**
     * this function saves the status pf wether a Part-Time Employee loaded their hours or not before the 2nd friday of the
     * month and saves the data to a csv ("LodgedHours.csv") along with the hours they worked this is to allow for us to keep this data saved even
     * when instance of the menu is ended
     * @param emp PartTimeEmployee object which the status is being saved
     */

    public static void saveEmployeeStatus(PartTimeEmployee emp) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("LodgedHours.csv", true))) {
            //writing in csv
            writer.append(emp.getName() + "," + emp.hasLodgedHours() + "," + emp.getHoursWorked() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method loads the data that is stored with in "LodgedHours.csv", and saves it to corresponding
     * PartTimeEmployee object
     * @param ptEmployeeData a Map that holds all the PartTimeEmployees objects which so the data can be loaded to the
     *                       objects from the csv for use in the code
     */

    public static void loadEmployeeStatus(Map<String, PartTimeEmployee> ptEmployeeData) {
        try (BufferedReader reader = new BufferedReader(new FileReader("LodgedHours.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if(data.length == 3) {
                    String name = data[0];
                    boolean hasLoggedHours = Boolean.parseBoolean(data[1]);
                    double hoursWorked = Double.parseDouble(data[2]);


                    //check if the employee is in the ptEmployeeData map
                    if (ptEmployeeData.containsKey(name)) {
                        PartTimeEmployee emp = ptEmployeeData.get(name);
                        emp.setLodgedHours(hasLoggedHours);
                        emp.setHoursWorked(hoursWorked);//set the lodged hours status for the employee

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



