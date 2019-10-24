/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import clases.cl_conectar;
import clases_autocomplete.cla_presentaciones;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author luis
 */
public class m_presentaciones {

    cl_conectar c_conectar = new cl_conectar();
    private int id_producto;

    public m_presentaciones() {
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public void cbx_empresas(JComboBox cbx) {
        try {
            cbx.removeAllItems();

            Statement st = c_conectar.conexion();

            String query = "select id_presentacion, nombre, factor "
                    + "from productos_presentaciones "
                    + "where id_producto = '" + id_producto + "' "
                    + "order by factor asc ";
            ResultSet rs = c_conectar.consulta(st, query);

            while (rs.next()) {
                String texto = rs.getString("nombre") + " x " + rs.getString("factor");
                cbx.addItem(new cla_presentaciones(rs.getInt("id_presentacion"), texto));
            }

            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
