package racing;

import java.util.ArrayList;

/**
 * [Step 1] 경주(Race)
 *
 * 요구사항:
 * - id, title → final (경주 번호/제목은 고정)
 * - 참가 정보는 ArrayList로 순서 유지
 *   · horseNames      : 말 이름
 *   · jockeyNames     : 기수 이름
 *   · strategyChoices : 전략 번호
 *   · records         : 기록 문장
 * - 같은 인덱스 i = 같은 참가 한 줄
 *   예) horseNames[0], jockeyNames[0], strategyChoices[0] → 1번 참가
 *
 * 출력 팁:
 * - Race가 println 하지 않음
 * - 호출하는 쪽에서 getter로 문장 조립
 *   예) "1위 " + race.horseNameAt(i) + " / " + race.jockeyNameAt(i)
 * - toString() 만들지 않음
 */
public class Race {
    private final int id;       // 경주 번호
    private final String title; // 경주 제목

    // 참가 목록 (인덱스끼리 짝을 맞춤)
    // final 이라 반드시 한 번은 할당해야 함 → 아래 생성자에서 new
    //
    // [방식 비교] 결과는 둘 다 "빈 ArrayList 준비"로 같음
    // ① 필드에서: private final ArrayList<String> horseNames = new ArrayList<>();
    //    → 객체 만들 때 자동 초기화, 생성자에 안 적어도 됨 (항상 빈 리스트면 이게 짧음)
    // ② 생성자에서: this.horseNames = new ArrayList<>();  ← 지금 이 방식
    //    → 생성 시점에 명시적으로 만듦, 생성자가 여러 개면 각각에 적어줘야 함
    // ※ 둘 다 쓰지 말 것 (필드에서 new 한 뒤 생성자에서 또 new 하면 첫 리스트는 버려짐)

    private final ArrayList<String> horseNames;
    private final ArrayList<String> jockeyNames;
    private final ArrayList<Integer> strategyChoices;
    // 기록 문장 (경주 결과 로그)
    private final ArrayList<String> records;

    public Race(int id, String title) {
        this.id = id;
        this.title = title;
        // ② 생성자 초기화: Race가 만들어질 때 네 리스트를 빈 상태로 준비
        this.horseNames = new ArrayList<>();
        this.jockeyNames = new ArrayList<>();
        this.strategyChoices = new ArrayList<>();
        this.records = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    /** 참가 한 줄 추가 — 말/기수/전략을 같은 인덱스로 세 리스트에 넣음 */
    public void addEntry(String horseName, String jockeyName, int strategyChoice) {
        horseNames.add(horseName);
        jockeyNames.add(jockeyName);
        strategyChoices.add(strategyChoice);
    }

    /** 참가 몇 명인지 (세 리스트 크기가 같아야 정상) */
    public int entryCount() {
        return horseNames.size();
    }

    /** index 위치의 말 이름 */
    public String horseNameAt(int index) {
        return horseNames.get(index);
    }

    /** index 위치의 기수 이름 */
    public String jockeyNameAt(int index) {
        return jockeyNames.get(index);
    }

    /** index 위치의 전략 번호 */
    public int strategyChoiceAt(int index) {
        return strategyChoices.get(index);
    }

    /** 기록 문장 전부 비우기 */
    public void clearRecords() {
        records.clear();
    }

    /** 기록 문장 한 줄 추가 */
    public void addRecord(String line) {
        records.add(line);
    }

    /**
     * 기록 목록 반환 — 방어적 복사
     * return records; 하면 안 됨 → 같은 리스트를 넘겨서 밖에서 clear() 하면 원본도 지워짐
     * 그래서 "내용만 복사한 새 ArrayList"를 돌려줌
     */
    public ArrayList<String> getRecords() {
        // 긴 버전 (같은 의미):
        // ArrayList<String> copy = new ArrayList<>();
        // for (String line : records) { copy.add(line); }
        // return copy;
        //
        // 짧은 버전: ArrayList 복사 생성자 — records 요소를 담은 새 리스트를 만들어 바로 반환
        // (for 복사를 한 줄로 줄인 것. 이미 새 리스트를 만들었으니 바로 return 가능)
        return new ArrayList<>(records);
    }
}
