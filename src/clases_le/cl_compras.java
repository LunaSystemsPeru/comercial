/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases_le;

import clases.cl_conectar;
import clases.cl_varios;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author s1nn0mbr3
 */
public class cl_compras {

    cl_conectar c_conectar = new cl_conectar();
    cl_varios c_varios = new cl_varios();

    private int periodo;
    private int id_empresa;

    public cl_compras() {
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public void llenar_libros() {
        String query = "SELECT c.id_compra, c.fecha, em.ruc as ruc_empresa, c.estado, p.nro_documento, "
                + "p.razon_social, ds.cod_sunat, ds.abreviado as doc_sunat, c.serie, c.numero, c.total "
                + "FROM compras as c "
                + "inner join proveedor as p on p.id_proveedor = c.id_proveedor "
                + "inner join empresa as em on em.id_empresa = c.id_empresa "
                + "inner join documentos_sunat as ds on ds.id_tido = c.id_tido "
                + "where concat(year(c.fecha), LPAD(month(c.fecha), 2, 0)) = '201909' "
                + "order by c.fecha asc ";
        Statement st = c_conectar.conexion();
        ResultSet rs = c_conectar.consulta(st, query);
        int nro_linea = 1;
        ArrayList lineas = new ArrayList();

        try {
            while (rs.next()) {
                int tipo_proveedor = 1;
                if (rs.getString("nro_documento").equals("00000000")) {
                    tipo_proveedor = 0;
                }
                if (rs.getString("nro_documento").length() == 11) {
                    tipo_proveedor = 6;
                }

                double total = rs.getDouble("total");
                double subtotal = total / 1.18;
                double igv = subtotal * 0.18;

                String linea = periodo + "00" + "|"
                        + nro_linea + "|"
                        + "M" + nro_linea + "|"
                        + c_varios.fecha_usuario(rs.getString("fecha")) + "|"
                        + c_varios.fecha_usuario(rs.getString("fecha")) + "|"
                        + rs.getString("cod_sunat") + "|"
                        + rs.getString("serie") + "|"
                        + "|"
                        + rs.getInt("numero") + "|"
                        + "|"
                        + tipo_proveedor + "|"
                        + rs.getString("nro_documento") + "|"
                        + rs.getString("razon_social") + "|"
                        + c_varios.formato_numero(subtotal) + "|"
                        + c_varios.formato_numero(igv) + "|"
                        + "0.00" + "|"
                        + "0.00" + "|"
                        + "0.00" + "|"
                        + "0.00" + "|"
                        + "0.00" + "|"
                        + "0.00" + "|"
                        + "0.00" + "|"
                        + c_varios.formato_numero(total) + "|"
                        + "1" + "|"
                        + "1.000" + "|"
                        + "|"
                        + "|"
                        + "|"
                        + "|"
                        + "|"
                        + "|"
                        + "|"
                        + "|"
                        + "|"
                        + "|"
                        + "|"
                        + "|"
                        + "|"
                        + "|"
                        + "6" + "|";

                lineas.add(linea);

                nro_linea++;
            }

            String info_le = "0";
            if (nro_linea > 1) {
                info_le = "1";
            }

            String nombre_libro = "LE" + "10444441629" + periodo + "00" + "080100" + "00" + "1" + info_le + "1" + "1";

            File miDir = new File(".");
            String direccion = "";

            FileWriter fichero = null;
            PrintWriter pw = null;
            try {
                String path = miDir.getCanonicalPath();
                direccion = path + File.separator + "temp" + File.separator;

                fichero = new FileWriter(direccion + nombre_libro + ".txt");
                pw = new PrintWriter(fichero);

                for (Object linea : lineas) {
                    pw.write(linea + "\n");
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    // Nuevamente aprovechamos el finally para 
                    // asegurarnos que se cierra el fichero.
                    if (null != fichero) {
                        fichero.close();
                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            JOptionPane.showMessageDialog(null, "Libro creado exitosamente \n" + nombre_libro + "\n" + "en " + direccion);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }
        c_conectar.cerrar(st);
        c_conectar.cerrar(rs);
    }
}
