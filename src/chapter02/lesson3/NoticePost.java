package chapter02.lesson3;

public class NoticePost extends Post{
    //Post : 부모, NoticePost : 자식 > 상속
    //부모로부터 자동으로 상속

    private String place; //Post에서는 해당 변수를 모름! 자식이 추가적으로 갖는 필드

    public NoticePost(String title, String body, String place) {
        super(title, body); //super 부모(Post)
        this.place = place; //this NoticePost
    }

    //부모로부터 받은 메서드를 덮어쓸 수 있음
    @Override
    public void print(){
        System.out.println("place="+this.place);
        super.print();
    }

    public void print(int a){
        System.out.println(a); //맞는 인자값이 들어올때 찍힘
    }

}
