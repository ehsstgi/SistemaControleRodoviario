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
    public void create(T entity);

    public void edit(T entity) ;

    public void remove(T entity);

    public T find(Object id) ;

    public List<T> findAll() ;

    public List<T> findRange(int[] range) ;
    public int count();
    
}
