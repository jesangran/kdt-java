# kdt-java

KDT Java 백엔드 과정에서 학습·실습한 콘솔 프로젝트 모음입니다.  
계층 분리(Controller → Service → Repository), 객체지향 설계, 전략 패턴, 예외 처리를 중심으로 연습했습니다.

> 상세 포트폴리오(면접 Q&A 포함): [PORTFOLIO.md](./PORTFOLIO.md)

---

## Tech Stack

| 구분 | 내용 |
|------|------|
| Language | Java |
| IDE | IntelliJ IDEA |
| Libraries | Collections, Stream / Lambda, 파일 I/O (JSON / txt) |
| Database | MySQL (JDBC, `lib/mysql-connector-j-8.4.0.jar`) |
| VCS | Git |

---

## Project Structure

```
kdt-java/
├── src/
│   ├── chapter01/          # 기초 문법 · 배열 · Map · Scanner
│   ├── chapter02/          # OOP · 다형성 · Stream / Lambda
│   ├── chapter03/          # 파일 I/O · JDBC
│   ├── algorithm/          # 알고리즘 연습
│   ├── homework_02 ~ 08/   # 주차별 과제
│   ├── avengers/           # 영웅·미션 관리 (전략 패턴)
│   ├── racing/             # 경마 시스템 (전략·상금 정책)
│   ├── songpyeon/          # 송편 만들기 (스타일 정책)
│   ├── grocery/ · guest/   # CRUD 계층 연습
│   ├── AcademyStudentSystem/
│   └── separate_AcademyStudentSystem/  # 역할별 리팩터링
├── data/                   # 데이터 파일
├── lib/                    # 외부 JAR
└── PORTFOLIO.md
```

---

## Featured Projects

### Avengers — 미션 관리 (`src/avengers`)

영웅·미션·미션 로그를 관리하고, 전투 방식에 따라 결과가 달라지는 콘솔 앱입니다.

- **계층**: Controller → Service → Repository (Hero / Mission / MissionLog)
- **설계**: `BattleStrategy` + `Melee` / `Ranged` / `Support`
- **예외**: `HeroNotFoundException`, `InvalidStrategyException` 등

### Racing — 경마 시스템 (`src/racing`)

말·기수·경주를 관리하고 전략별로 경주를 진행합니다. (23개 클래스)

- `AbstractRaceStrategy` + `FrontRunner` / `Closer` / `Pacer`
- `PrizeCalculator` ↔ `OfficialPrizeCalculator` (상금 정책 교체)
- Horse / Jockey / Race 각각 Repository · Service 분리

### Chicken 주문 (`src/chapter02/chiken`)

고객 등급별 할인 정책이 다른 주문 앱입니다.

- `DiscountPolicy` + `Newbie` / `Normal` / `Vip` → 다형성으로 할인 처리

### 기타

| 경로 | 핵심 |
|------|------|
| `songpyeon` | Pretty / Quick 스타일 정책을 인터페이스로 분리 |
| `grocery`, `guest` | Repository / Service / Controller CRUD |
| `AcademyStudentSystem` → `separate_AcademyStudentSystem` | 한 파일 → 역할별 클래스 리팩터링 |
| `chapter02/lesson7` | Stream, Lambda, Optional, `groupingBy`, `flatMap` |
| `chapter03` | 파일 I/O, JDBC 트랜잭션 |

---

## How to Run

IntelliJ에서 프로젝트를 연 뒤, 실행하려는 클래스의 `main` 메서드를 실행하면 됩니다.

```
예시
src/racing/Main.java
src/chapter02/chiken/Main.java
src/separate_AcademyStudentSystem/Main.java
```

JDBC 실습(`chapter03/lesson5`)은 `lib/mysql-connector-j-8.4.0.jar`를 모듈 경로에 추가하고, DB 연결 정보를 환경에 맞게 설정하세요.

---

## Key Takeaways

- **객체지향** — 상속 · 인터페이스 · 추상 클래스 · 다형성을 구분해 사용
- **계층 설계** — 입출력 / 비즈니스 규칙 / 저장 책임 분리
- **전략 패턴** — 분기(`if`)를 늘리지 않고 구현체만 추가
- **예외 처리** — 상황별 커스텀 예외로 실패 원인 표현
- **컬렉션 · 스트림** — `ArrayList`, `HashMap`, Stream 파이프라인

---

## Next Steps

- [ ] JDBC / JPA + MySQL로 영속화 확장
- [ ] JUnit으로 Service 계층 단위 테스트
- [ ] 전략 객체 생성을 팩토리 / Map 방식으로 개선
