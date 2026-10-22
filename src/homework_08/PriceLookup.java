package homework_08;

import java.util.HashMap;
import java.util.Scanner;

public class PriceLookup {
    public static void main(String[] args) {
        HashMap<String, String> prices = new HashMap<>();
        Scanner sc = new Scanner(System.in);

        prices.put("kimbap", "4500");
        prices.put("water", "1000");
        prices.put("cookie", "2000");

        System.out.println("menu?");
        String menu = sc.nextLine();

        if(prices.containsKey(menu)){
            System.out.println("price="+prices.get(menu));
        }else{
            System.out.println("missing");
        }

    }
}
