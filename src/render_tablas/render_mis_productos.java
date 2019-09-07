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
public class render_mis_productos extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
        if (Integer.parseInt(table.getValueAt(row, 3).toString()) <= 0) {
            setBackground(Color.red);
            setForeground(Color.white);
        } else {
            String fsalida = String.valueOf(table.getValueAt(row, 7));
            if (fsalida.equals("-")) {
                setBackground(Color.green);
                setForeground(Color.black);
            } else {
                setBackground(Color.white);
                setForeground(Color.black);
            }
        }

        if (column == 0) {
            setHorizontalAlignment(SwingConstants.CENTER);
        }
        if (column == 1) {
            setHorizontalAlignment(SwingConstants.LEFT);
        }
        if (column == 2) {
            setHorizontalAlignment(SwingConstants.CENTER);
        }
        if (column == 3) {
            setHorizontalAlignment(SwingConstants.RIGHT);
        }
        if (column == 4) {
            setHorizontalAlignment(SwingConstants.RIGHT);
        }
        if (column == 5) {
            setHorizontalAlignment(SwingConstants.RIGHT);
        }
        if (column == 6) {
            setHorizontalAlignment(SwingConstants.CENTER);
        }
        if (column == 7) {
            setHorizontalAlignment(SwingConstants.CENTER);
        }

        super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        return this;
    }

}
