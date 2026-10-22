package chapter02.mid_homework;

public class Animal {
    //private 필드 두 개
    private String name;
    private int age;

    public static int count = 0;

    //두 필드를 초기화.
    public Animal(String name, int age){

        if(age <= 0) {
            throw new IllegalArgumentException("나이는 1 이상이어야 합니다.");
        }

        this.name = name;
        this.age = age;
        count ++; //하나씩 증가
    }

    //public getter
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public static int getCount() {
        return count;
    }

    //sound()
    public String sound(){
        //System.out.println("...");
        return "...";
    }

    public String move(){
        return "이동한다";
    }
}
