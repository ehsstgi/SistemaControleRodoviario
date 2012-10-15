/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Linha;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Eduardo
 */
@Stateless(mappedName="ejb/LinhaFacade")
public class LinhaFacade extends AbstractFacade<Linha> implements InterfaceRemota<Linha>, InterfaceLocal<Linha> {
    @PersistenceContext(unitName = "SistemaControleRodoviario-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LinhaFacade() {
        super(Linha.class);
    }
    
}
