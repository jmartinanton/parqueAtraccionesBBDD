package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.*;

public class ControladorCoordinador implements ActionListener {

    private MenuCoordinadorVista menuCoordinadorsVista;
    private CoordinadorForm coordinadorForm = null;
    private CoordinadorLlista coordinadorLlista = null;
    private Integer opcioSeleccionada = 0;

    public ControladorCoordinador() {
        //Heu d'inizialitzar l'atribut menuCoordinadorsVista (això mostrarà el menú 
        //coordinador).
        //Heu d'afegir l'esdeveniment ActionListern a cada un dels botons del 
        //menú mitjançant el mètode afegirListenersMenu()d'aquesta classe.
    }

    private void afegirListenersMenu() {
        //Heu d'afegir l'esdeveniment ActionListener a cada un dels botons del 
        //menú de coordinadors.
    }

    private void afegirListenersForm() {
        //Heu d'afegir l'esdeveniment ActionListener a cada un dels botons (desar i sortir)
        //del formulari de coordinadors

    }

    private void afegirListenersLlista() {
        //Heu d'afegir l'esdeveniment ActionListener al boto sortir del llistat de
        //coordinadors.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Heu de soobrescriure el mètode actionPerformed perquè:
        // -Es cridar a bifurcaOpcio segons el botó seleccionat del menú coordinadors.
        //  Penseu que l'opció es correspon amb la posició que el botó ocupa a l'array 
        //  de botons de menuCoordinadorsVista.
        // -Si el botó premut és desar del formulari, es crea un nou coordinador amb les dades
        //  introduides per l'usuari i es guarda en el vector del parc d'atraccions
        //  registrat (parc d'atraccions actual)
        // -Si el botó premut és sortir, de la llista o formulari de coordinadors, 
        //  alehores heu de tornar al menú parc d'atraccions i ammagar la llista
        //  o el formulari, segons sigui la finestra oberta.
    }

    private void bifurcaOpcio(Integer opcio) {
        switch (opcio) {
            case 0: //sortir
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
