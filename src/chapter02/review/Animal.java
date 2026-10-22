package chapter02.review;

public class Animal { //도매인클래스

    private Long id; //생성자가 2개라서 final로 선언 x
    private final String name;
    private final String sound;

    public Animal(String name, String sound) {
        this.name = name;
        this.sound = sound;
    }

    public void assignId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSound() {
        return sound;
    }

    public Long getId() {
        return id;
    }
}
