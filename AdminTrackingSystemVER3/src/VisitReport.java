import java.util.HashMap;

public class VisitReport extends Report {
    private DataContainer VisitContainer = new DataContainer();

    public VisitReport(){
        super("All Visit History");

        VisitContainer.metadata.add("No");
        VisitContainer.metadata.add("Date");
        VisitContainer.metadata.add("Time");
        VisitContainer.metadata.add("Customer Name");
        VisitContainer.metadata.add("Shop Name");

        int count = -1;
        for (Visit v : Visit.visits) {
            VisitContainer.data.add(new HashMap<String, String>());
            VisitContainer.data.get(++count).put("No", Integer.toString(count + 1));
            VisitContainer.data.get(count).put("Date",Visit.visits.get(count).getDate());
            VisitContainer.data.get(count).put("Time",Visit.visits.get(count).getTime());
            int custID = Visit.visits.get(count).getCustID();
            int shopID = Visit.visits.get(count).getShopID();
            VisitContainer.data.get(count).put("Customer Name",Customer.custs.get(custID-1).getName());
            VisitContainer.data.get(count).put("Shop Name",Shop.shops.get(shopID-1).getName());
        }
    }

    public void exportCSV() {
        CSV.export(super.getTitle(), VisitContainer);
    }

    public void display() {
        Table.display(super.getTitle(), VisitContainer);
    }



}
