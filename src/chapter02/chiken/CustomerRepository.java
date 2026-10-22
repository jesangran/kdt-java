package chapter02.chiken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepository {

    private final Map<Integer, Customer> store = new HashMap<>();

    public void save(Customer customer) {
        store.put(customer.getId(), customer);
    }

    public Customer findById(int id) {
        Customer found = store.get(id);
        if (found == null) {
            throw new CustomerNotFoundException(id);
        }
        return found;
    }

    public List<Customer> findAll() {
        return new ArrayList<>(store.values());
    }
}