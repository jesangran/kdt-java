package chapter02.chiken;

public class Customer {

    private final int id;
    private final String name;
    private final String grade;

    public Customer (int id, String name, String grade){
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGrade() {
        return grade;
    }

}
