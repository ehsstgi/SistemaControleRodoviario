/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Eduardo
 */
@Local
public interface UsuarioLocal extends InterfaceRemota<Usuario> {
     public List<Usuario> verificaLogin(Usuario usuario);
    
}
