/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author luis
 */
public class cl_productos_ingresos_bono {
    
    cl_conectar c_conectar = new cl_conectar();
    cl_varios c_varios = new cl_varios();
    
    private int id_ingreso;
    private int id_producto;
    private double cantidad;

    public cl_productos_ingresos_bono() {
    }

    public int getId_ingreso() {
        return id_ingreso;
    }

    public void setId_ingreso(int id_ingreso) {
        this.id_ingreso = id_ingreso;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
    
     public boolean registrar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "insert into productos_ingresos_bono "
                + "values ('" + id_ingreso + "','" + id_producto + "', '" + cantidad + "')";
        int resultado = c_conectar.actualiza(st, query);
        //  System.out.println(query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
    }
     
      public void mostrar_detalle(JTable tabla) {
        DefaultTableModel modelo;
        try {
            modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };
            //c_conectar.conectar();
            String query = "select pi.id_producto, p.descripcion, p.marca, pi.cantidad "
                    + "from productos_ingresos_bono as pi "
                    + "inner join productos as p on p.id_producto = pi.id_producto "
                    + "where pi.id_ingreso= '" + id_ingreso + "'";
            System.out.println(query);
            Statement st = c_conectar.conexion();
            ResultSet rs = c_conectar.consulta(st, query);

            //La cantidad de columnas que tiene la consulta
            //Establecer como cabezeras el nombre de las colimnas
            modelo.addColumn("Id");
            modelo.addColumn("Producto");
            modelo.addColumn("Marca");
            modelo.addColumn("Cantidad");

            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[7];
                fila[0] = rs.getInt("id_producto");
                fila[1] = rs.getString("descripcion").trim();
                fila[2] = rs.getString("marca").trim();
                fila[3] = rs.getInt("cantidad");
                modelo.addRow(fila);
            }
            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(40);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(400);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(80);
            c_varios.centrar_celda(tabla, 0);
            c_varios.centrar_celda(tabla, 2);
            c_varios.centrar_celda(tabla, 3);
        } catch (SQLException e) {
            System.out.print(e);
        }
    }
      
      public void mostrar_modificar(DefaultTableModel modelo) {
        try {
            //c_conectar.conectar();
            String query = "select pi.id_producto, p.descripcion, p.marca, pi.cantidad "
                    + "from productos_ingresos_bono as pi "
                    + "inner join productos as p on p.id_producto = pi.id_producto "
                    + "where pi.id_ingreso= '" + id_ingreso + "'";
            System.out.println(query);
            Statement st = c_conectar.conexion();
            ResultSet rs = c_conectar.consulta(st, query);

            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[7];
                fila[0] = rs.getInt("id_producto");
                fila[1] = rs.getString("descripcion").trim();
                fila[2] = rs.getString("marca").trim();
                fila[3] = rs.getInt("cantidad");
                modelo.addRow(fila);
            }
            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
        } catch (SQLException e) {
            System.out.print(e);
        }
    }
    
}
