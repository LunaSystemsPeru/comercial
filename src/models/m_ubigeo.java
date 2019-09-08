/*
 * Copyright (c) 2019, luis
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package models;

import clases.cl_conectar;
import clases_autocomplete.cla_ubigeo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author luis
 */
public class m_ubigeo {

    cl_conectar c_conectar = new cl_conectar();

    public m_ubigeo() {
    }
    
    public void cbx_departamentos(JComboBox cbx) {
        try {
            cbx.removeAllItems();

            Statement st = c_conectar.conexion();

            String query = "select * "
                    + "from ubigeo_inei "
                    + "where provincia = '00' and distrito = '00' "
                    + "order by nombre asc";
            ResultSet rs = c_conectar.consulta(st, query);

            while (rs.next()) {
                cbx.addItem(new cla_ubigeo(rs.getInt("id_ubigeo"), rs.getString("nombre")));
            }

            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void cbx_provincias(JComboBox cbx, String departamento) {
        try {
            cbx.removeAllItems();

            Statement st = c_conectar.conexion();

            String query = "select * "
                    + "from ubigeo_inei "
                    + "where departamento = '" + departamento + "' and distrito = '00' "
                    + "order by nombre asc";
            ResultSet rs = c_conectar.consulta(st, query);

            while (rs.next()) {
                cbx.addItem(new cla_ubigeo(rs.getInt("id_ubigeo"), rs.getString("nombre")));
            }

            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public void cbx_distritos(JComboBox cbx, String departamento, String provincia) {
        try {
            cbx.removeAllItems();

            Statement st = c_conectar.conexion();

            String query = "select * "
                    + "from ubigeo_inei "
                    + "where departamento = '" + departamento + "' and provincia = '"+provincia+"' "
                    + "order by nombre asc";
            ResultSet rs = c_conectar.consulta(st, query);

            while (rs.next()) {
                cbx.addItem(new cla_ubigeo(rs.getInt("id_ubigeo"), rs.getString("nombre")));
            }

            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
