/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author luis
 */
public class cl_productos_ventas {

    cl_conectar c_conectar = new cl_conectar();
    cl_varios c_varios = new cl_varios();

    private int id_venta;
    private int id_almacen;
    private int id_producto;
    private double precio;
    private double costo;
    private int cantidad;

    public cl_productos_ventas() {
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getId_almacen() {
        return id_almacen;
    }

    public void setId_almacen(int id_almacen) {
        this.id_almacen = id_almacen;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean registrar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "insert into productos_ventas "
                + "Values ('" + id_almacen + "', '" + id_venta + "', '" + id_producto + "', '" + cantidad + "', '" + costo + "', '" + precio + "')";
        int resultado = c_conectar.actualiza(st, query);
        // System.out.println(query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
    }

    public boolean eliminar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "delete from productos_ventas "
                + "where id_almacen = '" + id_almacen + "' and id_ventas = '" + id_venta + "'";
        int resultado = c_conectar.actualiza(st, query);
        // System.out.println(query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
    }

    public int contar_lineas() {
        int contar = 0;
        try {
            Statement st = c_conectar.conexion();
            String query = "select count(*) as contar "
                    + "from productos_ventas "
                    + "where id_almacen = '" + id_almacen + "' and id_ventas = '" + id_venta + "'";
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                contar = rs.getInt("contar");
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return contar;
    }

    public void mostrar_modificar(DefaultTableModel modelo) {
        try {
            //c_conectar.conectar();
            String query = "select pv.id_producto, p.descripcion, p.marca,  pv.cantidad, pv.precio "
                    + "from productos_ventas as pv "
                    + "inner join productos as p on p.id_producto = pv.id_producto "
                    + "where id_ventas = '" + id_venta + "' and id_almacen = '" + id_almacen + "' "
                    + "order by p.descripcion asc";
            Statement st = c_conectar.conexion();
            ResultSet rs = c_conectar.consulta(st, query);

            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[5];
                fila[0] = rs.getInt("id_producto");
                fila[1] = (rs.getString("descripcion").trim() + " - " + rs.getString("marca").trim() ).trim();
                int pcantidad = rs.getInt("cantidad");
                double pprecio = rs.getDouble("precio");
                double pparcial = pcantidad * pprecio;
                fila[2] = pcantidad;
                fila[3] = pprecio;
                fila[4] = pparcial;
                modelo.addRow(fila);
            }
            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
        } catch (SQLException e) {
            System.out.print(e);
        }
    }

    public void mostrar_traslado(DefaultTableModel modelo) {
        try {
            //c_conectar.conectar();
            String query = "select pv.id_producto, p.descripcion, p.marca, pa.cactual, pv.cantidad, pv.precio "
                    + "from productos_ventas as pv "
                    + "inner join productos_almacen as pa on pa.id_almacen = pv.id_almacen and pa.id_producto = pv.id_producto "
                    + "inner join productos as p on p.id_producto = pv.id_producto "
                    + "where pv.id_ventas = '" + id_venta + "' and pv.id_almacen = '" + id_almacen + "' "
                    + "order by p.descripcion asc";
            System.out.println(query);
            Statement st = c_conectar.conexion();
            ResultSet rs = c_conectar.consulta(st, query);

            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[6];
                fila[0] = rs.getInt("id_producto");
                fila[1] = (rs.getString("descripcion").trim() ).trim();
                fila[2] = rs.getString("marca").trim();
                int pcantidad = rs.getInt("cantidad");
                double pprecio = rs.getDouble("precio");
                double pparcial = pcantidad * pprecio;
                fila[3] = rs.getDouble("cactual");
                fila[4] = pcantidad;
                fila[5] = pprecio;
                modelo.addRow(fila);
            }
            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
        } catch (SQLException e) {
            System.out.print(e);
        }
    }

    public void mostrar(JTable tabla) {
        DefaultTableModel modelo;
        try {
            modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };
            //c_conectar.conectar();
            String query = "select pv.id_producto, p.descripcion, p.marca,  pv.cantidad, pv.precio "
                    + "from productos_ventas as pv "
                    + "inner join productos as p on p.id_producto = pv.id_producto "
                    + "where id_ventas = '" + id_venta + "' and id_almacen = '" + id_almacen + "' "
                    + "order by p.descripcion asc";
            Statement st = c_conectar.conexion();
            ResultSet rs = c_conectar.consulta(st, query);

            //La cantidad de columnas que tiene la consulta
            //Establecer como cabezeras el nombre de las colimnas
            modelo.addColumn("Id");
            modelo.addColumn("Producto");
            modelo.addColumn("Marca");
            modelo.addColumn("Cant.");
            modelo.addColumn("Precio");
            modelo.addColumn("Subtotal");

            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[6];
                fila[0] = rs.getInt("id_producto");
                fila[1] = (rs.getString("descripcion").trim() ).trim();
                fila[2] = rs.getString("marca").trim();
                int pcantidad = rs.getInt("cantidad");
                double pprecio = rs.getDouble("precio");
                double pparcial = pcantidad * pprecio;
                fila[3] = pcantidad;
                fila[4] = c_varios.formato_totales(pprecio);
                fila[5] = c_varios.formato_totales(pparcial);
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
        } catch (SQLException e) {
            System.out.print(e);
        }
    }
}
