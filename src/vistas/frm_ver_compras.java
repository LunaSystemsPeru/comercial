/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import clases.cl_compra;
import clases.cl_varios;
import forms.frm_reg_compra;

/**
 *
 * @author luis
 */
public class frm_ver_compras extends javax.swing.JInternalFrame {
 
    //clases principales
    cl_varios c_varios = new cl_varios();
    cl_compra c_compra = new cl_compra();
    /**
     * 
     * Creates new form frm_ver_compras
     */
    public frm_ver_compras() {
        initComponents();
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
        btn_agregar = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        btn_detalle = new javax.swing.JButton();
        btn_pagos = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btn_eliminar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btn_salir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_compras = new javax.swing.JTable();

        setTitle("Ver Documentos de Compras y Pagos");

        jToolBar1.setFloatable(false);

        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        btn_agregar.setText("Agregar");
        btn_agregar.setFocusable(false);
        btn_agregar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_agregar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_agregar);
        jToolBar1.add(jSeparator3);

        btn_detalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/comment.png"))); // NOI18N
        btn_detalle.setText("Ver Detalle");
        btn_detalle.setEnabled(false);
        btn_detalle.setFocusable(false);
        btn_detalle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_detalle.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btn_detalle);

        btn_pagos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/coins.png"))); // NOI18N
        btn_pagos.setText("Ver Pagos");
        btn_pagos.setEnabled(false);
        btn_pagos.setFocusable(false);
        btn_pagos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_pagos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btn_pagos);
        jToolBar1.add(jSeparator2);

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.setEnabled(false);
        btn_eliminar.setFocusable(false);
        btn_eliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_eliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btn_eliminar);
        jToolBar1.add(jSeparator1);

        btn_salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cross.png"))); // NOI18N
        btn_salir.setText("Salir");
        btn_salir.setFocusable(false);
        btn_salir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_salir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btn_salir);

        jLabel1.setText("Buscar:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fecha", "Proveedor", "Periodo", "Nro Documento" }));

        t_compras.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(t_compras);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        frm_reg_compra formulario = new frm_reg_compra();
        c_varios.llamar_ventana_normal(formulario);
        this.dispose();
    }//GEN-LAST:event_btn_agregarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_detalle;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_pagos;
    private javax.swing.JButton btn_salir;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable t_compras;
    // End of variables declaration//GEN-END:variables
}
