/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Rota;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Eduardo
 */
@Stateless (mappedName="ejb/RotaFacade", name="RotaFacade")
public class RotaFacade extends AbstractFacade<Rota> implements InterfaceRemota<Rota>, InterfaceLocal<Rota> {
    @PersistenceContext(unitName = "SistemaControleRodoviario-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RotaFacade() {
        super(Rota.class);
    }
    
}
