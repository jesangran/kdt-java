package songpyeon;

/**
 * [커스텀 예외] IllegalArgumentException을 상속.
 * 가족/송편 "없는 번호"에 같이 쓰고, 메시지로 대상을 구분한다.
 * Controller에서 catch (자식) 후 catch (부모 IllegalArgumentException) 순서를 지킨다.
 * super(message) → 나중에 e.getMessage()로 같은 문장을 꺼낸다.
 */
public class SongpyeonNotFoundException extends IllegalArgumentException {
    public SongpyeonNotFoundException(String message) {
        super(message);
    }
}
