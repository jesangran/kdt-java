package chapter02.review;

public class RateDiscountPolicy implements DiscountPolicy{

    private static final int RATE = 10;

    @Override
    public int discount(int originalPrice) {
        //return originalPrice * 90 / 100;
        return originalPrice * (100 - RATE) / 100 ;
    }
}
