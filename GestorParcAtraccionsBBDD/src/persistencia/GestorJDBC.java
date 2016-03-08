/*
 * Gestor per accedir a la base de dades.
 */
package persistencia;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ParcAtraccions;
import principal.ParcAtraccionsExcepcio;

//Heu d'implementar el que es requereix a cada mètode / propietat
public class GestorJDBC implements ProveedorPersistencia {

    /*
     Connexió a la base de dades
     */
    private Connection conn;

    /*
     PreparedStatement necessaris
     */

    /*
     Obtenir els registres d'un parc d'atraccions
     */
    private static String selectParcAtraccionsSQL;
    //Heu de crer la sentència sql select de la taula parcAtraccions
    //Camps: tots
    //Registres: tots els del codi de parc d'atraccions passat per paràmetre
    public void crearSQLSelect(int codi) throws SQLException {
        selectParcAtraccionsSQLSt.setString(1, String.valueOf(codi));
        resultat = selectParcAtraccionsSQLSt.executeQuery();
    }

    private PreparedStatement selectParcAtraccionsSQLSt;

    /*
     * Insertar un parc d'atraccions
     */
    private static String insertParcAtraccionsSQL;
    //Heu de crear la sentència sql que insereixi un registre en la taula
    //parc d'atraccions. Els valors dels camps seran els passat per paràmetre
    public void insertarParcAtraccions(int codi, String nom, String adreca) throws SQLException {
        insertParcAtraccionsSQLSt.setString(1, String.valueOf(codi));
        insertParcAtraccionsSQLSt.setString(1, nom);
        insertParcAtraccionsSQLSt.setString(1, adreca);
    }

    private PreparedStatement insertParcAtraccionsSQLSt;

    /*
     * Modificar el nom i adreça d'un parc d'atraccions
     */
    private static String updateParcAtraccionsSQL;
    //Heu de crear la sentència per actualitzar un registre del parc d'atraccions
    //Registre a actualitzar: el que correspongui al codi passat per paràmetre
    //Camps a actualitzar: nom i adreça amb els valors passats per a paràmetre 
    public void actualizarParcAtraccions(int codi, String nom, String adreca) throws SQLException {
        updateParcAtraccionsSQLSt.setString(1, nom);
        updateParcAtraccionsSQLSt.setString(2, adreca);
        updateParcAtraccionsSQLSt.setString(3, String.valueOf(codi));
    }

    private PreparedStatement updateParcAtraccionsSQLSt;

    /*
     * Taula coordinador
     */
    /*
     * Eliminar els coordinadors d'un parc d'atraccions
     */
    private static String deleteCoordinadorSQL;
    //Heu de crear la sentència per eliminar tots el coordinador d'un parc 
    //d'atraccions determinat.
    //Registres a eliminar: Tots els registres amb el codi de parc d'atraccions
    //igual al passat per paràmetre.
    public void borrarCoordinadores(int codi) throws SQLException {
        deleteCoordinadorSQLSt.setString(1, String.valueOf(codi));
    }

    private PreparedStatement deleteCoordinadorSQLSt;

    /*
     * Insertar un coordinador
     */
    private static String insertCoordinadorSQL;
    //Heu de crear la sentència sql que insereixi un registre en la taula
    //coordinadors. Els valors dels camps seran els passat per paràmetre
    public void insertarCoordinadores(int codi) throws SQLException {
        insertCoordinadorSQL = "DELETE FROM Coordinadors WHERE codiParcAtraccions = " + codi;
        
        if(insertCoordinadorSQLSt.executeUpdate()==0){ //No s'ha insertat....
            System.out.println("No s'ha insertat l'usuari");
        }else{
           System.out.println("S'ha insertat l'usuari"); 
        }
    }

    private PreparedStatement insertCoordinadorSQLSt;

    /*
     *Seleccionar els coordinadors d'un parc d'atraccions
     */
    private static String selectCoordinadorsSQL;
    //Heu de crer la sentència sql select de la taula coordinadors
    //Camps: tots
    //Registres: tots els del codi de parc d'atraccions passat per paràmetre    
    public void selectCoordinadores(int codi) throws SQLException {
        selectCoordinadorsSQL = "SELECT * FROM Coordinadors WHERE codiParcAtraccions = " + codi;
    }

    private PreparedStatement selectCoordinadorsSQLSt;

    /**
     * Estableix la connexió amb la base de dades. Crea les sentències
     * preparades.
     *
     * SQLException si es produeix una excepció a l'establir la connexió (en
     * aquest cas, assignareu el valor null a la connexió).
     */
    public void estableixConnexio() {
        //Heu d'establir la connexio JDBC amb la base de dades GestorParcAtraccions
        //Heu de crear els objectes PrepareStatement declarats com a atributs d'aquesta classe
        //amb les respectives sentències sql declarades com a propietats just sobre cadascun d'ells.
        //Heu de fer el catch de les possibles excepcions SQL mostrant el missatge
        //de l'excepció capturada mitjançant getMessage().
        String baseDades = "GestorParcAtraccions";
        String urlBaseDades = "jdbc:mysql://mser.mooo.com:3306/"+baseDades;
        String usuari = "root";
        String contrasenya =null; //No existeix contrasenya.
        ResultSet resultat = null; //De moment no hi ha cap resultat
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(urlBaseDades,usuari,contrasenya);
            
            selectParcAtraccionsSQLSt = conn.prepareStatement("SELECT * FROM parcAtraccions WHERE codi = ?" );
            insertParcAtraccionsSQLSt = conn.prepareStatement("INSERT INTO parcAtraccions VALUES (?, ?, ?)");
            updateParcAtraccionsSQLSt = conn.prepareStatement("UPDATE parcAtraccions SET nom:= ?, adreca:= ?" +
                                                                "WHERE codi = ?");
            deleteCoordinadorSQLSt = conn.prepareStatement("DELETE FROM Coordinadors WHERE codiParcAtraccions = ?");
            insertCoordinadorSQLSt = conn.prepareStatement("INSERT INTO Coordinadors VALUES (?, ?, ?, ?");
            selectCoordinadorsSQLSt = conn.prepareStatement("SELECT * FROM Coordinadors WHERE codiParcAtraccions = ?");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GestorJDBC.class.getName()).log(Level.SEVERE, null, ex);            
        } catch (SQLException ex) {
            Logger.getLogger(GestorJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }

    /**
     * Tanca la connexió i li assigna el valor null.
     *
     */
    public void tancaConnexio() throws SQLException {
        //Heu de tancar la connexió i assignar-li el valor null, es produeixi o no una excepció.
        if (conn != null){ //Si existeix la connexió....
            conn.close(); //Tanquem la connexió
        }
    }

    @Override
    public void desarParcAtraccions(String nomFitxer, ParcAtraccions parcAtraccions) throws ParcAtraccionsExcepcio {
        //Heu de desar el parc d'atraccions passat com a paràmetre en la base de dades:
        //S'ha de desar en la taula parcAtraccions (nomFitxer és el codi del parc d'atraccions)
        //Cada coordinador del parc d'atraccions, s'ha de desar com registre de la taula coordinador
        //Heu de tenir en compte que si el parc d'atraccions ja existeix a la base de dades,
        //aleshores heu de fer el següent:
        //- actualitzar el parc d'atraccions ja existent
        //- eliminar tots els coordinadors d'aquest parc d'atraccions de la taula coordinadors
        //  i després insertar els nous coordinadors.
        //Si al fer qualsevol operació es dona una excepció, llavors heu de llançar l'excepció ParcAtraccionsExcepció amb codi "GestorJDBC.desar"
        
    }

    @Override
    public ParcAtraccions carregarParcAtraccions(String nomFitxer) throws ParcAtraccionsExcepcio {
        //Heu de carregar el parc d'atraccions des de la base de dades (nomFitxer és el codi del parc d'atraccions)
        //Per fer això, heu de cercar el registre parc d'atraccions de la taula
        //parcsAtraccions amb codi = nomFitxer
        //A més, heu d'afegir els coordinadors al vector d'elements del parc d'atraccions a 
        //partir de la taula coordinadors.
        //Si al fer qualsevol operació es dona una excepció, llavors heu de llançar 
        //l'excepció ParcAtraccionsExcepció amb codi "GestorJDBC.carrega"
        //Si el nomFitxer donat no existeix a la taula parcAtraccions (és a dir, el codi = nomFitxer no existeix), 
        //aleshores heu de llançar l'excepció ParcAtraccionsExcepció amb codi "GestorJDBC.noexist"

    }

}
