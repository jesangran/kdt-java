package chapter02.review;

public class FixedDiscountPolicy implements DiscountPolicy{

    private static final int FIXED_AMOUNT = 1000;

    @Override
    public int discount(int originalPrice) {
        //자바에서 제공하는 기본 라이브러리 Math를 통해 수학연산에 도움을 주는 메서드를 쓸 수 있다
        //결과가 0미만이면 0을 반환
        return Math.max(0, originalPrice - FIXED_AMOUNT);
    }
}
