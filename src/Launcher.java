import conexion.Conexion;
import ventanas.VentanaPrincipal;

public class Launcher {
    public static void main(String[] args) {
        VentanaPrincipal vP = new VentanaPrincipal();
        vP.run();
       /* Conexion c = new Conexion("VenzerKielen");
        System.out.println(c.getUserId());
        System.out.println(c.getArrayCampeones().get(0).toString());
        System.out.println(c.getArrayMaestrias());*/
    }
}