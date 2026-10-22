package chapter02.chiken;

public class Main {
    public static void main(String[] args) {
        // Repository
        ChickenRepository chickenRepo = new ChickenRepository();
        CustomerRepository customerRepo = new CustomerRepository();
        OrderRepository orderRepo = new OrderRepository();

        // Service
        ChickenService chickenService = new ChickenService(chickenRepo);
        CustomerService customerService = new CustomerService(customerRepo);
        OrderService orderService = new OrderService(orderRepo, chickenRepo, customerRepo);

        // 초기 데이터 (시드)
        chickenService.registerChicken(1, "후라이드", 18000);
        chickenService.registerChicken(2, "양념치킨", 20000);
        chickenService.registerChicken(3, "뿌링클", 23000);
        chickenService.registerChicken(4, "황금올리브", 25000);

        customerService.registerCustomer(1, "홍길동", "VIP");
        customerService.registerCustomer(2, "김철수", "NORMAL");
        customerService.registerCustomer(3, "이영희", "NEWBIE");

        // 컨트롤러 실행
        new ChickenController(chickenService, customerService, orderService).run();
    }
}
