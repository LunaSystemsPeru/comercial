/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import clases.cl_empresa;
import clases.cl_varios;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import json.cl_json_entidad;
import nicon.notify.core.Notification;
import org.json.simple.parser.ParseException;

/**
 *
 * @author CALIDAD
 */
public class frm_ver_empresas extends javax.swing.JInternalFrame {

    cl_empresa c_empresa = new cl_empresa();
    cl_varios c_varios = new cl_varios();

    public frm_ver_empresas() {
        initComponents();
        String query = "select * "
                + "from empresa "
                + "order by razon asc";
        c_empresa.mostrar(t_empresas, query);
    }

    private void llenar() {
        c_empresa.setId(c_empresa.obtener_codigo());
        c_empresa.setRazon(txt_j_razon.getText());
        c_empresa.setRuc(txt_j_ruc.getText());
        c_empresa.setDireccion(txt_j_direccion.getText());
        c_empresa.setCondicion(txt_j_condicion.getText());
        c_empresa.setEstado(txt_j_estado.getText());
    }

    private void limpiar() {
        txt_j_razon.setText("");
        txt_j_ruc.setText("");
        txt_j_direccion.setText("");
        txt_j_condicion.setText("");
        txt_j_estado.setText("");
        btn_j_grabar.setEnabled(false);
        txt_j_ruc.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jd_registrar = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_j_id = new javax.swing.JTextField();
        txt_j_ruc = new javax.swing.JTextField();
        txt_j_razon = new javax.swing.JTextField();
        txt_j_direccion = new javax.swing.JTextField();
        txt_j_estado = new javax.swing.JTextField();
        txt_j_condicion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        btn_j_grabar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btn_j_cerrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_empresas = new javax.swing.JTable();
        jToolBar2 = new javax.swing.JToolBar();
        btn_agregar = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jButton1 = new javax.swing.JButton();
        btn_cerrar = new javax.swing.JButton();

        jd_registrar.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jd_registrar.setTitle("Registrar mi empresa");

        jLabel1.setText("Codigo:");

        jLabel2.setText("RUC");

        jLabel3.setText("Razon Social");

        jLabel4.setText("Direccion:");

        jLabel5.setText("Estado:");

        jLabel6.setText("Condicion:");

        txt_j_id.setEnabled(false);

        txt_j_ruc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_j_rucKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_j_rucKeyPressed(evt);
            }
        });

        txt_j_razon.setEnabled(false);

        txt_j_direccion.setEnabled(false);

        txt_j_estado.setEnabled(false);

        txt_j_condicion.setEnabled(false);

        jLabel7.setText("enter para obtener datos");

        jToolBar1.setFloatable(false);
        jToolBar1.setOpaque(false);

        btn_j_grabar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/accept.png"))); // NOI18N
        btn_j_grabar.setText("Guardar");
        btn_j_grabar.setEnabled(false);
        btn_j_grabar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_j_grabar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btn_j_grabar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_j_grabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_j_grabarActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_j_grabar);
        jToolBar1.add(jSeparator1);

        btn_j_cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cross.png"))); // NOI18N
        btn_j_cerrar.setText("Cerrar");
        btn_j_cerrar.setFocusable(false);
        btn_j_cerrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_j_cerrar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btn_j_cerrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_j_cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_j_cerrarActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_j_cerrar);

        javax.swing.GroupLayout jd_registrarLayout = new javax.swing.GroupLayout(jd_registrar.getContentPane());
        jd_registrar.getContentPane().setLayout(jd_registrarLayout);
        jd_registrarLayout.setHorizontalGroup(
            jd_registrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_registrarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jd_registrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jd_registrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_j_razon)
                    .addComponent(txt_j_direccion)
                    .addGroup(jd_registrarLayout.createSequentialGroup()
                        .addGroup(jd_registrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jd_registrarLayout.createSequentialGroup()
                                .addGroup(jd_registrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_j_id, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_j_ruc, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7))
                            .addGroup(jd_registrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txt_j_condicion, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_j_estado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)))
                        .addGap(0, 194, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jd_registrarLayout.setVerticalGroup(
            jd_registrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_registrarLayout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_registrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_j_id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_registrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_j_ruc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_registrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_j_razon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_registrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_j_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_registrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_j_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_registrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_j_condicion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        setTitle("Ver Mis Empresas");

        t_empresas.setModel(new javax.swing.table.DefaultTableModel(
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
        t_empresas.setShowVerticalLines(false);
        jScrollPane1.setViewportView(t_empresas);

        jToolBar2.setFloatable(false);
        jToolBar2.setOpaque(false);

        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        btn_agregar.setText("Agregar");
        btn_agregar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_agregar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btn_agregar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });
        jToolBar2.add(btn_agregar);

        btn_modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/application_edit.png"))); // NOI18N
        btn_modificar.setText("Modificar");
        btn_modificar.setEnabled(false);
        btn_modificar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_modificar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btn_modificar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });
        jToolBar2.add(btn_modificar);
        jToolBar2.add(jSeparator2);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        jButton1.setText("Eliminar");
        jButton1.setEnabled(false);
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton1);

        btn_cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cross.png"))); // NOI18N
        btn_cerrar.setText("Cerrar");
        btn_cerrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_cerrar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btn_cerrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarActionPerformed(evt);
            }
        });
        jToolBar2.add(btn_cerrar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 866, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_cerrarActionPerformed

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        jd_registrar.setModal(true);
        jd_registrar.setSize(673, 305);
        jd_registrar.setLocationRelativeTo(null);
        jd_registrar.setVisible(true);
        txt_j_ruc.requestFocus();
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void btn_j_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_j_cerrarActionPerformed
        limpiar();
        jd_registrar.dispose();
    }//GEN-LAST:event_btn_j_cerrarActionPerformed

    private void txt_j_rucKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_j_rucKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String documento = txt_j_ruc.getText();
            if (documento.length() == 11) {
                if (c_varios.esDecimal(documento)) {
                    boolean existe_entidad = false;

                    c_empresa.setRuc(documento);
                    existe_entidad = c_empresa.comprobar_empresa_ruc();

                    if (existe_entidad == false) {
                        //ruc
                        try {
                            String json = cl_json_entidad.getJSONRUC(documento);
                            //Lo mostramos
                            String[] datos = cl_json_entidad.showJSONRUC(json);
                            txt_j_razon.setText(datos[0]);
                            txt_j_direccion.setText(datos[1]);
                            txt_j_condicion.setText(datos[2]);
                            txt_j_estado.setText(datos[3]);

                            btn_j_grabar.setEnabled(true);
                            btn_j_grabar.requestFocus();

                        } catch (ParseException e) {
                            JOptionPane.showMessageDialog(null, "ERROR EN BUSCAR RUC " + e.getLocalizedMessage());
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "POR FAVOR INGRESE CORRECTAMENTE NUMEROS");
                    txt_j_ruc.setText("");
                    txt_j_ruc.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "POR FAVOR COMPLETE EL NUMERO DE RUC");
                txt_j_ruc.setText("");
                txt_j_ruc.requestFocus();
            }

        }
    }//GEN-LAST:event_txt_j_rucKeyPressed

    private void txt_j_rucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_j_rucKeyTyped
        c_varios.solo_numeros(evt);
        c_varios.limitar_caracteres(evt, txt_j_ruc, 11);
    }//GEN-LAST:event_txt_j_rucKeyTyped

    private void btn_j_grabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_j_grabarActionPerformed
        //lenar datos
        llenar();
        boolean registrar = c_empresa.registrar();
        if (registrar) {
            Notification.show("EMPRESAS", "Registrado correctamente");
            limpiar();
            jd_registrar.dispose();

            //actualizar tabla
            String query = "select * "
                    + "from empresa "
                    + "order by razon asc";
            c_empresa.mostrar(t_empresas, query);
        }
    }//GEN-LAST:event_btn_j_grabarActionPerformed

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_modificarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int confirmado = JOptionPane.showConfirmDialog(null, "¿Esta seguro de elimina la empresa?");

        if (JOptionPane.OK_OPTION == confirmado) {
            c_empresa.eliminar();
            String query = "select * "
                    + "from empresa "
                    + "order by razon asc";
            c_empresa.mostrar(t_empresas, query);
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_cerrar;
    private javax.swing.JButton btn_j_cerrar;
    private javax.swing.JButton btn_j_grabar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JDialog jd_registrar;
    private javax.swing.JTable t_empresas;
    private javax.swing.JTextField txt_j_condicion;
    private javax.swing.JTextField txt_j_direccion;
    private javax.swing.JTextField txt_j_estado;
    private javax.swing.JTextField txt_j_id;
    private javax.swing.JTextField txt_j_razon;
    private javax.swing.JTextField txt_j_ruc;
    // End of variables declaration//GEN-END:variables
}
