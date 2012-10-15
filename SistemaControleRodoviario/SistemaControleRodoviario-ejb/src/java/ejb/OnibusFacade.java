/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Onibus;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Eduardo
 */
@Stateless (mappedName="ejb/OnibusFacade")
public class OnibusFacade extends AbstractFacade<Onibus> implements  InterfaceRemota<Onibus>, InterfaceLocal<Onibus> {
    @PersistenceContext(unitName = "SistemaControleRodoviario-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OnibusFacade() {
        super(Onibus.class);
    }
    
}
