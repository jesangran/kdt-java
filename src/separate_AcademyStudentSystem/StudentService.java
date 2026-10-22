package separate_AcademyStudentSystem;

import java.util.ArrayList;

public class StudentService {
    //스프링에서는 @Autowired로 대체 > 의존성 주입
    StudentRepository repository = new StudentRepository();

    void join(int no, String name, String subject, int score){

        //null이 아니면 "이미 존재하는 번호: {no}" 출력 후 중단
        if(repository.findByNo(no) != null) {
            System.out.println("이미 존재하는 번호: " + no);
            return;
        }

        //통과하면 Student 객체를 만들어 setInfo(...) 호출
        Student s = new Student();
        s.setInfo(no, name, subject, score);

        // 저장
        repository.save(s);
        System.out.println("등록 완료: " + name);
    }

    // 전체 학생을 Repository를 통해 가져와 출력
    void printAll(){
        for(Student s : repository.findAll()){
            s.prindInfo();
        }
    }

    //번호로 학생을 찾아 단건 출력
    void printOne(int no) {
        Student s = repository.findByNo(no);
        if(s == null) {
            System.out.println("없는 번호: " + no);
            return;
        }

        s.prindInfo();
    }

    void changeScore(int no, int newScore) {
        //없는 번호면 안내문구 출력 후 중단
        Student s = repository.findByNo(no);
        if(s == null) {
            System.out.println("없는 번호: " + no);
            return;
        }

        //점수가 0~100 범위를 벗어나면 안내 문구 출력 후 중단
        if (newScore < 0 || newScore > 100) {
            System.out.println("점수는 0~100 사이여야 합니다: " + newScore);
            return;
        }

        //통과하면 repository.updateScore(s, newScore) 호출
        repository.updateScore(s, newScore);

        //수정완료 no 출력
        System.out.println("수정 완료: " + no);
    }

    void remove(int no) {
        //없는 번호면 안내 문구 출력
        Student s = repository.findByNo(no);
        if(s == null) {
            System.out.println("없는 번호: " + no);
            return;
        }

        repository.delete(no);

        System.out.println("삭제 완료: " + no);
    }

    void printBySubject(String subject) {

        ArrayList<Student> result = repository.findAllBySubject(subject);

        //결과가 비어 있으면 "해당 과목 수강생이 없습니다:  {no}"
        if(result.isEmpty()){
            System.out.println("해당 과목 수강생이 없습니다: " + subject);
            return;
        }

        //있으면 헤더 출력 후 각 학생 printInfo()
        System.out.println("== " + subject + " 수강생 ==");
        for(Student s : result) {
            s.prindInfo();
        }

    }
}
