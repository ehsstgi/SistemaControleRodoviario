/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Eduardo
 */
@Local
public interface InterfaceLocal<T> {

    List<T> findAll();

    T find(Object id);
}
