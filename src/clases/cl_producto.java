/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import render_tablas.render_productos_todos;

/**
 *
 * @author CALIDAD
 */
public class cl_producto {

    cl_conectar c_conectar = new cl_conectar();
    cl_varios c_varios = new cl_varios();

    private int id;
    private String descripcion;
    private String marca;
    private String cod_barra;
    private double costo;
    private double precio;
    private double ctotal;
    private double csunat;
    private double comision;
    private int tipo_producto;
    private int icbper;
    private String estado;
    private int id_proveedor;
    private int id_clasificacion;

    public cl_producto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCod_barra() {
        return cod_barra;
    }

    public void setCod_barra(String cod_barra) {
        this.cod_barra = cod_barra;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getCtotal() {
        return ctotal;
    }

    public void setCtotal(double ctotal) {
        this.ctotal = ctotal;
    }

    public double getCsunat() {
        return csunat;
    }

    public void setCsunat(double csunat) {
        this.csunat = csunat;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    public int getTipo_producto() {
        return tipo_producto;
    }

    public void setTipo_producto(int tipo_producto) {
        this.tipo_producto = tipo_producto;
    }

    public int getIcbper() {
        return icbper;
    }

    public void setIcbper(int icbper) {
        this.icbper = icbper;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public int getId_clasificacion() {
        return id_clasificacion;
    }

    public void setId_clasificacion(int id_clasificacion) {
        this.id_clasificacion = id_clasificacion;
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
            tmodelo.addColumn("Id");
            tmodelo.addColumn("Descripcion");//descripcion modelo serie
            tmodelo.addColumn("Marca");
            tmodelo.addColumn("Precio");
            tmodelo.addColumn("Clasificacion");
            tmodelo.addColumn("Cant. Actual");
            tmodelo.addColumn("Comision");

            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[7];
                fila[0] = rs.getObject("id_producto");
                fila[1] = rs.getString("descripcion").trim();
                fila[2] = rs.getString("marca");
                fila[3] = c_varios.formato_numero(rs.getDouble("precio"));
                fila[4] = rs.getString("clase");
                fila[5] = rs.getInt("ctotal");
                fila[6] = c_varios.formato_numero(rs.getDouble("comision"));

                tmodelo.addRow(fila);
            }
            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
            tabla.setModel(tmodelo);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(10);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(450);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(50);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(20);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(30);
            tabla.getColumnModel().getColumn(6).setPreferredWidth(40);
            tabla.setDefaultRenderer(Object.class, new render_productos_todos());
            tabla.setRowSorter(sorter);

        } catch (SQLException e) {
            System.out.print(e);
        }
    }

    public void obtener_codigo() {
        try {
            Statement st = c_conectar.conexion();
            String query = "select ifnull(max(id_producto) + 1, 1) as codigo "
                    + "from productos ";
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                id = rs.getInt("codigo");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public boolean validar_id() {
        boolean existe = false;
        try {

            Statement st = c_conectar.conexion();
            String query = "select * "
                    + "from productos "
                    + "where id_producto = '" + id + "'";
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                descripcion = rs.getString("descripcion");
                marca = rs.getString("marca");
                cod_barra = rs.getString("cod_barra");
                costo = rs.getDouble("costo");
                precio = rs.getDouble("precio");
                ctotal = rs.getDouble("ctotal");
                csunat = rs.getDouble("csunat");
                comision = rs.getDouble("comision");
                tipo_producto = rs.getInt("tipo_producto");
                icbper = rs.getInt("afecto_icbper");
                estado = rs.getString("estado");
                id_proveedor = rs.getInt("id_proveedor");
                id_clasificacion = rs.getInt("id_clasificacion");
                existe = true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return existe;
    }

    public boolean registrar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "insert into productos "
                + "values ('" + id + "', '" + descripcion + "', '" + marca + "', '" + cod_barra + "', '"+costo+"', '" + precio + "', '0', '0', '" + comision + "', "
                + "'" + tipo_producto + "', '" + icbper + "', '1', '" + id_proveedor + "', '" + id_clasificacion + "')";
        System.out.println(query);
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        return registrado;
    }

    public boolean modificar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "update productos "
                + "set descripcion = '" + descripcion + "', marca = '" + marca + "', cod_barra = '" + cod_barra + "', precio = '" + precio + "', costo = '" + costo + "', comision = '" + comision + "', "
                + "clase = '" + id_clasificacion + "' "
                + "where id_producto = '" + id + "'";
        System.out.println(query);
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        return registrado;
    }
}
