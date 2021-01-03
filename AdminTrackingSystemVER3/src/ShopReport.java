import java.util.HashMap;

public class ShopReport extends Report {
    private DataContainer ShopContainer = new DataContainer();


    public ShopReport() {
        super("List of Shop");

        ShopContainer.metadata.add("No");
        ShopContainer.metadata.add("Name");
        ShopContainer.metadata.add("Phone");
        ShopContainer.metadata.add("Manager");
        ShopContainer.metadata.add("Status");
        int count = -1;

        for (Shop s : Shop.shops) {
            ShopContainer.data.add(new HashMap<String, String>());
            ShopContainer.data.get(++count).put("No", Integer.toString(count + 1));
            ShopContainer.data.get(count).put("Name", Shop.shops.get(count).getName());
            ShopContainer.data.get(count).put("Phone", Shop.shops.get(count).getPhone());
            ShopContainer.data.get(count).put("Manager", Shop.shops.get(count).getManager());
            ShopContainer.data.get(count).put("Status", Shop.shops.get(count).getStatus());
        }

    }

    public void exportCSV() {
        CSV.export(super.getTitle(), ShopContainer);
    }

    public void display() {
        Table.display(super.getTitle(), ShopContainer);
    }
}
