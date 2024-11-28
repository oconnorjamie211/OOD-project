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
    private double grossSalary;
    private Date dateOfPayslip;


    public Payslip(FullTimeEmployee employee){
        this.name = employee.getName();
        this.jobTitle = employee.getJobTitle();
        this.workId = employee.getId();
        this.ppsn = employee.getPpsn();
        this.iban = employee.getIban();
        this.payScale = employee.getPayScale();
        this.dateOfPayslip = new Date();

    }




    public String toString(){
        return"PaySlip: " +
                ""
    }

}
