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
@Stateless(mappedName = "ejb/PassagemFacade")
public class PassagemFacade extends AbstractFacade<Passagem> implements PassagemLocal {

    @PersistenceContext(unitName = "SistemaControleRodoviario-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<Passagem> passagemPorUsuario(Passagem passagem) {
        Query passagemPorUsuario = getEntityManager().createNamedQuery("passagemPorUsuario");
        passagemPorUsuario.setParameter("usuario", passagem.getUsuario());
        return passagemPorUsuario.getResultList();
    }

    public List<Passagem> findRangePorUsuario(int[] range, Passagem passagem) {
        Query passagemPorUsuario = getEntityManager().createNamedQuery("passagemPorUsuario");
        passagemPorUsuario.setParameter("usuario", passagem.getUsuario());
        passagemPorUsuario.setMaxResults(range[1] - range[0]);
        passagemPorUsuario.setFirstResult(range[0]);
        return passagemPorUsuario.getResultList();
    }

    public int countPorUsuario(Passagem passagem) {
        Query passagemPorUsuario = getEntityManager().createNamedQuery("passagemPorUsuarioCount");
        passagemPorUsuario.setParameter("usuario", passagem.getUsuario());
        return ((Long) passagemPorUsuario.getSingleResult()).intValue();
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

    public PassagemFacade() {
        super(Passagem.class);
    }
}
