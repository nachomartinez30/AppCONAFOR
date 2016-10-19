package gob.conafor.utils;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class Tablas {
    
     public void hideColumnTable(JTable tbl, int columna[]) {
        
        for (int i = 0; i < columna.length; i++) {
            
            tbl.getColumnModel().getColumn(columna[i]).setMaxWidth(0);
            tbl.getColumnModel().getColumn(columna[i]).setMinWidth(0);
            tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setMaxWidth(0);
            tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setMinWidth(0);
            
        }
    }
     
     public void ajustarCeldas(JTable tbl) {
         tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int column = 0; column < tbl.getColumnCount(); column++) {
            TableColumn tableColumn = tbl.getColumnModel().getColumn(column);
            int preferredWidth = tableColumn.getMinWidth();
            int maxWidth = tableColumn.getMaxWidth();

            for (int row = 0; row < tbl.getRowCount(); row++) {
                TableCellRenderer cellRenderer = tbl.getCellRenderer(row, column);
                Component c = tbl.prepareRenderer(cellRenderer, row, column);
                int width = c.getPreferredSize().width + tbl.getIntercellSpacing().width;
                preferredWidth = Math.max(preferredWidth, width);

        //  We've exceeded the maximum width, no need to check other rows
                if (preferredWidth >= maxWidth) {
                    preferredWidth = maxWidth;
                    break;
                }
            }

            tableColumn.setPreferredWidth(preferredWidth);
        }
    }
     
    public void ajustarColumnas(JTable tbl, int[] anchos) {

        TableColumnModel tcm = tbl.getColumnModel();
        /*for(int i = 0; i < tbl.getColumnCount(); i++){
         tbl.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
         }*/
        for (int i = 0; i < tcm.getColumnCount(); i++) {
            tcm.getColumn(i).setPreferredWidth(200);
        }
    }
    
    
   
}
