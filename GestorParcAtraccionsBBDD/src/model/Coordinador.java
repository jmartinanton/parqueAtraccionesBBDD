package model;

import java.util.Scanner;

/**
 *
 * @author Francesca
 */
public class Coordinador extends Persona {

    public Coordinador(String nif, String nom, String cognom) {
        super(nif, nom, cognom);
    }

    public static Coordinador nouCoordinador() {
        Scanner dades = new Scanner(System.in);
        String nif;
        String nom;
        String cognoms;
        System.out.println("Nif del coordinador:");
        nif = dades.next();
        dades.nextLine();
        System.out.println("Nom del coordinador:");;
        nom = dades.nextLine();
        System.out.println("Cognom del coordinador:");
        cognoms = dades.nextLine();
        return new Coordinador(nif, nom, cognoms);
    }

}
