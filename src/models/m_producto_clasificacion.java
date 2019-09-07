/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import clases.cl_conectar;
import clases_autocomplete.cla_producto_clasificacion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class m_producto_clasificacion {
    
    cl_conectar c_conectar = new cl_conectar();
    
    public void cbx_clasificaciones (JComboBox cbx) {
        try {
            cbx.removeAllItems();
            
            Statement st = c_conectar.conexion();
            
            String query = "select * "
                    + "from productos_clasificacion "
                    + "order by nombre asc";
            ResultSet rs = c_conectar.consulta(st, query);
            
            while (rs.next()) {
                cbx.addItem(new cla_producto_clasificacion(rs.getInt("id_clasificacion"), rs.getString("nombre")));
            }
            
            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
}
