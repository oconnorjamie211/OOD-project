import java.util.ArrayList;

public abstract class Employee {
    private String name;
    private String jobtitle;
    private String ppsn;
    private String workId;
    private String iban;
    private String address;
    private String phoneNumber;
    private String emailAddress;
    private ArrayList<Payslip> payslips;

    /**
     * Constructor for Employee
     * @param name stores employee name
     * @param jobtitle stores employee job title
     * @param ppsn stores employee ppsn also used as password in code
     * @param workId stores employee work id
     * @param iban stores employee iban
     * @param address stores employee address
     * @param phoneNumber stores employee phone number
     * @param emailAddress stores employee email address
     */
    public Employee(String name, String jobtitle, String ppsn, String workId, String iban, String address, String phoneNumber, String emailAddress){
        this.name = name;
        this.jobtitle = jobtitle;
        this.ppsn = ppsn; //ppsn is also password
        this.workId = workId;
        this.iban = iban;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.payslips = new ArrayList<>();
    }


    /**
     * a method that adds a payslip to an employees own ArrayList of payslips that hold payslip objects
     * @param payslip payslip object that is to be added to the list of payslips
     */
    public void addPayslip(Payslip payslip) {
        payslips.add(payslip);
    }

    /**
     *getter for name
     * @return returns name as a string
     */
    public String getName() { return name; }

    /**
     * getter for JobTitle
     * @return returns JobTitle as a string
     */
    public String getJobTitle() { return jobtitle; }

    /**
     * getter for ppsn
     * @return returns ppsn as a string
     */
    public String getPpsn() { return ppsn; }

    /**
     * getter for Id
     * @return returns Id as a string
     */
    public String getId() { return workId; }
    /**
     * getter for iban
     * @return returns iban as a string
     */
    public String getIban() { return iban; }
    /**
     * getter for address
     * @return returns address as a string
     */
    public String getAddress() { return address; }
    /**
     * getter for phone number
     * @return returns phone number as a string
     */
    public String getPhoneNumber() { return phoneNumber; }
    /**
     * getter for Email address
     * @return returns Email address as a string
     */
    public String getEmailAddress() { return emailAddress; }
}
