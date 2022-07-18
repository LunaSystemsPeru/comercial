/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import clases.cl_almacen;
import clases.cl_cliente;
import clases.cl_documento_sunat;
import clases.cl_ingresos;
import clases.cl_kardex;
import clases.cl_proveedor;
import clases.cl_traslados;
import clases.cl_usuario;
import clases.cl_varios;
import clases.cl_venta;
import clases_varios.leer_numeros;
import comercial.frm_principal;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author luis
 */
public class frm_ver_kardex_diario extends javax.swing.JDialog {

    cl_varios c_varios = new cl_varios();
    cl_kardex c_kardex = new cl_kardex();
    
    int kardex_seleccionado;
    int id_traslado = 0;
    int id_ventas = 0;

    int id_almacen = frm_principal.c_almacen.getId();
    String letras_numeros = "";
    /**
     * Creates new form frm_ver_kardex_diario
     */
    public frm_ver_kardex_diario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        c_kardex.setId_almacen(frm_principal.c_almacen.getId());
        c_kardex.setFecha(c_varios.getFechaActual());
        c_kardex.ver_kardex_diario(t_kardex_diario);
        
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jd_buscar = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        txt_fecha = new javax.swing.JFormattedTextField();
        jButton4 = new javax.swing.JButton();
        jd_detalle_traslado = new javax.swing.JDialog();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jToolBar2 = new javax.swing.JToolBar();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jd_detalle_ingreso = new javax.swing.JDialog();
        txt_compra_proveedor = new javax.swing.JTextField();
        jToolBar5 = new javax.swing.JToolBar();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txt_compra_documento = new javax.swing.JTextField();
        txt_compra_fecha = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txt_compra_usuario = new javax.swing.JTextField();
        jd_detalle_vena = new javax.swing.JDialog();
        jToolBar4 = new javax.swing.JToolBar();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txt_venta_documento = new javax.swing.JTextField();
        txt_venta_fecha = new javax.swing.JTextField();
        txt_venta_cliente = new javax.swing.JTextField();
        txt_venta_vendedor = new javax.swing.JTextField();
        txt_venta_total = new javax.swing.JTextField();
        jd_detalle_traslado1 = new javax.swing.JDialog();
        txt_tienda_origen = new javax.swing.JTextField();
        txt_tienda_destino = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_fecha_envio = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_fecha_recepcion = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_usuario_envia = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_id_traslado = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jToolBar3 = new javax.swing.JToolBar();
        btn_ver_pdf_traslado = new javax.swing.JButton();
        btn_salir_traslado = new javax.swing.JButton();
        txt_usuario_recibe = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_kardex_diario = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btn_detalle_kardex = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jLabel1.setText("Seleccionar Fecha:");

        try {
            txt_fecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_fecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/accept.png"))); // NOI18N
        jButton4.setText("Buscar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jd_buscarLayout = new javax.swing.GroupLayout(jd_buscar.getContentPane());
        jd_buscar.getContentPane().setLayout(jd_buscarLayout);
        jd_buscarLayout.setHorizontalGroup(
            jd_buscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_buscarLayout.createSequentialGroup()
                .addGroup(jd_buscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jd_buscarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jd_buscarLayout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)))
                .addContainerGap())
        );
        jd_buscarLayout.setVerticalGroup(
            jd_buscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_buscarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_buscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setText("Tienda Origen:");

        jLabel3.setText("Tienda Destino:");

        jLabel4.setText("Fecha Envio:");

        jLabel5.setText("Fecha Recepcion:");

        jLabel6.setText("Usuario Envia:");

        jLabel7.setText("Id. Traslado");

        jToolBar2.setFloatable(false);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/clipboard_text.png"))); // NOI18N
        jButton6.setText("Ver PDf");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar2.add(jButton6);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cross.png"))); // NOI18N
        jButton7.setText("Salir");
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar2.add(jButton7);

        javax.swing.GroupLayout jd_detalle_trasladoLayout = new javax.swing.GroupLayout(jd_detalle_traslado.getContentPane());
        jd_detalle_traslado.getContentPane().setLayout(jd_detalle_trasladoLayout);
        jd_detalle_trasladoLayout.setHorizontalGroup(
            jd_detalle_trasladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_detalle_trasladoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jd_detalle_trasladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jd_detalle_trasladoLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jd_detalle_trasladoLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jd_detalle_trasladoLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jd_detalle_trasladoLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jd_detalle_trasladoLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jd_detalle_trasladoLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jd_detalle_trasladoLayout.setVerticalGroup(
            jd_detalle_trasladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_detalle_trasladoLayout.createSequentialGroup()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_detalle_trasladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_detalle_trasladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_detalle_trasladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_detalle_trasladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_detalle_trasladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_detalle_trasladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jd_detalle_ingreso.setTitle("Ver Detalle de Compra");

        txt_compra_proveedor.setEditable(false);
        txt_compra_proveedor.setBackground(new java.awt.Color(255, 255, 255));

        jToolBar5.setFloatable(false);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/clipboard_text.png"))); // NOI18N
        jButton8.setText("Ver PDF");
        jButton8.setFocusable(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar5.add(jButton8);

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cross.png"))); // NOI18N
        jButton9.setText("Salir");
        jButton9.setFocusable(false);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jToolBar5.add(jButton9);

        jLabel19.setText("Documento:");

        jLabel20.setText("Fecha:");

        jLabel21.setText("Proveedor:");

        txt_compra_documento.setEditable(false);
        txt_compra_documento.setBackground(new java.awt.Color(255, 255, 255));

        txt_compra_fecha.setEditable(false);
        txt_compra_fecha.setBackground(new java.awt.Color(255, 255, 255));

        jLabel22.setText("Usuario:");

        txt_compra_usuario.setEditable(false);
        txt_compra_usuario.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jd_detalle_ingresoLayout = new javax.swing.GroupLayout(jd_detalle_ingreso.getContentPane());
        jd_detalle_ingreso.getContentPane().setLayout(jd_detalle_ingresoLayout);
        jd_detalle_ingresoLayout.setHorizontalGroup(
            jd_detalle_ingresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jd_detalle_ingresoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jd_detalle_ingresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jd_detalle_ingresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_compra_proveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                    .addGroup(jd_detalle_ingresoLayout.createSequentialGroup()
                        .addGroup(jd_detalle_ingresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_compra_documento, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                            .addComponent(txt_compra_fecha))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txt_compra_usuario))
                .addContainerGap())
        );
        jd_detalle_ingresoLayout.setVerticalGroup(
            jd_detalle_ingresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_detalle_ingresoLayout.createSequentialGroup()
                .addComponent(jToolBar5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_detalle_ingresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_compra_documento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_detalle_ingresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_compra_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_detalle_ingresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_compra_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_detalle_ingresoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_compra_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jd_detalle_vena.setTitle("Ver Detalle de Venta del Producto");

        jToolBar4.setFloatable(false);

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/clipboard_text.png"))); // NOI18N
        jButton10.setText("Ver PDF");
        jButton10.setFocusable(false);
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jToolBar4.add(jButton10);

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cross.png"))); // NOI18N
        jButton11.setText("Salir");
        jButton11.setFocusable(false);
        jButton11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jToolBar4.add(jButton11);

        jLabel14.setText("Documento:");

        jLabel15.setText("Fecha:");

        jLabel16.setText("Cliente:");

        jLabel17.setText("Vendedor:");

        jLabel18.setText("Total:");

        txt_venta_documento.setEditable(false);
        txt_venta_documento.setBackground(new java.awt.Color(255, 255, 255));

        txt_venta_fecha.setEditable(false);
        txt_venta_fecha.setBackground(new java.awt.Color(255, 255, 255));

        txt_venta_cliente.setEditable(false);
        txt_venta_cliente.setBackground(new java.awt.Color(255, 255, 255));

        txt_venta_vendedor.setEditable(false);
        txt_venta_vendedor.setBackground(new java.awt.Color(255, 255, 255));

        txt_venta_total.setEditable(false);
        txt_venta_total.setBackground(new java.awt.Color(255, 255, 255));
        txt_venta_total.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        javax.swing.GroupLayout jd_detalle_venaLayout = new javax.swing.GroupLayout(jd_detalle_vena.getContentPane());
        jd_detalle_vena.getContentPane().setLayout(jd_detalle_venaLayout);
        jd_detalle_venaLayout.setHorizontalGroup(
            jd_detalle_venaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jd_detalle_venaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jd_detalle_venaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jd_detalle_venaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_venta_cliente)
                    .addGroup(jd_detalle_venaLayout.createSequentialGroup()
                        .addGroup(jd_detalle_venaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_venta_documento, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                            .addComponent(txt_venta_total, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_venta_fecha))
                        .addGap(0, 234, Short.MAX_VALUE))
                    .addComponent(txt_venta_vendedor))
                .addContainerGap())
        );
        jd_detalle_venaLayout.setVerticalGroup(
            jd_detalle_venaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_detalle_venaLayout.createSequentialGroup()
                .addComponent(jToolBar4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_detalle_venaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_venta_documento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_detalle_venaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_venta_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_detalle_venaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_venta_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_detalle_venaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_venta_vendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_detalle_venaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_venta_total, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txt_tienda_origen.setEditable(false);
        txt_tienda_origen.setBackground(new java.awt.Color(255, 255, 255));

        txt_tienda_destino.setEditable(false);
        txt_tienda_destino.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setText("Tienda Origen:");

        txt_fecha_envio.setEditable(false);
        txt_fecha_envio.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setText("Tienda Destino:");

        txt_fecha_recepcion.setEditable(false);
        txt_fecha_recepcion.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setText("Fecha Envio:");

        txt_usuario_envia.setEditable(false);
        txt_usuario_envia.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setText("Fecha Recepcion:");

        txt_id_traslado.setEditable(false);
        txt_id_traslado.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setText("Usuario Envia:");

        jLabel13.setText("Id. Traslado");

        jToolBar3.setFloatable(false);

        btn_ver_pdf_traslado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/clipboard_text.png"))); // NOI18N
        btn_ver_pdf_traslado.setText("Ver PDf");
        btn_ver_pdf_traslado.setFocusable(false);
        btn_ver_pdf_traslado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_ver_pdf_traslado.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_ver_pdf_traslado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ver_pdf_trasladoActionPerformed(evt);
            }
        });
        jToolBar3.add(btn_ver_pdf_traslado);

        btn_salir_traslado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cross.png"))); // NOI18N
        btn_salir_traslado.setText("Salir");
        btn_salir_traslado.setFocusable(false);
        btn_salir_traslado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_salir_traslado.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_salir_traslado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salir_trasladoActionPerformed(evt);
            }
        });
        jToolBar3.add(btn_salir_traslado);

        txt_usuario_recibe.setEditable(false);
        txt_usuario_recibe.setBackground(new java.awt.Color(255, 255, 255));

        jLabel23.setText("Usuario Recibe:");

        javax.swing.GroupLayout jd_detalle_traslado1Layout = new javax.swing.GroupLayout(jd_detalle_traslado1.getContentPane());
        jd_detalle_traslado1.getContentPane().setLayout(jd_detalle_traslado1Layout);
        jd_detalle_traslado1Layout.setHorizontalGroup(
            jd_detalle_traslado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jd_detalle_traslado1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jd_detalle_traslado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jd_detalle_traslado1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_tienda_origen, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jd_detalle_traslado1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_tienda_destino, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jd_detalle_traslado1Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_fecha_envio, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jd_detalle_traslado1Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_fecha_recepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jd_detalle_traslado1Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_usuario_envia, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jd_detalle_traslado1Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_id_traslado, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jd_detalle_traslado1Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_usuario_recibe, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jd_detalle_traslado1Layout.setVerticalGroup(
            jd_detalle_traslado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_detalle_traslado1Layout.createSequentialGroup()
                .addComponent(jToolBar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_detalle_traslado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tienda_origen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_detalle_traslado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tienda_destino, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_detalle_traslado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_fecha_envio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_detalle_traslado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_fecha_recepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_detalle_traslado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_usuario_envia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_detalle_traslado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_usuario_recibe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_detalle_traslado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_id_traslado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ver Kardex Diario");

        t_kardex_diario.setModel(new javax.swing.table.DefaultTableModel(
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
        t_kardex_diario.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        t_kardex_diario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_kardex_diarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(t_kardex_diario);

        jToolBar1.setFloatable(false);
        jToolBar1.setOpaque(false);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/clipboard_text.png"))); // NOI18N
        jButton1.setText("Ver en PDF");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/find.png"))); // NOI18N
        jButton2.setText("Otra Fecha");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        btn_detalle_kardex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/comment.png"))); // NOI18N
        btn_detalle_kardex.setText("Ver Detalle");
        btn_detalle_kardex.setEnabled(false);
        btn_detalle_kardex.setFocusable(false);
        btn_detalle_kardex.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_detalle_kardex.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_detalle_kardex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_detalle_kardexActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_detalle_kardex);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cross.png"))); // NOI18N
        jButton3.setText("Salir");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1053, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String texto = txt_fecha.getText();
        c_kardex.setFecha(c_varios.fecha_myql(texto));
        c_kardex.ver_kardex_diario(t_kardex_diario);
        jd_buscar.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jd_buscar.setModal(true);
        jd_buscar.setSize(420, 125);
        jd_buscar.setLocationRelativeTo(null);
        jd_buscar.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        jd_detalle_ingreso.dispose();
        //jd_detalle_ingreso.setVisible(false);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        File miDir = new File(".");
        try {
            Map<String, Object> parametros = new HashMap<>();
            String path = miDir.getCanonicalPath();
            String diagonal = File.separator;
            String direccion = path + diagonal + "reports" + diagonal + "subreports" + diagonal;

            System.out.println(direccion);
            parametros.put("SUBREPORT_DIR", direccion);
            parametros.put("JRParameter.REPORT_LOCALE", Locale.ENGLISH);
            parametros.put("REPORT_LOCALE", Locale.ENGLISH);
            parametros.put("p_id_venta", id_ventas);
            parametros.put("p_id_almacen", id_almacen);
            parametros.put("p_letras_numero", letras_numeros);
            //   c_varios.imp_reporte("rpt_documento_venta", parametros);
            if (id_almacen == 1) {
                c_varios.ver_reporte("rpt_documento_venta_nota_rodson", parametros);
            } else {
                c_varios.ver_reporte("rpt_documento_venta_nota", parametros);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        jd_detalle_vena.dispose();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void btn_ver_pdf_trasladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ver_pdf_trasladoActionPerformed
        // TODO add your handling code here:
        File miDir = new File(".");
        try {
            Map<String, Object> parametros = new HashMap<>();
            String path = miDir.getCanonicalPath();
            String diagonal = File.separator;
            String direccion = path + diagonal + "reports" + diagonal + "subreports" + diagonal;
            System.out.println(direccion);
            parametros.put("SUBREPORT_DIR", direccion);
            parametros.put("JRParameter.REPORT_LOCALE", Locale.ENGLISH);
            parametros.put("id_traslado", id_traslado);
            //c_varios.imp_reporte("rpt_documento_venta", parametros);
            c_varios.ver_reporte("report_traslado", parametros);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }
    }//GEN-LAST:event_btn_ver_pdf_trasladoActionPerformed

    private void btn_salir_trasladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salir_trasladoActionPerformed
        jd_detalle_traslado.dispose();
    }//GEN-LAST:event_btn_salir_trasladoActionPerformed

    private void btn_detalle_kardexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_detalle_kardexActionPerformed
        if (kardex_seleccionado > -1) {
            btn_detalle_kardex.setEnabled(false);

            String kardexid = t_kardex_diario.getValueAt(kardex_seleccionado, 0).toString();
            c_kardex.setId_producto(Integer.parseInt(t_kardex_diario.getValueAt(kardex_seleccionado, 11).toString()));

            if (c_varios.esEntero(kardexid)) {
                int idkardex = Integer.parseInt(kardexid);
                cl_kardex c_kardexdetalle = new cl_kardex();
                c_kardexdetalle.setId_kardex(idkardex);
                c_kardexdetalle.setId_almacen(id_almacen);
                c_kardexdetalle.setId_producto(c_kardex.getId_producto());
                c_kardexdetalle.obtener_datos();

                if (c_kardexdetalle.getId_tipo_movimiento() == 1) {
                    cl_venta c_venta = new cl_venta();
                    cl_documento_sunat c_docsunat;
                    cl_cliente c_cliente;
                    cl_usuario c_usuario;

                    c_venta.setFecha(c_kardexdetalle.getFecha());
                    c_venta.setId_almacen(id_almacen);
                    c_venta.setId_tido(c_kardexdetalle.getId_tido());
                    c_venta.setSerie(c_kardexdetalle.getSerie());
                    c_venta.setNumero(c_kardexdetalle.getNumero());
                    if (c_venta.validar_documento()) {
                        c_venta.validar_venta();
                        id_ventas = c_venta.getId_venta();

                        leer_numeros c_letras = new leer_numeros();
                        letras_numeros = c_letras.Convertir(c_venta.getTotal() + "", true) + " SOLES";

                        c_docsunat = new cl_documento_sunat();
                        c_docsunat.setId(c_venta.getId_tido());
                        c_docsunat.validar_documento();

                        c_cliente = new cl_cliente();
                        c_cliente.setCodigo(c_venta.getId_cliente());
                        c_cliente.comprobar_cliente();

                        c_usuario = new cl_usuario();
                        c_usuario.setId_usuario(c_venta.getId_usuario());
                        c_usuario.validar_usuario();

                        txt_venta_documento.setText(c_docsunat.getAbreviado() + " | " + c_venta.getSerie() + " - " + c_venta.getNumero());
                        txt_venta_cliente.setText(c_cliente.getDocumento() + " | " + c_cliente.getNombre());
                        txt_venta_fecha.setText(c_venta.getFecha());
                        txt_venta_total.setText(c_varios.formato_totales(c_venta.getTotal()));
                        txt_venta_vendedor.setText(c_usuario.getUsername() + " | " + c_usuario.getNombre());

                        jd_detalle_vena.setModal(true);
                        jd_detalle_vena.setSize(501, 298);
                        jd_detalle_vena.setLocationRelativeTo(null);
                        jd_detalle_vena.setVisible(true);
                    }
                }

                if (c_kardexdetalle.getId_tipo_movimiento() == 2) {
                    cl_ingresos c_ingreso = new cl_ingresos();
                    cl_documento_sunat c_docsunat;
                    cl_proveedor c_proveedor;
                    cl_usuario c_usuario;

                    c_ingreso.setFecha(c_kardexdetalle.getFecha());
                    c_ingreso.setId_almacen(id_almacen);
                    c_ingreso.setId_tido(c_kardexdetalle.getId_tido());
                    c_ingreso.setSerie(c_kardexdetalle.getSerie());
                    c_ingreso.setNumero(c_kardexdetalle.getNumero());
                    if (c_ingreso.validar_documento_kardex()) {
                        c_ingreso.validar_ingreso();
                        c_docsunat = new cl_documento_sunat();
                        c_docsunat.setId(c_ingreso.getId_tido());
                        c_docsunat.validar_documento();

                        c_proveedor = new cl_proveedor();
                        c_proveedor.setId_proveedor(c_ingreso.getId_proveedor());
                        c_proveedor.cargar_datos();

                        c_usuario = new cl_usuario();
                        c_usuario.setId_usuario(c_ingreso.getId_usuario());
                        c_usuario.validar_usuario();

                        txt_compra_documento.setText(c_docsunat.getAbreviado() + " | " + c_ingreso.getSerie() + " - " + c_ingreso.getNumero());
                        txt_compra_proveedor.setText(c_proveedor.getRuc() + " | " + c_proveedor.getRazon_social());
                        txt_compra_fecha.setText(c_ingreso.getFecha());
                        txt_compra_usuario.setText(c_usuario.getUsername() + " | " + c_usuario.getNombre());

                        jd_detalle_ingreso.setModal(true);
                        jd_detalle_ingreso.setSize(537, 260);
                        jd_detalle_ingreso.setLocationRelativeTo(null);
                        jd_detalle_ingreso.setVisible(true);
                    }
                }

                if (c_kardexdetalle.getId_tipo_movimiento() == 11) {
                    cl_traslados c_traslado = new cl_traslados();
                    c_traslado.setId_traslado(c_kardexdetalle.getNumero());
                    c_traslado.validar_datos();

                    id_traslado = c_traslado.getId_traslado();

                    cl_almacen c_tenvia = new cl_almacen();
                    c_tenvia.setId(c_traslado.getId_tienda_envia());
                    c_tenvia.validar_almacen();

                    cl_almacen c_trecibe = new cl_almacen();
                    c_trecibe.setId(c_traslado.getId_tienda_recibe());
                    c_trecibe.validar_almacen();

                    cl_usuario c_uenvia = new cl_usuario();
                    c_uenvia.setId_usuario(c_traslado.getId_usuario_envia());
                    c_uenvia.validar_usuario();

                    cl_usuario c_urecibe = new cl_usuario();
                    c_urecibe.setId_usuario(c_traslado.getId_usuario_recibe());
                    c_urecibe.validar_usuario();

                    txt_tienda_origen.setText(c_tenvia.getNombre());
                    txt_tienda_destino.setText(c_trecibe.getNombre());
                    txt_fecha_envio.setText(c_traslado.getFecha_envio());
                    txt_fecha_recepcion.setText(c_traslado.getFecha_recepcion());
                    txt_usuario_envia.setText(c_uenvia.getNombre());
                    txt_usuario_recibe.setText(c_urecibe.getNombre());
                    txt_id_traslado.setText(c_traslado.getId_traslado() + "");

                    jd_detalle_traslado.setModal(true);
                    jd_detalle_traslado.setSize(516, 382);
                    jd_detalle_traslado.setLocationRelativeTo(null);
                    jd_detalle_traslado.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error al Seleccionar linea");
            }
        }
    }//GEN-LAST:event_btn_detalle_kardexActionPerformed

    private void t_kardex_diarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_kardex_diarioMouseClicked
        kardex_seleccionado = t_kardex_diario.getSelectedRow();
        btn_detalle_kardex.setEnabled(true);
    }//GEN-LAST:event_t_kardex_diarioMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see https://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frm_ver_kardex_diario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_ver_kardex_diario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_ver_kardex_diario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_ver_kardex_diario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frm_ver_kardex_diario dialog = new frm_ver_kardex_diario(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btn_detalle_kardex;
    private javax.swing.JButton btn_salir_traslado;
    private javax.swing.JButton btn_ver_pdf_traslado;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToolBar jToolBar3;
    private javax.swing.JToolBar jToolBar4;
    private javax.swing.JToolBar jToolBar5;
    private javax.swing.JDialog jd_buscar;
    private javax.swing.JDialog jd_detalle_ingreso;
    private javax.swing.JDialog jd_detalle_traslado;
    private javax.swing.JDialog jd_detalle_traslado1;
    private javax.swing.JDialog jd_detalle_vena;
    private javax.swing.JTable t_kardex_diario;
    private javax.swing.JTextField txt_compra_documento;
    private javax.swing.JTextField txt_compra_fecha;
    private javax.swing.JTextField txt_compra_proveedor;
    private javax.swing.JTextField txt_compra_usuario;
    private javax.swing.JFormattedTextField txt_fecha;
    private javax.swing.JTextField txt_fecha_envio;
    private javax.swing.JTextField txt_fecha_recepcion;
    private javax.swing.JTextField txt_id_traslado;
    private javax.swing.JTextField txt_tienda_destino;
    private javax.swing.JTextField txt_tienda_origen;
    private javax.swing.JTextField txt_usuario_envia;
    private javax.swing.JTextField txt_usuario_recibe;
    private javax.swing.JTextField txt_venta_cliente;
    private javax.swing.JTextField txt_venta_documento;
    private javax.swing.JTextField txt_venta_fecha;
    private javax.swing.JTextField txt_venta_total;
    private javax.swing.JTextField txt_venta_vendedor;
    // End of variables declaration//GEN-END:variables
}
