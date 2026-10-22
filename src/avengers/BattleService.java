package avengers;

/**
 * ============================================================
 * [Service] 저장소 없는 서비스 — "전략 선택"만 담당
 * ============================================================
 * songpyeon에서는 SongpyeonService 안에서 new Pretty/Quick 을 했다면,
 * 여기선 선택만 BattleService로 분리했다.
 *
 * choose 반환 타입은 BattleStrategy (인터페이스)
 * → 호출하는 쪽은 Melee인지 Support인지 몰라도 damage()만 호출하면 된다.
 *
 * 전략을 HashMap에 미리 담아 두지 않는다. if로 new 한다. (과제 규칙)
 */
public class BattleService {
    public BattleStrategy choose(int choice) {
        if (choice == 1) {
            return new MeleeStrategy();
        } else if (choice == 2) {
            return new RangedStrategy();
        } else if (choice == 3) {
            return new SupportStrategy();
        } else {
            throw new InvalidStrategyException("전투 방식은 1, 2, 3입니다.");
        }
    }
}
