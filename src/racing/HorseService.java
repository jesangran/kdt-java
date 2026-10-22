package racing;

/** [Service] 말 등록·조회. 빈 이름이면 번호 증가 전에 예외. */
public class HorseService {
    private final HorseRepository repository;

    public HorseService(HorseRepository repository) {
        this.repository = repository;
    }

    public Horse register(String name) {
        if (name.equals("")) {
            throw new IllegalArgumentException("말 이름이 비어 있습니다.");
        }
        return repository.save(name);
    }

    public Horse findById(int id) {
        return repository.findById(id);
    }
}
