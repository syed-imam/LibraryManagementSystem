package userinterface;

import java.sql.SQLException;
import library.account.LoginCollection;
import library.account.PatronAccount;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import library.account.Login;
import library.income.IncomeCol;
import static userinterface.LibrarianInterface.librarianInterface;
import static userinterface.PatronInterface.patronInterface;
import library.income.*;
import java.util.Date;
import library.account.*;
import library.media.CheckedOutCollection;

public class Main {

    public static final String staffMenu = "\n\n\n=========MENU OPTIONS:=========\n1. Look up Account"
            + "\n2. Media Managment\n3. Update Account\n4. View Income\n5. Insert Incom\n6. Check Media Due Dates\n"
            + "7. Send Late Notifications\n"
            + "8. Send Due Date Notifications\n"
            + "9. Apply Late Fees\n10. Bill Account\n0. Logout";
    public static final String patronMenu = "";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean login = false;
        System.out.println("\n\nWelcome to Not Your Mamas's Library!\n====================================");
        int input = 0;
        Login a;
        PatronAccount p;
        IncomeCol income_collection = new IncomeCol();
        AccountCollection account_collection = new AccountCollection();
        CheckedOutCollection checkedOutCollection = new CheckedOutCollection ();
        boolean appliedLateFee = false;
      

        String user;
        String pass;

        while (!login) {
            System.out.print("\n\n\n=========MENU OPTIONS:=========\n1. Staff Login\n2. Patron Login\n3. Register (Patron Only)\n0. Exit\nType an option: ");
            input = scan.nextInt();
            switch (input) {
                case 0:
                    System.out.println("Bye bye!");
                    System.exit(0);
                    break;
                case 1:
                    System.out.print("Username: ");
                    user = scan.next();
                    System.out.print("Password: ");
                    pass = scan.next();
                    a = LoginCollection.loginStaff(user, pass);

                    if (a == null) {
                        login = false;
                        System.out.println("Could not login!");
                    } else {
                        login = true;
                        System.out.println("\n\n======== Welcome " + a.getUsername() + " ========");

                        
                            while (login) {

                                System.out.println(staffMenu);
                                System.out.print("Please enter an option: ");

                                input = scan.nextInt();
                                switch (input) {
                                    case 0:
                                        login = false;
                                        System.out.println("Bye bye!");
                                        break;
                                    case 1:
                                        staffAccountInterface(scan, a);
                                        break;
                                    case 2:
                                        librarianInterface();
                                        break;
                                    case 3:
                                        break;
                                    case 4:
                                        System.out.println(income_collection.view('*').toString());
                                        break;
                                    case 5:
                                        System.out.println("Please Insert Patron's Id\n");
                                        String tempId = scan.next();
                                        System.out.println("Please enter the type of payment g for donation d for damages l for latapayment\n");
                                        char typePay = scan.next().charAt(0);
                                        System.out.println("Please enter the amount being paid\n");
                                        double amount = scan.nextDouble();
                                        income_collection.insert(new Income(tempId, typePay, amount, new Date()));
                                        break;
                                    case 6:
                                        checkedOutCollection.checkDueDates();
                                        break;
                                    case 7:
                                        checkedOutCollection.sendLateNotificaton();
                                        break;
                                    case 8:
                                        checkedOutCollection.sendDueDateNotification();
                                        break;
                                        
                                        //Elbin Set Case to apply late fees
                                    case 9:
                                        if(appliedLateFee)
                                        {
                                            System.out.println("Late Fee's have already been applied today.");
                                        }
                                        else
                                        {
                                            if(account_collection.applyLateFees())
                                            {
                                                System.out.println("Late Fee were successful applied.");
                                                appliedLateFee = true; 
                                            }
                                            else
                                            {
                                                System.out.println("Something went wrong and late fee's were not applied");
                                            }
                                        }
                                        break;
                                    //Elbin set case to bill a patron for any damages    
                                    case 10:
                                        System.out.println("Please enter the patron Id you want to bill.");
                                        String idToBill = scan.next();
                                        System.out.println("Please enter the amount to bill account.");
                                        double amountToBill = scan.nextDouble();
                                        if(account_collection.updateBalance(idToBill, amountToBill))
                                        {
                                            System.out.println("The Account has been successfully billed.");
                                        }
                                        else
                                        {
                                            System.out.println("Something went wrong account was not billed.");
                                        }
                                        break;
                                        
                                        
                                    default:
                                        print("Type a valid options");
                                        break;
                                }
                            }
                        
                    }
                    break;

                case 2:
                    System.out.print("Username: ");
                    user = scan.next();
                    System.out.print("Password: ");
                    pass = scan.next();
                    a = LoginCollection.loginStaff(user, pass);

                    if (a == null) {
                        login = false;
                        System.out.println("Could not login!");
                    } else {
                        login = true;
                        System.out.println("\n\n======== Welcome " + a.getUsername() + " ========");

                        while (login) {

                            System.out.println(staffMenu);
                            System.out.print("Please enter an option: ");

                            input = scan.nextInt();
                            switch (input) {
                                case 0:
                                    login = false;
                                    System.out.println("Bye bye!");
                                    break;
                                case 1:
                                    patronAccountInterface(scan, a);
                                    break;
                                case 2:
                                    patronInterface(a);
                                    break;
                                case 3:
                                    break;
                                case 4:
                                    System.out.println(income_collection.view('*').toString());
                                case 5:
                                    System.out.println("Please Insert Patron's Id\n");
                                    String tempId = scan.next();
                                    System.out.println("Please enter the type of payment g for donation d for damages l for latapayment\n");
                                    char typePay = scan.next().charAt(0);
                                    System.out.println("Please enter the amount being paid\n");
                                    double amount = scan.nextDouble();
                                    income_collection.insert(new Income(tempId, typePay, amount, new Date()));

                                    break;
                                default:
                                    print("Type a valid options");
                                    break;
                            }
                        }

                    }
                    break;
                case 3:
                    print("");
                    break;
                default:
                    print("");
                    break;
            }
        }

    }

    private static void print(String x) {
        System.out.println(x);
    }

    private static void staffAccountInterface(Scanner scan, Login login) {
        System.out.println("1. View Login");
        System.out.println("2. Change Password");
        print("0. Exit");
        int input;
        do {
            input = scan.nextInt();
            switch (input) {

//              -----------------------
//              | Edit by Ben - 5/11  |                                 
//              -----------------------
                case 0:
                    break;
                case 1:
                    System.out.println(login.toString());
                    break;
                case 2:
                    changePassword(login);
                    break;
                default:
                    print("Invalid input!");
                    break;

//              ----------------------                  
//              | Finish Edit - 5/11 |      
//              ----------------------  
            }
        } while (input != 0);
    }
    //Edit by Ben 5/11

    public static void changePassword(Login login) {
        Scanner scan = new Scanner(System.in);
        String curPass;
        for (int i = 0; i < 3; i++) {
            System.out.println("Please enter your current password");
            curPass = scan.next();
            if (login.getPassword().equals(curPass)) {
                System.out.println("Please enter your new password:");
                String newPass = scan.next();
                login.setPassword(newPass);
                if (LoginCollection.updateStaffLogin(login.getId(), login.getUsername(), login.getPassword())) {
                    System.out.println("Password Change Successful!!");
                }
                break;
            }
            System.out.println("Incorrect Password");
        }

    }

    private static void patronAccountInterface(Scanner scan, Login login) {
        System.out.println("1. View Login");
        System.out.println("2. Change Password");
        print("0. Exit");
        int input;
        do {
            input = scan.nextInt();
            switch (input) {

//              -----------------------
//              | Edit by Ben - 5/11  |                                 
//              -----------------------
                case 0:
                    break;
                case 1:
                    System.out.println(login.toString());
                    break;
                case 2:
                    changePassword(login);
                    break;
                default:
                    print("Invalid input!");
                    break;

//              ----------------------                  
//              | Finish Edit - 5/11 |      
//              ----------------------  
            }
        } while (input != 0);
    }
}
