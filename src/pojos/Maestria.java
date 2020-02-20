package pojos;

public class Maestria {
    Integer id;
    Integer points;

    public Maestria(Integer id, Integer points) {
        this.id = id;
        this.points = points;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Maestria{" +
                "id=" + id +
                ", points=" + points +
                '}';
    }
}
