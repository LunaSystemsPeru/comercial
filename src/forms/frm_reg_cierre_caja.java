/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import clases.cl_caja;
import clases.cl_movimiento_caja;
import clases.cl_varios;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import comercial.frm_principal;

/**
 *
 * @author PROYECTOS
 */
public class frm_reg_cierre_caja extends javax.swing.JDialog {

    cl_caja c_caja = new cl_caja();
    cl_movimiento_caja c_movimiento = new cl_movimiento_caja();
    cl_varios c_varios = new cl_varios();

    int id_almacen = frm_principal.c_almacen.getId();
    public static String origen;

    //variables
    double t_sistema = 0;
    double t_ingresos = 0;
    double t_egresos = 0;

    /**
     * Creates new form frm_ver_caja_diaria
     */
    public frm_reg_cierre_caja(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargar_datos();
    }

    private void cargar_datos() {
        c_caja.setId_almacen(id_almacen);
        c_caja.setFecha(c_varios.getFechaActual());
        c_caja.validar_caja();
        txt_apertura.setText(c_varios.formato_totales(c_caja.getM_apertura()));
        txt_ventas.setText(c_varios.formato_totales(c_caja.getIng_venta()));
        txt_cobranzas.setText(c_varios.formato_totales(c_caja.getCobro_venta()));
        txt_cupones.setText(c_varios.formato_totales(c_caja.getUso_cupon()));
        txt_usado_cupones.setText(c_varios.formato_totales(c_caja.getUso_cupon()));
        txt_oingresos.setText(c_varios.formato_totales(c_caja.getO_ingresos()));
        txt_pagos.setText("0.00");
        txt_devoluciones.setText(c_varios.formato_totales(c_caja.getDevolucion_ventas()));
        txt_osalidas.setText(c_varios.formato_totales(c_caja.getGastos_varios()));
        txt_fecha.setText(c_varios.fecha_usuario(c_caja.getFecha()));
        t_ingresos = c_caja.getM_apertura() + c_caja.getIng_venta() + c_caja.getCobro_venta() + c_caja.getUso_cupon() + c_caja.getO_ingresos();
        t_egresos = c_caja.getDevolucion_ventas() + c_caja.getGastos_varios();
        t_sistema = t_ingresos - t_egresos;
        txt_sistema.setText(c_varios.formato_totales(t_sistema));
        txt_s_ingresos.setText(c_varios.formato_totales(t_ingresos));
        txt_s_egresos.setText(c_varios.formato_totales(t_egresos));
        txt_s_bancos.setText(c_varios.formato_totales(c_caja.getVenta_banco()));
        if (origen.equals("ver_datos")) {
            btn_grabar.setEnabled(false);
            txt_efectivo.setEnabled(false);
        } else {
            txt_efectivo.setEnabled(true);
            txt_efectivo.requestFocus();
            txt_efectivo.selectAll();
        }
        origen = "";

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jd_ver_movimientos = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_movimientos = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        lbl_movimiento = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txt_fecha = new javax.swing.JFormattedTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_apertura = new javax.swing.JTextField();
        txt_ventas = new javax.swing.JTextField();
        txt_cobranzas = new javax.swing.JTextField();
        txt_cupones = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_usado_cupones = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_oingresos = new javax.swing.JTextField();
        btn_ver_ingresos = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_pagos = new javax.swing.JTextField();
        txt_osalidas = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_devoluciones = new javax.swing.JTextField();
        btn_ver_egresos = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_efectivo = new javax.swing.JTextField();
        txt_sistema = new javax.swing.JTextField();
        txt_diferencia = new javax.swing.JTextField();
        txt_s_ingresos = new javax.swing.JTextField();
        txt_s_egresos = new javax.swing.JTextField();
        btn_grabar = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        txt_s_bancos = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        jd_ver_movimientos.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jd_ver_movimientos.setTitle("Ver detalle de movimientos");

        t_movimientos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(t_movimientos);

        jLabel16.setText("Tipo Movimiento:");

        lbl_movimiento.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_movimiento.setText("INGRESOS");

        javax.swing.GroupLayout jd_ver_movimientosLayout = new javax.swing.GroupLayout(jd_ver_movimientos.getContentPane());
        jd_ver_movimientos.getContentPane().setLayout(jd_ver_movimientosLayout);
        jd_ver_movimientosLayout.setHorizontalGroup(
            jd_ver_movimientosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_ver_movimientosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jd_ver_movimientosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(jd_ver_movimientosLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(34, 34, 34)
                        .addComponent(lbl_movimiento)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jd_ver_movimientosLayout.setVerticalGroup(
            jd_ver_movimientosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_ver_movimientosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jd_ver_movimientosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(lbl_movimiento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ver Caja Diaria");
        setFocusable(false);

        jLabel1.setText("Fecha:");

        try {
            txt_fecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_fecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_fecha.setFocusable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle Ingresos"));

        jLabel2.setText("Apertura de Caja");

        jLabel3.setText("Tot. Ventas");

        jLabel4.setText("Tot. Cobranzas");

        jLabel5.setText("Tot. Cupones");

        txt_apertura.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_apertura.setText("0.00");
        txt_apertura.setFocusable(false);

        txt_ventas.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_ventas.setText("0.00");
        txt_ventas.setFocusable(false);

        txt_cobranzas.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_cobranzas.setText("0.00");
        txt_cobranzas.setFocusable(false);

        txt_cupones.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_cupones.setText("0.00");
        txt_cupones.setFocusable(false);

        jLabel6.setText("S/ Usados x Cupon");

        txt_usado_cupones.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_usado_cupones.setText("0.00");
        txt_usado_cupones.setFocusable(false);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Otros Ingresos");

        txt_oingresos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_oingresos.setText("0.00");
        txt_oingresos.setFocusable(false);

        btn_ver_ingresos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/find.png"))); // NOI18N
        btn_ver_ingresos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ver_ingresosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_cupones, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cobranzas, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_apertura, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_ver_ingresos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_oingresos, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(txt_usado_cupones, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_apertura, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(txt_oingresos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_ver_ingresos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cobranzas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cupones, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txt_usado_cupones, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle Egresos"));

        jLabel8.setText("Tot. Pagos Compras");

        jLabel9.setText("Otros Egresos");

        txt_pagos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_pagos.setText("0.00");
        txt_pagos.setFocusable(false);

        txt_osalidas.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_osalidas.setText("0.00");
        txt_osalidas.setFocusable(false);

        jLabel10.setText("Tot. Devoluciones");

        txt_devoluciones.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_devoluciones.setText("0.00");
        txt_devoluciones.setFocusable(false);

        btn_ver_egresos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/find.png"))); // NOI18N
        btn_ver_egresos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ver_egresosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(txt_pagos, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(30, 30, 30)
                        .addComponent(txt_devoluciones, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_ver_egresos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(txt_osalidas, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txt_pagos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(txt_osalidas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_ver_egresos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txt_devoluciones, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Resumen"));

        jLabel11.setText("Caja Efectivo");

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel12.setText("Suma Ingresos");

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel13.setText("Suma Egresos");

        jLabel14.setText("Total Sistema:");

        jLabel15.setText("Diferencia");

        txt_efectivo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_efectivo.setText("0.00");
        txt_efectivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_efectivoKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_efectivoKeyPressed(evt);
            }
        });

        txt_sistema.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_sistema.setText("0.00");
        txt_sistema.setFocusable(false);

        txt_diferencia.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_diferencia.setText("0.00");
        txt_diferencia.setFocusable(false);

        txt_s_ingresos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_s_ingresos.setText("0.00");
        txt_s_ingresos.setFocusable(false);

        txt_s_egresos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_s_egresos.setText("0.00");
        txt_s_egresos.setFocusable(false);

        btn_grabar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/accept.png"))); // NOI18N
        btn_grabar.setText("Grabar");
        btn_grabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_grabarActionPerformed(evt);
            }
        });

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel17.setText("Total Banco:");

        txt_s_bancos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_s_bancos.setText("0.00");
        txt_s_bancos.setFocusable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_grabar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txt_efectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txt_sistema, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_diferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(111, 111, 111)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_s_bancos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_s_egresos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_s_ingresos, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_efectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_s_ingresos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txt_sistema, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_s_egresos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txt_diferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_s_bancos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_grabar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cross.png"))); // NOI18N
        jButton2.setText("Cerrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btn_grabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_grabarActionPerformed
        double defectivo = Double.parseDouble(txt_efectivo.getText());
        //if (frm_menu.usu.getPve_eli().equals("1")) {
        int confirmado = JOptionPane.showConfirmDialog(null, "¿Desea cerrar caja con ese monto?");
        if (JOptionPane.OK_OPTION == confirmado) {
            c_caja.setM_cierre(defectivo);
            if (c_caja.cerrar_caja()) {
                this.dispose();
                JOptionPane.showMessageDialog(null, "CAJA CERRADA CORRECTAMENTE.\nHASTA MAÑANA!!");
                System.exit(0);
            }
        }
    }//GEN-LAST:event_btn_grabarActionPerformed

    private void txt_efectivoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_efectivoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (c_varios.esDecimal(txt_efectivo.getText())) {
                double efectivo = Double.parseDouble(txt_efectivo.getText());
                if (efectivo > 0) {
                    double diferencia = efectivo - t_sistema;
                    txt_diferencia.setText(c_varios.formato_totales(diferencia));
                    txt_efectivo.setEnabled(false);
                    btn_grabar.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_txt_efectivoKeyPressed

    private void btn_ver_ingresosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ver_ingresosActionPerformed
        jd_ver_movimientos.setModal(true);
        jd_ver_movimientos.setSize(600, 485);
        jd_ver_movimientos.setLocationRelativeTo(null);
        lbl_movimiento.setText("OTROS INGRESOS");
        String query = "select mc.id_movimiento, mc.ingresa, mc.retira, mc.motivo, u.username "
                + "from cajas_movimientos as mc "
                + "inner join usuarios as u on u.id_usuarios = mc.id_usuarios "
                + "where mc.fecha = '" + c_caja.getFecha() + "' and mc.id_almacen = '" + c_caja.getId_almacen() + "' and ingresa > 0 "
                + "order by id_movimiento asc ";
        c_movimiento.mostrar(t_movimientos, query);
        jd_ver_movimientos.setVisible(true);
    }//GEN-LAST:event_btn_ver_ingresosActionPerformed

    private void btn_ver_egresosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ver_egresosActionPerformed
        jd_ver_movimientos.setModal(true);
        jd_ver_movimientos.setSize(600, 485);
        jd_ver_movimientos.setLocationRelativeTo(null);
        lbl_movimiento.setText("OTRAS SALIDAS");
        String query = "select mc.id_movimiento, mc.ingresa, mc.retira, mc.motivo, u.username "
                + "from cajas_movimientos as mc "
                + "inner join usuarios as u on u.id_usuarios = mc.id_usuarios "
                + "where mc.fecha = '" + c_caja.getFecha() + "' and mc.id_almacen = '" + c_caja.getId_almacen() + "' and retira > 0 "
                + "order by id_movimiento asc ";
        c_movimiento.mostrar(t_movimientos, query);
        jd_ver_movimientos.setVisible(true);
    }//GEN-LAST:event_btn_ver_egresosActionPerformed

    private void txt_efectivoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_efectivoKeyTyped
        c_varios.solo_precio(evt);
    }//GEN-LAST:event_txt_efectivoKeyTyped

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
            java.util.logging.Logger.getLogger(frm_reg_cierre_caja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_reg_cierre_caja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_reg_cierre_caja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_reg_cierre_caja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frm_reg_cierre_caja dialog = new frm_reg_cierre_caja(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btn_ver_egresos;
    private javax.swing.JButton btn_ver_ingresos;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JDialog jd_ver_movimientos;
    private javax.swing.JLabel lbl_movimiento;
    private javax.swing.JTable t_movimientos;
    private javax.swing.JTextField txt_apertura;
    private javax.swing.JTextField txt_cobranzas;
    private javax.swing.JTextField txt_cupones;
    private javax.swing.JTextField txt_devoluciones;
    private javax.swing.JTextField txt_diferencia;
    private javax.swing.JTextField txt_efectivo;
    private javax.swing.JFormattedTextField txt_fecha;
    private javax.swing.JTextField txt_oingresos;
    private javax.swing.JTextField txt_osalidas;
    private javax.swing.JTextField txt_pagos;
    private javax.swing.JTextField txt_s_bancos;
    private javax.swing.JTextField txt_s_egresos;
    private javax.swing.JTextField txt_s_ingresos;
    private javax.swing.JTextField txt_sistema;
    private javax.swing.JTextField txt_usado_cupones;
    private javax.swing.JTextField txt_ventas;
    // End of variables declaration//GEN-END:variables
}
