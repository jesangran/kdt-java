package avengers;

/**
 * [커스텀 예외] 히어로·미션·로그 "없는 번호" 공용. 메시지로 구분.
 * extends IllegalArgumentException → Controller의 catch (IllegalArgumentException)에 잡힘.
 */
public class HeroNotFoundException extends IllegalArgumentException {
    public HeroNotFoundException(String message) {
        super(message);
    }
}
