/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file,
choose Tools | Templates
 * and open the template in the editor.
 */
package pdfs;

import clases.cl_almacen;
import clases.cl_cliente;
import clases.cl_documento_firmado;
import clases.cl_documento_sunat;
import clases.cl_empresa;
import clases.cl_productos_ventas;
import clases.cl_usuario;
import clases.cl_varios;
import clases.cl_venta;
import clases_varios.leer_numeros;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.property.TextAlignment;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Desktop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dereck
 */
public class pdfComprobanteVenta {

    cl_venta venta = new cl_venta();
    cl_almacen almacen = new cl_almacen();
    cl_empresa empresa = new cl_empresa();
    cl_documento_sunat docsunat = new cl_documento_sunat();
    cl_cliente cliente = new cl_cliente();
    cl_productos_ventas detalle = new cl_productos_ventas();
    cl_documento_firmado hash = new cl_documento_firmado();
    cl_usuario usuario = new cl_usuario();

    leer_numeros leernumero = new leer_numeros();
    cl_varios varios = new cl_varios();

    private static String FILE_NAME = "itext.pdf";

    private ArrayList arrayproductos;

    private static final Font WhiteBold12Font = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, Color.WHITE);
    private static final Font WhiteBold9Font = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, Color.WHITE);
    private static final Font WhiteBold8Font = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, Color.WHITE);
    private static final Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);

    private static final Font BOLD9Font = new Font(Font.HELVETICA, 9, Font.BOLD);
    private static final Font NORMAL9Font = new Font(Font.HELVETICA, 9, Font.NORMAL);
    private static final Font blueFont = new Font(Font.HELVETICA, 9, Font.NORMAL, Color.RED);
    private static final Font smallBold = new Font(Font.HELVETICA, 8, Font.BOLD);

    public pdfComprobanteVenta(int id) {
        this.obtenerDatos(id);
    }

    private void obtenerDatos(int idventa) {
        venta.setId_venta(idventa);
        venta.validar_venta();

        almacen.setId(venta.getId_almacen());
        almacen.validar_almacen();

        empresa.setId(almacen.getEmpresa());
        empresa.validar_empresa();

        docsunat.setId(venta.getId_tido());
        docsunat.validar_documento();
        //System.out.println(venta.toString());

        cliente.setCodigo(venta.getId_cliente());
        cliente.comprobar_cliente();

        detalle.setId_venta(venta.getId_venta());
        arrayproductos = detalle.getArrayDetalle();

        usuario.setId_usuario(venta.getId_usuario());
        usuario.validar_usuario();

        hash.setId_venta(venta.getId_venta());
        hash.validar_firma();
        //  System.out.println(hash.toString());

        FILE_NAME = empresa.getRuc() + "-" + varios.ceros_izquieda_letras(2, docsunat.getCod_sunat() + "") + "-" + venta.getSerie() + "-" + venta.getNumero();
    }

    public void generarPDF() {

        try {
            Document document = new Document(PageSize.A5.rotate(), 20, 20, 20, 20);
            //System.out.println(PageSize.A5);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_NAME + ".pdf")));

            //open
            document.open();
            // We add metadata to PDF
            // Añadimos los metadatos del PDF
            document.addTitle("BOLETA DE VENTA ELECTRONICA B001 - 0000400");
            document.addSubject("SONO MUSIC IMPORT EIRL");
            document.addKeywords("Java, PDF, iText");
            document.addAuthor("LUNA SYSTEMS PERU - SOFTWARE DE INVENTARIO Y FACTURACION ELECTRONICA");
            document.addCreator("LUNA SYSTEMS PERU");
            // First page

            // We create the table (Creamos la tabla).
            PdfPTable tablatitulo = new PdfPTable(3);
            tablatitulo.setWidthPercentage(100);

            // Now we fill the PDF table
            //generar tabla de encazabeado de datos de guia
            float[] columnWidths = new float[]{18f, 45f, 37f};
            tablatitulo.setWidths(columnWidths);
            tablatitulo.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            // Ahora llenamos la tabla del PDF
            PdfPCell columnHeader = null;

            // Fill table rows (rellenamos las filas de la tabla).
            Image imagen = Image.getInstance("reports/logo.png");
            imagen.scaleToFit(80f, 80f);
            columnHeader = new PdfPCell(imagen);
            columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            columnHeader.setBorder(PdfPCell.NO_BORDER);
            columnHeader.setRowspan(3);
            tablatitulo.addCell(columnHeader);

            columnHeader = new PdfPCell(new Phrase(empresa.getRazon(), BOLD9Font));
            columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            columnHeader.setBorder(PdfPCell.NO_BORDER);
            tablatitulo.addCell(columnHeader);

            columnHeader = new PdfPCell(new Phrase("RUC: " + empresa.getRuc(), WhiteBold12Font));
            columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            columnHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
            columnHeader.setBackgroundColor(Color.red);
            columnHeader.setBorderColor(Color.red);
            tablatitulo.addCell(columnHeader);

            // Fill table rows (rellenamos las filas de la tabla).
            columnHeader = new PdfPCell(new Phrase(empresa.getDireccion(), NORMAL9Font));
            columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            columnHeader.setBorder(PdfPCell.NO_BORDER);
            tablatitulo.addCell(columnHeader);

            columnHeader = new PdfPCell(new Phrase(venta.getSerie() + "-" + venta.getNumero(), WhiteBold12Font));
            columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            columnHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
            columnHeader.setBackgroundColor(Color.red);
            columnHeader.setBorderColor(Color.red);
            tablatitulo.addCell(columnHeader);

            // Fill table rows (rellenamos las filas de la tabla).
            columnHeader = new PdfPCell(new Phrase("TIENDA: " + almacen.getDireccion(), NORMAL9Font));
            columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            columnHeader.setBorder(PdfPCell.NO_BORDER);
            tablatitulo.addCell(columnHeader);

            columnHeader = new PdfPCell(new Phrase(docsunat.getDescripcion() + " ELECTRONICA", WhiteBold12Font));
            columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            columnHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
            columnHeader.setBackgroundColor(Color.red);
            //columnHeader.setBorder(PdfPCell.NO_BORDER);
            columnHeader.setBorderColor(Color.red);
            tablatitulo.addCell(columnHeader);

            //paragraphMore.add(tablatitulo);
            //Paragraph paragraph = new Paragraph("", subcategoryFont);
            document.add(tablatitulo);
            Paragraph saltoDeLinea = new Paragraph(" ", paragraphFont);
            document.add(saltoDeLinea);

            PdfPTable tablaencabezado = new PdfPTable(1);
            tablaencabezado.setWidthPercentage(100);
            tablaencabezado.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

            tablaencabezado.addCell(new Paragraph("CLIENTE: " + cliente.getDocumento() + " - " + cliente.getNombre(), paragraphFont));
            tablaencabezado.addCell(new Paragraph("DIRECCION: " + cliente.getDireccion(), paragraphFont));

            document.add(tablaencabezado);

            tablaencabezado = new PdfPTable(2);
            tablaencabezado.setWidthPercentage(100);
            tablaencabezado.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            tablaencabezado.addCell(new Paragraph("Fecha Comprobante: " + venta.getFecha(), paragraphFont));
            tablaencabezado.addCell(new Paragraph("Vendedor: " + usuario.getUsername() + "(" + venta.getId_usuario() + ")", paragraphFont));

            //paragraphMore.add(tablaencabezado);
            // We add the paragraph with the table (Añadimos el elemento con la tabla).
            document.add(tablaencabezado);

            document.add(saltoDeLinea);

            PdfPTable tablaproductos = new PdfPTable(6);
            tablaproductos.setWidthPercentage(100);
            tablaproductos.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            //generar tabla de encazabeado de datos de guia
            columnWidths = new float[]{6f, 66f, 8f, 6f, 7f, 7f};
            tablaproductos.setWidths(columnWidths);
            // Ahora llenamos la tabla del PDF
            // Fill table rows (rellenamos las filas de la tabla).
            columnHeader = new PdfPCell(new Phrase("Cod", WhiteBold8Font));
            columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            columnHeader.setBackgroundColor(Color.RED);
            columnHeader.setBorder(PdfPCell.NO_BORDER);
            tablaproductos.addCell(columnHeader);

            columnHeader = new PdfPCell(new Phrase("Descripcion", WhiteBold8Font));
            columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            columnHeader.setBackgroundColor(Color.RED);
            columnHeader.setBorder(PdfPCell.NO_BORDER);
            tablaproductos.addCell(columnHeader);

            columnHeader = new PdfPCell(new Phrase("Und. Med.", WhiteBold8Font));
            columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            columnHeader.setBackgroundColor(Color.RED);
            columnHeader.setBorder(PdfPCell.NO_BORDER);
            tablaproductos.addCell(columnHeader);

            columnHeader = new PdfPCell(new Phrase("Cant.", WhiteBold8Font));
            columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            columnHeader.setBackgroundColor(Color.RED);
            columnHeader.setBorder(PdfPCell.NO_BORDER);
            tablaproductos.addCell(columnHeader);

            columnHeader = new PdfPCell(new Phrase("Precio", WhiteBold8Font));
            columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            columnHeader.setBackgroundColor(Color.RED);
            columnHeader.setBorder(PdfPCell.NO_BORDER);
            tablaproductos.addCell(columnHeader);

            columnHeader = new PdfPCell(new Phrase("Parcial", WhiteBold8Font));
            columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            columnHeader.setBackgroundColor(Color.RED);
            columnHeader.setBorder(PdfPCell.NO_BORDER);
            tablaproductos.addCell(columnHeader);

            //tablaproductos.setHeaderRows(1);
            Iterator<Object> it = arrayproductos.iterator();
            while (it.hasNext()) {
                Object[] objeto = (Object[]) it.next();
                System.out.println(objeto[0]);
                tablaproductos.addCell(new Paragraph(objeto[0].toString(), paragraphFont));
                tablaproductos.addCell(new Paragraph(objeto[1].toString(), paragraphFont));
                tablaproductos.addCell(new Paragraph(objeto[2].toString(), paragraphFont));

                columnHeader = new PdfPCell(new Phrase(objeto[3].toString(), paragraphFont));
                columnHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);
                columnHeader.setBorder(PdfPCell.NO_BORDER);
                tablaproductos.addCell(columnHeader);

                columnHeader = new PdfPCell(new Phrase(objeto[4].toString(), paragraphFont));
                columnHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);
                columnHeader.setBorder(PdfPCell.NO_BORDER);
                tablaproductos.addCell(columnHeader);

                columnHeader = new PdfPCell(new Phrase(objeto[5].toString(), paragraphFont));
                columnHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);
                columnHeader.setBorder(PdfPCell.NO_BORDER);
                tablaproductos.addCell(columnHeader);
            }

            //paragraphMore.add(tablaencabezado);
            // We add the paragraph with the table (Añadimos el elemento con la tabla).
            document.add(tablaproductos);
            document.add(saltoDeLinea);

            //FOOTER 
            PdfPTable tablafooter = new PdfPTable(4);
            tablafooter.setWidthPercentage(100);
            tablafooter.setTotalWidth(550);
            tablafooter.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            //generar tabla de encazabeado de datos de guia
            columnWidths = new float[]{15f, 65f, 13f, 7f};
            tablafooter.setWidths(columnWidths);

            Image imagenqr = Image.getInstance("reports/icono.jpg");
            imagenqr.scaleToFit(50f, 50f);
            //PdfPCell imagecell = new PdfPCell(imagenqr);
            PdfPCell imagecell = new PdfPCell(new Paragraph(""));
            //columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            imagecell.setBorder(PdfPCell.NO_BORDER);
            imagecell.setRowspan(5);
            tablafooter.addCell(imagecell);

            tablafooter.addCell(new Paragraph("Representacion impresa de la " + docsunat.getDescripcion() + " ELECTRONICA", paragraphFont));
            tablafooter.addCell(new Paragraph("Total Gravado:", paragraphFont));
            columnHeader = new PdfPCell(new Phrase(varios.formato_numero(venta.getTotal() / 1.18), paragraphFont));
            columnHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);
            columnHeader.setBorder(PdfPCell.NO_BORDER);
            tablafooter.addCell(columnHeader);

            tablafooter.addCell(new Paragraph("Hash: " + hash.getHash(), paragraphFont));
            tablafooter.addCell(new Paragraph("Total Inafecto:", paragraphFont));
            columnHeader = new PdfPCell(new Phrase("0.00", paragraphFont));
            columnHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);
            columnHeader.setBorder(PdfPCell.NO_BORDER);
            tablafooter.addCell(columnHeader);

            tablafooter.addCell(new Paragraph("Consulta tu comprobante en: comercialpenia.lunasystemsperu.com", paragraphFont));
            tablafooter.addCell(new Paragraph("Total IGV 18%:", paragraphFont));
            columnHeader = new PdfPCell(new Phrase(varios.formato_numero(venta.getTotal() / 1.18 * 0.18), paragraphFont));
            columnHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);
            columnHeader.setBorder(PdfPCell.NO_BORDER);
            tablafooter.addCell(columnHeader);

            tablafooter.addCell(new Paragraph(" ", paragraphFont));
            tablafooter.addCell(new Paragraph("ICBPER:", paragraphFont));
            columnHeader = new PdfPCell(new Phrase("0.00", paragraphFont));
            columnHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);
            columnHeader.setBorder(PdfPCell.NO_BORDER);
            tablafooter.addCell(columnHeader);

            tablafooter.addCell(new Paragraph("SON: " + leernumero.Convertir(venta.getTotal() + "", true) + " SOLES", paragraphFont));
            tablafooter.addCell(new Paragraph("Total:", paragraphFont));
            columnHeader = new PdfPCell(new Phrase(varios.formato_numero(venta.getTotal()), paragraphFont));
            columnHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);
            columnHeader.setBorder(PdfPCell.NO_BORDER);
            tablafooter.addCell(columnHeader);

            FooterTable event = new FooterTable(tablafooter);
            //document.add(tablafooter);
            writer.setPageEvent(event);

            Image imagenqr2 = null;
            try {
                imagenqr2 = Image.getInstance("https://lunasystemsperu.com/clientes/comercial_penia/tools/generateQR/temp/" + this.FILE_NAME + ".png");
            } catch (BadElementException | IOException e) {
                imagenqr2 = Image.getInstance("reports/logo.png");
            }

            imagenqr2.setAbsolutePosition(20, 20);
            imagenqr2.scaleToFit(80f, 80f);
            //imagenqr2.scaleAbsolute(80f, 80f);
            document.add(imagenqr2);

            //close
            document.close();
            System.out.println("Done");

            try {
                File path = new File(this.FILE_NAME + ".pdf");
                Desktop.getDesktop().open(path);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        } catch (DocumentException | IOException ex) {
            Logger.getLogger(pdfComprobanteVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public class FooterTable extends PdfPageEventHelper {

        protected PdfPTable footer;

        public FooterTable(PdfPTable footer) {
            this.footer = footer;
            //PdfPTable footer
        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            footer.writeSelectedRows(0, -1, document.leftMargin(), writer.getPageSize().getBottom(document.bottomMargin()) + 75, writer.getDirectContent());
        }
    }
}
