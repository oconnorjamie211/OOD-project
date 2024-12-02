import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class PartTimeEmployee extends Employee {
    private double hoursWorked;
    private double hourlyRate;
    private String payScale;
    private boolean hasLodgedHours;

    /**
     * Constructor to create a PartTimeEmployee object
     * @param name stores PartTimeEmployee name as a string
     * @param jobtitle stores PartTimeEmployee job title as a string
     * @param ppsn stores PartTimeEmployee ppsn as a string
     * @param workId stores PartTimeEmployee work id as a string
     * @param iban stores PartTimeEmployee iban as a string
     * @param address stores PartTimeEmployee address as a string
     * @param phoneNumber stores PartTimeEmployee phone number as a string
     * @param emailAddress stores PartTimeEmployee email address as a string
     * @param payScale stores PartTimeEmployee pay scale as a string
     */
    public PartTimeEmployee(String name, String jobtitle, String ppsn, String workId, String iban, String address, String phoneNumber, String emailAddress, String payScale) {
        super(name, jobtitle, ppsn, workId, iban, address, phoneNumber, emailAddress);
        this.payScale = payScale;
        this.hasLodgedHours = false; // Default to no hours submitted
        this.hoursWorked = 0; // Initialize hours worked to 0
    }

    /**
     * method calling a csv reader to get the hourly rate of a part-time employee
     * @param employee employee object that is getting its hourly rate
     */
    public void readHourlyRate(PartTimeEmployee employee) {

        this.hourlyRate = CSVReader.getHourlyRate(employee);
    }

    /**
     * setter that can change pay scale
     * @param payScale new payScale value
     */
    public void setPayScale(String payScale){
        this.payScale = payScale;
    }

    /**
     * getter for hoursWorked
     * @return a double value of hoursWokred
     */
    public double getHoursWorked() {
        return hoursWorked;
    }

    /**
     * setter that can change hoursworked
     * @param hoursWorked new value for hoursWorked
     */
    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }


    /**
     * setter to change boolean value of logded hours which is used to track wether PartTimeEmployees submit pay claims before
     * the second friday of the month
     * @param hasLodgedHours new boolean value for lodged hours
     */
    public void setLodgedHours(boolean hasLodgedHours) {
        this.hasLodgedHours = hasLodgedHours;
    }

    /**
     * Method to check if it's before the second Friday of the month
     * @return returns a boolean value of false if it is not the second friday of the month
     */
    public boolean isBeforeSecondFriday() {
        LocalDate today = LocalDate.now();
        LocalDate secondFriday = today.with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.FRIDAY));
        return today.isBefore(secondFriday.plusDays(1));
    }

    /**
     * getter to get hourlyRate
     * @return double value of hourlyRate
     */
    public double getHourlyRate() {
        return hourlyRate;
    }

    /**
     * setter for hourly rate to change hourly rate
     * @param hourlyRate new value for hourly rate
     */
    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    /**
     * getter for payScale
     *
     * @return String value of payScale
     */
    public String getPayScale() {
        return payScale;
    }


    /**
     * method to display part-time employee information as a string
     *
     * @return a string of all the information of a part-time employee
     */
    @Override
    public String toString() {
        return "Part-Time Employee {"
                + "\nName: " + getName()
                + "\nAddress: " + getAddress()
                + "\nPhone Number: " + getPhoneNumber()
                + "\nEmail Address: " + getEmailAddress()
                + "\nPPSN: " + getPpsn()
                + "\nIBAN: " + getIban()
                + "\nJob Title: " + getJobTitle()
                + "\nHours Worked: " + hoursWorked
                + "\nPay Scale: " + payScale
                + "\n}";
    }
}
