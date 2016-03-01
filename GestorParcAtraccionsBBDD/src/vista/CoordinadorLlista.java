/*
 *Llistat de coordinadors
 */
package vista;

import java.awt.*;
import javax.swing.*;

public class CoordinadorLlista {

    private JFrame frame;
    private Integer amplada = 600;
    private Integer alcada = 200;
    
    private JTable tCoordinador;

    private JButton bSortir;   
    

    public CoordinadorLlista() {
        
        
        //Definició de la finestra del menú
        frame = new JFrame("Formulari Coordinador");
        frame.setLayout(new GridLayout(0, 1));

        //Creació de la taula en base al model
        tCoordinador = new JTable(new CoordinadorTableModel());
        

        //Creació dels botons del formulari

        bSortir = new JButton("Sortir");

        //Afegim el formulari a la finestra
        frame.add(new JScrollPane(tCoordinador));        
        frame.add(bSortir);

        //Es mostra la finestra amb propietats per defecte
        frame.setSize(amplada, alcada);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }


    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JTable getCoordinador() {
        return tCoordinador;
    }

    public void setCoordinador(JTable tCoordinador) {
        this.tCoordinador = tCoordinador;
    }    
    
    public JButton getbSortir() {
        return bSortir;
    }

    public void setbSortir(JButton bSortir) {
        this.bSortir = bSortir;
    }

}
