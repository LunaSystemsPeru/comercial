/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comercial;

import clases.cl_almacen;
import clases.cl_caja;
import clases.cl_conectar;
import clases.cl_empresa;
import clases.cl_usuario;
import clases.cl_usuario_permisos;
import clases.cl_varios;
import clases_hilos.cl_notificaciones;
import clases_varios.cl_grafica_mensual;
import forms.frm_reg_cierre_caja;
import forms.frm_reg_movimiento_caja;
import forms.frm_reg_traslado;
import forms.frm_reg_venta;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import nicon.notify.core.Notification;
import org.jvnet.substance.SubstanceLookAndFeel;
import vistas.frm_ver_almacenes;
import vistas.frm_ver_clientes;
import vistas.frm_ver_cobros;
import vistas.frm_ver_compras;
import vistas.frm_ver_empresas;
import vistas.frm_ver_guias_remision;
import vistas.frm_ver_ingresos;
import vistas.frm_ver_inventarios;
import vistas.frm_ver_kardex_diario;
import vistas.frm_ver_mis_productos;
import vistas.frm_ver_mis_productos2;
import vistas.frm_ver_productos_tiendas;
import vistas.frm_ver_productos_todos;
import vistas.frm_ver_proveedores;
import vistas.frm_ver_traslados;
import vistas.frm_ver_usuarios;
import vistas.frm_ver_ventas;
import vistas.rpt_mercaderia;
import vistas.rpt_ventas;

/**
 *
 * @author luis
 */
public class frm_principal extends javax.swing.JFrame {

    cl_conectar c_conectar = new cl_conectar();
    cl_varios c_varios = new cl_varios();

    public static cl_usuario c_usuario = new cl_usuario();
    public static cl_almacen c_almacen = new cl_almacen();
    public static cl_empresa c_empresa = new cl_empresa();
    public static cl_usuario_permisos c_permiso = new cl_usuario_permisos();

    cl_caja c_caja = new cl_caja();
    cl_grafica_mensual c_grafica;
    cl_notificaciones c_notificaciones = new cl_notificaciones();

    /**
     * Creates new form frm_principal
     */
    public frm_principal() {
        initComponents();
        if (c_conectar.verificar_conexion()) {
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            System.out.println(c_varios.getHoraActual());
            if (c_conectar.conectar()) {
                autoconectar();
                cargar_login();
            } else {
                JOptionPane.showMessageDialog(null, "ERROR!! , NO ES POSIBLE CONECTAR A LA BASE DE DATOS");
                System.exit(0);
            }
        } else {
            JOptionPane.showMessageDialog(null, "ERROR!! , NO TIENE CONEXION A INTERNET");
            System.exit(0);
        }
    }

    private void cargar_login() {
        jd_login.setSize(318, 380);
        jd_login.setModal(true);
        jd_login.setLocationRelativeTo(null);
        jd_login.setVisible(true);
    }

    private void autoconectar() {
        try {
            Timer timer = new Timer(35000, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    c_usuario.autoconectar();
                }
            });
            timer.start();
            timer.setRepeats(true);
        } catch (Exception e) {
            System.out.println("Error grave " + e.getLocalizedMessage());
        }
    }

    private void auto_notificar() {
        try {
            Timer timer = new Timer(60000, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //System.out.println("mostrando id almacen " + c_almacen.getId());
                    c_notificaciones.setId_almacen(c_almacen.getId());
                    c_notificaciones.mostrar();
                }
            });
            timer.start();
            timer.setRepeats(true);
        } catch (Exception e) {
            System.out.println("Error grave " + e.getLocalizedMessage());
        }
    }

    private void cargar_permisos() {
        c_permiso.setId_permiso(23);
        boolean permitido23 = c_permiso.validar();

        if (!permitido23) {
            jButton12.setEnabled(false);
        }
        
        c_permiso.setId_permiso(18);
        boolean permitido18 = c_permiso.validar();

        if (!permitido18) {
            jButton4.setEnabled(false);
        }

        c_permiso.setId_permiso(17);
        boolean permitido17 = c_permiso.validar();

        if (!permitido17) {
            jButton16.setEnabled(false);
        }

        c_permiso.setId_permiso(16);
        boolean permitido16 = c_permiso.validar();

        if (!permitido16) {
            jButton3.setEnabled(false);
        }

        c_permiso.setId_permiso(15);
        boolean permitido15 = c_permiso.validar();

        if (!permitido15) {
            jButton23.setEnabled(false);
        }
        
        c_permiso.setId_permiso(9);
        boolean permitido9 = c_permiso.validar();

        if (!permitido9) {
            jButton13.setEnabled(false);
        }
        
        c_permiso.setId_permiso(4);
        boolean permitido4 = c_permiso.validar();

        if (!permitido4) {
            jButton19.setEnabled(false);
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

        jd_login = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txt_usuario = new javax.swing.JTextField();
        btn_cerrar = new javax.swing.JButton();
        btn_ingresar = new javax.swing.JButton();
        txt_contrasena = new javax.swing.JPasswordField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jd_apertura = new javax.swing.JDialog();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_tienda = new javax.swing.JTextField();
        txt_fecha = new javax.swing.JTextField();
        txt_monto = new javax.swing.JTextField();
        jToolBar7 = new javax.swing.JToolBar();
        btn_abrir_caja = new javax.swing.JButton();
        jd_formato = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel1 = new javax.swing.JLabel();
        lbl_nom_tienda = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jLabel3 = new javax.swing.JLabel();
        lbl_empresa = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jp_meses = new javax.swing.JPanel();
        jp_dias = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jToolBar4 = new javax.swing.JToolBar();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jSeparator9 = new javax.swing.JToolBar.Separator();
        jButton1 = new javax.swing.JButton();
        jSeparator10 = new javax.swing.JToolBar.Separator();
        jButton8 = new javax.swing.JButton();
        jSeparator14 = new javax.swing.JToolBar.Separator();
        jButton9 = new javax.swing.JButton();
        jToolBar5 = new javax.swing.JToolBar();
        jButton18 = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JToolBar.Separator();
        jButton10 = new javax.swing.JButton();
        jSeparator11 = new javax.swing.JToolBar.Separator();
        jButton19 = new javax.swing.JButton();
        jToolBar2 = new javax.swing.JToolBar();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jButton15 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jButton14 = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        jButton7 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jSeparator12 = new javax.swing.JToolBar.Separator();
        jButton17 = new javax.swing.JButton();
        jSeparator15 = new javax.swing.JToolBar.Separator();
        jButton23 = new javax.swing.JButton();
        jToolBar6 = new javax.swing.JToolBar();
        jButton3 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jButton16 = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        jButton4 = new javax.swing.JButton();
        jSeparator13 = new javax.swing.JToolBar.Separator();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jToolBar3 = new javax.swing.JToolBar();
        jLabel13 = new javax.swing.JLabel();
        lbl_traslados_encontrados = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator16 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        lbl_usuario = new javax.swing.JLabel();

        jd_login.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        jd_login.setTitle("Ingresar al Sistema");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/locked-30.png"))); // NOI18N

        txt_usuario.setToolTipText("Ingresar Usuario");
        txt_usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_usuarioKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_usuarioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_usuarioKeyReleased(evt);
            }
        });

        btn_cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cross.png"))); // NOI18N
        btn_cerrar.setText("Cancelar");
        btn_cerrar.setContentAreaFilled(false);
        btn_cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarActionPerformed(evt);
            }
        });

        btn_ingresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/accept.png"))); // NOI18N
        btn_ingresar.setText("Ingresar");
        btn_ingresar.setEnabled(false);
        btn_ingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ingresarActionPerformed(evt);
            }
        });

        txt_contrasena.setText("jPasswordField1");
        txt_contrasena.setEnabled(false);
        txt_contrasena.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_contrasenaKeyPressed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jLabel11.setText("Ingreso al Sistema");

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/profle-30.png"))); // NOI18N

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/164426.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_ingresar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_usuario)
                            .addComponent(txt_contrasena)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 132, Short.MAX_VALUE)
                        .addComponent(btn_cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_usuario)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_contrasena))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_ingresar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jd_loginLayout = new javax.swing.GroupLayout(jd_login.getContentPane());
        jd_login.getContentPane().setLayout(jd_loginLayout);
        jd_loginLayout.setHorizontalGroup(
            jd_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_loginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jd_loginLayout.setVerticalGroup(
            jd_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_loginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jd_apertura.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        jd_apertura.setTitle("Aperturar Caja");

        jLabel4.setText("Tienda:");

        jLabel5.setText("Fecha:");

        jLabel7.setText("S/ Apertura:");

        txt_tienda.setEnabled(false);

        txt_fecha.setEnabled(false);

        txt_monto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_monto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_montoKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_montoKeyPressed(evt);
            }
        });

        jToolBar7.setFloatable(false);
        jToolBar7.setOpaque(false);

        btn_abrir_caja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/coins.png"))); // NOI18N
        btn_abrir_caja.setText("Abrir Caja");
        btn_abrir_caja.setEnabled(false);
        btn_abrir_caja.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_abrir_caja.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_abrir_caja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_abrir_cajaActionPerformed(evt);
            }
        });
        jToolBar7.add(btn_abrir_caja);

        javax.swing.GroupLayout jd_aperturaLayout = new javax.swing.GroupLayout(jd_apertura.getContentPane());
        jd_apertura.getContentPane().setLayout(jd_aperturaLayout);
        jd_aperturaLayout.setHorizontalGroup(
            jd_aperturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_aperturaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jd_aperturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jd_aperturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_tienda, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                    .addGroup(jd_aperturaLayout.createSequentialGroup()
                        .addGroup(jd_aperturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_fecha)
                            .addComponent(txt_monto, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jToolBar7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jd_aperturaLayout.setVerticalGroup(
            jd_aperturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_aperturaLayout.createSequentialGroup()
                .addComponent(jToolBar7, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_aperturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tienda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_aperturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jd_aperturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_monto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "org.jvnet.substance.skin.AutumnSkin", "org.jvnet.substance.skin.BusinessBlackSteelSkin", "org.jvnet.substance.skin.BusinessBlueSteelSkin", "org.jvnet.substance.skin.BusinessSkin", "org.jvnet.substance.skin.CremeCoffeeSkin", "org.jvnet.substance.skin.CremeSkin", "org.jvnet.substance.skin.EmeraldDuskSkin", "org.jvnet.substance.skin.FieldOfWheatSkin", "org.jvnet.substance.skin.FindingNemoSkin", "org.jvnet.substance.skin.GreenMagicSkin", "org.jvnet.substance.skin.MagmaSkin", "org.jvnet.substance.skin.MangoSkin", "org.jvnet.substance.skin.MistAquaSkin", "org.jvnet.substance.skin.ModerateSkin", "org.jvnet.substance.skin.NebulaBrickWallSkin", "org.jvnet.substance.skin.NebulaSkin", "org.jvnet.substance.skin.OfficeBlue2007Skin", "org.jvnet.substance.skin.OfficeSilver2007Skin", "org.jvnet.substance.skin.RavenGraphiteGlassSkin", "org.jvnet.substance.skin.RavenGraphiteSkin", "org.jvnet.substance.skin.RavenSkin", "org.jvnet.substance.skin.SaharaSkin" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(106, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jd_formatoLayout = new javax.swing.GroupLayout(jd_formato.getContentPane());
        jd_formato.getContentPane().setLayout(jd_formatoLayout);
        jd_formatoLayout.setHorizontalGroup(
            jd_formatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_formatoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jd_formatoLayout.setVerticalGroup(
            jd_formatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jd_formatoLayout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(134, 134, 134))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Comercial Peña | Sistema de Gestion de Ventas");
        setBackground(new java.awt.Color(204, 204, 204));
        setIconImage(Toolkit.getDefaultToolkit().getImage("reports/icono.jpg"));

        jToolBar1.setBorder(null);
        jToolBar1.setFloatable(false);
        jToolBar1.setBorderPainted(false);
        jToolBar1.setOpaque(false);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/computer.png"))); // NOI18N
        jLabel1.setText("Tienda: ");
        jToolBar1.add(jLabel1);

        lbl_nom_tienda.setText("Principal");
        jToolBar1.add(lbl_nom_tienda);

        jLabel2.setText("   ");
        jToolBar1.add(jLabel2);
        jToolBar1.add(jSeparator1);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/banco.png"))); // NOI18N
        jLabel3.setText("Empresa: ");
        jToolBar1.add(jLabel3);

        lbl_empresa.setText("10469932091 | OYANGUREN GIRON LUIS ENRIQUE");
        jToolBar1.add(lbl_empresa);

        jDesktopPane1.setBackground(new java.awt.Color(204, 204, 204));

        jp_meses.setPreferredSize(new java.awt.Dimension(419, 309));

        javax.swing.GroupLayout jp_mesesLayout = new javax.swing.GroupLayout(jp_meses);
        jp_meses.setLayout(jp_mesesLayout);
        jp_mesesLayout.setHorizontalGroup(
            jp_mesesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 419, Short.MAX_VALUE)
        );
        jp_mesesLayout.setVerticalGroup(
            jp_mesesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jp_diasLayout = new javax.swing.GroupLayout(jp_dias);
        jp_dias.setLayout(jp_diasLayout);
        jp_diasLayout.setHorizontalGroup(
            jp_diasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 419, Short.MAX_VALUE)
        );
        jp_diasLayout.setVerticalGroup(
            jp_diasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 309, Short.MAX_VALUE)
        );

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jToolBar4.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar4.setBorder(null);
        jToolBar4.setFloatable(false);
        jToolBar4.setRollover(true);
        jToolBar4.setBorderPainted(false);
        jToolBar4.add(jSeparator2);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Office-Customer-Male-Light-icon.png"))); // NOI18N
        jButton5.setText("Clientes");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar4.add(jButton5);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/shopping-add-512.png"))); // NOI18N
        jButton6.setText("Vender");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar4.add(jButton6);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Product-sale-report-icon.png"))); // NOI18N
        jButton2.setText("Ventas");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar4.add(jButton2);
        jToolBar4.add(jSeparator9);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Identity-separation-man-qr-code-data-barcode-512.png"))); // NOI18N
        jButton1.setText("Por Cobrar");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar4.add(jButton1);
        jToolBar4.add(jSeparator10);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/clipboard-512.png"))); // NOI18N
        jButton8.setText("Reportes");
        jButton8.setFocusable(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jToolBar4.add(jButton8);
        jToolBar4.add(jSeparator14);

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/iconfinder_computer_connection_1421640.png"))); // NOI18N
        jButton9.setText("Reconectar");
        jButton9.setFocusable(false);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jToolBar4.add(jButton9);

        jTabbedPane1.addTab("Facturacion", jToolBar4);

        jToolBar5.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar5.setBorder(null);
        jToolBar5.setFloatable(false);
        jToolBar5.setBorderPainted(false);

        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/customer-testimonials-512.png"))); // NOI18N
        jButton18.setText("Proveedores");
        jButton18.setFocusable(false);
        jButton18.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton18.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jToolBar5.add(jButton18);
        jToolBar5.add(jSeparator8);

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/eccomerce_-_receipt-512.png"))); // NOI18N
        jButton10.setText("Compras");
        jButton10.setFocusable(false);
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jToolBar5.add(jButton10);
        jToolBar5.add(jSeparator11);

        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/basket-01-512.png"))); // NOI18N
        jButton19.setText("Ingreso Mercaderia");
        jButton19.setFocusable(false);
        jButton19.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton19.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        jToolBar5.add(jButton19);

        jTabbedPane1.addTab("Compras", jToolBar5);

        jToolBar2.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar2.setBorder(null);
        jToolBar2.setFloatable(false);
        jToolBar2.setBorderPainted(false);

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/transport-512.png"))); // NOI18N
        jButton11.setText("Mis Productos");
        jButton11.setFocusable(false);
        jButton11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton11);

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/warehouse-512.png"))); // NOI18N
        jButton12.setText("Otras tiendas");
        jButton12.setFocusable(false);
        jButton12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton12);
        jToolBar2.add(jSeparator4);

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/archiver-512.png"))); // NOI18N
        jButton15.setText("Clasificacion");
        jButton15.setEnabled(false);
        jButton15.setFocusable(false);
        jButton15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton15.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar2.add(jButton15);

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/product-icon.png"))); // NOI18N
        jButton13.setText("Productos");
        jButton13.setFocusable(false);
        jButton13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton13.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton13);
        jToolBar2.add(jSeparator5);

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/86-512.png"))); // NOI18N
        jButton14.setText("Kardex Diario");
        jButton14.setFocusable(false);
        jButton14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton14.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton14);
        jToolBar2.add(jSeparator7);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/shopping2-02-512.png"))); // NOI18N
        jButton7.setText("Traslados");
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton7);

        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/shop-78-512.png"))); // NOI18N
        jButton20.setText("Guias de Remision");
        jButton20.setFocusable(false);
        jButton20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton20.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton20);
        jToolBar2.add(jSeparator12);

        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/inventory_management-512.png"))); // NOI18N
        jButton17.setText("Inventario");
        jButton17.setFocusable(false);
        jButton17.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton17.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton17);
        jToolBar2.add(jSeparator15);

        jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/clipboard-512.png"))); // NOI18N
        jButton23.setText("Reportes");
        jButton23.setFocusable(false);
        jButton23.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton23.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton23);

        jTabbedPane1.addTab("Mercaderia", jToolBar2);

        jToolBar6.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar6.setBorder(null);
        jToolBar6.setFloatable(false);
        jToolBar6.setBorderPainted(false);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/company_building-512.png"))); // NOI18N
        jButton3.setText("Empresas");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar6.add(jButton3);
        jToolBar6.add(jSeparator3);

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/40-512.png"))); // NOI18N
        jButton16.setText("Tiendas");
        jButton16.setFocusable(false);
        jButton16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton16.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jToolBar6.add(jButton16);
        jToolBar6.add(jSeparator6);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user-512.png"))); // NOI18N
        jButton4.setText("Usuarios");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar6.add(jButton4);
        jToolBar6.add(jSeparator13);

        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Sales-by-payment-method-icon.png"))); // NOI18N
        jButton21.setText("Caja Diaria");
        jButton21.setFocusable(false);
        jButton21.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton21.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        jToolBar6.add(jButton21);

        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Sales-by-payment-method-icon.png"))); // NOI18N
        jButton22.setText("Cerrar Caja");
        jButton22.setFocusable(false);
        jButton22.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton22.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        jToolBar6.add(jButton22);

        jTabbedPane1.addTab("Configuracion", jToolBar6);

        jDesktopPane1.setLayer(jp_meses, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jp_dias, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jTabbedPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addComponent(jp_meses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jp_dias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1006, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jp_dias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jp_meses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jToolBar3.setFloatable(false);
        jToolBar3.setBorderPainted(false);
        jToolBar3.setOpaque(false);

        jLabel13.setText("Traslados Pendientes: ");
        jToolBar3.add(jLabel13);

        lbl_traslados_encontrados.setText("0");
        jToolBar3.add(lbl_traslados_encontrados);

        jLabel10.setText(" ");
        jToolBar3.add(jLabel10);

        jSeparator16.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator16.setPreferredSize(new java.awt.Dimension(6, 0));
        jToolBar3.add(jSeparator16);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user_thief_baldie.png"))); // NOI18N
        jLabel6.setText("Usuario: ");
        jToolBar3.add(jLabel6);

        lbl_usuario.setText("loyangureng");
        jToolBar3.add(lbl_usuario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToolBar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToolBar3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        SubstanceLookAndFeel.setSkin(jComboBox1.getSelectedItem().toString());
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        frm_reg_venta formulario = new frm_reg_venta();
        c_varios.llamar_ventana(formulario);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        frm_ver_empresas formulario = new frm_ver_empresas();
        c_varios.llamar_ventana(formulario);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        frm_ver_ingresos formulario = new frm_ver_ingresos();
        c_varios.llamar_ventana(formulario);
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        Notification.show("Todos los Productos", "Presione Enter para buscar");
        frm_ver_productos_todos formulario = new frm_ver_productos_todos();
        c_varios.llamar_ventana(formulario);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        frm_ver_almacenes formulario = new frm_ver_almacenes();
        c_varios.llamar_ventana(formulario);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void txt_usuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_usuarioKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            c_usuario.setUsername(txt_usuario.getText().trim());
            boolean existe = c_usuario.validar_usuario_username();
            if (existe) {
                txt_contrasena.setEnabled(true);
                txt_contrasena.setText("");
                txt_contrasena.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Usuario no existe \nVuelva a ingresar usuario");
                txt_usuario.selectAll();
                txt_usuario.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_usuarioKeyPressed

    private void txt_usuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_usuarioKeyTyped
        c_varios.limitar_caracteres(evt, txt_usuario, 20);
    }//GEN-LAST:event_txt_usuarioKeyTyped

    private void btn_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btn_cerrarActionPerformed

    private void btn_ingresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ingresarActionPerformed
        c_almacen.setId(c_usuario.getId_almacen());
        c_almacen.validar_almacen();

        c_empresa.setId(c_almacen.getEmpresa());
        c_empresa.validar_empresa();

        c_permiso.setId_usuario(c_usuario.getId_usuario());
        cargar_permisos();

        lbl_nom_tienda.setText(c_almacen.getNombre());
        lbl_empresa.setText(c_empresa.getRuc() + " | " + c_empresa.getRazon());
        lbl_usuario.setText(c_usuario.getUsername());

        jd_login.setVisible(false);

        c_caja.setId_almacen(c_almacen.getId());
        c_caja.setFecha(c_varios.getFechaActual());
        boolean existe_caja = c_caja.validar_caja();

        if (!existe_caja) {
            txt_tienda.setText(c_almacen.getNombre());
            txt_fecha.setText(c_varios.fecha_usuario(c_varios.getFechaActual()));
            jd_apertura.setModal(true);
            jd_apertura.setSize(398, 224);
            jd_apertura.setLocationRelativeTo(null);
            jd_apertura.setVisible(true);
        }

        //mostrar notificaciones
        auto_notificar();

        c_grafica = new cl_grafica_mensual();
        c_grafica.llenar_series_diarias(jp_dias);
        c_grafica.llenar_series_mensuales(jp_meses);
    }//GEN-LAST:event_btn_ingresarActionPerformed

    private void txt_contrasenaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_contrasenaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String contrasena = txt_contrasena.getText();
            System.out.println(contrasena);
            if (contrasena.equals(c_usuario.getPassword())) {
                c_usuario.validar_usuario();
                btn_ingresar.setEnabled(true);
                btn_ingresar.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Contraseña Incorrecta");
                txt_contrasena.selectAll();
                txt_contrasena.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_contrasenaKeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        frm_ver_ventas formulario = new frm_ver_ventas();
        c_varios.llamar_ventana(formulario);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txt_usuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_usuarioKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_usuarioKeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        frm_ver_clientes formulario = new frm_ver_clientes();
        c_varios.llamar_ventana(formulario);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        /*        Frame f = JOptionPane.getRootFrame();
        frm_ver_mis_productos dialog = new frm_ver_mis_productos(f, true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
         */

//ahora se abre un jinternalfram
        frm_ver_mis_productos2 formulario = new frm_ver_mis_productos2();
        c_varios.llamar_ventana(formulario);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        Frame f = JOptionPane.getRootFrame();
        frm_ver_kardex_diario dialog = new frm_ver_kardex_diario(f, true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        frm_ver_usuarios formulario = new frm_ver_usuarios();
        c_varios.llamar_ventana(formulario);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        frm_ver_traslados formulario = new frm_ver_traslados();
        c_varios.llamar_ventana(formulario);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        frm_ver_cobros ver_cobros = new frm_ver_cobros();
        c_varios.llamar_ventana(ver_cobros);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        frm_ver_proveedores ver_proveedores = new frm_ver_proveedores();
        c_varios.llamar_ventana(ver_proveedores);
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        frm_ver_productos_tiendas ver_productos = new frm_ver_productos_tiendas();
        c_varios.llamar_ventana(ver_productos);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        frm_ver_inventarios ver_inventario = new frm_ver_inventarios();
        c_varios.llamar_ventana(ver_inventario);
    }//GEN-LAST:event_jButton17ActionPerformed

    private void txt_montoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_montoKeyTyped
        c_varios.solo_precio(evt);
        c_varios.limitar_caracteres(evt, txt_monto, 11);
    }//GEN-LAST:event_txt_montoKeyTyped

    private void txt_montoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_montoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String texto = txt_monto.getText();
            if (c_varios.esDecimal(texto)) {
                btn_abrir_caja.setEnabled(true);
                btn_abrir_caja.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_montoKeyPressed

    private void btn_abrir_cajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_abrir_cajaActionPerformed
        c_caja.setFecha(c_varios.getFechaActual());
        c_caja.setId_almacen(c_almacen.getId());
        c_caja.setM_apertura(Double.parseDouble(txt_monto.getText()));
        boolean iniciar_caja = c_caja.insertar();
        if (iniciar_caja) {
            jd_apertura.setVisible(false);
        } else {
            txt_monto.requestFocus();
        }
    }//GEN-LAST:event_btn_abrir_cajaActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        frm_reg_movimiento_caja formulario = new frm_reg_movimiento_caja();
        c_varios.llamar_ventana(formulario);
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        Frame f = JOptionPane.getRootFrame();
        frm_reg_cierre_caja.origen = "cerrar";
        frm_reg_cierre_caja dialog = new frm_reg_cierre_caja(f, true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        frm_ver_guias_remision formulario = new frm_ver_guias_remision();
        c_varios.llamar_ventana(formulario);

    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        Frame f = JOptionPane.getRootFrame();
        rpt_ventas dialog = new rpt_ventas(f, true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        c_conectar.conectar();
        autoconectar();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        Frame f = JOptionPane.getRootFrame();
        rpt_mercaderia dialog = new rpt_mercaderia(f, true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        frm_ver_compras formulario = new frm_ver_compras();
        c_varios.llamar_ventana(formulario);
    }//GEN-LAST:event_jButton10ActionPerformed

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
            java.util.logging.Logger.getLogger(frm_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_abrir_caja;
    private javax.swing.JButton btn_cerrar;
    private javax.swing.JButton btn_ingresar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    public static javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator10;
    private javax.swing.JToolBar.Separator jSeparator11;
    private javax.swing.JToolBar.Separator jSeparator12;
    private javax.swing.JToolBar.Separator jSeparator13;
    private javax.swing.JToolBar.Separator jSeparator14;
    private javax.swing.JToolBar.Separator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JToolBar.Separator jSeparator8;
    private javax.swing.JToolBar.Separator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToolBar jToolBar3;
    private javax.swing.JToolBar jToolBar4;
    private javax.swing.JToolBar jToolBar5;
    private javax.swing.JToolBar jToolBar6;
    private javax.swing.JToolBar jToolBar7;
    private javax.swing.JDialog jd_apertura;
    private javax.swing.JDialog jd_formato;
    private javax.swing.JDialog jd_login;
    private javax.swing.JPanel jp_dias;
    private javax.swing.JPanel jp_meses;
    private javax.swing.JLabel lbl_empresa;
    private javax.swing.JLabel lbl_nom_tienda;
    public static javax.swing.JLabel lbl_traslados_encontrados;
    private javax.swing.JLabel lbl_usuario;
    private javax.swing.JPasswordField txt_contrasena;
    private javax.swing.JTextField txt_fecha;
    private javax.swing.JTextField txt_monto;
    private javax.swing.JTextField txt_tienda;
    private javax.swing.JTextField txt_usuario;
    // End of variables declaration//GEN-END:variables
}
