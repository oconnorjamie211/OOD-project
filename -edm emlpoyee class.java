//-edm emlpoyee class
public class Employee {
    private String name;
    private String address;
    private int phoneNumber;
    private String emailAddress;
    private int ppsn;
    private String password;
    private int id;
    private int IBAN;
    private String jobtitle;
    private int payscale;
    private double hours=0;
    private double hourlyrate=0;
    private double totalpay;


   

    
   //password is defaulted as the ppsn
    public Employee(String name, String address, int phoneNumber, String emailAddress, int id, int ppsn, int IBAN, String jobtitle, int payscale, double hourlyrate){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.ppsn = ppsn;
        this.password =  String.valueOf(ppsn);
        this.id = id;
        this.IBAN = IBAN;
        this.jobtitle = jobtitle;
        this.payscale = payscale;
        this.hourlyrate = hourlyrate;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public int getPpsn() {
        return ppsn;
    }

    public int getIBAN() {
        return IBAN;
    }

    public double getHours() {
    return hours;

    }

    public double getTotalPay() {
       totalpay = hours * hourlyrate;
    return totalpay;
       
    }
    public double getHourlyRate() {
        return hourlyrate;
    
       
    }
    public String getJobTitle(){
      return jobtitle;



    }

    public int getPayscale(){
    return payscale;

    }
    
    public void changeName(String name){
        this.name = name;
            
            }

    
     public void changeAddress(String address){
        this.address = address;
                    
         }


     public void changePhoneNumber(int phoneNumber){
        this.phoneNumber = phoneNumber;
                
            }   
            
            
    public void changeEmailAddress(String emailAddress){
         this.emailAddress = emailAddress;
                    
            }



    public void changePassword(String password){
    this.password=password;
    
    }

    public void changeId(int id){
    this.id = id;
        
        }

    public void changePpsn(int ppsn){
    this.ppsn=ppsn;
                
       }

    public void changeIBAN(int IBAN){
    this.IBAN = IBAN;
    }

    public void changeJobTitle(String jobtitle)
   {this.jobtitle = jobtitle;
   }

   public void changeHours(double hours){
    this.hours = hours; 
    }

   

   












    


    @Override
    public String toString() {
        return "Employee {"
                + "\nName: " + name
                + "\nAddress: " + address
                + "\nPhone Number: " + phoneNumber
                + "\nEmail Address: " + emailAddress
                + "\nPassword: " + password
                + "\nID: " + id
                + "\nPPSN: " + ppsn
                + "\nIBAN: " +  IBAN
                + "\nJob Title: " + jobtitle
                + "\nPayscale: " + payscale
                + "\nHours: " + hours
                + "\nhourly rate: " + hourlyrate
                + "\ntotal pay:" + totalpay
                + "\n}";







    }


}