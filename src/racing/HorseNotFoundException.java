package racing;

/** 말/경주 없음. 메시지로 구분. IllegalArgumentException 자식. */
public class HorseNotFoundException extends IllegalArgumentException {
    public HorseNotFoundException(String message) {
        super(message);
    }
}
