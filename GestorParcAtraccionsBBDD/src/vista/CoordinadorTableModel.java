/*
 * Taula pels coordinadors
 */
package vista;

import model.Coordinador;
import controlador.ControladorPrincipal;
import javax.swing.table.AbstractTableModel;
import principal.Element;

public class CoordinadorTableModel extends AbstractTableModel {
    //Per una taula de 3 files * 4 columnes

    private final String[] nomColumnes = {"Nif", "Nom", "Cognom"};

    String[][] valors = new String[10][3];

    public CoordinadorTableModel() {
        int i = 0;
        for (Element element : ControladorPrincipal.getParcAtraccionsActual().getElements()) {
            if (element instanceof Coordinador) {
                Coordinador coordinador = (Coordinador) element;
                valors[i][0] = coordinador.getNif();
                valors[i][1] = coordinador.getNom();
                valors[i][2] = coordinador.getCognom();
                i++;
            }

        }
    }

    @Override
    public int getRowCount() {
        return valors.length;
    }

    @Override
    public int getColumnCount() {
        return valors[0].length;
    }

    @Override
    public String getColumnName(int col) {
        return nomColumnes[col];
    }

    @Override
    public Object getValueAt(int row, int column) {
        return valors[row][column];
    }

}
