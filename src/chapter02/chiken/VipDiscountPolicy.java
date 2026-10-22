package chapter02.chiken;

public class VipDiscountPolicy implements DiscountPolicy{

    @Override
    public int discount(int price) {
        return (int)(price * 0.9); //double이라서 형변환 필요
    }
}
