package controlador;

import persistencia.GestorPersistencia;
import model.ParcAtraccions;
import vista.MenuPrincipalVista;
import java.awt.event.*;
import javax.swing.*;

public class ControladorPrincipal implements ActionListener {

    static private MenuPrincipalVista menuPrincipalVista;
    static private final Integer MAXPARCSATRACCIONS = 10;
    static private ParcAtraccions[] parcsAtraccions = new ParcAtraccions[MAXPARCSATRACCIONS];
    static private Integer comptaParcsAtraccions = 0;
    static private ParcAtraccions parcAtraccionsActual = null;
    static private Integer tipusElement = 0;
    static private GestorPersistencia gp = new GestorPersistencia();
    static private final String[] METODEPERSISTENCIA = {"XML","JDBC"};

    public ControladorPrincipal() {
       menuPrincipalVista = new MenuPrincipalVista();
        //S'AFEGEIX EL CONTROLADOR COM A LISTENER DELS BOTONS
        for (JButton boto : menuPrincipalVista.getMenuButtons()) {
            boto.addActionListener(this);
        }
    }

    public void actionPerformed(ActionEvent e) {
        JButton[] botons = menuPrincipalVista.getMenuButtons();
        for (Integer i = 0; i < botons.length; i++) {
            if (e.getSource() == botons[i]) {
                bifurcaOpcio(i);
            }
        }
    }

    private void bifurcaOpcio(Integer opcio) {
        switch (opcio) {
            case 0:
                System.exit(0);
                break;
            case 1:
                menuPrincipalVista.getFrame().setVisible(false);
                ControladorParcAtraccions controladorParcAtraccions = new ControladorParcAtraccions();
                break;
            case 2:
                if (parcAtraccionsActual != null) {
                    tipusElement = 1;
                    menuPrincipalVista.getFrame().setVisible(false);
                    ControladorCoordinador controladorTecnic = new ControladorCoordinador();
                } else {
                    JOptionPane.showMessageDialog(menuPrincipalVista.getFrame(), "Primer s'ha de registrar el parcd'atraccions al menú Parc d'atraccions");
                }
                break;
        }
    }



    public static MenuPrincipalVista getMenuPrincipalVista() {
        return menuPrincipalVista;
    }

    public static Integer getMAXPARCSATRACCIONS() {
        return MAXPARCSATRACCIONS;
    }

    
    public static ParcAtraccions[] getParcsAtraccions() {
        return parcsAtraccions;
    }

    public static void setParcsAtraccions(ParcAtraccions[] pParcsAtraccions) {
        ControladorPrincipal.parcsAtraccions = pParcsAtraccions;
    }

    public static Integer getComptaParcsAtraccions() {
        return comptaParcsAtraccions;
    }

    public static void setComptaParcsAtraccions() {
        ControladorPrincipal.comptaParcsAtraccions++;
    }

    public static ParcAtraccions getParcAtraccionsActual() {
        return parcAtraccionsActual;
    }

    public static void setParcAtraccionsActual(ParcAtraccions pParcAtraccionsActual) {
        ControladorPrincipal.parcAtraccionsActual = pParcAtraccionsActual;
    }

    public static Integer getTipusElement() {
        return tipusElement;
    }

    public static void setTipusElement(Integer pTipusElement) {
        ControladorPrincipal.tipusElement = pTipusElement;
    }

    public static GestorPersistencia getGp() {
        return gp;
    }

    public static void setGp(GestorPersistencia gp) {
        ControladorPrincipal.gp = gp;
    }

    public static String[] getMETODEPERSISTENCIA() {
        return METODEPERSISTENCIA;
    }
    
}
