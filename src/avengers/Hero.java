package avengers;

/** [Domain] 히어로. id/name final + getter. */
public class Hero {
    private final int id;
    private final String name;

    public Hero(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
