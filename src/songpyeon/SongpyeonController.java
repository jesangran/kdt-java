package songpyeon;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * ============================================================
 * [Controller] 입력·출력 + 서비스 호출 순서 조립
 * ============================================================
 * Repository를 직접 호출하지 않는다. Service만 호출한다.
 *
 * main에서의 조립 순서 (의존하는 쪽을 먼저 new)
 *   Repository → Service → Scanner → Controller → run()
 *
 * 송편 빚기 핵심 흐름 (서비스 협력)
 *   1) 입력 전부 다 읽기 (이름, 가족번호, 스타일)
 *   2) familyMemberService.findById → 가족 객체
 *   3) songpyeonService.register(이름, 가족이름문자열, 스타일)
 *      ※ 송편 서비스에는 "이름 문자열"만 넘김 (가족 저장소 연결 X)
 *
 * catch 순서: 자식(SongpyeonNotFoundException) → 부모(IllegalArgumentException)
 */
public class SongpyeonController {
    private final FamilyMemberService familyMemberService;
    private final SongpyeonService songpyeonService;
    private final Scanner scanner;

    public SongpyeonController(FamilyMemberService familyMemberService,
                               SongpyeonService songpyeonService,
                               Scanner scanner) {
        // 생성자 안에서 new Service/Repository 하지 않음 = 생성자 주입
        this.familyMemberService = familyMemberService;
        this.songpyeonService = songpyeonService;
        this.scanner = scanner;
    }

    public static void main(String[] args) {
        // 애플리케이션 조립(DI)은 main 책임
        FamilyMemberRepository familyMemberRepository = new FamilyMemberRepository();
        SongpyeonRepository songpyeonRepository = new SongpyeonRepository();
        FamilyMemberService familyMemberService = new FamilyMemberService(familyMemberRepository);
        SongpyeonService songpyeonService = new SongpyeonService(songpyeonRepository);
        Scanner scanner = new Scanner(System.in);
        SongpyeonController controller = new SongpyeonController(familyMemberService, songpyeonService, scanner);
        controller.run();
    }

    public void run() {
        boolean running = true;
        while (running) {
            printMenu();
            int menu = scanner.nextInt();
            scanner.nextLine();
            if (menu == 1) {
                registerFamily();
            } else if (menu == 2) {
                makeSongpyeon();
            } else if (menu == 3) {
                printSongpyeonList();
            } else if (menu == 4) {
                printFamilyList();
            } else if (menu == 0) {
                System.out.println("다음에 또 빚어요.");
                running = false;
            } else {
                System.out.println("메뉴는 0부터 4까지입니다.");
            }
        }
    }

    private void printMenu() {
        System.out.println("🌕 추석에 모인 가족");
        System.out.println("1. 가족 등록");
        System.out.println("2. 송편 빚기");
        System.out.println("3. 송편 목록");
        System.out.println("4. 가족 목록");
        System.out.println("0. 종료");
        System.out.print("번호> ");
    }

    private void registerFamily() {
        System.out.print("이름> ");
        String name = scanner.nextLine();
        try {
            FamilyMember member = familyMemberService.register(name);
            System.out.println("👵 가족을 등록했습니다. 번호 " + member.getId() + ", " + member.getName());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void makeSongpyeon() {
        // nextInt 후 nextLine으로 엔터 비우기 (안 비우면 다음 nextLine이 빈 문자열)
        System.out.print("송편 이름> ");
        String name = scanner.nextLine();
        System.out.print("가족 번호> ");
        int familyId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("빚기 스타일 1.예쁘게 2.빠르게> ");
        int styleChoice = scanner.nextInt();
        scanner.nextLine();

        try {
            // 입력을 모두 받은 뒤에 조회 (없는 가족이어도 스타일까지 묻는다)
            FamilyMember member = familyMemberService.findById(familyId);
            // 가족 객체 전체가 아니라 이름만 송편 서비스로 전달
            Songpyeon songpyeon = songpyeonService.register(name, member.getName(), styleChoice);
            System.out.println("🥟 " + songpyeon.getName() + "을 빚었습니다. "
                    + songpyeon.getMakerName() + " / "
                    + songpyeon.getStyleLabel() + " / "
                    + songpyeon.getCount() + "개 / 모양 "
                    + songpyeon.getShapeScore() + "점");
        } catch (SongpyeonNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void printSongpyeonList() {
        ArrayList<Songpyeon> list = songpyeonService.findAll();
        System.out.println("🥟 송편 목록");
        if (list.size() == 0) {
            System.out.println("아직 빚은 송편이 없습니다.");
        } else {
            for (int i = 0; i < list.size(); i++) {
                Songpyeon songpyeon = list.get(i);
                System.out.println(songpyeon.getId() + ". "
                        + songpyeon.getName() + " / "
                        + songpyeon.getMakerName() + " / "
                        + songpyeon.getStyleLabel() + " / "
                        + songpyeon.getCount() + "개 / 모양 "
                        + songpyeon.getShapeScore() + "점");
            }
        }
    }

    private void printFamilyList() {
        ArrayList<FamilyMember> list = familyMemberService.findAll();
        System.out.println("🌕 가족 목록");
        if (list.size() == 0) {
            System.out.println("아직 모인 가족이 없습니다.");
        } else {
            for (int i = 0; i < list.size(); i++) {
                FamilyMember member = list.get(i);
                System.out.println(member.getId() + ". " + member.getName());
            }
        }
    }
}
