package chapter02.lesson4;

public class CafeMenuApp {
    public static void main(String[] args) {
        //new CafeItem() > abstract 인스턴스화x
        CafeItem kimbapItem = new KimbapItem();
        kimbapItem.printWon();

        CafeItem[] items = {
                new KimbapItem(),
                new WaterItem()
        };

        //CafeItem[] items = new CafeItem[2];

        for (CafeItem item : items) {
            item.printWon();
        }
    }

    //인터페이스 : 구현하는 클래스에게 지켜야 할 규칙을 정한다,인터페이스에 명시된 규칙이나 기능을 사용할 수 있어야함
    //해야할 역할 지정
}
