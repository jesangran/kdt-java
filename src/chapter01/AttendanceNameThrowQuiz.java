package chapter01;

public class AttendanceNameThrowQuiz {
    public void takeName(String name) throws IllegalArgumentException {
        if(name == null | name.equals("")){
            throw new IllegalArgumentException("empty-name");
        }
        System.out.println("name="+name);
    }

    public static void main(String[] args) {
        AttendanceNameThrowQuiz app = new AttendanceNameThrowQuiz();
        app.takeName(""); //IllegalArgumentException
        app.takeName("kim");
    }
}
