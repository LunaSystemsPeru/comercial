/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import clases.cl_almacen;
import clases.cl_cliente;
import clases.cl_conectar;
import clases.cl_producto;
import clases.cl_productos_almacen;
import clases.cl_productos_traslado;
import clases.cl_productos_ventas;
import clases.cl_traslados;
import clases.cl_usuario;
import clases.cl_varios;
import clases.cl_venta;
import clases_autocomplete.cla_almacen;
import clases_autocomplete.cla_producto;
import com.mxrck.autocompleter.AutoCompleterCallback;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.m_almacen;
import comercial.frm_principal;
import vistas.frm_ver_traslados;

/**
 *
 * @author luis
 */
public class frm_reg_traslado extends javax.swing.JInternalFrame {

    cl_conectar c_conectar = new cl_conectar();
    cl_varios c_varios = new cl_varios();
    public static cl_traslados c_traslado = new cl_traslados();
    cl_producto c_producto = new cl_producto();
    cl_productos_almacen c_producto_almacen = new cl_productos_almacen();
    cl_productos_traslado c_detalle = new cl_productos_traslado();
    cl_venta c_venta;

    cl_usuario c_usuario = new cl_usuario();
    cl_almacen c_almacen = new cl_almacen();

    m_almacen m_almacen = new m_almacen();

    public static int tipo_operacion;
    boolean modificar = false;
    int id_almacen = frm_principal.c_almacen.getId();
    int id_usuario = frm_principal.c_usuario.getId_usuario();
    int id_empresa = frm_principal.c_almacen.getEmpresa();

    TextAutoCompleter tac_productos = null;
    DefaultTableModel detalle;

    int fila_seleccionada = -1;
    int tipo_traslado = 1;

    /**
     * Creates new form frm_reg_traslado
     */
    public frm_reg_traslado() {
        initComponents();
        if (tipo_operacion == 1) {
            modelo_traslado();
            c_producto_almacen.setAlmacen(id_almacen);

            String tienda = frm_principal.c_almacen.getNombre();
            txt_origen.setText(tienda);

            String usuario = frm_principal.c_usuario.getUsername();
            txt_envia.setText(usuario);

            String fecha = c_varios.fecha_usuario(c_varios.getFechaActual());
            txt_fecha.setText(fecha);

            m_almacen.cbx_almacenes(cbx_tiendas);
            cbx_tiendas.requestFocus();
            btn_cargar_productos.setEnabled(true);
        }
        if (tipo_operacion == 2) {
            c_traslado.validar_datos();

            c_almacen.setId(c_traslado.getId_tienda_envia());
            c_almacen.validar_almacen();

            c_usuario.setId_usuario(c_traslado.getId_usuario_envia());
            c_usuario.validar_usuario();

            txt_origen.setText(c_almacen.getNombre());

            txt_fecha.setEnabled(false);
            txt_fecha.setText(c_varios.fecha_usuario(c_traslado.getFecha_envio()));

            cbx_tiendas.setEnabled(false);
            cbx_tiendas.removeAllItems();
            cbx_tiendas.addItem(frm_principal.c_almacen.getNombre());

            txt_fecha_recibe.setText(c_varios.fecha_usuario(c_varios.getFechaActual()));
            txt_envia.setText(c_usuario.getUsername());
            txt_recibe.setText(frm_principal.c_usuario.getUsername());

            c_detalle.setId_traslado(c_traslado.getId_traslado());
            c_detalle.mostrar(t_traslado);

            btn_verificar.setEnabled(true);
        }

        if (tipo_operacion == 3) {
            c_traslado.validar_datos();
            c_producto_almacen.setAlmacen(id_almacen);

            c_almacen.setId(c_traslado.getId_tienda_envia());
            c_almacen.validar_almacen();

            c_usuario.setId_usuario(c_traslado.getId_usuario_envia());
            c_usuario.validar_usuario();

            txt_origen.setText(c_almacen.getNombre());

            txt_fecha.setEnabled(false);
            txt_fecha.setText(c_varios.fecha_usuario(c_traslado.getFecha_envio()));

            cbx_tiendas.setEnabled(false);
            cbx_tiendas.removeAllItems();
            cbx_tiendas.addItem(frm_principal.c_almacen.getNombre());

            txt_fecha_recibe.setText(c_varios.fecha_usuario(c_varios.getFechaActual()));
            txt_envia.setText(c_usuario.getUsername());
            txt_recibe.setText(frm_principal.c_usuario.getUsername());

            c_detalle.setId_traslado(c_traslado.getId_traslado());
            detalle = c_detalle.model_borrador();
            t_traslado.setModel(detalle);
            t_traslado.getColumnModel().getColumn(0).setPreferredWidth(20);
            t_traslado.getColumnModel().getColumn(1).setPreferredWidth(400);
            t_traslado.getColumnModel().getColumn(2).setPreferredWidth(150);
            t_traslado.getColumnModel().getColumn(3).setPreferredWidth(60);
            t_traslado.getColumnModel().getColumn(4).setPreferredWidth(60);
            t_traslado.getColumnModel().getColumn(5).setPreferredWidth(60);
            c_varios.centrar_celda(t_traslado, 0);
            c_varios.centrar_celda(t_traslado, 2);
            c_varios.derecha_celda(t_traslado, 3);
            c_varios.derecha_celda(t_traslado, 4);
            c_varios.derecha_celda(t_traslado, 5);
            //c_detalle.mostrar_borrador(t_traslado);

            cargar_productos();

            txt_buscar_producto.setEnabled(true);
            btn_enviar.setEnabled(false);
            btn_guardar.setEnabled(true);
            txt_buscar_producto.requestFocus();
        }
    }

    private void modelo_traslado() {
        //formato de tabla detalle de venta
        detalle = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        detalle.addColumn("Id");
        detalle.addColumn("Descripcion");
        detalle.addColumn("Marca");
        detalle.addColumn("C. Actual");
        detalle.addColumn("Cant.");
        detalle.addColumn("Precio");
        t_traslado.setModel(detalle);
        t_traslado.getColumnModel().getColumn(0).setPreferredWidth(20);
        t_traslado.getColumnModel().getColumn(1).setPreferredWidth(400);
        t_traslado.getColumnModel().getColumn(2).setPreferredWidth(150);
        t_traslado.getColumnModel().getColumn(3).setPreferredWidth(60);
        t_traslado.getColumnModel().getColumn(4).setPreferredWidth(60);
        t_traslado.getColumnModel().getColumn(5).setPreferredWidth(60);
        c_varios.centrar_celda(t_traslado, 0);
        c_varios.centrar_celda(t_traslado, 2);
        c_varios.derecha_celda(t_traslado, 3);
        c_varios.derecha_celda(t_traslado, 4);
        c_varios.derecha_celda(t_traslado, 5);
    }

    private void cargar_productos() {
        try {
            if (tac_productos != null) {
                tac_productos.removeAllItems();
            }
            tac_productos = new TextAutoCompleter(txt_buscar_producto, new AutoCompleterCallback() {
                @Override
                public void callback(Object selectedItem) {
                    Object itemSelected = selectedItem;
                    c_producto.setId(0);
                    c_producto_almacen.setProducto(0);
                    if (itemSelected instanceof cla_producto) {
                        int pcodigo = ((cla_producto) itemSelected).getId_producto();
                        String pnombre = ((cla_producto) itemSelected).getDescripcion();
                        System.out.println("producto seleccionado " + pnombre);
                        c_producto.setId(pcodigo);
                        c_producto_almacen.setProducto(pcodigo);
                    } else {
                        System.out.println("El item es de un tipo desconocido");
                    }
                }
            });

            tac_productos.setMode(0);
            tac_productos.setCaseSensitive(false);
            Statement st = c_conectar.conexion();
            String sql = "select p.descripcion, pa.cactual, pe.precio, p.id_producto, p.marca "
                    + "from productos_almacen as pa "
                    + "inner join almacen as al on al.id_almacen = pa.id_almacen "
                    + "inner join productos_empresa as pe on pe.id_empresa = al.id_empresa and pe.id_producto = pa.id_producto "
                    + "inner join productos as p on p.id_producto = pa.id_producto "
                    + "where pa.id_almacen = '" + id_almacen + "' and pa.cactual > 0";
            ResultSet rs = c_conectar.consulta(st, sql);
            while (rs.next()) {
                int id_producto = rs.getInt("id_producto");
                String descripcion = rs.getString("descripcion") + " | " + rs.getString("marca") 
                        + "    |    Cant: " + rs.getInt("cactual") + "    |    Precio: S/ " + c_varios.formato_numero(rs.getDouble("precio"));
                tac_productos.addItem(new cla_producto(id_producto, descripcion));
            }
            c_conectar.cerrar(rs);
            c_conectar.cerrar(st);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getLocalizedMessage());
            System.out.println(e.getLocalizedMessage());
        }
    }

    private boolean valida_tabla(int producto) {
        //estado de ingreso
        boolean ingresar = false;
        int cuenta_iguales = 0;

        //verificar fila no se repite
        int contar_filas = t_traslado.getRowCount();
        if (contar_filas == 0) {
            ingresar = true;
        }

        if (contar_filas > 0) {
            for (int j = 0; j < contar_filas; j++) {
                int id_producto_fila = Integer.parseInt(t_traslado.getValueAt(j, 0).toString());
                if (producto == id_producto_fila) {
                    ingresar = false;
                    cuenta_iguales++;
                    JOptionPane.showMessageDialog(null, "El Producto a Ingresar ya existe en la lista");
                } else {
                    ingresar = true;
                }
            }
        }

        if (cuenta_iguales == 0) {
            ingresar = true;
        }
        return ingresar;
    }

    private void limpiar_buscar() {
        txt_buscar_producto.setText("");
        txt_cantidad_actual.setText("");
        txt_cantidad_enviar.setText("");
        txt_precio.setText("");
        txt_cantidad_enviar.setEnabled(false);
        txt_cantidad_enviar.setEnabled(false);
        txt_precio.setEnabled(false);
        btn_agregar.setEnabled(false);
        txt_buscar_producto.requestFocus();
    }

    private void llenar() {
        c_traslado.setId_traslado(c_traslado.obtener_codigo());
        c_traslado.setEstado(1);
        c_traslado.setId_usuario_envia(id_usuario);
        c_traslado.setFecha_recepcion("1000-01-01");
        c_traslado.setId_tienda_envia(id_almacen);
        c_traslado.setId_usuario_recibe(id_usuario);
        c_traslado.setObservaciones("");
    }

    private void llenar_detalle() {
        int contar_filas = t_traslado.getRowCount();
        c_detalle.setId_traslado(c_traslado.getId_traslado());
        for (int i = 0; i < contar_filas; i++) {
            c_detalle.setId_producto(Integer.parseInt(t_traslado.getValueAt(i, 0).toString()));
            c_detalle.setCenviado(Integer.parseInt(t_traslado.getValueAt(i, 4).toString()));
            c_detalle.setCrecibido(0);
            c_detalle.registrar();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jd_add_producto = new javax.swing.JDialog();
        jLabel8 = new javax.swing.JLabel();
        txt_producto = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_cantidad_actual = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_precio = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_cantidad_enviar = new javax.swing.JTextField();
        btn_eliminar_producto = new javax.swing.JButton();
        btn_agregar = new javax.swing.JButton();
        jd_recibir_producto = new javax.swing.JDialog();
        jLabel12 = new javax.swing.JLabel();
        txt_producto_seleccionado = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_seleccionado_enviado = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txt_seleccionado_precio = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txt_seleccionado_actual = new javax.swing.JTextField();
        btn_recibir_item = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        txt_seleccionado_recibido = new javax.swing.JTextField();
        jd_cargar_venta = new javax.swing.JDialog();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txt_id_venta = new javax.swing.JTextField();
        txt_fecha_venta = new javax.swing.JTextField();
        txt_cliente_venta = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_origen = new javax.swing.JTextField();
        cbx_tiendas = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_envia = new javax.swing.JTextField();
        txt_recibe = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_fecha_recibe = new javax.swing.JTextField();
        txt_fecha = new javax.swing.JFormattedTextField();
        jToolBar1 = new javax.swing.JToolBar();
        btn_guardar = new javax.swing.JButton();
        btn_enviar = new javax.swing.JButton();
        btn_recibir = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btn_cargar_productos = new javax.swing.JButton();
        btn_verificar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btn_salir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txt_buscar_producto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_traslado = new javax.swing.JTable();

        jd_add_producto.setTitle("Agregar Item");

        jLabel8.setText("Producto");

        txt_producto.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_producto.setEnabled(false);

        jLabel9.setText("Cantidad actual:");

        txt_cantidad_actual.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_cantidad_actual.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_cantidad_actual.setEnabled(false);

        jLabel10.setText("Precio:");

        txt_precio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_precio.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_precio.setEnabled(false);

        jLabel11.setText("Cantidad enviar:");

        txt_cantidad_enviar.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_cantidad_enviar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_cantidad_enviarKeyPressed(evt);
            }
        });

        btn_eliminar_producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cross.png"))); // NOI18N
        btn_eliminar_producto.setText("Eliminar Producto");
        btn_eliminar_producto.setEnabled(false);
        btn_eliminar_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminar_productoActionPerformed(evt);
            }
        });

        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        btn_agregar.setText("Agregar");
        btn_agregar.setEnabled(false);
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jd_add_productoLayout = new javax.swing.GroupLayout(jd_add_producto.getContentPane());
        jd_add_producto.getContentPane().setLayout(jd_add_productoLayout);
        jd_add_productoLayout.setHorizontalGroup(
            jd_add_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_add_productoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jd_add_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jd_add_productoLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_producto))
                    .addGroup(jd_add_productoLayout.createSequentialGroup()
                        .addGroup(jd_add_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jd_add_productoLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_cantidad_enviar))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jd_add_productoLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_cantidad_actual, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 239, Short.MAX_VALUE)
                        .addGroup(jd_add_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jd_add_productoLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jd_add_productoLayout.createSequentialGroup()
                                .addComponent(btn_eliminar_producto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_agregar)))))
                .addContainerGap())
        );
        jd_add_productoLayout.setVerticalGroup(
            jd_add_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_add_productoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jd_add_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_add_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cantidad_actual, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_add_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cantidad_enviar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_eliminar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel12.setText("Producto");

        txt_producto_seleccionado.setEnabled(false);

        jLabel13.setText("C. Enviada:");

        txt_seleccionado_enviado.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_seleccionado_enviado.setEnabled(false);

        jLabel14.setText("Precio:");

        txt_seleccionado_precio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_seleccionado_precio.setEnabled(false);

        jLabel15.setText("C. Actual");

        txt_seleccionado_actual.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_seleccionado_actual.setEnabled(false);

        btn_recibir_item.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/accept.png"))); // NOI18N
        btn_recibir_item.setText("Recibir");
        btn_recibir_item.setEnabled(false);
        btn_recibir_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_recibir_itemActionPerformed(evt);
            }
        });

        jLabel16.setText("C. Recibida:");

        txt_seleccionado_recibido.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_seleccionado_recibido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_seleccionado_recibidoKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_seleccionado_recibidoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jd_recibir_productoLayout = new javax.swing.GroupLayout(jd_recibir_producto.getContentPane());
        jd_recibir_producto.getContentPane().setLayout(jd_recibir_productoLayout);
        jd_recibir_productoLayout.setHorizontalGroup(
            jd_recibir_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_recibir_productoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jd_recibir_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jd_recibir_productoLayout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_seleccionado_enviado, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_seleccionado_recibido, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 232, Short.MAX_VALUE)
                        .addComponent(btn_recibir_item, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jd_recibir_productoLayout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_producto_seleccionado))
                    .addGroup(jd_recibir_productoLayout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_seleccionado_actual, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_seleccionado_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jd_recibir_productoLayout.setVerticalGroup(
            jd_recibir_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_recibir_productoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jd_recibir_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_producto_seleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_recibir_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jd_recibir_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_seleccionado_actual, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jd_recibir_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_seleccionado_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_recibir_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jd_recibir_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_seleccionado_enviado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jd_recibir_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_seleccionado_recibido, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_recibir_item, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jd_cargar_venta.setTitle("Buscar Productos desde Venta");

        jLabel17.setText("ID Venta:");

        jLabel18.setText("Fecha:");

        jLabel19.setText("Cliente:");

        txt_fecha_venta.setEditable(false);
        txt_fecha_venta.setBackground(new java.awt.Color(255, 255, 255));

        txt_cliente_venta.setEditable(false);
        txt_cliente_venta.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/find.png"))); // NOI18N
        jButton1.setText("Validar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/accept.png"))); // NOI18N
        jButton2.setText("Cargar");
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jd_cargar_ventaLayout = new javax.swing.GroupLayout(jd_cargar_venta.getContentPane());
        jd_cargar_venta.getContentPane().setLayout(jd_cargar_ventaLayout);
        jd_cargar_ventaLayout.setHorizontalGroup(
            jd_cargar_ventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_cargar_ventaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jd_cargar_ventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_cargar_ventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_cliente_venta)
                    .addGroup(jd_cargar_ventaLayout.createSequentialGroup()
                        .addGroup(jd_cargar_ventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_fecha_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jd_cargar_ventaLayout.createSequentialGroup()
                                .addComponent(txt_id_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)))
                        .addGap(0, 63, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jd_cargar_ventaLayout.setVerticalGroup(
            jd_cargar_ventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_cargar_ventaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jd_cargar_ventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_id_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_cargar_ventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_fecha_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_cargar_ventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cliente_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setTitle("Registrar Traslados a otras Tiendas");
        setToolTipText("");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Generales"));

        jLabel1.setText("Tienda Origen:");

        jLabel2.setText("Tienda Destino:");

        txt_origen.setEnabled(false);

        cbx_tiendas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ALMACEN" }));
        cbx_tiendas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_tiendasKeyPressed(evt);
            }
        });

        jLabel3.setText("Usuario Envia:");

        jLabel4.setText("Usuario Recibe:");

        txt_envia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_envia.setEnabled(false);

        txt_recibe.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_recibe.setEnabled(false);

        jLabel5.setText("Fecha Envio:");

        jLabel6.setText("Fecha Recepcion:");

        txt_fecha_recibe.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_fecha_recibe.setEnabled(false);

        try {
            txt_fecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_fecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_fecha.setEnabled(false);
        txt_fecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_fechaKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbx_tiendas, 0, 180, Short.MAX_VALUE)
                    .addComponent(txt_origen)
                    .addComponent(txt_fecha)
                    .addComponent(txt_envia)
                    .addComponent(txt_recibe)
                    .addComponent(txt_fecha_recibe))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_origen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_tiendas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_envia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_recibe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_fecha_recibe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        jToolBar1.setFloatable(false);
        jToolBar1.setOpaque(false);

        btn_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/computer.png"))); // NOI18N
        btn_guardar.setText("Guardar");
        btn_guardar.setEnabled(false);
        btn_guardar.setFocusable(false);
        btn_guardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_guardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_guardar);

        btn_enviar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/accept.png"))); // NOI18N
        btn_enviar.setText("Enviar");
        btn_enviar.setEnabled(false);
        btn_enviar.setFocusable(false);
        btn_enviar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_enviar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_enviarActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_enviar);

        btn_recibir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/application_add.png"))); // NOI18N
        btn_recibir.setText("Recibir");
        btn_recibir.setEnabled(false);
        btn_recibir.setFocusable(false);
        btn_recibir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_recibir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_recibir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_recibirActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_recibir);
        jToolBar1.add(jSeparator1);

        btn_cargar_productos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/currency.png"))); // NOI18N
        btn_cargar_productos.setText("Desde Venta");
        btn_cargar_productos.setEnabled(false);
        btn_cargar_productos.setFocusable(false);
        btn_cargar_productos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_cargar_productos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_cargar_productos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cargar_productosActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_cargar_productos);

        btn_verificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/error.png"))); // NOI18N
        btn_verificar.setText("verificar Cantidad");
        btn_verificar.setEnabled(false);
        btn_verificar.setFocusable(false);
        btn_verificar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_verificar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_verificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_verificarActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_verificar);
        jToolBar1.add(jSeparator2);

        btn_salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cross.png"))); // NOI18N
        btn_salir.setText("Salir");
        btn_salir.setFocusable(false);
        btn_salir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_salir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_salir);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle de Traslado"));

        jLabel7.setText("Buscar Producto:");

        txt_buscar_producto.setEnabled(false);
        txt_buscar_producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_buscar_productoKeyPressed(evt);
            }
        });

        t_traslado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        t_traslado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_trasladoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(t_traslado);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_buscar_producto)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_buscar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 169, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        frm_ver_traslados formulario = new frm_ver_traslados();
        c_varios.llamar_ventana(formulario);
        this.dispose();
    }//GEN-LAST:event_btn_salirActionPerformed

    private void cbx_tiendasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_tiendasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cla_almacen cla_almacen = (cla_almacen) cbx_tiendas.getSelectedItem();
            int id_tienda = cla_almacen.getId_almacen();

            cl_almacen c_envia_tienda = new cl_almacen();
            c_envia_tienda.setId(id_tienda);
            c_envia_tienda.validar_almacen();
            //     if (c_envia_tienda.getEmpresa() == id_empresa) {
            c_traslado.setId_tienda_recibe(id_tienda);
            btn_cargar_productos.setEnabled(false);
            if (tipo_traslado == 3) {
                btn_guardar.setEnabled(true);
                btn_guardar.requestFocus();
            }
            if (tipo_traslado == 1) {
                txt_fecha.setEnabled(true);
                txt_fecha.requestFocus();
            }

            /*    } else {
                JOptionPane.showMessageDialog(null, "NO SE PUEDE SELECCIONAR A ESTE ALMACEN \nNo corresponde a tu empresa");
                cbx_tiendas.requestFocus();
                txt_fecha.setEnabled(false);
                txt_buscar_producto.setEnabled(false);
            }*/
        }
    }//GEN-LAST:event_cbx_tiendasKeyPressed

    private void txt_fechaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_fechaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String fecha = txt_fecha.getText();
            if (fecha.length() == 10) {
                fecha = c_varios.fecha_myql(fecha);
                c_traslado.setFecha_envio(fecha);

                //cargar todos los productos
                txt_buscar_producto.setEnabled(true);
                cargar_productos();
                txt_buscar_producto.setText("");
                txt_buscar_producto.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_fechaKeyPressed

    private void txt_buscar_productoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscar_productoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            modificar = false;
            if (txt_buscar_producto.getText().length() > 25) {
                if (c_producto_almacen.validar_id()) {
                    //validar que no existe en la tabla
                    if (valida_tabla(c_producto.getId())) {
                        c_producto.validar_id();
                        jd_add_producto.setModal(true);
                        jd_add_producto.setSize(717, 164);
                        jd_add_producto.setLocationRelativeTo(null);
                        txt_producto.setText(c_producto.getDescripcion() + " " + c_producto.getMarca());
                        txt_precio.setText(c_varios.formato_numero(c_producto.getPrecio()));
                        txt_cantidad_actual.setText(c_producto_almacen.getCtotal()+ "");
                        txt_cantidad_enviar.setText("1");
                        txt_cantidad_enviar.setEnabled(true);
                        txt_cantidad_enviar.requestFocus();
                        jd_add_producto.setVisible(true);
                    } else {
                        c_producto.setId(0);
                        c_producto_almacen.setProducto(0);
                        limpiar_buscar();
                        JOptionPane.showMessageDialog(null, "ESTE PRODUCTO YA ESTA SELECCIONADO");
                    }
                } else {
                    c_producto.setId(0);
                    c_producto_almacen.setProducto(0);
                    limpiar_buscar();
                    JOptionPane.showMessageDialog(null, "ERROR AL SELECCIONAR PRODUCTO");
                }
            }

            if (txt_buscar_producto.getText().length() == 0) {
                //si nro de filas es mayor a 0 entonces ir a datos generales
                int contar_filas = t_traslado.getRowCount();
                if (contar_filas > 0) {
                    btn_guardar.setEnabled(true);
                    btn_enviar.setEnabled(false);
                    btn_guardar.requestFocus();
                }
            }
        }

        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txt_buscar_producto.setText("");
            txt_buscar_producto.requestFocus();
        }
    }//GEN-LAST:event_txt_buscar_productoKeyPressed

    private void txt_cantidad_enviarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cantidad_enviarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String tcantidad = txt_cantidad_enviar.getText();
            if (c_varios.esEntero(tcantidad)) {
                int cactual = Integer.parseInt(txt_cantidad_actual.getText());
                if (cactual <= 0) {
                    JOptionPane.showMessageDialog(null, "ERROR NO HAY STOCK PARA ESTE PRODUCTO");
                } else {
                    int cenviar = Integer.parseInt(tcantidad);
                    if (cenviar > cactual) {
                        JOptionPane.showMessageDialog(null, "ERROR LA CANTIDAD ES MAYOR A LA CANTIDAD ACTUAL");
                    } else {
                        btn_agregar.setEnabled(true);
                        btn_agregar.requestFocus();
                    }
                }
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            jd_add_producto.dispose();
            txt_buscar_producto.setText("");
            txt_buscar_producto.requestFocus();
        }

    }//GEN-LAST:event_txt_cantidad_enviarKeyPressed

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        int cenviar = Integer.parseInt(txt_cantidad_enviar.getText());
        System.out.println(modificar);
        if (modificar == false) {
            Object fila[] = new Object[6];
            fila[0] = c_producto.getId();
            fila[1] = c_producto.getDescripcion();
            fila[2] = c_producto.getMarca();
            fila[3] = c_producto_almacen.getCtotal();
            fila[4] = cenviar;
            fila[5] = c_varios.formato_numero(c_producto.getPrecio());

            detalle.addRow(fila);
        } else {
            t_traslado.setValueAt(cenviar, fila_seleccionada, 4);
        }

        jd_add_producto.dispose();

        limpiar_buscar();
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void btn_enviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_enviarActionPerformed
        int confirmado = JOptionPane.showConfirmDialog(null, "¿Esta Seguro de Enviar el Traslado?");
        btn_enviar.setEnabled(false);

        if (JOptionPane.OK_OPTION == confirmado) {
            c_detalle.setId_traslado(c_traslado.getId_traslado());
            c_detalle.eliminar();

            c_traslado.setEstado(1);
            boolean registrar = c_traslado.enviar_traslado();

            if (registrar) {

                llenar_detalle();
                frm_ver_traslados formulario = new frm_ver_traslados();
                c_varios.llamar_ventana(formulario);
                this.dispose();
            }
        }
    }//GEN-LAST:event_btn_enviarActionPerformed

    private void t_trasladoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_trasladoMouseClicked
        int contar_filas = t_traslado.getRowCount();
        if (contar_filas > 0) {
            fila_seleccionada = t_traslado.getSelectedRow();
            if (tipo_operacion == 1 || tipo_operacion == 3) {
                modificar = true;
                int id_producto = Integer.parseInt(t_traslado.getValueAt(fila_seleccionada, 0).toString());
                int c_actual = Integer.parseInt(t_traslado.getValueAt(fila_seleccionada, 3).toString());
                int c_enviar = Integer.parseInt(t_traslado.getValueAt(fila_seleccionada, 4).toString());
                c_producto.setId(id_producto);
                c_producto.validar_id();
                btn_eliminar_producto.setEnabled(true);
                jd_add_producto.setModal(true);
                jd_add_producto.setSize(717, 164);
                jd_add_producto.setLocationRelativeTo(null);
                txt_producto.setText(c_producto.getDescripcion() + " " + c_producto.getMarca());
                txt_precio.setText(c_varios.formato_numero(c_producto.getPrecio()));
                txt_cantidad_actual.setText(c_actual + "");
                txt_cantidad_enviar.setText(c_enviar + "");
                txt_cantidad_enviar.setEnabled(true);
                txt_cantidad_enviar.requestFocus();
                jd_add_producto.setVisible(true);
            }
            if (tipo_operacion == 2) {
                if (id_almacen == c_traslado.getId_tienda_recibe()) {
                    cargar_item();
                } else {
                    JOptionPane.showMessageDialog(null, "Esta tienda no puede recibir el pedido");
                }
            }
        }
    }//GEN-LAST:event_t_trasladoMouseClicked

    private void cargar_item() {
        jd_recibir_producto.setModal(true);
        jd_recibir_producto.setSize(742, 174);
        jd_recibir_producto.setLocationRelativeTo(null);
        String descripcion = t_traslado.getValueAt(fila_seleccionada, 1) + " " + t_traslado.getValueAt(fila_seleccionada, 2);
        System.out.println("recibir item" + descripcion);
        String cenviado = t_traslado.getValueAt(fila_seleccionada, 4).toString();
        String cprecio = t_traslado.getValueAt(fila_seleccionada, 3).toString();
        int id_sproducto = Integer.parseInt(t_traslado.getValueAt(fila_seleccionada, 0).toString());
        c_producto_almacen.setProducto(id_sproducto);
        c_producto_almacen.setAlmacen(id_almacen);
        c_producto_almacen.validar_id();
        txt_producto_seleccionado.setText(descripcion);
        txt_seleccionado_enviado.setText(cenviado);
        txt_seleccionado_precio.setText(cprecio);
        txt_seleccionado_actual.setText(c_producto_almacen.getCtotal() + "");

        jd_recibir_producto.setVisible(true);
    }
    private void btn_recibir_itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_recibir_itemActionPerformed
        //modificar item
        String cantidad = txt_seleccionado_recibido.getText();
        t_traslado.setValueAt(cantidad, fila_seleccionada, 5);

        txt_seleccionado_recibido.setText("");

        int contar_filas = t_traslado.getRowCount();
        System.out.println("contar filas " + contar_filas);
        System.out.println("fila_seleccionada " + fila_seleccionada);

        fila_seleccionada++;

        if (fila_seleccionada == contar_filas) {
            jd_recibir_producto.dispose();
        }

        if (fila_seleccionada > contar_filas) {
            jd_recibir_producto.dispose();
            t_traslado.requestFocus();
        }

        if (fila_seleccionada < contar_filas) {
            btn_recibir_item.setEnabled(false);
            cargar_item();
        }

    }//GEN-LAST:event_btn_recibir_itemActionPerformed

    private void txt_seleccionado_recibidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_seleccionado_recibidoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int scenviado = Integer.parseInt(txt_seleccionado_enviado.getText());
            String texto = txt_seleccionado_recibido.getText();
            if (c_varios.esEntero(texto)) {
                int screcibido = Integer.parseInt(texto);
                if (screcibido > scenviado) {
                    JOptionPane.showMessageDialog(null, "LA CANTIDAD NO PUEDE SER MAYOR AL ENVIADO");
                    txt_seleccionado_recibido.selectAll();
                } else {
                    btn_recibir_item.setEnabled(true);
                    btn_recibir_item.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "ESTE NO ES UN NUMERO");
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            jd_recibir_producto.dispose();
            t_traslado.requestFocus();
        }
    }//GEN-LAST:event_txt_seleccionado_recibidoKeyPressed

    private void txt_seleccionado_recibidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_seleccionado_recibidoKeyTyped
        c_varios.solo_numeros(evt);
    }//GEN-LAST:event_txt_seleccionado_recibidoKeyTyped

    private void btn_verificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_verificarActionPerformed
        //validar q todos los rgistros sean mayor a cero o menor o igual a cantidad enviada
        int contar_filas = t_traslado.getRowCount();
        int ceros = 0;
        for (int i = 0; i < contar_filas; i++) {
            int c_recibido = Integer.parseInt(t_traslado.getValueAt(i, 5).toString());
            int c_enviado = Integer.parseInt(t_traslado.getValueAt(i, 4).toString());
            if (c_recibido == 0 & c_enviado > 0) {
                ceros++;
            }
        }

        if (ceros == 0) {
            btn_recibir.setEnabled(true);
        } else {
            btn_recibir.setEnabled(false);
            JOptionPane.showMessageDialog(null, "EXISTEN PRODUCTOS NO RECIBIDOS");
        }
    }//GEN-LAST:event_btn_verificarActionPerformed

    private void btn_recibirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_recibirActionPerformed
        c_traslado.setId_usuario_recibe(id_usuario);
        c_traslado.setEstado(2);
        c_traslado.recibir_traslado();

        int contar_filas = t_traslado.getRowCount();
        for (int i = 0; i < contar_filas; i++) {
            int c_recibido = Integer.parseInt(t_traslado.getValueAt(i, 5).toString());
            int id_producto = Integer.parseInt(t_traslado.getValueAt(i, 0).toString());
            //System.out.println("contando filas " + i + " producto " + id_producto + " recibido " + c_recibido);
            c_detalle.setId_producto(id_producto);
            c_detalle.setCrecibido(c_recibido);
            c_detalle.actualizar();
        }

        frm_ver_traslados formulario = new frm_ver_traslados();
        c_varios.llamar_ventana(formulario);
        this.dispose();
    }//GEN-LAST:event_btn_recibirActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        int confirmado = JOptionPane.showConfirmDialog(null, "¿Esta Seguro de Guardar el Traslado?");
        btn_enviar.setEnabled(false);

        if (JOptionPane.OK_OPTION == confirmado) {
            llenar();
            boolean registrar = c_traslado.registrar_borrador();

            if (registrar) {
                llenar_detalle();
                frm_ver_traslados formulario = new frm_ver_traslados();
                c_varios.llamar_ventana(formulario);
                this.dispose();
            }
        }
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void btn_cargar_productosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cargar_productosActionPerformed
        jd_cargar_venta.setModal(true);
        jd_cargar_venta.setSize(509, 179);
        jd_cargar_venta.setLocationRelativeTo(null);
        jd_cargar_venta.setVisible(true);
    }//GEN-LAST:event_btn_cargar_productosActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String id_ventas = txt_id_venta.getText();
        if (c_varios.esEntero(id_ventas)) {
            c_venta = new cl_venta();
            c_venta.setId_almacen(id_almacen);
            c_venta.setId_venta(Integer.parseInt(id_ventas));
            if (c_venta.validar_venta()) {
                cl_cliente c_cliente = new cl_cliente();
                c_cliente.setCodigo(c_venta.getId_cliente());
                c_cliente.comprobar_cliente();

                //txt_id_venta.setEditable(false);
                txt_fecha_venta.setText(c_varios.fecha_usuario(c_venta.getFecha()));
                txt_cliente_venta.setText(c_cliente.getDocumento() + " | " + c_cliente.getNombre());

                jButton2.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "Esta Venta no existe");
                txt_id_venta.selectAll();
                txt_id_venta.requestFocus();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //probando venta
        cl_productos_ventas c_producto_venta = new cl_productos_ventas();
        c_producto_venta.setId_venta(c_venta.getId_venta());
        c_producto_venta.mostrar_traslado(detalle);
        t_traslado.setModel(detalle);
        tipo_traslado = 3;
        jd_cargar_venta.dispose();
        cbx_tiendas.requestFocus();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btn_eliminar_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminar_productoActionPerformed
        detalle.removeRow(fila_seleccionada);
        jd_add_producto.dispose();
        limpiar_buscar();
    }//GEN-LAST:event_btn_eliminar_productoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_cargar_productos;
    private javax.swing.JButton btn_eliminar_producto;
    private javax.swing.JButton btn_enviar;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_recibir;
    private javax.swing.JButton btn_recibir_item;
    private javax.swing.JButton btn_salir;
    private javax.swing.JButton btn_verificar;
    private javax.swing.JComboBox<String> cbx_tiendas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JDialog jd_add_producto;
    private javax.swing.JDialog jd_cargar_venta;
    private javax.swing.JDialog jd_recibir_producto;
    private javax.swing.JTable t_traslado;
    private javax.swing.JTextField txt_buscar_producto;
    private javax.swing.JTextField txt_cantidad_actual;
    private javax.swing.JTextField txt_cantidad_enviar;
    private javax.swing.JTextField txt_cliente_venta;
    private javax.swing.JTextField txt_envia;
    private javax.swing.JFormattedTextField txt_fecha;
    private javax.swing.JTextField txt_fecha_recibe;
    private javax.swing.JTextField txt_fecha_venta;
    private javax.swing.JTextField txt_id_venta;
    private javax.swing.JTextField txt_origen;
    private javax.swing.JTextField txt_precio;
    private javax.swing.JTextField txt_producto;
    private javax.swing.JTextField txt_producto_seleccionado;
    private javax.swing.JTextField txt_recibe;
    private javax.swing.JTextField txt_seleccionado_actual;
    private javax.swing.JTextField txt_seleccionado_enviado;
    private javax.swing.JTextField txt_seleccionado_precio;
    private javax.swing.JTextField txt_seleccionado_recibido;
    // End of variables declaration//GEN-END:variables
}
