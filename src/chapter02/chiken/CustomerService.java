package chapter02.chiken;

import java.util.List;

public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void registerCustomer(int id, String name, String grade) {

        //예외부터 생성
        boolean valid = grade.equals("NORMAL")||grade.equals("VIP")||grade.equals("NEWBIE");
        if(!valid){
            throw new InvalidGradeException(grade);
        }

        customerRepository.save(new Customer(id, name, grade));
        //Customer customer = new Customer(id, name, grade);
        //customerRepository.save(customer);
    }

    public List<Customer> getAllChickens(){
        return customerRepository.findAll();
    }

    public Customer getCustomer(int id) {
        return customerRepository.findById(id);
    }


}
