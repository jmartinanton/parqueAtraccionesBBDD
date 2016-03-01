/*
 * Menu pels coordinadors
 */
package vista;

import java.awt.*;
import javax.swing.*;

public class MenuCoordinadorVista {

    private JFrame frame;

    private JButton[] menuButtons = new JButton[3];

    private Integer amplada = 1200;
    private Integer alcada = 800;

    public MenuCoordinadorVista() {
        //Definició de la finestra del menú
        frame = new JFrame("Menú Coordinador");
        frame.setLayout(new GridLayout(0, 1));

        //Creació dels botons a la llista
        menuButtons[0] = new JButton("0. Sortir");
        menuButtons[1] = new JButton("1. Alta");
        menuButtons[2] = new JButton("2. Llista de coordinadors");

        //Afegir botons a la finestra
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
