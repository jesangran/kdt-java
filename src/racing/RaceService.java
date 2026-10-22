package racing;

import java.util.ArrayList;

/**
 * [Service] 경주 저장소만 참조. 말/기수 Repository는 모른다.
 * enter: 컨트롤러가 넘겨준 이름·전략번호로 addEntry.
 * clearRecords / addRecord / recordsOf: 경주 시작·기록 보기용.
 */
public class RaceService {
    private final RaceRepository repository;

    public RaceService(RaceRepository repository) {
        this.repository = repository;
    }

    public Race create(String title) {
        if (title.equals("")) {
            throw new IllegalArgumentException("경주 이름이 비어 있습니다.");
        }
        return repository.save(title);
    }

    public Race findById(int id) {
        return repository.findById(id);
    }

    public void enter(int raceId, String horseName, String jockeyName, int strategyChoice) {
        Race race = repository.findById(raceId);
        race.addEntry(horseName, jockeyName, strategyChoice);
    }

    public void clearRecords(int raceId) {
        Race race = repository.findById(raceId);
        race.clearRecords();
    }

    public void addRecord(int raceId, String line) {
        Race race = repository.findById(raceId);
        race.addRecord(line);
    }

    public ArrayList<String> recordsOf(int raceId) {
        Race race = repository.findById(raceId);
        return race.getRecords();
    }
}
