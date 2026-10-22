package chapter02.chiken;

import java.util.List;
import java.util.Scanner;

public class ChickenController {
    private final Scanner sc = new Scanner(System.in);
    private final ChickenService chickenService;
    private final CustomerService customerService;
    private final OrderService orderService;

    private int orderSequence = 1;

    public ChickenController(ChickenService chickenService, CustomerService customerService, OrderService orderService) {
        this.chickenService = chickenService;
        this.customerService = customerService;
        this.orderService = orderService;
    }

    public void printMenu(){
        System.out.println("====치킨왕국====");
        System.out.println("1. 메뉴 조회  2. 고객 등록  3. 주문하기  4. 주문 내역  0. 종료");
        System.out.print("선택 > ");
    }

    public void handleChickenList(){
        List<Chicken> chickens = chickenService.getAllChickens();
        System.out.println("====치킨메뉴====");
        for(Chicken chicken : chickens){
            System.out.println(chicken.getId() + ". " + chicken.getName() + " - " + chicken.getPrice() + "원");
        }
    }

    private int readInt() {
        try {
            int value = Integer.parseInt(sc.nextLine().trim());
            return value;
        } catch (NumberFormatException e) {
            // 숫자가 아닌 입력은 -1로 처리하여 "잘못된 입력" 분기로 보낸다.
            return -1;
        }
    }

    private String readLine() {
        return sc.nextLine().trim();
    }

    public void handleRegisterCustomer() {
        System.out.print("고객 ID: ");
        int id = readInt();

        System.out.print("이름: ");
        String name = readLine();

        System.out.print("등급 (NORMAL/VIP/NEWBIE): ");
        String grade = readLine();

        try {
            customerService.registerCustomer(id, name, grade);
            System.out.println("고객이 등록되었습니다. "+name);
        } catch (IllegalArgumentException e) {
            System.out.println("[오류] " + e.getMessage());
        }
    }

    public void handleOrder(){

        System.out.print("[고객 ID 입력] ");
        int customerId = readInt();

        System.out.print("[치킨 ID 입력] ");
        int chickenId = readInt();

        try {
            Order order = orderService.order(orderSequence, customerId, chickenId);
            orderSequence++;

            Customer customer = customerService.getCustomer(customerId);
            String grade = customer.getGrade();

            if (grade.equals("VIP")) {
                System.out.println("[등급 확인] VIP 고객님! 10% 할인 적용됩니다.");
            } else if (grade.equals("NEWBIE")) {
                System.out.println("[등급 확인] NEWBIE 고객님! 20% 할인 적용됩니다.");
            } else if (grade.equals("NORMAL")) {
                System.out.println("[등급 확인] NORMAL 고객님! 할인 없이 결제됩니다.");
            }

            System.out.println("결제 금액: " + order.getFinalPrice() + "원 (원가 "+ order.getOriginalPrice() + "원)");
            System.out.println("주문이 완료되었습니다!");

        } catch (IllegalArgumentException e) {
            System.out.println("[오류] " + e.getMessage());
        }
    }

    public void handleOrderList() {
        List<Order> orders = orderService.getAllOrders();

        System.out.println("----- 주문 내역 -----");
        if (orders.isEmpty()) {
            System.out.println("주문 내역이 없습니다.");
            return;
        }

        for (Order order : orders) {
            System.out.println("주문번호 #: " + order.getOrderId()
                    + " | 고객ID: " + order.getCustomerId()
                    + " | 치킨ID: " + order.getChickenId()
                    + " | 원가: " + order.getOriginalPrice() + "원"
                    + " | 결제: " + order.getFinalPrice() + "원");
        }
    }

    public void run() {
        boolean running = true;

        while (running) {
            printMenu();
            int menu = readInt();

            // switch 대신 if ~ else if 로 분기.
            if (menu == 1) {
                handleChickenList();
            } else if (menu == 2) {
                handleRegisterCustomer();
            } else if (menu == 3) {
                handleOrder();
            } else if (menu == 4) {
                handleOrderList();
            } else if (menu == 0) {
                System.out.println("종료합니다. 안녕히 가세요!");
                running = false;
            } else {
                System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            }
        }
    }

}
