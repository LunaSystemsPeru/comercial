/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import static clases.cl_varios.mostrar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class cl_proveedor {

    cl_conectar c_conectar = new cl_conectar();
    cl_varios c_varios = new cl_varios();

    private int id_proveedor;
    private String ruc;
    private String razon_social;
    private String direccion;
    private String condicion;
    private String estado;
    private double tcompra;
    private double tpagado;

    public cl_proveedor() {
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getTcompra() {
        return tcompra;
    }

    public void setTcompra(double tcompra) {
        this.tcompra = tcompra;
    }

    public double getTpagado() {
        return tpagado;
    }

    public void setTpagado(double tpagado) {
        this.tpagado = tpagado;
    }

    public boolean buscar_ruc() {
        boolean existe = false;
        try {
            Statement st = c_conectar.conexion();
            String query = "select id_proveedor "
                    + "from proveedor "
                    + "where nro_documento = '" + ruc + "'";
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                existe = true;
                id_proveedor = rs.getInt("id_proveedor");
            }
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return existe;
    }

    public boolean registrar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "insert into proveedor "
                + "Values ('" + id_proveedor + "', '" + ruc + "', '" + razon_social + "', '" + direccion + "', '" + condicion + "', '" + estado + "', '0', '0')";
        System.out.println(query);
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
    }
    
     public boolean modificar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "update proveedor "
                + "set razon social = '" + razon_social + "', direccion ='" + direccion + "',condicion= '" + condicion + "',estado= '" + estado + "' "
                + "where id_proveedor= '"+id_proveedorfperez+"'";
        System.out.println(query);
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
    }
     
     public void obtener_codigo() {
        try {
            Statement st = c_conectar.conexion();
            String query = "select ifnull(max(id_proveedor) + 1, 1) as codigo "
                    + "from proveedor";
            ResultSet rs = c_conectar.consulta(st, query);

            while (rs.next()) {
                id_proveedor = rs.getInt("codigo");
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }
    }

    public boolean cargar_datos() {
        boolean existe = false;
        try {
            Statement st = c_conectar.conexion();
            String query = "select * "
                    + "from proveedor "
                    + "where id_proveedor = '" + id_proveedor + "'";
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                existe = true;
                ruc = rs.getString("nro_documento");
                razon_social = rs.getString("razon_social");
                direccion = rs.getString("direccion");
                condicion = rs.getString("condicion");
                estado = rs.getString("estado");
                tcompra = rs.getDouble("tcompra");
                tpagado = rs.getDouble("tpagado");
            }
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return existe;
    }

    public void mostrar(JTable tabla, String query) {
        try {
            DefaultTableModel tmodelo;
            tmodelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };
            TableRowSorter sorter = new TableRowSorter(tmodelo);
            Statement st = c_conectar.conexion();
            ResultSet rs = c_conectar.consulta(st, query);

            //Establecer como cabezeras el nombre de las colimnas
            tmodelo.addColumn("ID.");
            tmodelo.addColumn("Nro Doc.");
            tmodelo.addColumn("Nombre Proveedor");
            tmodelo.addColumn("T. Compra");

            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[4];
                fila[0] = rs.getString("id_proveedor");
                fila[1] = rs.getString("nro_documento");
                fila[2] = rs.getString("razon_social");
                fila[3] = rs.getString("tcompra");

                tmodelo.addRow(fila);
            }
            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
            tabla.setModel(tmodelo);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(50);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(550);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
            c_varios.derecha_celda(tabla, 3);
            tabla.setRowSorter(sorter);

        } catch (SQLException e) {
            System.out.print(e);
        }
    }

}
