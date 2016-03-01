package principal;

import java.util.InputMismatchException;
import model.ParcAtraccions;
import model.Atraccio;
import model.Zona;
import model.PersonaManteniment;
import java.util.Scanner;
import persistencia.GestorPersistencia;
import model.Coordinador;

/**
 *
 * @author Francesca
 */
public class Aplicacio {

    static private String FITXER = "parcAtraccions";
    static private ParcAtraccions[] parcsAtraccions = new ParcAtraccions[1];
    static private Integer comptaParcsAtraccions = 0;
    static private ParcAtraccions parcAtraccionsActual = null;
    static private Integer tipusElement = 0;
    static private GestorPersistencia gp = new GestorPersistencia();

    public static void main(String[] args) {
        try {
            menuPrincipal();
        } catch (ParcAtraccionsExcepcio e) {
            System.out.println(e.getMessage());
        }
    }

    private static void menuPrincipal() throws ParcAtraccionsExcepcio {
        int opcio = 0;
        Scanner teclado = new Scanner(System.in);
        do {
             try {
            System.out.println("\nSelecciona una opció");
            System.out.println("\n0. Sortir");
            System.out.println("\n1. Gestió dels parcs d'atraccions");
            System.out.println("\n2. Gestió dels coordinadors");
            System.out.println("\n3. Gestió del personal de manteniment");
            System.out.println("\n4. Gestió d'atraccions");
            System.out.println("\n5. Gestió de zones");
            opcio = teclado.nextInt();
            switch (opcio) {
                case 0:
                    break;
                case 1:
                    menuParcAtraccions();
                    break;
                case 2:
                    if (parcAtraccionsActual != null) {
                        tipusElement = 1;
                        menuElement();
                    } else {
                        System.out.println("\nPrimer s'ha d'assignar el parc d'atraccions al menú parc d'atraccions");
                    }
                    break;
                case 3:
                    if (parcAtraccionsActual != null) {
                        tipusElement = 2;
                        menuElement();
                    } else {
                        System.out.println("\nPrimer s'ha d'assignar el parc d'atraccions al menú parc d'atraccions");
                    }
                    break;
                case 4:
                    if (parcAtraccionsActual != null) {
                        tipusElement = 3;
                        menuElement();
                    } else {
                        System.out.println("\nPrimer s'ha d'assignar el parc d'atraccions al menú parc d'atraccions");
                    }
                    break;
                case 5:
                    if (parcAtraccionsActual != null) {
                        tipusElement = 4;
                        menuZones();
                    } else {
                        System.out.println("\nPrimer s'ha d'assignar el parc d'atraccions al menú parc d'atraccions");
                    }
                    break;
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
             } catch (InputMismatchException e) {
                ParcAtraccionsExcepcio ee = new ParcAtraccionsExcepcio("1");
                throw ee;
            } catch (ArrayIndexOutOfBoundsException e) {
                ParcAtraccionsExcepcio ee = new ParcAtraccionsExcepcio("2");
                throw ee;
            }
        } while (opcio != 0);
    }

    public static void menuParcAtraccions() throws ParcAtraccionsExcepcio{
        int opcio = 0;
        Scanner teclado = new Scanner(System.in);
        do {
            Integer indexSel = -1;
            System.out.println("\nSelecciona una opció");
            System.out.println("\n0. Sortir");
            System.out.println("\n1. Alta");
            System.out.println("\n2. Assignar parc d'atraccions");
            System.out.println("\n3. Modificació");
            System.out.println("\n4. Llistat de parcs d'atraccio");
            System.out.println("\n5. Carregar parc d'atraccions");
            System.out.println("\n6. Desa parc d'atraccions");
            opcio = teclado.nextInt();
            switch (opcio) {
                case 0:
                    break;
                case 1:
                    parcsAtraccions[comptaParcsAtraccions] = ParcAtraccions.nouParcAtraccions();
                    comptaParcsAtraccions++;
                    break;
                case 2:
                    indexSel = seleccionaParcAtraccions();
                    if (indexSel >= 0) {
                        parcAtraccionsActual = parcsAtraccions[indexSel];
                    } else {
                        System.out.println("\nNo existeix aquest parc d'atraccions");
                    }
                    break;
                case 3:
                    indexSel = seleccionaParcAtraccions();
                    if (indexSel >= 0) {
                        parcsAtraccions[indexSel].modificaParcAtraccions();
                    } else {
                        System.out.println("\nNo existeix aquest parc d'atraccions");
                    }
                    break;
                case 4:
                    for (int i = 0; i < comptaParcsAtraccions; i++) {
                        parcsAtraccions[i].mostraParcAtraccions();
                    }
                    break;
                case 5: //Carregar parc atracció
                    comptaParcsAtraccions = 0;
                    parcsAtraccions = new ParcAtraccions[1];
                    gp.carregarParcAtraccions("XML", FITXER);
                    parcsAtraccions[comptaParcsAtraccions] = gp.getGestor().getParcAtraccions();
                    comptaParcsAtraccions++;
                    break;
                case 6: //Desar parc atracció
                    indexSel = seleccionaParcAtraccions();
                    if (indexSel >= 0) {
                        gp.desarParcAtraccions("XML", FITXER, parcsAtraccions[indexSel]);
                    } else {
                        System.out.println("\nNo existeix aquest parc d'atraccions");
                    }
                    break;
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
        } while (opcio != 0);
    }

    public static void menuElement() {
        int opcio = 0;
        Scanner teclado = new Scanner(System.in);
        do {
            System.out.println("\nSelecciona una opció");
            System.out.println("\n0. Sortir");
            System.out.println("\n1. Alta");
            System.out.println("\n2. Modificació");
            System.out.println("\n3. Llista");
            opcio = teclado.nextInt();
            switch (opcio) {
                case 0:
                    break;
                case 1:
                    switch (tipusElement) {
                        case 1:
                            parcAtraccionsActual.nouCoordinador(null);
                            break;
                        case 2:
                            parcAtraccionsActual.novaPersonaManteniment(null);
                            break;
                        case 3:
                            parcAtraccionsActual.novaAtraccio(null);
                            break;
                    }
                    break;
                case 2:
                    Integer indexSel = parcAtraccionsActual.seleccionaElement(tipusElement,"");
                    if (indexSel >= 0) {
                        parcAtraccionsActual.getElements()[indexSel].modificaElement();
                    } else {
                        System.out.println("\nNo existeix aquest coordinador");
                    }
                    break;
                case 3:
                    for (int i = 0; i < parcAtraccionsActual.getComptaElements(); i++) {
                        if (parcAtraccionsActual.getElements()[i] instanceof Coordinador && tipusElement == 1) {
                            parcAtraccionsActual.getElements()[i].mostraElement();
                        }
                        if (parcAtraccionsActual.getElements()[i] instanceof PersonaManteniment && tipusElement == 2) {
                            parcAtraccionsActual.getElements()[i].mostraElement();
                        }
                        if (parcAtraccionsActual.getElements()[i] instanceof Atraccio && tipusElement == 3) {
                            parcAtraccionsActual.getElements()[i].mostraElement();
                        }
                    }
                    break;
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
        } while (opcio != 0);
    }

    public static void menuZones() {
        int opcio = 0;
        Scanner teclado = new Scanner(System.in);
        do {
            System.out.println("\nSelecciona una opció");
            System.out.println("\n0. Sortir");
            System.out.println("\n1. Alta");
            System.out.println("\n2. Afegeix coordinador");
            System.out.println("\n3. Afegeix persona manteniment");
            System.out.println("\n4. Afegeix atracció");
            System.out.println("\n5. Llistar zones");
            opcio = teclado.nextInt();
            switch (opcio) {
                case 0:
                    break;
                case 1:
                    parcAtraccionsActual.novaZona(null);
                    break;
                case 2:
                    parcAtraccionsActual.afegeixElementZona(1);
                    break;
                case 3:
                    parcAtraccionsActual.afegeixElementZona(2);
                    break;
                case 4:
                    parcAtraccionsActual.afegeixElementZona(3);
                    break;
                case 5:
                    for (int i = 0; i < parcAtraccionsActual.getComptaElements(); i++) {
                        if (parcAtraccionsActual.getElements()[i] instanceof Zona) {
                            parcAtraccionsActual.getElements()[i].mostraElement();
                        }

                    }
                    break;
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
        } while (opcio != 0);
    }

    public static int seleccionaParcAtraccions() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("\nCodi del parc d'atraccions?:");
        int codiSel = teclado.nextInt();
        int indexSel = -1;
        for (int i = 0; i < comptaParcsAtraccions; i++) {
            if (parcsAtraccions[i].getCodi() == codiSel) {
                indexSel = i;
                break;
            }
        }
        return indexSel;
    }

}
