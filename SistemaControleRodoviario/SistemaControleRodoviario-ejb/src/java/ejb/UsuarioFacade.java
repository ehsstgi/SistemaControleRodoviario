/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Eduardo
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario>{ //implements InterfaceRemota<Usuario>{

    @PersistenceContext(unitName = "SistemaControleRodoviario-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Usuario> verificaLogin(Usuario usuario) {
        Query verificaLogin = getEntityManager().createNamedQuery("verificaLogin");
        verificaLogin.setParameter("nome", usuario.getNome());
        verificaLogin.setParameter("senha", usuario.getSenha());
        return verificaLogin.getResultList();
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
}
