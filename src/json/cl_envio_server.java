/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author gerenciatecnica
 */
public class cl_envio_server {

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.125 Safari/537.36";

    public static String[] enviar_documento(int id_venta, int tipo_documento, int id_almacen) {
        String SERVER_PATH = "https://lunasystemsperu.com/clientes/comercial_penia/composer/generateXML/";

        String url = null;
        String[] datos = new String[6];
        // open a connection to the site
        if (tipo_documento == 1) {
            url = SERVER_PATH + "boleta.php?id_venta=" + id_venta + "&id_almacen=" + id_almacen;
        }
        if (tipo_documento == 2) {
            url = SERVER_PATH + "factura.php?id_venta=" + id_venta + "&id_almacen=" + id_almacen;
        }

        if (tipo_documento == 5) {
            url = SERVER_PATH + "guia-remision.php?id_venta=" + id_venta + "&id_almacen=" + id_almacen;
        }
        StringBuffer response;

        System.out.println(url);
        try {
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
            System.out.println("\nSending 'GET' request to URL : " + url);
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

            System.out.println("INFORMACIÓN OBTENIDA DE LA BASE DE DATOS:");

            JSONParser Jparser = new JSONParser();
            JSONObject jsonObject = (JSONObject) Jparser.parse(response.toString());
            boolean estatus = (Boolean) jsonObject.get("success");
            if (estatus) {
                //https://examples.javacodegeeks.com/core-java/json/java-json-parser-example/
                //JSONObject result = (JSONObject) jsonObject.get("resultado");

                datos[0] = jsonObject.get("observaciones").toString();
                datos[1] = jsonObject.get("nombreDocumento").toString();
                datos[2] = jsonObject.get("codigoSunat").toString();
                datos[3] = jsonObject.get("hash").toString();
                datos[4] = "aceptado";
            } else {
                datos[0] = "";
                datos[1] = "";
                datos[2] = "";
                datos[3] = "";
                datos[4] = "error";
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return datos;
    }
}
