import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Menu for UL Payroll system
 */
public class ULPayrollSystemMenu {
    //creating scanner for use in code
    private Scanner in;
    //creating a hash map as it will keep username and password linked avoiding errors
    private static Map<String, String> credentials = new HashMap<>();
    private static final String EMPLOYEE_FILE = "employeeData.csv";
    private Map<String, Employee> employees;


    /**
     * Constructs a ULPayrollSystemMenu object.
     */
    public ULPayrollSystemMenu() {
        employees = new HashMap<>();
        loadEmployees();
        in = new Scanner(System.in);
    }

    /**
     * Runs the system.
     */
    public void run() {
        //keeps the system running
        boolean session = true;
        while (session) {

            //prints options
            System.out.println("E)mplyoee A)dmin H)uman Resources Q)uit");
            //takes input and makes sure it is made uppercase to avoid case errors
            String input = in.nextLine().toUpperCase().trim();
            //takes input of e
            if (input.equals("E")) {
                boolean empSession = true;
                while (empSession) {
                    System.out.print("Employee Login type Q to quit\nEnter your name: ");
                    String enteredName = in.nextLine().trim();
                    if (enteredName.equalsIgnoreCase("Q")) {
                        empSession = false;
                    } else {
                        Employee emp = employees.get(enteredName);

                        if (emp != null) {
                            System.out.println("Login successful! Welcome, " + emp.getName());
                            boolean empSubSession = true;

                            while (empSubSession) {
                                System.out.println("P)ersonal Details V)iew Payslips Q)uit");
                                String choice = in.nextLine().toUpperCase().trim();
                                if (choice.equals("P")) {
                                    System.out.println(emp.toString());
                                } else if (choice.equals("V")) {
                                    System.out.println("to be implemented");
                                } else if (choice.equals("Q")) {
                                    empSubSession = false;
                                    empSession = false;
                                }

                            }
                        } else {
                            System.out.println("Employee does not exist. Please try again.");
                        }
                    }
                }
                //admin functionality
            }else if(input.equals("A")) {
                boolean adminSession = true;
                while (adminSession) {
                    String adminPassword = "123456789";
                    System.out.print("Admin Login type Q to quit \nEnter Admin Password: ");
                    input = in.nextLine();
                    System.out.println("");

                    //login is successful it will run the if otherwise runs the else
                    if (adminPassword.equals(input)) {
                        //runs this if login successful
                        System.out.println("A)dd Employee Q)uit");
                        input = in.nextLine().toUpperCase();
                        if (input.equals("A")) {
                            adminCreateEmployee(in);

                        } else if (input.equals("Q")) {
                            adminSession = false;
                        } else {
                            System.out.println("Invalid input");
                        }

                    } else if(input.equalsIgnoreCase("Q")) {
                        adminSession = false;

                    } else {
                        System.out.println("Access Denied, try again!");
                    }
                }
            }
            //runs if input is h
            else if (input.equals("H")) {
                boolean hrSession = true;
                while(hrSession) {
                    //loads csv file passwords and usernames
                    //String filePath = "humanResourcesCredentials.csv";
                    //credentialsReader(filePath);
                    String hrPassword = "hr1234567!";
                    System.out.print("Human Resources Login type Q to quit\nEnter Human Resources Password: ");
                    input = in.nextLine();
                    System.out.println("");
                    if (input.equals(hrPassword)) {
                        System.out.println("P)romote Employee Q)uit");
                        input = in.nextLine().toUpperCase();
                        if (input.equals("P")) {
                            System.out.println("Waiting on code.");
                        } else if (input.equals("Q")) {
                            hrSession = false;
                        } else {
                            System.out.println("Invalid input");
                        }

                    }else if(input.equalsIgnoreCase("q")){
                        hrSession = false;
                    }else {
                        System.out.println("Access denied.");
                    }
                }
            }
                //quitting session
            else if (input.equals("Q")) {
                //setting session false so it will terminate while loop
                session = false;
                saveEmployees();
            }
            //if an input was incorrect
            else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }













    public void employeeLogin(){

    }
    //add a quit funcitnoality
    public void adminCreateEmployee(Scanner scanner){
        System.out.println("Creating An Employee");
        System.out.println("Is this employee F)ull-Time or P)art-Time");
        String input = scanner.nextLine().toUpperCase();
        if (input.equals("F") || input.equals("P")){
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Job Title: ");
            String jobTitle = scanner.nextLine();
            System.out.print("PPSN: ");
            String ppsn = scanner.nextLine();
            System.out.print("Work ID: ");
            String workId = scanner.nextLine();
            System.out.print("IBAN: ");
            String iban = scanner.nextLine();
            System.out.print("Address: ");
            String address = scanner.nextLine();
            System.out.print("Phone Number: ");
            String phoneNumber = scanner.nextLine();
            System.out.print("Email: ");
            String emailAddress = scanner.nextLine();
            if(input.equals("F")){
                System.out.print("Pay Scale: ");
                String payScale = scanner.nextLine();
                FullTimeEmployee emp = new FullTimeEmployee(name, jobTitle, ppsn, workId, iban, address, phoneNumber, emailAddress, payScale);
                employees.put(name, emp);
                saveEmployees();
                System.out.println("Full-Time Employee created successfully.");
            } else{
               PartTimeEmployee emp = new PartTimeEmployee(name, jobTitle, ppsn, workId, iban, address, phoneNumber, emailAddress);
                employees.put(name, emp);
                saveEmployees();
                System.out.println("Part-Time Employee created successfully.");
            }
        }

    }

    public void loadEmployees() {

        try (Scanner scanner = new Scanner(new File(EMPLOYEE_FILE))) {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                if (data.length > 8){
                    employees.put(data[0], new FullTimeEmployee(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8]));
                } else {
                    //changing the first data point chnages how emplyees are found
                    employees.put(data[0], new PartTimeEmployee(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7]));
                }

            }
        } catch (IOException e) {
            System.out.println("No existing employee data found.");
        }
    }

    public void saveEmployees() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(EMPLOYEE_FILE))) {
            writer.println("Name, JobTitle, Ppsn, Password, Id, IBAN, Address, Phone Number, Email, PayScale");
            for (Employee emp : employees.values()) {
                if(emp instanceof FullTimeEmployee){
                    writer.println(String.join(",", emp.getName(), emp.getJobTitle(), ""+emp.getPpsn(), emp.getPassword(),
                            ""+emp.getId(), ""+emp.getIban(), emp.getAddress(), emp.getPhoneNumber(), emp.getEmailAddress(),  ((FullTimeEmployee) emp).getPayScale()));
                } else if (emp instanceof PartTimeEmployee) {
                    writer.println(String.join(",", emp.getName(), emp.getJobTitle(), ""+emp.getPpsn(), emp.getPassword(),
                            ""+emp.getId(), ""+emp.getIban(), emp.getAddress(), emp.getPhoneNumber(), emp.getEmailAddress()));
                }

            }
        } catch (IOException e) {
            System.out.println("Failed to save employee data.");
        }
    }


/*
    //method to load usernames and passwords from the CSV file
    private static void credentialsReader(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            //skip the header line
            br.readLine();
            //runs when there is something in the csv
            while ((line = br.readLine()) != null) {
                //breaks up values stored in arrays
                String[] values = line.split(",");
                //validations making sure theres only 2 in the array
                if (values.length == 2) {
                    //setting username and password values
                    String username = values[0].trim();
                    String password = values[1].trim();
                    //putting them in a hash map where they are stored together preventing errors
                    credentials.put(username, password);
                    //print loaded credentials to verify for testing reasons
                    System.out.println("Loaded username: " + username + ", password: " + password);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }



    //method to handle login process
    private boolean login() {
        String username;
        String password;

        //prompt for username with quit option
        while (true) {
            System.out.print("Enter username (or Q to quit): ");
            username = in.nextLine().trim();

            if (username.equalsIgnoreCase("Q")) {
                System.out.println("Exiting login.");
                return false;
            }

            //check if username exists
            if (credentials.containsKey(username)) {
                //it breaks if and jumps to password while loop
                break;
            } else {
                System.out.println("Username not found. Please try again.");
            }
        }

        //prompt for password with quit option
        while (true) {
            System.out.print("Enter password (or Q to quit): ");
            password = in.nextLine().trim();

            if (password.equalsIgnoreCase("Q")) {
                System.out.println("Exiting login.");
                return false;
            }

            //validate password for the given username
            if (credentials.get(username).equals(password)) {
                System.out.println("Login successful!");
                return true;
            } else {
                System.out.println("Incorrect password. Please try again.");
            }
        }
    }*/
}
