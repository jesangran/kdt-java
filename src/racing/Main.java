package racing;

import java.util.Scanner;

/**
 * ============================================================
 * [조립] Main만 실행 진입점 — Controller에 main을 두지 않는다
 * ============================================================
 * songpyeon/avengers는 Controller.main 이었고,
 * racing은 "조립(Main)"과 "화면(Controller)"을 파일로 나눈 단계.
 *
 * new 순서: Repository → Service → PrizeCalculator/Scanner → Controller → run()
 */
public class Main {
    public static void main(String[] args) {
        HorseRepository horseRepository = new HorseRepository();
        JockeyRepository jockeyRepository = new JockeyRepository();
        RaceRepository raceRepository = new RaceRepository();

        HorseService horseService = new HorseService(horseRepository);
        JockeyService jockeyService = new JockeyService(jockeyRepository);
        RaceService raceService = new RaceService(raceRepository);
        StrategyService strategyService = new StrategyService(); // 저장소 없음
        PrizeCalculator prizeCalculator = new OfficialPrizeCalculator();
        Scanner scanner = new Scanner(System.in);

        RaceController controller = new RaceController(
                horseService,
                jockeyService,
                raceService,
                strategyService,
                prizeCalculator,
                scanner
        );
        controller.run();
    }
}
