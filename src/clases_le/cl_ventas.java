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
public class cl_ventas {

    cl_conectar c_conectar = new cl_conectar();
    cl_varios c_varios = new cl_varios();

    private int id_emprea;
    private int periodo;

    public cl_ventas() {
    }

    public int getId_emprea() {
        return id_emprea;
    }

    public void setId_emprea(int id_emprea) {
        this.id_emprea = id_emprea;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public void llenar_libros() {
        String query = "select v.id_ventas, v.fecha, c.documento, c.nombre, ds.cod_sunat, ds.abreviado, v.serie, v.numero, "
                + "v.total, v.estado, v.tipo_venta "
                + "from ventas as v "
                + "inner join clientes as c on c.id_cliente = v.id_cliente "
                + "inner join documentos_sunat as ds on ds.id_tido = v.id_tido "
                + "inner join usuarios as u on u.id_usuarios = v.id_usuarios "
                + "where concat(year(v.fecha), LPAD(month(v.fecha), 2, 0)) = '201909' and v.id_almacen = 1  and ds.id_tido in (1,2) "
                + "order by v.fecha asc, v.id_ventas asc";

        Statement st = c_conectar.conexion();
        ResultSet rs = c_conectar.consulta(st, query);
        int nro_linea = 1;
        ArrayList lineas = new ArrayList();

        try {
            while (rs.next()) {
                String linea = "";
                int tipo_cliente = 1;
                if (rs.getString("documento").equals("00000000")) {
                    tipo_cliente = 0;
                }
                if (rs.getString("documento").length() == 11) {
                    tipo_cliente = 6;
                }

                double total = rs.getDouble("total");
                double subtotal = total / 1.18;
                double igv = subtotal * 0.18;

                linea = periodo + "00" + "|"
                        + c_varios.ceros_izquieda_numero(4, nro_linea) + "|"
                        + "M" + nro_linea + "|"
                        + c_varios.fecha_usuario(rs.getString("fecha")) + "|"
                        + c_varios.fecha_usuario(rs.getString("fecha")) + "|"
                        + rs.getString("cod_sunat") + "|"
                        + rs.getString("serie") + "|"
                        + rs.getInt("numero") + "|"
                        + "|"
                        + tipo_cliente + "|"
                        + rs.getString("documento") + "|"
                        + rs.getString("nombre") + "|"
                        + 0.00 + "|"
                        + c_varios.formato_numero(subtotal) + "|"
                        + 0.00 + "|"
                        + c_varios.formato_numero(igv) + "|"
                        + 0.00 + "|"
                        + 0.00 + "|"
                        + 0.00 + "|"
                        + 0.00 + "|"
                        + 0.00 + "|"
                        + 0.00 + "|"
                        + 0.00 + "|"
                        + c_varios.formato_numero(total) + "|"
                        + 1 + "|"
                        + 1.000 + "|"
                        + "|"
                        + "|"
                        + "|"
                        + "|"
                        + "|"
                        + "|"
                        + "|";
                lineas.add(linea);

                nro_linea++;
            }
            String info_le = "0";
            if (nro_linea > 1) {
                info_le = "1";
            }

            String nombre_libro = "LE" + "10444441629" + periodo + "00" + "140100" + "00" + "1" + info_le + "1" + "1";

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
