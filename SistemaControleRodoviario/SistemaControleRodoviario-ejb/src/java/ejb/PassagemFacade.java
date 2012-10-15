/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Passagem;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Eduardo
 */
@Stateless (mappedName="ejb/PassagemFacade")
public class PassagemFacade extends AbstractFacade<Passagem>  implements PassagemLocal{

    @PersistenceContext(unitName = "SistemaControleRodoviario-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Passagem> passagemPorUsuario(Passagem passagem) {
        Query passagemPorUsuario = getEntityManager().createNamedQuery("passagemPorUsuario");
        passagemPorUsuario.setParameter("usuario", passagem.getUsuario());
        return passagemPorUsuario.getResultList();
    }

    public List<Passagem> passagemPorFuncionario(Passagem passagem) {
        Query passagemPorFuncionario = getEntityManager().createNamedQuery("passagemPorFuncionario");
        passagemPorFuncionario.setParameter("funcionario", passagem.getFuncionario());
        return passagemPorFuncionario.getResultList();
    }
    public List<Passagem> passagemWeb(Passagem passagem) {
        Query passagemWeb = getEntityManager().createNamedQuery("passagemWeb");
        return passagemWeb.getResultList();
    }
    public int countPorUsuario(Passagem passagem) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<Passagem> rt = cq.from(Passagem.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));    
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public PassagemFacade() {
        super(Passagem.class);
    }
}
