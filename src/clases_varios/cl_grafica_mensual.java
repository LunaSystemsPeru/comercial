/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases_varios;

import clases.cl_cobros_ventas;
import clases.cl_reportes_graficas;
import clases.cl_venta;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import comercial.frm_principal;

/**
 *
 * @author luis
 */
public class cl_grafica_mensual {

    cl_venta c_venta = new cl_venta();
    cl_cobros_ventas c_cobros = new cl_cobros_ventas();
    cl_reportes_graficas c_graficas = new cl_reportes_graficas();
    
    int id_almacen = frm_principal.c_almacen.getId();

    public void llenar_series_diarias(JPanel panel) {

        XYSeries series = new XYSeries("S/");
        c_venta.setId_almacen(id_almacen);
        Integer[] valor_x = c_venta.ventas_diaras();
        //System.out.println("canidad_items" + valor_x.length);

        for (int i = 1; i < valor_x.length; i++) {
         //   System.out.println("valor de i =" + i +  " es = " + valor_x[i]+ "\n"); 
            int valor = 0;
            if (valor_x[i] != null) {
                valor = valor_x[i];
            }
            series.add(i, valor);
            //System.out.println(i + " valor " + valor);
        }

// Add the series to your data set
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

// Generate the graph
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Ventas del Mes", // Title
                "Dias", // x-axis Label
                "Total S/", // y-axis Label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Plot Orientation
                true, // Show Legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
        );

        ChartPanel chartPanel = new ChartPanel((JFreeChart) null);
        chartPanel.setChart(chart);
        chartPanel.setBounds(0, 0, 419, 309);
        panel.add(chartPanel);
        panel.repaint();
    }

    public void llenar_series_mensuales(JPanel panel) {

        XYSeries series = new XYSeries("S/");
        c_venta.setId_almacen(id_almacen);
        Integer[] valor_x = c_venta.ventas_mensuales();
        // System.out.println("canidad_items" + valor_x.length);

        for (int i = 1; i < valor_x.length; i++) {
            int valor = 0;
            if (valor_x[i] != null) {
                valor = valor_x[i];
            }
            series.add(i, valor);
            //System.out.println(i + " valor " + valor);
        }

// Add the series to your data set
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

// Generate the graph
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Ventas del Año", // Title
                "Meses", // x-axis Label
                "Total S/", // y-axis Label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Plot Orientation
                true, // Show Legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
        );

        ChartPanel chartPanel = new ChartPanel((JFreeChart) null);
        chartPanel.setChart(chart);
        chartPanel.setBounds(0, 0, 419, 309);
        panel.add(chartPanel);
        panel.repaint();
    }

    public void ver_ventas_cobros(JPanel panel) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        c_graficas.setId_almacen(id_almacen);
        ResultSet rs = c_graficas.ver_ventas_cobros();
        
        try {
            while (rs.next()) {
                dataset.addValue(rs.getDouble("total_venta"), "Venta", rs.getString("dia"));
                dataset.addValue(rs.getDouble("total_ingreso"), "Cobros", rs.getString("dia"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Ventas vs Cobros - Ultimos 7 dias",
                "Fecha",
                "Sumas totales",
                dataset,
                PlotOrientation.HORIZONTAL,
                true, 
                true, 
                false
        );

        ChartPanel chartPanel = new ChartPanel((JFreeChart) null);
        chartPanel.setChart(barChart);
        chartPanel.setBounds(0, 0, 600, 400);
        panel.removeAll();
        panel.add(chartPanel);
        panel.repaint();
    }
    
    public void ver_ingresos_mensuales (JPanel panel) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        c_graficas.setId_almacen(id_almacen);
        ResultSet rs = c_graficas.ver_cobros_mensuales();
        
        try {
            while (rs.next()) {
                dataset.addValue(rs.getDouble("monto"), "Venta", rs.getString("nombre"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Ingresos Mensuales - Mi tienda",
                "Mes",
                "Monto",
                dataset,
                PlotOrientation.VERTICAL,
                true, 
                true, 
                false
        );

        ChartPanel chartPanel = new ChartPanel((JFreeChart) null);
        chartPanel.setChart(barChart);
        chartPanel.setBounds(0, 0, 600, 400);
        panel.removeAll();
        panel.add(chartPanel);
        panel.repaint();
    }
    
    public void ver_ingresos_anuales(JPanel panel) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        c_graficas.setId_almacen(id_almacen);
        ResultSet rs = c_graficas.ver_cobros_anuales();
        
        try {
            while (rs.next()) {
                dataset.addValue(rs.getDouble("monto"), "Venta", rs.getString("anio"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Ingresos Anuales - Mi tienda",
                "Año",
                "Monto",
                dataset,
                PlotOrientation.VERTICAL,
                true, 
                true, 
                false
        );

        ChartPanel chartPanel = new ChartPanel((JFreeChart) null);
        chartPanel.setChart(barChart);
        chartPanel.setBounds(0, 0, 600, 400);
        panel.removeAll();
        panel.add(chartPanel);
        panel.repaint();
    }
    
    public void ver_ingresos_ayer_hoy(JPanel panel) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        ResultSet rs_hoy = c_graficas.ver_ventas_hoy();
        
        try {
            while (rs_hoy.next()) {
                dataset.addValue(rs_hoy.getDouble("monto"), "Hoy (" + rs_hoy.getString("fecha") + ")" , rs_hoy.getString("nombre"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        
        ResultSet rs_ayer = c_graficas.ver_ventas_ayer();
        
        try {
            while (rs_ayer.next()) {
                dataset.addValue(rs_ayer.getDouble("monto"), "Ayer (" + rs_ayer.getString("fecha") + ")", rs_ayer.getString("nombre"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Comparativa Ingresos Ayer y Hoy",
                "Tienda",
                "Monto",
                dataset,
                PlotOrientation.VERTICAL,
                true, 
                true, 
                false
        );

        ChartPanel chartPanel = new ChartPanel((JFreeChart) null);
        chartPanel.setChart(barChart);
        chartPanel.setBounds(0, 0, 600, 400);
        panel.removeAll();
        panel.add(chartPanel);
        panel.repaint();
    }
    
    public void ver_ingresos_tienda_mes(JPanel panel) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        ResultSet rs = c_graficas.ver_ingresos_tienda_mes();
        
        try {
            while (rs.next()) {
                dataset.addValue(rs.getDouble("monto"), "Ingresos"  , rs.getString("tienda"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        

        JFreeChart barChart = ChartFactory.createBarChart(
                "Comparativa Ingresos - este mes",
                "Tienda",
                "Monto",
                dataset,
                PlotOrientation.VERTICAL,
                true, 
                true, 
                false
        );

        ChartPanel chartPanel = new ChartPanel((JFreeChart) null);
        chartPanel.setChart(barChart);
        chartPanel.setBounds(0, 0, 600, 400);
        panel.removeAll();
        panel.add(chartPanel);
        panel.repaint();
    }
    
    public void ver_ingresos_tienda_anio(JPanel panel) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        ResultSet rs = c_graficas.ver_ingresos_tienda_anio();
        
        try {
            while (rs.next()) {
                dataset.addValue(rs.getDouble("monto"), "Ingresos"  , rs.getString("tienda"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        

        JFreeChart barChart = ChartFactory.createBarChart(
                "Comparativa Ingresos - este Año",
                "Tienda",
                "Monto",
                dataset,
                PlotOrientation.VERTICAL,
                true, 
                true, 
                false
        );

        ChartPanel chartPanel = new ChartPanel((JFreeChart) null);
        chartPanel.setChart(barChart);
        chartPanel.setBounds(0, 0, 600, 400);
        panel.removeAll();
        panel.add(chartPanel);
        panel.repaint();
    }

}
