package com.Project1;
import java.util.ArrayList;

/**
 * This method creates members, searches members, checks member ID and edit members
 * @author Kamil Ismayil
 * @version 1.0
 * @since 2019-09-13
 */

public class Gym< M extends Members> {
    String name;
    ArrayList<M> Members = new ArrayList<>();

    public Gym(String name) {
        this.name = name;
    }

    /**
     * This method adds a new member
     * @param newMember
     * @return creates a new member
     */
    public boolean AddMember(M newMember) {
        try {
            if (findMember(newMember) == false) {
                this.Members.add(newMember);
                Thread.sleep(1000);
                System.out.println("Member has successfully been added!");
                return true;
            } else {
                Thread.sleep(1000);
                System.out.println(newMember.getName() + " " + newMember.getSurname() + newMember.getAge() +
                        "already exists. Please type another name, surname or age");
                return false;
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        return true;
    }

    /**
     * This method checks whether the member ID is valid or not
     * @param memberID
     * @return true or false
     */
    public boolean FindMemberID(int memberID){
        if (!Members.isEmpty()){
           for(int i=0; i<Members.size();i++){
               if(memberID == Members.get(i).getMemberID()){
                   return true;
               }
           }
        }else{
            System.out.println("There is no member added yet!");
        }
        return false;
    }

    /**
     * This method edits member details
     * @param memberID ID for the member
     * @param choice indicates which detail the user has chosen to change
     * @param newValue new value that the user has written
     * @return String newValue
     */
    public boolean EditMember(int memberID, String choice, String newValue) {
            Members.forEach(member -> {
                if (memberID == member.getMemberID()) {
                    switch (choice){
                        case "Email":
                            member.setEmailAddress(newValue);
                            break;
                        case "Phone number":
                            member.setPhoneNumber(newValue);
                            break;
                        case "Address":
                            member.setAddress(newValue);
                            break;
                        case "Membership Type":
                            member.setMembershipType(newValue);
                            break;
                    }
                    System.out.println("The contact has been updated!");
                }
            });
        return true;
    }

    /**
     * This method whether the member exists or not
     * @param member
     * @return true or false
     */
    public boolean findMember(M member) {
        if (Members.contains(member) == false) {
            return false;
        }
        return true;
    }

    /**
     * This method return the list of members in Arraylist format
     */
    public ArrayList<M> getMembers() {
        return Members;
    }

    /**
     * This method return the gym name
     */
    public String getName() {
        return name;
    }
}
