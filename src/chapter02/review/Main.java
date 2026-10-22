package chapter02.review;

public class Main {

    public static void main(String[] args) {

        AnimalRepository animalRepository = new AnimalRepository();
        SoundPolicy dogSoundPolicy = new DogSoundPolicy();
        //SoundPolicy catSoundPolicy = new CatSoundPolicy();
        //AnimalService animalService = new AnimalService(animalRepository, dogSoundPolicy, catSoundPolicy);
        AnimalService animalService = new AnimalService(animalRepository, dogSoundPolicy);
        AnimalController animalController = new AnimalController(animalService);


        Animal a1 = animalController.requestAnimal("바둑이");
        Animal a2 = animalController.requestAnimal("");

        System.out.println(a1.getId() + " / " + a1.getName() + " / " + a1.getSound());
        System.out.println(a2.getId() + " / " + a2.getName() + " / " + a2.getSound());
    }
}
