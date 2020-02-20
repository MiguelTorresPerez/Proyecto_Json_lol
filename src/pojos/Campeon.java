package pojos;

public class Campeon {
    String nombre;
    Integer id;

    public Campeon(String nombre, Integer id) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Campeon{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                '}';
    }
}
