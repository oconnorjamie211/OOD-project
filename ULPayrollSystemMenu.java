import java.io.*;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class ULPayrollSystemMenu {
    private Scanner in;
    private static final String FTEMPLOYEE_FILE = "ftEmployeeData.csv";
    private static final String PTEMPLOYEE_FILE = "ptEmployeeData.csv";
    private Map<String, FullTimeEmployee> ftEmployees;
    private HashMap<String, PartTimeEmployee> ptEmployees;
    private Map<String, List<Payslip>> ftPayslipsMap;
    private Map<String, List<Payslip>> ptPayslipsMap;

    public ULPayrollSystemMenu() {
        ftEmployees = new HashMap<>();
        ptEmployees = new HashMap<>();
        ftPayslipsMap = new HashMap<>();
        ptPayslipsMap = new HashMap<>();
        loadEmployeesAndPaySlips();
        CSVReader.loadEmployeeStatus(ptEmployees);
        in = new Scanner(System.in);

        // Automatically generate payslips on initialization
        generatePayslips();
    }

    public void run() {//Runs main method to display menu
        boolean session = true;
        while (session) {
            System.out.println("E)mployee A)dmin H)uman Resources Q)uit");
            String input = in.nextLine().toUpperCase().trim();
            if (input.equals("E")) {
                handleEmployeeLogin();
            } else if (input.equals("A")) {
                handleAdmin();
            } else if (input.equals("H")) {
                handleHR();
            } else if (input.equals("Q")) {
                session = false;
                saveEmployees(); // Save employees when quitting
                savePayslips(); // Save payslips when quitting
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /*
    Employee login methods
     */
    private void handleEmployeeLogin() {
        boolean empSession = true;
        while (empSession) {
            System.out.println("Employee Login");
            System.out.println("F)ull-Time Employee P)art-Time Employee Q)uit");
            String input = in.nextLine().toUpperCase();
            if (input.equals("P")) {
                processPartTimeEmployeeLogin();
            } else if (input.equals("F")) {
                processFullTimeEmployeeLogin();
            } else if (input.equals("Q")) {
                empSession = false;
            } else{
                System.out.println("Invalid input try again (only letters)");
            }
        }
    }

    private void processPartTimeEmployeeLogin() {
        System.out.print("Enter Employee username (name): ");
        String enteredName = in.nextLine().trim();
        if (enteredName.equalsIgnoreCase("Q")) {
            return;
        }
        PartTimeEmployee emp = ptEmployees.get(enteredName);
        if (emp != null) {
            handlePartTimeEmployeeDetails(emp);
        } else {
            System.out.println("Employee does not exist. Please try again.");
        }
    }

    private void processFullTimeEmployeeLogin() {
        System.out.print("Enter Employee username (name): ");
        String enteredName = in.nextLine().trim();
        if (enteredName.equalsIgnoreCase("Q")) {
            return;
        }
        FullTimeEmployee emp = ftEmployees.get(enteredName);
        if (emp != null) {
            handleFullTimeEmployeeDetails(emp);
        } else {
            System.out.println("Employee does not exist. Please try again.");
        }
    }

    private void handleFullTimeEmployeeDetails(FullTimeEmployee emp) {
        String password = emp.getPpsn();
        System.out.print("Enter Password (ppsn): ");
        String input = in.nextLine();
        if (password.equals(input)) {
            System.out.println("Login successful! Welcome, " + emp.getName());
            boolean empSubSession = true;
            while (empSubSession) {
                System.out.println("P)ersonal Details V)iew Payslips Q)uit");
                String choice = in.nextLine().toUpperCase().trim();
                if (choice.equals("P")) {
                    System.out.println(emp.toString());
                } else if (choice.equals("V")) {
                    viewPayslips(emp);
                } else if (choice.equals("Q")) {
                    empSubSession = false;
                }
            }
        } else {
            System.out.println("Incorrect password. Please try again.");
        }
    }

    private void handlePartTimeEmployeeDetails(PartTimeEmployee emp) {
        String password = emp.getPpsn();
        System.out.print("Enter Password (ppsn): ");
        String input = in.nextLine();
        if (password.equals(input)) {
            System.out.println("Login successful! Welcome, " + emp.getName());
            boolean empSubSession = true;
            while (empSubSession) {
                System.out.println("P)ersonal Details V)iew Payslips L)odge Hours Q)uit");
                String choice = in.nextLine().toUpperCase().trim();
                if (choice.equals("P")) {
                    System.out.println(emp.toString());
                } else if (choice.equals("V")) {
                    viewPayslips(emp);
                } else if (choice.equals("L")){
                    lodgeHours(emp);
                } else if (choice.equals("Q")) {
                    empSubSession = false;
                } else {
                    System.out.println("Invalid input try again (only letters)");
                }
            }
        } else {
            System.out.println("Incorrect password. Please try again.");
        }
    }

    private void lodgeHours(PartTimeEmployee emp) {
        boolean session = true;
        Scanner input = new Scanner(System.in);
        if(emp.isBeforeSecondFriday()){
            while (session) {
                Deductions payCycle = new Deductions(emp, emp.getHourlyRate());
                int payWeek = payCycle.PayWeeksCalculation();
                System.out.println("Please enter hours for this " + payWeek + " week pay week (or type 'Q' to exit):");

                String userInput = input.nextLine(); // Read input as a string

                // Check if user wants to quit
                if (userInput.equalsIgnoreCase("Q")) {
                    System.out.println("Exiting the session.");
                    session = false;
                } else {
                    try {
                        // Try to parse the input as a double
                        int hoursWorked = Integer.parseInt(userInput);
                        emp.setHoursWorked(hoursWorked);
                        emp.setLodgedHours(true);
                        // Save the updated status (whether hours were lodged)
                        CSVReader.saveEmployeeStatus(emp);
                        session = false;  //Exit the loop if valid input is entered
                    } catch (NumberFormatException e) {
                        // If parsing fails, show error message
                        System.out.println("Invalid input! Please enter a valid number for hours worked.");
                    }
                }
            }
        }else {
            System.out.println("Past the 2nd friday of the month wait for next month!");
        }

    }

    private void viewPayslips(Employee emp) {
        String workId = emp.getId();
        List<Payslip> payslips = emp instanceof FullTimeEmployee
                ? ftPayslipsMap.getOrDefault(workId, new ArrayList<>())
                : ptPayslipsMap.getOrDefault(workId, new ArrayList<>());

        if (payslips.isEmpty()) {
            System.out.println("No payslips found.");
            return;
        }

        boolean viewSession = true;
        while (viewSession) {
            System.out.println("V)iew Most Recent Payslip A)ll Historic Payslips Q)uit");
            String choice = in.nextLine().toUpperCase().trim();
            switch (choice) {
                case "V":
                    System.out.println(payslips.get(payslips.size() - 1)); // Most recent payslip
                    break;
                case "A":
                    for (Payslip payslip : payslips) {
                        System.out.println(payslip);
                    }
                    break;
                case "Q":
                    viewSession = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }
    /*
    Admin login methods
     */
    private void handleAdmin() {
        boolean adminSession = true;
        while (adminSession) {
            System.out.print("Admin Login type Q to quit \nEnter Admin Password: ");
            String input = in.nextLine();
            if (input.equals("ad123456789")) {
                boolean adminSubSession = true;
                while (adminSubSession) {
                    System.out.println("A)dd Employee Q)uit");
                    input = in.nextLine().toUpperCase();
                    if (input.equals("A")) {
                        adminCreateEmployee(in);
                    } else if (input.equals("Q")) {
                        adminSubSession = false;
                        adminSession = false;
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

    public void adminCreateEmployee(Scanner scanner) {
        boolean session = true;
        while(session){
            System.out.println("Creating An Employee");
            System.out.println("Is this employee F)ull-Time or P)art-Time or Q)uit");
            String input = scanner.nextLine().toUpperCase();
            if (input.equals("Q")){
                session = false;
            }
            if (input.equals("F") || input.equals("P")) {
                boolean subSession = true;
                while(subSession){
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Job Title: ");
                    String jobTitle = scanner.nextLine();
                    System.out.print("Pay Scale: ");
                    String payScale = scanner.nextLine();
                    if (!CSVReader.checkJobTitlePayScale(jobTitle, payScale)) {
                        System.out.println("Invalid Job Title or Pay Scale try again");
                        subSession = false;
                    } else {
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
                            FullTimeEmployee emp = new FullTimeEmployee(name, jobTitle, ppsn, workId, iban, address, phoneNumber, emailAddress, payScale);
                            ftEmployees.put(name, emp);
                            saveEmployees();
                            System.out.println("Full-Time Employee created successfully.");
                        } else {
                            PartTimeEmployee emp = new PartTimeEmployee(name, jobTitle, ppsn, workId, iban, address, phoneNumber, emailAddress, payScale);
                            ptEmployees.put(name, emp);
                            saveEmployees();
                            System.out.println("Part-Time Employee created successfully.");
                        }
                    }
                }
            }
        }
    }


    /*
    Hr login methods
     */
    private void handleHR() {
        boolean hrSession = true;
        while (hrSession) {
            System.out.print("Human Resources Login type Q to quit\nEnter Human Resources Password: ");
            String input = in.nextLine();
            if (input.equals("hr123456789")) {
                boolean hrSubSession = true;
                while (hrSubSession) {
                    System.out.println("P)romote Employee Q)uit");
                    input = in.nextLine().toUpperCase();
                    if (input.equals("P")) {
                        promoteEmployee();
                        break;
                    } else if (input.equals("Q")) {
                        hrSubSession = false;
                    } else {
                        System.out.println("Invalid input");
                    }
                }
            } else if (input.equalsIgnoreCase("Q")) {
                hrSession = false;
            } else {
                System.out.println("Access denied.");
            }
        }
    }

    public void promoteEmployee() {
        boolean session = true;
        while(session){
            System.out.println("Promote F)ull Time Employee or P)art Time Employee or Q)uit");
            String input = in.nextLine().trim().toUpperCase();
            if(input.equals("F")){
                System.out.print("Enter Employee Name to Promote: ");
                String name = in.nextLine().trim();
                FullTimeEmployee emp = ftEmployees.get(name);
                if (emp != null) {
                    boolean subSession = true;
                    while(subSession){
                        String ps = emp.getPayScale();
                        int payScale = Integer.parseInt(ps);
                        int newPayScale = payScale + 1;
                        System.out.println("Current Pay Scale: " + emp.getPayScale());
                        System.out.print("New Pay Scale: " + newPayScale);

                        String payScaleString = String.valueOf(newPayScale);
                        emp.setPayScale(payScaleString);
                        emp.readGrossSalary(emp);// Update salary based on new pay scale
                        if(emp.getGrossSalary()!=-1){
                            System.out.println("Promotion Successful! New Pay Scale: " + emp.getPayScale());
                            saveEmployees();
                            subSession = false;
                            session = false;
                        } else {
                            System.out.println("This employee is already at their max pay scale.");
                            session = false;
                            subSession = false;
                            emp.setPayScale(ps);
                            emp.readGrossSalary(emp);

                        }
                    }
                } else {
                    System.out.println("Employee not found.");
                }
            } else if (input.equals("P")) {
                System.out.print("Enter Employee Name to Promote: ");
                String name = in.nextLine().trim();
                PartTimeEmployee emp = ptEmployees.get(name);
                if (emp != null) {
                    boolean subSession1 = true;
                    while(subSession1){
                        String ps = emp.getPayScale();
                        int payScale = Integer.parseInt(ps);
                        int newPayScale = payScale + 1;
                        System.out.println("Current Pay Scale: " + emp.getPayScale());
                        System.out.print("New Pay Scale: " + newPayScale);

                        String payScaleString = String.valueOf(newPayScale);
                        emp.setPayScale(payScaleString);
                        emp.readHourlyRate(emp);// Update salary based on new pay scale
                        if(emp.getHourlyRate()!=-1){
                            System.out.println("Promotion Successful! New Pay Scale: " + emp.getPayScale());
                            saveEmployees();
                            subSession1 = false;
                            session = false;
                        } else {
                            System.out.println("This employee is already at their max pay scale.");
                            session = false;
                            subSession1 = false;
                            emp.setPayScale(ps);
                            emp.readHourlyRate(emp);
                        }
                    }
                } else {
                    System.out.println("Employee not found.");
                }
            } else if (input.equals("Q")) {
                session = false;
            } else {
                System.out.println("Invalid input");
            }
        }
    }


    /*
    CSV readers
     */
    public void loadEmployeesAndPaySlips() {
        // Load Full-Time Employees
        try (Scanner scanner = new Scanner(new File(FTEMPLOYEE_FILE))) {
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // Skip header
            }
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                if (data.length == 9) {
                    FullTimeEmployee employee = new FullTimeEmployee(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7],data[8]);
                    ftEmployees.put(data[0], employee); // Use work ID as the key
                } else {
                    System.err.println("Invalid full-time employee data: " + Arrays.toString(data));
                }
            }
        } catch (IOException e) {
            System.out.println("No existing full-time employee data found.");
        }

        // Load Part-Time Employees
        try (Scanner scanner2 = new Scanner(new File(PTEMPLOYEE_FILE))) {
            if (scanner2.hasNextLine()) {
                scanner2.nextLine(); // Skip header
            }
            while (scanner2.hasNextLine()) {
                String[] data2 = scanner2.nextLine().split(",");
                if (data2.length == 9) {
                    PartTimeEmployee employee = new PartTimeEmployee(data2[0], data2[1], data2[2], data2[3], data2[4], data2[5], data2[6], data2[7], data2[8]);
                    ptEmployees.put(data2[0], employee); // Use name as the key
                } else {
                    System.err.println("Invalid part-time employee data: " + Arrays.toString(data2));
                }
            }
        } catch (IOException e) {
            System.out.println("No existing part-time employee data found.");
        }

        // Load Full-Time Payslips
        try (Scanner scanner3 = new Scanner(new File("ftPayslips.csv"))) {
            if (scanner3.hasNextLine()) {
                scanner3.nextLine(); // Skip header
            }
            while (scanner3.hasNextLine()) {
                String[] data3 = scanner3.nextLine().split(",");
                if (data3.length == 14) {
                    Payslip payslip = new Payslip();
                    try {
                        payslip.setDateOfPayslip(new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH).parse(data3[4]));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    payslip.setWorkId(data3[0]);
                    payslip.setName(data3[1]);
                    payslip.setJobTitle(data3[2]);
                    payslip.setPayScale(data3[3]);
                    payslip.setGrossSalary(Double.parseDouble(data3[5]));
                    payslip.setGrossPay(Double.parseDouble(data3[6]));
                    payslip.setIncomeTax(Double.parseDouble(data3[7]));
                    payslip.setPRSI(Double.parseDouble(data3[8]));
                    payslip.setUSC(Double.parseDouble(data3[9]));
                    payslip.setHealthInsurance(Double.parseDouble(data3[10]));
                    payslip.setUnionFees(Double.parseDouble(data3[11]));
                    payslip.setTotalDeductions(Double.parseDouble(data3[12]));
                    payslip.setNetPay(Double.parseDouble(data3[13]));

                    ftPayslipsMap.computeIfAbsent(data3[0], k -> new ArrayList<>()).add(payslip);
                } else {
                    System.err.println("Invalid full-time payslip data: " + Arrays.toString(data3));
                }
            }
        } catch (IOException e) {
            System.out.println("No existing full-time payslip data found.");
        }

        // Load Part-Time Payslips
        try (Scanner scanner4 = new Scanner(new File("ptPayslips.csv"))) {
            if (scanner4.hasNextLine()) {
                scanner4.nextLine(); // Skip header
            }
            while (scanner4.hasNextLine()) {
                String[] data4 = scanner4.nextLine().split(",");
                if (data4.length == 15) {
                    Payslip payslip = new Payslip();
                    try {
                        payslip.setDateOfPayslip(new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH).parse(data4[4]));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    payslip.setWorkId(data4[0]);
                    payslip.setName(data4[1]);
                    payslip.setJobTitle(data4[2]);
                    payslip.setPayScale(data4[3]);
                    payslip.setHourlyRate(Double.parseDouble(data4[5]));
                    payslip.setHoursWorked(Double.parseDouble(data4[6]));
                    payslip.setGrossPay(Double.parseDouble(data4[7]));
                    payslip.setIncomeTax(Double.parseDouble(data4[8]));
                    payslip.setPRSI(Double.parseDouble(data4[9]));
                    payslip.setUSC(Double.parseDouble(data4[10]));
                    payslip.setHealthInsurance(Double.parseDouble(data4[11]));
                    payslip.setUnionFees(Double.parseDouble(data4[12]));
                    payslip.setTotalDeductions(Double.parseDouble(data4[13]));
                    payslip.setNetPay(Double.parseDouble(data4[14]));

                    ptPayslipsMap.computeIfAbsent(data4[0], k -> new ArrayList<>()).add(payslip);
                } else {
                    System.err.println("Invalid part-time payslip data: " + Arrays.toString(data4));
                }
            }
        } catch (IOException e) {
            System.out.println("No existing part-time payslip data found.");
        }
    }




    /*
    CSV writers
     */
    public void saveEmployees() {
        // Save Full-Time Employees
        try (PrintWriter writer = new PrintWriter(new FileWriter(FTEMPLOYEE_FILE))) {
            writer.println("Name,JobTitle,Ppsn,Id,IBAN,Address,PhoneNumber,Email,PayScale");
            for (FullTimeEmployee emp : ftEmployees.values()) {
                writer.println(String.join(",",
                        emp.getName(),
                        emp.getJobTitle(),
                        emp.getPpsn(),
                        emp.getId(),
                        emp.getIban(),
                        emp.getAddress(),
                        emp.getPhoneNumber(),
                        emp.getEmailAddress(),
                        emp.getPayScale()
                ));
            }
        } catch (IOException e) {
            System.out.println("Failed to save Full-Time Employee Data.");
        }

        // Save Part-Time Employees
        try (PrintWriter writer2 = new PrintWriter(new FileWriter(PTEMPLOYEE_FILE))) {
            writer2.println("Name,JobTitle,Ppsn,Id,IBAN,Address,PhoneNumber,Email,PayScale");
            for (PartTimeEmployee emp : ptEmployees.values()) {
                writer2.println(String.join(",",
                        emp.getName(),
                        emp.getJobTitle(),
                        emp.getPpsn(),
                        emp.getId(),
                        emp.getIban(),
                        emp.getAddress(),
                        emp.getPhoneNumber(),
                        emp.getEmailAddress(),
                        emp.getPayScale()
                ));
            }
        } catch (IOException e) {
            System.out.println("Failed to save Part-Time Employee Data.");
        }
    }

    public void savePayslips() {
        // Save Full-Time Payslips
        try (PrintWriter writer = new PrintWriter(new FileWriter("ftPayslips.csv"))) {
            writer.println("WorkId,Name,JobTitle,PayScale,DateOfPayslip,GrossSalary,GrossPay,IncomeTax,PRSI,USC,HealthInsurance,UnionFees,TotalDeductions,NetPay");
            for (List<Payslip> payslips : ftPayslipsMap.values()) {
                for (Payslip payslip : payslips) {
                    writer.println(String.join(",",
                            payslip.getWorkId(),
                            payslip.getName(),
                            payslip.getJobTitle(),
                            payslip.getPayScale(),
                            payslip.getDateOfPayslip().toString(),
                            String.valueOf(payslip.getGrossSalary()),
                            String.valueOf(payslip.getGrossPay()),
                            String.valueOf(payslip.getIncomeTax()),
                            String.valueOf(payslip.getPRSI()),
                            String.valueOf(payslip.getUSC()),
                            String.valueOf(payslip.getHealthInsurance()),
                            String.valueOf(payslip.getUnionFees()),
                            String.valueOf(payslip.getTotalDeductions()),
                            String.valueOf(payslip.getNetPay())
                    ));
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to save Full-Time Payslips.");
        }

        // Save Part-Time Payslips
        try (PrintWriter writer2 = new PrintWriter(new FileWriter("ptPayslips.csv"))) {
            writer2.println("WorkId,Name,JobTitle,PayScale,DateOfPayslip,HourlyRate,HoursWorked,GrossPay,IncomeTax,PRSI,USC,HealthInsurance,UnionFees,TotalDeductions,NetPay");
            for (List<Payslip> payslips : ptPayslipsMap.values()) {
                for (Payslip payslip : payslips) {
                    writer2.println(String.join(",",
                            payslip.getWorkId(),
                            payslip.getName(),
                            payslip.getJobTitle(),
                            payslip.getPayScale(),
                            payslip.getDateOfPayslip().toString(),
                            String.valueOf(payslip.getHourlyRate()),
                            String.valueOf(payslip.getHoursWorked()),
                            String.valueOf(payslip.getPTgrossPay()),
                            String.valueOf(payslip.getIncomeTax()),
                            String.valueOf(payslip.getPRSI()),
                            String.valueOf(payslip.getUSC()),
                            String.valueOf(payslip.getHealthInsurance()),
                            String.valueOf(payslip.getUnionFees()),
                            String.valueOf(payslip.getTotalDeductions()),
                            String.valueOf(payslip.getNetPay())
                    ));
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to save Part-Time Payslips.");
        }
    }


    /*
    Generates Payslips
     */
    public void generatePayslips() {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        CSVReader csvReader = new CSVReader();

        // Check if today is the 25th (or any specific date you choose)
        if (calendar.get(Calendar.DAY_OF_MONTH) == 25) {
            // Generate payslips for full-time employees
            for (FullTimeEmployee emp : ftEmployees.values()) {
                List<Payslip> payslips = ftPayslipsMap.getOrDefault(emp.getId(), new ArrayList<>());
                boolean payslipExists = payslips.stream().anyMatch(p -> isSameDay(p.getDateOfPayslip(), today));
                if (!payslipExists) {
                    emp.readGrossSalary(emp);
                    Deductions deductions = new Deductions(emp);
                    Payslip payslip = new Payslip(emp, deductions);
                    emp.addPayslip(payslip);
                    payslips.add(payslip);
                    ftPayslipsMap.put(emp.getId(), payslips);
                }
            }

            // Generate payslips for part-time employees with current claims
            for (PartTimeEmployee emp : ptEmployees.values()) {
                if (emp.hasLodgedHours()) {
                    List<Payslip> payslips = ptPayslipsMap.getOrDefault(emp.getId(), new ArrayList<>());
                    boolean payslipExists = payslips.stream().anyMatch(p -> isSameDay(p.getDateOfPayslip(), today));
                    if (!payslipExists) {
                        double hourlyRate = CSVReader.getHourlyRate(emp);
                        emp.setHourlyRate(hourlyRate);
                        Deductions deductions = new Deductions(emp, hourlyRate);
                        Payslip payslip = new Payslip(emp, deductions);
                        emp.addPayslip(payslip);
                        payslips.add(payslip);
                        ptPayslipsMap.put(emp.getId(), payslips);
                    }
                }
            }
        }

            // Save the updated employees with payslips to CSV files
            savePayslips();
        }


    private boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }


}










