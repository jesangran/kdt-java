package avengers;

/**
 * ============================================================
 * [전략 패턴] 전투 방식 인터페이스 — songpyeon의 SongpyeonStylePolicy와 같은 자리
 * ============================================================
 * BattleStrategy s = new MeleeStrategy(); // 또는 Ranged / Support
 * s.damage(); s.teamScore(); s.label();   // 호출은 같고 결과만 다름
 *
 * 구현: Melee(40/15/근접), Ranged(25/25/원거리), Support(10/40/지원)
 */
public interface BattleStrategy {
    int damage();

    int teamScore();

    String label();
}
