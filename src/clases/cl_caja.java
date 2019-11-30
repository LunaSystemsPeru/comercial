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

/**
 *
 * @author luis
 */
public class cl_caja {

    cl_conectar c_conectar = new cl_conectar();
    cl_varios c_varios = new cl_varios();

    private int id_almacen;
    private String fecha;
    private double ing_venta;
    private double cobro_venta;
    private double o_ingresos;
    private double devolucion_ventas;
    private double gastos_varios;
    private double m_sistema;
    private double uso_cupon;
    private double m_apertura;
    private double m_cierre;
    private double venta_banco;

    public cl_caja() {
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

    public double getIng_venta() {
        return ing_venta;
    }

    public void setIng_venta(double ing_venta) {
        this.ing_venta = ing_venta;
    }

    public double getCobro_venta() {
        return cobro_venta;
    }

    public void setCobro_venta(double cobro_venta) {
        this.cobro_venta = cobro_venta;
    }

    public double getO_ingresos() {
        return o_ingresos;
    }

    public void setO_ingresos(double o_ingresos) {
        this.o_ingresos = o_ingresos;
    }

    public double getDevolucion_ventas() {
        return devolucion_ventas;
    }

    public void setDevolucion_ventas(double devolucion_ventas) {
        this.devolucion_ventas = devolucion_ventas;
    }

    public double getGastos_varios() {
        return gastos_varios;
    }

    public void setGastos_varios(double gastos_varios) {
        this.gastos_varios = gastos_varios;
    }

    public double getM_sistema() {
        return m_sistema;
    }

    public void setM_sistema(double m_sistema) {
        this.m_sistema = m_sistema;
    }

    public double getUso_cupon() {
        return uso_cupon;
    }

    public void setUso_cupon(double uso_cupon) {
        this.uso_cupon = uso_cupon;
    }

    public double getM_apertura() {
        return m_apertura;
    }

    public void setM_apertura(double m_apertura) {
        this.m_apertura = m_apertura;
    }

    public double getM_cierre() {
        return m_cierre;
    }

    public void setM_cierre(double m_cierre) {
        this.m_cierre = m_cierre;
    }

    public double getVenta_banco() {
        return venta_banco;
    }

    public void setVenta_banco(double venta_banco) {
        this.venta_banco = venta_banco;
    }

    public boolean insertar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "insert into cajas "
                + "Values ('" + id_almacen + "', '" + fecha + "', '0', '0', '0', '0', '0', '" + m_apertura + "', '0', '" + m_apertura + "', '0', '0')";
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
    }
    
    public boolean cerrar_caja() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "update cajas "
                + "set m_cierre = '"+m_cierre+"' "
                + "where id_almacen = '" + id_almacen + "' and fecha = '" + fecha + "'";
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
    }

    public boolean validar_caja() {
        boolean existe = false;

        try {
            Statement st = c_conectar.conexion();
            String query = "select * "
                    + "from cajas "
                    + "where id_almacen = '" + id_almacen + "' and fecha = '" + fecha + "'";
            ResultSet rs = c_conectar.consulta(st, query);

            while (rs.next()) {
                existe = true;
                ing_venta = rs.getDouble("ing_venta");
                cobro_venta = rs.getDouble("cobro_venta");
                o_ingresos = rs.getDouble("o_ingresos");
                devolucion_ventas = rs.getDouble("devolucion_ventas");
                gastos_varios = rs.getDouble("gastos_varios");
                m_sistema = rs.getDouble("m_sistema");
                uso_cupon = rs.getDouble("uso_cupon");
                m_apertura = rs.getDouble("m_apertura");
                m_cierre = rs.getDouble("m_cierre");
                venta_banco = rs.getDouble("m_banco");
            }

            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }

        return existe;
    }
}
