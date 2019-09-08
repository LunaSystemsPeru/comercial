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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author gerenciatecnica
 */
public class cl_json_entidad {

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.125 Safari/537.36";
    private static final String SERVER_PATH = "http://lunasystemsperu.com/";

    public static String getJSONRUC_LUNASYSTEMS(String ruc) {

        StringBuffer response = null;

        try {
            //Generar la URL
            String url = SERVER_PATH + "consultas_json/composer/consulta_sunat_JMP.php?ruc=" + ruc;
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

    public static String getJSONRUC_NUBEFACT(String ruc) {

        StringBuffer response = null;

        try {
            //Generar la URL
            String url = "http://www.conmetal.pe/erp/ajax_post/consulta_ruc_nubefact.php?ruc=" + ruc;
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

    public static String getJSONDNI_LUNASYSTEMS(String dni) {

        StringBuffer response = null;

        try {
            //Generar la URL
            String url = SERVER_PATH + "consultas_json/composer/consultas_dni_JMP.php?dni=" + dni;
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

    public static String[] showJSONRUCL(String json) throws ParseException {
        String[] datos = new String[2];
        System.out.println("INFORMACIÓN OBTENIDA DE LA BASE DE DATOS:");

        JSONParser Jparser = new JSONParser();
        JSONObject jsonObject = (JSONObject) Jparser.parse(json);
        boolean estatus = (Boolean) jsonObject.get("success");
        //System.out.println("el estado es: " + estatus);
        //JSONArray result = (JSONArray) jsonObject.get("result");
        //array cuando es repetitivo
        //estructurs cuando es uno simple
        //aprendi de aqui
        //https://examples.javacodegeeks.com/core-java/json/java-json-parser-example/
        JSONObject result = (JSONObject) jsonObject.get("entity");
        //System.out.println("razon social: " + result.get("RazonSocial"));
        datos[0] = result.get("nombre_o_razon_social").toString();
        datos[1] = result.get("direccion_completa").toString();
        return datos;
    }

    public static String[] showJSONRUC_JMP(String json) throws ParseException {
        String[] datos = new String[4];
        System.out.println("INFORMACIÓN OBTENIDA DE LA BASE DE DATOS:");

        JSONParser Jparser = new JSONParser();
        JSONObject jsonObject = (JSONObject) Jparser.parse(json);
        boolean estatus = (Boolean) jsonObject.get("success");

        //System.out.println("el estado es: " + estatus);
        //JSONArray result = (JSONArray) jsonObject.get("result");
        //array cuando es repetitivo
        //estructurs cuando es uno simple
        //aprendi de aqui
        //https://examples.javacodegeeks.com/core-java/json/java-json-parser-example/
        JSONObject result = (JSONObject) jsonObject.get("result");
        //System.out.println("razon social: " + result.get("RazonSocial"));
        datos[0] = result.get("RazonSocial").toString();
        datos[1] = result.get("Direccion").toString();
        datos[2] = result.get("Condicion").toString();
        datos[3] = result.get("Estado").toString();
        return datos;
    }

    public static String[] showJSONDNI(String json) throws ParseException {
        String[] datos = new String[2];
        System.out.println("INFORMACIÓN OBTENIDA DE LA BASE DE DATOS:");

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(json);
        JSONArray array = new JSONArray();
        array.add(obj);

        //Iterar el array y extraer la información
        for (Object array_json : array) {
            JSONObject row = (JSONObject) array_json;
            String dni = (String) row.get("dni");
            String nombres = (String) row.get("apellido_paterno") + ' ' + (String) row.get("apellido_materno") + ' ' + (String) row.get("nombres");
            //Mostrar la información en pantalla
            datos[0] = dni;
            datos[1] = nombres;
        }
        return datos;
    }

    public static String showJSONDNIL(String json) throws ParseException {
        String nombres = "";
        System.out.println("INFORMACIÓN OBTENIDA DE LA BASE DE DATOS:");

        JSONParser Jparser = new JSONParser();
        JSONObject jsonObject = (JSONObject) Jparser.parse(json);
        boolean estatus = (Boolean) jsonObject.get("success");
        //array cuando es repetitivo
        //estructurs cuando es uno simple
        //aprendi de aqui
        //https://examples.javacodegeeks.com/core-java/json/java-json-parser-example/
        String source = (String) jsonObject.get("source");
        JSONObject result = (JSONObject) jsonObject.get("result");
        if (source.equals("padron_jne")) {
            nombres = result.get("apellidos").toString() + " " + result.get("Nombres").toString();
        }
        if (source.equals("essalud")) {
            nombres = result.get("ApellidoPaterno").toString() + " " + result.get("ApellidoMaterno").toString() + " " + result.get("Nombres").toString();
        }
        if (!estatus) {
            nombres = "ERROR AL ENCONTRAR NOMBRE";
        }
        return nombres;
    }
}
