/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import clases.cl_producto;
import clases.cl_productos_clasificacion;
import clases.cl_productos_presentacion;
import clases.cl_proveedor;
import clases.cl_unidad_medida;
import clases.cl_varios;
import clases_autocomplete.cla_producto_clasificacion;
import clases_autocomplete.cla_unidad_medida;
import java.awt.event.KeyEvent;
import javax.swing.table.DefaultTableModel;
import models.m_empresas;
import models.m_producto_clasificacion;
import models.m_unidades;

/**
 *
 * @author luis
 */
public class frm_reg_producto extends javax.swing.JDialog {

    //clases principales    
    public static cl_producto c_producto = new cl_producto();

    //clases secundarias
    cl_varios c_varios = new cl_varios();
    cl_productos_clasificacion c_clasificacion;
    cl_productos_presentacion c_presentacion;
    cl_unidad_medida c_unidad = new cl_unidad_medida();

    //autollenado clasificacon
    m_producto_clasificacion m_clasificacion = new m_producto_clasificacion();
    m_empresas m_empresa = new m_empresas();
    m_unidades m_unidad = new m_unidades();

    //variables publicas    
    static DefaultTableModel detalle;
    static int fila_seleccionada;
    public static boolean registrar;
    boolean modificar_presentacion = false;

    /**
     * Creates new form frm_reg_producto
     */
    public frm_reg_producto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        m_clasificacion.cbx_clasificaciones(cbx_clasificacion);
        m_unidad.llenar_combo(cbx_unidad_medida);

        modelo_presentaciones();

        if (!registrar) {
            this.setTitle("Modificar Producto");

            cl_proveedor c_proveedor = new cl_proveedor();
            c_presentacion = new cl_productos_presentacion();

            c_producto.validar_id();

            //cargar datos
            c_proveedor.setId_proveedor(c_producto.getId_proveedor());
            c_proveedor.cargar_datos();

            txt_descripcion.setText(c_producto.getDescripcion());
            txt_marca.setText(c_producto.getMarca());
            txt_cod_barra.setText(c_producto.getCod_barra());
            txt_precio_minimo.setText(c_varios.formato_numero(c_producto.getPrecio()));
            txt_proveedor.setText(c_proveedor.getRuc() + " | " + c_proveedor.getRazon_social());

            //obtener modelo unidad
            c_unidad.setId(c_producto.getId_unidad());
            c_unidad.validar_id();
            cbx_unidad_medida.setEnabled(true);
            cbx_unidad_medida.getModel().setSelectedItem(new cla_unidad_medida(c_unidad.getId(), c_unidad.getNombre()));
            
            //marcar si esta afecto
            if(c_producto.getIcbper() == 1) {
                cbx_afecto_icbper.setSelected(true);
            } else {
                cbx_afecto_icbper.setSelected(false);
            }

            //obtener modelo clasificacion
            c_clasificacion = new cl_productos_clasificacion();
            c_clasificacion.setId_clasificacion(c_producto.getId_clasificacion());
            c_clasificacion.obtener_datos();
            cbx_clasificacion.setEnabled(true);
            cbx_clasificacion.getModel().setSelectedItem(new cla_producto_clasificacion(c_clasificacion.getId_clasificacion(), c_clasificacion.getDescripcion()));

            c_presentacion.setId_producto(c_producto.getId());
            c_presentacion.mostrar(t_presentaciones, detalle);

            txt_nombre_presentacion.setEnabled(true);

            txt_descripcion.setEnabled(true);
            txt_cod_barra.setEnabled(true);
            txt_marca.setEnabled(true);
            btn_guardar.setEnabled(true);
        }
    }

    private void modelo_presentaciones() {
        //formato de tabla detalle de venta
        detalle = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        detalle.addColumn("Item");
        detalle.addColumn("Descripcion");
        detalle.addColumn("Factor");
        detalle.addColumn("Precio Unit.");
        t_presentaciones.setModel(detalle);
        t_presentaciones.getColumnModel().getColumn(0).setPreferredWidth(20);
        t_presentaciones.getColumnModel().getColumn(1).setPreferredWidth(300);
        t_presentaciones.getColumnModel().getColumn(2).setPreferredWidth(50);
        t_presentaciones.getColumnModel().getColumn(3).setPreferredWidth(80);
        c_varios.centrar_celda(t_presentaciones, 0);
        c_varios.centrar_celda(t_presentaciones, 2);
        c_varios.derecha_celda(t_presentaciones, 3);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        btn_guardar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_descripcion = new javax.swing.JTextField();
        txt_marca = new javax.swing.JTextField();
        cbx_clasificacion = new javax.swing.JComboBox<>();
        txt_proveedor = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_cod_barra = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbx_tipo_producto = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txt_nombre_presentacion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_factor = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_precio = new javax.swing.JTextField();
        btn_add_presentacion = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_presentaciones = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        txt_precio_minimo = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cbx_unidad_medida = new javax.swing.JComboBox<>();
        cbx_afecto_icbper = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Registrar Producto");
        setResizable(false);

        jToolBar1.setFloatable(false);

        btn_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/accept.png"))); // NOI18N
        btn_guardar.setText("Guardar");
        btn_guardar.setEnabled(false);
        btn_guardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_guardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_guardar);

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

        jLabel1.setText("Descripcion:");

        jLabel3.setText("Marca:");

        jLabel8.setText("Clasificacion:");

        jLabel9.setText("Proveedor:");

        txt_descripcion.setEnabled(false);
        txt_descripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_descripcionKeyPressed(evt);
            }
        });

        txt_marca.setEnabled(false);
        txt_marca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_marcaKeyPressed(evt);
            }
        });

        cbx_clasificacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbx_clasificacion.setEnabled(false);
        cbx_clasificacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbx_clasificacionItemStateChanged(evt);
            }
        });
        cbx_clasificacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_clasificacionKeyPressed(evt);
            }
        });

        txt_proveedor.setEnabled(false);

        jLabel7.setText("Cod. Barra");

        txt_cod_barra.setEnabled(false);
        txt_cod_barra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_cod_barraKeyPressed(evt);
            }
        });

        jLabel2.setText("Tipo Producto:");

        cbx_tipo_producto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BIEN" }));
        cbx_tipo_producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_tipo_productoKeyPressed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Presentaciones Productos"));

        jLabel5.setText("Nombre:");

        txt_nombre_presentacion.setEnabled(false);
        txt_nombre_presentacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nombre_presentacionKeyPressed(evt);
            }
        });

        jLabel6.setText("Factor:");

        txt_factor.setEnabled(false);
        txt_factor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_factorKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_factorKeyPressed(evt);
            }
        });

        jLabel10.setText("Precio Venta Unitario:");

        txt_precio.setEnabled(false);
        txt_precio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_precioKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_precioKeyPressed(evt);
            }
        });

        btn_add_presentacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        btn_add_presentacion.setText("Agregar");
        btn_add_presentacion.setEnabled(false);
        btn_add_presentacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_presentacionActionPerformed(evt);
            }
        });

        t_presentaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Item", "Descripcion", "Factor", "Precio Unitario"
            }
        ));
        t_presentaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_presentacionesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(t_presentaciones);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txt_factor, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_precio, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                .addGap(101, 101, 101)
                                .addComponent(btn_add_presentacion))
                            .addComponent(txt_nombre_presentacion)))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nombre_presentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_factor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_add_presentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel11.setText("Precio Unitario Minimo:");

        txt_precio_minimo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_precio_minimo.setEnabled(false);
        txt_precio_minimo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_precio_minimoKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_precio_minimoKeyPressed(evt);
            }
        });

        jLabel12.setText("Unidad Medida:");

        cbx_unidad_medida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbx_unidad_medida.setEnabled(false);
        cbx_unidad_medida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_unidad_medidaKeyPressed(evt);
            }
        });

        cbx_afecto_icbper.setText("Afecto ICBPER");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_descripcion)
                            .addComponent(txt_proveedor)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbx_tipo_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbx_afecto_icbper))
                            .addComponent(cbx_clasificacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_marca, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                                    .addComponent(cbx_unidad_medida, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_precio_minimo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_cod_barra, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbx_tipo_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_afecto_icbper, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_clasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_cod_barra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_marca, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txt_precio_minimo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(cbx_unidad_medida, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        c_producto.setDescripcion(txt_descripcion.getText());
        c_producto.setMarca(txt_marca.getText());
        c_producto.setCod_barra(txt_cod_barra.getText());
        c_producto.setComision(0);
        c_producto.setCosto(0);
        c_producto.setPrecio(Double.parseDouble(txt_precio_minimo.getText()));
        int tipo_producto = cbx_tipo_producto.getSelectedIndex();
        c_producto.setTipo_producto(tipo_producto);
        cla_producto_clasificacion cla_clasificacion = (cla_producto_clasificacion) cbx_clasificacion.getSelectedItem();
        c_producto.setId_clasificacion(cla_clasificacion.getId_clasificacion());
        c_producto.setIcbper(0);
        if (cbx_afecto_icbper.isSelected()) {
            c_producto.setIcbper(1);
        }
        cla_unidad_medida cla_unidad = (cla_unidad_medida) cbx_unidad_medida.getSelectedItem();
        c_producto.setId_unidad(cla_unidad.getId());

        c_presentacion = new cl_productos_presentacion();

        boolean realizado = false;

        if (registrar) {
            c_producto.obtener_codigo();
            realizado = c_producto.registrar();
        } else {
            realizado = c_producto.modificar();
        }

        c_presentacion.setId_producto(c_producto.getId());

        if (realizado) {
            if (!registrar) {
                c_presentacion.eliminar_todo();
            }
            int contar_presentaciones = t_presentaciones.getRowCount();
            for (int i = 0; i < contar_presentaciones; i++) {
                c_presentacion.setId_presentacion(i + 1);
                c_presentacion.setNombre(t_presentaciones.getValueAt(i, 1).toString());
                c_presentacion.setFactor(Double.parseDouble(t_presentaciones.getValueAt(i, 2).toString()));
                c_presentacion.setPrecio(Double.parseDouble(t_presentaciones.getValueAt(i, 3).toString()));
                c_presentacion.insertar();
            }
            this.dispose();
        }
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void txt_descripcionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_descripcionKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String texto = txt_descripcion.getText();
            if (texto.length() > 0) {
                txt_marca.setEnabled(true);
                txt_marca.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_descripcionKeyPressed

    private void txt_marcaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_marcaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String texto = txt_marca.getText();
            if (texto.length() > 0) {
                txt_cod_barra.setEnabled(true);
                txt_cod_barra.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_marcaKeyPressed

    private void cbx_clasificacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_clasificacionKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_descripcion.setEnabled(true);
            txt_descripcion.requestFocus();
        }
    }//GEN-LAST:event_cbx_clasificacionKeyPressed

    private void cbx_clasificacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbx_clasificacionItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_clasificacionItemStateChanged

    private void txt_cod_barraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cod_barraKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cbx_unidad_medida.setEnabled(true);
            cbx_unidad_medida.requestFocus();
        }
    }//GEN-LAST:event_txt_cod_barraKeyPressed

    private void cbx_tipo_productoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_tipo_productoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int tipo_producto = cbx_tipo_producto.getSelectedIndex();
            if (tipo_producto == 0) {
                cbx_clasificacion.setEnabled(true);
                cbx_clasificacion.requestFocus();
            } else {
                txt_descripcion.setEnabled(true);
                txt_descripcion.requestFocus();
            }
        }
    }//GEN-LAST:event_cbx_tipo_productoKeyPressed

    private void txt_nombre_presentacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombre_presentacionKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String texto = txt_nombre_presentacion.getText();
            if (texto.length() > 0) {
                txt_factor.setEnabled(true);
                txt_factor.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_nombre_presentacionKeyPressed

    private void txt_factorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_factorKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String texto = txt_factor.getText();
            if (texto.length() > 0) {
                txt_precio.setEnabled(true);
                txt_precio.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_factorKeyPressed

    private void txt_precio_minimoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_precio_minimoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_precio_minimo.isEnabled() && txt_precio_minimo.isEditable()) {
                String texto = txt_precio_minimo.getText();
                if (c_varios.esDecimal(texto)) {
                    txt_precio_minimo.setEditable(false);
                    cla_unidad_medida cla_unidad = (cla_unidad_medida) cbx_unidad_medida.getSelectedItem();

                    //crear objeto unidad 
                    Object fila[] = new Object[4];
                    fila[0] = 1;
                    fila[1] = cla_unidad.getNombre();
                    fila[2] = 1;
                    fila[3] = Double.parseDouble(texto);
                    detalle.addRow(fila);

                    //pasar a presentacion y activar boton grabar
                    btn_guardar.setEnabled(true);
                    txt_nombre_presentacion.setEnabled(true);
                    txt_nombre_presentacion.selectAll();
                    txt_nombre_presentacion.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_txt_precio_minimoKeyPressed

    private void txt_precio_minimoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_precio_minimoKeyTyped
        c_varios.solo_precio(evt);
    }//GEN-LAST:event_txt_precio_minimoKeyTyped

    private void cbx_unidad_medidaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_unidad_medidaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (registrar) {
                if (!txt_precio_minimo.isEditable() && txt_precio_minimo.isEnabled()) {
                    txt_nombre_presentacion.requestFocus();
                }
                if (!txt_precio_minimo.isEnabled()) {
                    txt_precio_minimo.setEnabled(true);
                    txt_precio_minimo.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_cbx_unidad_medidaKeyPressed

    private void txt_precioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_precioKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String texto = txt_precio.getText();
            if (c_varios.esDecimal(texto)) {
                btn_add_presentacion.setEnabled(true);
                btn_add_presentacion.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_precioKeyPressed

    private void txt_factorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_factorKeyTyped
        c_varios.solo_precio(evt);
        c_varios.limitar_caracteres(evt, txt_factor, 6);
    }//GEN-LAST:event_txt_factorKeyTyped

    private void txt_precioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_precioKeyTyped
        c_varios.solo_precio(evt);
        c_varios.limitar_caracteres(evt, txt_precio, 6);
    }//GEN-LAST:event_txt_precioKeyTyped

    private void btn_add_presentacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_presentacionActionPerformed
        if (!modificar_presentacion) {
            int contar_filas = t_presentaciones.getRowCount();

            //crear objeto unidad 
            Object fila[] = new Object[4];
            fila[0] = contar_filas + 1;
            fila[1] = txt_nombre_presentacion.getText().toUpperCase();
            fila[2] = Double.parseDouble(txt_factor.getText());
            fila[3] = Double.parseDouble(txt_precio.getText());
            detalle.addRow(fila);

        } else {
            int idpresentacion = Integer.parseInt(t_presentaciones.getValueAt(fila_seleccionada, 0).toString());
            if (idpresentacion == 1) {
                txt_precio_minimo.setText(txt_precio.getText());
            }
            t_presentaciones.setValueAt(txt_factor.getText(), fila_seleccionada, 2);
            t_presentaciones.setValueAt(txt_precio.getText(), fila_seleccionada, 3);
            t_presentaciones.setValueAt(txt_nombre_presentacion.getText(), fila_seleccionada, 1);
        }

        txt_nombre_presentacion.setText("");
        txt_factor.setText("");
        txt_precio.setText("");
        btn_add_presentacion.setEnabled(false);
        txt_factor.setEnabled(false);
        txt_precio.setEnabled(false);
        txt_nombre_presentacion.setEnabled(true);
        txt_nombre_presentacion.requestFocus();
        modificar_presentacion = false;
    }//GEN-LAST:event_btn_add_presentacionActionPerformed

    private void t_presentacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_presentacionesMouseClicked
        if (evt.getClickCount() == 2) {
            fila_seleccionada = t_presentaciones.getSelectedRow();
            int idpresentacion = Integer.parseInt(t_presentaciones.getValueAt(fila_seleccionada, 0).toString());

            modificar_presentacion = true;
            txt_nombre_presentacion.setText(t_presentaciones.getValueAt(fila_seleccionada, 1).toString());
            txt_factor.setText(t_presentaciones.getValueAt(fila_seleccionada, 2).toString());
            txt_precio.setText(t_presentaciones.getValueAt(fila_seleccionada, 3).toString());
            btn_add_presentacion.setEnabled(true);
            txt_factor.setEnabled(true);
            txt_precio.setEnabled(true);
            if (idpresentacion == 1) {
                txt_nombre_presentacion.setEnabled(false);
                txt_factor.setEnabled(false);
                txt_precio.requestFocus();
            } else {
                txt_nombre_presentacion.setEnabled(true);
                txt_nombre_presentacion.requestFocus();
            }
        }
    }//GEN-LAST:event_t_presentacionesMouseClicked

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
            java.util.logging.Logger.getLogger(frm_reg_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_reg_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_reg_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_reg_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frm_reg_producto dialog = new frm_reg_producto(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btn_add_presentacion;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JCheckBox cbx_afecto_icbper;
    private javax.swing.JComboBox<String> cbx_clasificacion;
    private javax.swing.JComboBox<String> cbx_tipo_producto;
    private javax.swing.JComboBox<String> cbx_unidad_medida;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable t_presentaciones;
    private javax.swing.JTextField txt_cod_barra;
    private javax.swing.JTextField txt_descripcion;
    private javax.swing.JTextField txt_factor;
    private javax.swing.JTextField txt_marca;
    private javax.swing.JTextField txt_nombre_presentacion;
    private javax.swing.JTextField txt_precio;
    private javax.swing.JTextField txt_precio_minimo;
    private javax.swing.JTextField txt_proveedor;
    // End of variables declaration//GEN-END:variables
}
