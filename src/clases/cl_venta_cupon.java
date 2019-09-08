/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class cl_venta_cupon {

    cl_conectar c_conectar = new cl_conectar();

    private int id_venta;
    private int id_almacen;
    private String fecha;
    private double monto;
    private double usado;
    private String motivo;
    private int estado;
    private int id_usuario;

    public cl_venta_cupon() {
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

    public double getUsado() {
        return usado;
    }

    public void setUsado(double usado) {
        this.usado = usado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public boolean registrar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "insert into ventas_cupones "
                + "Values ('" + id_venta + "', '" + id_almacen + "', '" + fecha + "', '" + motivo + "', '" + monto + "', '0', '" + id_usuario + "', '1')";
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
    }

    public boolean actualizar_cupon() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "update ventas_cupones "
                + "set monto_usado = monto_usado + '" + usado + "' "
                + "where id_ventas = '" + id_venta + "'  and id_almacen = '" + id_almacen + "'";
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
    }

    public boolean validar_cupon() {
        boolean registrado = false;
        try {
            Statement st = c_conectar.conexion();
            String query = "select * from ventas_cupones "
                    + "where id_ventas = '" + id_venta + "' and id_almacen = '" + id_almacen + "'";
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                registrado = true;
                fecha = rs.getString("fecha");
                motivo = rs.getString("motivo");
                monto = rs.getDouble("monto_total");
                usado = rs.getDouble("monto_usado");
                id_usuario = rs.getInt("id_usuarios");
            }
            c_conectar.cerrar(st);
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return registrado;

    }

    public boolean validar_cliente(int id_cliente) {
        boolean registrado = false;
        try {
            Statement st = c_conectar.conexion();
            String query = "select vc.id_ventas, vc.id_almacen "
                    + "from ventas_cupones as vc "
                    + "inner join ventas as v on v.id_ventas = vc.id_ventas and v.id_almacen = vc.id_almacen "
                    + "where v.id_cliente = '" + id_cliente + "' and v.id_almacen= '" + this.id_almacen + "'";
            System.out.println(query);
            System.out.println(query);
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                registrado = true;
                id_venta = rs.getInt("id_ventas");
                id_almacen = rs.getInt("id_almacen");
            }
            c_conectar.cerrar(st);
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return registrado;

    }

}
