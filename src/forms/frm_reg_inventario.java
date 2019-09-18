/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import clases.cl_conectar;
import clases.cl_inventarios;
import clases.cl_producto;
import clases.cl_productos_almacen;
import clases.cl_productos_empresa;
import clases.cl_productos_inventarios;
import clases.cl_varios;
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
import vistas.frm_ver_inventarios;

/**
 *
 * @author luis
 */
public class frm_reg_inventario extends javax.swing.JInternalFrame {

    cl_conectar c_conectar = new cl_conectar();
    cl_varios c_varios = new cl_varios();

    cl_producto c_producto = new cl_producto();
    cl_productos_almacen c_producto_almacen = new cl_productos_almacen();

    cl_inventarios c_inventario = new cl_inventarios();
    cl_productos_inventarios c_detalle = new cl_productos_inventarios();

    int id_almacen = frm_principal.c_almacen.getId();
    int id_usuario = frm_principal.c_usuario.getId_usuario();
    int id_empresa = frm_principal.c_empresa.getId();

    DefaultTableModel detalle;
    TextAutoCompleter tac_productos = null;

    int fila_seleccionada = -1;

    /**
     * Creates new form frm_reg_inventario
     */
    public frm_reg_inventario() {
        initComponents();
        c_producto_almacen.setAlmacen(id_almacen);
        modelo_inventario();
        cargar_productos();

    }

    private void modelo_inventario() {
        //bloquear celdas para no permitir modificar datos
        detalle = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        //dar nombre a columnas - texto en cabezeras
        detalle.addColumn("Id");
        detalle.addColumn("Producto");
        detalle.addColumn("Marca");
        detalle.addColumn("Precio");
        detalle.addColumn("C. Sistema");
        detalle.addColumn("C. Fisico");
        detalle.addColumn("Diferencia");

        //agregar modelo a jtable
        t_inventario.setModel(detalle);
        //definir tamaño de celas (ancho)
        t_inventario.getColumnModel().getColumn(0).setPreferredWidth(20);
        t_inventario.getColumnModel().getColumn(1).setPreferredWidth(400);
        t_inventario.getColumnModel().getColumn(2).setPreferredWidth(100);
        t_inventario.getColumnModel().getColumn(3).setPreferredWidth(50);
        t_inventario.getColumnModel().getColumn(4).setPreferredWidth(50);
        t_inventario.getColumnModel().getColumn(5).setPreferredWidth(50);
        t_inventario.getColumnModel().getColumn(6).setPreferredWidth(70);
        //definir posicion de texto en celdas
        c_varios.centrar_celda(t_inventario, 0);
        c_varios.centrar_celda(t_inventario, 2);
        c_varios.derecha_celda(t_inventario, 3);
        c_varios.centrar_celda(t_inventario, 4);
        c_varios.centrar_celda(t_inventario, 5);
        c_varios.centrar_celda(t_inventario, 6);
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
            String sql = "select p.descripcion, pa.cactual, p.precio, p.id_producto, p.marca "
                    + "from productos_almacen as pa "
                    + "inner join productos as p on p.id_producto = pa.id_producto "
                    + "where pa.id_almacen = '" + id_almacen + "'";
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
        int contar_filas = t_inventario.getRowCount();
        if (contar_filas == 0) {
            ingresar = true;
        }

        if (contar_filas > 0) {
            for (int j = 0; j < contar_filas; j++) {
                int id_producto_fila = Integer.parseInt(t_inventario.getValueAt(j, 0).toString());
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
        btn_agregar = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        btn_guardar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButton1 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jButton2 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        lbl_ayuda = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_buscar_producto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_inventario = new javax.swing.JTable();

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

        jLabel11.setText("Cantidad fisica:");

        txt_cantidad_enviar.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_cantidad_enviar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_cantidad_enviarFocusGained(evt);
            }
        });
        txt_cantidad_enviar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_cantidad_enviarKeyPressed(evt);
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
                        .addGroup(jd_add_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jd_add_productoLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jd_add_productoLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(19, 19, 19)))
                        .addGroup(jd_add_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_cantidad_enviar)
                            .addComponent(txt_cantidad_actual, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 366, Short.MAX_VALUE)
                        .addGroup(jd_add_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jd_add_productoLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn_agregar, javax.swing.GroupLayout.Alignment.TRAILING))))
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
                    .addComponent(btn_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setTitle("Registrar Inventario");

        jToolBar1.setFloatable(false);
        jToolBar1.setOpaque(false);

        btn_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/accept.png"))); // NOI18N
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
        jToolBar1.add(jSeparator1);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        jButton1.setText("Eliminar");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton1);
        jToolBar1.add(jSeparator2);

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
        jToolBar1.add(jSeparator3);

        lbl_ayuda.setText("jLabel2");
        jToolBar1.add(lbl_ayuda);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar Producto"));

        jLabel1.setText("Buscar:");

        txt_buscar_producto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_buscar_productoFocusGained(evt);
            }
        });
        txt_buscar_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_buscar_productoActionPerformed(evt);
            }
        });
        txt_buscar_producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_buscar_productoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_buscar_producto)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_buscar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        t_inventario.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(t_inventario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 797, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        frm_ver_inventarios formulario = new frm_ver_inventarios();
        c_varios.llamar_ventana(formulario);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txt_cantidad_enviarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cantidad_enviarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String tcantidad = txt_cantidad_enviar.getText();
            if (c_varios.esEntero(tcantidad)) {
                int cactual = Integer.parseInt(txt_cantidad_actual.getText());
                int cenviar = Integer.parseInt(tcantidad);
                if (cenviar < 0) {
                    JOptionPane.showMessageDialog(null, "ERROR LA CANTIDAD ES MENOR A CERO");
                } else {
                    btn_agregar.setEnabled(true);
                    btn_agregar.requestFocus();
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
        double cenviar = Double.parseDouble(txt_cantidad_enviar.getText());
        Object fila[] = new Object[7];
        fila[0] = c_producto.getId();
        fila[1] = c_producto.getDescripcion();
        fila[2] = c_producto.getMarca();
        fila[3] = c_varios.formato_numero(c_producto.getPrecio());
        fila[4] = c_producto_almacen.getCtotal();
        double diferencia = cenviar - c_producto_almacen.getCtotal();
        fila[5] = cenviar;
        fila[6] = diferencia;

        detalle.addRow(fila);

        jd_add_producto.dispose();

        limpiar_buscar();
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void txt_buscar_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_buscar_productoActionPerformed

    }//GEN-LAST:event_txt_buscar_productoActionPerformed

    private void txt_buscar_productoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscar_productoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
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
                        txt_cantidad_actual.setText(c_producto_almacen.getCtotal() + "");
                        txt_cantidad_enviar.setText("");
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
                int contar_filas = t_inventario.getRowCount();
                if (contar_filas > 0) {
                    btn_guardar.setEnabled(true);
                    btn_guardar.requestFocus();
                }
            }
        }

        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txt_buscar_producto.setText("");
            txt_buscar_producto.requestFocus();
        }
    }//GEN-LAST:event_txt_buscar_productoKeyPressed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed

        int confirmado = JOptionPane.showConfirmDialog(null, "¿Esta Seguro de Guardar el Inventario?");
        btn_guardar.setEnabled(false);

        if (JOptionPane.OK_OPTION == confirmado) {
            c_inventario.setAnio(2019);
            c_inventario.setFecha(c_varios.getFechaActual());
            c_inventario.setId_almacen(id_almacen);
            c_inventario.setId_usuario(id_usuario);
            c_inventario.obtener_codigo();

            boolean registrar = c_inventario.registrar();

            if (registrar) {
                c_detalle.setAnio(c_inventario.getAnio());
                c_detalle.setId_inventario(c_inventario.getId_inventario());
                c_detalle.setId_almacen(id_almacen);
                int contar_filas = t_inventario.getRowCount();
                for (int i = 0; i < contar_filas; i++) {
                    c_detalle.setId_producto(Integer.parseInt(t_inventario.getValueAt(i, 0).toString()));
                    c_detalle.setCactual(Integer.parseInt(t_inventario.getValueAt(i, 4).toString()));
                    c_detalle.setCfisico(Integer.parseInt(t_inventario.getValueAt(i, 5).toString()));
                    c_detalle.registrar();
                }

                this.dispose();
                frm_ver_inventarios formulario = new frm_ver_inventarios();
                c_varios.llamar_ventana(formulario);
            }
        }
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void txt_buscar_productoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_buscar_productoFocusGained
        lbl_ayuda.setText("ESCAPE: LIMPIA TEXTO             ESCRIBA NOMBRE MARCA O MODELO DEL PRODUCTO Y PRESIONE ENTER");
    }//GEN-LAST:event_txt_buscar_productoFocusGained

    private void txt_cantidad_enviarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_cantidad_enviarFocusGained
        lbl_ayuda.setText("ESCRIBA CANTIDAD ENCONTRADA EN FISICO Y PRESIONE ENTER");
    }//GEN-LAST:event_txt_cantidad_enviarFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JDialog jd_add_producto;
    private javax.swing.JLabel lbl_ayuda;
    private javax.swing.JTable t_inventario;
    private javax.swing.JTextField txt_buscar_producto;
    private javax.swing.JTextField txt_cantidad_actual;
    private javax.swing.JTextField txt_cantidad_enviar;
    private javax.swing.JTextField txt_precio;
    private javax.swing.JTextField txt_producto;
    // End of variables declaration//GEN-END:variables
}
