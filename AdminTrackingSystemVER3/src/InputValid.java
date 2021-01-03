import java.util.Scanner;

public class InputValid {

    public static int checkRange(int min, int max) {
        Scanner input = new Scanner(System.in);

        while(true){
            String choice = input.nextLine();

            if(!checkInt(choice)) {
                System.out.println("Please enter number.");
                continue;
            }
                if(Integer.parseInt(choice) < min || Integer.parseInt(choice) > max)
                    System.out.println("Invalid choice.\nPlease only enter between " + min + " and " + max);
                else
                    return Integer.parseInt(choice);
            }
        }

    public static boolean checkInt(String str1){
        if(str1.isBlank() || str1.isEmpty())
            return false;
        try {
            int i = Integer.parseInt(str1);
        }
        catch (NumberFormatException nfe){
                return false;
        }
        return true;
    }

    public static String checkValidChar(){
        Scanner input = new Scanner(System.in);

        while (true){
            String ch = input.nextLine();
            if(!(ch.equalsIgnoreCase("Y") || ch.equalsIgnoreCase("N")))
                System.out.println("Invalid input.\nPlease only input Y / N.");
            else
                return ch;

        }
    }
}
