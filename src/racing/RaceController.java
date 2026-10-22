package racing;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * ============================================================
 * [Controller] 화면 + 사용 사례 조립 (main 없음 → Main이 조립)
 * ============================================================
 * 참가 등록 순서: 경주 find → 말 find → 기수 find → choose → enter
 * 경주 시작: 참가 2마리 이상 확인 → clearRecords → 3라운드 점수/순위
 *            → 합계로 최종 순위 → prizeOf → addRecord
 *
 * 동점: 점수 같으면 먼저 참가한 말(인덱스가 작은 쪽)이 앞.
 * 메뉴 7 다형성: List<RaceStrategy>에 네 구현을 담고 execute(1)만 호출.
 */
public class RaceController {
    private final HorseService horseService;
    private final JockeyService jockeyService;
    private final RaceService raceService;
    private final StrategyService strategyService;
    private final PrizeCalculator prizeCalculator;
    private final Scanner scanner;

    public RaceController(HorseService horseService,
                          JockeyService jockeyService,
                          RaceService raceService,
                          StrategyService strategyService,
                          PrizeCalculator prizeCalculator,
                          Scanner scanner) {
        this.horseService = horseService;
        this.jockeyService = jockeyService;
        this.raceService = raceService;
        this.strategyService = strategyService;
        this.prizeCalculator = prizeCalculator;
        this.scanner = scanner;
    }

    public void run() {
        boolean running = true;
        while (running) {
            printMenu();
            int menu = scanner.nextInt();
            scanner.nextLine();
            if (menu == 1) {
                registerHorse();
            } else if (menu == 2) {
                registerJockey();
            } else if (menu == 3) {
                createRace();
            } else if (menu == 4) {
                enterRace();
            } else if (menu == 5) {
                startRace();
            } else if (menu == 6) {
                showRecords();
            } else if (menu == 7) {
                demoStrategies();
            } else if (menu == 0) {
                System.out.println("경주를 마칩니다.");
                running = false;
            } else {
                System.out.println("없는 번호입니다.");
            }
        }
    }

    private void printMenu() {
        System.out.println("🏇 서울경마공원");
        System.out.println("1. 말 등록");
        System.out.println("2. 기수 등록");
        System.out.println("3. 경주 만들기");
        System.out.println("4. 참가 등록");
        System.out.println("5. 경주 시작");
        System.out.println("6. 기록 보기");
        System.out.println("7. 전략 체험");
        System.out.println("0. 종료");
        System.out.println("전략은 1선행 2추입 3선입 4균속");
        System.out.print("번호> ");
    }

    private void registerHorse() {
        System.out.print("말 이름> ");
        String name = scanner.nextLine();
        try {
            Horse horse = horseService.register(name);
            System.out.println("🐴 말을 등록했습니다. 번호 " + horse.getId() + ", " + horse.getName());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void registerJockey() {
        System.out.print("기수 이름> ");
        String name = scanner.nextLine();
        try {
            Jockey jockey = jockeyService.register(name);
            System.out.println("👤 기수를 등록했습니다. 번호 " + jockey.getId() + ", " + jockey.getName());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createRace() {
        System.out.print("경주 이름> ");
        String title = scanner.nextLine();
        try {
            Race race = raceService.create(title);
            System.out.println("🏁 경주를 만들었습니다. 번호 " + race.getId() + ", " + race.getTitle());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /** 참가: 번호 4개 전부 입력 후, 경주→말→기수→전략 순으로 검증 */
    private void enterRace() {
        System.out.print("경주 번호> ");
        int raceId = scanner.nextInt();
        System.out.print("말 번호> ");
        int horseId = scanner.nextInt();
        System.out.print("기수 번호> ");
        int jockeyId = scanner.nextInt();
        System.out.print("전략 번호> ");
        int strategyChoice = scanner.nextInt();
        scanner.nextLine(); // 숫자 네 번 뒤 엔터 한 번만 비움

        try {
            Race race = raceService.findById(raceId);
            Horse horse = horseService.findById(horseId);
            Jockey jockey = jockeyService.findById(jockeyId);
            RaceStrategy strategy = strategyService.choose(strategyChoice); // 실패하면 enter 안 함
            raceService.enter(raceId, horse.getName(), jockey.getName(), strategyChoice);
            System.out.println("🎫 참가했습니다. " + race.getTitle() + " / "
                    + horse.getName() + " / " + jockey.getName() + " / " + strategy.label());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 경주 시작: 참가 수 확인 → 기록 삭제 → 3라운드 → 최종(합계+상금)
     * 참가 2마리 미만이면 clearRecords를 호출하지 않는다 (이전 기록 보존).
     */
    private void startRace() {
        System.out.print("경주 번호> ");
        int raceId = scanner.nextInt();
        scanner.nextLine();

        try {
            Race race = raceService.findById(raceId);
            if (race.entryCount() < 2) {
                throw new IllegalArgumentException("참가 말이 2마리 미만입니다.");
            }
            raceService.clearRecords(raceId); // 확인 통과 후에만 지움

            int count = race.entryCount();
            ArrayList<Integer> totals = new ArrayList<>(); // 참가별 3라운드 합계
            for (int i = 0; i < count; i++) {
                totals.add(0);
            }

            for (int round = 1; round <= 3; round++) {
                ArrayList<Integer> scores = new ArrayList<>(); // 이번 라운드 점수
                for (int i = 0; i < count; i++) {
                    // 다형성: choose가 준 구현체의 execute → 부모 final + 자식 run
                    RaceStrategy strategy = strategyService.choose(race.strategyChoiceAt(i));
                    int pace = strategy.execute(round);
                    scores.add(pace);
                    totals.set(i, totals.get(i) + pace);
                }

                ArrayList<Integer> order = rank(scores); // 점수가 큰 참가 인덱스가 앞
                String header = "🏁 " + race.getTitle() + " " + round + "라운드";
                System.out.println(header);
                raceService.addRecord(raceId, header); // 화면과 같은 줄을 기록에도

                for (int r = 0; r < order.size(); r++) {
                    int index = order.get(r);
                    RaceStrategy strategy = strategyService.choose(race.strategyChoiceAt(index));
                    String line = (r + 1) + "위 " + race.horseNameAt(index) + " / "
                            + race.jockeyNameAt(index) + " / "
                            + strategy.label() + " / "
                            + scores.get(index); // 라운드 줄에는 합계/상금 없음
                    System.out.println(line);
                    raceService.addRecord(raceId, line);
                }
            }

            ArrayList<Integer> finalOrder = rank(totals);
            String finalHeader = "🏆 최종 순위";
            System.out.println(finalHeader);
            raceService.addRecord(raceId, finalHeader);

            for (int r = 0; r < finalOrder.size(); r++) {
                int index = finalOrder.get(r);
                int place = r + 1;
                RaceStrategy strategy = strategyService.choose(race.strategyChoiceAt(index));
                String line = place + "위 " + race.horseNameAt(index) + " / "
                        + race.jockeyNameAt(index) + " / "
                        + strategy.label() + " / 합계 "
                        + totals.get(index) + " / 상금 "
                        + prizeCalculator.prizeOf(place); // 상금은 최종 순위만
                System.out.println(line);
                raceService.addRecord(raceId, line);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 버블 정렬로 참가 인덱스 순위를 만든다.
     * 점수 큰 쪽이 앞. 동점이면 인덱스가 작은 쪽(먼저 참가)이 앞.
     */
    private ArrayList<Integer> rank(ArrayList<Integer> scores) {
        ArrayList<Integer> order = new ArrayList<>();
        for (int i = 0; i < scores.size(); i++) {
            order.add(i);
        }
        for (int i = 0; i < order.size() - 1; i++) {
            for (int j = 0; j < order.size() - 1 - i; j++) {
                int left = order.get(j);
                int right = order.get(j + 1);
                int leftScore = scores.get(left);
                int rightScore = scores.get(right);
                if (rightScore > leftScore) {
                    order.set(j, right);
                    order.set(j + 1, left);
                } else if (rightScore == leftScore && right < left) {
                    // 동점 + 오른쪽이 더 먼저 참가 → 앞으로
                    order.set(j, right);
                    order.set(j + 1, left);
                }
            }
        }
        return order;
    }

    private void showRecords() {
        System.out.print("경주 번호> ");
        int raceId = scanner.nextInt();
        scanner.nextLine();
        try {
            ArrayList<String> lines = raceService.recordsOf(raceId);
            if (lines.size() == 0) {
                System.out.println("아직 기록이 없습니다.");
            } else {
                System.out.println("📒 기록");
                for (String line : lines) {
                    System.out.println(line);
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 다형성 체험: 타입은 RaceStrategy, 실제 객체는 네 구현.
     * 같은 execute(1) 호출 → 결과 40/16/30/24 (lesson7 forEach + 다형성과 맞닿음)
     */
    private void demoStrategies() {
        ArrayList<RaceStrategy> strategies = new ArrayList<>();
        strategies.add(new FrontRunner());
        strategies.add(new Closer());
        strategies.add(new Marker());
        strategies.add(new Pacer());

        System.out.println("🎭 전략 체험 1라운드");
        for (RaceStrategy strategy : strategies) {
            System.out.println(strategy.label() + " " + strategy.execute(1));
        }
    }
}
