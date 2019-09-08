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
public class cl_documento_sunat {
    cl_conectar c_conectar = new cl_conectar();
    
    private int id;
    private String descripcion;
    private int cod_sunat;
    private String abreviado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCod_sunat() {
        return cod_sunat;
    }

    public void setCod_sunat(int cod_sunat) {
        this.cod_sunat = cod_sunat;
    }

    public String getAbreviado() {
        return abreviado;
    }

    public void setAbreviado(String abreviado) {
        this.abreviado = abreviado;
    }
    
    public boolean validar_documento() {
        boolean existe = false;

        try {
            Statement st = c_conectar.conexion();
            String query = "select * "
                    + "from documentos_sunat "
                    + "where id_tido = '" + id + "' ";
            ResultSet rs = c_conectar.consulta(st, query);

            while (rs.next()) {
                existe = true;
                descripcion = rs.getString("descripcion");
                cod_sunat = rs.getInt("cod_sunat");
                abreviado = rs.getString("abreviado");
            }
            
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }

        return existe;
    }
}
