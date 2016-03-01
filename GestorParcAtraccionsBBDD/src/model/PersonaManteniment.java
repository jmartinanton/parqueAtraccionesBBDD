package model;

import java.util.Scanner;

/**
 *
 * @author Francesca
 */
public class PersonaManteniment extends Persona {

    public PersonaManteniment(String nif, String nom, String cognom) {
        super(nif, nom, cognom);
    }

    public static PersonaManteniment novaPersonaManteniment() {
        Scanner dades = new Scanner(System.in);
        String nif;
        String nom;
        String cognoms;
        System.out.println("Nif de la persona de manteniment:");
        nif = dades.next();
        dades.nextLine();
        System.out.println("Nom de la persona de mantenimen:");;
        nom = dades.nextLine();
        System.out.println("Cognoms de la persona de mantenimen:");
        cognoms = dades.nextLine();
        return new PersonaManteniment(nif, nom, cognoms);
    }


}
