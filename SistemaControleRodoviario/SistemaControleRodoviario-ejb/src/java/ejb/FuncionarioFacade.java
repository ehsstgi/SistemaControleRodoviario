/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Funcionario;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Eduardo
 */
@Stateful(mappedName = "ejb/FuncionarioFacade")
    public class FuncionarioFacade extends AbstractFacade<Funcionario> implements FuncionarioRemote {

    @PersistenceContext(unitName = "SistemaControleRodoviario-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    
    public List<Funcionario> verificaLoginFuncionario(Funcionario funcionario) {
        Query verificaLoginFuncionario = getEntityManager().createNamedQuery("verificaLoginFuncionario");
        verificaLoginFuncionario.setParameter("nome", funcionario.getNome());
        verificaLoginFuncionario.setParameter("senha", funcionario.getSenha());
        return verificaLoginFuncionario.getResultList();
    }

    public FuncionarioFacade() {
        super(Funcionario.class);
    }
}
