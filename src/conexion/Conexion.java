package conexion;
import org.json.JSONArray;
import org.json.JSONObject;
import pojos.Campeon;
import pojos.Maestria;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class Conexion {
    private String user;
    private String userId;
    private ArrayList<Campeon> arrayCampeones;
    private ArrayList<Maestria> maestrias;
    private final String token = "RGAPI-3ae70eda-7923-45ed-b784-146ba7ed2b10";
    public Conexion(String u) {
        this.user = u;
        getNombresChamps();
        queryUser();
        queryMaestrie();
    }

    //Query para sacar el id encriptado que luego usare en la query para sacar la maestria con cada campeon
    private void queryUser() {
        final String pathDecryptId = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/";
        try {
            URL url = new URL(pathDecryptId + user);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("X-Riot-Token ", token);
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String linea;
            while ((linea = rd.readLine()) != null) {
                builder.append(linea);
            }
            JSONObject jsonObject = new JSONObject(builder.toString());
            userId = jsonObject.getString("id");
        } catch (Exception e){

        }
    }

    //Rellena
    private void queryMaestrie(){
        final String pathMaest = "https://euw1.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-summoner/";
        StringBuilder builder = new StringBuilder();
        try {
            URL url = new URL(pathMaest + userId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("X-Riot-Token ", token);
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String linea;
            while ((linea = rd.readLine()) != null) {
                builder.append(linea);
            }
            //JSONObject jo = new JSONObject(builder.toString());
            JSONArray jsonArray = new JSONArray(builder.toString());
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            Iterator x = jsonObject.keys();
            while (x.hasNext()) {
                String key = (String) x.next();
                jsonArray.put(jsonObject.get(key));
            }
            maestrias = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                Integer id = jsonArray.getJSONObject(i).getInt("championId");
                Integer points = jsonArray.getJSONObject(i).getInt("championPoints");
                Maestria m = new Maestria(id,points);
                maestrias.add(m);
            }
        } catch (Exception e){

        }
    }


    //Llena un array con ids de campeones y nombres (actualizado desde el sitio que usan los desarrolladores)
    private void getNombresChamps() {
        try {
            final String path = "http://ddragon.leagueoflegends.com/cdn/10.4.1/data/en_US/champion.json";
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader lector = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String linea;
            while ((linea = lector.readLine()) != null) {
                builder.append(linea);
            }
            JSONObject jsonObject = new JSONObject(builder.toString());
            JSONObject champs = jsonObject.getJSONObject("data");
            Iterator x = champs.keys();
            JSONArray jsonArray = new JSONArray();
            while (x.hasNext()) {
                String key = (String) x.next();
                jsonArray.put(champs.get(key));
            }
            arrayCampeones = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                Integer id = jsonArray.getJSONObject(i).getInt("key");
                String nombre = jsonArray.getJSONObject(i).getString("id");
                Campeon c = new Campeon(nombre,id);
                arrayCampeones.add(c);
            }
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public ArrayList<Campeon> getArrayCampeones() {
        return this.arrayCampeones;
    }

    public ArrayList<Maestria> getArrayMaestrias() {
        return this.maestrias;
    }
}
