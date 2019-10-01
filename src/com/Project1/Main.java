package com.Project1;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Gym<Members<PurchasedProducts>> gym = new Gym<Members<PurchasedProducts>>("KamilGym");
        Menu(gym);
    }

    /**
     * This method shows menu for buying a new product for gym members
     */
    public static void ShowBuyProducts(Gym<Members<PurchasedProducts>> gym){
        Members<PurchasedProducts> member;
        Scanner scan = new Scanner(System.in);
        int[] i = {0};
        boolean quit = false;
        int choice=0, amount=0, memberID;
        PurchasedProducts purchasedProducts;
        ArrayList<String> productNames = new ArrayList<>();
        ArrayList<Integer> productPrices = new ArrayList<>();
        Products products = new Products();
        products.productList();

        System.out.println("Available products that you can buy: ");
        products.getProducts().entrySet().forEach(entry->{ i[0]++;
            System.out.println(i[0] + ". " + entry.getKey() + " " + entry.getValue() + "kr");
            productNames.add(entry.getKey());
            productPrices.add(entry.getValue());
        });

        System.out.print("Please select the product you would like to buy: ");
        while(!quit) {
            try {
                choice = scan.nextInt();
                if(choice > products.getProducts().size()) {
                    System.out.print("Please enter number between 0 and " + products.getProducts().size() +": ");
                } else {
                    quit = true;
                }
            } catch (InputMismatchException e) {
                scan.nextLine();
                System.out.print("You did not enter an integer value. Please try again: ");
            }
        }
        quit = false;
        System.out.print("Please enter amount: ");
        while(!quit){
            try{
                amount = scan.nextInt();
                quit = true;
            }catch (InputMismatchException e){
                scan.nextLine();
                System.out.print("You did not enter an integer value. Please try again: ");
            }
        }


        //Checks whether CU has correct member ID. If yes, adds the product to his account. If not, returns to the main manu
        System.out.print("Please enter your member ID: ");
        memberID = scan.nextInt();
        for(int j=0; j<gym.getMembers().size(); j++) {
            if (gym.getMembers().get(j).getMemberID() == memberID) {
                gym.getMembers().get(j).AddPurchasedProduct(new PurchasedProducts(productNames.get(choice-1),productPrices.get(choice-1),amount));
                System.out.println("Product(s) been successfully purchased");
                showPurchasedProducts(gym.getMembers().get(j));
                break;
            } else{
                System.out.println("Member ID has not been found. Please find your member ID and proceed the operation again!");
            }
        }
    }

    /**
     * This method shows the purchased products for each member
     */
    public static void showPurchasedProducts(Members<PurchasedProducts> member){
        System.out.println("Your purchases:");
        for (int i=0; i<member.getNewPurchasedProduct().size(); i++)
        System.out.println( "Product name: " + member.getNewPurchasedProduct().get(i).productName + "\n" +
                            "Product amount: " + member.getNewPurchasedProduct().get(i).getAmount() + "\n" +
                            "Total Price: " + member.getNewPurchasedProduct().get(i).getPriceTotal() + "kr");
        System.out.println("*****************");
    }

    /**
     * This method creates a new member based on the values what the user has entered
     */
    public static void AddMember(Gym<Members<PurchasedProducts>> gym){
        final int feePensioner = 200;
        final int feeStudent = 150;
        final int feeOrdinary = 300;
        LocalDate currentDate = LocalDate.now();
        Date startDate = Date.from(currentDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        boolean quit = false;
        String Name, Surname, Address, phoneNumber, startDateTemp;
        String Gender, emailAddress;
        int Age;
        Members<PurchasedProducts> newContact;

        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the following details about a new member:");
        System.out.print("Name: ");
            Name = scan.nextLine();
        System.out.print("Surname: ");
            Surname = scan.nextLine();
        System.out.print("Phone number: ");
            phoneNumber = scan.nextLine();
        System.out.print("Address: ");
            Address = scan.nextLine();
        System.out.print("Email address: ");
            emailAddress = scan.nextLine();

        while(!emailAddress.contains("@")){
            System.out.println("Email address must contain @ sign: ");
            emailAddress = scan.nextLine();
        }

        System.out.print("Gender: ");
        Gender = scan.nextLine();
        while(!Gender.equalsIgnoreCase("male") && !Gender.equalsIgnoreCase("female") ) {
            System.out.print("Please enter either male or female: ");
                Gender = scan.next();
        }
        System.out.print("Would you like to start the membership today (" + currentDate + ")?" + " (Y/N) ");
        if(scan.next().equalsIgnoreCase("Y")) {
            startDate = Date.from(currentDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        } else{
            System.out.print("Please enter the date you would like to start the membership:(yyyy-mm-dd) ");
            while(!quit) {
                try {
                    startDateTemp = scan.next();
                    startDate = dateFormat.parse(startDateTemp);

                    if(startDate.compareTo(Calendar.getInstance().getTime())<0){ //checking whether the start date is in the past
                        System.out.print("The start date cant be in the past! Please enter the start date again: ");
                        quit = false;
                    }else{
                        quit = true;
                    }

                } catch (ParseException | DateTimeException e) {
                    //e.printStackTrace();
                    System.out.print("Please enter correct date format: ");
                    quit = false;
                }
            }
        }
        quit = false;
        boolean quit_pensioner = false;

        System.out.print("Age: ");
        while(!quit) {
            try {
                Age = scan.nextInt();
                if (Age > 65) {
                    System.out.print("We would like to be Pensioner Members? (Y/N) ");
                    while(!quit_pensioner) {
                        String choice_pensioner = scan.next();
                        if (choice_pensioner.equalsIgnoreCase("Y")) {
                            newContact = new Pensioner(Name, Surname, Address, phoneNumber, emailAddress, Gender, Age, feePensioner,
                                    "Pensioner", startDate);
                            newContact.checkMemberID(gym);
                            gym.AddMember(newContact);
                            quit_pensioner = true;
                            //quit = true;
                        } else if (choice_pensioner.equalsIgnoreCase("N")) {
                            newContact = new Ordinary(Name, Surname, Address, phoneNumber, emailAddress, Gender, Age, feeOrdinary,
                                    "Ordinary", startDate);
                            newContact.checkMemberID(gym);
                            gym.AddMember(newContact);
                            quit_pensioner = true;
                            //quit = true;
                        } else {
                            System.out.print("You have to type either Y or N: ");
                            quit_pensioner = false;
                        }
                    }
                    quit = true;
                }

                else { //age < 65
                    System.out.print("Are you a student? (Y/N) ");
                    String choice_student;

                    boolean quit_student = false;
                    while (!quit_student){
                        choice_student = scan.next();
                        if (choice_student.equalsIgnoreCase("Y")) {
                            newContact = new Student(Name, Surname, Address, phoneNumber, emailAddress, Gender, Age, feeStudent,
                                    "Student", startDate);
                            newContact.checkMemberID(gym);
                            gym.AddMember(newContact);
                            quit_student = true;
                        } else if (choice_student.equalsIgnoreCase("N")) {
                            newContact = new Ordinary(Name, Surname, Address, phoneNumber, emailAddress, Gender, Age, feeOrdinary,
                                    "Ordinary", startDate);
                            newContact.checkMemberID(gym);
                            gym.AddMember(newContact);
                            quit_student = true;
                        } else {
                            System.out.print("You have to type either Y or N: ");
                            quit_student = false;
                        }
                    }
                }
                quit = true;
            } catch (InputMismatchException e) {
                scan.nextLine();
                System.out.print("You did not enter an integer value. Please try again: ");
            }
        }
    }

    /**
     * Shows the list of members
     */
    public static void ListOfContacts(Gym<Members<PurchasedProducts>> gym){
        if(!gym.getMembers().isEmpty()) {
            gym.getMembers().forEach(entry->{
                System.out.println(entry.getName() + " " + entry.getSurname() + " details:\n" +
                        "Age: " + entry.getAge() + "\nAddress: " + entry.getAddress() +
                        "\nEmail: " + entry.getEmailAddress() + "\nGender: " + entry.getGender() +
                        "\nMembership Type: " + entry.getMembershipType() + "\nPhone: " + entry.getPhoneNumber() +
                        "\nStart Date: " + entry.getStartDate() + "\nMember ID: " + entry.getMemberID() + "\n********************");
            });
        } else{
            System.out.println("Currently there is no member available!");
        }
    }

    /**
     * This method edits member details like email, phone number, address and membership type
     */
    public static void EditMembers(Gym<Members<PurchasedProducts>> gym){
        Scanner scan = new Scanner(System.in);
        Boolean quit = false;
        int memberID, choice=1, i=1;
        String choiceNewValue;
        Boolean quitTemp = false;
        String[] choices = {"Email", "Phone number", "Address", "Membership Type"};

        while(!quit) {
            try {
                System.out.print("Please enter member ID: ");
                memberID = scan.nextInt();

                if(gym.FindMemberID(memberID)){
                    System.out.println("What would you like to change: ");
                    for(String c: choices){
                        System.out.println(i++ + ". " + c);
                    }
                    while(!quitTemp) {
                        try {
                            choice = scan.nextInt();

                            if(choice > choices.length) {
                                System.out.print("Please enter value between 1 and " + choices.length + ": ");
                                quit = false;
                            }else {
                                quitTemp = true;
                            }
                        } catch (InputMismatchException e) {
                            scan.nextLine();
                            System.out.print("You did not enter an integer value! Please try again: ");
                        }
                    }

                    System.out.print("Please enter a new value: ");
                    scan.nextLine();
                    choiceNewValue = scan.nextLine();
                    switch (choice){
                        case 1:
                            while(!choiceNewValue.contains("@")){
                                System.out.print("Email address must contain @ sign: ");
                                choiceNewValue = scan.nextLine();
                            }
                            break;
                        case 4:
                            while(!choiceNewValue.contains("Student") && !choiceNewValue.contains("Pensioner") &&
                                    !(choiceNewValue.contains("Ordinary"))){
                                System.out.print("Membership type can be one of the following: Student, Pensioner or Ordinary: ");
                                choiceNewValue = scan.nextLine();
                            }
                            break;
                    }
                    gym.EditMember(memberID, choices[choice-1],choiceNewValue);
                }else{
                    System.out.println("Wrong member ID!");
                }
                quit = true;

            } catch (InputMismatchException e) {
                scan.nextLine();
                System.out.print("You did not enter an integer value! ");
            }
        }
    }

    /**
     * This method shows the main menu
     */

    public static void Menu(Gym<Members<PurchasedProducts>> gym){
        boolean quit = false;
        Scanner scan = new Scanner(System.in);

        while(!quit) {
            System.out.println("Please choose one of the following:");
            System.out.println("1. Add a new member\n2. Edit Members\n3. Show list of members\n4. Purchase a product" +
                    "\n5. Quit");
            int choice = scan.nextInt();
            switch (choice){
                case 1:
                    AddMember(gym);
                    break;
                case 2:
                    EditMembers(gym);
                    break;
                case 3:
                    ListOfContacts(gym);
                    break;
                case 4:
                    ShowBuyProducts(gym);
                    break;
                case 5:
                    quit = true;
                    break;
            }
        }
    }
}
