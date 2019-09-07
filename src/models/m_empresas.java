/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import clases.cl_conectar;
import clases_autocomplete.cla_empresa;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author luis
 */
public class m_empresas {

    cl_conectar c_conectar = new cl_conectar();

    public void cbx_empresas(JComboBox cbx) {
        try {
            cbx.removeAllItems();

            Statement st = c_conectar.conexion();

            String query = "select id_empresa, ruc, razon "
                    + "from empresa "
                    + "where estado = 'ACTIVO' "
                    + "order by razon asc";
            ResultSet rs = c_conectar.consulta(st, query);

            while (rs.next()) {
                cbx.addItem(new cla_empresa(rs.getInt("id_empresa"), rs.getString("ruc"), rs.getString("razon")));
            }

            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
