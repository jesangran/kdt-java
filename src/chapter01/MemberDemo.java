package chapter01;

import java.util.ArrayList;

public class MemberDemo {
    public static void main(String[] args) {
        ArrayList<Member> list = new ArrayList<>();
        Member mem = new Member();

        mem.id = "m1";
        mem.name = "Kim";
        mem.age = 20;

        list.add(mem);

        for(Member m : list) {
            System.out.println(m.id+"+"+m.name+"+"+m.age);
        }

        mem.isNameKim();

    }
}
