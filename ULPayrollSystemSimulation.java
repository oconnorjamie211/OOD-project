import java.io.IOException;

/**
 This program simulates a UL Payroll System.
 */
public class ULPayrollSystemSimulation {

    public static void main(String[] args)
                throws IOException
    {

        ULPayrollSystemMenu menu = new ULPayrollSystemMenu();
        menu.run();
        menu.generatePayslips();
        menu.savePayslips();

    }
}

