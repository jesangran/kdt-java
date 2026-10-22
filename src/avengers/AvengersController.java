package avengers;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * ============================================================
 * [Controller] 여러 서비스 호출 순서를 조립하는 감독
 * ============================================================
 * 배정: HeroService.findById → 이름만 → MissionService.assign
 * 전투: MissionService.findById → 배정 확인 → BattleService.choose
 *       → label/damage/teamScore → MissionLogService.record
 *
 * 배정 확인을 전략 선택보다 먼저 한다.
 * (히어로 없는데 방식도 틀리면 "배정된 히어로가 없습니다."가 나와야 함)
 *
 * catch는 IllegalArgumentException 하나 —
 * HeroNotFoundException, InvalidStrategyException 도 그 자식이라 함께 잡힌다.
 */
public class AvengersController {
    private final HeroService heroService;
    private final MissionService missionService;
    private final MissionLogService missionLogService;
    private final BattleService battleService;
    private final Scanner scanner;

    public AvengersController(HeroService heroService,
                              MissionService missionService,
                              MissionLogService missionLogService,
                              BattleService battleService,
                              Scanner scanner) {
        this.heroService = heroService;
        this.missionService = missionService;
        this.missionLogService = missionLogService;
        this.battleService = battleService;
        this.scanner = scanner;
    }

    public static void main(String[] args) {
        // Repo 3 → Service 3 + BattleService(저장소 없음) → Controller
        HeroRepository heroRepository = new HeroRepository();
        MissionRepository missionRepository = new MissionRepository();
        MissionLogRepository missionLogRepository = new MissionLogRepository();

        HeroService heroService = new HeroService(heroRepository);
        MissionService missionService = new MissionService(missionRepository);
        MissionLogService missionLogService = new MissionLogService(missionLogRepository);
        BattleService battleService = new BattleService();
        Scanner scanner = new Scanner(System.in);

        AvengersController controller = new AvengersController(
                heroService, missionService, missionLogService, battleService, scanner);
        controller.run();
    }

    public void run() {
        boolean running = true;
        while (running) {
            printMenu();
            int menu = scanner.nextInt();
            scanner.nextLine();
            if (menu == 1) {
                registerHero();
            } else if (menu == 2) {
                registerMission();
            } else if (menu == 3) {
                assignHero();
            } else if (menu == 4) {
                fight();
            } else if (menu == 5) {
                printLogs();
            } else if (menu == 6) {
                printHeroes();
            } else if (menu == 7) {
                printMissions();
            } else if (menu == 0) {
                System.out.println("본부로 복귀합니다.");
                running = false;
            } else {
                System.out.println("메뉴는 0부터 7까지입니다.");
            }
        }
    }

    private void printMenu() {
        System.out.println("🛡️ 어벤져스 본부");
        System.out.println("1. 히어로 등록");
        System.out.println("2. 미션 등록");
        System.out.println("3. 히어로 배정");
        System.out.println("4. 전투");
        System.out.println("5. 전투 로그");
        System.out.println("6. 히어로 목록");
        System.out.println("7. 미션 목록");
        System.out.println("0. 종료");
        System.out.print("번호> ");
    }

    private void registerHero() {
        System.out.print("이름> ");
        String name = scanner.nextLine();
        try {
            Hero hero = heroService.register(name);
            System.out.println("🦸 히어로를 등록했습니다. 번호 " + hero.getId() + ", " + hero.getName());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void registerMission() {
        System.out.print("미션 이름> ");
        String title = scanner.nextLine();
        try {
            Mission mission = missionService.register(title);
            System.out.println("🎯 미션을 등록했습니다. 번호 " + mission.getId() + ", " + mission.getTitle());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void assignHero() {
        // 번호를 모두 읽은 뒤 서비스 호출 (없는 히어로여도 미션 번호까지 입력)
        System.out.print("히어로 번호> ");
        int heroId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("미션 번호> ");
        int missionId = scanner.nextInt();
        scanner.nextLine();
        try {
            Hero hero = heroService.findById(heroId);
            // 히어로 객체가 아니라 이름만 미션 서비스로
            Mission mission = missionService.assign(missionId, hero.getName());
            System.out.println("🤝 배정했습니다. " + mission.getTitle() + " / " + mission.getHeroName());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void fight() {
        System.out.print("미션 번호> ");
        int missionId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("전투 방식 1.근접 2.원거리 3.지원> ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        try {
            Mission mission = missionService.findById(missionId);
            // 1) 배정 검사 먼저 (전략 번호가 틀려도 여기가 우선)
            if (mission.getHeroName().equals("")) {
                throw new IllegalArgumentException("배정된 히어로가 없습니다.");
            }
            // 2) 전략 선택 (다형성: 반환 타입은 BattleStrategy)
            BattleStrategy strategy = battleService.choose(choice);
            // 3) 전략 결과 값만 로그 서비스에 전달
            MissionLog log = missionLogService.record(
                    mission.getTitle(),
                    mission.getHeroName(),
                    strategy.label(),
                    strategy.damage(),
                    strategy.teamScore()
            );
            System.out.println("💥 전투를 마쳤습니다. "
                    + log.getMissionTitle() + " / "
                    + log.getHeroName() + " / "
                    + log.getStrategyLabel() + " / 피해 "
                    + log.getDamage() + " / 팀 "
                    + log.getTeamScore());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void printLogs() {
        ArrayList<MissionLog> logs = missionLogService.findAll();
        System.out.println("📜 전투 로그");
        if (logs.size() == 0) {
            System.out.println("아직 전투 로그가 없습니다.");
        } else {
            for (MissionLog log : logs) {
                System.out.println(log.getId() + ". "
                        + log.getMissionTitle() + " / "
                        + log.getHeroName() + " / "
                        + log.getStrategyLabel() + " / 피해 "
                        + log.getDamage() + " / 팀 "
                        + log.getTeamScore());
            }
        }
    }

    private void printHeroes() {
        ArrayList<Hero> heroes = heroService.findAll();
        System.out.println("🦸 히어로 목록");
        if (heroes.size() == 0) {
            System.out.println("아직 히어로가 없습니다.");
        } else {
            for (Hero hero : heroes) {
                System.out.println(hero.getId() + ". " + hero.getName());
            }
        }
    }

    private void printMissions() {
        ArrayList<Mission> missions = missionService.findAll();
        System.out.println("🎯 미션 목록");
        if (missions.size() == 0) {
            System.out.println("아직 미션이 없습니다.");
        } else {
            for (Mission mission : missions) {
                String heroName = mission.getHeroName();
                if (heroName.equals("")) {
                    heroName = "없음";
                }
                System.out.println(mission.getId() + ". " + mission.getTitle() + " / " + heroName);
            }
        }
    }
}
