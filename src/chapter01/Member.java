package chapter01;

public class Member {
    String id;
    String name;
    int age;

    public void isNameKim() {
        if (name.equals("kim")){
            System.out.println("oh!");
        }else {
            System.out.println("oh2!");
        }
    }
}
