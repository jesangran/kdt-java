package separate_AcademyStudentSystem;

public class Student {
    int no; //학생번호
    String name; //학생이름
    String subject; //수강과목
    int score; //점수

    void setInfo (int pNo, String pName, String pSubject, int pScore){
        no = pNo;
        name = pName;
        subject = pSubject;
        score = pScore;
    }

    void prindInfo () {
        System.out.println(no +"/"+ name +"/"+ subject +"/"+ score);
    }

}
