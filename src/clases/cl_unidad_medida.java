/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author luis
 */
public class cl_unidad_medida {

    cl_conectar c_conectar = new cl_conectar();

    private int id;
    private String nombre;
    private String cod_sunat;
    private String abreviatura;

    public cl_unidad_medida() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCod_sunat() {
        return cod_sunat;
    }

    public void setCod_sunat(String cod_sunat) {
        this.cod_sunat = cod_sunat;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public boolean insertar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "insert into unidades_medida "
                + "Values ('" + id + "', '" + nombre + "', '" + cod_sunat + "', '" + abreviatura + "')";
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
    }

    public boolean modificar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "update unidades_medida "
                + "set nombre = '" + nombre + "', cod_sunat = '" + cod_sunat + "', abreviatura = '" + abreviatura + "' "
                + "where id_unidad = '" + id + "'";
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
            String query = "select ifnull(max(id_unidad) + 1, 1) as codigo "
                    + "from unidades_medida ";
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                id = rs.getInt("codigo");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     public boolean validar_id() {
        boolean existe = false;
        try {
            Statement st = c_conectar.conexion();
            String query = "select * "
                    + "from unidades_medida "
                    + "where id_unidad = '" + id + "' ";
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                existe = true;
                nombre = rs.getString("nombre");
                abreviatura = rs.getString("abreviatura");
                cod_sunat = rs.getString("cod_sunat");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return existe;
    }
}
