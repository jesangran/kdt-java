package chapter02.review;

import java.util.HashMap;
import java.util.Map;

public class AnimalRepository {
    private final Map<Long, Animal> store = new HashMap<>();
    //store을 db대체로 사용
    private Long sequence = 0L; //고유한 기본 key

    public Animal save(Animal animal) {
        //sequence++;
        sequence += 1 ;
        animal.assignId(sequence);
        store.put(sequence, animal);

        return animal;
    }

    public Animal findById(Long id){
        return store.get(id); //반환값이 없을 경우 null
    }
}
