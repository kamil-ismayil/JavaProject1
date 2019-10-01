package com.Project1;
import java.util.Date;

 /**
  * This class extends Members class and creates a new pensioner gym member
  * @author Kamil Ismayil
  * @version 1.0
  * @since 2019-09-13
 **/
public class Pensioner extends Members {
    public Pensioner(String name, String surname, String address, String phoneNumber, String emailAddress, String gender, int age,
                     int fee, String membershipType, Date startDate) {
        super(name, surname, address, phoneNumber, emailAddress, gender, age, fee, membershipType, startDate);
    }
}
