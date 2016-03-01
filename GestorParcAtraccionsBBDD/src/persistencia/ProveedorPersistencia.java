package persistencia;

import model.ParcAtraccions;
import principal.ParcAtraccionsExcepcio;

/**
 *
 * @author MiguelAngel
 */
public interface ProveedorPersistencia {
    public void desarParcAtraccions(String nomFitxer, ParcAtraccions parcAtraccions) throws ParcAtraccionsExcepcio;
    public ParcAtraccions carregarParcAtraccions(String nomFitxer) throws ParcAtraccionsExcepcio;
}
