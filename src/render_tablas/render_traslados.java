/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render_tablas;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author lubricante
 */
public class render_traslados extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
        switch (String.valueOf(table.getValueAt(row, 7))) {
            case "CON OBSERVACIONES":
                setBackground(Color.green);
                setForeground(Color.black);
                break;
            case "ANULADO":
                setBackground(Color.black);
                setForeground(Color.white);
                break;
            case "PENDIENTE":
                setBackground(Color.yellow);
                setForeground(Color.black);
                break;
            case "BORRADOR":
                setBackground(Color.pink);
                setForeground(Color.black);
                break;
            default:
                setBackground(Color.white);
                setForeground(Color.black);
                break;
        }

        if (column == 0) {
            setHorizontalAlignment(SwingConstants.CENTER);
        }
        if (column == 1) {
            setHorizontalAlignment(SwingConstants.CENTER);
        }
        if (column == 2) {
            setHorizontalAlignment(SwingConstants.LEFT);
        }
        if (column == 3) {
            setHorizontalAlignment(SwingConstants.LEFT);
        }
        if (column == 4) {
            setHorizontalAlignment(SwingConstants.LEFT);
        }
        if (column == 5) {
            setHorizontalAlignment(SwingConstants.LEFT);
        }
        if (column == 6) {
            setHorizontalAlignment(SwingConstants.CENTER);
        }
        if (column == 7) {
            setHorizontalAlignment(SwingConstants.CENTER);
        }
        if (column == 8) {
            setHorizontalAlignment(SwingConstants.CENTER);
        }
        if (column == 9) {
            setHorizontalAlignment(SwingConstants.CENTER);
        }

        super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        return this;
    }

}
