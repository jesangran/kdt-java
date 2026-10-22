package chapter01;

// class : 설계도
public class Post {
    String title; //필드 > 자원 / 자원에 값을 넣는게 행위
    String body; //필드

    public void print(){ //void : 반환값, print(함수이름)
        System.out.println(title);
        System.out.println(body);
        //void는 반환할 type이 없어서 return문을 사용하지 않는다.
    }

    public String showTitle() {
        //반환할 값 > 반환할 값이 String이라서 return문을 반드시 사용, return 값에는 반환할 type이 맞아야함
        return "okay";
    }

    /*
    함수이름은 plusTitle 이고 반환값은 문자열이다. 반환할 값은 title 필드 값에 있는 문자열 더하기 "1"을 해주면된다.
    */
    public String titlePlus() {
        return title+"1";
    }

    public String titleBody() {
        return body+"1";
    }

    public boolean isBody() {
       /* if(body == null){
            return true;
        } else {
            return false;
        } return문을 만나는 순간 함수는 종료 */

        return body == null; //Simple is best

        //return false;
    }

    //아규먼트 (argument) : 인자
    public void pringArg(String arg){
        System.out.println(arg);
        //String arg = arg 변수를 본문에 쓴 것
    }

    public void addBody(String arg) {
        System.out.println(body + arg);
    }

    public void addTitle(String arg, String arg2){
        System.out.println(title + arg + arg2);
    }

    public void test (String arg, int ...ints) {
        //가변인자, String arg만 넣어도 됨
        //String ...arg, > 에러, 맨 마지막에 넣어줘야한다.
    }
    //배열(array) : 길이가 정해져있다. 메모리 고정 할당


}
