package racing;

/** 기수 없음. */
public class JockeyNotFoundException extends IllegalArgumentException {
    public JockeyNotFoundException(String message) {
        super(message);
    }
}
