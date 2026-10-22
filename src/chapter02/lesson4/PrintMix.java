package chapter02.lesson4;

import java.util.ArrayList;

public class PrintMix {

    public static void main(String[] args) {
        ArrayList<Printable> lines = new ArrayList<>();
        lines.add(new Post("closed", "no class"));
        lines.add(new LunchSlip());
        lines.add(new CheckSlip());

        for(Printable line : lines) {
            line.print();
            System.out.println("==================");
        }

        Printable t1 = new Post("closed", "no class");
        Printable t2 = new LunchSlip();

    }
}
