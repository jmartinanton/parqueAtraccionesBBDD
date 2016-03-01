package model;

import model.PersonaManteniment;
import model.Atraccio;
import java.util.Scanner;
import model.RegistreUsuaris;
import model.Coordinador;
import principal.Element;
import principal.ParcAtraccionsExcepcio;

/**
 *
 * @author Francesca
 */
public class ParcAtraccions {

    static private Integer comptaCodi = 1; //El proper codi a assignar
    private Integer codi;
    private String nom;
    private String adreca;
    private Element[] elements = new Element[10];
    private Integer comptaElements = 0;
    private RegistreUsuaris[] registres = new RegistreUsuaris[100];
    private Integer comptaRegistres = 0;

    public ParcAtraccions(String pNom, String pAdreca) {
        codi = getComptaCodi();
        setComptaCodi();
        nom = pNom;
        adreca = pAdreca;
    }
    
     public ParcAtraccions(Integer codi, String nom, String adreca) {
        this.codi = codi;
        this.nom = nom;
        this.adreca = adreca;
    }

    public static Integer getComptaCodi() {
        return comptaCodi;
    }

    public static void setComptaCodi() {
        comptaCodi++;
    }

    public Integer getCodi() {
        return codi;
    }

    public void setCodi(Integer pCodi) {
        codi = pCodi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String pNom) {
        nom = pNom;
    }

    public String getAdreca() {
        return adreca;
    }

    public void setAdreça(String pAdreca) {
        adreca = pAdreca;
    }

    public Element[] getElements() {
        return elements;
    }

    public void setElements(Element[] elements) {
        this.elements = elements;
    }

    public Integer getComptaElements() {
        return comptaElements;
    }

    public void setComptaElements(Integer comptaElements) {
        this.comptaElements = comptaElements;
    }

    public RegistreUsuaris[] getRegistres() {
        return registres;
    }

    public void setRegistres(RegistreUsuaris[] registres) {
        this.registres = registres;
    }

    public Integer getComptaRegistres() {
        return comptaRegistres;
    }

    public void setComptaRegistres(Integer comptaRegistres) {
        this.comptaRegistres = comptaRegistres;
    }

    public static ParcAtraccions nouParcAtraccions() {
        Scanner dades = new Scanner(System.in);
        String nom;
        String adreca;
        System.out.println("Nom del parc d'atraccions:");
        nom = dades.nextLine();
        System.out.println("Adreça del parc d'atraccions:");
        adreca = dades.nextLine();
        return new ParcAtraccions(nom, adreca);
    }
    
    public void modificaParcAtraccions() {
        Scanner dades = new Scanner(System.in);
        System.out.println("\nNom del parc d'atraccions és: " + this.getNom());
        System.out.println("\nEntra el nou nom:");
        this.setNom(dades.next());
        System.out.println("\nAdreça del parc d'atraccions és: " + this.getAdreca());
        System.out.println("\nEntra la nova adreça:");
        this.setAdreça(dades.next());
    }

    public void modificaParcAtraccions(String nom, String adreca) {
        this.nom = nom;
        this.adreca = adreca;
    }

    public void mostraParcAtraccions() {
        System.out.println("\nLes dades del parc d'atraccions amb codi " + getCodi() + " són:");
        System.out.println("\nNom:" + getNom());
        System.out.println("\nAdreça:" + getAdreca());
    }

    /*
     Coordinadors
     */
    public Integer seleccionaCoordinador(String nif) throws ParcAtraccionsExcepcio {
        Integer indexSel = -1;
        for (int i = 0; i < comptaElements; i++) {
            if (elements[i] instanceof Coordinador) {
                if (((Coordinador) elements[i]).getNif().equals(nif)) {
                    indexSel = i;
                    throw new ParcAtraccionsExcepcio("3");
                }
            }
        }
        return indexSel;
    }
    
    
    public void nouCoordinador(Coordinador coordinador) {
        try {
            Coordinador nouCoordinador = coordinador;
            if (nouCoordinador == null) {
                nouCoordinador = Coordinador.nouCoordinador();
            }
            seleccionaCoordinador(nouCoordinador.getNif());
            elements[comptaElements] = nouCoordinador;
            comptaElements++;
        } catch (ParcAtraccionsExcepcio e) {
            System.out.println(e.getMessage());
        }
    }

    /*
     Personal manteniment
     */
    public void novaPersonaManteniment(PersonaManteniment personaManteniment) {
        if (personaManteniment == null) {
            elements[comptaElements] = personaManteniment.novaPersonaManteniment();
        } else {
            elements[comptaElements] = personaManteniment;
        }
        comptaElements++;
    }

    /*
     Atraccions
     */
    public void novaAtraccio(Atraccio atraccio) {
        if (atraccio == null) {
            elements[comptaElements] = atraccio.novaAtraccio();
        } else {
            elements[comptaElements] = atraccio;
        }
        comptaElements++;
    }


    /*
     Zones
     */
    public void novaZona(Zona zona) {
        if (zona == null) {
            elements[comptaElements] = zona.novaZona();
        } else {
            elements[comptaElements] = zona;
        }
        comptaElements++;
    }

    public Integer seleccionaElement(Integer tipusElement,String codi) {
        Scanner dades = new Scanner(System.in);
        Integer laClasse = tipusElement;
        String codiSel = "";
        String missatge = "";
        switch (laClasse) {
            case 0:
                System.out.println("\nQuè vols seleccionar?:");
                System.out.println("1. Coordinador:");
                System.out.println("2. Persona demanteniment:");
                System.out.println("3. Atracció:");
                System.out.println("4. Zona:");
                laClasse = dades.nextInt();
                break;
            case 1:
                missatge="Nif del coordinador?:";
                break;
            case 2:
                missatge="Nif de la persona de manteniment?:";
                break;
            case 3:
                missatge="Nom de l'Atracció?:";
                break;
            case 4:
                missatge ="Nom de la Zona?:";
                break;
        }
        
        if (codi == "") {
            System.out.println(missatge);
            codiSel = dades.next();
        } else {
            codiSel = codi;
        }
        
        Integer indexSel = -1;
        for (int i = 0; i < comptaElements; i++) {
            if (elements[i] instanceof Coordinador && laClasse == 1) {
                if (((Coordinador) elements[i]).getNif().equals(codiSel)) {
                    indexSel = i;
                    break;
                }
            }
            if (elements[i] instanceof PersonaManteniment && laClasse == 2) {
                if (((PersonaManteniment) elements[i]).getNif().equals(codiSel)) {
                    indexSel = i;
                    break;
                }
            }
            if (elements[i] instanceof Atraccio && laClasse == 3) {
                if (((Atraccio) elements[i]).getNom().equals(codiSel)) {
                    indexSel = i;
                    break;
                }
            }
            if (elements[i] instanceof Zona && laClasse == 4) {
                if (((Zona) elements[i]).getNom().equals(codiSel)) {
                    indexSel = i;
                    break;
                }
            }
        }
        return indexSel;
    }

    public void afegeixElementZona(Integer tipusElement) {
        Zona nauSel = null;
        Integer indexSelNau = seleccionaElement(4,"");
        if (indexSelNau >= 0) {
            nauSel = (Zona) this.getElements()[indexSelNau];
        } else {
            System.out.println("\nNo existeix aquesta nau");
        }
        Integer indexSel = seleccionaElement(tipusElement,"");
        if (indexSel >= 0) {
            nauSel.afegeixElementZona(getElements()[indexSel]);
        } else {
            System.out.println("\nNo existeix aquest element");
        }
    }

    public void afegeixRegistre() {
        registres[comptaRegistres] = RegistreUsuaris.nouRegistre();
        comptaRegistres++;
    }
}
