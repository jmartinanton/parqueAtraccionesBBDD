package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import principal.Element;

/**
 *
 * @author Francesca
 */
public class Zona implements Element {

    private String nom;
    private ArrayList<Element> elements = new ArrayList<Element>();

    public Zona(String pNom) {
        nom = pNom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Element> getElements() {
        return elements;
    }

    public void setElements(ArrayList<Element> elements) {
        this.elements = elements;
    }

    public static Zona novaZona() {
        Scanner dades = new Scanner(System.in);
        String nomZona;
        System.out.println("Nom de la zona:");;
        nomZona = dades.nextLine();
        return new Zona(nomZona);
    }

    public void afegeixElementZona(Element element) {
        elements.add(element);
    }

    public void modificaElement() {
        Scanner dades = new Scanner(System.in);
        System.out.println("\nNom de la zona és: " + getNom());
        System.out.println("\nEntra el nou nom:");
        this.setNom(dades.next());
    }

    public void mostraElement() {
        System.out.println("\nNom:" + getNom());
        System.out.println("Aquesta zona està formada pels elements:");
        Iterator i = elements.iterator();
        while(i.hasNext()){
            Element e = (Element) i.next();
            e.mostraElement();
        }
    }

}
