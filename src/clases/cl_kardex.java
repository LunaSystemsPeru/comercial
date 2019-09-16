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
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author luis
 */
public class cl_kardex {

    cl_conectar c_conectar = new cl_conectar();
    cl_varios c_varios = new cl_varios();

    private int id_producto;
    private int id_almacen;
    private String fecha;
    private int id_kardex;
    private int id_tipo_movimiento;
    private double cant_ingreso;
    private double cant_salida;
    private double costo_ingreso;
    private double costo_salida;
    private int id_tido;
    private String serie;
    private int numero;
    private int id_usuario;

    public cl_kardex() {
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
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

    public int getId_kardex() {
        return id_kardex;
    }

    public void setId_kardex(int id_kardex) {
        this.id_kardex = id_kardex;
    }

    public int getId_tipo_movimiento() {
        return id_tipo_movimiento;
    }

    public void setId_tipo_movimiento(int id_tipo_movimiento) {
        this.id_tipo_movimiento = id_tipo_movimiento;
    }

    public double getCant_ingreso() {
        return cant_ingreso;
    }

    public void setCant_ingreso(double cant_ingreso) {
        this.cant_ingreso = cant_ingreso;
    }

    public double getCant_salida() {
        return cant_salida;
    }

    public void setCant_salida(double cant_salida) {
        this.cant_salida = cant_salida;
    }

    public double getCosto_ingreso() {
        return costo_ingreso;
    }

    public void setCosto_ingreso(double costo_ingreso) {
        this.costo_ingreso = costo_ingreso;
    }

    public double getCosto_salida() {
        return costo_salida;
    }

    public void setCosto_salida(double costo_salida) {
        this.costo_salida = costo_salida;
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

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void ver_kardex_diario(JTable tabla) {
        int saldo = 0;
        try {
            DefaultTableModel mostrar = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };
            String query = "select k.id_kardex, k.fecha, p.descripcion, p.marca,  tm.descripcion as nombre_timo, k.cant_ingreso, k.cant_salida, k.costo_ingreso, "
                    + "k.costo_salida, ds.abreviado, k.serie_documento, k.numero_documento, k.id_usuarios, u.username, k.fecha_registro "
                    + "from kardex_productos as k "
                    + "inner join productos as p on p.id_producto = k.id_producto "
                    + "inner join documentos_sunat as ds on ds.id_tido = k.id_tido "
                    + "inner join tipo_movimiento as tm on tm.id_tipo_movimiento = k.id_tipo_movimiento "
                    + "inner join usuarios as u on u.id_usuarios = k.id_usuarios "
                    + "where k.id_almacen = '" + id_almacen + "' and k.fecha_registro like '" + fecha + "%' "
                    + "order by k.fecha_registro asc, k.id_kardex asc ";

            System.out.println(query);
            
            Statement st = c_conectar.conexion();
            ResultSet rs = c_conectar.consulta(st, query);

            RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(mostrar);
            tabla.setRowSorter(sorter);

            mostrar.addColumn("COD");
            mostrar.addColumn("FECHA");
            mostrar.addColumn("Producto");
            mostrar.addColumn("MOTIVO");
            mostrar.addColumn("DOCUMENTO");
            mostrar.addColumn("C. Ingreso");
            mostrar.addColumn("C. Salida");
            mostrar.addColumn("S/ Ingreso");
            mostrar.addColumn("S/ Salida");
            mostrar.addColumn("Usuario");
            mostrar.addColumn("Fec. Registro");

            int total_ingreso = 0;
            int total_salida = 0;

            while (rs.next()) {
                Object fila[] = new Object[11];
                saldo = saldo + (rs.getInt("cant_ingreso") - rs.getInt("cant_salida"));
                fila[0] = rs.getString("id_kardex");
                fila[1] = c_varios.fecha_usuario(rs.getString("fecha"));
                fila[2] = (rs.getString("descripcion").trim() + " " + rs.getString("marca").trim()).trim();
                fila[3] = rs.getString("nombre_timo");
                fila[4] = rs.getString("abreviado") + " | " + c_varios.ceros_izquieda_letras(4, rs.getString("serie_documento")) + " - " + c_varios.ceros_izquieda_numero(7, rs.getInt("numero_documento"));
                String cingreso = rs.getString("cant_ingreso");
                if (cingreso.equals("0")) {
                    cingreso = "-";
                }
                String csalida = rs.getString("cant_salida");
                if (csalida.equals("0")) {
                    csalida = "-";
                }

                total_ingreso += rs.getInt("cant_ingreso");
                total_salida += rs.getInt("cant_salida");

                fila[5] = cingreso;
                fila[6] = csalida;
                fila[7] = c_varios.formato_numero(rs.getDouble("costo_ingreso"));
                fila[8] = c_varios.formato_numero(rs.getDouble("costo_salida"));
                fila[9] = rs.getString("username");
                fila[10] = rs.getString("fecha_registro");

                mostrar.addRow(fila);
            }

            Object filaf[] = new Object[11];
            filaf[0] = "Z";
            filaf[1] = "";
            filaf[2] = "TOTALES";
            filaf[3] = "";
            filaf[4] = "";
            filaf[5] = total_ingreso;
            filaf[6] = total_salida;
            filaf[7] = "";
            filaf[8] = "";
            filaf[9] = "";
            filaf[10] = "";

            mostrar.addRow(filaf);

            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
            tabla.setModel(mostrar);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(50);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(400);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(50);
            tabla.getColumnModel().getColumn(6).setPreferredWidth(50);
            tabla.getColumnModel().getColumn(7).setPreferredWidth(60);
            tabla.getColumnModel().getColumn(8).setPreferredWidth(60);
            tabla.getColumnModel().getColumn(9).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(10).setPreferredWidth(150);
            c_varios.centrar_celda(tabla, 0);
            c_varios.centrar_celda(tabla, 1);
            c_varios.centrar_celda(tabla, 3);
            c_varios.centrar_celda(tabla, 4);
            c_varios.derecha_celda(tabla, 5);
            c_varios.derecha_celda(tabla, 6);
            c_varios.derecha_celda(tabla, 7);
            c_varios.derecha_celda(tabla, 8);
            c_varios.centrar_celda(tabla, 9);
            c_varios.centrar_celda(tabla, 10);
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void ver_kardex(JTable tabla) {
        int saldo = 0;
        try {
            DefaultTableModel mostrar = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };
            String query = "select k.id_kardex, k.fecha, tm.descripcion, k.cant_ingreso, k.cant_salida, k.costo_ingreso, k.costo_salida, ds.abreviado, k.serie_documento, "
                    + "k.numero_documento, k.id_usuarios, u.username, k.fecha_registro "
                    + "from kardex_productos as k "
                    + "inner join documentos_sunat as ds on ds.id_tido = k.id_tido "
                    + "inner join tipo_movimiento as tm on tm.id_tipo_movimiento = k.id_tipo_movimiento "
                    + "inner join usuarios as u on u.id_usuarios = k.id_usuarios "
                    + "where k.id_producto = '" + id_producto + "' and k.id_almacen = '" + id_almacen + "' "
                    + "order by k.fecha asc, k.id_kardex asc";

            //System.out.println(query);
            Statement st = c_conectar.conexion();
            ResultSet rs = c_conectar.consulta(st, query);

            RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(mostrar);
            tabla.setRowSorter(sorter);

            mostrar.addColumn("COD");
            mostrar.addColumn("FECHA");
            mostrar.addColumn("MOTIVO");
            mostrar.addColumn("DOCUMENTO");
            mostrar.addColumn("C. Ingreso");
            mostrar.addColumn("C. Salida");
            mostrar.addColumn("SALDO");
            mostrar.addColumn("S/ Ingreso");
            mostrar.addColumn("S/ Salida");
            mostrar.addColumn("Usuario");
            mostrar.addColumn("Fec. Registro");

            int total_ingreso = 0;
            int total_salida = 0;

            while (rs.next()) {
                Object fila[] = new Object[11];
                saldo = saldo + (rs.getInt("cant_ingreso") - rs.getInt("cant_salida"));
                fila[0] = rs.getString("id_kardex");
                fila[1] = c_varios.fecha_usuario(rs.getString("fecha"));
                fila[2] = rs.getString("descripcion");
                fila[3] = rs.getString("abreviado") + " | " + c_varios.ceros_izquieda_letras(4, rs.getString("serie_documento")) + " - " + c_varios.ceros_izquieda_numero(7, rs.getInt("numero_documento"));
                fila[4] = rs.getInt("cant_ingreso");
                fila[5] = rs.getInt("cant_salida");
                fila[6] = saldo;
                fila[7] = c_varios.formato_numero(rs.getDouble("costo_ingreso"));
                fila[8] = c_varios.formato_numero(rs.getDouble("costo_salida"));
                fila[9] = rs.getString("username");
                fila[10] = rs.getString("fecha_registro");

                mostrar.addRow(fila);

                total_ingreso += rs.getInt("cant_ingreso");
                total_salida += rs.getInt("cant_salida");
            }

            Object filaf[] = new Object[11];
            filaf[0] = "";
            filaf[1] = "";
            filaf[2] = "TOTALES";
            filaf[3] = "";
            filaf[4] = total_ingreso;
            filaf[5] = total_salida;
            filaf[6] = total_ingreso - total_salida;
            filaf[7] = "";
            filaf[8] = "";
            filaf[9] = "";
            filaf[10] = "";

            mostrar.addRow(filaf);

            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
            tabla.setModel(mostrar);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(50);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(50);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(50);
            tabla.getColumnModel().getColumn(6).setPreferredWidth(50);
            tabla.getColumnModel().getColumn(7).setPreferredWidth(60);
            tabla.getColumnModel().getColumn(8).setPreferredWidth(60);
            tabla.getColumnModel().getColumn(9).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(10).setPreferredWidth(150);
            c_varios.centrar_celda(tabla, 0);
            c_varios.centrar_celda(tabla, 1);
            c_varios.centrar_celda(tabla, 2);
            c_varios.centrar_celda(tabla, 3);
            c_varios.derecha_celda(tabla, 4);
            c_varios.derecha_celda(tabla, 5);
            c_varios.derecha_celda(tabla, 6);
            c_varios.derecha_celda(tabla, 7);
            c_varios.derecha_celda(tabla, 8);
            c_varios.centrar_celda(tabla, 9);
            c_varios.centrar_celda(tabla, 10);
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public boolean obtener_datos() {
        boolean existe = false;
        try {
            Statement st = c_conectar.conexion();
            String sql = "select * "
                    + "from kardex_productos "
                    + "where id_producto = '" + id_producto + "' and id_almacen = '" + this.id_almacen + "' and id_kardex = '" + this.id_kardex + "'";
            ResultSet rs = c_conectar.consulta(st, sql);
            if (rs.next()) {
                fecha = rs.getString("fecha");
                id_tipo_movimiento = rs.getInt("id_tipo_movimiento");
                cant_ingreso = rs.getInt("cant_ingreso");
                cant_salida = rs.getInt("cant_salida");
                costo_ingreso =rs.getDouble("costo_ingreso");
                costo_salida =rs.getDouble("costo_salida");
                id_tido =rs.getInt("id_tido");
                serie =rs.getString("serie_documento");
                numero =rs.getInt("numero_documento");
                id_usuario =rs.getInt("id_usuarios");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return existe;
    }

    public int obtener_suma_kardex() {
        int cantidad_kardex = 0;
        try {
            Statement st = c_conectar.conexion();
            String sql = "select (sum(kp.cant_ingreso) - sum(kp.cant_salida)) as stock "
                    + "from kardex_productos as kp "
                    + "where kp.id_producto = '" + id_producto + "' and kp.id_almacen = '" + this.id_almacen + "'";
            ResultSet rs = c_conectar.consulta(st, sql);
            if (rs.next()) {
                cantidad_kardex = rs.getInt("stock");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cantidad_kardex;
    }

    public void obtener_codigo() {
        try {
            Statement st = c_conectar.conexion();
            String query = "select ifnull(max(id_kardex) + 1, 1) as codigo "
                    + "from kardex_productos "
                    + "where id_producto = '" + id_producto + "' and id_almacen = '" + id_almacen + "'";
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                id_kardex = rs.getInt("codigo");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean regitrar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "insert into kardex_productos values ('" + id_kardex + "', '" + id_producto + "', '" + id_almacen + "', '" + fecha + "', '" + id_tipo_movimiento + "', "
                + "'" + cant_ingreso + "', '" + cant_salida + "', '" + costo_ingreso + "', '" + costo_salida + "', '" + id_tido + "', '" + serie + "', '" + numero + "', "
                + "'" + id_usuario + "', current_timestamp())";
        int resultado = c_conectar.actualiza(st, query);
        System.out.println(query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);

        return registrado;
    }
}
