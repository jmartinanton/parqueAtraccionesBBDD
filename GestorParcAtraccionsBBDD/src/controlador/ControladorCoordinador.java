package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import model.Coordinador;
import model.ParcAtraccions;
import vista.*;

public class ControladorCoordinador implements ActionListener {

    private MenuCoordinadorVista menuCoordinadorsVista;
    private CoordinadorForm coordinadorForm = null;
    private CoordinadorLlista coordinadorLlista = null;
    private Integer opcioSeleccionada = 0;

    public ControladorCoordinador() {
           menuCoordinadorsVista = new MenuCoordinadorVista();
           afegirListenersMenu();
        //Heu d'inizialitzar l'atribut menuCoordinadorsVista (això mostrarà el menú 
        //coordinador).
        //Heu d'afegir l'esdeveniment ActionListern a cada un dels botons del 
        //menú mitjançant el mètode afegirListenersMenu()d'aquesta classe.
    }

    private void afegirListenersMenu() {
        for (JButton menuButton : menuCoordinadorsVista.getMenuButtons()) {
            menuButton.addActionListener(this);
        }
        //Heu d'afegir l'esdeveniment ActionListener a cada un dels botons del 
        //menú de coordinadors.
    }

    private void afegirListenersForm() {
        coordinadorForm.getbDesar().addActionListener(this);
        coordinadorForm.getbSortir().addActionListener(this);
        
        //Heu d'afegir l'esdeveniment ActionListener a cada un dels botons (desar i sortir)
        //del formulari de coordinadors

    }

    private void afegirListenersLlista() {
        coordinadorLlista.getbSortir().addActionListener(this);

        //Heu d'afegir l'esdeveniment ActionListener al boto sortir del llistat de
        //coordinadors.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton botonpresionado = (JButton) e.getSource();
        
        JFrame formOrigenBoton = (JFrame) botonpresionado.getRootPane().getParent();
        if (menuCoordinadorsVista != null && formOrigenBoton == menuCoordinadorsVista.getFrame()) {
            JButton[] listaBotones = menuCoordinadorsVista.getMenuButtons();
                    for (int i = 0; i < listaBotones.length; i++) {
            if (listaBotones[i] == botonpresionado) { 
                opcioSeleccionada = i;
                bifurcaOpcio(opcioSeleccionada);
            }
        }
        } else if (coordinadorForm != null && formOrigenBoton == coordinadorForm.getFrame()) {
            if (botonpresionado == coordinadorForm.getbDesar()){
                ParcAtraccions pActual = ControladorPrincipal.getParcAtraccionsActual();
                Coordinador coo = new Coordinador(coordinadorForm.gettNif().getText(), 
                                                   coordinadorForm.gettNom().getText(), 
                                                    coordinadorForm.gettCognom().getText());
                pActual.nouCoordinador(coo);
            } else if (botonpresionado == coordinadorForm.getbSortir()){
                coordinadorForm.getFrame().setVisible(false);
            }
        } else if (coordinadorLlista != null && formOrigenBoton == coordinadorLlista.getFrame()) {
            if (botonpresionado == coordinadorLlista.getbSortir()){
                coordinadorLlista.getFrame().setVisible(false);
            }       
        }

        
        //Heu de soobrescriure el mètode actionPerformed perquè:
        // *-Es cridar a bifurcaOpcio segons el botó seleccionat del menú coordinadors.
        // * Penseu que l'opció es correspon amb la posició que el botó ocupa a l'array 
        // * de botons de menuCoordinadorsVista.
        // *-Si el botó premut és desar del formulari, es crea un nou coordinador amb les dades
        // * introduides per l'usuari i es guarda en el vector del parc d'atraccions
        // * registrat (parc d'atraccions actual)
        // -Si el botó premut és sortir, de la llista o formulari de coordinadors, 
        //  alehores heu de tornar al menú parc d'atraccions i ammagar la llista
        //  o el formulari, segons sigui la finestra oberta.
    }

    private void bifurcaOpcio(Integer opcio) {
        switch (opcio) {
            case 0: //sortir
                menuCoordinadorsVista.getFrame().setVisible(false);
                ControladorPrincipal.getMenuPrincipalVista().getFrame().setVisible(true);
                break;
            case 1: // alta
                coordinadorForm = new CoordinadorForm();
                afegirListenersForm();
                break;
            case 2: // llista
                coordinadorLlista = new CoordinadorLlista();
                afegirListenersLlista();
                break;
        }
    }
}
