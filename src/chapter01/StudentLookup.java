package chapter01;

import java.util.HashMap;

public class StudentLookup {
    public static void main(String[] args) {
        //key : 학번, value : 학생이름 / 변수이름 student

        HashMap<String, String> students = new HashMap<>();

        /*students.put("2024001", "김민");
        students.put("2024002", "이준");

        System.out.println(students.get("2024001"));
        System.out.println(students.get("2024002"));
        System.out.println(students.get("2024999"));*/

        students.put("2024001", "김민");
        students.put("2024001", "김민수"); // 동일한 key가 있기때문에 덮어 씀

        System.out.println(students.get("2024001"));
        System.out.println(students.size()); //key를 기준으로 1개

        students.put("2024002", "이준");
        System.out.println(students.size());

        if(students.containsKey("2024001")){
            System.out.println(students.get("2024001"));
        }else {
            System.out.println("missing");
        }

        if(students.containsKey("2024999")){
            System.out.println(students.get("2024999"));
        }else {
            System.out.println("missing");
        }

        for (String student : students.keySet()){
            System.out.println(student+":"+students.get(student));
        } //for문이 keySet()에서 꺼낸 key를 student라는 변수에 넣어주는 것

        students.put("2024003","박소라");
        students.put("2024001", "김민");

        System.out.println(students.get("2024001"));
        System.out.println(students.get("2024003"));
        System.out.println(students.size());

        String[] ids = {"2024004", "2024005", "2024004"};
        String[] names = {"최윤", "정하", "최윤서"};

        for (int i = 0; i < ids.length; i++){
            students.put(ids[i],names[i]);
        }

        System.out.println(students.size());
        System.out.println(students.get("2024004"));
        System.out.println(students.get("2024005"));

    }
}
