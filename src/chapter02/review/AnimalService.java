package chapter02.review;

public class AnimalService {

    private final AnimalRepository animalRepository;
    private final SoundPolicy soundPolicy;

    public AnimalService (AnimalRepository animalRepository, SoundPolicy soundPolicy){
        this.animalRepository = animalRepository;
        this.soundPolicy = soundPolicy;
    }

    public Animal register(String name) {

        String sound = soundPolicy.makeSound(name); //울음소리 결정 메서드
        Animal animal = new Animal(name, sound);

        return animalRepository.save(animal);
    }

    public Animal getAnimal(Long id) {
        return animalRepository.findById(id);
    }

    //ctrl + alt + m > 메서드 추출


}
