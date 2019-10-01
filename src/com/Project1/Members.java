package com.Project1;
import java.util.ArrayList;
import java.util.Date;

 /**
  * This class creates a member with parameters: name, surname, address, phone number, email address, gender, age, membership type and
  * membership start date.
  * @author Kamil Ismayil
  * @version 1.0
  * @since 2019-09-13
 **/
public abstract class Members<M extends PurchasedProducts>{
    int memberID, age, fee;
    final String Name, Surname;
    String emailAddress, Gender, phoneNumber, membershipType, Address;
    Date startDate;
    private ArrayList<M> newPurchasedProduct = new ArrayList<>();

    public Members(String name, String surname, String address, String phoneNumber, String emailAddress, String gender,
                   int age, int fee, String membershipType, Date startDate) {
        this.memberID = (int)(1+Math.random() * 1000);
        this.phoneNumber = phoneNumber;
        Name = name;
        Surname = surname;
        this.Address = address;
        this.emailAddress = emailAddress;
        Gender = gender;
        this.age = age;
        this.membershipType = membershipType;
        this.startDate = startDate;
        this.fee = fee;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

     /**
      * This method generates and set a new ID for the member,
      * if the memberID already been assigned to another member
      * @param g //member
      */
    public void checkMemberID(Gym<Members<PurchasedProducts>> g){
        g.getMembers().forEach(entry->{
            if(this.memberID==entry.getMemberID()) {setMemberID((int)(1+Math.random() * 1000));}
        });
    }

    public ArrayList<M> getNewPurchasedProduct() {
        return newPurchasedProduct;
    }

    public void AddPurchasedProduct(M purchasedProduct){
        newPurchasedProduct.add(purchasedProduct);
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public int getMemberID() {
        return memberID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return Name;
    }

    public String getSurname() {
        return Surname;
    }

    public String getAddress() {
        return Address;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getGender() {
        return Gender;
    }
 }
