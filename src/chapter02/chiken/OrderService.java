package chapter02.chiken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {

    private final OrderRepository orderRepository;
    private final ChickenRepository chickenRepository;
    private final CustomerRepository customerRepository;

    private final Map<String, DiscountPolicy> policyMap = new HashMap<>();

    public OrderService(OrderRepository orderRepository, ChickenRepository chickenRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.chickenRepository = chickenRepository;
        this.customerRepository = customerRepository;

        //컨트롤러에서 사용
        policyMap.put("NORMAL", new NormalDiscountPolicy());
        policyMap.put("VIP", new VipDiscountPolicy());
        policyMap.put("NEWBIE", new NewbieDiscountPolicy());
    }

    public Order order(int orderId, int customerId, int chickenId) {
        Customer customer = customerRepository.findById(customerId); //고객id
        Chicken chicken = chickenRepository.findById(chickenId); //어떤 치킨을 주문할 것인지

        DiscountPolicy policy = policyMap.get(customer.getGrade());
        //고객등록시점에 이미 한번 검증하지만 한번 더 방어
        if (policy == null) {
            throw new InvalidGradeException(customer.getGrade());
        }

        int originalPrice = chicken.getPrice(); //원래가격
        int finalPrice = policy.discount(originalPrice); // 정책에 적용된 가격

        //Order order = new Order(orderId, customerId, chickenId, originalPrice, finalPrice);
        Order order = new Order(orderId, customer.getId(), chicken.getId(), originalPrice, finalPrice);
        orderRepository.save(order);

        return order;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

}
