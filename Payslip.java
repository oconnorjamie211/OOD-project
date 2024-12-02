import java.util.Date;

public class Payslip {
    private Employee employee;
    private String name;
    private String jobTitle;
    private String workId;
    private String ppsn;
    private String iban;
    private String payScale;
    private Date dateOfPayslip;
    private double grossSalary;
    private double grossPay;
    private double PTgrossPay;
    private double IncomeTax;
    private double PRSI;
    private double USC;
    private double TotalDeductions;
    private double NetPay;
    private double HealthInsurance;
    private double UnionFees;
    private double hoursWorked;
    private double hourlyRate;

    public Payslip(){}

    public Payslip(FullTimeEmployee employee, Deductions deductions) {
        this.name = employee.getName();
        this.jobTitle = employee.getJobTitle();
        this.workId = employee.getId();
        this.ppsn = employee.getPpsn();
        this.iban = employee.getIban();
        this.payScale = employee.getPayScale();
        this.dateOfPayslip = new Date();
        this.grossSalary = employee.getGrossSalary();
        this.grossPay = deductions.getgrossPay();
        this.IncomeTax = deductions.getIncomeTax();
        this.PRSI = deductions.getPRSI();
        this.USC = deductions.getUSC();
        this.HealthInsurance = deductions.getHealthInsurance();
        this.UnionFees = deductions.getUnionFees();
        this.TotalDeductions = deductions.getTotalDeductions();
        this.NetPay = deductions.getNetPay();
    }

    public Payslip(PartTimeEmployee employee, Deductions deductions) {
        CSVReader reader = new CSVReader();

        this.name = employee.getName();
        this.jobTitle = employee.getJobTitle();
        this.workId = employee.getId();
        this.ppsn = employee.getPpsn();
        this.iban = employee.getIban();
        this.payScale = employee.getPayScale();
        this.dateOfPayslip = new Date();
        this.hourlyRate = employee.getHourlyRate();
        this.hoursWorked = employee.getHoursWorked();
        this.PTgrossPay = deductions.getPTgrossPay();
        this.IncomeTax = deductions.getIncomeTax();
        this.PRSI = deductions.getPRSI();
        this.USC = deductions.getUSC();
        this.HealthInsurance = deductions.getHealthInsurance();
        this.UnionFees = deductions.getUnionFees();
        this.TotalDeductions = deductions.getTotalDeductions();
        this.NetPay = deductions.getNetPay();
    }
    //getters

    public String getName() {
        return name;
    }
    public String getWorkId(){
        return workId;
    }
    public String getJobTitle(){
        return jobTitle;
    }
    public String getPayScale(){
        return payScale;
    }
    public Date getDateOfPayslip(){
        return dateOfPayslip;
    }

    public double getGrossSalary() {
        return grossSalary;
    }

    public double getGrossPay() {
        return grossPay;
    }

    public double getPTgrossPay() {
        return PTgrossPay;
    }

    public double getIncomeTax() {
        return IncomeTax;
    }

    public double getPRSI() {
        return PRSI;
    }

    public double getUSC() {
        return USC;
    }

    public double getHealthInsurance() {
        return HealthInsurance;
    }

    public double getUnionFees() {
        return UnionFees;
    }

    public double getTotalDeductions() {
        return TotalDeductions;
    }

    public double getNetPay() {
        return NetPay;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    // Setter methods
    public void setDateOfPayslip(Date dateOfPayslip) {
        this.dateOfPayslip = dateOfPayslip;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public void setPpsn(String ppsn) {
        this.ppsn = ppsn;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setPayScale(String payScale) {
        this.payScale = payScale;
    }

    public void setGrossSalary(double grossSalary) {
        this.grossSalary = grossSalary;
    }

    public void setGrossPay(double grossPay) {
        this.grossPay = grossPay;
    }

    public void setPTgrossPay(double grossPay){this.PTgrossPay = PTgrossPay;}

    public void setIncomeTax(double incomeTax) {
        this.IncomeTax = incomeTax;
    }

    public void setPRSI(double PRSI) {
        this.PRSI = PRSI;
    }

    public void setUSC(double USC) {
        this.USC = USC;
    }

    public void setHealthInsurance(double healthInsurance) {
        this.HealthInsurance = healthInsurance;
    }

    public void setUnionFees(double unionFees) {
        this.UnionFees = unionFees;
    }

    public void setTotalDeductions(double totalDeductions) {
        this.TotalDeductions = totalDeductions;
    }

    public void setNetPay(double netPay) {
        this.NetPay = netPay;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    @Override
    public String toString() {
        return "Payslip: "
                + "\nIssue Date of Payslip: " + dateOfPayslip
                + "\nName: " + name
                + "\nJob Title: " + jobTitle
                + "\nPayScale: " + payScale
                + "\nWork ID: " + workId
                + "\nPPSN: " + ppsn
                + "\nIBAN: " + iban
                + "\nDate of Payslip: " + dateOfPayslip
                + "\nGross Pay: " + grossPay
                + "\nIncome Tax: " + IncomeTax
                + "\nPRSI: " + PRSI
                + "\nUSC: " + USC
                + "\nHealth Insurance: " + HealthInsurance
                + "\nUnion Fees: " + UnionFees
                + "\nTotal Deductions: " + TotalDeductions
                + "\nNet Pay: " + NetPay;
    }



}
