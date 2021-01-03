import java.util.HashMap;

public class CustReport extends Report{
    private DataContainer CustContainer = new DataContainer();


    public CustReport() {
        super("List of Customer");

        CustContainer.metadata.add("No");
        CustContainer.metadata.add("Name");
        CustContainer.metadata.add("Phone");
        CustContainer.metadata.add("Status");
        int count = -1;

        for (Customer c : Customer.custs) {
            CustContainer.data.add(new HashMap<String, String>());
            CustContainer.data.get(++count).put("No", Integer.toString(count + 1));
            CustContainer.data.get(count).put("Name", Customer.custs.get(count).getName());
            CustContainer.data.get(count).put("Phone", Customer.custs.get(count).getPhone());
            CustContainer.data.get(count).put("Status", Customer.custs.get(count).getStatus());
            }
    }

    public CustReport(Customer c){
        super(c.getName() + "'s Visit History");

        int custId = c.getId();
        CustContainer.metadata.add("No");
        CustContainer.metadata.add("Date");
        CustContainer.metadata.add("Time");
        CustContainer.metadata.add("Shop");
        int count = -1;

        for (Visit v : Visit.visits){
            if(v.getCustID() == custId){
                int shopID = v.getShopID();
                String shopName = Shop.shops.get(shopID - 1).getName();
                CustContainer.data.add(new HashMap<String, String>());
                CustContainer.data.get(++count).put("No", Integer.toString(count + 1));
                CustContainer.data.get(count).put("Date", v.getDate());
                CustContainer.data.get(count).put("Time", v.getTime());
                CustContainer.data.get(count).put("Shop", shopName);
            }
        }
    }

    public void exportCSV() {
        CSV.export(super.getTitle(), CustContainer);
    }

    public void display() {
        Table.display(super.getTitle(), CustContainer);
    }
}
