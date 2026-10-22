package chapter02.lesson5;

public class EmptyTitleException extends IllegalArgumentException {
    //커스텀예외
    public EmptyTitleException() { //클래스 생성자
        super("empty-title"); //IllegalArgumentException("empty-title");
    }
}
