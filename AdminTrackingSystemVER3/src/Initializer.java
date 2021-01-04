import jdk.jshell.execution.LoaderDelegate;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class Initializer {
    public final static String CUR_PATH = getPreviousPath();

    public static String getPreviousPath() {
        Path p1 = Paths.get(System.getProperty("user.dir")).getParent();
        return p1.toString();
    }

    public static void Initialize(){
        Customer.Deserialize();
        Shop.Deserialize();
        Visit.Deserialize();
        Admin.Deserialize();

        if (Admin.adminArrayList.isEmpty())
            preAdmin();

    }

    public static void preAdmin(){
        new Admin("User1", "User1");
        new Admin("User2", "User2");
        new Admin("User3", "User3");
    }

    public static void preVisit() {
        Visit.visits.clear();
        LocalDateTime[] randomDTs = timeGene();
        final Random random = new Random();
        int CustAmt = Customer.custs.size();
        int ShopAmt = Shop.shops.size();

        for (int i = 0; i < 30; i++) {
            Customer randomCust = Customer.custs.get(random.nextInt(CustAmt));
            Shop randomShop = Shop.shops.get(random.nextInt(ShopAmt));
            new Visit(randomDTs[i], randomCust.getId(), randomShop.getId());
        }

        for (Customer c : Customer.custs)
            c.setStatus("Normal");
        Customer.Serialize();

        for (Shop s : Shop.shops)
            s.setStatus("Normal");
        Shop.Serialize();

    }

    public static LocalDateTime[] timeGene() {
        final Random random = new Random();
        /** Declared a day in millisecond */
        final int millisInDay = 24*60*60*1000;

        /**Local Date Time array with size 30 */
        LocalDateTime[] randomDTs = new LocalDateTime[30];
        long[] diffSec = new long[30];
        /** Current DateTime */
        LocalDateTime dt = LocalDateTime.now();
        /** ZoneId.Systemdefault() = take current GMT convert to zoneID */
        /**toEpochMilli() take the zoneID convert to epoch Milli calculation */
        long curTime = dt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        for (int i = 0; i < 30; i++)
            diffSec[i] = random.nextInt(millisInDay);
        Arrays.sort(diffSec);

        for (int i = 0; i < 30; i++) {
            /** To get Randam time (either pm /am/the last day) */
            long newDT = curTime - diffSec[29-i];
            /**Instant.ofEpochMilli(newDT) - Get instance of Instant using Millisecond form the epoch*/
            randomDTs[i] = LocalDateTime.ofInstant(Instant.ofEpochMilli(newDT), ZoneId.systemDefault());
        }
        /** Sort DT array. */
        Arrays.sort(randomDTs);
        /** Return a LocalDateTime array with random DT */
        return randomDTs;
    }

}