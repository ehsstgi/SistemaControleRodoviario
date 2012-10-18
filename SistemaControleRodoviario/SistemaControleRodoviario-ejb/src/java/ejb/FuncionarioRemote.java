/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Funcionario;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Eduardo
 */
@Remote
public interface FuncionarioRemote extends InterfaceRemota<Funcionario> {

    List<Funcionario> verificaLoginFuncionario(Funcionario funcionario);
}
