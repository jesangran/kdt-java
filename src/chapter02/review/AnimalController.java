package chapter02.review;

public class AnimalController {
    private final AnimalService animalService;

    public AnimalController(AnimalService animalService){
        this.animalService = animalService;
    }

    public Animal requestAnimal (String name) {
        return animalService.register(name);
    }
}
