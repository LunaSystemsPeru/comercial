/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import clases.cl_conectar;
import clases_autocomplete.cla_almacen;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import sonomusic.frm_principal;

/**
 *
 * @author User
 */
public class m_almacen {
    
    cl_conectar c_conectar = new cl_conectar();
    
    int id_almacen = frm_principal.c_almacen.getId();
   
    public void cbx_almacenes (JComboBox cbx) {
        try {
            cbx.removeAllItems();
            
            Statement st = c_conectar.conexion();
            
            String query = "select * "
                    + "from almacen "
                    + "where id_almacen != '"+id_almacen+"' "
                    + "order by nombre asc";
            ResultSet rs = c_conectar.consulta(st, query);
            
            while (rs.next()) {
                cbx.addItem(new cla_almacen(rs.getInt("id_almacen"), rs.getString("nombre")));
            }
            
            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public void cbx_todos_almacenes (JComboBox cbx) {
        try {
            cbx.removeAllItems();
            
            Statement st = c_conectar.conexion();
            
            String query = "select * "
                    + "from almacen "
                    + "order by nombre asc";
            ResultSet rs = c_conectar.consulta(st, query);
            
            while (rs.next()) {
                cbx.addItem(new cla_almacen(rs.getInt("id_almacen"), rs.getString("nombre")));
            }
            
            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
