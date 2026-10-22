package chapter02.lesson3;

public class Author {

    private final String name; //상수 각 객체마다 하나씩 가지고 있는데, 한 번 정하면 바꿀 수 없다.
    //private static final double PI = 3.14;
    // 모든 객체가 하나를 같이 사용하고, 한 번 정하면 바꿀 수 없다.
    //static이 붙으면 객체마다 생기는 게 아니라 클래스에 하나만 존재, 공통 상수, 클래스에 소속
    //static final = 클래스가 공유하는 변경 불가능한 값(상수)

    public String getName(){
        return name;
    }

    public Author(String name){
        this.name = name;
    }
}
