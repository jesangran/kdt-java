package chapter02.lesson4;

public class DualRoleApp {
    public static void main(String[] args) {
        Stampslip stampSlip = new Stampslip();

        Printable printable = stampSlip;
        Dated dated = stampSlip;

        printable.print();

        System.out.println("day= "+dated.dayLabel());
    }
}
