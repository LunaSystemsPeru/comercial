package clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author CALIDAD
 */
public class cl_almacen {

    cl_conectar c_conectar = new cl_conectar();
    cl_varios c_varios = new cl_varios();

    private int id;
    private int empresa;
    private String nombre;
    private String direccion;
    private String ciudad;
    private String ticketera;
    private String ubigeo;
    private String telefono;
    private String estado;

    public cl_almacen() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpresa() {
        return empresa;
    }

    public void setEmpresa(int empresa) {
        this.empresa = empresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.trim().toUpperCase();
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion.trim().toUpperCase();
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad.trim().toUpperCase();
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTicketera() {
        return ticketera.trim().toUpperCase();
    }

    public void setTicketera(String ticketera) {
        this.ticketera = ticketera;
    }

    public String getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void mostrar(JTable tabla, String query) {
        try {
            DefaultTableModel modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };

            Statement st = c_conectar.conexion();
            ResultSet rs = c_conectar.consulta(st, query);

            modelo.addColumn("Id");
            modelo.addColumn("Nombre");
            modelo.addColumn("Direccion");
            modelo.addColumn("Empresa");
            modelo.addColumn("Ciudad");
            modelo.addColumn("Estado");

            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[6];
                fila[0] = rs.getInt("id_almacen");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("direccion");
                fila[3] = rs.getString("ruc") + " | " + rs.getString("razon");
                fila[4] = rs.getString("ciudad");
                if (rs.getString("estado").equals("1")) {
                    fila[5] = "ACTIVO";
                } else {
                    fila[5] = "-";
                }

                modelo.addRow(fila);
            }

            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);

            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(30);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(250);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(350);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(70);
        } catch (SQLException e) {
            System.out.print(e);
        }

    }

    public boolean registrar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "insert into almacen "
                + "Values ('" + id + "', '" + empresa + "', '" + nombre + "', '" + direccion + "', '" + ciudad + "', '" + ticketera + "', '" + ubigeo + "', '" + telefono + "', '1')";
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
        String query = "update almacen "
                + "set id_empresa = '" + empresa + "', nombre = '" + nombre + "', direccion = '" + direccion + "', ciudad = '" + ciudad + "', ticketera = '" + ticketera + "', ubigeo = '" + ubigeo + "', telefono = '" + telefono + "'  "
                + "where id_almacen = '" + id + "'";
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
            String query = "select ifnull(max(id_almacen) + 1, 1) as codigo "
                    + "from almacen ";
            ResultSet rs = c_conectar.consulta(st, query);

            while (rs.next()) {
                id = rs.getInt("codigo");
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }
    }

    public boolean validar_almacen() {
        boolean existe = false;
        try {
            Statement st = c_conectar.conexion();
            String query = "select * "
                    + "from almacen "
                    + "where id_almacen = '" + id + "' ";
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                existe = true;
                empresa = rs.getInt("id_empresa");
                nombre = rs.getString("nombre");
                direccion = rs.getString("direccion");
                ciudad = rs.getString("ciudad");
                ticketera = rs.getString("ticketera");
                ubigeo = rs.getString("ubigeo");
                telefono = rs.getString("telefono");
                estado = rs.getString("estado");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return existe;
    }

}
