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
import javax.swing.table.TableRowSorter;
import render_tablas.render_mis_productos;
import render_tablas.render_productos_tiendas;

/**
 *
 * @author PROYECTOS
 */
public class cl_productos_almacen {

    cl_conectar c_conectar = new cl_conectar();
    cl_varios c_varios = new cl_varios();

    private int producto;
    private int almacen;
    private double ctotal;
    private double csunat;
    private String fecha_ingreso;
    private String fecha_salida;

    public cl_productos_almacen() {
    }

    public int getProducto() {
        return producto;
    }

    public void setProducto(int producto) {
        this.producto = producto;
    }

    public int getAlmacen() {
        return almacen;
    }

    public void setAlmacen(int almacen) {
        this.almacen = almacen;
    }

    public double getCtotal() {
        return ctotal;
    }

    public void setCtotal(double ctotal) {
        this.ctotal = ctotal;
    }

    public double getCsunat() {
        return csunat;
    }

    public void setCsunat(double csunat) {
        this.csunat = csunat;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(String fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public void mostrar(JTable tabla, String query) {
        try {
            DefaultTableModel tmodelo;
            tmodelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };
            //    TableRowSorter sorter = new TableRowSorter(mostrar);
            Statement st = c_conectar.conexion();
            ResultSet rs = c_conectar.consulta(st, query);

            //Establecer como cabezeras el nombre de las colimnas
            tmodelo.addColumn("Tienda");
            tmodelo.addColumn("Cant. Actual");//descripcion modelo serie
            tmodelo.addColumn("Ult. Ingreso");
            tmodelo.addColumn("Ult. Salida");

            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[4];
                fila[0] = rs.getObject("nalmacen");
                fila[1] = rs.getInt("cactual");
                String f_ingreso = rs.getString("f_ingreso");
                if (f_ingreso.equals("1000-01-01")) {
                    f_ingreso = "-";
                }
                String f_salida = rs.getString("f_salida");
                if (f_salida.equals("1000-01-01")) {
                    f_salida = "-";
                }
                fila[2] = f_ingreso;
                fila[3] = f_salida;

                tmodelo.addRow(fila);
            }
            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
            tabla.setModel(tmodelo);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(80);
            //tabla.setDefaultRenderer(Object.class, new render_productos());
            //   t_productos.setRowSorter(sorter);

        } catch (SQLException e) {
            System.out.print(e);
        }
    }

    public void mis_productos(String query, JTable tabla) {
        try {
            DefaultTableModel mostrar = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };
            TableRowSorter sorter = new TableRowSorter(mostrar);

            Statement st = c_conectar.conexion();
            ResultSet rs = c_conectar.consulta(st, query);
            //Establecer como cabezeras el nombre de las colimnas
            mostrar.addColumn("Id");
            mostrar.addColumn("Descripcion");
            mostrar.addColumn("Marca");
            mostrar.addColumn("Cant. Act.");
            mostrar.addColumn("Cant. Sunat.");
            mostrar.addColumn("Precio");
            mostrar.addColumn("Ult. Ingreso");
            mostrar.addColumn("Ult. Salida");

            //Creando las filas para el JTable
            Object[] fila = new Object[8];
            while (rs.next()) {

                fila[0] = rs.getString("id_producto");
                fila[1] = (rs.getString("descripcion").trim() + " x " + rs.getString("um.nombre")).trim();
                fila[2] = rs.getString("marca").trim();
                fila[3] = rs.getDouble("cactual");
                fila[4] = rs.getDouble("csunat");
                fila[5] = c_varios.formato_numero(rs.getDouble("precio"));
                String fingreso = rs.getString("f_infreso");
                if (fingreso.equals("1000-01-01")) {
                    fingreso = "-";
                }
                String fsalida = rs.getString("f_salida");
                if (fsalida.equals("1000-01-01")) {
                    fsalida = "-";
                }
                fila[6] = fingreso;
                fila[7] = fsalida;

                mostrar.addRow(fila);
            }
            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
            tabla.setModel(mostrar);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(30);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(450);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(50);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(50);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(50);
            tabla.getColumnModel().getColumn(6).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(7).setPreferredWidth(80);
            tabla.setDefaultRenderer(Object.class, new render_mis_productos());
            mostrar.fireTableDataChanged();
            tabla.setRowSorter(sorter);
            tabla.updateUI();

        } catch (SQLException e) {
            System.out.print(e);
        }

    }

    public void productos_tiendas(String query, JTable tabla) {
        try {
            DefaultTableModel mostrar = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };
            TableRowSorter sorter = new TableRowSorter(mostrar);

            Statement st = c_conectar.conexion();
            ResultSet rs = c_conectar.consulta(st, query);
            //Establecer como cabezeras el nombre de las colimnas
            mostrar.addColumn("Id");
            mostrar.addColumn("Descripcion");
            mostrar.addColumn("Marca");
            mostrar.addColumn("Cant. Act.");
            mostrar.addColumn("Precio");
            mostrar.addColumn("Tienda");

            //Creando las filas para el JTable
            Object[] fila = new Object[6];
            while (rs.next()) {

                fila[0] = rs.getString("id_producto");
                fila[1] = (rs.getString("descripcion").trim()).trim();
                fila[2] = rs.getString("marca").trim();
                fila[3] = rs.getInt("cactual");
                fila[4] = c_varios.formato_numero(rs.getDouble("precio"));
                fila[5] = rs.getString("nalmacen");

                mostrar.addRow(fila);
            }
            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
            tabla.setModel(mostrar);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(30);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(450);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(50);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(50);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(80);
            tabla.setDefaultRenderer(Object.class, new render_productos_tiendas());
            tabla.setRowSorter(sorter);
            tabla.updateUI();

        } catch (SQLException e) {
            System.out.print(e);
        }

    }

    public boolean validar_id() {
        boolean existe = false;
        try {

            Statement st = c_conectar.conexion();
            String query = "select * "
                    + "from productos_almacen "
                    + "where id_producto = '" + producto + "' and id_almacen = '" + almacen + "'";
         //   System.out.println(query);
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                ctotal = rs.getDouble("cactual");
                csunat = rs.getDouble("csunat");
                fecha_ingreso = rs.getString("f_infreso");
                fecha_salida = rs.getString("f_salida");
                existe = true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return existe;
    }

    public boolean limpiar_tienda() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "delete from productos_almacen "
                + "where cactual = 0 and id_almacen = '" + almacen + "'";
        //System.out.println(query);
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
    }

    public boolean actualizar_cantidad_producto() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "update productos_almacen "
                + "set cactual = '" + ctotal + "' "
                + "where id_almacen = '" + almacen + "' and id_producto = '" + producto + "'";
        //System.out.println(query);
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
    }

}
