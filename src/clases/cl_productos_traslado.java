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
 * @author User
 */
public class cl_productos_traslado {

    cl_conectar c_conectar = new cl_conectar();
    cl_varios c_varios = new cl_varios();

    private int id_traslado;
    private int id_almacen;
    private int id_producto;
    private int cenviado;
    private int crecibido;

    public cl_productos_traslado() {
    }

    public int getId_traslado() {
        return id_traslado;
    }

    public void setId_traslado(int id_traslado) {
        this.id_traslado = id_traslado;
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

    public int getCenviado() {
        return cenviado;
    }

    public void setCenviado(int cenviado) {
        this.cenviado = cenviado;
    }

    public int getCrecibido() {
        return crecibido;
    }

    public void setCrecibido(int crecibido) {
        this.crecibido = crecibido;
    }

    public boolean registrar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "insert into productos_traslados "
                + "Values ('" + id_traslado + "', '" + id_producto + "', '" + cenviado + "', '" + crecibido + "')";
        int resultado = c_conectar.actualiza(st, query);
        // System.out.println(query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
    }

    public boolean actualizar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "update productos_traslados "
                + "set c_recibido = '" + crecibido + "' "
                + "where id_traslado = '" + id_traslado + "' and id_producto = '" + id_producto + "'";
        int resultado = c_conectar.actualiza(st, query);
        //System.out.println(query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
    }

    public boolean eliminar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "delete from productos_traslados "
                + "where id_traslado = '" + id_traslado + "'";
        int resultado = c_conectar.actualiza(st, query);
        //  System.out.println(query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
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
            String query = "select pt.id_producto, p.descripcion, p.marca, p.modelo, p.precio, pt.c_enviado, pt.c_recibido "
                    + "from productos_traslados as pt "
                    + "inner join productos as p on p.id_producto = pt.id_producto "
                    + "where pt.id_traslado = '" + id_traslado + "'";
            //System.out.println(query);
            Statement st = c_conectar.conexion();
            ResultSet rs = c_conectar.consulta(st, query);

            //La cantidad de columnas que tiene la consulta
            //Establecer como cabezeras el nombre de las colimnas
            modelo.addColumn("Id");
            modelo.addColumn("Producto");
            modelo.addColumn("Marca");
            modelo.addColumn("Precio");
            modelo.addColumn("C. enviada");
            modelo.addColumn("C. recibido");

            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[6];
                fila[0] = rs.getInt("id_producto");
                fila[1] = (rs.getString("descripcion").trim() + " " + rs.getString("modelo").trim()).trim();
                fila[2] = rs.getString("marca").trim();
                int tcenviado = rs.getInt("c_enviado");
                double pprecio = rs.getDouble("precio");
                fila[3] = c_varios.formato_totales(pprecio);
                fila[4] = tcenviado;
                fila[5] = 0;
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
            c_varios.centrar_celda(tabla, 0);
            c_varios.centrar_celda(tabla, 2);
            c_varios.derecha_celda(tabla, 3);
            c_varios.centrar_celda(tabla, 4);
            c_varios.centrar_celda(tabla, 5);
        } catch (SQLException e) {
            System.out.print(e);
        }
    }

    public DefaultTableModel model_borrador() {
        DefaultTableModel modelo = null;
        try {
            modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };
            //c_conectar.conectar();
            String query = "select pt.id_producto, p.descripcion, p.marca, p.modelo, p.precio, pt.c_enviado, pt.c_recibido, pa.cactual "
                    + "from productos_traslados as pt "
                    + "inner join traslados as t on t.id_traslado = pt.id_traslado "
                    + "inner join productos as p on p.id_producto = pt.id_producto "
                    + "inner join productos_almacen as pa on pa.id_producto = pt.id_producto and pa.id_almacen = t.a_origen "
                    + "where pt.id_traslado = '" + id_traslado + "'";
//            System.out.println(query);
            Statement st = c_conectar.conexion();
            ResultSet rs = c_conectar.consulta(st, query);

            //La cantidad de columnas que tiene la consulta
            //Establecer como cabezeras el nombre de las colimnas
            modelo.addColumn("Id");
            modelo.addColumn("Descripcion");
            modelo.addColumn("Marca");
            modelo.addColumn("C. Actual");
            modelo.addColumn("Cant.");
            modelo.addColumn("Precio");

            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[6];
                fila[0] = rs.getInt("id_producto");
                fila[1] = (rs.getString("descripcion").trim() + " " + rs.getString("modelo").trim()).trim();
                fila[2] = rs.getString("marca").trim();
                int cactual = rs.getInt("cactual");
                double pprecio = rs.getDouble("precio");
                fila[3] = cactual;
                fila[4] = rs.getInt("c_enviado");
                fila[5] = c_varios.formato_totales(pprecio);
                modelo.addRow(fila);
            }
            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
        } catch (SQLException e) {
            System.out.print(e);
        }
        return modelo;
    }
}
