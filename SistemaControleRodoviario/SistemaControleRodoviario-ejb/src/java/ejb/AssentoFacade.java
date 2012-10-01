/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Assento;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Eduardo
 */
@Stateless
public class AssentoFacade extends AbstractFacade<Assento> implements InterfaceRemota<Assento> {
    @PersistenceContext(unitName = "SistemaControleRodoviario-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AssentoFacade() {
        super(Assento.class);
    }
    
}
