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

public class cl_resumen_diario {

    cl_conectar c_conectar = new cl_conectar();
    cl_varios c_varios = new cl_varios();

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
    
    public void verResumenVentas (JTable tabla, String query) {
          try {
            DefaultTableModel modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };

            Statement st = c_conectar.conexion();
            ResultSet rs = c_conectar.consulta(st, query);
           
            //nombre de las columnas de las tablas
            modelo.addColumn("Id");
            modelo.addColumn("Tienda");
            modelo.addColumn("Documento");
            modelo.addColumn("Fecha");
            modelo.addColumn("Cantidad Items");
            modelo.addColumn("Estado Documentos");
            modelo.addColumn("estadoid");
            modelo.addColumn("Correlativo");
            modelo.addColumn("Nro Ticket");
            modelo.addColumn("Observaciones");

            //Creando las filas para el JTable
            //nombre dela columna de la consulta
            int nrofilas = 1;
            while (rs.next()) {
                String sestado = "ACTIVO";
                if (rs.getInt("estado") == 2) {
                    sestado = "POR COBRAR";
                } 
                if (rs.getInt("estado") == 3) {
                    sestado = "ANULADO";
                } 
                
                Object[] fila = new Object[7];
                fila[0] = nrofilas;
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("descripcion");
                fila[3] = rs.getString("fecha");
                fila[4] = rs.getString("nroitems"); 
                fila[5] =sestado;   
                fila[6] =rs.getInt("estado");   
                
                nrofilas++;

                modelo.addRow(fila);
            }

            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);

            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(30);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(50);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(6).setPreferredWidth(50);
            tabla.getColumnModel().getColumn(7).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(8).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(9).setPreferredWidth(200);
            c_varios.centrar_celda(tabla,0);
            c_varios.centrar_celda(tabla,1);
            c_varios.centrar_celda(tabla,2);
            c_varios.centrar_celda(tabla,4);
            c_varios.centrar_celda(tabla,5);
            
            
        } catch (SQLException e) {
            System.out.print(e);
        }

    }

}
