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

public class cl_resumen_diario {

    cl_conectar c_conectar = new cl_conectar();

    private int id_empresa;
    private int id_resumen;
    private String fecha;
    private int id_usuario;
    private String codigo;

    public cl_resumen_diario() {
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public int getId_resumen() {
        return id_resumen;
    }

    public void setId_resumen(int id_resumen) {
        this.id_resumen = id_resumen;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean registrar() {
        id_resumen = obtener_codigo();
        codigo = id_empresa + fecha + id_resumen;
        
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "insert into resumen_diario "
                + "Values ('" + id_empresa + "', '" + fecha + "', '" + id_resumen + "', '" + id_usuario + "', '" + codigo + "')";
        int resultado = c_conectar.actualiza(st, query);
        // System.out.println(query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
    }

    public int obtener_codigo() {
        int resultado = 0;

        try {
            Statement st = c_conectar.conexion();
            String query = "select ifnull(max(id_resumen) + 1, 1) as codigo "
                    + "from resumen_diario where id_empresa = '" + id_empresa + "' and fecha = '" + fecha + "'";
            ResultSet rs = c_conectar.consulta(st, query);

            while (rs.next()) {
                resultado = rs.getInt("codigo");
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }

        return resultado;
    }

}
