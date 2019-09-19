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

public class cl_cliente {

    cl_conectar c_conectar = new cl_conectar();
    cl_varios c_varios = new cl_varios();

    private int codigo;
    private String documento;
    private String nombre;
    private String direccion;
    private String telefono;
    private String celular;
    private double venta;
    private double pago;
    private String ultima_venta;

    public cl_cliente() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public double getVenta() {
        return venta;
    }

    public void setVenta(double venta) {
        this.venta = venta;
    }

    public double getPago() {
        return pago;
    }

    public void setPago(double pago) {
        this.pago = pago;
    }

    public String getUltima_venta() {
        return ultima_venta;
    }

    public void setUltima_venta(String ultima_venta) {
        this.ultima_venta = ultima_venta;
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
            modelo.addColumn("Documento");
            modelo.addColumn("Nombre Cliente");
            modelo.addColumn("Ultima Visita");
            modelo.addColumn("T. Ventas S/");
            modelo.addColumn("T. Deuda S/");
            modelo.addColumn("Estado");

            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[7];
                fila[0] = rs.getInt("id_cliente");
                String sdocumento = rs.getString("documento");
                if (sdocumento.length() == 11) {
                    if (c_varios.esEntero(sdocumento)) {
                        if (!c_varios.validar_RUC(sdocumento)) {
                            System.out.println(" ruc no valido " + sdocumento);
                            sdocumento = "-";
                        }
                    }
                }
                fila[1] = sdocumento;
                fila[2] = rs.getString("nombre");
                String visita = rs.getString("ultima_venta");
                if (visita.equals("1000-01-01")) {
                    fila[3] = "--";
                } else {
                    fila[3] = rs.getString("ultima_venta");
                }
                double venta = rs.getDouble("venta");
                fila[4] = c_varios.formato_totales(rs.getDouble("venta"));
                double deuda = rs.getDouble("venta") - rs.getDouble("pago");
                fila[5] = c_varios.formato_totales(deuda);
                if (venta > 0 & deuda > 0) {
                    fila[6] = "DEUDOR";
                }
                if (venta > 0 & deuda == 0) {
                    fila[6] = "-";
                }
                if (venta <= 0) {
                    fila[6] = "INACTIVO";
                }

                modelo.addRow(fila);
            }
            c_conectar.cerrar(st);
            c_conectar.cerrar(rs);
            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(40);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(450);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(70);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(70);
            tabla.getColumnModel().getColumn(6).setPreferredWidth(70);
        } catch (SQLException e) {
            System.out.print(e);
        }
    }

    public boolean registrar() {
        boolean registrado = false;
        Statement st = c_conectar.conexion();
        String query = "insert into clientes "
                + "Values ('" + codigo + "', '" + documento + "', '" + nombre + "', '" + direccion + "', '" + telefono + "', '" + celular + "', '0', '0', '1000-01-01')";
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
        String query = "update clientes "
                + "set nombre = '" + nombre + "', direccion = '" + direccion + "', telefono = '" + telefono + "', celular = '" + celular + "' "
                + "where id_cliente = '" + codigo + "' ";
        System.out.println(query);
        int resultado = c_conectar.actualiza(st, query);
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
            String query = "select ifnull(max(id_cliente) + 1, 1) as codigo "
                    + "from clientes ";
            ResultSet rs = c_conectar.consulta(st, query);

            while (rs.next()) {
                codigo = rs.getInt("codigo");
                resultado = rs.getInt("codigo");
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }

        return resultado;
    }

    public boolean comprobar_cliente() {
        boolean existe = false;

        try {
            Statement st = c_conectar.conexion();
            String query = "select * "
                    + "from clientes "
                    + "where id_cliente = '" + codigo + "'";
            //System.out.println(query);
            ResultSet rs = c_conectar.consulta(st, query);

            while (rs.next()) {
                existe = true;
                documento = rs.getString("documento");
                nombre = rs.getString("nombre");
                direccion = rs.getString("direccion");
                telefono = rs.getString("telefono");
                celular = rs.getString("celular");
                ultima_venta = rs.getString("ultima_venta");
                venta = rs.getDouble("venta");
                pago = rs.getDouble("pago");
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }

        return existe;
    }

    public boolean comprobar_cliente_doc() {
        boolean existe = false;

        try {
            Statement st = c_conectar.conexion();
            String query = "select id_cliente "
                    + "from clientes "
                    + "where documento = '" + documento + "'";
            //System.out.println(query);
            ResultSet rs = c_conectar.consulta(st, query);

            while (rs.next()) {
                existe = true;
                codigo = rs.getInt("id_cliente");
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }

        return existe;
    }

}
