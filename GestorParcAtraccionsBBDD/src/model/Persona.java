package model;

import java.util.Scanner;
import principal.*;

/**
 *
 * @author Francesca
 */
public abstract class Persona implements Element {

    private String nif;
    private String nom;
    private String cognom;

    public Persona(String nif, String nom, String cognom) {
        this.nif = nif;
        this.nom = nom;
        this.cognom = cognom;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public void modificaElement() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("\nNif és: " + getNif());
        System.out.println("\nEntra el nou nif:");
        this.setNif(teclado.next());
        System.out.println("\nNom és: " + getNom());
        System.out.println("\nEntra el nou nom:");
        this.setNom(teclado.next());
        System.out.println("\nCognom és: " + getCognom());
        System.out.println("\nEntra el nou cognom:");
        this.setCognom(teclado.next());
    }

    public void mostraElement() {
        String laClasse = getClass().getSimpleName();
        System.out.println("\nLes dades de " + laClasse + " amb nif " + getNif() + " són:");
        System.out.println("\nNom:" + getNom());
        System.out.println("\nCognom:" + getCognom());
    }

}
