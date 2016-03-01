package principal;

/**
 *
 * @author Francesca
 */
public class ParcAtraccionsExcepcio extends Exception {

    private String codiCausa = "desconegut";
    private String missatge;

    //Heu d'afegir tres casos més:
    //   -"GestorJDBC.noExisteix", amb missatge "No existeix el parc d'atraccions";
    //   -"GestorJDBC.desar", amb missatge "No s'ha pogut desar el parc d'atraccions";
    //   -"GestorJDBC.carregar", amb missatge "No s'ha pogut carregar el parc d'atraccions"; 
    
    public ParcAtraccionsExcepcio(String pCodiCausa) {
        codiCausa = pCodiCausa;
        switch (codiCausa) {
            case "1":
                missatge = "L'opció ha de ser correcta";
                break;
            case "2":
                missatge = "Ja no hi caben més elements";
                break;
            case "3":
                missatge = "El nif està repetit";
                break;
            case "4":
                missatge = "El nom està repetit";
                break;
            case "GestorXML.model":
                missatge = "No s'ha pogut crear el model XML per desar el parc d'atraccions";
                break;
            case "GestorXML.desar":
                missatge = "No s'ha pogut desar el parc d'atraccions a causa d'error d'entrada/sortida";
                break;
            case "GestorXML.carregar":
                missatge = "No s'ha pogut carregar el parc d'atraccions a causa d'error d'entrada/sortida";
                break;
            default:
                missatge = "Error desconegut";
                break;
        }
    }
    
    public String getMessage() {
        return "Amb el codi de causa:  " + codiCausa + ", s'ha generat el següent missatge: " + missatge;
    }

}
