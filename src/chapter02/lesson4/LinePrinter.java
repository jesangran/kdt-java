package chapter02.lesson4;

public class LinePrinter {
    private final Printable target;

    public LinePrinter(Printable printable) {
        this.target = printable;
        //this.target = new LunchSlip(); //의존성이 강해진다
    }

    public void run() {
        target.print();
    }
}
