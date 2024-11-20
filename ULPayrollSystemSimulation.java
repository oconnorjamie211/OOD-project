import java.io.IOException;

/**
 This program simulates a UL Payroll System.
 */
public class ULPayrollSystemSimulation {

    public static void main(String[] args)
                throws IOException
    {
        /**
         VendingMachine machine = new VendingMachine();
         VendingMachineMenu menu = new VendingMachineMenu();
         menu.run(machine);
         */
        ULPayrollSystemMenu menu = new ULPayrollSystemMenu();
        menu.run();
    }
}

