package vista;

import java.awt.*;
import javax.swing.*;

public class ParcAtraccionsLlista {

    private JFrame frame;
    private Integer amplada = 600;
    private Integer alcada = 200;
    
    private JTable taulaParcAtraccions;

    private JButton bSortir;   
    

    public ParcAtraccionsLlista() {
        //Definició de la finestra del menú
        frame = new JFrame("Llistat Parc Atraccions");
        frame.setLayout(new GridLayout(0, 1));

        //Creació de la taula en base al model
        taulaParcAtraccions = new JTable(new ParcAtraccionsTableModel());
        

        //Creació dels botons del formulari

        bSortir = new JButton("Sortir");

        //Addició del tot el formulari a la finestra
        frame.add(new JScrollPane(taulaParcAtraccions));        
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

    public JTable getTaulaParcAtraccions() {
        return taulaParcAtraccions;
    }

    public void setTaulaParcAtraccions(JTable pTaulaParcAtraccions) {
        taulaParcAtraccions = pTaulaParcAtraccions;
    }    
    
    public JButton getSortir() {
        return bSortir;
    }

    public void setSortir(JButton bSortir) {
        this.bSortir = bSortir;
    }

}
