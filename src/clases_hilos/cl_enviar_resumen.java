/*
 * Copyright (c) 2019, luis
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package clases_hilos;

import clases.cl_varios;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author luis
 */
public class cl_enviar_resumen extends Thread {

    private String fecha;
    private int tipo;
    private final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.125 Safari/537.36";

    public cl_enviar_resumen() {
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    public String[] enviar_documento() {
        String SERVER_PATH = "https://lunasystemsperu.com/clientes/comercial_penia/composer/generateXML/";
        
        String url = null;
        String[] datos = new String[5];
        // open a connection to the site
        if (this.tipo == 3) {
            url = SERVER_PATH + "resumen-anulados.php?empresaid=1&fecha=" + this.fecha;
        } else {
            url = SERVER_PATH + "resumen-activos.php?empresaid=1&fecha=" + this.fecha;
        }
        
        StringBuffer response = null;

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
            //System.out.println("Respuesta del servidor: " + response);
            JOptionPane.showMessageDialog(null, "Respuesta del servidor: " + response.toString());
            //cerramos la conexión
            in.close();
            // }

         //  System.out.println("INFORMACIÓN OBTENIDA DE LA BASE DE DATOS:");
/*
            JSONParser Jparser = new JSONParser();
            JSONObject jsonObject = (JSONObject) Jparser.parse(response.toString());
            boolean estatus = (Boolean) jsonObject.get("success");
            if (estatus) {
                //https://examples.javacodegeeks.com/core-java/json/java-json-parser-example/
                //JSONObject result = (JSONObject) jsonObject.get("resultado");

                datos[0] = jsonObject.get("nombrexml").toString();
                datos[1] = jsonObject.get("ticket").toString();
                datos[2] = jsonObject.get("fecha").toString();
                datos[3] = jsonObject.get("estado").toString();
                datos[4] = "enviado";
            } else {
                datos[0] = "";
                datos[1] = "";
                datos[2] = "";
                datos[3] = "";
                datos[4] = "error";
            }
*/
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(datos);
        return datos;
    }

    private void enviar_venta() {
        String[] envio_sunat = enviar_documento();

      //  System.out.println(envio_sunat);
    }

    @Override
    public void run() {
        enviar_venta();
    }
}
