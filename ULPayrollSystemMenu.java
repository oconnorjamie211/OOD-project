import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

    /**
     * Constructs a ULPayrollSystemMenu object.
     */
    public ULPayrollSystemMenu() {
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
            String input = in.nextLine().toUpperCase();

            if (input.equals("E")) {
                System.out.println("Employee functionality not yet implemented.");
            }

            //admin functionality
            else if(input.equals("A")) {
                //loading credentials from the csv for password and username
                String filePath1 = "adminCredentials.csv";
                credentialsReader(filePath1);
                //calls login function bringing up more text and if the
                //login is successful it will run the if otherwise runs the else
                if (login()) {
                    //runs this if login successful
                    System.out.println("Access granted. Admin functionality not implemented");

                } else {
                    System.out.println("Access denied.");
                } //runs if denied
            }

            //runs if input is h
            else if (input.equals("H")) {
                //loads csv file passwords and usernames
                String filePath = "humanResourcesCredentials.csv";
                credentialsReader(filePath);

                if (login()) {
                    System.out.println("Access granted. Human Resources function not yet implemented");
                } else {
                    System.out.println("Access denied.");
                }
            }
                //quitting session
            else if (input.equals("Q")) {
                //setting session false so it will terminate while loop
                session = false;
            }
            //if an input was incorrect
            else {
                System.out.println("Invalid option. Please try again.");
            }
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
    }

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
}
