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
import render_tablas.render_traslados;

/**
 *
 * @author User
 */
public class cl_traslados {

    cl_conectar c_conectar = new cl_conectar();
    cl_varios c_varios = new cl_varios();

    private int id_traslado;
    private int id_tienda_envia;
    private int id_tienda_recibe;
    private int id_usuario_envia;
    private int id_usuario_recibe;
    private int estado;
    private String fecha_envio;
    private String fecha_recepcion;
    private String observaciones;

    public cl_traslados() {
    }

    public int getId_traslado() {
        return id_traslado;
    }

    public void setId_traslado(int id_traslado) {
        this.id_traslado = id_traslado;
    }

    public int getId_tienda_envia() {
        return id_tienda_envia;
    }

    public void setId_tienda_envia(int id_tienda_envia) {
        this.id_tienda_envia = id_tienda_envia;
    }

    public int getId_tienda_recibe() {
        return id_tienda_recibe;
    }

    public void setId_tienda_recibe(int id_tienda_recibe) {
        this.id_tienda_recibe = id_tienda_recibe;
    }

    public int getId_usuario_envia() {
        return id_usuario_envia;
    }

    public void setId_usuario_envia(int id_usuario_envia) {
        this.id_usuario_envia = id_usuario_envia;
    }

    public int getId_usuario_recibe() {
        return id_usuario_recibe;
    }

    public void setId_usuario_recibe(int id_usuario_recibe) {
        this.id_usuario_recibe = id_usuario_recibe;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getFecha_envio() {
        return fecha_envio;
    }

    public void setFecha_envio(String fecha_envio) {
        this.fecha_envio = fecha_envio;
    }

    public String getFecha_recepcion() {
        return fecha_recepcion;
    }

    public void setFecha_recepcion(String fecha_recepcion) {
        this.fecha_recepcion = fecha_recepcion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;

    }

    public boolean registrar_borrador() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "insert into traslados "
                + "Values ('" + id_traslado + "', '" + fecha_envio + "', '" + fecha_recepcion + "', '" + id_tienda_envia + "', '" + id_tienda_recibe + "', "
                + "'" + id_usuario_envia + "', '" + id_usuario_recibe + "', '" + observaciones + "', '" + estado + "')";
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
    }

    public boolean enviar_traslado() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "update traslados "
                + "set fecha = now(), estado = '" + estado + "' "
                + "where id_traslado = '" + id_traslado + "'";
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
    }

    public boolean recibir_traslado() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "update traslados "
                + "set fecha_recepcion = now(), u_recibe = '" + id_usuario_recibe + "', estado = '" + estado + "' "
                + "where id_traslado = '" + id_traslado + "'";
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
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
            modelo.addColumn("Fecha");
            modelo.addColumn("T. Origen");
            modelo.addColumn("t. Destino");
            modelo.addColumn("Usu. Envia");
            modelo.addColumn("Usu. Recibe");
            modelo.addColumn("F. recibido");
            modelo.addColumn("Estado");
            modelo.addColumn("id");

            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[9];
                String codigo = c_varios.ceros_izquieda_numero(3, rs.getInt("id_traslado"));
                fila[0] = codigo;
                fila[1] = rs.getString("fecha");
                fila[2] = rs.getString("tienda_origen");
                fila[3] = rs.getString("tienda_destino");
                int tusuenvia = rs.getInt("u_envia");
                int tusurecibe = rs.getInt("u_recibe");
                String strecibe;
                if (tusuenvia == tusurecibe) {
                    strecibe = "-";
                } else {
                    strecibe = rs.getString("usu_recibe");
                }
                fila[4] = rs.getString("usu_envia");
                fila[5] = strecibe;

                int testado = rs.getInt("estado");
                String sestado;
                String tfecha = c_varios.fecha_usuario(rs.getString("fecha_recepcion"));
                switch (testado) {
                    case 0:
                        sestado = "BORRADOR";
                        tfecha = "-";
                        break;
                    case 1:
                        sestado = "PENDIENTE";
                        tfecha = "-";
                        break;
                    case 2:
                        sestado = "RECIBIDO";
                        break;
                    case 3:
                        sestado = "ANULADO";
                        break;
                    default:
                        sestado = "CON OBSERVACIONES";
                        break;
                }
                fila[6] = tfecha;
                fila[7] = sestado;
                fila[8] = rs.getInt("id_traslado");

                modelo.addRow(fila);
            }
            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(60);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(70);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(70);
            tabla.getColumnModel().getColumn(6).setPreferredWidth(70);
            tabla.getColumnModel().getColumn(7).setPreferredWidth(70);
            tabla.setDefaultRenderer(Object.class, new render_traslados());
        } catch (SQLException e) {
            System.out.print(e);
        }
    }

    public int obtener_traslados_pendientes() {
        int resultado = 0;

        try {
            Statement st = c_conectar.conexion();
            String query = "select count(*) as encontrado "
                    + "from traslados "
                    + "where a_destino = '" + this.id_tienda_recibe + "' and estado = 1";
            ResultSet rs = c_conectar.consulta(st, query);

            if (rs.next()) {
                resultado = rs.getInt("encontrado");
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }

        return resultado;
    }

    public int obtener_codigo() {
        int resultado = 0;

        try {
            Statement st = c_conectar.conexion();
            String query = "select ifnull(max(id_traslado) + 1, 1) as codigo "
                    + "from traslados";
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

    public boolean validar_datos() {
        boolean existe = false;

        try {
            Statement st = c_conectar.conexion();
            String query = "select * from "
                    + "traslados "
                    + "where id_traslado = '" + id_traslado + "'";
            ResultSet rs = c_conectar.consulta(st, query);

            while (rs.next()) {
                existe = true;
                fecha_envio = rs.getString("fecha");
                fecha_recepcion = rs.getString("fecha_recepcion");
                id_tienda_envia = rs.getInt("a_origen");
                id_tienda_recibe = rs.getInt("a_destino");
                id_usuario_envia = rs.getInt("u_envia");
                id_usuario_recibe = rs.getInt("u_recibe");
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }

        return existe;
    }

    public boolean eliminar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "update traslados "
                + "set estado = '3' "
                + "where id_traslado = '" + id_traslado + "'";
        int resultado = c_conectar.actualiza(st, query);
        if (resultado > -1) {
            registrado = true;
        }
        c_conectar.cerrar(st);
        return registrado;
    }

}
