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
    private static final String FTEMPLOYEE_FILE = "ftEmployeeData.csv";
    private static final String PTEMPLOYEE_FILE = "ptEmployeeData.csv";
    private Map<String, FullTimeEmployee> ftEmployees;
    private Map<String, PartTimeEmployee> ptEmployees;


    /**
     * Constructs a ULPayrollSystemMenu object.
     */
    public ULPayrollSystemMenu() {
        ftEmployees = new HashMap<>();
        ptEmployees = new HashMap<>();
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
                    System.out.println("Employee Login");
                    System.out.println("F)ull-Time Employee P)art-Time Employee Q)uit");
                    input = in.nextLine().toUpperCase();
                    if(input.equals("P")){
                        System.out.print("Enter Employee username(name): ");
                        String enteredName = in.nextLine().trim();
                        if (enteredName.equalsIgnoreCase("Q")) {
                            empSession = false;
                        } else {
                            PartTimeEmployee emp = ptEmployees.get(enteredName);

                            if (emp != null) {
                                String password = emp.getPassword();
                                System.out.print("Enter Password(ppsn): ");
                                input = in.nextLine();
                                if (password.equals(input)) {
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
                                }
                            } else {
                                System.out.println("Employee does not exist. Please try again.");
                            }
                        }
                    }else if(input.equals("F")){
                        System.out.print("Enter Employee username(name): ");
                        String enteredName = in.nextLine().trim();
                        if (enteredName.equalsIgnoreCase("Q")) {
                            empSession = false;
                        } else {
                            FullTimeEmployee emp = ftEmployees.get(enteredName);

                            if (emp != null) {
                                String password = emp.getPassword();
                                System.out.print("Enter Password(ppsn): ");
                                input = in.nextLine();
                                if (password.equals(input)) {
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
                                }
                            } else {
                                System.out.println("Employee does not exist. Please try again.");
                            }
                        }
                    } else if (input.equals("Q")) {
                        empSession = false;
                    }



                }
                //admin functionality
            } else if (input.equals("A")) {
                boolean adminSession = true;
                while (adminSession) {
                    String adminPassword = "123456789";
                    System.out.print("Admin Login type Q to quit \nEnter Admin Password: ");
                    input = in.nextLine();
                    System.out.println("");

                    //login is successful it will run the if otherwise runs the else
                    if (adminPassword.equals(input)) {
                        boolean adminSubSession = true;
                        while (adminSubSession) {
                            //runs this if login successful
                            System.out.println("A)dd Employee Q)uit");
                            input = in.nextLine().toUpperCase();
                            if (input.equals("A")) {
                                adminCreateEmployee(in);

                            } else if (input.equals("Q")) {
                                adminSubSession = false;
                            } else {
                                System.out.println("Invalid input");
                            }
                        }

                    } else if (input.equalsIgnoreCase("Q")) {
                        adminSession = false;

                    } else {
                        System.out.println("Access Denied, try again!");
                    }
                }
            }
            //runs if input is h
            else if (input.equals("H")) {
                boolean hrSession = true;
                while (hrSession) {
                    //loads csv file passwords and usernames
                    //String filePath = "humanResourcesCredentials.csv";
                    //credentialsReader(filePath);
                    String hrPassword = "hr1234567!";
                    System.out.print("Human Resources Login type Q to quit\nEnter Human Resources Password: ");
                    input = in.nextLine();
                    System.out.println("");
                    if (input.equals(hrPassword)) {
                        boolean hrSubSession = true;
                        while (hrSubSession) {
                            System.out.println("P)romote Employee Q)uit");
                            input = in.nextLine().toUpperCase();
                            if (input.equals("P")) {
                                System.out.println("Waiting on code.");
                            } else if (input.equals("Q")) {
                                hrSubSession = false;
                            } else {
                                System.out.println("Invalid input");
                            }
                        }

                    } else if (input.equalsIgnoreCase("q")) {
                        hrSession = false;
                    } else {
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


    public void employeeLogin() {

    }

    //add a quit funcitnoality
    public void adminCreateEmployee(Scanner scanner) {
        System.out.println("Creating An Employee");
        System.out.println("Is this employee F)ull-Time or P)art-Time");
        String input = scanner.nextLine().toUpperCase();
        if (input.equals("F") || input.equals("P")) {
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
            if (input.equals("F")) {
                System.out.print("Pay Scale: ");
                String payScale = scanner.nextLine();
                FullTimeEmployee emp = new FullTimeEmployee(name, jobTitle, ppsn, workId, iban, address, phoneNumber, emailAddress, payScale);
                ftEmployees.put(name, emp);
                saveEmployees();
                System.out.println("Full-Time Employee created successfully.");
            } else {
                PartTimeEmployee emp = new PartTimeEmployee(name, jobTitle, ppsn, workId, iban, address, phoneNumber, emailAddress);
                ptEmployees.put(name, emp);
                saveEmployees();
                System.out.println("Part-Time Employee created successfully.");
            }
        }

    }

    public void loadEmployees() {
        try (Scanner scanner = new Scanner(new File(FTEMPLOYEE_FILE))) {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                ftEmployees.put(data[0], new FullTimeEmployee(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8]));
            }
        } catch (IOException e) {
            System.out.println("No existing employee data found.");
        }

        try (Scanner scanner2 = new Scanner(new File(PTEMPLOYEE_FILE))) {
            if (scanner2.hasNextLine()) {
                scanner2.nextLine();
            }
            while (scanner2.hasNextLine()) {
                String[] data2 = scanner2.nextLine().split(",");
                ptEmployees.put(data2[0], new PartTimeEmployee(data2[0], data2[1], data2[2], data2[3], data2[4], data2[5], data2[6], data2[7]));
            }
        } catch (IOException e) {
            System.out.println("No existing employee data found.");
        }
    }

    public void saveEmployees() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FTEMPLOYEE_FILE))) {
            writer.println("Name, JobTitle, Ppsn, Password, Id, IBAN, Address, Phone Number, Email, Pay Scale");
            for (FullTimeEmployee emp : ftEmployees.values()) {
                writer.println(String.join(",", emp.getName(), emp.getJobTitle(), "" + emp.getPpsn(), emp.getPassword(),
                        "" + emp.getId(), "" + emp.getIban(), emp.getAddress(), emp.getPhoneNumber(), emp.getEmailAddress(), emp.getPayScale()));
            }
        } catch (IOException e) {
            System.out.println("Failed to save Full-Time Employee Data.");
        }


        try (PrintWriter writer2 = new PrintWriter(new FileWriter(PTEMPLOYEE_FILE))) {
            writer2.println("Name, JobTitle, Ppsn, Password, Id, IBAN, Address, Phone Number, Email");
            for (PartTimeEmployee emp : ptEmployees.values()) {
                writer2.println(String.join(",", emp.getName(), emp.getJobTitle(), "" + emp.getPpsn(), emp.getPassword(),
                        "" + emp.getId(), "" + emp.getIban(), emp.getAddress(), emp.getPhoneNumber(), emp.getEmailAddress()));
            }
        } catch (IOException e) {
            System.out.println("Failed to save Part-Time Employee Data.");
        }
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

