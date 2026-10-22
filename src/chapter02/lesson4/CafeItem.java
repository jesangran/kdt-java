package chapter02.lesson4;

public abstract class CafeItem {
    //추상클래스 Post post = new Post(); x
    //추상 클래스는 직접 객체를 생성할 수 없고, 추상 클래스를 상속받은 일반(구체) 자식 클래스의 객체는 생성할 수 있음
    //자바 클래스는 일반적으로 하나의 클래스만 상속받을 수 있음

    public abstract int price(); //함수, 반환값 앞에 붙이면 추상 메서드
    //추상클래스는 자식클래스에서 구현을 반드시 해줘야함

    public void printWon(){
        System.out.println("won= "+price());
    }

    //공통로직이나 변수를 접근할때 많이 사용
}
