public class lecturer extends Employee,HR{

private double payrate;
private double hours =0;

public lecturer(){


}

public employee(String name, String address, String phoneNumber, String emailAddress, String password, String id, String ppsn, String IBAN, double hourlyrate, double hours, double payrate )
{
this.hourlyrate = hourlyrate;
this.payrate = payrate;
this.hours = hours;

}






public double hours() {
    return hours;

   
}

public double hourlyrate() {
    return hours;

   
}

public double payrate() {
    return hours;

   
}

public void hours(double hours){
    this.hours = hours;
    }


public void payrate(double payrate){
     this.payrate = IBAN;
       }

public void hourlyrate(double hourlyrate){
    this.IBAN = IBAN;
    }



}