package controlador;

import persistencia.GestorPersistencia;
import vista.*;
import java.awt.event.*;
import javax.swing.*;
import model.ParcAtraccions;
import principal.ParcAtraccionsExcepcio;

public class ControladorParcAtraccions implements ActionListener {

    private MenuParcAtraccionsVista menuParcAtraccionsVista;
    private ParcAtraccionsForm parcAtraccionsForm = null;
    private ParcAtraccionsLlista parcAtraccionsLlista = null;
    private Integer opcioSeleccionada = 0;

    public ControladorParcAtraccions() {
        menuParcAtraccionsVista = new MenuParcAtraccionsVista();
        afegirListenersMenu();
    }

    //El controlador com a listener dels controls de les finestres que gestionen els parcs d'atraccions
    //S'AFEGEIX EL CONTROLADOR COM A LISTENER DELS BOTONS DEL MENU
    private void afegirListenersMenu() {
        for (JButton unBoto : menuParcAtraccionsVista.getMenuButtons()) {
            unBoto.addActionListener(this);
        }
    }

    //S'AFEGEIX EL CONTROLADOR COM A LISTENER DELS BOTONS DEL FORMULARI
    private void afegirListenersForm() {
        parcAtraccionsForm.getDesar().addActionListener(this);
        parcAtraccionsForm.getSortir().addActionListener(this);
    }

    //S'AFEGEIX EL CONTROLADOR COM A LISTENER DEL BOTO DE LA LLISTA
    private void afegirListenersLlista() {
        parcAtraccionsLlista.getSortir().addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        //Accions per al menú Parc d'atraccions
        JButton[] elsBotons = menuParcAtraccionsVista.getMenuButtons();
        for (Integer i = 0; i < elsBotons.length; i++) {
            if (e.getSource() == elsBotons[i]) {
                menuParcAtraccionsVista.getFrame().setVisible(false);
                opcioSeleccionada = i;
                bifurcaOpcio(i);
            }
        }
        //Accions per al formulari Parc d'atraccions
        if (parcAtraccionsForm != null) {
            if (e.getSource() == parcAtraccionsForm.getDesar()) {
                if (opcioSeleccionada == 1) {// Si nou parc d'atraccions ...
                    if (validaParcAtraccions()) {
                        ParcAtraccions granja = new ParcAtraccions(Integer.parseInt(parcAtraccionsForm.getCodi().getText()), parcAtraccionsForm.getNom().getText(), parcAtraccionsForm.getAdreca().getText());
                        ControladorPrincipal.getParcsAtraccions()[ControladorPrincipal.getComptaParcsAtraccions()] = granja;
                        ControladorPrincipal.setComptaParcsAtraccions();
                        parcAtraccionsForm.getCodi().setText(granja.getCodi().toString());
                        parcAtraccionsForm.getCodi().setEnabled(false);
                        ControladorPrincipal.setParcAtraccionsActual(granja);
                        opcioSeleccionada = 2;
                    }
                } else if (opcioSeleccionada == 3) {//Si modificar parc d'atraccions...
                    ControladorPrincipal.getParcAtraccionsActual().modificaParcAtraccions(parcAtraccionsForm.getNom().getText(), parcAtraccionsForm.getAdreca().getText());
                }

            }
            if (e.getSource() == parcAtraccionsForm.getSortir()) {
                parcAtraccionsForm.getFrame().setVisible(false);
                menuParcAtraccionsVista.getFrame().setVisible(true);
            }
        }
        if (parcAtraccionsLlista != null) {
            if (e.getSource() == parcAtraccionsLlista.getSortir()) {
                parcAtraccionsLlista.getFrame().setVisible(false);
                menuParcAtraccionsVista.getFrame().setVisible(true);
            }
        }

    }

    private void bifurcaOpcio(Integer opcio) {
        int tipusMissatge;
        switch (opcio) {
            case 0: //sortir
                ControladorPrincipal.getMenuPrincipalVista().getFrame().setVisible(true);
                break;
            case 1: // alta
                if (ControladorPrincipal.getComptaParcsAtraccions() < ControladorPrincipal.getMAXPARCSATRACCIONS()) {
                    parcAtraccionsForm = new ParcAtraccionsForm();
                    afegirListenersForm();
                } else {
                    menuParcAtraccionsVista.getFrame().setVisible(true);
                    JOptionPane.showMessageDialog(menuParcAtraccionsVista.getFrame(), "Màxim nombre de parcs d'atraccions assolit.");
                }
                break;
            case 2: //registrar
                menuParcAtraccionsVista.getFrame().setVisible(true);
                if (ControladorPrincipal.getParcsAtraccions()[0] != null) {
                    seleccionaParcAtraccions();
                } else {
                    JOptionPane.showMessageDialog(menuParcAtraccionsVista.getFrame(), "Abans s'ha de crear al menys un parc d'atraccions");
                }
                break;
            case 3: //modificar
                if (ControladorPrincipal.getParcsAtraccions()[0] != null) {
                    seleccionaParcAtraccions();
                    parcAtraccionsForm = new ParcAtraccionsForm(ControladorPrincipal.getParcAtraccionsActual().getCodi(), ControladorPrincipal.getParcAtraccionsActual().getNom(), ControladorPrincipal.getParcAtraccionsActual().getAdreca());
                    parcAtraccionsForm.getCodi().setEnabled(false);
                    afegirListenersForm();
                } else {
                    menuParcAtraccionsVista.getFrame().setVisible(true);
                    JOptionPane.showMessageDialog(menuParcAtraccionsVista.getFrame(), "Abans s'ha de crear al menys un parc d'atraccions");
                }
                break;
            case 4: // llista
                if (ControladorPrincipal.getParcsAtraccions()[0] != null) {
                    parcAtraccionsLlista = new ParcAtraccionsLlista();
                    afegirListenersLlista();
                } else {
                    menuParcAtraccionsVista.getFrame().setVisible(true);
                    JOptionPane.showMessageDialog(menuParcAtraccionsVista.getFrame(), "Abans s'ha de crear al menys un parc d'atraccions");
                }
                break;
            case 5: //carregar
                menuParcAtraccionsVista.getFrame().setVisible(true);
                tipusMissatge = JOptionPane.QUESTION_MESSAGE;
                int code = JOptionPane.showOptionDialog(null, "Selecciona un mètode", "Carregar parc d'atraccions", 0, tipusMissatge, null, ControladorPrincipal.getMETODEPERSISTENCIA(), "XML");
                if (code != JOptionPane.CLOSED_OPTION) {
                    GestorPersistencia gestor = new GestorPersistencia();
                    try {
                        String codi = JOptionPane.showInputDialog("Quin és el codi del parc d'atraccions que vols carregar?");
                        ParcAtraccions parcAtraccions = gestor.carregarParcAtraccions(ControladorPrincipal.getMETODEPERSISTENCIA()[code], codi);
                        Integer index = comprovaParcAtraccions(parcAtraccions.getCodi());
                        if (index >= 0) {
                            Object[] options = {"OK", "Cancel·lar"};
                            Integer i = JOptionPane.showOptionDialog(null, "Premeu OK per substituir-lo.", "El parc d'atraccions ja existent",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                                    null, options, options[0]);
                            if (i == 0) {
                                ControladorPrincipal.getParcsAtraccions()[index] = parcAtraccions;
                                ControladorPrincipal.setParcsAtraccions(null);
                            }
                        } else {
                            ControladorPrincipal.getParcsAtraccions()[ControladorPrincipal.getComptaParcsAtraccions()] = parcAtraccions;
                            ControladorPrincipal.setComptaParcsAtraccions();
                            ControladorPrincipal.setParcAtraccionsActual(null);
                            JOptionPane.showMessageDialog(menuParcAtraccionsVista.getFrame(), "Parc d'atraccions afegit correctament");
                        }

                    } catch (ParcAtraccionsExcepcio e) {
                        JOptionPane.showMessageDialog(menuParcAtraccionsVista.getFrame(), e.getMessage());
                    }
                }
                break;
            case 6: //desar
                menuParcAtraccionsVista.getFrame().setVisible(true);
                if (ControladorPrincipal.getParcAtraccionsActual() != null) {
                    tipusMissatge = JOptionPane.QUESTION_MESSAGE;
                    int codi = JOptionPane.showOptionDialog(null, "Selecciona un mètode", "Desar parc d'atraccions", 0, tipusMissatge, null, ControladorPrincipal.getMETODEPERSISTENCIA(), "XML");
                    if (codi != JOptionPane.CLOSED_OPTION) {
                        GestorPersistencia gestor = new GestorPersistencia();
                        try {
                            gestor.desarParcAtraccions(ControladorPrincipal.getMETODEPERSISTENCIA()[codi], ControladorPrincipal.getParcAtraccionsActual().getCodi().toString(), ControladorPrincipal.getParcAtraccionsActual());
                        } catch (ParcAtraccionsExcepcio e) {
                            JOptionPane.showMessageDialog(menuParcAtraccionsVista.getFrame(), e.getMessage());
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(menuParcAtraccionsVista.getFrame(), "Abans s'ha de seleccionar un parc d'atraccions");
                }
                break;
        }
    }

    private void seleccionaParcAtraccions() {
        String[] nomParcsAtraccions = new String[ControladorPrincipal.getComptaParcsAtraccions()];
        Integer i = 0;
        for (ParcAtraccions parcAtraccions : ControladorPrincipal.getParcsAtraccions()) {
            if (parcAtraccions != null) {
                nomParcsAtraccions[i] = parcAtraccions.getNom();
            }
            i++;
        }
        int messageType = JOptionPane.QUESTION_MESSAGE;
        int code = JOptionPane.showOptionDialog(null, "Selecciona un parc d'atraccions", "Selecció d'un parc d'atraccions", 0, messageType, null, nomParcsAtraccions, "A");
        if (code != JOptionPane.CLOSED_OPTION) {
            ControladorPrincipal.setParcAtraccionsActual(ControladorPrincipal.getParcsAtraccions()[code]);
        }
    }

    private Integer comprovaParcAtraccions(Integer codi) {
        Integer index = -1;
        Integer i = 0;
        for (ParcAtraccions parcAtraccions : ControladorPrincipal.getParcsAtraccions()) {
            if (parcAtraccions != null) {
                if (parcAtraccions.getCodi().equals(codi)) {
                    index = i;
                    break;
                }
            }
            i++;
        }
        return index;
    }

    private Boolean validaParcAtraccions() {
        Boolean retorna = true;
        try {
            Integer codi = Integer.parseInt(parcAtraccionsForm.getCodi().getText());
            Integer index = comprovaParcAtraccions(codi);
            if (index >= 0) {
                retorna = false;
                JOptionPane.showMessageDialog(menuParcAtraccionsVista.getFrame(), "El codi ja existeix");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(menuParcAtraccionsVista.getFrame(), "El codi ha de ser numèric");
            retorna = false;
        }
        if (parcAtraccionsForm.getNom().getText().equals("") || parcAtraccionsForm.getAdreca().getText().equals("")) {
            JOptionPane.showMessageDialog(menuParcAtraccionsVista.getFrame(), "S'han d'introduir el nom i l'adreça");
            retorna = false;
        }

        return retorna;
    }

}
