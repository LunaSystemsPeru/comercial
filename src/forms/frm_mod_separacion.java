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
package forms;

import clases.cl_cliente;
import clases.cl_conectar;
import clases.cl_producto;
import clases.cl_productos_almacen;
import clases.cl_productos_empresa;
import clases.cl_productos_ventas;
import clases.cl_varios;
import clases.cl_venta;
import clases_autocomplete.cla_producto;
import com.mxrck.autocompleter.AutoCompleterCallback;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import comercial.frm_principal;

/**
 *
 * @author luis
 */
public class frm_mod_separacion extends javax.swing.JDialog {

    cl_varios c_varios = new cl_varios();
    public static cl_venta c_venta = new cl_venta();
    cl_productos_ventas c_detalle = new cl_productos_ventas();
    cl_conectar c_conectar = new cl_conectar();

    cl_producto c_producto = new cl_producto();
    cl_productos_almacen c_producto_almacen = new cl_productos_almacen();
    cl_productos_empresa c_producto_empresa = new cl_productos_empresa();

    cl_cliente c_cliente = new cl_cliente();

    int fila_seleccionada;

    static DefaultTableModel detalle;

    TextAutoCompleter tac_productos = null;
    TextAutoCompleter tac_clientes = null;

    int id_empresa = frm_principal.c_empresa.getId();
    int id_almacen = frm_principal.c_almacen.getId();
    int id_usuario = frm_principal.c_usuario.getId_usuario();

    /**
     * Creates new form frm_mod_separacion
     */
    public frm_mod_separacion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        modelo_venta();
        c_producto_almacen.setAlmacen(id_almacen);
        c_producto_empresa.setId_empresa(id_empresa);
        cargar_productos();

        c_venta.setId_almacen(id_almacen);
        c_venta.validar_venta();
        c_detalle.setId_venta(c_venta.getId_venta());

        c_detalle.mostrar_modificar(detalle);
        t_detalle.setModel(detalle);

        c_cliente.setCodigo(c_venta.getId_cliente());
        c_cliente.comprobar_cliente();

        txt_doc_cliente.setText(c_cliente.getDocumento());
        txt_nom_cliente.setText(c_cliente.getNombre());
        txt_fecha_separacion.setText(c_varios.fecha_usuario(c_venta.getFecha()));
        txt_pagado.setText(c_varios.formato_totales(c_venta.getPagado()));
        lbl_total_venta.setText(c_varios.formato_totales(c_venta.getTotal()));
    }

    private void modelo_venta() {
        //formato de tabla detalle de venta
        detalle = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        detalle.addColumn("Id");
        detalle.addColumn("Descripcion");
        detalle.addColumn("Cant.");
        detalle.addColumn("Precio");
        detalle.addColumn("Parcial");
        t_detalle.setModel(detalle);
        t_detalle.getColumnModel().getColumn(0).setPreferredWidth(20);
        t_detalle.getColumnModel().getColumn(1).setPreferredWidth(400);
        t_detalle.getColumnModel().getColumn(2).setPreferredWidth(50);
        t_detalle.getColumnModel().getColumn(3).setPreferredWidth(50);
        t_detalle.getColumnModel().getColumn(4).setPreferredWidth(70);
        c_varios.centrar_celda(t_detalle, 0);
        c_varios.derecha_celda(t_detalle, 2);
        c_varios.derecha_celda(t_detalle, 3);
        c_varios.derecha_celda(t_detalle, 4);
    }

    private double calcular_total() {
        double total = 0;
        int contar_filas = t_detalle.getRowCount();
        for (int i = 0; i < contar_filas; i++) {
            total = total + Double.parseDouble(t_detalle.getValueAt(i, 4).toString());
        }
        lbl_total_venta.setText("S/ " + c_varios.formato_totales(total));
        return total;
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
                        c_producto_empresa.setId_producto(pcodigo);
                    } else {
                        System.out.println("El item es de un tipo desconocido");
                    }
                }
            });

            tac_productos.setMode(0);
            tac_productos.setCaseSensitive(false);
            Statement st = c_conectar.conexion();
            String sql = "select p.descripcion, pa.cactual, pe.precio, p.id_producto, p.marca "
                    + "from productos as p "
                    + "inner join productos_almacen as pa on pa.id_producto = p.id_producto "
                    + "inner join almacen as al on al.id_almacen = pa.id_almacen "
                    + "inner join productos_empresa as pe on pe.id_empresa = al.id_empresa and pe.id_producto = pa.id_producto "
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
        int contar_filas = t_detalle.getRowCount();
        if (contar_filas == 0) {
            ingresar = true;
        } else {
            for (int j = 0; j < contar_filas; j++) {
                int id_producto_fila = Integer.parseInt(t_detalle.getValueAt(j, 0).toString());
                if (producto == id_producto_fila) {
                    cuenta_iguales++;
                    JOptionPane.showMessageDialog(null, "El Producto a Ingresar ya existe en la lista");
                }
            }

            if (cuenta_iguales == 0) {
                ingresar = true;
            }
        }

        return ingresar;
    }

    private void limpiar_buscar() {
        txt_buscar_producto.setText("");
        txt_cantidad.setText("");
        txt_cactual.setText("");
        txt_precio.setText("");
        txt_cantidad.setEnabled(false);
        txt_cactual.setEnabled(false);
        txt_precio.setEnabled(false);
        txt_buscar_producto.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jd_modificar_item = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txt_jd_idproducto = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txt_jd_descripcion = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txt_jd_cantidad = new javax.swing.JTextField();
        txt_jd_precio = new javax.swing.JTextField();
        btn_jd_actualizar = new javax.swing.JButton();
        btn_jd_eliminar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_buscar_producto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_cantidad = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_cactual = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_precio = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txt_fecha_separacion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_doc_cliente = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_pagado = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        lbl_total_venta = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_nom_cliente = new javax.swing.JTextField();
        jToolBar1 = new javax.swing.JToolBar();
        btn_grabar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        lbl_ayuda = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_detalle = new javax.swing.JTable();

        jd_modificar_item.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jd_modificar_item.setTitle("Actualizar Item");

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Modificar ITem"));

        jLabel17.setText("Codigo:");

        txt_jd_idproducto.setEnabled(false);

        jLabel20.setText("Descripcion:");

        txt_jd_descripcion.setEnabled(false);

        jLabel21.setText("Cantidad:");

        jLabel22.setText("Precio:");

        txt_jd_cantidad.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_jd_cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_jd_cantidadKeyTyped(evt);
            }
        });

        txt_jd_precio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_jd_precio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_jd_precioKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_jd_idproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(txt_jd_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 164, Short.MAX_VALUE)
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_jd_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(170, 170, 170))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(txt_jd_descripcion)
                                .addContainerGap())))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_jd_idproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_jd_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_jd_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_jd_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btn_jd_actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/application_edit.png"))); // NOI18N
        btn_jd_actualizar.setText("Actualizar");
        btn_jd_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_jd_actualizarActionPerformed(evt);
            }
        });

        btn_jd_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cross.png"))); // NOI18N
        btn_jd_eliminar.setText("Eliminar Item");
        btn_jd_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_jd_eliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jd_modificar_itemLayout = new javax.swing.GroupLayout(jd_modificar_item.getContentPane());
        jd_modificar_item.getContentPane().setLayout(jd_modificar_itemLayout);
        jd_modificar_itemLayout.setHorizontalGroup(
            jd_modificar_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_modificar_itemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jd_modificar_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jd_modificar_itemLayout.createSequentialGroup()
                        .addComponent(btn_jd_eliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_jd_actualizar)))
                .addContainerGap())
        );
        jd_modificar_itemLayout.setVerticalGroup(
            jd_modificar_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_modificar_itemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_modificar_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_jd_actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_jd_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cambio de Productos de la Separacion");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar Productos"));

        jLabel1.setText("Busqueda:");

        txt_buscar_producto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_buscar_productoFocusGained(evt);
            }
        });
        txt_buscar_producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_buscar_productoKeyPressed(evt);
            }
        });

        jLabel2.setText("Cantidad:");

        txt_cantidad.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_cantidad.setEnabled(false);
        txt_cantidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_cantidadFocusGained(evt);
            }
        });
        txt_cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_cantidadKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_cantidadKeyPressed(evt);
            }
        });

        jLabel10.setText("Cant. Actual:");

        txt_cactual.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_cactual.setEnabled(false);

        jLabel3.setText("Precio:");

        txt_precio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_precio.setEnabled(false);
        txt_precio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_precioFocusGained(evt);
            }
        });
        txt_precio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_precioKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_precioKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_cactual, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_buscar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_buscar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cactual, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Generales"));

        jLabel4.setText("Fecha Separacion:");

        txt_fecha_separacion.setEditable(false);
        txt_fecha_separacion.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setText("Cliente:");

        txt_doc_cliente.setEditable(false);
        txt_doc_cliente.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setText("Pagado:");

        txt_pagado.setEditable(false);
        txt_pagado.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setText("Total:");

        lbl_total_venta.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lbl_total_venta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_total_venta.setText("S/ 00.00");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("SEPARACION");

        txt_nom_cliente.setEditable(false);
        txt_nom_cliente.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_fecha_separacion)
                    .addComponent(txt_doc_cliente)
                    .addComponent(txt_pagado)
                    .addComponent(lbl_total_venta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(0, 135, Short.MAX_VALUE))
                    .addComponent(txt_nom_cliente))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_fecha_separacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_doc_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nom_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_pagado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_total_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(143, Short.MAX_VALUE))
        );

        jToolBar1.setFloatable(false);

        btn_grabar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/accept.png"))); // NOI18N
        btn_grabar.setText("Grabar");
        btn_grabar.setEnabled(false);
        btn_grabar.setFocusable(false);
        btn_grabar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_grabar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_grabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_grabarActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_grabar);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cross.png"))); // NOI18N
        jButton2.setText("Salir");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);
        jToolBar1.add(jSeparator1);

        lbl_ayuda.setText("lb_ayuda");
        jToolBar1.add(lbl_ayuda);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Productos Escogidos")));

        t_detalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1520", "GUITARRA ACUSTICA VOZZEX", "1", "152.00", "152.00"}
            },
            new String [] {
                "ID.", "Descripcion", "Cant.", "Precio", "Parcial"
            }
        ));
        t_detalle.setShowVerticalLines(false);
        t_detalle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_detalleMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(t_detalle);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_cantidadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_cantidadFocusGained
        lbl_ayuda.setText("ESCRIBIR CANTIDAD        ENTER: PARA MODIFICAR PRECIO");
    }//GEN-LAST:event_txt_cantidadFocusGained

    private void txt_cantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cantidadKeyTyped
        c_varios.solo_numeros(evt);
    }//GEN-LAST:event_txt_cantidadKeyTyped

    private void txt_cantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cantidadKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String tcantidad = txt_cantidad.getText();
            if (c_varios.esEntero(tcantidad)) {
                txt_precio.setEnabled(true);
                txt_precio.selectAll();
                txt_precio.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_cantidadKeyPressed

    private void txt_precioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_precioFocusGained
        lbl_ayuda.setText("MODIFICAR CANTIDAD        ENTER: PARA GUARDAR EN DETALLE");
    }//GEN-LAST:event_txt_precioFocusGained

    private void txt_precioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_precioKeyTyped
        c_varios.solo_precio(evt);
    }//GEN-LAST:event_txt_precioKeyTyped

    private void txt_precioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_precioKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            boolean error = false;
            String tprecio = txt_precio.getText();
            double precio = 0;
            int cantidad = 0;
            if (!c_varios.esDecimal(tprecio)) {
                error = true;
            } else {
                precio = Double.parseDouble(tprecio);
            }

            //validar cantidad
            String tcantidad = txt_cantidad.getText();
            if (!c_varios.esEntero(tcantidad)) {
                error = true;
            } else {
                cantidad = Integer.parseInt(tcantidad);
                if (cantidad <= 0) {
                    JOptionPane.showMessageDialog(null, "NO PUEDE SER CERO (0)");
                    error = true;
                }
            }

            int cactual = Integer.parseInt(txt_cactual.getText());
            if (cactual <= 0) {
                JOptionPane.showMessageDialog(null, "ERROR NO HAY STOCK PARA ESTE PRODUCTO");
                error = true;
            }

            double parcial = precio * cantidad;

            //formar objeto y agregar para tabla
            if (!error) {
                Object fila[] = new Object[5];
                fila[0] = c_producto.getId();
                fila[1] = c_producto.getDescripcion() + " | " + c_producto.getMarca();
                fila[2] = cantidad;
                fila[3] = c_varios.formato_numero(precio);
                fila[4] = c_varios.formato_numero(parcial);

                detalle.addRow(fila);
                calcular_total();
                limpiar_buscar();
            } else {
                JOptionPane.showMessageDialog(null, "ERROR CON EL PRECIO O LA CANTIDAD");
            }
        }

        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            limpiar_buscar();
        }
    }//GEN-LAST:event_txt_precioKeyPressed

    private void t_detalleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_detalleMouseClicked
        int nro_filas = t_detalle.getRowCount();
        if (nro_filas > 0) {
            fila_seleccionada = t_detalle.getSelectedRow();
            String id_producto = t_detalle.getValueAt(fila_seleccionada, 0).toString();
            String descripcion = t_detalle.getValueAt(fila_seleccionada, 1).toString();
            int cantidad = Integer.parseInt(t_detalle.getValueAt(fila_seleccionada, 2).toString());
            double precio = Double.parseDouble(t_detalle.getValueAt(fila_seleccionada, 3).toString());

            jd_modificar_item.setModal(true);
            jd_modificar_item.setSize(881, 260);
            jd_modificar_item.setLocationRelativeTo(null);

            //cargar datos
            txt_jd_idproducto.setText(id_producto);
            txt_jd_descripcion.setText(descripcion);
            txt_jd_cantidad.setText(cantidad + "");
            txt_jd_precio.setText(c_varios.formato_numero(precio));

            jd_modificar_item.setVisible(true);

            txt_jd_cantidad.requestFocus();
        }
    }//GEN-LAST:event_t_detalleMouseClicked

    private void txt_jd_cantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_jd_cantidadKeyTyped
        c_varios.solo_numeros(evt);
    }//GEN-LAST:event_txt_jd_cantidadKeyTyped

    private void txt_jd_precioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_jd_precioKeyTyped
        c_varios.solo_precio(evt);
    }//GEN-LAST:event_txt_jd_precioKeyTyped

    private void btn_jd_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_jd_actualizarActionPerformed
        String tcantidad = txt_jd_cantidad.getText();
        String tprecio = txt_jd_precio.getText();

        int suma_error = 0;

        if (!c_varios.esEntero(tcantidad) && !tcantidad.isEmpty()) {
            suma_error++;
            JOptionPane.showMessageDialog(null, "ERROR AL INGRESAR CANTIDAD");
        }

        if (!c_varios.esDecimal(tprecio) && !tprecio.isEmpty()) {
            suma_error++;
            JOptionPane.showMessageDialog(null, "ERROR AL MODIFICAR PRECIO");
        }

        if (suma_error == 0) {
            int cantidad = Integer.parseInt(tcantidad);
            double precio = Double.parseDouble(tprecio);
            double parcial = cantidad * precio;
            t_detalle.setValueAt(cantidad, fila_seleccionada, 2);
            t_detalle.setValueAt(c_varios.formato_numero(precio), fila_seleccionada, 3);
            t_detalle.setValueAt(c_varios.formato_numero(parcial), fila_seleccionada, 4);

            calcular_total();
            jd_modificar_item.dispose();
            txt_buscar_producto.requestFocus();

        }
    }//GEN-LAST:event_btn_jd_actualizarActionPerformed

    private void btn_jd_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_jd_eliminarActionPerformed
        detalle.removeRow(fila_seleccionada);
        calcular_total();
        jd_modificar_item.dispose();
        txt_buscar_producto.requestFocus();
    }//GEN-LAST:event_btn_jd_eliminarActionPerformed

    private void txt_buscar_productoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_buscar_productoFocusGained
        lbl_ayuda.setText("ESCRIBIR PARA MOSTRAR PRODUCTOS        ENTER: PARA GRABAR CAMBIO");
    }//GEN-LAST:event_txt_buscar_productoFocusGained

    private void txt_buscar_productoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscar_productoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_buscar_producto.getText().length() > 25) {
                if (c_producto_almacen.validar_id()) {
                    //validar que no existe en la tabla
                    if (valida_tabla(c_producto.getId())) {
                        c_producto.validar_id();
                        c_producto_empresa.obtener_datos();
                        txt_precio.setText(c_varios.formato_numero(c_producto_empresa.getPrecio()));
                        txt_cactual.setText(c_producto_almacen.getCtotal() + "");
                        txt_cantidad.setText("1");
                        txt_cantidad.setEnabled(true);
                        txt_cantidad.requestFocus();
                    } else {
                        c_producto.setId(0);
                        c_producto_empresa.setId_empresa(0);
                        c_producto_almacen.setProducto(0);
                        limpiar_buscar();
                        JOptionPane.showMessageDialog(null, "ESTE PRODUCTO YA ESTA SELECCIONADO");
                    }
                } else {
                    c_producto.setId(0);
                    c_producto_empresa.setId_empresa(0);
                    c_producto_almacen.setProducto(0);
                    limpiar_buscar();
                    JOptionPane.showMessageDialog(null, "ERROR AL SELECCIONAR PRODUCTO");
                }
            }

            if (txt_buscar_producto.getText().length() == 0) {
                //si nro de filas es mayor a 0 entonces ir a datos generales
                int contar_filas = t_detalle.getRowCount();
                if (contar_filas > 0) {
                    btn_grabar.setEnabled(true);
                    btn_grabar.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_txt_buscar_productoKeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btn_grabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_grabarActionPerformed
        //desea grabar los cambios
        int confirmado = JOptionPane.showConfirmDialog(null, "¿Esta Seguro de Guardar el Traslado?");
        btn_grabar.setEnabled(false);

        if (JOptionPane.OK_OPTION == confirmado) {
            //borrar los productos anteirores
            c_detalle.eliminar();

            //recorrer tabla y agregar nuevos productos
            int contar_tabla = t_detalle.getRowCount();
            for (int i = 0; i < contar_tabla; i++) {
                c_detalle.setId_venta(c_venta.getId_venta());
                c_detalle.setId_producto(Integer.parseInt(t_detalle.getValueAt(i, 0).toString()));
                c_detalle.setCantidad(Integer.parseInt(t_detalle.getValueAt(i, 2).toString()));
                c_producto.setId(c_detalle.getId_producto());
                c_producto.validar_id();
                c_detalle.setCosto(c_producto.getCosto());
                c_detalle.setPrecio(Double.parseDouble(t_detalle.getValueAt(i, 3).toString()));

                c_detalle.registrar();
            }
        }
    }//GEN-LAST:event_btn_grabarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frm_mod_separacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_mod_separacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_mod_separacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_mod_separacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frm_mod_separacion dialog = new frm_mod_separacion(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_grabar;
    private javax.swing.JButton btn_jd_actualizar;
    private javax.swing.JButton btn_jd_eliminar;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JDialog jd_modificar_item;
    private javax.swing.JLabel lbl_ayuda;
    private javax.swing.JLabel lbl_total_venta;
    private javax.swing.JTable t_detalle;
    private javax.swing.JTextField txt_buscar_producto;
    private javax.swing.JTextField txt_cactual;
    private javax.swing.JTextField txt_cantidad;
    private javax.swing.JTextField txt_doc_cliente;
    private javax.swing.JTextField txt_fecha_separacion;
    private javax.swing.JTextField txt_jd_cantidad;
    private javax.swing.JTextField txt_jd_descripcion;
    private javax.swing.JTextField txt_jd_idproducto;
    private javax.swing.JTextField txt_jd_precio;
    private javax.swing.JTextField txt_nom_cliente;
    private javax.swing.JTextField txt_pagado;
    private javax.swing.JTextField txt_precio;
    // End of variables declaration//GEN-END:variables
}
