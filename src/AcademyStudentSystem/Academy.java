package AcademyStudentSystem;

import java.util.ArrayList;
import java.util.HashMap;

public class Academy {
    ArrayList<Student> list = new ArrayList<>();
    HashMap<Integer, Student> map = new HashMap<>();

    //등록 기능 만들기
    public void addStudent(int no, String name, String subject, int score){
        Student s = new Student();
        s.setInfo(no, name, subject, score);

        //같은 객체 Student를 list, map에 동시 저장
        list.add(s);
        map.put(no,s);

        System.out.println("등록완료: "+ name);

        //list와 map은 같은 객체 Student를 가르킬뿐 복사본이 x
    }

    //조회 기능 만들기
    public void prindAll() {
        //list를 순회하며 학생정보 출력
        for(Student s : list){
           s.prindInfo(); //Student클래스에서 만들었으니까 만든기능 호출
        }
    }

    //map에서 없는 번호를 찾아서 단건 출력
    public void printOne(int no) {
        Student s = map.get(no);
        if (s == null) {
            System.out.println("없는번호: "+ no);
            return;
        }
        s.prindInfo();
    }

    //수정 기능 만들기
    public void updateScore(int no, int newScore) {
        Student s = map.get(no);

        if (s == null) {
            System.out.println("없는번호: "+ no);
            return;
        }

        //점수가 0~100 범위를 벗어나면 안내 문구 출력 후 중단
        if (newScore < 0 || newScore > 100) {
            System.out.println("점수는 0~100 사이여야 합니다: " + newScore);
            return;
        }

        //map에서 학생을 찾아 score만 변경
        s.score = newScore;
        //map에 새로운 값을 덮어 씌우기

        /*
        * 이 메서드를 실행한 뒤 printAll()을 호출하면 list 쪽 결과도 바뀜
        * map과 list가 애초에 "같은 객체"를 참조
        * */
    }

    //삭제 기능 만들기
    public void deleteStudent(int no) {
        Student s = map.remove(no); //map에서 제거 > 동시에 객체를 반환
        // map에서 no로 객체를 꺼내고 list에서 객체 제거

        if (s == null) {
            System.out.println("없는번호: "+ no);
            return;
        }

        list.remove(s); // list에서 같은 객체를 참조하기때문에 같은 객체를 제거
        //메모리상 같은 객체를 찾아 지움, map -> list로 객체 넘기기
        //Object 클래스에 정의된 equals()를 그대로 사용하면 메모리 주소값을 비교 > false반환
        //String 클래스에서는 equals()함수를 재정의(오버라이딩)해서 문자가 같은지 검증 > 문자 비교 true반환

        System.out.println("삭제완료: "+ no);
    }

    //과목별 학생 찾기
    public ArrayList<Student> findAllBySubject(String subject) {
        ArrayList<Student> result  = new ArrayList<>();

        //list를 순회하며 subject가 같은 학생만 새 리스트에 담아 반환
        //결과가 없으면 null이 아니라 빈 리스트를 반환
        for (Student s : list) {
            if (s.subject.equals(subject)) {
                result.add(s);
            }
        }

        return result; //하나도 못 찾아도 빈 리스트를 반환 (null 아님)
    }

    //과목별 학생 출력하기
    public void printBySubject(String subject) {

        ArrayList<Student> result = findAllBySubject(subject);
        // 필터링 findAllBySubject 메서드를 그대로 재사용

        if(result.isEmpty()) { //리스트가 비어있는지 확인하는 함수
            System.out.println("해당 과목 수강생이 없습니다. :"+subject);
            return;
        }

        //결과가 있으면 헤더를 출력한 뒤 각 학생의 printInfo() 호출
        System.out.println("==" + subject + "수강생 ==");
        for (Student s : result) {
            s.prindInfo();
        }

    }

    public static void main(String[] args) {
        Academy academy = new Academy();

        //1. 4명 등록하기
        academy.addStudent(1, "홍길동", "Java", 90);
        academy.addStudent(2, "김철수", "Java", 85);
        academy.addStudent(3, "이영희", "Python", 95);
        academy.addStudent(4, "박민수", "Java", 70);

        //전체 조회
        System.out.println("\n--전체조회--");
        academy.prindAll();

        //2번 점수를 100으로 수정 → 전체 조회
        System.out.println("\n--- 2번 점수 100으로 수정 ---");
        academy.updateScore(2,100);
        academy.prindAll();

        //1번 삭제 → 전체 조회
        System.out.println("\n--- 1번 삭제 ---");
        academy.deleteStudent(1);
        academy.prindAll();

        //3번 단건 조회
        System.out.println("\n--- 3번 단건 조회 ---");
        academy.printOne(3);

        //과목별 조회: Java, Python, C++(존재하지 않는 과목)
        System.out.println("\n--- 과목별 조회 ---");
        academy.printBySubject("Java");
        academy.printBySubject("Python");
        academy.printBySubject("C++");

    }



}
