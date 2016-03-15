/*
 * Formulari pels coordinadors
 */
package vista;

import java.awt.*;
import javax.swing.*;

public class CoordinadorForm {

    private JFrame frame;
    private JPanel panelfu; //Panel per les dades del coordinador
    private Integer amplada = 300;
    private Integer alcada = 200;

    private JLabel lNif;
    private JTextField tNif;
    private JLabel lNom;
    private JTextField tNom;
    private JLabel lCognom;
    private JTextField tCognom;

    private JButton bDesar;
    private JButton bSortir;

    public CoordinadorForm() {
        //Definició de la finestra del menú
        frame = new JFrame("Formulari Coordinador");
        panelfu = new JPanel();
        panelfu.setLayout(new GridLayout(0, 1));

        //Creació dels controls del formulari
        lNif = new JLabel("Nif");
        tNif = new JTextField(20);
        lNom = new JLabel("Nom");
        tNom = new JTextField(20);
        lCognom = new JLabel("Cognom");
        tCognom = new JTextField(20);

        //Creació dels botons del formulari
        bDesar = new JButton("Desar");
        bSortir = new JButton("Sortir");

        //Afegim el formulari a la finestra
        panelfu.add(lNif);
        panelfu.add(tNif);
        panelfu.add(lNom);
        panelfu.add(tNom);
        panelfu.add(lCognom);
        panelfu.add(tCognom);
        panelfu.add(bDesar);
        panelfu.add(bSortir);
        frame.add(panelfu, BorderLayout.WEST);

        //Es mostra la finestra amb propietats per defecte
        frame.setSize(amplada, alcada);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public CoordinadorForm(String nif, String nom, String cognom) {
        //this();
        tNif.setText(nif);
        tNom.setText(nom);
        tCognom.setText(cognom);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JPanel getPanelfu() {
        return panelfu;
    }

    public void setPanelfu(JPanel panelfu) {
        this.panelfu = panelfu;
    }

    public JTextField gettNif() {
        return tNif;
    }

    public void settNif(JTextField tNif) {
        this.tNif = tNif;
    }

    public JTextField gettNom() {
        return tNom;
    }

    public void settNom(JTextField tNom) {
        this.tNom = tNom;
    }

    public JTextField gettCognom() {
        return tCognom;
    }

    public void settCognom(JTextField tCognom) {
        this.tCognom = tCognom;
    }

    public JButton getbDesar() {
        return bDesar;
    }

    public void setbDesar(JButton bDesar) {
        this.bDesar = bDesar;
    }

    public JButton getbSortir() {
        return bSortir;
    }

    public void setbSortir(JButton bSortir) {
        this.bSortir = bSortir;
    }
}
