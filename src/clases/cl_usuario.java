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
import javax.swing.table.TableRowSorter;

/**
 *
 * @author luis
 */
public class cl_usuario {

    cl_conectar c_conectar = new cl_conectar();
    cl_varios c_varios = new cl_varios();

    private int id_usuario;
    private String username;
    private String password;
    private String documento;
    private String nombre;
    private String email;
    private String celular;
    private int id_almacen;
    private int estado;

    public cl_usuario() {
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public int getId_almacen() {
        return id_almacen;
    }

    public void setId_almacen(int id_almacen) {
        this.id_almacen = id_almacen;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public boolean validar_usuario() {
        boolean existe = false;
        try {
            Statement st = c_conectar.conexion();
            String query = "select * "
                    + "from usuarios "
                    + "where id_usuarios = '" + id_usuario + "' ";
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                existe = true;
                username = rs.getString("username");
                id_almacen = rs.getInt("id_almacen");
                nombre = rs.getString("datos");
                email = rs.getString("email");
                celular = rs.getString("celular");
                documento = rs.getString("nro_documento");
                password = rs.getString("password");
                estado = rs.getInt("estado");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return existe;
    }

    public void autoconectar() {
        boolean existe = false;
        try {
            Statement st = c_conectar.conexion();
            String query = "select 1 ";
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                existe = true;
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "se perdio la conexion \n"+ex.getLocalizedMessage());
            c_conectar.conectar();
        }
        //return existe;
    }

    public boolean validar_usuario_username() {
        boolean existe = false;
        try {
            Statement st = c_conectar.conexion();
            String query = "select * "
                    + "from usuarios "
                    + "where username = '" + username + "' ";
            ResultSet rs = c_conectar.consulta(st, query);
            if (rs.next()) {
                existe = true;
                id_usuario = rs.getInt("id_usuarios");
                password = rs.getString("password");
                estado = rs.getInt("estado");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
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
            tmodelo.addColumn("Alias");
            tmodelo.addColumn("Datos");
            tmodelo.addColumn("Tienda");
            tmodelo.addColumn("Estado");

            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[5];
                fila[0] = rs.getString("id_usuarios");
                fila[1] = rs.getString("username");
                fila[2] = rs.getString("nro_documento") + " | " + rs.getString("datos");
                fila[3] = rs.getString("nalmacen");
                int iestado = rs.getInt("estado");
                String vestado = "ACTIVO";
                if (iestado == 1) {
                    vestado = "ACTIVO";
                }
                if (iestado == 2) {
                    vestado = "DE BAJA";
                }
                fila[4] = vestado;

                tmodelo.addRow(fila);
            }
            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
            tabla.setModel(tmodelo);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(50);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(550);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(100);
            c_varios.centrar_celda(tabla, 0);
            c_varios.centrar_celda(tabla, 1);
            c_varios.centrar_celda(tabla, 3);
            c_varios.centrar_celda(tabla, 4);
            tabla.setRowSorter(sorter);

        } catch (SQLException e) {
            System.out.print(e);
        }
    }

    public int obtener_codigo() {
        int resultado = 0;

        try {
            Statement st = c_conectar.conexion();
            String query = "select ifnull(max(id_usuarios) + 1, 1) as codigo "
                    + "from usuarios ";
            ResultSet rs = c_conectar.consulta(st, query);

            while (rs.next()) {
                id_usuario = rs.getInt("codigo");
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }

        return resultado;
    }

    public boolean registrar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "insert into usuarios "
                + "Values ('" + id_usuario + "', '" + username + "', '" + password + "', '" + documento + "', '" + nombre + "', '" + email + "', '" + celular + "', '" + id_almacen + "', '1')";
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
        String query = "update usuarios "
                + "set username = '" + username + "', password = '" + password + "', nro_documento = '" + documento + "', datos = '" + nombre + "', email = '" + email + "', celular = '" + celular + "', id_almacen = '" + id_almacen + "', estado = '" + estado + "' "
                + "where id_usuarios = '" + id_usuario + "'";
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
    }
}
