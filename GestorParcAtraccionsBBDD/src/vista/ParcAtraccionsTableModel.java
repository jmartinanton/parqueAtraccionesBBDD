package vista;

import model.ParcAtraccions;
import controlador.ControladorPrincipal;
import javax.swing.table.AbstractTableModel;

public class ParcAtraccionsTableModel extends AbstractTableModel {
    //Per una taula de 3 files * 4 columnes

    private final String[] columnNames = {"Codi", "Nom", "Adreça"};

    String[][] data = new String[ControladorPrincipal.getMAXPARCSATRACCIONS()][3];

    public ParcAtraccionsTableModel() {
        int i = 0;
        for (ParcAtraccions parcAtraccions : ControladorPrincipal.getParcsAtraccions()) {
            if (parcAtraccions != null) {
                data[i][0] = parcAtraccions.getCodi().toString();
                data[i][1] = parcAtraccions.getNom();
                data[i][2] = parcAtraccions.getAdreca();
                i++;
            }
        }
    }


    public int getRowCount() {
        return data.length;
    }


    public int getColumnCount() {
        return data[0].length;
    }


    public String getColumnName(int col) {
        return columnNames[col].toString();
    }

   
    public Object getValueAt(int row, int column) {
        return data[row][column];
    }

}
