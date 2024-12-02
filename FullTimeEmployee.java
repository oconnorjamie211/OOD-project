public class FullTimeEmployee extends Employee {
    private String payScale;
    private double grossSalary;
    private String netPay;

    /**
     * Constructor for a full time employee
     * @param name stores FullTimeEmployee name as string
     * @param jobtitle stores FullTimeEmployee job title as string
     * @param ppsn stores FullTimeEmployee ppsn as string
     * @param workId stores FullTimeEmployee work id as string
     * @param iban stores FullTimeEmployee iban as string
     * @param address stores FullTimeEmployee address as string
     * @param phoneNumber stores FullTimeEmployee phone number as string
     * @param emailAddress stores FullTimeEmployee email address as string
     * @param payScale stores FullTimeEmployee pay scale as string
     */
    public FullTimeEmployee(String name, String jobtitle, String ppsn, String workId, String iban, String address, String phoneNumber, String emailAddress, String payScale) {
        super(name, jobtitle, ppsn, workId, iban, address, phoneNumber, emailAddress);
        this.payScale = payScale;
    }

    /**
     * this calls a reader that reads a csv called "Project Data scales(Sheet1).csv" and stores the grossSalary of the employee
     * in this class
     * @param employee object that is used to find the grossSalary
     */
    public void readGrossSalary(FullTimeEmployee employee) {

        this.grossSalary = CSVReader.getSalary(employee);
    }

    /**
     * getter for grossSalary
     * @return double value grossSalary
     */
    public double getGrossSalary() {
        return grossSalary;
    }

    /**
     * getter for payScale
     * @return String value payScale
     */
    public String getPayScale() {
        return payScale;
    }

    /**
     * a setter for payScale
     * @param payScale value payScale is being set to
     */
    public void setPayScale(String payScale) {
        this.payScale = payScale;
    }

    /**
     * toString method to display all the personal details of a Full Time Employee
     * @return a String that is displaying all the details of a full time employee
     */
    @Override
    public String toString() {
        return "Full-Time Employee {"
                + "\nName: " + getName()
                + "\nAddress: " + getAddress()
                + "\nPhone Number: " + getPhoneNumber()
                + "\nEmail Address: " + getEmailAddress()
                + "\nPPSN: " + getPpsn()
                + "\nIBAN: " + getIban()
                + "\nJob Title: " + getJobTitle()
                + "\nPay Scale: " + payScale
                + "\n}";
    }
}
