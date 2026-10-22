package chapter01;

import java.util.HashMap;

public class MapTest {
    public static void main(String[] args) {
        //key, value > 한 set
        HashMap<String, String> orders = new HashMap<>();

        //데이터인입
        orders.put("mina", "americano");
        orders.put("jun", "kimbap");
        //key값을 통해 value를 가져온다, key값은 고유해야함

        System.out.println(orders.get("mina")); //key를 통해 value를 호출하는 것이라서 value를 출력한다

        orders.put("mina", "water"); //기존 key값에 다른 value를 넣으면 그 값을 update함
        System.out.println("americano>water= "+orders.get("mina"));

        orders.replace("jun","dukbboki");
        System.out.println("kimbap>dukbboki= "+orders.get("jun"));

        //조건부수정
        orders.replace("mina","water", "cafelatte");
        //oldValue값이 맞다면 newValue로 바꿈
        System.out.println("water>cafelatte= "+orders.get("mina"));

        //key유무 확인 : containKey("키값")
        if(orders.containsKey("luna")){
            System.out.println(orders.get("mina"));
        }else {
            System.out.println("missing");
        }

        //key+값 추출
        for (String name : orders.keySet()) {
            System.out.println(name + " * " + orders.get(name));
        }

        for (String value : orders.values()){
            System.out.println(value);
        }

    }
}
