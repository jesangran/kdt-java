콘솔 애플리케이션을 여러 도메인에 반복 구현하면서  
**계층 분리 · 전략/정책 패턴 · 커스텀 예외**를 체득

| | |
|---|---|
| GitHub | https://github.com/jesangran/kdt-java |
| Email | `<이메일>` |
| Stack | Java · Collections · Stream/Lambda · 파일 I/O · JDBC · Git · IntelliJ |

---

## 소개

같은 설계를 **치킨 주문 → 송편 → 어벤져스 → 경마**처럼 도메인만 바꿔 적용했습니다.  
입출력은 Controller, 규칙은 Service, 저장은 Repository에 두고,  
달라지는 행동(할인·전투·경주)은 인터페이스로 빼는 방식을 반복했습니다.

목표: 이 구조를 Spring + DB 서비스로 확장하는 것.

---

## 대표 프로젝트

### 1. Avengers 미션 관리 · `src/avengers`

영웅·미션·미션 로그 CRUD와 전투 결과 계산을 다루는 콘솔 앱입니다. (17개 클래스)

| 구분 | 내용 |
|------|------|
| 계층 | Hero / Mission / MissionLog 각각 Controller · Service · Repository |
| 패턴 | `BattleStrategy` ← `MeleeStrategy` / `RangedStrategy` / `SupportStrategy` |
| 포인트 | `BattleService.choose()`가 전략 **선택만** 담당 → 호출부는 `damage()`만 호출 |
| 예외 | `HeroNotFoundException`, `InvalidStrategyException` |

**설계 의도**  
전투 방식이 늘 때 Service 곳곳의 `if`를 고치지 않고, 선택 지점과 실행 지점을 나눕니다.

---

### 2. Racing 경마 시스템 · `src/racing`

말·기수·경주 관리와 전략별 점수·상금 계산 앱입니다. (23개 클래스)

- **템플릿 메서드 + 전략**: `AbstractRaceStrategy.execute()`가 `prepare → run → finish` 순서를 `final`로 고정하고, 점수표만 `FrontRunner` / `Closer` / `Pacer` / `Marker`가 구현
- **책임 분리**: 주행 전략(`RaceStrategy`)과 상금표(`PrizeCalculator` → `OfficialPrizeCalculator`)를 별도 인터페이스로 분리
- **계층·예외**: Horse / Jockey / Race별 Repository · Service, `HorseNotFoundException` 등

**설계 의도**  
인터페이스는 “계약”, 추상 클래스는 “공통 실행 순서 재사용” — 둘을 섞어 쓰지 않고 역할을 나눴습니다.

---

### 3. Chicken 주문 시스템 · `src/chapter02/chiken`

고객 등급에 따라 할인율이 달라지는 치킨 주문 앱입니다. (18개 클래스)

- `DiscountPolicy` + `Newbie` / `Normal` / `Vip` 구현체
- 등급 → 정책을 `HashMap`으로 매핑해 다형성 적용 (`OrderService`)
- `InvalidGradeException`, `CustomerNotFoundException`, `ChickenNotFoundException`으로 입력 방어

**설계 의도**  
등급이 늘어도 `if-else` 체인을 늘리지 않고 Map에 정책만 추가하면 됩니다.

---

## 그 밖의 학습

| 경로 | 한 줄 요약 |
|------|------------|
| `songpyeon` | Pretty / Quick 스타일 정책을 인터페이스로 분리 |
| `grocery`, `guest` | Controller · Service · Repository CRUD 기본기 |
| `AcademyStudentSystem` → `separate_AcademyStudentSystem` | 한 파일 코드를 Main / Service / Repository / Student로 리팩터링 |
| `chapter02/lesson7` | Stream, Lambda, Optional, `groupingBy`, `flatMap` |
| `chapter03` | 파일 I/O(JSON/txt), JDBC CRUD · 트랜잭션 롤백 |
| `homework_02` ~ `08` | 주차별 과제 (컬렉션, Map 조회 등) |
| `algorithm` | 알고리즘 연습 |

---

## 핵심 역량

1. **객체지향** — 상속, 인터페이스, 추상 클래스, 다형성을 역할에 맞게 구분
2. **계층 설계** — UI / 비즈니스 / 저장소를 분리해 변경 영향 범위를 줄임
3. **확장 가능한 분기** — 전략·정책 패턴으로 행동 추가 시 기존 코드 수정 최소화
4. **예외** — 도메인별 커스텀 예외로 실패 원인을 명확히 전달
5. **컬렉션·스트림·JDBC** — `ArrayList`/`HashMap`, Stream 파이프라인, MySQL 연동 기초

---