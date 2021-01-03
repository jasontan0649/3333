import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class Menu {

    public static Admin signIn() {
        Scanner input = new Scanner(System.in);

        while (true) {

            System.out.print("Username: ");
            String username = input.nextLine();
            System.out.print("Password: ");
            String password = input.nextLine();

            Admin adm = Admin.getAdminByName(username);
            if (adm == null || !adm.getPassword().equals(password)) {
                System.out.println("Incorrect Password / Username.\nPlease try again.");
                continue;
            }
            return adm;
        }
    }


    public static Admin signUp() {
        Scanner input = new Scanner(System.in);

        while (true) {

            System.out.println("Enter your username: ");
            String username = input.nextLine();
            if (username.contains(" ")) {
                System.out.println("Space is not accepted.\nPlease try again.");
                continue;
            }

            System.out.println("Enter your password(max 16 characters): ");
            String password = input.nextLine();
            if (password.length() > 16) {
                System.out.println("Exceed max character.\nPlease enter only 16 characters.");
                password = input.nextLine();
            } else
                return new Admin(username, password);
        }
    }

    public static void AdminInterface() {
        Boolean flag = true;

        while (true) {
            System.out.print("\n");
            System.out.println("Please select below operation.");
            System.out.println("1. View Master's History");
            System.out.println("2. View all customers status");
            System.out.println("3. View all shop status");
            System.out.println("4. Flag Status (Case/Close)");
            System.out.println("5. Flag Normal");
            System.out.println("6. Generate Random 30 visits");
            System.out.println("7. Exit");


            Scanner input = new Scanner(System.in);

            int choice = InputValid.checkRange(1, 7);

            switch (choice) {
                case 1:
                    viewMasterHistory();
                    break;
                case 2:
                    viewCustStatus();
                    break;
                case 3:
                    viewShopStatus();
                    break;
                case 4:
                    flagStatus();
                    break;
                case 5:
                    flagNormal();
                    break;
                case 6:
                    Initializer.preVisit();
                    break;
                case 7:
                    return;

            }
        }
    }

    public static void viewMasterHistory() {
        Scanner input = new Scanner(System.in);

        CustReport custReport = new CustReport();
        custReport.display();
        System.out.println("Please select Customer: ");

        int choice = InputValid.checkRange(1,Customer.custs.size());

        Customer SelectedMaster = Customer.custs.get(choice-1);
        CustReport selectedReport = new CustReport(SelectedMaster);
        selectedReport.display();

        System.out.println("Export into csv file? Y/N");
        String ch = InputValid.checkValidChar();
        if (ch.equals("Y"))
            custReport.exportCSV();
    }

    public static void viewCustStatus() {
        CustReport custReport = new CustReport();
        custReport.display();
        System.out.println("Export into csv file? Y/N");
        String ch = InputValid.checkValidChar();
        if (ch.equals("Y"))
            custReport.exportCSV();

    }

    public static void viewShopStatus() {
        ShopReport shopReport = new ShopReport();
        shopReport.display();
        System.out.println("Export into csv file? Y/N");
        String ch = InputValid.checkValidChar();
        if (ch.equals("Y"))
            shopReport.exportCSV();

    }

    public static void flagStatus() {
        FlagSystem.flagCaseClose();
        viewCustStatus();
        viewShopStatus();
    }

    public static void flagNormal() {
        while (true) {
            System.out.println("1. Customer");
            System.out.println("2. Shop");
            System.out.println("3. back");

            int role = InputValid.checkRange(1, 3);
            if (role == 3)
                return;

            FlagSystem.flagNormal(role);

            viewCustStatus();
            viewShopStatus();
        }
    }
}


