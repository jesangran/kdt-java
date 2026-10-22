package separate_AcademyStudentSystem;

import java.util.ArrayList;
import java.util.HashMap;

//Academy가 했던 "저장 관련 작업"만 순수하게 떼어냅니다.
//이 클래스는 오직 저장하고, 찾고, 지우는 일만 하며, 그 어떤 판단(검증)이나 출력도 하지 않는다는 원칙
public class StudentRepository {

    ArrayList<Student> list = new ArrayList<>();
    HashMap<Integer, Student> map = new HashMap<>();

    //list, map 양쪽에 저장
    void save(Student s){
        list.add(s);
        map.put(s.no, s);
    }

    // 전체 조회용 리스트를 그대로 반환
    ArrayList<Student> findAll() {
        return list;
    }

    //번호로 단건 조회 (없으면 null)
    Student findByNo(int no){
        return map.get(no);
    }

    // 점수 변경 (검증은 이미 Service에서 끝났다고 가정하고 값만 바꾼다)
    void updateScore(Student s, int newScore) {
        s.score = newScore;
    }

    // 삭제: map에서 꺼낸 뒤 같은 객체를 list에서도 제거
    void delete(int no) {
        Student s = map.remove(no);
        if (s != null) {
            list.remove(s);
        }
    }

    // 특정 과목 학생만 걸러서 반환 (출력은 하지 않는다)
    ArrayList<Student> findAllBySubject(String subject) {
        ArrayList<Student> result = new ArrayList<>();

        for (Student s : list) {
            if (s.subject.equals(subject)) {
                result.add(s);
            }
        }

        return result;
    }





}
