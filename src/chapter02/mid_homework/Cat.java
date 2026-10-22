package chapter02.mid_homework;

public class Cat extends Animal{
    public Cat (String name, int age) {
        super(name, age);
    }

    @Override
    public String sound(){
        return "nyanya";
    }

    @Override
    public String move() {
        return "살금살금 걷는다";
    }
}