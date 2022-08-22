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
 * @author CALIDAD
 */
public class cl_empresa {

    cl_conectar c_conectar = new cl_conectar();

    private int id;
    private String ruc;
    private String razon;
    private String direccion;
    private String distrito;
    private String provincia;
    private String departamento;
    private String ubigeo;
    private String estado;
    private String condicion;

    public cl_empresa() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon.trim();
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion.trim();
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }

    public boolean validar_empresa() {
        boolean existe = false;
        try {
            Statement st = c_conectar.conexion();
            String query = "select * "
                    + "from empresa "
                    + "where id_empresa = '" + id + "'";
            
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                existe = true;
                ruc = rs.getString("ruc");
                razon = rs.getString("razon");
                direccion = rs.getString("direccion");
                estado = rs.getString("estado");
                condicion = rs.getString("condicion");
                distrito = rs.getString("distrito");
                provincia = rs.getString("provincia");
                departamento = rs.getString("departamento");
                ubigeo = rs.getString("ubigeo");
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return existe;
    }

    public boolean comprobar_empresa_ruc() {
        boolean existe = false;
        //existe = true;
        return existe;
    }

    public void mostrar(JTable tabla, String query) {
        try {
            DefaultTableModel modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };
            modelo.addColumn("Id");
            modelo.addColumn("RUC");
            modelo.addColumn("Razon Social");
            modelo.addColumn("Estado");

            Statement st = c_conectar.conexion();
            ResultSet rs = c_conectar.consulta(st, query);

            while (rs.next()) {
                Object[] fila = new Object[4];
                fila[0] = rs.getString("id_empresa");
                fila[1] = rs.getString("ruc");
                fila[2] = rs.getString("razon");
                fila[3] = rs.getString("estado");
                modelo.addRow(fila);
            }

            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);

            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(10);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(40);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(300);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(30);
        } catch (SQLException ex) {
            System.out.print(ex);
        }
    }

    public boolean registrar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "insert into empresa "
                + "Values ('" + id + "', '" + ruc + "', '" + razon + "', '" + direccion + "', '" + distrito + "','" + provincia + "','" + departamento + "','" + ubigeo + "','" + estado + "', '" + condicion + "')";
        int resultado = c_conectar.actualiza(st, query);
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
            String query = "select ifnull(max(id_empresa) + 1, 1) as codigo "
                    + "from empresa ";
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

    public boolean eliminar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "delete from empresa "
                + "where id_empresa = '" + id + "'";
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
    }

}
