/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author luis
 */
public class cl_productos_inventarios {

    cl_conectar c_conectar = new cl_conectar();
    cl_varios c_varios = new cl_varios();

    private int id_inventario;
    private int id_producto;
    private double cactual;
    private double cfisico;

    public cl_productos_inventarios() {
    }

    public int getId_inventario() {
        return id_inventario;
    }

    public void setId_inventario(int id_inventario) {
        this.id_inventario = id_inventario;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public double getCactual() {
        return cactual;
    }

    public void setCactual(double cactual) {
        this.cactual = cactual;
    }

    public double getCfisico() {
        return cfisico;
    }

    public void setCfisico(double cfisico) {
        this.cfisico = cfisico;
    }

    public boolean registrar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "insert into productos_inventario "
                + "values ('" + id_producto + "','" + id_inventario + "', '" + cactual + "', '" + cfisico + "')";
        int resultado = c_conectar.actualiza(st, query);
        //  System.out.println(query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
    }

    public void mostrar_productos(JTable tabla) {
        DefaultTableModel modelo;
        try {
            modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };
            //c_conectar.conectar();
            String query = "select pi.id_producto, p.descripcion, p.marca, p.modelo, p.precio, pi.cant_actual, pi.cant_fisico "
                    + "from productos_inventario as pi "
                    + "inner join productos as p on p.id_producto = pi.id_producto "
                    + "where pi.id_inventario = '" + id_inventario + "' ";
            //System.out.println(query);
            Statement st = c_conectar.conexion();
            ResultSet rs = c_conectar.consulta(st, query);

            //La cantidad de columnas que tiene la consulta
            //Establecer como cabezeras el nombre de las colimnas
            modelo.addColumn("Id");
            modelo.addColumn("Producto");
            modelo.addColumn("Marca");
            modelo.addColumn("Precio");
            modelo.addColumn("C. Sistema");
            modelo.addColumn("C. Fisico");
            modelo.addColumn("Diferencia");

            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[7];
                fila[0] = rs.getInt("id_producto");
                fila[1] = (rs.getString("descripcion").trim() + " " + rs.getString("modelo").trim()).trim();
                fila[2] = rs.getString("marca").trim();
                int isistema = rs.getInt("cant_actual");
                int ifisico = rs.getInt("cant_fisico");
                int diferencia = ifisico - isistema;
                double pprecio = rs.getDouble("precio");
                fila[3] = c_varios.formato_totales(pprecio);
                fila[4] = isistema;
                fila[5] = ifisico;
                fila[6] = diferencia;
                modelo.addRow(fila);
            }
            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(40);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(400);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(70);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(70);
            tabla.getColumnModel().getColumn(6).setPreferredWidth(70);
            c_varios.centrar_celda(tabla, 0);
            c_varios.centrar_celda(tabla, 2);
            c_varios.derecha_celda(tabla, 3);
            c_varios.centrar_celda(tabla, 4);
            c_varios.centrar_celda(tabla, 5);
            c_varios.centrar_celda(tabla, 6);
        } catch (SQLException e) {
            System.out.print(e);
        }
    }
}
