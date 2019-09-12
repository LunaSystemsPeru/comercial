/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import clases.cl_conectar;
import clases_autocomplete.cla_mis_documentos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author luis
 */
public class m_documentos_sunat {

    cl_conectar c_conectar = new cl_conectar();

    public void cbx_documentos(JComboBox cbx) {
        try {
            cbx.removeAllItems();

            Statement st = c_conectar.conexion();

            String query = "select id_tido, descripcion "
                    + "from documentos_sunat "
                    + "order by descripcion ";
            ResultSet rs = c_conectar.consulta(st, query);

            while (rs.next()) {
                cbx.addItem(new cla_mis_documentos(rs.getInt("id_tido"), rs.getString("descripcion")));
            }

            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void cbx_documentos_ingreso(JComboBox cbx) {
        try {
            cbx.removeAllItems();

            Statement st = c_conectar.conexion();

            String query = "select id_tido, descripcion "
                    + "from documentos_sunat  "
                    + "where id_tido in (1,2,10) "
                    + "order by descripcion ";
            ResultSet rs = c_conectar.consulta(st, query);

            while (rs.next()) {
                cbx.addItem(new cla_mis_documentos(rs.getInt("id_tido"), rs.getString("descripcion")));
            }

            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
