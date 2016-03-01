/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.FileWriter;
import java.util.Iterator;
import model.*;
import nu.xom.*;
import principal.ParcAtraccionsExcepcio;

public class GestorXML implements ProveedorPersistencia {

    private Document doc;
    private ParcAtraccions parcAtraccions;

    public ParcAtraccions getParcAtraccions() {
        return parcAtraccions;
    }

    public void setParcAtraccions(ParcAtraccions pParcAtraccions) {
        parcAtraccions = pParcAtraccions;
    }

    @Override
    public void desarParcAtraccions(String nomFitxer, ParcAtraccions pParcAtraccions) throws ParcAtraccionsExcepcio {
        construeixModel(pParcAtraccions);
        desarModel(nomFitxer);
    }

    public ParcAtraccions carregarParcAtraccions(String nomFitxer) throws ParcAtraccionsExcepcio {
        carregarFitxer(nomFitxer);
        fitxerParcAtraccions();
        return parcAtraccions;
    }

    private void construeixModel(ParcAtraccions pParcAtraccions) throws ParcAtraccionsExcepcio {
        //Element arrel
        Element arrel = new Element("parcAtraccions");
        arrel.addAttribute(new Attribute("codi", parcAtraccions.getCodi().toString()));
        arrel.addAttribute(new Attribute("nom", parcAtraccions.getNom()));
        arrel.addAttribute(new Attribute("adreca", parcAtraccions.getAdreca()));

        for (int i = 0; i < parcAtraccions.getComptaElements(); i++) {
            if (parcAtraccions.getElements()[i] instanceof Coordinador) {
                Element element = new Element("coordinador");
                element.addAttribute(new Attribute("nif", ((Coordinador) parcAtraccions.getElements()[i]).getNif()));
                element.addAttribute(new Attribute("nom", ((Coordinador) parcAtraccions.getElements()[i]).getNom()));
                element.addAttribute(new Attribute("cognom", ((Coordinador) parcAtraccions.getElements()[i]).getCognom()));
                arrel.appendChild(element);
            }
            if (parcAtraccions.getElements()[i] instanceof PersonaManteniment) {
                Element element = new Element("personaManteniment");
                element.addAttribute(new Attribute("nif", ((PersonaManteniment) parcAtraccions.getElements()[i]).getNif()));
                element.addAttribute(new Attribute("nom", ((PersonaManteniment) parcAtraccions.getElements()[i]).getNom()));
                element.addAttribute(new Attribute("cognom", ((PersonaManteniment) parcAtraccions.getElements()[i]).getCognom()));
                arrel.appendChild(element);
            }
            if (parcAtraccions.getElements()[i] instanceof Atraccio) {
                //codiProblema="null" estaSolucionat
                Element element = new Element("atraccio");
                element.addAttribute(new Attribute("nom", ((Atraccio) parcAtraccions.getElements()[i]).getNom()));
                element.addAttribute(new Attribute("tipus", ((Atraccio) parcAtraccions.getElements()[i]).getTipus()));
                element.addAttribute(new Attribute("restriccioEdat", String.valueOf(((Atraccio) parcAtraccions.getElements()[i]).getRestriccioEdat())));
                element.addAttribute(new Attribute("restriccioAlcada", String.valueOf(((Atraccio) parcAtraccions.getElements()[i]).getRestriccioAlcada())));
                if (((Atraccio) parcAtraccions.getElements()[i]).getTeProblema()) {
                    element.addAttribute(new Attribute("teProblema", "true"));
                } else {
                    element.addAttribute(new Attribute("teProblema", "false"));
                }
                element.addAttribute(new Attribute("codiProblema", ((Atraccio) parcAtraccions.getElements()[i]).getCodiProblema()));
                if (((Atraccio) parcAtraccions.getElements()[i]).getEstaSolucionat()) {
                    element.addAttribute(new Attribute("estaSolucionat", "true"));
                } else {
                    element.addAttribute(new Attribute("estaSolucionat", "false"));
                }
                arrel.appendChild(element);
            }
            if (parcAtraccions.getElements()[i] instanceof Zona) {
                Element element = new Element("zona");
                element.addAttribute(new Attribute("nom", ((Zona) parcAtraccions.getElements()[i]).getNom()));

                Iterator iterador = ((Zona) parcAtraccions.getElements()[i]).getElements().iterator();
                while (iterador.hasNext()) {
                    Object objecte = iterador.next();
                    if (objecte instanceof Coordinador) {
                        Coordinador coordinador = (Coordinador) objecte;
                        Element elementZona = new Element("coordinador");
                        elementZona.addAttribute(new Attribute("nif", coordinador.getNif()));
                        elementZona.addAttribute(new Attribute("nom", coordinador.getNom()));
                        elementZona.addAttribute(new Attribute("cognom", coordinador.getCognom()));
                        element.appendChild(elementZona);
                    }
                    if (objecte instanceof PersonaManteniment) {
                        PersonaManteniment personaManteniment = (PersonaManteniment) objecte;
                        Element elementZona = new Element("personaManteniment");
                        elementZona.addAttribute(new Attribute("nif", personaManteniment.getNif()));
                        elementZona.addAttribute(new Attribute("nom", personaManteniment.getNom()));
                        elementZona.addAttribute(new Attribute("cognom", personaManteniment.getCognom()));
                        element.appendChild(elementZona);
                    }
                    if (objecte instanceof Atraccio) {
                        Atraccio atraccio = (Atraccio) objecte;
                        Element elementZona = new Element("atraccio");
                        elementZona.addAttribute(new Attribute("nom", atraccio.getNom()));
                        elementZona.addAttribute(new Attribute("tipus", atraccio.getTipus()));
                        elementZona.addAttribute(new Attribute("restriccioEdat", String.valueOf(atraccio.getRestriccioEdat())));
                        elementZona.addAttribute(new Attribute("restriccioAlcada", String.valueOf(atraccio.getRestriccioAlcada())));
                        if (atraccio.getTeProblema()) {
                            elementZona.addAttribute(new Attribute("teProblema", "true"));
                        } else {
                            elementZona.addAttribute(new Attribute("teProblema", "false"));
                        }
                        elementZona.addAttribute(new Attribute("codiProblema", atraccio.getCodiProblema()));
                        if (atraccio.getEstaSolucionat()) {
                            elementZona.addAttribute(new Attribute("estaSolucionat", "true"));
                        } else {
                            elementZona.addAttribute(new Attribute("estaSolucionat", "false"));
                        }
                        element.appendChild(elementZona);
                    }
                }
                arrel.appendChild(element);
            }
        }
        doc = new Document(arrel);
    }

    private void desarModel(String rutaFitxer) throws ParcAtraccionsExcepcio {
        try {
            FileWriter fitxer = new FileWriter(rutaFitxer, false); //Obrim fitxer per sobreescriure
            fitxer.write(doc.toXML());
            fitxer.close();
        } catch (Exception e) {
            throw new ParcAtraccionsExcepcio("GestorXML.desar");
        }
    }

    private void carregarFitxer(String rutaFitxer) throws ParcAtraccionsExcepcio {
        Builder builder = new Builder();
        try {
            doc = builder.build(rutaFitxer);
        } catch (Exception e) {
            throw new ParcAtraccionsExcepcio("GestorXML.carregar");
        }
    }

    private void fitxerParcAtraccions() throws ParcAtraccionsExcepcio {

        Element arrel = new Element("parcAtraccions");

        //Es crea l'objecte parcAtraccions
        ParcAtraccions nouParcAtraccions = new ParcAtraccions(Integer.parseInt(arrel.getAttributeValue("codi")), arrel.getAttributeValue("nom"), arrel.getAttributeValue("adreca"));

        Elements elements; //Elements del parc d'atraccions.
        Elements elementsZona; //Elements d'una zona
        int index; //Index dels elements d'un parc d'atraccions

        //Recorregut dels coordinadors      
        elements = arrel.getChildElements("coordinador");
        for (int i = 0; i < elements.size(); i++) {

            //Es crea l'objecte Coordinador
            Coordinador coordinador = new Coordinador(elements.get(i).getAttributeValue("nif"), elements.get(i).getAttributeValue("nom"), elements.get(i).getAttributeValue("cognom"));

            //Afegim el nou coordinador als elements del parc d'atraccions.
            nouParcAtraccions.nouCoordinador(coordinador);
        }

        //Recorregut del personal de manteniment     
        elements = arrel.getChildElements("personaManteniment");
        for (int i = 0; i < elements.size(); i++) {

            //Es crea l'objecte PersonaManteniment
            PersonaManteniment personaManteniment = new PersonaManteniment(elements.get(i).getAttributeValue("nif"), elements.get(i).getAttributeValue("nom"), elements.get(i).getAttributeValue("cognom"));

            //Afegim la nova persona de manteniment als elements del parc d'atraccions.
            nouParcAtraccions.novaPersonaManteniment(personaManteniment);
        }

        //Recorregut de les atraccions   
        elements = arrel.getChildElements("atraccio");
        for (int i = 0; i < elements.size(); i++) {

            //Es crea l'objecte Atraccio
            Atraccio atraccio = new Atraccio(elements.get(i).getAttributeValue("nom"), elements.get(i).getAttributeValue("tipus"), Integer.parseInt(elements.get(i).getAttributeValue("restriccioEdat")), Float.parseFloat(elements.get(i).getAttributeValue("restriccioAlcada")));

            //Assignem valor als atributs teProblema, codiProblema, estaSolucionat
            if (elements.get(i).getAttributeValue("teProblema").equals("false")) {
                atraccio.setTeProblema(false);
            } else {
                atraccio.setTeProblema(true);
            }

            atraccio.setCodiProblema(elements.get(i).getAttributeValue("codiProblema"));

            if (elements.get(i).getAttributeValue("estaSolucionat").equals("false")) {
                atraccio.setEstaSolucionat(false);
            } else {
                atraccio.setEstaSolucionat(true);
            }

            //Afegim la nova atracció als elements del parc d'atraccions.
            nouParcAtraccions.novaAtraccio(atraccio);
        }

        //Recorregut de les zones    
        elements = arrel.getChildElements("zona");
        for (int i = 0; i < elements.size(); i++) {

            //Es crea l'objecte Zona
            Zona zona = new Zona(elements.get(i).getAttributeValue("nom"));

            //Recorregut dels coordinadors de la zona 
            elementsZona = elements.get(i).getChildElements("coordinador");
            for (int j = 0; j < elementsZona.size(); j++) {

                //Seleccionem l'element de la zona, del vector d'elements del parc d'atraccions
                index = nouParcAtraccions.seleccionaElement(1, elementsZona.get(i).getAttributeValue("nif"));

                //Afegim l'element seleccionat a la zona.
                zona.afegeixElementZona(nouParcAtraccions.getElements()[index]);
            }

            //Recorregut del personal de manteniment de la zona 
            elementsZona = elements.get(i).getChildElements("personaManteniment");
            for (int j = 0; j < elementsZona.size(); j++) {

                //Seleccionem l'element de la zona, del vector d'elements del parc d'atraccions
                index = nouParcAtraccions.seleccionaElement(2, elementsZona.get(i).getAttributeValue("nif"));

                //Afegim l'element seleccionat a la zona.
                zona.afegeixElementZona(nouParcAtraccions.getElements()[index]);
            }

            //Recorregut de les atraccions de la zona 
            elementsZona = elements.get(i).getChildElements("atraccio");
            for (int j = 0; j < elementsZona.size(); j++) {

                //Seleccionem l'element de la zona, del vector d'elements del parc d'atraccions
                index = nouParcAtraccions.seleccionaElement(3, elementsZona.get(i).getAttributeValue("nom"));

                //Afegim l'element seleccionat a la zona.
                zona.afegeixElementZona(nouParcAtraccions.getElements()[index]);
            }

        }
    }

}
