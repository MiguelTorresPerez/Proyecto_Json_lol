package pojos;

public class MaestriaUser {
    String nombre;
    Integer points;

    public MaestriaUser(String nombre, Integer points) {
        this.nombre = nombre;
        this.points = points;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
