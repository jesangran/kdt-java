package avengers;

import java.util.ArrayList;

/**
 * [Service] 전투 로그 기록. BattleStrategy를 직접 받지 않는다.
 * Controller가 choose 후 label/damage/teamScore 숫자·문자열만 record에 넘긴다.
 * → 로그 서비스는 "저장 규칙"만, 전투 방식 선택은 BattleService 몫.
 */
public class MissionLogService {
    private final MissionLogRepository repository;

    public MissionLogService(MissionLogRepository repository) {
        this.repository = repository;
    }

    public MissionLog record(String missionTitle, String heroName, String strategyLabel, int damage, int teamScore) {
        if (missionTitle == null || missionTitle.equals("") || heroName == null || heroName.equals("")) {
            throw new IllegalArgumentException("미션과 히어로 이름이 필요합니다.");
        }
        int id = repository.nextId();
        MissionLog log = new MissionLog(id, missionTitle, heroName, strategyLabel, damage, teamScore);
        repository.save(log);
        return log;
    }

    public MissionLog findById(int id) {
        return repository.findById(id);
    }

    public ArrayList<MissionLog> findAll() {
        return repository.findAll();
    }
}
