package persistencia;

import model.ParcAtraccions;
import principal.ParcAtraccionsExcepcio;

public class GestorPersistencia {
    private GestorXML gestor;

    public GestorXML getGestor() {
        return gestor;
    }

    public void setGestor(GestorXML pGestor) {
        gestor = pGestor;
    }  

    public void desarParcAtraccions(String tipusPersistencia, String nomFitxer, ParcAtraccions parcAtraccions) throws ParcAtraccionsExcepcio {
       
        ProveedorPersistencia gestor = null;
        switch (tipusPersistencia) {
            case "XML":
                gestor = new GestorXML();
                gestor.desarParcAtraccions(nomFitxer, parcAtraccions);
                break;
            case "JDBC":
                gestor = new GestorJDBC();
                gestor.desarParcAtraccions(nomFitxer, parcAtraccions);
                break;  
        }
    }

    public ParcAtraccions carregarParcAtraccions(String tipusPersistencia, String nomFitxer) throws ParcAtraccionsExcepcio {
       ProveedorPersistencia gestor = null;
        ParcAtraccions parcAtraccions = null;
        switch (tipusPersistencia) {
            case "XML":
                gestor = new GestorXML();
                parcAtraccions = gestor.carregarParcAtraccions(nomFitxer);                
                break;
            case "JDBC":
                gestor = new GestorJDBC();
                parcAtraccions = gestor.carregarParcAtraccions(nomFitxer);
                break;
        }
        return parcAtraccions;
    }
}
