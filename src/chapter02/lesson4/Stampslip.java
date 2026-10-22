package chapter02.lesson4;

public class Stampslip implements Printable, Dated{

    @Override
    public String dayLabel() {
       return "friday";
    }

    @Override
    public void print() {
        System.out.println("stamp==ok");
    }
}
