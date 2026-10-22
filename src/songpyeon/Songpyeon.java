package songpyeon;

/**
 * [Domain] 송편 빚기 결과 한 건.
 * count에는 정책의 pieceCount() 결과가 들어간다. toString 대신 getter로 화면 조립.
 */
public class Songpyeon {
    private final int id;
    private final String name;
    private final String makerName;
    private final String styleLabel;
    private final int count;
    private final int shapeScore;

    public Songpyeon(int id, String name, String makerName, String styleLabel, int count, int shapeScore) {
        this.id = id;
        this.name = name;
        this.makerName = makerName;
        this.styleLabel = styleLabel;
        this.count = count;
        this.shapeScore = shapeScore;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMakerName() {
        return makerName;
    }

    public String getStyleLabel() {
        return styleLabel;
    }

    public int getCount() {
        return count;
    }

    public int getShapeScore() {
        return shapeScore;
    }
}
