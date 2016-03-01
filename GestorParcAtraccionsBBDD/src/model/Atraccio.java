package model;

import java.util.Scanner;
import principal.Element;

/**
 *
 * @author Francesca
 */
public class Atraccio implements Element{

    private String nom;
    private String tipus;
    private int restriccioEdat;
    private float restriccioAlcada;
    private boolean teProblema;
    private String codiProblema;
    private boolean estaSolucionat;
    
    public Atraccio(String pNom, String pTipus, int pRestriccioEdat, float pRestriccioAlcada) {
        nom = pNom;
        tipus = pTipus;
        restriccioEdat = pRestriccioEdat;
        restriccioAlcada = pRestriccioAlcada;
        teProblema = false;
        codiProblema = null;
        estaSolucionat = false;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String pNom) {
        nom = pNom;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String pTipus) {
        tipus = pTipus;
    }

    public int getRestriccioEdat() {
        return restriccioEdat;
    }

    public void setRestriccioEdat(int edat) {
        restriccioEdat = edat;
    }

    public float getRestriccioAlcada() {
        return restriccioAlcada;
    }

    public void setRestriccioAlcada(float alcada) {
        restriccioAlcada = alcada;
    }

    public boolean getTeProblema() {
        return teProblema;
    }

    public void setTeProblema(boolean pTeProblema) {
        teProblema = pTeProblema;
    }

    public String getCodiProblema() {
        return codiProblema;
    }

    public void setCodiProblema(String codi) {
        codiProblema = codi;
    }

    public boolean getEstaSolucionat() {
        return estaSolucionat;
    }

    public void setEstaSolucionat(boolean pEstaSolucionat) {
        estaSolucionat = pEstaSolucionat;
    }

    public static Atraccio novaAtraccio() {
        Scanner dades = new Scanner(System.in);
        String nom;
        String tipus;
        int restriccioEdat;
        float restriccioAlcada;
        System.out.println("Nom de l'atracció:");
        nom = dades.nextLine();
        System.out.println("Tipus d'atracció:");
        tipus = dades.nextLine();
        System.out.println("Restricció d'edat de l'atracció:");
        restriccioEdat = dades.nextInt();
        System.out.println("Restricció d'alçada de l'atracció:");
        restriccioAlcada = dades.nextFloat();
        return new Atraccio(nom, tipus, restriccioEdat, restriccioAlcada);
    }

    public void modificaElement() {
        Scanner dades = new Scanner(System.in);
        System.out.println("\nEl nom de l'atracció és: " + getNom());
        System.out.println("\nEntra el nou nom:");
        setNom(dades.nextLine());
        System.out.println("\nEl tipus de l'atracció és: " + getTipus());
        System.out.println("\nEntra el nou tipus:");
        setTipus(dades.nextLine());
        System.out.println("\nLa restricció d'edat de l'atracció és: " + getRestriccioEdat());
        System.out.println("\nEntra la nova edat:");
        setRestriccioEdat(dades.nextInt());
        System.out.println("\nLa restricció de l'alçada de l'atracció és: " + getRestriccioAlcada());
        System.out.println("\nEntra la nova alçada:");
        setRestriccioAlcada(dades.nextFloat());
        if (getTeProblema()) {
            System.out.println("\nL'atracció té algun problema: Si");
        } else {
            System.out.println("\nL'atracció té algun problema: No");
        }
        System.out.println("\nL'atracció té algun problema?. Introdueix si o no.");
        String resposta = dades.next();
        if (resposta.equals("si")) {
            setTeProblema(true);
        } else {
            setTeProblema(false);
        }
        if (getTeProblema()) {
            System.out.println("\nEl codi del problema és: " + getCodiProblema());
            System.out.println("\nEntra el nou codi del problema:");
            setCodiProblema(dades.next());
            if (getCodiProblema() != null) {
                if (getEstaSolucionat()) {
                    System.out.println("\nEl problema " + getCodiProblema() + " està solucionat: Si");
                } else {
                    System.out.println("\nEl problema " + getCodiProblema() + " està solucionat: No");
                }
                System.out.println("\nS'ha solucionat el problema?. Introdueix si o no.");
                resposta = dades.next();
                if (resposta.equals("si")) {
                    setEstaSolucionat(true);
                } else {
                    setEstaSolucionat(false);
                }
            }
        }
    }

    public void mostraElement() {
        System.out.println("\nLes dades de l'atracció amb nom " + getNom() + " són:");
        System.out.println("\nTipus: " + getTipus());
        System.out.println("\nRestricció d'edat: " + getRestriccioEdat());
        System.out.println("\nRestricció d'alçada: " + getRestriccioAlcada());
        if (getTeProblema()) {
            System.out.println("\nL'atracció té un problema.");
            System.out.println("\nEl codi del problema és: " + getCodiProblema());
            if (getEstaSolucionat()) {
                System.out.println("\nEl problema " + getCodiProblema() + " està solucionat.");
            } else {
                System.out.println("\nEl problema " + getCodiProblema() + " no està solucionat.");
            }
        } else {
            System.out.println("\nL'atracció no té cap problema.");
        }
    }
    

}
