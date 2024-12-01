import java.time.Month;
import java.time.LocalDate;
public class Deductions {
    private double grossSalary;
    private double grossPay;
    private double IncomeTax;
    private double PRSI;
    private double USC;
    private double TotalDeductions;
    private double NetPay;
    private double HealthInsurance;
    private double UnionFees;
    private int PayWeeks;

    public Deductions(Employee employee, int PayWeeks) {
        this.grossSalary = employee.getgrossSalary();
        this.PayWeeks = PayWeeksCalculation();
    }

    public int PayWeeksCalculation() {
        Month currentMonth = LocalDate.now().getMonth();
        if(currentMonth == Month.JANUARY || currentMonth == Month.APRIL || currentMonth == Month.JULY || currentMonth == Month.OCTOBER){
            return 5;
        }else {
            return 4;
        }
    }

    public double getgrossPay(){
        if (PayWeeks == 4){
            grossPay = ((grossSalary/52) * 4);
        }else if (PayWeeks == 5){
            grossPay = ((grossSalary/52) * 5);
        }
        return grossPay;
    }

    public double getIncomeTax() {
        if (grossPay <= 42000){
            IncomeTax = grossPay * 0.20;
        } else {
            IncomeTax = ((grossPay - 42000) * 0.4) + (grossPay * 0.20);
        }
        return IncomeTax;
    }

    public double getPRSI (){
        if (PayWeeks == 4) {
            if (grossPay/4 <= 352) {
                PRSI = 0;
            } else if (grossPay / 4 >= 352.01) {
                PRSI = 0.041;
            }
        }
        else if (PayWeeks == 5) {
            if (grossPay/5 <= 352){
                PRSI = 0;
            }
            else if (grossPay / 4 >= 352.01){
                PRSI = 0.041;
            }
        }
        return PRSI;
    }

    public double getUSC() {
        if (grossPay <= 12012) { //First threshold for USC
            USC = grossPay * 0.005;
        } else if (grossPay <= 25760) {// Second threshold for USC
            USC = ((grossPay - 12012) * 0.02) + 60.06; //Second bracket rate plus the 0.5% of the first 12,012.
        } else if (grossPay <= 70044) { //Next USC threshold.
            USC = ((grossPay - 25760) * 0.04) +274.96 + 60.06;
        }
        else if (grossPay > 70044 ) {
            USC = ((grossPay - 70044) * 0.08) + 1771.36 + 274.96 +60.06;
        }
        return USC;
    }

    public double getHealthInsurance() {
        HealthInsurance = 40;
        return HealthInsurance;
    }

    public double getUnionFees() {
        UnionFees = 25;
        return UnionFees;
    }

    public double getTotalDeductions() {
        TotalDeductions = IncomeTax + PRSI + USC + HealthInsurance + UnionFees;
        return TotalDeductions;
    }

    public double getNetPay() {
        NetPay = grossPay - (IncomeTax + PRSI + USC + HealthInsurance + UnionFees);
        return NetPay;
    }

    public String toString() {
        return "Deductions: "
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
