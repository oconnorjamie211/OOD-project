//-edm emlpoyee class
public class Employee {
    private String name;
    private String address;
    private String phoneNumber;
    private String emailAddress;
    private String ppsn;
    private String password;
    private String id;
    
    private String IBAN;
    private String jobtitle;
    private int payscale;


   

    
   //password is defaulted as the ppsn
    public Person(String name, String address, String phoneNumber, String emailAddress, String id, String ppsn, String IBAN, String jobtitle, int payscale){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.password = ppsn;
        this.id = id;
        this.ppsn = ppsn;
        this.IBAN = IBAN;
        this.jobtitle = jobtitle;
        this.payscale = payscale;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String password() {
        return password;
    }

    public String getid() {
        return id;
    }

    public String getppsn() {
        return ppsn;
    }

    public String getIBAN() {
        return IBAN;
    }

    public String gethours() {
    return hours;

    }

    public double getpayrate() {
        return hours;
    
       
    }
    public double gethourlyrate() {
        return hours;
    
       
    }
    public String getjobtitle(){
      return jobtitle;



    }

    public int getpayscale(){
    return payscale;

    }
    
    public void changename(String name){
        this.name = name;
            
            }

    
     public void changeaddress(String address){
        this.address = address;
                    
         }


     public void changephoneNumber(int phoneNumber){
        this.phoneNumber = phoneNumber;
                
            }   
            
            
    public void changeemailaddress(String emailAddress){
         this.emailAddress = emailAddress;
                    
            }



    public void changepassword(String password){
    this.password=password;
    
    }

    public void changeid(int id){
    this.id = id;
        
        }

    public void changeppsn(int ppsn){
    this.ppsn=ppsn;
                
       }

    public void changeIBAN(int IBAN){
    this.IBAN = IBAN;
    }


   















    


    @Override
    public String toString(){
        return "Person "
                +"\nName: " + name
                +"\nAddress: " + address
                +"\nPhone Number: " + phoneNumber
                +"\nEmail Address: " + emailAddress
                +"\npassword: " + password
                +"\nid: " + id
                +"\nppsn: " + ppsn
                +"\nIBAN: " + IBAN;






    }


}