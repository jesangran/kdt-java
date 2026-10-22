package avengers;

/** [커스텀 예외] 전투 방식이 1,2,3이 아닐 때. 역시 IllegalArgumentException 자식. */
public class InvalidStrategyException extends IllegalArgumentException {
    public InvalidStrategyException(String message) {
        super(message);
    }
}
