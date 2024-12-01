import javax.xml.namespace.QName;
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
    private double IncomeTax;
    private double PRSI;
    private double USC;
    private double TotalDeductions;
    private double NetPay;
    private double HealthInsurance;
    private double UnionFees;
    private int hoursWorked;
    private double hourlyRate;

    public Payslip(FullTimeEmployee employee,Deductions deductions) {
        this.name = employee.getName();
        this.jobTitle = employee.getJobTitle();
        this.workId = employee.getId();
        this.ppsn = employee.getPpsn();
        this.iban = employee.getIban();
        this.payScale = employee.getPayScale();
        this.dateOfPayslip = new Date();
        this.grossSalary = employee.getgrossSalary();
        this.grossPay = deductions.getgrossPay();
        this.IncomeTax = deductions.getIncomeTax();
        this.PRSI = deductions.getPRSI();
        this.USC = deductions.getUSC();
        this.TotalDeductions = deductions.getTotalDeductions();
        this.NetPay = deductions.getNetPay();
    }

    public Payslip(PartTimeEmployee employee,Deductions deductions) {
        this.name = employee.getName();
        this.jobTitle = employee.getJobTitle();
        this.workId = employee.getId();
        this.ppsn = employee.getPpsn();
        this.iban = employee.getIban();
        this.payScale = employee.getPayScale();
        this.dateOfPayslip = new Date();
        this.hourlyRate = employee.gethourlyRate();
        this.hoursWorked = employee.getHoursWorked();
        this.grossPay = deductions.getgrossPay();
        this.IncomeTax = deductions.getIncomeTax();
        this.PRSI = deductions.getPRSI();
        this.USC = deductions.getUSC();
        this.TotalDeductions = deductions.getTotalDeductions();
        this.NetPay = deductions.getNetPay();
    }




    public String toString(){
        return"PaySlip: "
                +"\nIssue Date of Payslip: " + dateOfPayslip
                +"\nName: "+ name
                +"\nJob Title: " + jobTitle
                +"\nPayScale: " + payScale
                +"\nWork ID: " + workId
                +"\nPPSN: " + ppsn
                +"\nIBAN: " + iban
                +"\nPayScale: " + payScale
                +"\nDate of Payslip: " + dateOfPayslip
                +"\nGross Salary:" + grossSalary
                +"\nIncome Tax:" + IncomeTax
                +"\nPRSI:" + PRSI
                +"\nUSC:" + USC
                +"\nHealth Insurance:" + HealthInsurance
                +"\nUnion Fees:" + UnionFees
                +"\nTotal Deductions:" + TotalDeductions
                +"\nNet Pay:" + NetPay;
    }

}

