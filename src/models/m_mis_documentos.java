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
import comercial.frm_principal;

/**
 *
 * @author luis
 */
public class m_mis_documentos {
    
    cl_conectar c_conectar = new cl_conectar();
    
    int id_almacen = frm_principal.c_almacen.getId();
    
    public void cbx_mis_documentos(JComboBox cbx) {
        try {
            cbx.removeAllItems();
            
            Statement st = c_conectar.conexion();
            
            String query = "select da.id_tido, ds.descripcion "
                    + "from documentos_almacen as da "
                    + "inner join documentos_sunat as ds on ds.id_tido= da.id_tido "
                    + "where da.id_almacen = '" + id_almacen + "' "
                    + "group by da.id_tido "
                    + "order by ds.descripcion asc";
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
    
    public void cbx_documentos_venta(JComboBox cbx) {
        try {
            cbx.removeAllItems();
            
            Statement st = c_conectar.conexion();
            
            String query = "select da.id_tido, ds.descripcion "
                    + "from documentos_almacen as da "
                    + "inner join documentos_sunat as ds on ds.id_tido= da.id_tido "
                    + "where da.id_almacen = '" + id_almacen + "' and da.id_tido in (1,2, 6) "
                    + "group by da.id_tido "
                    + "order by ds.descripcion desc";
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
