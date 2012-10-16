/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Passagem;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Eduardo
 */
@Local
public interface PassagemLocal extends InterfaceRemota<Passagem> {

    public List<Passagem> passagemPorUsuario(Passagem passagem);

    public List<Passagem> findRangePorUsuario(int[] range, Passagem passagem);

    public int countPorUsuario(Passagem passagem);

    public List<Passagem> verificaPassagem(Passagem passagem);
}
