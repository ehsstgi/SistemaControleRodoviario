/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.lang.Exception;
import java.util.Properties;
import javax.naming.InitialContext;

/**
 *
 * @author Eduardo
 */
public class ContextUtil {
    
    public static InitialContext getInitialContext(){
        try{
            Properties props = new Properties();
            props.load(new java.io.FileInputStream("jndi.properties"));
            return new InitialContext(props);
        }catch (Exception e ){
            e.printStackTrace();
            return null;
        
    }
        
    }
    
 
    
}
