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

/**
 *
 * @author luis
 */
public class cl_movimiento_caja {

    cl_conectar c_conectar = new cl_conectar();
    cl_varios c_varios = new cl_varios();

    private int id_almacen;
    private int id_movimiento;
    private String fecha;
    private double ingresa;
    private double retirar;
    private String motivo;
    private int id_usuario;

    public cl_movimiento_caja() {
    }

    public int getId_almacen() {
        return id_almacen;
    }

    public void setId_almacen(int id_almacen) {
        this.id_almacen = id_almacen;
    }

    public int getId_movimiento() {
        return id_movimiento;
    }

    public void setId_movimiento(int id_movimiento) {
        this.id_movimiento = id_movimiento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getIngresa() {
        return ingresa;
    }

    public void setIngresa(double ingresa) {
        this.ingresa = ingresa;
    }

    public double getRetirar() {
        return retirar;
    }

    public void setRetirar(double retirar) {
        this.retirar = retirar;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo.toUpperCase().trim();
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public boolean insertar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "insert into cajas_movimientos "
                + "Values ('" + fecha + "', '" + id_almacen + "', '" + id_movimiento + "', '" + ingresa + "', '" + retirar + "', '" + motivo + "', '" + id_usuario + "')";
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
    }

    public void obtener_codigo() {
        try {
            Statement st = c_conectar.conexion();
            String query = "select ifnull(max(id_movimiento) + 1, 1) as codigo "
                    + "from cajas_movimientos ";
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                id_movimiento = rs.getInt("codigo");
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

            //Establecer como cabezeras el nombre de las columnas
            tmodelo.addColumn("Id");
            tmodelo.addColumn("Descripcion");//motivo del movimiento
            tmodelo.addColumn("Ingresa");
            tmodelo.addColumn("Retira");
            tmodelo.addColumn("Saldo");
            tmodelo.addColumn("Usuario");

            //Creando las filas para el JTable
            double suma_saldo = 0;
            while (rs.next()) {
                Object[] fila = new Object[6];
                fila[0] = rs.getObject("id_movimiento");
                fila[1] = rs.getString("motivo").trim();
                double dingresa = rs.getDouble("ingresa");
                fila[2] = c_varios.formato_numero(dingresa);
                double dretira = rs.getDouble("retira");
                fila[3] = c_varios.formato_numero(dretira);
                double dsaldo = dingresa - dretira;
                suma_saldo += dsaldo;
                fila[4] = c_varios.formato_numero(suma_saldo);
                fila[5] = rs.getString("username");

                tmodelo.addRow(fila);
            }
            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
            tabla.setModel(tmodelo);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(10);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(450);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(100);
            tabla.setRowSorter(sorter);
            c_varios.centrar_celda(tabla, 0);
            c_varios.derecha_celda(tabla, 2);
            c_varios.derecha_celda(tabla, 3);
            c_varios.derecha_celda(tabla, 4);
            c_varios.centrar_celda(tabla, 5);

        } catch (SQLException e) {
            System.out.print(e);
        }
    }

    public boolean eliminar_dia() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "delete from cajas_movimientos "
                + "where fecha = '" + this.fecha + "' and id_almacen = '" + this.id_almacen + "'";
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
    }

}
