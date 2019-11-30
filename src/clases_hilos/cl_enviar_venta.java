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

import clases.cl_documento_firmado;
import clases.cl_varios;
import clases.cl_venta;
import clases_varios.leer_numeros;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.JOptionPane;
import json.cl_envio_server;

/**
 *
 * @author luis
 */
public class cl_enviar_venta extends Thread {

    cl_venta c_venta;
    cl_varios c_varios = new cl_varios();
    private int id_venta;
    private int guia;
    private int id_almacen;

    public cl_enviar_venta() {
        this.c_venta = new cl_venta();

    }

    private void cargar_venta() {
        c_venta.setId_almacen(id_almacen);
        c_venta.setId_venta(id_venta);
        c_venta.validar_venta();
    }

    private void enviar_venta() {
        String[] envio_sunat;
        envio_sunat = cl_envio_server.enviar_documento(c_venta.getId_venta(), c_venta.getId_tido(), c_venta.getId_almacen());

        String nombre_archivo = envio_sunat[0];
        String url_codigo_qr = envio_sunat[2];
        String hash = envio_sunat[3];
        String estatus = envio_sunat[5];
        if (estatus.equals("aceptado")) {
            System.out.println("imprimiendo docmumento de venta");
            //imprimir boleta o factura
            leer_numeros c_letras = new leer_numeros();
            String letras_numeros = c_letras.Convertir(c_venta.getTotal() + "", true) + " SOLES";
            System.out.println(letras_numeros);
            System.out.println(url_codigo_qr);

            File miDir = new File(".");
            try {
                Map<String, Object> parametros = new HashMap<>();
                String path = miDir.getCanonicalPath();
                String diagonal = File.separator;
                String direccion = path + diagonal + "reports" + diagonal + "subreports" + diagonal;

                System.out.println(direccion);
                parametros.put("SUBREPORT_DIR", direccion);
                parametros.put("JRParameter.REPORT_LOCALE", Locale.ENGLISH);
                parametros.put("REPORT_LOCALE", Locale.ENGLISH);
                parametros.put("p_id_venta", c_venta.getId_venta());
                parametros.put("p_id_almacen", c_venta.getId_almacen());
                parametros.put("p_letras_numero", letras_numeros);
                parametros.put("p_codigo_qr", url_codigo_qr);
                parametros.put("p_hash", hash);
                //c_varios.imp_reporte("rpt_documento_venta", parametros);
               
                    c_varios.ver_reporte("rpt_documento_venta", parametros);
                
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al recibir el comprobante");
        }
    }

    private void enviar_guia() {
        String[] envio_sunat;
        envio_sunat = cl_envio_server.enviar_documento(id_venta, 5, id_almacen);

        String nombre_archivo = envio_sunat[0];
        String url_codigo_qr = envio_sunat[2];
        String hash = envio_sunat[3];
        String estatus = envio_sunat[5];
        if (estatus.equals("aceptado")) {
            File miDir = new File(".");
            try {
                Map<String, Object> parametros = new HashMap<>();
                String path = miDir.getCanonicalPath();
                String diagonal = File.separator;
                String direccion = path + diagonal + "reports" + diagonal + "subreports" + diagonal;

                System.out.println(direccion);
                parametros.put("SUBREPORT_DIR", direccion);
                parametros.put("JRParameter.REPORT_LOCALE", Locale.ENGLISH);
                parametros.put("REPORT_LOCALE", Locale.ENGLISH);
                parametros.put("p_id_venta", c_venta.getId_venta());
                parametros.put("p_id_almacen", c_venta.getId_almacen());
                parametros.put("p_codigo_qr", url_codigo_qr);
                parametros.put("p_hash", hash);
                //c_varios.imp_reporte("rpt_documento_venta", parametros);
                if (id_almacen == 1) {
                    c_varios.ver_reporte("rpt_documento_guia_rodson", parametros);
                } else {
                    c_varios.ver_reporte("rpt_documento_guia", parametros);
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al recibir el comprobante");
        }
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public void setGuia(int guia) {
        this.guia = guia;
    }

    public void setId_almacen(int id_almacen) {
        this.id_almacen = id_almacen;
    }

    @Override
    public void run() {
        cargar_venta();
        enviar_venta();

        //si guia = 0 enviar guia; sino, es 1 selecciono sin guia
        /*if (guia == 0) {
            enviar_guia();
        }*/

    }
}
