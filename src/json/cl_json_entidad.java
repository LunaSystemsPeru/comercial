/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;
import nicon.notify.core.Notification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author gerenciatecnica
 */
public class cl_json_entidad {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String SERVER_PATH = "https://lunasystemsperu.com/";

    public static String getJSONRUC(String ruc) {

        StringBuffer response = null;

        try {
            //Generar la URL
            //String url = "https://lunasystemsperu.com/consultas_json/composer/consulta_sunat_JMP.php?ruc=" + ruc;
            String url = "https://lunasystemsperu.com/apis/apiruc.php?ruc=" + ruc;
            //Creamos un nuevo objeto URL con la url donde pedir el JSON
            URL obj = new URL(url);
            //Creamos un objeto de conexión
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            //Añadimos la cabecera
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            // Enviamos la petición por POST
            con.setDoOutput(true);
            //Capturamos la respuesta del servidor
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            //if (responseCode != 200) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            //Mostramos la respuesta del servidor por consola
            System.out.println("Respuesta del servidor: " + response);
            //cerramos la conexión
            in.close();
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response.toString();
    }

    public static String getJSONDNI(String dni) {

        StringBuffer response = null;

        try {
            //Generar la URL
            String url = "https://lunasystemsperu.com/apis/apidni.php?dni=" + dni;
            //Creamos un nuevo objeto URL con la url donde pedir el JSON
            URL obj = new URL(url);
            //Creamos un objeto de conexión
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            //Añadimos la cabecera
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            // Enviamos la petición por POST
            con.setDoOutput(true);
            //Capturamos la respuesta del servidor
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);
            //JOptionPane.showMessageDialog(null, "Respuesta del servidor: " + responseCode);

            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                //Mostramos la respuesta del servidor por consola
                System.out.println("Respuesta del servidor: " + response);
                System.out.println();
                //cerramos la conexión
                in.close();
            } else {
                response = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response.toString();
    }

    public static String[] showJSONRUC(String json) throws ParseException {
        String[] datos = new String[4];
        System.out.println("INFORMACIÓN OBTENIDA DE LA BASE DE DATOS:");

        JSONParser Jparser = new JSONParser();
        JSONObject result = (JSONObject) Jparser.parse(json); //jsonObject
        datos[0] = result.get("razonSocial").toString();
        datos[1] = result.get("direccion").toString();
        datos[2] = result.get("condicion").toString();
        datos[3] = result.get("estado").toString();
        return datos;
    }

    public static String showJSONDNI(String json) throws ParseException {
        String datos = new String();
        System.out.println("INFORMACIÓN OBTENIDA DE LA BASE DE DATOS:");

        JSONParser Jparser = new JSONParser();
        JSONObject result = (JSONObject) Jparser.parse(json);       //jsonObject
        // boolean estatus = (Boolean) jsonObject.get("success");
        // String source = (String) jsonObject.get("source");
        //https://examples.javacodegeeks.com/core-java/json/java-json-parser-example/
        // JSONObject result = (JSONObject) jsonObject.get("result");

        // if (source.equals("essalud")) {
        //datos[0] = result.get("apellidoPaterno").toString() + " " + result.get("apellidoMaterno").toString() + " " + result.get("nombres").toString();
        datos = result.get("nombre").toString();
        /* }
        if (source.equals("padron_jne")) {
        datos[0] = result.get("apellidos").toString() + " " + result.get("Nombres").toString();
        datos[1] = "-";
        }*/
        return datos;
    }
}
