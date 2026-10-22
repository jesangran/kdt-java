package chapter01;

public class LunchThrow {
    public void takeMenu (String menu) throws IllegalArgumentException {
        //예외를 던질 수 있다고 선언
        if(menu == null || menu.equals("")){
            //System.out.println("empty-menu");
            throw new IllegalArgumentException("empty-menu"); //내가 의도한 요구사항에 맞기 않기때문에
            //return;
        }

        //실행x
        System.out.println("menu="+menu);
    }

    public static void main(String[] args) {
        LunchThrow lunchThrow = new LunchThrow();

        lunchThrow.takeMenu(""); //여기서 stop, err 발생!
        lunchThrow.takeMenu("pork");
    }

    /*
    * 메서드 호출 스택
    *
    * Exception : checked(컴파일할때 강제로 확인) / unchecked(컴파일러가 강제로 확인하지 않음)
    *
    * */
}
