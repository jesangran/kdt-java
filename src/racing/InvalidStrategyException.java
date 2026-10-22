package racing;

/** 전략 번호 1~4 아님. */
public class InvalidStrategyException extends IllegalArgumentException {
    public InvalidStrategyException(String message) {
        super(message);
    }
}
