package vista;

import java.awt.*;
import javax.swing.*;

public class MenuPrincipalVista {

    private JFrame frame;

    private JButton[] menuButtons = new JButton[3];

    private Integer amplada = 800;
    private Integer alcada = 600;

    public MenuPrincipalVista() {
        //Definició de la finestra del menú
        frame = new JFrame("Menú Principal");
        frame.setLayout(new GridLayout(0, 1));

        //Creació dels botons a la llista
        menuButtons[0] = new JButton("0. Sortir");
        menuButtons[1] = new JButton("1. Gestió parcs d'atraccions");
        menuButtons[2] = new JButton("2. Gestió coordinadors");

        //Addició dels botons a la finestra
        for (JButton unBoto : menuButtons) {
            frame.add(unBoto);
        }
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

    public JButton[] getMenuButtons() {
        return menuButtons;
    }

    public void setMenuButtons(JButton[] menuButtons) {
        this.menuButtons = menuButtons;
    }



}
