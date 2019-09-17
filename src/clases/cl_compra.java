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
import render_tablas.render_compras;

/**
 *
 * @author luis
 */
public class cl_compra {

    cl_conectar c_conectar = new cl_conectar();
    cl_varios c_varios = new cl_varios();

    private int id_compra;
    private String fecha;
    private int id_proveedor;
    private int id_tido;
    private String serie;
    private int numero;
    private double total;
    private double pagado;
    private int id_empresa;
    private int id_usuario;
    private int estado;
    private String glosa;

    public cl_compra() {
    }

    public cl_conectar getC_conectar() {
        return c_conectar;
    }

    public void setC_conectar(cl_conectar c_conectar) {
        this.c_conectar = c_conectar;
    }

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public int getId_tido() {
        return id_tido;
    }

    public void setId_tido(int id_tido) {
        this.id_tido = id_tido;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getPagado() {
        return pagado;
    }

    public void setPagado(double pagado) {
        this.pagado = pagado;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa.toUpperCase();
    }

    public void obtener_codigo() {
        try {
            Statement st = c_conectar.conexion();
            String query = "select ifnull(max(id_compra) + 1, 1) as codigo "
                    + "from compras";
            ResultSet rs = c_conectar.consulta(st, query);

            while (rs.next()) {
                id_compra = rs.getInt("codigo");
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }
    }

    public boolean obtener_datos() {
        boolean existe = false;

        try {
            Statement st = c_conectar.conexion();
            String query = "select * from "
                    + "compras "
                    + "where id_compra = '" + id_compra + "'";
            ResultSet rs = c_conectar.consulta(st, query);

            while (rs.next()) {
                existe = true;
                fecha = rs.getString("fecha");
                id_proveedor = rs.getInt("id_proveedor");
                id_tido = rs.getInt("id_tido");
                serie = rs.getString("serie");
                numero = rs.getInt("numero");
                total = rs.getDouble("total");
                pagado = rs.getDouble("pagado");
                estado = rs.getInt("estado");
                id_usuario = rs.getInt("id_usuarios");
                id_empresa = rs.getInt("id_empresa");
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }

        return existe;
    }

    public boolean insertar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "insert into compras "
                + "values ('" + id_compra + "', '" + fecha + "', '" + id_proveedor + "', '" + id_tido + "', '" + serie + "', '" + numero + "', '" + total + "', '0', '2', '" + id_usuario + "', '" + id_empresa + "')";
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
    }

    public boolean eliminar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "delete from compras "
                + "where id_compra = '" + id_compra + "'";
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
    }

    public boolean validar_documento() {
        boolean existe = false;
        try {
            Statement st = c_conectar.conexion();
            String query = "select * "
                    + "from compras "
                    + "where id_proveedor = '" + id_proveedor + "' and id_tido = '" + id_tido + "' and serie = '" + serie + "' and numero = '" + numero + "'";
            System.out.println(query);
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                existe = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return existe;
    }

    public void mostrar(JTable tabla, String query) {
        DefaultTableModel modelo;
        try {
            modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };
            //c_conectar.conectar();
            Statement st = c_conectar.conexion();
            ResultSet rs = c_conectar.consulta(st, query);

            //La cantidad de columnas que tiene la consulta
            //Establecer como cabezeras el nombre de las colimnas
            modelo.addColumn("Id");
            modelo.addColumn("Empresa");
            modelo.addColumn("Fecha");
            modelo.addColumn("Documento");
            modelo.addColumn("Proveedor");
            modelo.addColumn("T. Compra S/");
            modelo.addColumn("T. Deuda S/");
            modelo.addColumn("Estado");

            //Creando las filas para el JTable
            while (rs.next()) {
                String documento = rs.getString("doc_sunat") + " | " + rs.getString("serie") + " - " + rs.getString("numero");
                double dtotal = rs.getDouble("total");
                double dpagado = rs.getDouble("pagado");
                double ddeuda = dtotal - dpagado;
                int iestado = rs.getInt("estado");

                Object[] fila = new Object[8];
                fila[0] = rs.getInt("id_compra");
                fila[1] = rs.getString("ruc_empresa");
                fila[2] = rs.getString("fecha");
                fila[3] = documento;
                fila[4] = rs.getString("nro_documento") + " | " + rs.getString("razon_social");
                fila[5] = c_varios.formato_numero(dtotal);
                fila[6] = c_varios.formato_numero(ddeuda);
                if (iestado == 2) {
                    fila[7] = "POR PAGAR";
                }
                if (iestado == 1) {
                    fila[7] = "-";
                }

                modelo.addRow(fila);
            }
            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(30);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(450);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(70);
            tabla.getColumnModel().getColumn(6).setPreferredWidth(70);
            tabla.getColumnModel().getColumn(7).setPreferredWidth(70);
            tabla.setDefaultRenderer(Object.class, new render_compras());
        } catch (SQLException e) {
            System.out.print(e);
        }
    }

}
