package chapter02.lesson3;

public class Human {
    private String name;
    private int age;
    private boolean isMale;

    private static int money;


    public Human(String name, int age, boolean isMale) {
        this.name = name;
        this.age = age;
        this.isMale = isMale;
        money += 10000; //1명이 증가할때마다 만원씩 증가
    }

    public static int getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }
}

