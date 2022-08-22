/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.awt.AWTException;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import nicon.notify.core.Notification;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellRange;
import static org.apache.poi.ss.usermodel.CellType.NUMERIC;
import org.apache.poi.ss.util.CellRangeAddress;
import utils.Notificador;

/**
 *
 * @author luisog
 */
public class cl_reporte_ventas {

    cl_conectar conectar = new cl_conectar();
    cl_varios varios = new cl_varios();
    Notificador NotifyI = new Notificador();

    private String fec_inicio;
    private String fec_fin;

    public cl_reporte_ventas() {
    }

    public String getFec_inicio() {
        return fec_inicio;
    }

    public void setFec_inicio(String fec_inicio) {
        this.fec_inicio = fec_inicio;
    }

    public String getFec_fin() {
        return fec_fin;
    }

    public void setFec_fin(String fec_fin) {
        this.fec_fin = fec_fin;
    }

    public void generarXLSVentas() {
        String[] titulos;

        titulos = new String[10];
        titulos[0] = "Item";
        titulos[1] = "Fecha";
        titulos[2] = "Comprobante";
        titulos[3] = "Cliente";
        titulos[4] = "Sub Total";
        titulos[5] = "IGV";
        titulos[6] = "Total";
        titulos[7] = "Estado Doc";
        titulos[8] = "Enviado SUNAT";
        titulos[9] = "ID";

        String query = "select v.id_ventas, v.fecha, v.numero, v.serie, ds.abreviado, c.documento, c.nombre, v.total, v.estado, v.enviado_sunat "
                + "from ventas as v "
                + "inner join clientes c on v.id_cliente = c.id_cliente "
                + "inner join documentos_sunat ds on v.id_tido = ds.id_tido "
                + "where v.fecha between '" + this.fec_inicio + "' and '" + this.fec_fin + "' "
                + "order by v.fecha asc, v.numero asc";

        Statement st = conectar.conexion();
        ResultSet rs = conectar.consulta(st, query);
        //ArrayList listafilas = new ArrayList();
        ArrayList<Object> listafilas = new ArrayList<>();
        try {
            int nroitems = 1;

            while (rs.next()) {
                Object objectfila[] = new Object[10];

                int iestado = rs.getInt("estado");
                int ienviado = rs.getInt("enviado_sunat");
                String estado = "";
                String enviado = null;
                double total = rs.getDouble("total");

                if (iestado == 1) {
                    estado = "ACTIVO";
                }
                if (iestado == 3) {
                    estado = "ANULADO";
                    total = 0.00;
                }

                if (ienviado == 0) {
                    enviado = "Falta Enviar a SUNAT";
                }
                if (ienviado == 1) {
                    enviado = "Enviado SUNAT";
                }

                objectfila[0] = nroitems;
                nroitems++;
                objectfila[1] = varios.fecha_usuario(rs.getString("fecha"));
                objectfila[2] = rs.getString("abreviado") + " | " + rs.getString("serie") + "-" + rs.getString("numero");
                objectfila[3] = rs.getString("documento") + " " + rs.getString("nombre");
                objectfila[4] = total / 1.18;
                objectfila[5] = total / 1.18 * 0.18;
                objectfila[6] = total;
                objectfila[7] = estado;
                objectfila[8] = enviado;
                objectfila[9] = rs.getInt("id_ventas");

                listafilas.add(objectfila);
                //System.out.println(Arrays.toString(objectfila));
            }
            conectar.cerrar(st);
            conectar.cerrar(rs);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }

        //   System.out.println(guardarComo());
        // Creamos el archivo donde almacenaremos la hoja
        // de calculo, recuerde usar la extension correcta,
        // en este caso .xlsx
        String nombre_reporte = "xls_reporte_ventas_" + fec_inicio + "_al_" + fec_fin + ".xls";
        String dir_archivo = varios.obtenerCarpetaGuardar(nombre_reporte);
        File archivo = new File(dir_archivo);

        System.out.println(dir_archivo);

        //generar xls
        // Creamos el libro de trabajo de Excel formato OOXML
        HSSFWorkbook workbook = new HSSFWorkbook();

        // La hoja donde pondremos los datos
        HSSFSheet pagina = workbook.createSheet("Resumen");

        // Creamos una fila en la hoja en la posicion 0
        HSSFRow titulogeneral = pagina.createRow(0);
        setMergeRegion(0, 0, 9, pagina);
        HSSFCell celdaencabezado = titulogeneral.createCell(0);
        celdaencabezado.setCellValue("REPORTE DE VENTAS");

        HSSFRow fila3 = pagina.createRow(2);
        SetValorCelda(fila3, 1, "Desde:");
        SetValorCelda(fila3, 2, fec_inicio);

        setMergeRegion(2, 4, 9, pagina);
        SetValorCelda(fila3, 4, "RUC: 10444441629");

        HSSFRow fila4 = pagina.createRow(3);
        SetValorCelda(fila4, 1, "Hasta:");
        SetValorCelda(fila4, 2, fec_fin);

        setMergeRegion(3, 4, 9, pagina);
        SetValorCelda(fila4, 4, "RAZON SOCIAL: PEÑA SANCHEZ JOHNNY AQUILES");

        // Creamos una fila en la hoja en la posicion 0
        HSSFRow fila = pagina.createRow(5);
        //System.out.println(titulos.length + " total columnas");

        //ADAPTAMOS EL ANCHO DE COLUMNA
        pagina.setColumnWidth(0, 1200);
        pagina.setColumnWidth(1, 3000);
        pagina.setColumnWidth(2, 4500);
        pagina.setColumnWidth(3, 15000);
        pagina.setColumnWidth(4, 3000);
        pagina.setColumnWidth(5, 3000);
        pagina.setColumnWidth(6, 3000);
        pagina.setColumnWidth(7, 3000);
        pagina.setColumnWidth(8, 5000);
        pagina.setColumnWidth(9, 1500);

        // Creamos el encabezado
        for (int i = 0; i < titulos.length; i++) {
            // Creamos una celda en esa fila, en la posicion 
            // indicada por el contador del ciclo
            HSSFCell celda = fila.createCell(i);

            // Indicamos el estilo que deseamos 
            // usar en la celda, en este caso el unico 
            // que hemos creado
            celda.setCellValue(titulos[i]);
        }

        //se hace el recorrido de la base de datos para cargar lo vaores en las celdas
        //fomartos de texto para cada celda tipo monto y hora
        HSSFCellStyle stylemonto = workbook.createCellStyle();
        // style.setDataFormat(HSSFDataFormat.getBuiltinFormat("###0.00"));
        stylemonto.setDataFormat(HSSFDataFormat.getBuiltinFormat("_(* #,##0.00_);_(* (#,##0.00);_(* \"-\"??_);_(@_)"));

        HSSFCellStyle stylehora = workbook.createCellStyle();
        // style.setDataFormat(HSSFDataFormat.getBuiltinFormat("###0.00"));
        stylehora.setDataFormat(HSSFDataFormat.getBuiltinFormat("[HH]:mm"));

        int filanro = 6;
        double sumabase = 0;
        double sumaigv = 0;
        double sumatotal = 0;
        //    System.out.println(listafilas.size());
        for (int i = 0; i < listafilas.size(); i++) {

            // Ahora creamos una fila en la posicion 1
            fila = pagina.createRow(filanro);
            // Y colocamos los datos en esa fila
            Object filita[] = (Object[]) listafilas.get(i);
            //System.out.println(Arrays.toString(filita));
            sumabase = sumabase + Double.parseDouble(filita[4] + "");
            sumaigv = sumaigv + Double.parseDouble(filita[5] + "");
            sumatotal = sumatotal + Double.parseDouble(filita[6] + "");

            for (int j = 0; j < 10; j++) {
                // Creamos una celda en esa fila, en la
                // posicion indicada por el contador del ciclo
                HSSFCell celda = fila.createCell(j);

                if (j < 4) {
                    celda.setCellValue(filita[j] + "");
                }

                if (j > 3 && j < 7) {
                    String valorfila = filita[j] + "";
                    double dvalor = Double.parseDouble(valorfila);
                    celda.setCellValue(dvalor);
                    celda.setCellStyle(stylemonto);
                    celda.setCellType(NUMERIC);
                }

                if (j > 6) {
                    celda.setCellValue(filita[j] + "");
                }

            }

            filanro++;
        }

        //colocar totales
        fila = pagina.createRow(filanro);
        HSSFCell celda = fila.createCell(4);
        celda.setCellValue(sumabase);
        celda.setCellStyle(stylemonto);
        celda.setCellType(NUMERIC);

        celda = fila.createCell(5);
        celda.setCellValue(sumaigv);
        celda.setCellStyle(stylemonto);
        celda.setCellType(NUMERIC);

        celda = fila.createCell(6);
        celda.setCellValue(sumatotal);
        celda.setCellStyle(stylemonto);
        celda.setCellType(NUMERIC);

        // Ahora guardaremos el archivo
        try {
            FileOutputStream salida = new FileOutputStream(archivo);
            workbook.write(salida);
            salida.close();

            System.out.println("Archivo creado existosamente en " + archivo.getAbsolutePath());
            //Notification.show("Creado", "Archivo creado existosamente en " + archivo.getAbsolutePath());

            NotifyI.displayTray("Creado", "Archivo creado existosamente en " + archivo.getAbsolutePath());

            Desktop.getDesktop().open(new File(archivo.getAbsolutePath()));
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getLocalizedMessage());
            System.out.println("Archivo no localizable en sistema de archivos");
            JOptionPane.showMessageDialog(null, "ERROR AL GENERAR EL ARCHIVO \n" + ex.getLocalizedMessage());
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
            System.out.println("Error de entrada/salida");
            JOptionPane.showMessageDialog(null, "Error de entrada/salida \n" + ex.getLocalizedMessage());
        } catch (AWTException ex) {
            System.out.println(ex.getLocalizedMessage());
        }

    }

    private void SetValorCelda(HSSFRow filax, int col, String texto) {
        HSSFCell celdax = filax.createCell(col);
        celdax.setCellValue(texto);
    }

    private void setMergeRegion(int row, int coli, int colf, HSSFSheet pagina) {
        CellRangeAddress regiontitulo = new CellRangeAddress(row, row, coli, colf);
        pagina.addMergedRegion(regiontitulo);
    }
}
