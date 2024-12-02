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

    /**
     * Constructor for FullTimeEmployees that gets the gross salary, pay week, and runs deduction calculations on the
     * FullTimeEmployee
     * @param employee FullTimeEmployee object which deductions are calculated for
     */
    public Deductions(FullTimeEmployee employee) {
        this.grossSalary = employee.getGrossSalary();
        this.PayWeeks = PayWeeksCalculation();
        this.calculateDeductions(employee);
    }

    /**
     * Constructor for PartTimeEmployee that gets the gross pay by multiplying hoursWorked and hourlyRate, pay week, and runs deduction calculations on the
     * PartTimeEmployee
     * @param employee PartTimeEmployee object which deductions are calculated for
     */
    public Deductions(PartTimeEmployee employee, double hourlyRate) {
        this.grossPay = employee.getHoursWorked() * hourlyRate;
        this.PayWeeks = PayWeeksCalculation();
        this.calculateDeductions(employee);
    }

    /**
     * due to their being 52 weeks in the year if you try and divide it by 12, to spread those 52 weeks evenly you
     * would be left with 4 weeks.to use the remaining 4 weeks, every third month is a 5 week pay month.
     * @return 5 it returns 5 if the current month is january,april,july, or october
     * @return 4 it returns 4 if the current month is not january,april,july, or october
     */
    public int PayWeeksCalculation() {
        Month currentMonth = LocalDate.now().getMonth();
        if (currentMonth == Month.JANUARY || currentMonth == Month.APRIL || currentMonth == Month.JULY || currentMonth == Month.OCTOBER) {
            return 5;
        } else {
            return 4;
        }
    }

    /**
     * method calculates the grossPay monthly
     * @return returns a double grossPay that is storing the monthly gross pay rounded to 2 decimal places
     */
    public double getgrossPay() {
        if (PayWeeks == 4) {//if 4 week pay cycle
            grossPay = ((grossSalary / 52) * 4);
        } else if (PayWeeks == 5) {// if 5 week pay cycle
            grossPay = ((grossSalary / 52) * 5);
        }
        return  Math.round(grossPay * 100.0) / 100.0;//rounded 2 decimal places
    }

    /**
     * this method gets gross pay for a partTimeEmployee rounded 2 decimal places
     * @return grossPay as a double rounded 2 decimal places for a PartTimeEmployee
     */
    public double getPTgrossPay(){
        return Math.round(grossPay * 100.0) / 100.0;
    }

    /**
     * Gets the IncomeTax on grossPay, and is calculated according to revenue
     * @return a double IncomeTax rounded 2 decimal places
     */
    public double getIncomeTax() {
        if (grossPay <= 42000){
            IncomeTax = grossPay * 0.20;
        } else {
            IncomeTax = ((grossPay - 42000) * 0.4) + (grossPay * 0.20);
        }
        return Math.round(IncomeTax * 100.0) / 100.0;
    }

    /**
     * Gets the PRSI tax on grosspay calculated according to revenue
     * @return a double PRSI rounded 2 decimal places
     */
    public double getPRSI () {
        if (PayWeeks == 4) {
            if (grossPay / 4 <= 352) {
                PRSI = 0;
            } else if (grossPay / 4 >= 352.01) {
                PRSI = (grossPay * .041);
            }
        }else if (PayWeeks == 5) {
            if (grossPay/5 <= 352){
                PRSI = 0;
            }
            else if (grossPay / 4 >= 352.01){
                PRSI = 0.041;
            }
        }
        return  Math.round(PRSI * 100.0) / 100.0;
    }

    /**
     * Gets the USC tax on grossPay according to revenue
     * @return a double value USC rounded 2 decimal places
     */
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
        return  Math.round(USC * 100.0) / 100.0;
    }

    /**
     * getter for health insurance price
     * @return a double health insurance rounded 2 decimal places
     */
    public double getHealthInsurance() {
        HealthInsurance = 40;
        return Math.round(HealthInsurance * 100.0) / 100.0;
    }

    /**
     * getter for union fees
     * @return a double value of union fees rounded 2 decimal places
     */
    public double getUnionFees() {
        UnionFees = 25;
        return Math.round(UnionFees * 100.0) / 100.0;
    }

    /**
     * gets the total deductions by adding all taxes and fees
     * @return returns a double value of TotalDeductions rounded to 2 decimal places
     */
    public double getTotalDeductions() {
        TotalDeductions = IncomeTax + PRSI + USC + HealthInsurance + UnionFees;
        return Math.round(TotalDeductions * 100.0) / 100.0;
    }

    /**
     * gets the net Pay by subtracting the sum of deductions from grosspay to get the net pay
     * @return a double value of netpay rounded to 2 decimal places
     */
    public double getNetPay() {
        NetPay = grossPay - (IncomeTax + PRSI + USC + HealthInsurance + UnionFees);
        return Math.round(NetPay * 100.0) / 100.0;
    }

    /**
     * Method that calculates all deductions for a FullTimeEmployee object
     * @param employee FullTimeEmployee object to be used to calculate total deductions
     */
    public void calculateDeductions(FullTimeEmployee employee) {
        this.grossSalary = employee.getGrossSalary();
        if (grossPay <= 42000) {
            IncomeTax = grossPay * 0.20;
        } else {
            IncomeTax = ((grossPay - 42000) * 0.4) + (grossPay * 0.20);
        }


        if (PayWeeks == 4) {
            if (grossPay / 4 <= 352) {
                PRSI = 0;
            } else if (grossPay / 4 >= 352.01) {
                PRSI = 0.041;
            }
        } else if (PayWeeks == 5) {
            if (grossPay / 5 <= 352) {
                PRSI = 0;
            } else if (grossPay / 4 >= 352.01) {
                PRSI = 0.041;
            }
        }

        if (grossPay <= 12012) {
            USC = grossPay * 0.005;
        } else if (grossPay <= 25760) {
            USC = ((grossPay - 12012) * 0.02) + 60.06;
        } else if (grossPay <= 70044) {
            USC = ((grossPay - 25760) * 0.04) + 274.96 + 60.06;
        } else if (grossPay > 70044) {
            USC = ((grossPay - 70044) * 0.08) + 1771.36 + 274.96 + 60.06;
        }
        HealthInsurance = 40;
        UnionFees = 25;

        TotalDeductions = IncomeTax + PRSI + USC + HealthInsurance + UnionFees;
        NetPay = Math.round((grossPay - TotalDeductions) * 100.0) / 100.0;
    }
    /**
     * Method that calculates all deductions for a PartTimeEmployee object
     * @param employee PartTimeEmployee object to be used to calculate total deductions
     */
    public void calculateDeductions(PartTimeEmployee employee) {
        this.grossSalary = employee.getHoursWorked();//Needs to be changed to hours x hourlyrate
        if (grossPay <= 42000) {
            IncomeTax = grossPay * 0.20;
        } else {
            IncomeTax = ((grossPay - 42000) * 0.4) + (grossPay * 0.20);
        }

        if (PayWeeks == 4) {
            if (grossPay / 4 <= 352) {
                PRSI = 0;
            } else if (grossPay / 4 >= 352.01) {
                PRSI = 0.041;
            }
        } else if (PayWeeks == 5) {
            if (grossPay / 5 <= 352) {
                PRSI = 0;
            } else if (grossPay / 4 >= 352.01) {
                PRSI = 0.041;
            }
        }

        if (grossPay <= 12012) {
            USC = grossPay * 0.005;
        } else if (grossPay <= 25760) {
            USC = ((grossPay - 12012) * 0.02) + 60.06;
        } else if (grossPay <= 70044) {
            USC = ((grossPay - 25760) * 0.04) + 274.96 + 60.06;
        } else if (grossPay > 70044) {
            USC = ((grossPay - 70044) * 0.08) + 1771.36 + 274.96 + 60.06;
        }

        HealthInsurance = 40;
        UnionFees = 25;

        TotalDeductions = IncomeTax + PRSI + USC + HealthInsurance + UnionFees;
        NetPay = Math.round((grossPay - TotalDeductions) * 100.0) / 100.0;
    }


    /**
     * method that displays the deductions
     * @return returns the deductions in a string format
     */
    @Override
    public String toString() {
        return "Deductions: "
                + "\nGross Salary: " + grossSalary
                + "\nIncome Tax: " + IncomeTax
                + "\nPRSI: " + PRSI
                + "\nUSC: " + USC
                + "\nHealth Insurance: " + HealthInsurance
                + "\nUnion Fees: " + UnionFees
                + "\nTotal Deductions: " + TotalDeductions
                + "\nNet Pay: " + NetPay;
    }
}
