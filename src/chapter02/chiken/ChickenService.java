package chapter02.chiken;

import java.util.List;

public class ChickenService {
    private final ChickenRepository chickenRepository;

    public ChickenService(ChickenRepository chickenRepository) {
        this.chickenRepository = chickenRepository;
    }

    public void registerChicken(int id, String name, int price) {
        chickenRepository.save(new Chicken(id, name, price));
        //Chicken chicken = new Chicken(id, name, price);
        //chickenRepository.save(chicken);
    }

    public List<Chicken> getAllChickens() {
        return chickenRepository.findAll();
    }

    public Chicken getChicken(int id) { //id를 통해 단일 객체 반환
        return chickenRepository.findById(id);
    }
}
