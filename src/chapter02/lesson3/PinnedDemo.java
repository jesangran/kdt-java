package chapter02.lesson3;

public class PinnedDemo {
    public static void main(String[] args) {
        PinnedPost pin = new PinnedPost("exam", "bring id");
        //Post pin2 = new PinnedPost("exam", "bring id");

        pin.changeTitle("quiz"); //reject=pinned, this.pinned
        pin.pin();
        pin.changeTitle("closed");
        pin.print();
    }
}
