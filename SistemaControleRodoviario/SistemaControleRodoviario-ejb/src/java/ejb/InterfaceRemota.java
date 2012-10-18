/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Eduardo
 */
@Remote
public interface InterfaceRemota<T> {

   void create(T entity);

     void edit(T entity);

     void remove(T entity);

     T find(Object id);

     List<T> findAll();

     List<T> findRange(int[] range);

     int count();
}
