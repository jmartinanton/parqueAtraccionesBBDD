package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Francesca
 */
public class RegistreUsuaris {

    private String codi;
    private Date dataRegistre;
    private String nomAtraccio;
    private int valor;

    public RegistreUsuaris(String pCodi, Date pDataRegistre, String pNomAtraccio, int pValor) {
        codi = pCodi;
        dataRegistre = pDataRegistre;
        nomAtraccio = pNomAtraccio;
        valor = pValor;
    }

    public String getCodi() {
        return codi;
    }

    public void setCodi(String pCodi) {
        codi = codi;
    }

    public Date getDataRegistre() {
        return dataRegistre;
    }

    public void setDataRegistre(Date pDataRegistre) {
        dataRegistre = pDataRegistre;
    }

    public String getNomAtraccio() {
        return nomAtraccio;
    }

    public void setNomAtraccio(String pNomAtraccio) {
        nomAtraccio = pNomAtraccio;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int pValor) {
        valor = pValor;
    }

    public static RegistreUsuaris nouRegistre() {
        Scanner dades = new Scanner(System.in);
        String codi;
        Date dataRegistre = null;
        String nomAtraccio;
        int valor;
        System.out.println("Codi del registre:");
        codi = dades.next();
        dades.nextLine();
        System.out.println("Nom atracció:");
        nomAtraccio = dades.nextLine();
        System.out.println("Valor del registre:");
        valor = dades.nextInt();
        dades.nextLine();
        System.out.println("Data del registre (dd/mm/aaaa hh:mm:ss):");
        String dateFormat = "dd/MM/yyyy hh:mm:ss";
        //Aquest try-catch, s'ha de posar obligatoriament. Ja ho explicarem en la propera UF.
        try {
            dataRegistre = new SimpleDateFormat(dateFormat).parse(dades.nextLine());
        } catch (Exception e) {
        }

        return new RegistreUsuaris(codi, dataRegistre, nomAtraccio, valor);
    }

}
