/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author MiguelAngel
 */
public class ParcAtraccionsForm {

    private JFrame frame;
    private Integer amplada = 300;
    private Integer alcada = 200;

    private JLabel lCodi;
    private JTextField tCodi;
    private JLabel lNom;
    private JTextField tNom;
    private JLabel lAdreça;
    private JTextField tAdreça;

    private JButton bDesar;   
    private JButton bSortir;   

    public ParcAtraccionsForm() {
        //Definició de la finestra del menú
        frame = new JFrame("Formulari Parc Atraccions");
        frame.setLayout(new GridLayout(0, 1));

        //Creació dels controls del formulari
        lCodi = new JLabel("Codi");
        tCodi = new JTextField(20);
        lNom = new JLabel("Nom");
        tNom = new JTextField(20);
        lAdreça = new JLabel("Adreça");
        tAdreça = new JTextField(20);

        //Creació dels botons del formulari
        bDesar = new JButton("Desar");
        bSortir = new JButton("Sortir");

        //Addició del tot el formulari a la finestra
        frame.add(lCodi);
        frame.add(tCodi);
        frame.add(lNom);
        frame.add(tNom);
        frame.add(lAdreça);
        frame.add(tAdreça);
        frame.add(bDesar);       
        frame.add(bSortir);

        //Es mostra la finestra amb propietats per defecte
        frame.setSize(amplada, alcada);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public ParcAtraccionsForm(Integer codi, String nom, String adreça){
        this();
        tCodi.setText(codi.toString());
        tNom.setText(nom);
        tAdreça.setText(adreça);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JTextField getCodi() {
        return tCodi;
    }

    public void setCodi(JTextField tCodi) {
        this.tCodi = tCodi;
    }

    public JTextField getNom() {
        return tNom;
    }

    public void setNom(JTextField tNom) {
        this.tNom = tNom;
    }

    public JTextField getAdreca() {
        return tAdreça;
    }

    public void setAdreca(JTextField tAdreça) {
        this.tAdreça = tAdreça;
    }

    public JButton getDesar() {
        return bDesar;
    }

    public void setDesar(JButton bDesar) {
        this.bDesar = bDesar;
    }

    
    public JButton getSortir() {
        return bSortir;
    }

    public void setSortir(JButton bSortir) {
        this.bSortir = bSortir;
    }

}
