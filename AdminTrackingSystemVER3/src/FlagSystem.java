import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class FlagSystem {
    public static void flagCaseClose() {

        new CustReport().display();

        while (true) {
            System.out.println("Choose from visit history to be flagged:");
            System.out.println("(Please input one by one && input -1 to stop)");
            int custID = InputValid.checkRange(-1, Customer.custs.size());
            if (custID == -1)
                return;
            else
                flagCaseGetRecord(custID);
        }

    }

    public static void flagCaseGetRecord(int custID) {
        for (int i = 0; i < Visit.visits.size(); i++)
            if (custID == Visit.visits.get(i).getCustID())
                flagCaseClose(i);


        Customer.Serialize();
        Shop.Serialize();
        System.out.println("Flag successfully.\n");
    }

    public static void flagCaseClose(int choice) {

        Visit selectedCase = Visit.visits.get(choice);

        int shopID = selectedCase.getShopID();
        int custID = selectedCase.getCustID();
        LocalDateTime CaseDT = selectedCase.getDt();
        LocalDateTime BfCaseDT = CaseDT.minusHours(1);
        LocalDateTime AfCaseDT = CaseDT.plusHours(1);

        Customer.custs.get(custID - 1).setStatus("Case");
        Shop.shops.get(shopID - 1).setStatus("Case");

        ArrayList<Visit> CloseCustList = new ArrayList<>();

        for (Visit v : Visit.visits) {
            if (v.getShopID() == shopID)
                CloseCustList.add(v);
        }

        for (Visit v : CloseCustList) {
            LocalDateTime VisitDT = v.getDt();
            int VisitCustID = v.getCustID();

            if ((VisitDT.isBefore(CaseDT) && VisitDT.isAfter(BfCaseDT))
                    || (VisitDT.isAfter(CaseDT) && VisitDT.isBefore(AfCaseDT))) {
                if (!Customer.custs.get(VisitCustID - 1).getStatus().equals("Case"))
                    Customer.custs.get(VisitCustID - 1).setStatus("Close");
            }
        }
    }


    public static void flagNormal(int role){
        /** role 1 = customer // role 2 = shop */

        if(role == 1)
            flagCaseNormalCust();
        else
            flagCaseNormalShop();

        System.out.println("Flag Successfully.\n");

    }
    public static void flagCaseNormalCust(){
        int choice;

        CustReport custReport = new CustReport();
        custReport.display();
        System.out.print("Please enter No. to be flag Normal: ");
        choice = InputValid.checkRange(1,Customer.custs.size());

        for(int i = 0; i < Customer.custs.size(); i++){
            if(Customer.custs.get(i).getId() == choice){
                /** Mark the Customer status to normal */
                Customer.custs.get(choice-1).setStatus("Normal");
            }
        }
        Customer.Serialize();
    }

    public static void flagCaseNormalShop(){
        int choice;

        ShopReport shopReport = new ShopReport();
        shopReport.display();
        System.out.print("Please enter No. to be flag Normal: ");
        choice = InputValid.checkRange(1,Shop.shops.size());

        for(int i = 0; i < Shop.shops.size(); i++){
            if(Shop.shops.get(i).getId() == choice){
                /** Mark the shop's status to normal */
                Shop.shops.get(choice-1).setStatus("Normal");
            }
        }
        Shop.Serialize();
    }
}
