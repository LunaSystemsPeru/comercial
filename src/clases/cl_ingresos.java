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
import javax.swing.table.TableRowSorter;
import render_tablas.render_ventas;

/**
 *
 * @author luis
 */
public class cl_ingresos {

    cl_conectar c_conectar = new cl_conectar();
    cl_varios c_varios = new cl_varios();

    private int id_ingreso;
    private int id_almacen;
    private String fecha;
    private int id_proveedor;
    private int id_tido;
    private String serie;
    private int numero;
    private double total;
    private int id_moneda;
    private double tc;
    private int id_usuario;

    public cl_ingresos() {
    }

    public int getId_ingreso() {
        return id_ingreso;
    }

    public void setId_ingreso(int id_ingreso) {
        this.id_ingreso = id_ingreso;
    }

    public int getId_almacen() {
        return id_almacen;
    }

    public void setId_almacen(int id_almacen) {
        this.id_almacen = id_almacen;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getId_moneda() {
        return id_moneda;
    }

    public void setId_moneda(int id_moneda) {
        this.id_moneda = id_moneda;
    }

    public double getTc() {
        return tc;
    }

    public void setTc(double tc) {
        this.tc = tc;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    public boolean validar_ingreso() {
        boolean existe = false;
        try {
            Statement st = c_conectar.conexion();
            String query = "select * "
                    + "from ingresos "
                    + "where id_ingreso = '" + id_ingreso + "'";
            System.out.println(query);
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                existe = true;
                fecha = rs.getString("fecha");
                id_almacen = rs.getInt("id_almacen");
                id_proveedor = rs.getInt("id_proveedor");
                id_tido = rs.getInt("id_tido");
                serie = rs.getString("serie");
                numero = rs.getInt("numero");
                total = rs.getDouble("total");
                id_moneda = rs.getInt("id_moneda");
                id_usuario = rs.getInt("id_usuarios");
                tc = rs.getDouble("tc");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return existe;
    }

    public boolean validar_documento() {
        boolean existe = false;
        try {
            Statement st = c_conectar.conexion();
            String query = "select * "
                    + "from ingresos "
                    + "where id_proveedor = '" + id_proveedor + "' and id_tido = '" + id_tido + "' and serie = '" + serie + "' and numero = '" + numero + "'";
            System.out.println(query);
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                existe = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return existe;
    }
    
    public boolean validar_documento_kardex() {
        boolean existe = false;
        try {
            Statement st = c_conectar.conexion();
            String query = "select id_ingreso, periodo "
                    + "from ingresos "
                    + "where id_almacen = '" + id_almacen + "' and id_tido = '" + id_tido + "' and serie = '" + serie + "' and numero = '" + numero + "' and fecha = '"+fecha+"'";
            System.out.println(query);
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                existe = true;
                this.id_ingreso = rs.getInt("id_ingreso");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return existe;
    }

    public void obtener_codigo() {
        try {
            Statement st = c_conectar.conexion();
            String query = "select ifnull(max(id_ingreso) + 1, 1) as codigo "
                    + "from ingresos ";
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                id_ingreso = rs.getInt("codigo");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
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
            TableRowSorter sorter = new TableRowSorter(tmodelo);
            Statement st = c_conectar.conexion();
            ResultSet rs = c_conectar.consulta(st, query);

            //Establecer como cabezeras el nombre de las colimnas
            tmodelo.addColumn("ID.");
            tmodelo.addColumn("Fecha");
            tmodelo.addColumn("Documento");
            tmodelo.addColumn("Proveedor");
            tmodelo.addColumn("Total");
            tmodelo.addColumn("Usuario");

            int contar = 0;
            //Creando las filas para el JTable
            while (rs.next()) {
                contar++;
                Object[] fila = new Object[6];
                fila[0] = rs.getString("id_ingreso");
                fila[1] = rs.getString("fecha");
                fila[2] = rs.getString("abreviado") + " | " + c_varios.ceros_izquieda_letras(4, rs.getString("serie")) + " - " + c_varios.ceros_izquieda_numero(7, rs.getInt("numero"));
                fila[3] = rs.getString("nro_documento") + " | " + rs.getString("razon_social");
                fila[4] = c_varios.formato_numero(rs.getDouble("total"));
                fila[5] = rs.getString("username");

                tmodelo.addRow(fila);
            }
            
            if (contar == 0) {
                JOptionPane.showMessageDialog(null, "NO SE HA ENCONTRADO RESULTADOS");
            }
            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
            tabla.setModel(tmodelo);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(450);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(120);
            tabla.setRowSorter(sorter);
            c_varios.derecha_celda(tabla, 4);

        } catch (SQLException e) {
            System.out.print(e);
        }
    }

    public boolean registrar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "insert into ingresos "
                + "values ('" + id_ingreso + "', '" + fecha + "', '" + id_almacen + "', '" + id_proveedor + "', '" + id_tido + "', '" + serie + "', '" + numero + "', "
                + "'" + total + "', '" + id_moneda + "', '" + tc + "', '" + id_usuario + "')";
        System.out.println(query);
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        return registrado;
    }

    public boolean eliminar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "delete from ingresos "
                + "where id_ingreso = '" + id_ingreso + "'";
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
    }

}
