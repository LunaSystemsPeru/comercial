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
 * @author s1nn0mbr3
 */
public class cl_compra_pago {

    cl_conectar c_conectar = new cl_conectar();

    private int id_pago;
    private int id_compra;
    private String fecha;
    private double monto;

    public cl_compra_pago() {
    }

    public int getId_pago() {
        return id_pago;
    }

    public void setId_pago(int id_pago) {
        this.id_pago = id_pago;
    }

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public void mostrar(JTable tabla) {
        try {
            DefaultTableModel modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };

            Statement st = c_conectar.conexion();
            String query = "select id_pago, fecha, monto from compras_pagos "
                    + "where id_compra = '" + id_compra + "' "
                    + "order by fecha asc";
            ResultSet rs = c_conectar.consulta(st, query);

            modelo.addColumn("Id");
            modelo.addColumn("Fecha");
            modelo.addColumn("Monto");

            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[3];
                fila[0] = rs.getInt("id_pago");
                fila[1] = rs.getString("fecha");
                fila[2] = rs.getDouble("monto");

                modelo.addRow(fila);
            }

            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);

            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(50);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
        } catch (SQLException e) {
            System.out.print(e);
        }

    }

    public void obtener_codigo() {
        try {
            Statement st = c_conectar.conexion();
            String query = "select ifnull(max(id_pago) + 1, 1) as codigo "
                    + "from compras_pagos "
                    + "where id_compra = '" + id_compra + "'";
            ResultSet rs = c_conectar.consulta(st, query);

            while (rs.next()) {
                id_pago = rs.getInt("codigo");
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
        String query = "insert into compras_pagos "
                + "Values ('" + id_pago + "', '" + id_compra + "', '" + fecha + "', '" + monto + "')";
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
    }

    public boolean eliminar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "delete from compras_pagos "
                + "where id_compra = '" + id_compra + "' and id_pago = '" + id_pago + "'";
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
    }

}
