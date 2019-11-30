/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Flavio
 */
public class cl_salida {

    cl_conectar c_conectar = new cl_conectar();
 cl_varios c_varios = new cl_varios();
 
    private int id_salida;
    private String fecha;
    private int id_almacen;
    private int id_tido;
    private String serie;
    private int numero;
    private String documento;
    private String datos;
    private int id_usuario;
    private int estado;

    public cl_salida() {

    }

    public int getId_salida() {
        return id_salida;
    }

    public void setId_salida(int id_salida) {
        this.id_salida = id_salida;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId_almacen() {
        return id_almacen;
    }

    public void setId_almacen(int id_almacen) {
        this.id_almacen = id_almacen;
    }

    public int getId_tido() {
        return id_tido;
    }

    public void setId_tido(int id_tido) {
        this.id_tido = id_tido;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void obtener_codigo() {
        try {
            Statement st = c_conectar.conexion();
            String query = "select ifnull(max(id_salida) + 1, 1) as codigo "
                    + "from salidas ";
            ResultSet rs = c_conectar.consulta(st, query);

            while (rs.next()) {
                id_salida = rs.getInt("codigo");
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }
    }

    public boolean registrar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "insert into salidas "
                + "Values ('" + id_salida + "', '" + fecha + "', '" + id_almacen + "', '" + id_tido + "', '" + serie + "', '" + numero + "', '" + documento + "', '" + datos + "', '" + id_usuario + "','1')";
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
    }

    public boolean anular() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "update salidas "
                + "set estado = '2' "
                + "where id_salida = '" + id_salida + "'";
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
    }

    public void mostrar(JTable tabla, String query) {
        try {
            DefaultTableModel modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };

            Statement st = c_conectar.conexion();
            ResultSet rs = c_conectar.consulta(st, query);
           
            //nombre de las columnas de las tablas
            modelo.addColumn("Id");
            modelo.addColumn("Fecha");
            modelo.addColumn("Documento");
            modelo.addColumn("Persona");
            modelo.addColumn("Usuario");
            modelo.addColumn("Estado");

            //Creando las filas para el JTable
            //nombre dela columna de la consulta
            while (rs.next()) {
                Object[] fila = new Object[6];
                fila[0] = rs.getInt("id_salida");
                fila[1] = rs.getString("fecha");
                fila[2] = rs.getString("docsunat") + " / " + rs.getString("serie") + " - " + rs.getString("numero");
                fila[3] = rs.getString("documento") + " | " + rs.getString("datos");
                fila[4] = rs.getString("nomusuario");
                if (rs.getString("estado").equals("1")) {
                    fila[5] = "ACTIVO";
                } else {
                    fila[5] = "ANULADO";
                }

                modelo.addRow(fila);
            }

            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);

            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(30);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(450);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(70);
            c_varios.centrar_celda(tabla,0);
            c_varios.centrar_celda(tabla,1);
            c_varios.centrar_celda(tabla,2);
            c_varios.centrar_celda(tabla,4);
            c_varios.centrar_celda(tabla,5);
            
            
        } catch (SQLException e) {
            System.out.print(e);
        }

    }

}
